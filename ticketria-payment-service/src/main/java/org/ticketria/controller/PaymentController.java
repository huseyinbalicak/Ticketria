package org.ticketria.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.ticketria.dto.request.PaymentRequest;
import org.ticketria.dto.response.GenericResponse;
import org.ticketria.dto.response.PaymentResponse;
import org.ticketria.service.PaymentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/payments")
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public GenericResponse<PaymentResponse> createPayment(@RequestBody PaymentRequest request){
        return GenericResponse.success(paymentService.createPayment(request), HttpStatus.CREATED);
    }

    @GetMapping
    public GenericResponse<List<PaymentResponse>> getAllPayments(){
        return GenericResponse.success(paymentService.getAllPayments(),HttpStatus.OK);
    }

}
