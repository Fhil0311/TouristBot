package com.savitsky.controllers;

import com.savitsky.entitys.City;
import com.savitsky.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

@Controller
public class CreateController {

    @Autowired
    private CitiesRepository citiesRepository;

    @GetMapping("/create")
    public String getCreate() {
        return "create";
    }

    @PostMapping("/create")
    public String create(@RequestParam String cityName, @RequestParam String cityDescription, Map<String, Object> model) {
        City city = new City(cityName, cityDescription);
        citiesRepository.save(city);

        Iterable<City> cities = citiesRepository.findAll();
        model.put("cities", cities);
        return "create";
    }





}


