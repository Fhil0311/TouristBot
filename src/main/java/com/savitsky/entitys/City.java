package com.savitsky.entitys;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class City {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private String cityName;
    private String cityDescription;

    public City() {
    }

    public City(String cityName, String cityDescription) {
        this.cityName = cityName;
        this.cityDescription = cityDescription;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityDescription() {
        return cityDescription;
    }

    public void setCityDescription(String cityDescription) {
        this.cityDescription = cityDescription;
    }
}
