package com.quileia.technicaltest.repository;

import com.quileia.technicaltest.entity.City;
import com.quileia.technicaltest.entity.TouristInCity;
import com.quileia.technicaltest.entity.TouristInCityID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TouristInCityRepository extends JpaRepository<TouristInCity, TouristInCityID> {

    List<TouristInCity> findByCity(City city);

}
