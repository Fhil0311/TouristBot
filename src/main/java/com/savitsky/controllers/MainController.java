package com.savitsky.controllers;

import com.savitsky.entitys.City;
import com.savitsky.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainController {
    @Autowired
    private CitiesRepository citiesRepository;

    @GetMapping
    public String main(Map<String, Object> map) {
        Iterable<City> cities = citiesRepository.findAll();
        map.put("cities", cities);
        return "main";
    }
}
