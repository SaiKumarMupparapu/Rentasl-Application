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

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_type")
    private PaymentType paymentType;


    private String status;

    private String razorpayOrderId;

    private String razorpayPaymentId;

    private String razorpaySignature;

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
