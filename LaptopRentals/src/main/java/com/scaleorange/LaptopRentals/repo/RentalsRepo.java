package com.scaleorange.LaptopRentals.repo;

import com.scaleorange.LaptopRentals.entity.LaptopRentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RentalsRepo extends JpaRepository<LaptopRentals,Integer> {

        // For consumer
        List<LaptopRentals> findByRentedToOrganizationId(Integer organizationId);

        // For provider
        List<LaptopRentals> findByProviderOrganizationId(Integer organizationId);

        // Find by rental ID
        Optional<LaptopRentals> findByRentalId(Integer rentalId);


//
//    // Orders where this org is the customer
//    List<LaptopRentals> findByRentedToId(Integer rentedToId);
//
//    Optional<LaptopRentals> findByRentalId(Integer rentalId);
}
