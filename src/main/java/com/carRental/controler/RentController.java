package com.carRental.controler;

import com.carRental.controler.exceptions.CarNotFoundException;
import com.carRental.controler.exceptions.RentNotFoundException;
import com.carRental.controler.exceptions.UserNotFoundException;
import com.carRental.domain.Rent;
import com.carRental.domain.dto.RentDto;
import com.carRental.mapper.RentMapper;
import com.carRental.service.CarService;
import com.carRental.service.RentService;
import com.carRental.service.UserService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/rent")
public class RentController {
    private final RentService rentService;
    private final CarService carService;
    private final UserService userService;
    private final RentMapper rentMapper;

    @GetMapping
    public ResponseEntity<List<RentDto>> getRents() {
        List<Rent> rents = rentService.getAllRents();
        return ResponseEntity.ok(rentMapper.listMapToRentDto(rents));
    }

    @GetMapping(value = "{carId}")
    public ResponseEntity<RentDto> getRent(@PathVariable long rentId) throws RentNotFoundException {
     Rent rent = rentService.findById(rentId);
     return ResponseEntity.ok(rentMapper.mapToRentDto(rent));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createRent(@RequestBody RentDto rentDto) throws UserNotFoundException, CarNotFoundException {
        Rent rent = rentMapper.mapToRent(rentDto);
        rentService.saveRent(rent);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{RentId}")
    public ResponseEntity<Void> deleteRent(@PathVariable long rentId) {
        rentService.deleteRent(rentId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RentDto> upadteRent(@RequestBody RentDto rentDto) throws UserNotFoundException, CarNotFoundException, RentNotFoundException {
        Rent rent = rentService.findById(rentDto.getId());
        rent.setCost(rentDto.getCost());
        rent.setRentStatus(rentDto.getRentStatus());
        rent.setStartRentDate(rentDto.getStartRentDate());
        rent.setEndRentDate(rentDto.getEndRentDate());
        rent.setRentUser(userService.findById(rentDto.getUserId()));
        rent.setRentedCar(carService.findById(rentDto.getCarId()));
        Rent updatedRent = rentService.saveRent(rent);
        return ResponseEntity.ok(rentMapper.mapToRentDto(updatedRent));
    }
}
