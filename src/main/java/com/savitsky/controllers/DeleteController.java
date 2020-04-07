package com.savitsky.controllers;

import com.savitsky.entitys.City;
import com.savitsky.repositories.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@Controller
public class DeleteController {
    @Autowired
    private CitiesRepository citiesRepository;

    @GetMapping("/delete")
    public String getDelete(Map<String, Object> model) {
        Iterable<City> cities = citiesRepository.findAll();
        model.put("cities", cities);
        return "delete";
    }

    @PostMapping("/delete")
    public RedirectView delete(@RequestParam("id") City city
    ) {
        citiesRepository.delete(city);
        return new RedirectView("delete");
    }
}
