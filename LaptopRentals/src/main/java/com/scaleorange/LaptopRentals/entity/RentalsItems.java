package com.scaleorange.LaptopRentals.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Data
@Table(name = "rental_items")
public class RentalsItems {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rentalItemId;

    private Double rate;

    @CreationTimestamp
    @Column(updatable = false)
    private Date startDate;


    private Date endDate;

    @JsonBackReference
    @ManyToOne()
    @JoinColumn(name = "rentalId")
    private LaptopRentals rentals;

    @OneToOne()
    @JoinColumn(name = "laptopId")
    private Laptops laptops;

}
