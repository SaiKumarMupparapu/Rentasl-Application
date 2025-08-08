package com.scaleorange.LaptopRentals.controller.payments;


import com.razorpay.RazorpayException;
import com.scaleorange.LaptopRentals.dto.payments.PaymentRequest;

import com.scaleorange.LaptopRentals.dto.payments.RazorpayResponse;
import com.scaleorange.LaptopRentals.entity.Payments;
import com.scaleorange.LaptopRentals.service.payments.PaymentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/razor")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @PostMapping(value = "/pay", produces = "application/json")
    public ResponseEntity<Payments> payment(@RequestBody PaymentRequest paymentRequest) {
        try {
            Payments payments = paymentsService.makeRazorpayOrder(paymentRequest);

            return new ResponseEntity<>(payments, HttpStatus.CREATED);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

    }




    @PostMapping("/payments/verify")
    public ResponseEntity<String> verifyPayment(@RequestBody RazorpayResponse response) {
        try {
            Boolean status = paymentsService.verifyPayment(response);
            if (!status) {
                return ResponseEntity.status(400).body("Invalid payment signature!");
            }
            return ResponseEntity.ok("Payment verified successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error verifying payment");
        }
    }

}



