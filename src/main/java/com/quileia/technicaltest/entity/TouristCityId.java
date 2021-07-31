package com.quileia.technicaltest.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;


@Embeddable
@Data
public class TouristCityId implements Serializable {

    @Column(name = "tourist_id")
    private String touristId;

    @Column(name = "city_id")
    private int cityId;

}
