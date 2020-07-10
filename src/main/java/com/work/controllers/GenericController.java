package com.work.controllers;

import com.work.services.GenericService;
import org.springframework.beans.factory.annotation.Autowired;

public class GenericController <T extends GenericService> {

    @Autowired
    private T service;

    public GenericController() {
    }

    public T getService() {
        return service;
    }


}
