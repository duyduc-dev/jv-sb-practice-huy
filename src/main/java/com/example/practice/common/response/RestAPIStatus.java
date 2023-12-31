package com.example.practice.common.response;

public enum RestAPIStatus {
    OK(200, "OK"),
    NOT_FOUND(404, "Not Found"),
    BAD_REQUEST(400, "Bad request"),
    EXISTED(405, "Already existed"),
    CONFLICT(409, "Conflict"),
    BAD_PARAMS(406, "There is some invalid data"),
    ;


    private final int code;
    private final String value;

    RestAPIStatus(int code, String value) {
        this.code = code;
        this.value = value;
    }


    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }

    public static RestAPIStatus getEnum(int code) {
        for (RestAPIStatus v : values())
            if (v.getCode() == code) return v;
        throw new IllegalArgumentException();
    }
}
