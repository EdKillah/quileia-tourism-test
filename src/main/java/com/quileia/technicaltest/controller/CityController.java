package com.quileia.technicaltest.controller;


import com.quileia.technicaltest.dto.city.CityDTO;
import com.quileia.technicaltest.dto.tourist.TouristRequest;
import com.quileia.technicaltest.entity.City;
import com.quileia.technicaltest.service.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cities")
@CrossOrigin("*")
public class CityController {

    @Autowired
    private CityService cityService;

    @GetMapping("/")
    public ResponseEntity<List<CityDTO>> getCities(){
        return ResponseEntity.ok(cityService.findAllCities());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CityDTO> getCityById(@PathVariable("id")Integer id){
        return ResponseEntity.ok(cityService.findCityById(id));
    }


    @PostMapping
    public ResponseEntity<City> saveCity(@RequestBody City city){
        return new ResponseEntity(cityService.saveCity(city), HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<City> updateCity(@PathVariable("id")Integer id,@RequestBody City city){
        return new ResponseEntity(cityService.updateCity(id, city), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<City> deleteCityById(@PathVariable("id")Integer id){
        return new ResponseEntity(cityService.removeCity(id), HttpStatus.OK);
    }

    @PostMapping("/addTourist/{idCity}")
    public ResponseEntity<String> addTouristToCity(@PathVariable("idCity")Integer idCity,
                                                   @RequestBody TouristRequest touristRequest)
            throws ParseException {
        return new ResponseEntity(cityService.addTourist(idCity,
                touristRequest.getTouristId(),
                touristRequest.getDate()),
                HttpStatus.OK);
    }



}
