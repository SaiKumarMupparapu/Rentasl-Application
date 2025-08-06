package com.scaleorange.LaptopRentals.repo;

import com.scaleorange.LaptopRentals.entity.Organizations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OrganizationRepo extends JpaRepository<Organizations,Integer> {

    Optional<Organizations> findByEmailAndPassword(String email, String password);


    Optional<Organizations> findByEmail(String email);
}
