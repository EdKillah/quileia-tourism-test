package com.quileia.technicaltest.dto.touristincity;

import com.quileia.technicaltest.dto.city.CityDTO;
import com.quileia.technicaltest.entity.City;
import com.quileia.technicaltest.entity.Tourist;
import com.quileia.technicaltest.entity.TouristInCityID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TouristInCityDTO {

    private CityDTO city;

    private Date date;

}
