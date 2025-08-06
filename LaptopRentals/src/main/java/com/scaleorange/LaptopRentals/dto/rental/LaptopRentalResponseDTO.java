package com.scaleorange.LaptopRentals.dto.rental;

import com.scaleorange.LaptopRentals.dto.auth.User;
import com.scaleorange.LaptopRentals.dto.products.ItemResponse;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class LaptopRentalResponseDTO {

    private Integer rentalId;

    private Date rentalStart;

    private Date rentalEnd;

    private Double advanceAmount;

    private User provider;

    private User rentedTo;

    private List<ItemResponse> rentalItems;
}

