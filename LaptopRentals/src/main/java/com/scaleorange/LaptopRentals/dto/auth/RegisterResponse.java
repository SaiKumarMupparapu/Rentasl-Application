package com.scaleorange.LaptopRentals.dto.auth;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class RegisterResponse {

    String message;
    HttpStatus statusCode;
}
