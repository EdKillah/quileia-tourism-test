package com.quileia.technicaltest.entity;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor @NoArgsConstructor
public class TouristInCityID implements Serializable {

    @Column(name = "tourist_id")
    private String touristId;

    @Column(name = "city_id")
    private Integer cityId;

}
