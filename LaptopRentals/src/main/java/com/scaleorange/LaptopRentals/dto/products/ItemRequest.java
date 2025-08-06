package com.scaleorange.LaptopRentals.dto.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemRequest {

    private Integer laptopId;
    private String brand;
    private String model;
    private String serialNumber;
    private String specs;
    private Double currentRatePerMonth;
    private Integer providerId;

}
