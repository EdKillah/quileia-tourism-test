package com.quileia.technicaltest.service;


import com.quileia.technicaltest.entity.City;

import java.util.List;

public interface CityService {

    public City saveCity(City city);

    public City findCityById(int id);

    public List<City> findAllCities();

    public City updateCity(int id, City city);

    public City removeCity(int id);

}
