package com.example.practice.controllers;

import com.example.practice.controllers.models.request.InsertUserRequest;
import com.example.practice.entities.Address;
import com.example.practice.entities.User;
import com.example.practice.repositories.AddressRepository;
import com.example.practice.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/auth")
public class AuthenticationController {

    @Autowired
    UserRepositories userRepositories;
    @Autowired
    AddressRepository addressRepository;

    @GetMapping
    public ResponseEntity<Object> getAllUser() {
        return ResponseEntity.ok(userRepositories.findAll());
    }

    @PostMapping
    public ResponseEntity<Object> insertUser(@RequestBody InsertUserRequest newUser) {
        User user = User.builder()
                .age(newUser.getAge())
                .firstName(newUser.getFirstName())
                .lastName(newUser.getLastName())
                .username(newUser.getUsername())
                .build();
        userRepositories.save(user);
        return ResponseEntity.ok("ok");
    }

    @PostMapping(path = "address")
    public ResponseEntity<Object> insertAddress(@RequestBody Address address) {
        addressRepository.save(address);
        return ResponseEntity.ok("ok");
    }
    @GetMapping(path = "address")
    public ResponseEntity<Object> getAddress() {

        return ResponseEntity.ok(addressRepository.findAll());
    }

    @GetMapping(path = "address/{user_id}")
    public ResponseEntity<Object> getAddressByUserId(@PathVariable("user_id") String userId) {

        return ResponseEntity.ok(addressRepository.findAddressByUserId(userId));
    }

}
