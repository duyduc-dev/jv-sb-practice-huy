package com.example.practice.common.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Builder
@Getter
@Setter
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestApiResponse<T extends Object> implements Serializable {
    private int status;
    private String message;
    private T data;
    private String description;

    public RestApiResponse(RestAPIStatus restAPIStatus, T data) {
        this.status = restAPIStatus.getCode();
        this.message = restAPIStatus.getValue();
        this.data = data;
        this.description = "";
    }

    public RestApiResponse(RestAPIStatus restAPIStatus, T data, String description) {
        this.status = restAPIStatus.getCode();
        this.message = restAPIStatus.getValue();
        this.data = data;
        this.description = description;
    }
}
