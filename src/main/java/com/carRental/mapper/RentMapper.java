package com.carRental.mapper;

import com.carRental.controler.exceptions.CarNotFoundException;
import com.carRental.controler.exceptions.UserNotFoundException;
import com.carRental.domain.Rent;
import com.carRental.domain.dto.RentDto;
import com.carRental.repository.CarRepository;
import com.carRental.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentMapper {

    private CarRepository carRepository;
    private UserRepository userRepository;

    public Rent mapToRent(RentDto rentDto) throws CarNotFoundException, UserNotFoundException {
        return Rent.builder()
                .id(rentDto.getId())
                .rentStatus(rentDto.getRentStatus())
                .startRentDate(rentDto.getStartRentDate())
                .endRentDate(rentDto.getEndRentDate())
                .cost(rentDto.getCost())
                .rentUser(userRepository.findById(rentDto.getUserId()).orElseThrow(UserNotFoundException::new))
                .rentedCar(carRepository.findById(rentDto.getCarId()).orElseThrow(CarNotFoundException::new))
                .build();
    }

    public RentDto mapToRentDto(Rent rent) {
        return RentDto.builder()
                .id(rent.getId())
                .rentStatus(rent.getRentStatus())
                .startRentDate(rent.getStartRentDate())
                .endRentDate(rent.getEndRentDate())
                .cost(rent.getCost())
                .userId(rent.getRentUser().getId())
                .carId(rent.getRentedCar().getId())
                .build();
    }

    public Rent createRent(RentDto rentDto, long carId, long userId) throws UserNotFoundException, CarNotFoundException {
        return Rent.builder()
                .id(rentDto.getId())
                .rentStatus(rentDto.getRentStatus())
                .startRentDate(rentDto.getStartRentDate())
                .endRentDate(rentDto.getEndRentDate())
                .cost(rentDto.getCost())
                .rentUser(userRepository.findById(userId).orElseThrow(UserNotFoundException::new))
                .rentedCar(carRepository.findById(carId).orElseThrow(CarNotFoundException::new))
                .build();
    }

    public List<RentDto> listMapToRentDto(List<Rent> rents) {
        return rents.stream()
                .map(this::mapToRentDto)
                .collect(Collectors.toList());
    }

}
