package com.quileia.technicaltest.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.core.serializer.Serializer;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor @AllArgsConstructor
public class City implements Serializable {

    @Id
    private int id;

    private String name;

    private int population;

    private String touristicPlace;

    private String touristicHotel;




}
