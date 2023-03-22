package com.carRental.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDto {
    private long id;
    private String licencePlateNumber;
    private String brand;
    private String model;
    private List<Long> rentsId = new ArrayList<>();
}

