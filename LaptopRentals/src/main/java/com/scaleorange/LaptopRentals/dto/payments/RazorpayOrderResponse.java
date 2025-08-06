package com.scaleorange.LaptopRentals.dto.payments;

import lombok.Data;

import java.util.Date;

@Data
public class RazorpayOrderResponse {
    private String id;
    private Double amount;
    private Double amount_paid;
    private Double amount_due;
    private String currency;
    private String receipt;
    private String status;
    private int attempts;
    private Date created_at;
}
