package com.scaleorange.LaptopRentals.service.organizations;

import com.scaleorange.LaptopRentals.dto.auth.LoginRequest;
import com.scaleorange.LaptopRentals.dto.auth.LoginResponse;
import com.scaleorange.LaptopRentals.dto.auth.RegisterRequest;
import com.scaleorange.LaptopRentals.dto.auth.User;
import com.scaleorange.LaptopRentals.entity.Organizations;
import com.scaleorange.LaptopRentals.repo.OrganizationRepo;
import com.scaleorange.LaptopRentals.secutiry.jwt.utils.JwtUtil;
import com.scaleorange.LaptopRentals.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class OrganizationServiceImpl implements OrganizationService{

    @Autowired
    private OrganizationRepo organizationRepo;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager manager;

    @Override
    public Boolean register(RegisterRequest register) {
        Organizations organizations = Mapper.convertToOrganization(register);
        organizations.setPassword(encoder.encode(organizations.getPassword()));
        Organizations saved = organizationRepo.save(organizations);
        return saved.getOrganizationId() != null;
    }

    @Override
    public LoginResponse login(LoginRequest login) {
           String token=generateToken(login.getEmail());
        return new LoginResponse(login.getEmail(), token);

    }

    @Override
    public String generateToken(String email) {
        Organizations user = organizationRepo.findByEmail(email).orElseThrow(() -> new RuntimeException("User Not Found"));
        return jwtUtil.generateToken(user.getEmail(),String.valueOf(user.getRole()));
    }

    @Override
    public User getById(Integer id) {
        Organizations user = organizationRepo.findById(id).orElseThrow(() -> new RuntimeException("User Not Found"));
        return Mapper.convertToUser(user);
    }
}
