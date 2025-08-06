package com.scaleorange.LaptopRentals.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
@Entity
@Table(name = "payments")
@Data
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer paymentId;

    private Double amount;

    private Date paymentDate;

    private String status;

    private String razorpayOrderId;

    private String getRazorpayPaymentId;

    private String getRazorpaySignature;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdDate;

    @UpdateTimestamp
    @Column(insertable = false)
    private Date updatedDate;

    @OneToOne()
    @JoinColumn(name = "rentalId")
    private LaptopRentals rentals;

}
