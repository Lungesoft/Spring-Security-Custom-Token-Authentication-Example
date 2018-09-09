package com.lungesoft.token.example.controller;

import com.lungesoft.token.example.model.City;
import com.lungesoft.token.example.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/city")
    public List<City> handleRegistration() {
        return cityService.retrieveAllCities();
    }

}
