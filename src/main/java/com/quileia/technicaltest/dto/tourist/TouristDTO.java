package com.quileia.technicaltest.dto.tourist;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.quileia.technicaltest.dto.touristincity.TouristInCityDTO;
import com.quileia.technicaltest.entity.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.ManyToMany;
import java.util.Date;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class TouristDTO {

    private String id;

    private String typeId;

    private String fullName;

    private Date dateOfBirth;

    private Integer travelFrequency;

    private Double budget;

    private boolean hasCreditCard;

    private List<TouristInCityDTO> touristInCityDTO;
}
