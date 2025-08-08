package com.scaleorange.LaptopRentals.controller.allocation;

import com.scaleorange.LaptopRentals.dto.employee.LaptopAllocationRequest;
import com.scaleorange.LaptopRentals.entity.EmployeeLaptopAllocations;
import com.scaleorange.LaptopRentals.service.allocation.EmployeeLaptopAllocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/allocation")
@PreAuthorize("hasAuthority('COMPANY')")
public class LaptopAllocationController {

    @Autowired
    private EmployeeLaptopAllocationService laptopAllocationService;

    @PostMapping("/assign")
    public ResponseEntity<EmployeeLaptopAllocations> assign(@RequestBody LaptopAllocationRequest allocationRequest){
        return new ResponseEntity<>(laptopAllocationService.assignItem(allocationRequest), HttpStatus.CREATED);
    }

    @PutMapping("/unassign")
    public ResponseEntity<String> unAssign(@RequestParam Integer id){
        Boolean status = laptopAllocationService.unassign(id);
        if(status)return ResponseEntity.ok("Laptop unassigned");
      else return new ResponseEntity<>("Something went wrong",HttpStatus.CONFLICT);

    }

}
