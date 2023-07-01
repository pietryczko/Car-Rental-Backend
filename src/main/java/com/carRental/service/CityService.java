package com.carRental.service;

import com.carRental.controler.exceptions.CityNotFoundException;
import com.carRental.controler.exceptions.RentNotFoundException;
import com.carRental.domain.City;
import com.carRental.repository.CityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository repository;
    public City saveCity(final City city) {
        return repository.save(city);
    }

    public List<City> getAllCities() {
        return repository.findAll();
    }

    public City getCityById(long cityId) throws CityNotFoundException {
        return repository.findById(cityId).orElseThrow(CityNotFoundException::new);
    }

    public void deleteCity(long id) {
        repository.deleteById(id);
    }

    public City findById(Long id) throws CityNotFoundException {
        return repository.findById(id).orElseThrow(CityNotFoundException::new);
    }
}
