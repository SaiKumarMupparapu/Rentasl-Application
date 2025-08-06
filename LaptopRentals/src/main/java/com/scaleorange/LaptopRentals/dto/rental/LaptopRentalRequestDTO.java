package com.scaleorange.LaptopRentals.dto.rental;

import lombok.Data;

import java.util.List;

@Data
public class LaptopRentalRequestDTO {

    private Double advanceAmount;

    private Integer providerId;    // Organization ID who is providing

    private Integer rentedToId;    // Organization ID who is receiving

    private List<RentalItemRequestDTO> rentalItems;
}
