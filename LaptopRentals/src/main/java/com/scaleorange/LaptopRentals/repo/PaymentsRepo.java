package com.scaleorange.LaptopRentals.repo;

import com.scaleorange.LaptopRentals.entity.Payments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentsRepo extends JpaRepository<Payments,Integer> {
}
