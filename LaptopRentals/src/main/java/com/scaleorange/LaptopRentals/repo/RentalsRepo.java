package com.scaleorange.LaptopRentals.repo;

import com.scaleorange.LaptopRentals.entity.LaptopRentals;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RentalsRepo extends JpaRepository<LaptopRentals,Integer> {
}
