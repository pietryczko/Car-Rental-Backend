package com.carRental.domain.dto;

import com.carRental.domain.CarStatus;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private long id;
    private String licencePlateNumber;
    private String brand;
    private String model;
    private CarStatus carStatus;
    private List<Long> rentsId;
}

