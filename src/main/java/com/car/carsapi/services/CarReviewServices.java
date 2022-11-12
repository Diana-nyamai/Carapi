package com.car.carsapi.services;

import com.car.carsapi.dto.CarReviewDto;

import java.util.List;

public interface CarReviewServices {
    CarReviewDto createCarReviews(int carid, CarReviewDto carReviewDto);
    List<CarReviewDto> getAllCarReviewsById(int id);
}
