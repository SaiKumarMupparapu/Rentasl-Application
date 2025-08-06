package com.scaleorange.LaptopRentals.service.rentals;


import com.scaleorange.LaptopRentals.dto.rental.LaptopRentalRequestDTO;
import com.scaleorange.LaptopRentals.dto.rental.RentalItemRequestDTO;
import com.scaleorange.LaptopRentals.entity.LaptopRentals;
import com.scaleorange.LaptopRentals.entity.Laptops;
import com.scaleorange.LaptopRentals.entity.Organizations;
import com.scaleorange.LaptopRentals.entity.RentalsItems;
import com.scaleorange.LaptopRentals.repo.OrganizationRepo;
import com.scaleorange.LaptopRentals.repo.ProductsRepo;
import com.scaleorange.LaptopRentals.repo.RentalsRepo;
import com.scaleorange.LaptopRentals.repo.RentalItemsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RentalServiceImpl implements RentalsService{

    @Autowired
    private RentalsRepo rentalsRepo;
    @Autowired
    private RentalItemsRepo rentalsItemsRepo;
    @Autowired
    private ProductsRepo productsRepo;
    @Autowired
    private OrganizationRepo organizationRepo;


    @Override
    public LaptopRentals placeOrder(LaptopRentalRequestDTO rentalsDto) {
        Organizations provider = organizationRepo.findById(rentalsDto.getProviderId())
                .orElseThrow(() -> new RuntimeException("Laptop Provider Not Found"));

        Organizations consumer = organizationRepo.findById(rentalsDto.getRentedToId())
                .orElseThrow(() -> new RuntimeException("Laptop Consumer Not Found"));

        LaptopRentals laptopRentals = new LaptopRentals();
        laptopRentals.setProvider(provider);
        laptopRentals.setRentedTo(consumer);
        laptopRentals.setAdvanceAmount(rentalsDto.getAdvanceAmount());

        List<RentalItemRequestDTO> rentalItems = rentalsDto.getRentalItems();
        List<RentalsItems> rentalsItemsList = rentalItems.stream()
                .map(
                        a -> {
                            Laptops laptop = productsRepo.findById(a.getLaptopId())
                                    .orElseThrow(() -> new RuntimeException("Product not found, id : "+a.getLaptopId()));
                            RentalsItems rentalsItems = new RentalsItems();
                            rentalsItems.setRate(a.getRate());
                            rentalsItems.setRentals(laptopRentals);
                            rentalsItems.setLaptops(laptop);
                            return rentalsItems;
                        }).toList();
        laptopRentals.setItems(rentalsItemsList);

        return rentalsRepo.save(laptopRentals);
    }

    public RentalsItems addItemToOrder(RentalItemRequestDTO item){
        LaptopRentals laptopRentals = rentalsRepo.findById(item.getRentalId()).orElseThrow(() -> new RuntimeException("Order Not Found"));
        Laptops laptop = productsRepo.findById(item.getLaptopId()).orElseThrow(() -> new RuntimeException("Product Not Found"));
        RentalsItems rentalItems = new RentalsItems();
        rentalItems.setLaptops(laptop);
        rentalItems.setRate(item.getRate());
        rentalItems.setRentals(laptopRentals);
      return rentalsItemsRepo.save(rentalItems);
    }

    @Override
    public List<LaptopRentals> orders() {
        return rentalsRepo.findAll();
    }

}
