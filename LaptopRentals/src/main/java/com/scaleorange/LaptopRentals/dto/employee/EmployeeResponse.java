package com.scaleorange.LaptopRentals.dto.employee;

import lombok.Data;

@Data
public class EmployeeResponse {
    private Integer id;
    private String name;
    private String email;
    private String phno;
    private Integer companyId;
    private String companyOwnerName;
}
