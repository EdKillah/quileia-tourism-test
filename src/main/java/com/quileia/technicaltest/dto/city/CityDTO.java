package com.quileia.technicaltest.dto.city;


import com.quileia.technicaltest.dto.tourist.TouristDTO;
import com.quileia.technicaltest.entity.Tourist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor
public class CityDTO {

    private Integer id;

    private String name;

    private int population;

    private String touristicPlace;

    private String touristicHotel;

    private List<TouristDTO> tourists;

}
