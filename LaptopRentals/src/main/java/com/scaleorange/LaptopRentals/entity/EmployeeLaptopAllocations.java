package com.scaleorange.LaptopRentals.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@Data
@Entity
public class EmployeeLaptopAllocations {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private Date allocationStart;

    private Date allocationEnd;

    @CreationTimestamp
    @Column(updatable = false)
    private Date createdAt;

    @UpdateTimestamp
    @Column(insertable = false)
    private Date updatedAt;

    @OneToOne()
    @JoinColumn(name = "laptop_id")
    private Laptops laptop;

    @JsonIgnoreProperties({"organization","laptopAllocations"})
    @OneToOne
    @JoinColumn(name = "emp_id")
    private Employee employee;

}
