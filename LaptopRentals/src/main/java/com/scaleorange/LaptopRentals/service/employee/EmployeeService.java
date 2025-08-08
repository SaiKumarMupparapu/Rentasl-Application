package com.scaleorange.LaptopRentals.service.employee;

import com.scaleorange.LaptopRentals.dto.employee.EmployeeRequest;
import com.scaleorange.LaptopRentals.dto.employee.EmployeeResponse;
import com.scaleorange.LaptopRentals.entity.Employee;

public interface EmployeeService {

    public EmployeeResponse registerEmployee(EmployeeRequest employeeRequest);
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest);
    public void deleteEmployee(Integer id);
}
