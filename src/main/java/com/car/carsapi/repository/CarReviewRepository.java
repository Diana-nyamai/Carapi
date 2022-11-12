package com.car.carsapi.repository;

import com.car.carsapi.models.CarR;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarReviewRepository extends JpaRepository<CarR, Integer> {
    List<CarR> findByCarId(int carid);
}
