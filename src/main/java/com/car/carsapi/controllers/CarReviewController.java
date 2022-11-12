package com.car.carsapi.controllers;

import com.car.carsapi.dto.CarDto;
import com.car.carsapi.dto.CarReviewDto;
import com.car.carsapi.services.CarReviewServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CarReviewController {
    private CarReviewServices carReviewServices;
    @Autowired
    public CarReviewController(CarReviewServices carReviewServices) {
        this.carReviewServices = carReviewServices;
    }
    @PostMapping("/car/{id}/createreview")
    public ResponseEntity<CarReviewDto> createcarreview(@PathVariable int id, @RequestBody CarReviewDto carReviewDto){
        return ResponseEntity.ok(carReviewServices.createCarReviews(id, carReviewDto));
    }
    @GetMapping("/car/{id}/reviews")
    public ResponseEntity<List<CarReviewDto>> getallreviewsbyid(@PathVariable int id){
        return ResponseEntity.ok(carReviewServices.getAllCarReviewsById(id));
    }
}
