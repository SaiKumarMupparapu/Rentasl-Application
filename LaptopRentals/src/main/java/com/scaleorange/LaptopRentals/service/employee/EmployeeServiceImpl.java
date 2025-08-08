package com.scaleorange.LaptopRentals.service.employee;

import com.scaleorange.LaptopRentals.dto.employee.EmployeeRequest;
import com.scaleorange.LaptopRentals.dto.employee.EmployeeResponse;
import com.scaleorange.LaptopRentals.entity.Employee;
import com.scaleorange.LaptopRentals.repo.EmployeeRepo;
import com.scaleorange.LaptopRentals.repo.OrganizationRepo;
import com.scaleorange.LaptopRentals.utils.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepo employeeRepo;
    @Autowired
    private OrganizationRepo organizationRepo;

    @Override
    public EmployeeResponse registerEmployee(EmployeeRequest employeeRequest) {
        Employee employee = Mapper.convertToEmployee(employeeRequest);
        employee.setIsAvilableToAssign(Boolean.TRUE);
        employee.setOrganization(
                organizationRepo.findById(employeeRequest.getCompanyId())
                        .orElseThrow(()-> new RuntimeException("Invalid Company id"))
        );
        Employee saved = employeeRepo.save(employee);

        EmployeeResponse response = new EmployeeResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setPhno(saved.getPhno());
        response.setCompanyId(saved.getOrganization().getOrganizationId());
        response.setCompanyOwnerName(saved.getOrganization().getName());
        return response;
    }

    @Override
    public EmployeeResponse updateEmployee(EmployeeRequest employeeRequest) {
        Employee employee = employeeRepo.findById(employeeRequest.getId()).orElseThrow(() -> new RuntimeException("Employee Not Found"));
        if(employeeRequest.getName()!=null)employee.setName(employeeRequest.getName());
        if(employeeRequest.getEmail()!=null)employee.setEmail(employeeRequest.getEmail());
        if(employeeRequest.getPhno()!=null)employee.setPhno(employeeRequest.getPhno());

        Employee saved = employeeRepo.save(employee);

        EmployeeResponse response = new EmployeeResponse();
        response.setId(saved.getId());
        response.setName(saved.getName());
        response.setEmail(saved.getEmail());
        response.setPhno(saved.getPhno());
        response.setCompanyId(saved.getOrganization().getOrganizationId());
        response.setCompanyOwnerName(saved.getOrganization().getName());
        return response;
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepo.deleteById(id);
    }
}
