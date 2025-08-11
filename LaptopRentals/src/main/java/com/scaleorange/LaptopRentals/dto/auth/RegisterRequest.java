package com.scaleorange.LaptopRentals.dto.auth;

import com.scaleorange.LaptopRentals.entity.Roles;
import lombok.Data;

@Data
public class RegisterRequest {

    private String name;
    private String email;
    private String password;
    private String phno;
    private Roles role;
    private String address;

}
