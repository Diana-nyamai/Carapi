package com.car.carsapi.dto;

import lombok.Data;

@Data
public class CarReviewDto {
    private int id;
    private String title;
    private String comment;
    private int stars;
}
