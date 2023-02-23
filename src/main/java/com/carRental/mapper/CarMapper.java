package com.carRental.mapper;

import com.carRental.controler.exceptions.RentNotFoundException;
import com.carRental.domain.Car;
import com.carRental.domain.dto.CarDto;
import com.carRental.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    private RentRepository rentRepository;

    public Car mapToCar(CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .licencePlateNumber(carDto.getLicencePlateNumber())
                .build();
    }

    public CarDto mapToCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .licencePlateNumber(car.getLicencePlateNumber())
                .rent(car.getRent())
                .build();
    }

    public List<CarDto> listMapToCarDto(List<Car> cars) {
        return cars.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }
}
