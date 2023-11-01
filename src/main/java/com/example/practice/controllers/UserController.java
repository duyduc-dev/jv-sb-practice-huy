package com.example.practice.controllers;

import com.example.practice.controllers.models.request.InsertUserRequest;
import com.example.practice.entities.User;
import com.example.practice.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepositories userRepositories;

    @GetMapping
    public ResponseEntity getAllUser() {
        return ResponseEntity.ok(userRepositories.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable("id") String userId) {

        User user = userRepositories.findById(userId).orElse(null);
        if(user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity insertUser(@RequestBody InsertUserRequest userRequest) {
        User user = User.builder()
                .age(userRequest.getAge())
                .firstName(userRequest.getFirstName())
                .lastName(userRequest.getLastName())
                .username(userRequest.getUsername())
                .build();

        userRepositories.save(user);
        return ResponseEntity.ok("ok");
    }

}
