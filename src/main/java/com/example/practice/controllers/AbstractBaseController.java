package com.example.practice.controllers;


import com.example.practice.common.response.ResponseUtil;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractBaseController {

    @Autowired
    ResponseUtil responseUtil;
}
