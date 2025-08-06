package com.scaleorange.LaptopRentals.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Time;
import java.util.Date;
import java.util.List;


@Entity
@Table(name = "Organizations")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Organizations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer organizationId;

    private String name;
    private String email;
    private String password;
    private String phno;
    private String address;

    @Enumerated(EnumType.STRING)
    private Roles role;

    @CreationTimestamp()
    @Column(updatable = false)
    private Date createdDate;

    @JsonManagedReference(value = "provider-laptops")
    @OneToMany(mappedBy = "provider")
    private List<Laptops> laptops;

    @JsonManagedReference(value = "provider-rentals" )
    @OneToMany(mappedBy = "provider")
    private List<LaptopRentals> rentals;

    @JsonManagedReference(value = "received-rentals")
    @OneToMany(mappedBy = "rentedTo",fetch = FetchType.LAZY)
    private List<LaptopRentals> receivedRentals;
}
