package com.quileia.technicaltest.entity;

import javax.persistence.*;

@Entity
@Table(name = "tourist_in_cities")
public class TouristInCity {

    @EmbeddedId
    private TouristCityId id;

    @MapsId("touristId")
    @ManyToOne
    private Tourist tourist;

    @MapsId("cityId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;



}
