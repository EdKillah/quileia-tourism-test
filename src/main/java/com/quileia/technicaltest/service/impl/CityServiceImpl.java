package com.quileia.technicaltest.service.impl;

import com.quileia.technicaltest.entity.City;
import com.quileia.technicaltest.repository.CityRepository;
import com.quileia.technicaltest.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public City saveCity(City city) {
        return cityRepository.save(city);
    }

    @Override
    public City findCityById(int id) {
        return cityRepository.getById(id);
    }

    @Override
    public List<City> findAllCities() {
        return cityRepository.findAll();
    }

    @Override
    public City updateCity(int id, City city) {
        City cityDB = getCityById(id);
        if(cityDB != null){
            cityDB.setName(city.getName());
            cityDB.setPopulation(city.getPopulation());
            cityDB.setTouristicHotel(city.getTouristicHotel());
            cityDB.setTouristicPlace(city.getTouristicPlace());
        }
        return cityRepository.save(city);
    }

    @Override
    public City removeCity(int id) {
        City cityDB = getCityById(id);
        if(cityDB == null){
            System.out.println("La ciudad que quiere borrar no existe");
        }else{
            cityRepository.delete(cityDB);
            System.out.println("Elimino la ciudad correctamente");
        }
        return cityDB;
    }

    private City getCityById(int id){
        return cityRepository.findById(id).orElse(null);
    }
}
