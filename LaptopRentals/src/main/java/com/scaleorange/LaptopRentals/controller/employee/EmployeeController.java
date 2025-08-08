package com.scaleorange.LaptopRentals.controller.employee;

import com.scaleorange.LaptopRentals.dto.employee.EmployeeRequest;
import com.scaleorange.LaptopRentals.dto.employee.EmployeeResponse;
import com.scaleorange.LaptopRentals.entity.Employee;
import com.scaleorange.LaptopRentals.service.employee.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/employee")
@PreAuthorize("hasRole('COMPANY')")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/register")
    public ResponseEntity<EmployeeResponse> registerEmployee(@RequestBody EmployeeRequest employeeRequest){
       return new ResponseEntity<>(employeeService.registerEmployee(employeeRequest), HttpStatus.CREATED);
    }
    @PostMapping("/update")
    public ResponseEntity<EmployeeResponse> updateEmployee(@RequestBody EmployeeRequest employeeRequest){
        return new ResponseEntity<>(employeeService.updateEmployee(employeeRequest), HttpStatus.OK);
    }

    @DeleteMapping("/remove")
    public ResponseEntity<String> updateEmployee(@RequestParam("id") Integer id){
        try{
            employeeService.deleteEmployee(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return new ResponseEntity<>("Employee deleted Successfully",HttpStatus.OK);
    }

}
