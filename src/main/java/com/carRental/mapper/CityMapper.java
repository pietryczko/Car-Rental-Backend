package com.carRental.mapper;

import com.carRental.controler.exceptions.RentNotFoundException;
import com.carRental.domain.City;
import com.carRental.domain.Rent;
import com.carRental.domain.dto.CityDto;
import com.carRental.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class CityMapper {

    private RentRepository rentRepository;

    public City mapToCity(CityDto cityDto) throws RentNotFoundException {
        return City.builder()
                .id(cityDto.getId())
                .name(cityDto.getName())
                .priceMultiplier(cityDto.getPriceMultiplier())
                .rents(rentRepository.findAllByCity_Id(cityDto.getId()).orElseThrow(RentNotFoundException::new))
                .build();
    }

    public CityDto mapToCityDto(City city) {
        return CityDto.builder()
                .id(city.getId())
                .name(city.getName())
                .priceMultiplier(city.getPriceMultiplier())
                .rentsId((city.getRents() == null ? null : city.getRents().stream()
                        .map(Rent::getId)
                        .collect(Collectors.toList())))
                .build();
    }

    public List<CityDto> listMapToCityDto(List<City> cities){
        return cities.stream()
                .map(this::mapToCityDto)
                .collect(Collectors.toList());
    }
}
