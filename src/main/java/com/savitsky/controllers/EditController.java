package com.savitsky.controllers;

import com.savitsky.entitys.City;
import com.savitsky.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class EditController {
    @Autowired
    private CitiesRepository citiesRepository;

    @GetMapping("/edit")
    public String getEdit(Map<String, Object> model) {
        Iterable<City> cities = citiesRepository.findAll();
        model.put("cities", cities);
        return "edit";
    }


    @PostMapping("/edit")
    public RedirectView create(@RequestParam("id") City city,
                               @RequestParam String cityName,
                               @RequestParam String cityDescription) {

        if (!StringUtils.isEmpty(cityName)) {
            city.setCityName(cityName);
        }

        if (!StringUtils.isEmpty(cityDescription)) {
            city.setCityDescription(cityDescription);
        }

        citiesRepository.save(city);
        return new RedirectView("edit");
    }
}
