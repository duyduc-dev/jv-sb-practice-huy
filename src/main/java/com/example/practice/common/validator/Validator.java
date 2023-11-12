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

    public static void notEmpty(String data, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(data.isEmpty()) {
            throw new ApplicationException(restAPIStatus, restApiMessage);
        }
    }

    public static void mustNull(Object data, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(data != null) {
            throw new ApplicationException(restAPIStatus, restApiMessage);
        }
    }

    public static void mustEqualString(String obj1, String obj2, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(!obj1.equals(obj2)) {
            throw new ApplicationException(restAPIStatus, restApiMessage);
        }
    }

    public static void mustTrue(boolean data, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(!data) {
            throw new ApplicationException(restAPIStatus, restApiMessage);
        }
    }

    public static void notNullAndNotEmptyString(String data, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(data == null || data.isEmpty())
            throw new ApplicationException(restAPIStatus, restApiMessage);
    }

    public static void mustGreater(int num, int intCompare, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(num <= intCompare) throw new ApplicationException(restAPIStatus, restApiMessage);
    }
    public static void mustLessThan(int num, int intCompare, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(num >= intCompare) throw new ApplicationException(restAPIStatus, restApiMessage);
    }

    public static void mustEqual(int num1, int num2, RestAPIStatus restAPIStatus, RestApiMessage restApiMessage) {
        if(num1 != num2) throw new ApplicationException(restAPIStatus, restApiMessage);
    }



    public static boolean isNull(Object data) {
        return data == null;
    }
}
