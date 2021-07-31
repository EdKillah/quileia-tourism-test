package com.quileia.technicaltest.repository;

import com.quileia.technicaltest.entity.Tourist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TouristRepository extends JpaRepository<Tourist, String> {


}
