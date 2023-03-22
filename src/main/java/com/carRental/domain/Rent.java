package com.carRental.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "RENTS")
public class Rent {

    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "RENT_ID", unique = true)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "RENT_STATUS")
    private RentStatus rentStatus;

    @Column(name = "RENT_COST")
    private BigDecimal cost;

    @NotNull
    @Column(name = "START_RENT_DATE")
    private LocalDate startRentDate;

    @Column(name = "END_RENT_DATE")
    private LocalDate endRentDate;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "USER_ID")
    private User rentUser;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "CAR_ID")
    private Car rentedCar;
}
