package com.car.carsapi.exceptions;

public class CarNotFoundException extends RuntimeException{
    private static final long id=1;

    public CarNotFoundException(String message) {
        super(message);
    }
}
