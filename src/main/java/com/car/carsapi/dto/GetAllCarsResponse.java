package com.car.carsapi.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetAllCarsResponse {
    private List<CarDto> content;
    private int pageno;
    private int pagesize;
    private long totalelements;
    private int totalpages;
    private boolean last;
}
