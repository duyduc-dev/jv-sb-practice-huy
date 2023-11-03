package com.example.practice.common.exception;

import com.example.practice.common.response.ResponseUtil;
import com.example.practice.common.response.RestApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ValidationExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    ResponseUtil responseUtil;

    @ExceptionHandler(value = ApplicationException.class)
    public ResponseEntity<RestApiResponse<Object>> applicationExceptionHandler(ApplicationException ae) {
        return responseUtil.buildResponse(ae.getApiStatus(), ae.getMessage(), HttpStatus.OK);
    }
}
