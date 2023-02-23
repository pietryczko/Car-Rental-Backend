package com.carRental.service;

import com.carRental.controler.exceptions.CarNotFoundException;
import com.carRental.domain.Car;
import com.carRental.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {
    private final CarRepository carRepository;

    public Car saveCar(final Car car) {
        return carRepository.save(car);
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public Car findById(Long id) throws CarNotFoundException {
        return carRepository.findById(id).orElseThrow(CarNotFoundException::new);
    }

    public void deleteCar(long id) {
        carRepository.deleteById(id);
    }
}
