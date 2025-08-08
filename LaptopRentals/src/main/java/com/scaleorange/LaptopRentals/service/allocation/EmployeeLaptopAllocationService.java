package com.scaleorange.LaptopRentals.service.allocation;

import com.scaleorange.LaptopRentals.dto.employee.LaptopAllocationRequest;
import com.scaleorange.LaptopRentals.entity.EmployeeLaptopAllocations;

public interface EmployeeLaptopAllocationService {

    public EmployeeLaptopAllocations assignItem(LaptopAllocationRequest allocationRequest);

    public Boolean unassign(Integer allocationId);


}
