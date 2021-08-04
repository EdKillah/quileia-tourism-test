package com.quileia.technicaltest.service;


//import com.quileia.technicaltest.dto.city.CityDTO;
import com.quileia.technicaltest.dto.city.CityDTO;
import com.quileia.technicaltest.entity.City;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

public interface CityService {

    public City saveCity(City city);

    public CityDTO findCityById(int id);

    public List<CityDTO> findAllCities();

    public City updateCity(int id, City city);

    public City removeCity(int id);

    public String addTourist(int idCity, String idTourist, Date date) throws ParseException;

}
