package com.example.practice.controllers;

import com.example.practice.common.response.RestAPIStatus;
import com.example.practice.common.response.RestApiMessage;
import com.example.practice.common.response.RestApiResponse;
import com.example.practice.common.validator.Validator;
import com.example.practice.controllers.models.request.InsertUserRequest;
import com.example.practice.entities.User;
import com.example.practice.repositories.UserRepositories;
import com.example.practice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController extends AbstractBaseController {

    @Autowired
    UserRepositories userRepositories;

    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<RestApiResponse<Object>> getAllUser() {
        return responseUtil.successResponse(userRepositories.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RestApiResponse<Object>> getUserById(@PathVariable("id") String userId) {
        return responseUtil.successResponse(userService.getUserById(userId));
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
