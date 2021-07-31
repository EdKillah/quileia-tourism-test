package com.quileia.technicaltest.controller;

import com.quileia.technicaltest.entity.City;
import com.quileia.technicaltest.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/")
    public ResponseEntity<List<City>> getCities(){
        System.out.println("Entrando en ciudades");
        return ResponseEntity.ok(cityService.findAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<City> getCityById(@PathVariable("id")int id){
        return ResponseEntity.ok(cityService.findCityById(id));
    }


    @PostMapping
    public ResponseEntity<City> saveCity(@RequestBody City city){
        //return ResponseEntity.ok(cityService.saveCity(city));
        return new ResponseEntity(cityService.saveCity(city), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable("id")int id,@RequestBody City city){
        return new ResponseEntity(cityService.updateCity(id, city), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCityById(@PathVariable("id")int id){
        return new ResponseEntity(cityService.removeCity(id), HttpStatus.OK);
    }





}
