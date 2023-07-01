package com.carRental.controler;

import com.carRental.controler.exceptions.CityNotFoundException;
import com.carRental.controler.exceptions.RentNotFoundException;
import com.carRental.domain.City;
import com.carRental.domain.dto.CityDto;
import com.carRental.mapper.CityMapper;
import com.carRental.repository.RentRepository;
import com.carRental.service.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/city")
public class CityController {

    private final CityService service;
    private final CityMapper mapper;
    private final RentRepository rentRepository;

    @GetMapping
    public ResponseEntity<List<CityDto>> getCities() {
        List<City> cities = service.getAllCities();
        return ResponseEntity.ok(mapper.listMapToCityDto(cities));
    }

    @GetMapping(value = "{cityId}")
    public ResponseEntity<CityDto> getCityById(@PathVariable long cityId) throws CityNotFoundException {
        City city = service.getCityById(cityId);
        return ResponseEntity.ok(mapper.mapToCityDto(city));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createCity(@RequestBody CityDto cityDto) throws RentNotFoundException {
        City city = mapper.mapToCity(cityDto);
        service.saveCity(city);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{cityId}")
    public ResponseEntity<Void> deleteCity(@PathVariable long cityId) {
        service.deleteCity(cityId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CityDto> updateCity(@RequestBody CityDto cityDto) throws CityNotFoundException {
        City city = service.findById(cityDto.getId());
        city.setName(cityDto.getName());
        city.setPriceMultiplier(cityDto.getPriceMultiplier());
        city.setRents(rentRepository.findAllByCity_Id(cityDto.getId()).orElse(null));
        City updatedCity = service.saveCity(city);
        return ResponseEntity.ok(mapper.mapToCityDto(updatedCity));
    }
}
