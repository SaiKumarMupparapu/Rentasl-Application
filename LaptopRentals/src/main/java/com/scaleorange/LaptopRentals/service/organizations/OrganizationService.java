package com.scaleorange.LaptopRentals.service.organizations;

import com.scaleorange.LaptopRentals.dto.auth.LoginRequest;
import com.scaleorange.LaptopRentals.dto.auth.LoginResponse;
import com.scaleorange.LaptopRentals.dto.auth.RegisterRequest;
import com.scaleorange.LaptopRentals.dto.auth.User;

public interface OrganizationService {

    public Boolean register(RegisterRequest register);

    public LoginResponse login(LoginRequest login);

    public String generateToken(String email);

    public User getById(Integer id);

}
