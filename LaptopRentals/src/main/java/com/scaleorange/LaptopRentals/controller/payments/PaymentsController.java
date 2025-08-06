package com.scaleorange.LaptopRentals.controller.payments;

import com.razorpay.Order;
import com.razorpay.RazorpayException;
import com.scaleorange.LaptopRentals.dto.payments.RazorpayOrderResponse;
import com.scaleorange.LaptopRentals.dto.payments.RazorpayRequest;
import com.scaleorange.LaptopRentals.service.payments.PaymentsService;
import jdk.jfr.ContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/razor")
public class PaymentsController {

    @Autowired
    private PaymentsService paymentsService;

    @PostMapping(value = "/pay",produces = "application/json")
    public ResponseEntity<RazorpayOrderResponse> payment(@RequestBody RazorpayRequest razorpayRequest){
        try {
            RazorpayOrderResponse orderResponse = paymentsService.makePaymentOrder(razorpayRequest);

            return new ResponseEntity<>(orderResponse, HttpStatus.CREATED);
        } catch (RazorpayException e) {
            throw new RuntimeException(e);
        }

    }


}
