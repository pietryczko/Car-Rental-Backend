package com.carRental.repository;

import com.carRental.domain.Car;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CarRepository extends CrudRepository<Car, Long> {
    @Override
    Optional<Car> findById(Long id);

    @Override
    List<Car> findAll();
}
