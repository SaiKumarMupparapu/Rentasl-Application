package com.scaleorange.LaptopRentals.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "laptop_rentals")
public class LaptopRentals {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer rentalId;

    @CreationTimestamp
    @Column(updatable = false)
    private Date rentalStart;


    @Column(insertable = false)
    private Date rentalEnd;

    @Column(nullable = false)
    private Double advanceAmount;


    @JsonBackReference(value = "provider-rentals")
    @ManyToOne()
    @JoinColumn(name = "provider")
    private Organizations provider;

    @JsonBackReference(value = "received-rentals")
    @ManyToOne
    @JoinColumn(name = "consumer", nullable = false)
    private Organizations rentedTo;

    @JsonManagedReference()
    @OneToMany(mappedBy = "rentals",cascade = CascadeType.ALL)
    private List<RentalsItems> items;

    @JsonIgnore
    @OneToOne(mappedBy = "rentals")
    private Payments payments;

}
