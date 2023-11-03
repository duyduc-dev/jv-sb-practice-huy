package com.example.practice.common.validator;

import com.example.practice.common.exception.ApplicationException;
import com.example.practice.common.response.RestAPIStatus;
import com.example.practice.common.response.RestApiMessage;

public class Validator {


    public static void notNull(Object data, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(data == null) {
            throw new ApplicationException(restAPIStatus, restApiMessage);
        }
    }
}
