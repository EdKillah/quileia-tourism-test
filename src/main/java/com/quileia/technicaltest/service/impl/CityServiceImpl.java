package com.quileia.technicaltest.service.impl;


import com.quileia.technicaltest.dto.city.CityDTO;
import com.quileia.technicaltest.dto.tourist.TouristDTO;
import com.quileia.technicaltest.entity.City;
import com.quileia.technicaltest.entity.Tourist;
import com.quileia.technicaltest.entity.TouristInCity;
import com.quileia.technicaltest.entity.TouristInCityID;
import com.quileia.technicaltest.repository.CityRepository;
import com.quileia.technicaltest.repository.TouristInCityRepository;
import com.quileia.technicaltest.repository.TouristRepository;
import com.quileia.technicaltest.service.CityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private TouristRepository touristRepository;

    @Autowired
    private TouristInCityRepository touristInCityRepository;

    @Override
    public City saveCity(City city) {
        City cityDB = getCityById(city.getId());
        if(cityDB == null){
            cityDB =cityRepository.save(city);
            log.info("Guardo la ciudad {} exitosamente",city.getName());
        }
        log.error("La ciudad {} ya existe en base de datos.", city.getName());
        return cityDB;
    }

    @Override
    public CityDTO findCityById(int id) {
        log.info("Obteniendo ciudad con id {}", id);
        return parseCityDTO(getCityById(id));
    }

    @Override
    public List<CityDTO> findAllCities() {
        log.info("Solicitando todas las ciudades");
        return cityRepository.findAll().stream().map(city ->parseCityDTO(city)).collect(Collectors.toList());
    }

    @Override
    public City updateCity(int id, City city) {
        City cityDB = getCityById(id);
        if(cityDB != null){
            cityDB.setName(city.getName());
            cityDB.setPopulation(city.getPopulation());
            cityDB.setTouristicHotel(city.getTouristicHotel());
            cityDB.setTouristicPlace(city.getTouristicPlace());
            log.info("La ciudad con id {} se actualizo exitosamente.",id);
            return cityRepository.save(cityDB);
        }
        log.error("La ciudad con id {} no existe en base de datos",id);
        return null;
    }

    @Override
    public City removeCity(int id) {
        City cityDB = getCityById(id);
        if(cityDB == null){
            log.error("La ciudad con id: {} no existe",id);
        }else{
            cityRepository.delete(cityDB);
            log.info("Elimino la ciudad con id {} exitosamente",id);
        }
        return cityDB;
    }

    @Override
    public String addTourist(int idCity, String idTourist, Date date) throws ParseException {
        City cityDB = getCityById(idCity);
        if(cityDB != null){
            Tourist touristDB = touristRepository.findById(idTourist).orElse(null);
            if(touristDB != null){
                TouristInCityID ID = new TouristInCityID(touristDB.getId(), cityDB.getId());
                TouristInCity touristInCity = new TouristInCity(ID,touristDB, cityDB, date);
                List<TouristInCity> cityWithDay = touristInCityRepository.findByCity(cityDB);
                AtomicBoolean canSave = new AtomicBoolean(true);
                AtomicInteger cont= new AtomicInteger();
                cityWithDay.forEach(city -> {
                    if(city.getDate().compareTo(date)==0){
                        cont.getAndIncrement();
                    }
                    if(cont.intValue()>4){
                        canSave.set(false);
                        log.error("Se supero el máximo de turistas para la ciudad {} en un día", city.getCity().getName());
                        throw new IllegalArgumentException("CUPO LLENO PARA LA CIUDAD: "
                                +cityDB.getName() +" EN LA FECHA: "+date);
                    }
                });
                if(canSave.get()){
                    touristInCityRepository.save(touristInCity);
                    log.info("Se guardo el turista {} en la ciudad {} exitosamente", idTourist, idCity);
                }
            }
        }
        return "Tourist added successfully.";
    }

    private CityDTO parseCityDTO(City city){
        CityDTO cityDTO = new CityDTO();
        cityDTO.setId(city.getId());
        cityDTO.setName(city.getName());
        cityDTO.setPopulation(city.getPopulation());
        cityDTO.setTouristicHotel(city.getTouristicHotel());
        cityDTO.setTouristicPlace(city.getTouristicPlace());
        List<TouristInCity> prueba =city.getTourists();
        cityDTO.setTourists(city.getTourists().stream().map(touristInCity -> (
                parseTouristDTO(touristInCity.getTourist())
                )).collect(Collectors.toList()));
        return cityDTO;
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
                .build();
        return touristDTO;
    }


    private City getCityById(int id){
        return cityRepository.findById(id).orElse(null);
    }
}
