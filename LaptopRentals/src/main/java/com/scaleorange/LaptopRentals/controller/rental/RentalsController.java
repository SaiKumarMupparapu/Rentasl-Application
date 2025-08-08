package com.scaleorange.LaptopRentals.controller.rental;

import com.scaleorange.LaptopRentals.dto.rental.LaptopRentalRequestDTO;
import com.scaleorange.LaptopRentals.dto.rental.RentalItemRequestDTO;
import com.scaleorange.LaptopRentals.entity.LaptopRentals;
import com.scaleorange.LaptopRentals.entity.RentalsItems;
import com.scaleorange.LaptopRentals.service.rentals.RentalsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rental")
@PreAuthorize("hasAuthority('COMPANY')")
public class RentalsController {

    @Autowired
    private RentalsService rentalsService;

    @PostMapping("/place")
    public ResponseEntity<LaptopRentals> createOrder(@RequestBody LaptopRentalRequestDTO rentalRequest){
        LaptopRentals placedOrder = rentalsService.placeOrder(rentalRequest);
        return new ResponseEntity<>(placedOrder, HttpStatus.CREATED);
    }

    @PostMapping("/item/add")
    public ResponseEntity<RentalsItems> addItemToOrder(@RequestBody RentalItemRequestDTO item){
        RentalsItems result = rentalsService.addItemToOrder(item);
        return new ResponseEntity<>(result,HttpStatus.CREATED);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<LaptopRentals>> listOfOrders(){
        return new ResponseEntity<>(rentalsService.orders(),HttpStatus.OK);
    }


}
