package com.quileia.technicaltest.service;

import com.quileia.technicaltest.dto.tourist.TouristDTO;
import com.quileia.technicaltest.entity.Tourist;

import java.util.List;

public interface TouristService {

    public Tourist saveTourist(Tourist tourist);

    public TouristDTO findTouristById(String id);

    public List<TouristDTO> findAllTourists();

    public Tourist updateTourist(String id, Tourist tourist);

    public TouristDTO deleteTourist(String id);

}
