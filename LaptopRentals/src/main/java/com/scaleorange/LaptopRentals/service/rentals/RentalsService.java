package com.scaleorange.LaptopRentals.service.rentals;



import com.scaleorange.LaptopRentals.dto.products.ItemRequest;
import com.scaleorange.LaptopRentals.dto.rental.LaptopRentalRequestDTO;
import com.scaleorange.LaptopRentals.dto.rental.RentalItemRequestDTO;
import com.scaleorange.LaptopRentals.entity.LaptopRentals;
import com.scaleorange.LaptopRentals.entity.RentalsItems;

import java.util.List;

public interface RentalsService {

    public LaptopRentals placeOrder(LaptopRentalRequestDTO rentalsDto);
    public RentalsItems addItemToOrder(RentalItemRequestDTO item);
    public List<LaptopRentals> orders(Integer id);
}
