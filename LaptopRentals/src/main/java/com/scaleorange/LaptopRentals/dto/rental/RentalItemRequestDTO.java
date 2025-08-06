package com.scaleorange.LaptopRentals.dto.rental;

import lombok.Data;

@Data
public class RentalItemRequestDTO {

    private Integer laptopId;

    private Double rate;

    private Integer rentalId;
}




//
//import lombok.Data;
//
//import java.util.Date;
//
//@Data
//public class LaptopRentalsDto {
//
//    private Integer rentalId;
//    private Integer providerId;
//    private Integer ConsumerId;
////    private Integer laptopId;
//    private Date rentalStart;
//    private Date rentalEnd;
//    private Double advanceAmount;
//
//}
