package com.example.practice.common.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ResponseUtil {

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    public ResponseUtil(ObjectMapper mapper) {
        this.objectMapper = mapper;
    }

    public RestApiResponse<Object> createResponse(RestAPIStatus restAPIStatus, Object data) {
        return new RestApiResponse<>(restAPIStatus, data);
    }

    public RestApiResponse<Object> createResponse(RestAPIStatus restAPIStatus, Object data, String desc) {
        return new RestApiResponse<>(restAPIStatus, data, desc);
    }

     public ResponseEntity<RestApiResponse<Object>> buildResponse(RestAPIStatus restAPIStatus, Object data, HttpStatus httpStatus) {
        return new ResponseEntity<>(createResponse(restAPIStatus, data), httpStatus);
     }

     public ResponseEntity<RestApiResponse<Object>> buildResponse(RestAPIStatus restAPIStatus, Object data,String desc, HttpStatus httpStatus) {
        return new ResponseEntity<>(createResponse(restAPIStatus, data, desc), httpStatus);
     }

     public ResponseEntity<RestApiResponse<Object>> successResponse(Object data) {
        return this.buildResponse(RestAPIStatus.OK, data, HttpStatus.OK);
     }

    public ResponseEntity<RestApiResponse<Object>> successResponse(Object data, String description) {
        return buildResponse(RestAPIStatus.OK, data, description, HttpStatus.OK);
    }

}
