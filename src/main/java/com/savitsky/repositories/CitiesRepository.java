package com.savitsky.repositories;

import com.savitsky.entitys.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends CrudRepository<City, Long> {
    City findByCityName(String cityName);
}
