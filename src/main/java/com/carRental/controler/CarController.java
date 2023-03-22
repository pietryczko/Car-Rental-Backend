package com.carRental.controler;

import com.carRental.controler.exceptions.CarNotFoundException;
import com.carRental.domain.Car;
import com.carRental.domain.dto.CarDto;
import com.carRental.mapper.CarMapper;
import com.carRental.repository.RentRepository;
import com.carRental.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/car")
public class CarController {

    private final CarService carService;
    private final CarMapper carMapper;
    private final RentRepository rentRepository;

    @GetMapping
    public ResponseEntity<List<CarDto>> getCars() {
        List<Car> cars = carService.getAllCars();
        return ResponseEntity.ok(carMapper.listMapToCarDto(cars));
    }

    @GetMapping(value = "{carId}")
    public ResponseEntity<CarDto> getCarById(@PathVariable long carId) throws CarNotFoundException {
        Car car = carService.findById(carId);
        return ResponseEntity.ok(carMapper.mapToCarDto(car));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCar(@RequestBody CarDto carDto) {
        Car car = carMapper.mapToCar(carDto);
        carService.saveCar(car);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{carId}")
    public ResponseEntity<Void> deleteCar(@PathVariable long carId) {
        carService.deleteCar(carId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CarDto> updateCar(@RequestBody CarDto carDto) throws CarNotFoundException {
        Car car = carService.findById(carDto.getId());
        car.setBrand(carDto.getBrand());
        car.setModel(carDto.getModel());
        car.setLicencePlateNumber(carDto.getLicencePlateNumber());
        car.setRents(rentRepository.findAllByRentedCar_Id(carDto.getId()).orElse(null));
        Car updatedCar = carService.saveCar(car);
        return ResponseEntity.ok(carMapper.mapToCarDto(updatedCar));
    }
}
