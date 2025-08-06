package com.scaleorange.LaptopRentals.controller.organization;

import com.scaleorange.LaptopRentals.dto.auth.LoginRequest;
import com.scaleorange.LaptopRentals.dto.auth.LoginResponse;
import com.scaleorange.LaptopRentals.dto.auth.RegisterRequest;
import com.scaleorange.LaptopRentals.dto.auth.RegisterResponse;
import com.scaleorange.LaptopRentals.service.organizations.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rentals")
public class OrganizationController {

    @Autowired
    private OrganizationService organizationService;
    @Autowired
    private AuthenticationManager manager;

    @PostMapping("/register")
    public RegisterResponse register(@RequestBody RegisterRequest register){
        RegisterResponse response = new RegisterResponse();


        if (organizationService.register(register)){
            response.setMessage("Successfully Registred");
            response.setStatusCode(HttpStatus.OK);
        }
        else {
            response.setMessage("Unable to register ! Try again");
            response.setStatusCode(HttpStatus.BAD_REQUEST);
        }

        return response;

    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest login){
        Authentication authenticated = manager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));
       if(authenticated.isAuthenticated()){
           return organizationService.login(login);
       }else{
           throw new RuntimeException("Invalid credintials");
       }


    }
}
