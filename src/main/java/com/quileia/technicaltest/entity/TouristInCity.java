package com.quileia.technicaltest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tourist_in_cities")
@Data @AllArgsConstructor @NoArgsConstructor
public class TouristInCity {

    @EmbeddedId
    private TouristInCityID touristInCityID;

    @MapsId("touristId")
    @ManyToOne(fetch = FetchType.LAZY)
    private Tourist tourist;

    @MapsId("cityId")
    @ManyToOne(fetch = FetchType.LAZY)
    private City city;

    private Date date;

}
