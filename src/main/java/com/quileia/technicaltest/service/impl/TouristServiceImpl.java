package com.quileia.technicaltest.service.impl;


import com.quileia.technicaltest.dto.city.CityDTO;
import com.quileia.technicaltest.dto.tourist.TouristDTO;
import com.quileia.technicaltest.dto.touristincity.TouristInCityDTO;
import com.quileia.technicaltest.entity.City;
import com.quileia.technicaltest.entity.Tourist;

import com.quileia.technicaltest.entity.TouristInCity;
import com.quileia.technicaltest.entity.TouristInCityID;
import com.quileia.technicaltest.repository.TouristInCityRepository;
import com.quileia.technicaltest.repository.TouristRepository;
import com.quileia.technicaltest.service.TouristService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;


@Service
@Slf4j
public class TouristServiceImpl implements TouristService {

    @Autowired
    private TouristRepository touristRepository;

    @Autowired
    private TouristInCityRepository touristInCityRepository;


    @Override
    public Tourist saveTourist(Tourist tourist) {
        Tourist touristDB = getTouristById(tourist.getId());
        if(touristDB == null){
            touristDB = touristRepository.save(tourist);
            log.info("Registro del turista {} exitoso", tourist.getId());
        }
        log.error("El usuario {} ya existe en la base de datos", tourist.getId());
        return touristDB;
    }

    @Override
    public TouristDTO findTouristById(String id) {
        log.info("Buscando tourista con id: {}", id);
        return parseTouristDTO(getTouristById(id));
    }

    @Override
    public List<TouristDTO> findAllTourists() {
        log.info("Buscando todos los turistas");
        List<TouristDTO> tourists = touristRepository.findAll().stream().map(tourist -> (
            parseTouristDTO(tourist)
            )).collect(Collectors.toList());

        return tourists;
    }

    @Override
    public Tourist updateTourist(String id, Tourist tourist) {
        Tourist touristDB = getTouristById(id);
        log.info("Actualizando turista con id: {}", id);
        if(touristDB != null){
            if(tourist.getBudget()!=null)
                touristDB.setBudget(tourist.getBudget());
            if(tourist.getFullName()!=null)
                touristDB.setFullName(tourist.getFullName());
            if(tourist.getId()!=null)
                touristDB.setId(tourist.getId());
            if(tourist.getDateOfBirth()!=null)
                touristDB.setDateOfBirth(tourist.getDateOfBirth());
            if(tourist.getTravelFrequency()!=null)
                touristDB.setTravelFrequency(tourist.getTravelFrequency());
            touristDB.setTypeId(tourist.getTypeId());
            touristDB.setHasCreditCard(tourist.isHasCreditCard());
            touristRepository.save(touristDB);
        }
        log.info("El turista con id: {} no pudo ser actualizado por falta de cuerpo",id);
        return tourist;
    }

    @Override
    public TouristDTO deleteTourist(String id) {
        Tourist touristDB = getTouristById(id);
        if(touristDB != null){
            log.info("Turista con id {} eliminado exitosamente",id);
            touristRepository.delete(touristDB);
        }else{
            log.error("No se pudo eliminar el turista con id: {}",id);
        }
        return parseTouristDTO(touristDB);
    }

    private TouristDTO parseTouristDTO(Tourist tourist){
        TouristDTO touristDTO = TouristDTO.builder()
                .budget(tourist.getBudget())
                .id(tourist.getId())
                .dateOfBirth(tourist.getDateOfBirth())
                .fullName(tourist.getFullName())
                .hasCreditCard(tourist.isHasCreditCard())
                .travelFrequency(tourist.getTravelFrequency())
                .typeId(tourist.getTypeId())
                .touristInCityDTO(tourist.getDestiny().stream().map(touristCity -> (
                        parseTouristInCityDTO(touristCity)
                )).collect(Collectors.toList()))
                .build();
        return touristDTO;
    }

    private TouristInCityDTO parseTouristInCityDTO(TouristInCity touristInCity){
        TouristInCityDTO touristInCityDTO = TouristInCityDTO.builder()
                .city(parseCityDTO(touristInCity.getCity()))
                .date(touristInCity.getDate())
                .build();
        return touristInCityDTO;
    }

    private CityDTO parseCityDTO(City city){
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setPopulation(city.getPopulation());
        cityDTO.setTouristicHotel(city.getTouristicHotel());
        cityDTO.setTouristicPlace(city.getTouristicPlace());
        return cityDTO;
    }


    private Tourist getTouristById(String id){
        return touristRepository.findById(id).orElse(null);
    }
}
