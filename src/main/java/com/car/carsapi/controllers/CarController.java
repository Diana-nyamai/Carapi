package com.car.carsapi.controllers;

import com.car.carsapi.dto.CarDto;
import com.car.carsapi.dto.GetAllCarsResponse;
import com.car.carsapi.models.Car;
import com.car.carsapi.services.CarServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class CarController {
    private CarServices carServices;
    @Autowired
    public CarController(CarServices carServices) {
        this.carServices = carServices;
    }

    //    create car
    @PostMapping("/car/create")
    public ResponseEntity<CarDto> createcar(@RequestBody CarDto carDto){
        System.out.println(carDto.getName());
        System.out.println(carDto.getModel());
        return ResponseEntity.ok(carServices.createcar(carDto));
    }
    @GetMapping("/car")
    public ResponseEntity<GetAllCarsResponse> getallcars(
            @RequestParam(value = "pageno", defaultValue = "0", required = false) int pageno,
            @RequestParam(value = "pagesize", defaultValue = "10", required = false) int pagesize
    ){
        return ResponseEntity.ok(carServices.getallcars(pageno, pagesize));
    }
    @GetMapping("/car/{id}")
    public ResponseEntity<CarDto> getonecar(@PathVariable int id){
        return ResponseEntity.ok(carServices.getonecar(id));
    }
    @PutMapping("/car/{id}/update")
    public ResponseEntity<CarDto> updateCars(@RequestBody CarDto carDto, @PathVariable int id){
        return ResponseEntity.ok(carServices.updatecar(carDto, id));
    }
    @DeleteMapping("/car/{id}/delete")
    public ResponseEntity<String> deletecar(@PathVariable int id){
        carServices.deletecar(id);
        return ResponseEntity.ok("car deleted successful");
    }
}
