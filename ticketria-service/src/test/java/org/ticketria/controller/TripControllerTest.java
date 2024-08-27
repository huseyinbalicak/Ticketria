package org.ticketria.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.ticketria.converter.TripConverter;
import org.ticketria.dto.request.TripRequest;
import org.ticketria.model.Trip;
import org.ticketria.model.enums.VehicleType;
import org.ticketria.service.TripService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(MockitoExtension.class)
public class TripControllerTest {

    @InjectMocks
    private TripController tripController;
    @Mock
    private TripService tripService;
    @Mock
    private TripConverter tripConverter;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(tripController).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }

    @Test
    void createTrip() throws Exception {

        TripRequest tripRequest = new TripRequest("Edirne", "Iğdır", LocalDateTime.now(), LocalDateTime.now().plusDays(1), VehicleType.BUS, BigDecimal.valueOf(100));
        Trip savedTrip = new Trip();
        savedTrip.setTripNumber("TRIP123");
        savedTrip.setOriginCity(tripRequest.getOriginCity());
        savedTrip.setDestinationCity(tripRequest.getDestinationCity());
        savedTrip.setDepartureDate(LocalDateTime.now());
        savedTrip.setArrivalDate(LocalDateTime.now().plusDays(1));
        savedTrip.setVehicleType(VehicleType.BUS);
        savedTrip.setPrice(BigDecimal.valueOf(100));


        when(tripConverter.convertToEntity(any(TripRequest.class))).thenReturn(savedTrip);
        when(tripService.addTrip(any(Trip.class))).thenReturn(savedTrip);


        mockMvc.perform(post("/api/v1/trips")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(tripRequest)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tripNumber").value("TRIP123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.originCity").value(tripRequest.getOriginCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.destinationCity").value(tripRequest.getDestinationCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(100));
    }

    @Test
    void getTrip() throws Exception {

        Trip trip = new Trip();
        trip.setTripNumber("TRIP123");
        trip.setOriginCity("Edirne");
        trip.setDestinationCity("Iğdır");
        trip.setDepartureDate(LocalDateTime.now());
        trip.setArrivalDate(LocalDateTime.now().plusDays(1));
        trip.setVehicleType(VehicleType.BUS);
        trip.setPrice(BigDecimal.valueOf(100));


        when(tripService.findTrip(anyString())).thenReturn(trip);


        mockMvc.perform(get("/api/v1/trips/{tripNumber}", "TRIP123"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.tripNumber").value(trip.getTripNumber()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.originCity").value(trip.getOriginCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.destinationCity").value(trip.getDestinationCity()))
                .andExpect(MockMvcResultMatchers.jsonPath("$.price").value(trip.getPrice()));
    }


 /*   @Test
    void searchTrips() throws Exception {

        TripSearchRequest searchRequest = new TripSearchRequest();
        searchRequest.setOriginCity("Edirne");
        searchRequest.setDestinationCity("Iğdır");

        Trip trip = new Trip();
        trip.setTripNumber("TRIP123");
        trip.setOriginCity("Edirne");
        trip.setDestinationCity("Iğdır");
        trip.setDepartureDate(LocalDateTime.now());
        trip.setArrivalDate(LocalDateTime.now().plusDays(1));
        trip.setVehicleType(VehicleType.BUS);
        trip.setPrice(BigDecimal.valueOf(100));

        List<Trip> trips = List.of(trip);
        Page<Trip> tripPage = new PageImpl<>(trips, PageRequest.of(0, 10), trips.size());


        when(tripService.getAllTrips(any(TripSearchRequest.class), any(Pageable.class))).thenReturn(tripPage);


        mockMvc.perform(get("/api/v1/trips/search")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(searchRequest)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].tripNumber").value("TRIP123"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].originCity").value("Edirne"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].destinationCity").value("Iğdır"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data[0].price").value(100));
    }
*/
    @Test
    void cancelTrip() throws Exception {

        String tripNumber = "TRIP123";

        mockMvc.perform(delete("/api/v1/trips/{tripNumber}", tripNumber))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
