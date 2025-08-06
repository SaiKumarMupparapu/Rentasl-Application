package com.scaleorange.LaptopRentals.service.payments;

import com.razorpay.RazorpayException;
import com.scaleorange.LaptopRentals.dto.payments.RazorpayOrderResponse;
import com.scaleorange.LaptopRentals.dto.payments.RazorpayRequest;

public interface PaymentsService {

    public RazorpayOrderResponse makePaymentOrder(RazorpayRequest request) throws RazorpayException;


}
