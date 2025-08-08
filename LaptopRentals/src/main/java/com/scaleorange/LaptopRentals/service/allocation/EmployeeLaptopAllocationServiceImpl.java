package com.scaleorange.LaptopRentals.service.allocation;

import com.scaleorange.LaptopRentals.dto.employee.LaptopAllocationRequest;
import com.scaleorange.LaptopRentals.entity.Employee;
import com.scaleorange.LaptopRentals.entity.EmployeeLaptopAllocations;
import com.scaleorange.LaptopRentals.entity.Laptops;
import com.scaleorange.LaptopRentals.repo.EmployeeLaptopAllocationRepo;
import com.scaleorange.LaptopRentals.repo.EmployeeRepo;
import com.scaleorange.LaptopRentals.repo.ProductsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.util.Date;

@Service
public class EmployeeLaptopAllocationServiceImpl implements EmployeeLaptopAllocationService{

    @Autowired
    private EmployeeLaptopAllocationRepo laptopAllocationRepo;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private EmployeeRepo employeeRepo;

    @Override
    public EmployeeLaptopAllocations assignItem(LaptopAllocationRequest allocationRequest) {

        EmployeeLaptopAllocations laptopAllocations = new EmployeeLaptopAllocations();

        laptopAllocations.setAllocationStart(new Date());

        Laptops laptop = productsRepo.findById(allocationRequest.getLaptopId()).orElseThrow(() -> new RuntimeException("Product Not Found"));

        Employee employee = employeeRepo.findById(allocationRequest.getEmployeeId()).orElseThrow(() -> new RuntimeException("Employee Not Found"));

        employee.setIsAvilableToAssign(Boolean.FALSE);

        employee= employeeRepo.save(employee);

        laptopAllocations.setLaptop(laptop);
        laptopAllocations.setEmployee(employee);

        return laptopAllocationRepo.save(laptopAllocations);
    }

    @Override
    public Boolean unassign(Integer allocationId) {
        EmployeeLaptopAllocations laptopAllocations = laptopAllocationRepo.findById(allocationId).orElseThrow(() -> new RuntimeException("Invalid allocation id"));
        laptopAllocations.setAllocationEnd(new Date());

        EmployeeLaptopAllocations saved = laptopAllocationRepo.save(laptopAllocations);

        Employee employee = saved.getEmployee();
        employee.setIsAvilableToAssign(Boolean.FALSE);

        employee = employeeRepo.save(employee);

        return true;
    }
}
