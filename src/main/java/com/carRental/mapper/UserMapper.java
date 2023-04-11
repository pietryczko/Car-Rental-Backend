package com.carRental.mapper;

import com.carRental.domain.Rent;
import com.carRental.domain.User;
import com.carRental.domain.dto.UserDto;
import com.carRental.repository.RentRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class UserMapper {

    private RentRepository rentRepository;

    public User mapToUser(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .personalIdNumber(userDto.getPersonalIdNumber())
                .rentsId(findAllById(userDto.getRentsId()))
                .build();
    }

    public UserDto mapToUserDto(User user) {
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .surname(user.getSurname())
                .personalIdNumber(user.getPersonalIdNumber())
                .rentsId(user.getRentsId() == null ? null : user.getRentsId().stream()
                        .map(Rent::getId)
                        .collect(Collectors.toList()))
                .build();
    }

    public List<UserDto> listMapToUserDto(List<User> users) {
        return users.stream()
                .map(this::mapToUserDto)
                .collect(Collectors.toList());
    }

    public List<Rent> findAllById(List<Long> rentsId) {
        List<Rent> results = new ArrayList<>();
        if (Objects.nonNull(rentsId)) {
            for (Long id : rentsId) {
                rentRepository.findById(id).ifPresent(results::add);
            }
        }
        return results;
    }
}
