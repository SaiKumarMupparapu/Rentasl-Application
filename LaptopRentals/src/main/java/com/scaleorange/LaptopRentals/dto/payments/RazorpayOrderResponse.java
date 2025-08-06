package com.scaleorange.LaptopRentals.dto.payments;

import lombok.Data;

@Data
public class RazorpayOrderResponse {
    private String id;
    private int amount;
    private int amount_paid;
    private int amount_due;
    private String currency;
    private String receipt;
    private String status;
    private int attempts;
    private long created_at;
}
