package com.carRental.repository;

import com.carRental.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface CityRepository extends CrudRepository<City, Long> {
    @Override
    List<City> findAll();
}
