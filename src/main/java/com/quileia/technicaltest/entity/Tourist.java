package com.quileia.technicaltest.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tourist {

    @Id
    private String id;

    private String typeId;

    private String fullName;

    private Date dateOfBirth;

    private int travelFrequency;

    private Double budget;

    //Esto podria ser solamente una ciudad pero vamos a intentarlo con varios destinos
    @OneToOne(mappedBy = "city",cascade = CascadeType.ALL, fetch = FetchType.LAZY) //mappedBy = "shoppingCart",
    private TouristInCity destiny;

    @Column(name="has_credit_card")
    private boolean hasCreditCard;







}
