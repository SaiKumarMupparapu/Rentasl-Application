package com.scaleorange.LaptopRentals.utils;

import com.razorpay.Order;
import com.scaleorange.LaptopRentals.dto.auth.RegisterRequest;
import com.scaleorange.LaptopRentals.dto.auth.User;
import com.scaleorange.LaptopRentals.dto.employee.EmployeeRequest;
import com.scaleorange.LaptopRentals.dto.payments.RazorpayOrderResponse;
import com.scaleorange.LaptopRentals.dto.products.ItemRequest;
import com.scaleorange.LaptopRentals.dto.products.ItemResponse;
import com.scaleorange.LaptopRentals.dto.rental.LaptopRentalRequestDTO;
import com.scaleorange.LaptopRentals.dto.rental.RentalItemRequestDTO;
import com.scaleorange.LaptopRentals.entity.Employee;
import com.scaleorange.LaptopRentals.entity.LaptopRentals;
import com.scaleorange.LaptopRentals.entity.Laptops;
import com.scaleorange.LaptopRentals.entity.Organizations;
import org.modelmapper.ModelMapper;

public class Mapper {

    private static final ModelMapper mapper=  new ModelMapper();

    public static Organizations convertToOrganization(RegisterRequest dto){
        return mapper.map(dto,Organizations.class);
    }

    public static Laptops convertToLaptops(ItemRequest laptopDto){
       return mapper.map(laptopDto,Laptops.class);
    }

    public static ItemResponse convertToItemsResponse(Laptops laptop){
        ItemResponse response = mapper.map(laptop, ItemResponse.class);
        response.setProviderId(laptop.getProvider().getOrganizationId());
        response.setProviderName(laptop.getProvider().getName());
        return response;
    }

    public static User convertToUser(Organizations organizations){
        return  mapper.map(organizations, User.class);
    }


    public static LaptopRentals convertToLaptopRentals(LaptopRentalRequestDTO dto){
        LaptopRentals rentals = mapper.map(dto, LaptopRentals.class);
        return rentals;
    }

    public static Laptops convertToLaptop(RentalItemRequestDTO dto){
        return  mapper.map(dto,Laptops.class);
    }

    public static RazorpayOrderResponse convertToPaymentRespo(Order order){
        return mapper.map(order,RazorpayOrderResponse.class);
    }

    public static Employee convertToEmployee(EmployeeRequest employeeRequest){
      return  mapper.map(employeeRequest,Employee.class);
    }

}
