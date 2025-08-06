package com.scaleorange.LaptopRentals.repo;

import com.scaleorange.LaptopRentals.entity.RentalsItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalItemsRepo extends JpaRepository<RentalsItems,Integer> {
}
