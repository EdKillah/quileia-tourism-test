package com.quileia.technicaltest.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "destiny")
@Table(name = "tourists")
public class Tourist implements Serializable {

    @Id
    private String id;

    @NotNull(message = "type Id must be included")
    @NotEmpty(message = "Type Id must cointain a valid value (CC) (TI) (CE)")
    private String typeId;

    @NotNull
    private String fullName;

    @NotNull
    private Date dateOfBirth;

    @NotNull
    private Integer travelFrequency;

    @NotNull
    @Min(0)
    private Double budget;


    @OneToMany(mappedBy = "tourist", cascade =CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TouristInCity> destiny;


    @NotNull
    @Column(name="has_credit_card")
    private boolean hasCreditCard;







}
