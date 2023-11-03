package com.example.practice.common.exception;

import com.example.practice.common.response.RestAPIStatus;
import com.example.practice.common.response.RestApiMessage;

public class ApplicationException extends RuntimeException {

    private RestAPIStatus restAPIStatus;
    private Object data;


    public ApplicationException(RestAPIStatus restAPIStatus) {
        super(restAPIStatus.getValue());
        this.restAPIStatus = restAPIStatus;
    }

    public ApplicationException(RestAPIStatus apiStatus, Object data) {
        this(apiStatus);
        this.data = data;
    }

    public ApplicationException(RestAPIStatus apiStatus, Object data, String message) {
        super(message);
        this.restAPIStatus = apiStatus;
        this.data = data;
    }

    public ApplicationException(Throwable cause) {
        super(cause);
    }

    public ApplicationException(RestAPIStatus apiStatus, String message) {
        super(message);
        this.restAPIStatus = apiStatus;
    }

    public ApplicationException(RestAPIStatus apiStatus, RestApiMessage message) {
        super(message.toString());
        this.restAPIStatus = apiStatus;
    }

    public RestAPIStatus getApiStatus() {
        return restAPIStatus;
    }

    public Object getData() {
        return data;
    }

}
