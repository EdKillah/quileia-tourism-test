package com.quileia.technicaltest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class City {

    @Id
    private int id;

    private String name;

    private int population;

    private String touristicPlace;

    private String touristicHotel;




}
