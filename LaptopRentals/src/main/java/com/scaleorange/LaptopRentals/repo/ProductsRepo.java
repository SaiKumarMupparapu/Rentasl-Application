package com.scaleorange.LaptopRentals.repo;

import com.scaleorange.LaptopRentals.entity.Laptops;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepo extends JpaRepository<Laptops,Integer> {

}
