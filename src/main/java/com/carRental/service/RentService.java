package com.carRental.service;

import com.carRental.controler.exceptions.RentNotFoundException;
import com.carRental.domain.Rent;
import com.carRental.domain.dto.RentDto;
import com.carRental.repository.RentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RentService {
    private final RentRepository rentRepository;

    public Rent saveRent(final Rent rent) {
       return rentRepository.save(rent);
    }

    public List<Rent> getAllRents() {
        return rentRepository.findAll();
    }
    public List<Rent> getRentsByUser(long userId){
        return rentRepository.findAllByRentUser_Id(userId).orElse(null);
    }

    public Rent findById(Long id) throws RentNotFoundException {
        return rentRepository.findById(id).orElseThrow(RentNotFoundException::new);
    }

    public void deleteRent(Long id) {
        rentRepository.deleteById(id);
    }
}