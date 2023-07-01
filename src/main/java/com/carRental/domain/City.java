package com.carRental.domain;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "CITIES")
public class City {
    @Id
    @GeneratedValue
    @NotNull
    @Column(name = "CITY_ID", unique = true)
    private Long id;
    @NotNull
    @Column(name = "CITY_NAME")
    private String name;
    @NotNull
    @Column(name = "PRICE_MULTIPLIER")
    private BigDecimal priceMultiplier;

    @OneToMany(
            targetEntity = Rent.class,
            mappedBy = "city",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    private List<Rent> rents;
}
