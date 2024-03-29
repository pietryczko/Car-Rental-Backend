package com.carRental.domain;

import com.carRental.domain.status.CarStatus;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
@Table(name = "CARS")
public class Car {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RENT_ID", unique = true)
    private long id;

    @NotNull
    @Column(name = "LICENSE_PLATE_NUMBER")
    private String licencePlateNumber;

    @NotNull
    @Column(name = "BRAND")
    private String brand;

    @NotNull
    @Column(name = "MODEL")
    private String model;

    @NotNull
    @Column(name = "PRICE_PER_DAY")
    private BigDecimal price;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "CAR_STATUS")
    private CarStatus status;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "rentedCar",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY

    )
    private List<Rent> rents;
}
