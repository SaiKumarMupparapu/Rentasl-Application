package com.scaleorange.LaptopRentals.dto.payments;

import lombok.Data;

@Data
public class RazorpayResponse {
    private String razorpayOrderId;
    private String razorpayPaymentId;
    private String razorpaySignature;
}

