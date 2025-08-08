package com.scaleorange.LaptopRentals.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String name;
    private String email;
    private String phno;

    private Boolean isAvilableToAssign;

    @CreationTimestamp
    @Column(updatable = false)
    private Date created_at;

    @UpdateTimestamp
    @Column(insertable = false)
    private Date updated_at;


    @ManyToOne()
    @JoinColumn(name = "organizationId")
    @JsonIgnoreProperties({"laptops", "rentals", "receivedRentals"})
    private Organizations organization;


    @JsonIgnore
    @OneToOne(mappedBy = "employee")
    private EmployeeLaptopAllocations laptopAllocations;
}
