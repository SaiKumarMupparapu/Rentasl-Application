package com.scaleorange.LaptopRentals.dto.auth;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer organizationId;

    private String name;
    private String email;
    private String password;
    private String phno;
    private String role;
    private String address;

    private Date createdDate;
}
