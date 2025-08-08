package com.scaleorange.LaptopRentals.repo;

import com.scaleorange.LaptopRentals.entity.EmployeeLaptopAllocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeLaptopAllocationRepo extends JpaRepository<EmployeeLaptopAllocations,Integer> {
}
