package com.car.carsapi.services.impl;

import com.car.carsapi.dto.CarDto;
import com.car.carsapi.dto.GetAllCarsResponse;
import com.car.carsapi.exceptions.CarNotFoundException;
import com.car.carsapi.models.Car;
import com.car.carsapi.repository.CarRepository;
import com.car.carsapi.services.CarServices;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarImpl implements CarServices {
    private CarRepository carRepository;

    public CarImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public CarDto createcar(CarDto carDto) {
        Car car = maptoEntity(carDto);
        Car newcar = carRepository.save(car);
        return maptoDto(newcar);
    }

    @Override
    public GetAllCarsResponse getallcars(int pageno, int pagesize) {
        Pageable pagable = PageRequest.of(pageno, pagesize);
        Page<Car> car = carRepository.findAll(pagable);
        List<Car> allcars = car.getContent();
        List<CarDto> content = allcars.stream().map(car1 -> maptoDto(car1)).collect(Collectors.toList());

        GetAllCarsResponse getAllCarsResponse = new GetAllCarsResponse();
        getAllCarsResponse.setContent(content);
        getAllCarsResponse.setPageno(car.getNumber());
        getAllCarsResponse.setPagesize(car.getSize());
        getAllCarsResponse.setTotalelements(car.getTotalElements());
        getAllCarsResponse.setTotalpages(car.getTotalPages());
        getAllCarsResponse.setLast(car.isLast());
        return getAllCarsResponse;
    }

    @Override
    public CarDto getonecar(int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("could not find car by id"));
        return maptoDto(car);
    }

    @Override
    public CarDto updatecar(CarDto carDto, int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("could not update car"));
        car.setName(carDto.getName());
        car.setModel(carDto.getModel());

        Car updatedcar = carRepository.save(car);
        return maptoDto(updatedcar);
    }

    @Override
    public void deletecar(int id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException("car was not deleted"));
        carRepository.delete(car);
    }

    //    mapping methods
    public Car maptoEntity(CarDto carDto){
        Car car = new Car();
        car.setName(carDto.getName());
        car.setModel(carDto.getModel());
        return car;
    }
    public CarDto maptoDto(Car car){
        CarDto carDto = new CarDto();
        carDto.setCarId(car.getCarId());
        carDto.setName(car.getName());
        carDto.setModel(car.getModel());
        return carDto;
    }
}
