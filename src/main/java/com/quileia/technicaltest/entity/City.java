package com.quileia.technicaltest.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode(exclude = "tourists")
@Table(name = "cities") @ToString
public class City implements Serializable {

    @Id
    private Integer id;

    private String name;

    private int population;

    private String touristicPlace;

    private String touristicHotel;

    @ToString.Exclude
    @JsonIgnore
    @OneToMany(mappedBy = "city", cascade =CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TouristInCity> tourists;


}
