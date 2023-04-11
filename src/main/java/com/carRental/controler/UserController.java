package com.carRental.controler;

import com.carRental.controler.exceptions.UserNotFoundException;
import com.carRental.domain.User;
import com.carRental.domain.dto.UserDto;
import com.carRental.mapper.UserMapper;
import com.carRental.service.RentService;
import com.carRental.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final RentService rentService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(userMapper.listMapToUserDto(users));
    }

    @GetMapping(value = "{userId}")
    public ResponseEntity<UserDto> getRentById(@PathVariable long userId) throws UserNotFoundException {
        User user = userService.findById(userId);
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@RequestBody UserDto userDto) {
        User user = userMapper.mapToUser(userDto);
        userService.saveUser(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(value = "{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.ok().build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) throws UserNotFoundException {
        User user = userService.findById(userDto.getId());
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setRentsId(rentService.getRentsByUser(userDto.getId()));
        user.setPersonalIdNumber(userDto.getPersonalIdNumber());
        return ResponseEntity.ok(userMapper.mapToUserDto(user));
    }
}
