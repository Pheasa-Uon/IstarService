package com.istar.service.repository;

import com.istar.service.model.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByParentIsNull(); // For top-level menus
}
