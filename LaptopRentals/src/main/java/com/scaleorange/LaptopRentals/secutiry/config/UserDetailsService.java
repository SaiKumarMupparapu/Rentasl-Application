package com.scaleorange.LaptopRentals.secutiry.config;

import com.scaleorange.LaptopRentals.entity.Organizations;
import com.scaleorange.LaptopRentals.repo.OrganizationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserDetailsService  implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private OrganizationRepo repo;

    @Override
    public org.springframework.security.core.userdetails.UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Organizations> optionaluser = repo.findByEmail(username);
        return optionaluser.map(UserInfo::new).orElseThrow(()->new UsernameNotFoundException(username+ " Not found"));
    }
}
