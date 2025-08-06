package com.scaleorange.LaptopRentals.dto.payments;

import lombok.Data;

//@Data
//public class RazorpayRequest {
//    private Integer orderId;
//    private Double amount;
//    // in INR
//    private String currency;
//}

@Data
public class PaymentRequest {
    private Integer rentalId;
    private Double amount;
}
