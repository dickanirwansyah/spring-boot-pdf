package com.app.demopdf.service;

import com.app.demopdf.entity.City;
import com.app.demopdf.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService implements ICityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public List<City> findAll() {
        List<City> cities = new ArrayList<>();
        for (City city : cityRepository.findAll()){
            cities.add(city);
        }
        return cities;
    }
}
