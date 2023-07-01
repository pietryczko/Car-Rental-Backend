package com.carRental.repository;

import com.carRental.domain.Rent;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentRepository extends CrudRepository<Rent, Long> {
    @Override
    Optional<Rent> findById(Long id);

    @Override
    List<Rent> findAll();

    Optional<List<Rent>> findAllByRentedCar_Id(Long carId);

    Optional<List<Rent>> findAllByRentUser_Id(Long userId);

    Optional<List<Rent>> findAllByCity_Id(Long cityId);

}
