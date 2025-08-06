package com.scaleorange.LaptopRentals.dto.products;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    private Integer laptopId;
    private String brand;
    private String model;
    private String serialNumber;
    private String specs;
    private Double currentRatePerMonth;
    private Date updatedDate;
    private Integer providerId;
    private String providerName;
}
