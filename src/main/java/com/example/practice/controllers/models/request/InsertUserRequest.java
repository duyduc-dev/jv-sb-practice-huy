package com.example.practice.controllers.models.request;


import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;

@Data
@JsonNaming(PropertyNamingStrategy.LowerCaseWithUnderscoresStrategy.class)
public class InsertUserRequest {
    private String username;
    private int age;
    private String firstName;
    private String lastName;
}
