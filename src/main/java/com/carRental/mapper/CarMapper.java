package com.carRental.mapper;

import com.carRental.domain.Car;
import com.carRental.domain.Rent;
import com.carRental.domain.dto.CarDto;
import com.carRental.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CarMapper {

    private RentRepository rentRepository;

    public Car mapToCar(CarDto carDto) {
        return Car.builder()
                .id(carDto.getId())
                .brand(carDto.getBrand())
                .model(carDto.getModel())
                .status(carDto.getCarStatus())
                .licencePlateNumber(carDto.getLicencePlateNumber())
                .rents(findAllById(carDto.getRentsId()))
                .build();
    }

    public CarDto mapToCarDto(Car car) {
        return CarDto.builder()
                .id(car.getId())
                .brand(car.getBrand())
                .model(car.getModel())
                .carStatus(car.getStatus())
                .licencePlateNumber(car.getLicencePlateNumber())
                .rentsId((car.getRents() == null ? null : car.getRents().stream()
                        .map(Rent::getId)
                        .collect(Collectors.toList())))
                .build();
    }

    public List<CarDto> listMapToCarDto(List<Car> cars) {
        return cars.stream()
                .map(this::mapToCarDto)
                .collect(Collectors.toList());
    }

    public List<Rent> findAllById(List<Long> rentsId) {
        List<Rent> results = new ArrayList<>();
        if (Objects.nonNull(rentsId)) {
            for (Long id : rentsId) {
                rentRepository.findById(id).ifPresent(results::add);
            }
        }
        return results;
    }
}
