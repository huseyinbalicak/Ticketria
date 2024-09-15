package org.ticketria.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.ticketria.client.payment.service.PaymentClientService;
import org.ticketria.client.payment.service.dto.request.PaymentRequest;
import org.ticketria.client.payment.service.dto.response.enums.PaymentMethod;
import org.ticketria.client.user.response.UserResponse;
import org.ticketria.client.user.response.enums.UserType;
import org.ticketria.client.user.service.UserClientService;
import org.ticketria.converter.TicketPassengerConverter;
import org.ticketria.dto.request.RequestPassenger;
import org.ticketria.dto.request.SendNotificationRequest;
import org.ticketria.dto.request.TicketPurchaseRequest;
import org.ticketria.dto.request.TicketRequest;
import org.ticketria.dto.response.PurchasedTicketInformationResponse;
import org.ticketria.dto.response.TicketPurchaseResponse;
import org.ticketria.dto.response.TicketResponse;
import org.ticketria.exception.*;
import org.ticketria.model.Passenger;
import org.ticketria.model.Ticket;
import org.ticketria.model.Trip;
import org.ticketria.model.enums.Gender;
import org.ticketria.model.enums.NotificationType;
import org.ticketria.producer.RabbitMqProducer;
import org.ticketria.repository.PassengerRepository;
import org.ticketria.repository.TicketRepository;
import org.ticketria.repository.TripRepository;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TicketService {


    private final TripRepository tripRepository;
    private final TicketRepository ticketRepository;
    private final PassengerRepository passengerRepository;
    private final UserClientService userClientService;
    private final PaymentClientService paymentClientService;
    private final RabbitMqProducer rabbitMqProducer;


     public boolean validateTicketPurchase(List<TicketPurchaseRequest> request, String tripNumber, String email) {

        UserResponse userResponse = userClientService.getUserByEmail(email);

        Trip foundTrip = tripRepository.findByTripNumber(tripNumber);

        if (foundTrip == null) {
            throw new TripNotFoundException(tripNumber);
        }

         BigDecimal totalTicketPrice=calculateTotalPrice(request, foundTrip, userResponse.getId());

         validatePassengerCounts(request,tripNumber, userResponse.getId(),userResponse.getUserType());
         validateSeatNumbers(request, foundTrip);
         saveTicketsAndPassengers(request, foundTrip, userResponse.getId());

        //TODO :odeme yapıldı, yapılmadı response olarak dön, bilet alma işlemini gerçekleştir.
         paymentClientService.createPayment(new PaymentRequest(totalTicketPrice, PaymentMethod.CREDIT_CARD,userResponse.getEmail(),request));

         /*rabbitMqProducer.sendNotification(new SendEmailRequest(userResponse.getEmail(),totalTicketPrice,
                foundTrip.getOriginCity()
         ,foundTrip.getDestinationCity(),foundTrip.getVehicleType()));*/

         rabbitMqProducer.sendNotification(new SendNotificationRequest(userResponse.getEmail(),userResponse.getPhoneNumber(),totalTicketPrice,
                 foundTrip.getOriginCity()
                 ,foundTrip.getDestinationCity(),foundTrip.getVehicleType(), List.of(NotificationType.EMAIL,NotificationType.SMS)));

         return true;

    }

    public List<PurchasedTicketInformationResponse> purchasedTicketInformationResponse(String email/*,String tripNumber*/ )
    {
        UserResponse userResponse = userClientService.getUserByEmail(email);
        List<Ticket> tickets = ticketRepository.findByUserId(userResponse.getId());

        List<PurchasedTicketInformationResponse> responses = new ArrayList<>();
        for (Ticket ticket : tickets) {
            responses.add(new PurchasedTicketInformationResponse(
                    Collections.singletonList(ticket),
                    userResponse
            ));
        }

        return responses;

    }

    private void validatePassengerCounts(List<TicketPurchaseRequest> request,String tripNumber, Long id,UserType userType) {
        long totalPassengers = passengerRepository.countPassengersByTripNumberAndUserId(tripNumber, id);

        long requestTotalPassengers = 0;
        long malePassengerCount = 0;


        for (TicketPurchaseRequest ticketPurchaseRequest : request) {
            for (TicketRequest ticketRequest : ticketPurchaseRequest.getTickets()) {
                requestTotalPassengers++;
                if (ticketRequest.getPassenger().getGender().equals(Gender.MALE)) {
                    malePassengerCount++;
                }
            }
        }

        if (userType == UserType.INDIVIDUAL) {
            if (totalPassengers+requestTotalPassengers > 5) {
                throw new MaxTicketsPerIndividualExceededException();
            }

            if (malePassengerCount > 2) {
                throw new MaxMaleTicketsPerIndividualExceededException();
            }
        } else if (userType == UserType.CORPORATE) {
            if (requestTotalPassengers + totalPassengers > 40) {
                throw new MaxTicketsPerCorporateExceededException();
            }
        }
    }

    private void validateSeatNumbers(List<TicketPurchaseRequest> requests, Trip trip) {

        Set<Integer> seatNumbersFromRequests = new HashSet<>();

        for (TicketPurchaseRequest ticketPurchaseRequest : requests) {
            for (TicketRequest ticketRequest : ticketPurchaseRequest.getTickets()) {
                if (!seatNumbersFromRequests.add(ticketRequest.getSeatNumber())) {
                    throw new DuplicateSeatNumberException(ticketRequest.getSeatNumber());
                }
            }
        }

        for (Integer seatNumber : seatNumbersFromRequests) {
            long existingTicketsCount = ticketRepository.countByTripIdAndSeatNumber(trip.getId(), seatNumber);
            if (existingTicketsCount > 0) {
                throw new DuplicateSeatNumberException(seatNumber);
            }
        }
    }


    private void saveTicketsAndPassengers(List<TicketPurchaseRequest> request, Trip foundTrip, Long userId) {

        for (TicketPurchaseRequest ticketPurchaseRequest : request) {
            for (TicketRequest ticketRequest : ticketPurchaseRequest.getTickets()) {
                Ticket ticket = TicketPassengerConverter.convertToTicket(ticketRequest, foundTrip, userId);
                ticket = ticketRepository.save(ticket);

                RequestPassenger passenger = ticketRequest.getPassenger();
                Passenger passengerEntity = TicketPassengerConverter.convertToPassenger(passenger, ticket);

                ticket.setPassenger(passengerEntity);

                passengerRepository.save(passengerEntity);

            }
        }
    }

    private BigDecimal calculateTotalPrice(List<TicketPurchaseRequest> request, Trip foundTrip, Long userId) {

        BigDecimal totalPrice = BigDecimal.ZERO;

        for (TicketPurchaseRequest ticketPurchaseRequest : request) {
            for (TicketRequest ticketRequest : ticketPurchaseRequest.getTickets()) {
                Ticket ticket = TicketPassengerConverter.convertToTicket(ticketRequest, foundTrip, userId);

                BigDecimal increment = new BigDecimal(String.valueOf(ticket.getPrice()));
                totalPrice = totalPrice.add(increment);

            }
        }
        return totalPrice;
    }


    public TicketPurchaseResponse getTicketsByEmail(String email) {

        UserResponse userResponse = userClientService.getUserByEmail(email);

        List<Ticket> tickets = ticketRepository.findByUserId(userResponse.getId());

        List<TicketResponse> ticketResponses = tickets.stream()
                .map(TicketPassengerConverter::toTicketResponse)
                .collect(Collectors.toList());

        return new TicketPurchaseResponse(
                ticketResponses,
                email,
                ticketResponses.size()

        );
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

}
