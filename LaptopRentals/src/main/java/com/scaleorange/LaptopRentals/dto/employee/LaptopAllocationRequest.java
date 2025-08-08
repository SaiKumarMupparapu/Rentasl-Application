package com.scaleorange.LaptopRentals.dto.employee;

import lombok.Data;

@Data
public class LaptopAllocationRequest {
    private Integer laptopId;
    private Integer employeeId;
}
