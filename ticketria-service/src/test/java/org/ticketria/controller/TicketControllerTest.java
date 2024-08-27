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
import org.ticketria.dto.request.RequestPassenger;
import org.ticketria.dto.request.TicketPurchaseRequest;
import org.ticketria.dto.request.TicketRequest;
import org.ticketria.dto.response.TicketPurchaseResponse;
import org.ticketria.model.enums.Gender;
import org.ticketria.service.TicketService;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@ExtendWith(MockitoExtension.class)
public class TicketControllerTest {
    @InjectMocks
    private TicketController ticketController;
    @Mock
    private TicketService ticketService;
    ObjectMapper objectMapper;
    private MockMvc mockMvc;


    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
        objectMapper = new ObjectMapper().registerModule(new JavaTimeModule());
    }


    @Test
    void purchaseTickets() throws Exception {

        String email = "alex10@gmail.com";
        String tripNumber = "TRIP123";
        List<TicketPurchaseRequest> request = buildTicketPurchaseRequest();
        when(ticketService.validateTicketPurchase(anyList(), eq(tripNumber), eq(email))).thenReturn(true);

        mockMvc.perform(post("/api/v1/trips/user/purchase/{email}/{tripNumber}", email, tripNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void purchaseTicketsInvalid() throws Exception {

        String email = "alex10@gmail.com";
        String tripNumber = "TRIP123";
        List<TicketPurchaseRequest> request = buildTicketPurchaseRequest();
        when(ticketService.validateTicketPurchase(anyList(), eq(tripNumber), eq(email))).thenReturn(false);

        mockMvc.perform(post("/api/v1/trips/user/purchase/{email}/{tripNumber}", email, tripNumber)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void getTicketsByEmail() throws Exception {

        String email = "alex10@gmail.com";
        TicketPurchaseResponse response = new TicketPurchaseResponse(new ArrayList<>(), email, 0);
        when(ticketService.getTicketsByEmail(email)).thenReturn(response);

        mockMvc.perform(get("/api/v1/trips/user/tickets/{email}", email))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email));
    }

    private List<TicketPurchaseRequest> buildTicketPurchaseRequest() {
        List<TicketPurchaseRequest> requests = new ArrayList<>();
        TicketPurchaseRequest ticketPurchaseRequest = new TicketPurchaseRequest();
        TicketRequest ticketRequest = new TicketRequest();
        ticketRequest.setSeatNumber(1);
        ticketRequest.setPassenger(new RequestPassenger("Huseyin", "Balicak", "123456789", Gender.MALE));
        ticketPurchaseRequest.setTickets(List.of(ticketRequest));
        requests.add(ticketPurchaseRequest);
        return requests;
    }
}