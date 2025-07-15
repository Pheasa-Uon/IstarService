package com.istar.service.Repository.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Feature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeatureRepository extends JpaRepository<Feature, Long> {
    List<Feature> findByParentIsNull(); // For top-level menus
}