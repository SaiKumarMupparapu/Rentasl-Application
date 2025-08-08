package com.scaleorange.LaptopRentals.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
@Table(name = "Laptops")
public class Laptops {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer laptopId;
    private String brand;
    private String model;
    private String serialNumber;
    private String specs;
    private Double currentRatePerMonth;

    @UpdateTimestamp
    @Column(insertable = false)
    private Date updatedDate;

    @JsonBackReference(value = "provider-laptops")
    @ManyToOne()
    @JoinColumn(name = "organizationId")
    private Organizations provider;

    @JsonIgnore
    @OneToOne(mappedBy = "laptops")
    private RentalsItems items;

    @OneToOne(mappedBy = "laptop")
    private EmployeeLaptopAllocations laptopAllocations;

}
