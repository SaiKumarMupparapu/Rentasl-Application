package com.scaleorange.LaptopRentals.service.payments;

import com.razorpay.RazorpayException;
import com.scaleorange.LaptopRentals.dto.payments.PaymentRequest;

import com.scaleorange.LaptopRentals.dto.payments.RazorpayResponse;
import com.scaleorange.LaptopRentals.entity.Payments;

public interface PaymentsService {

    public Payments makeRazorpayOrder(PaymentRequest request) throws RazorpayException;

   public Boolean verifyPayment(RazorpayResponse response) throws Exception;
}
