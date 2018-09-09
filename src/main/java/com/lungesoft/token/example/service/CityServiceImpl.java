package com.lungesoft.token.example.service;

import com.lungesoft.token.example.model.City;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    String[] cities = {
            "Auckland",
            "Wellington",
            "Christchurch",
            "Hamilton",
            "Tauranga",
            "Napier-Hastings",
            "Dunedin",
            "Palmerston North",
            "Nelson",
            "Rotorua",
            "Whangarei",
            "New Plymouth",
            "Invercargill",
            "Whanganui",
            "Gisborne"
    };

    @Override
    public List<City> retrieveAllCities() {
        List<City> citiesResult = new ArrayList<>();
        for (int i = 0; i < cities.length; i++) {
            citiesResult.add(new City(i + 1L, cities[i]));
        }
        return citiesResult;
    }
}
