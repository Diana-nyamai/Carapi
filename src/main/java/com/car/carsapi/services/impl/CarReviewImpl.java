package com.car.carsapi.services.impl;

import com.car.carsapi.dto.CarReviewDto;
import com.car.carsapi.exceptions.CarNotFoundException;
import com.car.carsapi.models.Car;
import com.car.carsapi.models.CarR;
import com.car.carsapi.repository.CarRepository;
import com.car.carsapi.repository.CarReviewRepository;
import com.car.carsapi.services.CarReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarReviewImpl implements CarReviewServices {
    private CarReviewRepository carReviewRepository;
    private CarRepository carRepository;
@Autowired
    public CarReviewImpl(CarReviewRepository carReviewRepository, CarRepository carRepository) {
        this.carReviewRepository = carReviewRepository;
        this.carRepository = carRepository;
    }

    @Override
    public CarReviewDto createCarReviews(int carid, CarReviewDto carReviewDto) {
        CarR carReviews = maptoEntity(carReviewDto);
        Car car = carRepository.findById(carid).orElseThrow(() -> new CarNotFoundException("could not find bbby id"));
        carReviews.setCar(car);
        CarR newcarreviews = carReviewRepository.save(carReviews);
        return maptoDto(newcarreviews);
    }

    @Override
    public List<CarReviewDto> getAllCarReviewsById(int id) {
        List<CarR> carReviews = carReviewRepository.findByCarId(id);
        return carReviews.stream().map(r -> maptoDto(r)).collect(Collectors.toList());
    }

//mapping to entity and dtos
    public CarR maptoEntity(CarReviewDto carReviewDto){
        CarR carReviews = new CarR();
        carReviews.setTitle(carReviewDto.getTitle());
        carReviews.setComment(carReviewDto.getComment());
        carReviews.setStars(carReviewDto.getStars());
        return carReviews;
    }
    public CarReviewDto maptoDto(CarR carReviews){
        CarReviewDto carReviewDto = new CarReviewDto();
        carReviewDto.setId(carReviews.getId());
        carReviewDto.setTitle(carReviews.getTitle());
        carReviewDto.setComment(carReviews.getComment());
        carReviewDto.setStars(carReviews.getStars());
        return carReviewDto;
    }
}
