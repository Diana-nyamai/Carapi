package com.car.carsapi.services;

import com.car.carsapi.dto.CarDto;
import com.car.carsapi.dto.GetAllCarsResponse;
import com.car.carsapi.models.Car;

import java.util.List;

public interface CarServices {
    CarDto createcar(CarDto carDto);
    GetAllCarsResponse getallcars(int pageno, int pagesize);
    CarDto getonecar(int id);
    CarDto updatecar(CarDto carDto, int id);
    void deletecar(int id);
}
