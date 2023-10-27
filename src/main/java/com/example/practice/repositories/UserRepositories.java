package com.example.practice.repositories;

import com.example.practice.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRepositories extends JpaRepository<User, String> {

    List<User> findUsersByAge(int age);
}
