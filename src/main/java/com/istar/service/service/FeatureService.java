package com.istar.service.service;

import com.istar.service.model.Feature;
import com.istar.service.repository.FeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    public List<Feature> getAllFeatures() {
        return featureRepository.findAll();
    }

    public Optional<Feature> getFeatureById(Long id) {
        return featureRepository.findById(id);
    }

    public Feature createFeature(Feature feature) {
        feature.setCreatedAt(LocalDateTime.now());
        feature.setUpdatedAt(LocalDateTime.now());
        return featureRepository.save(feature);
    }

    public Feature updateFeature(Long id, Feature updated) {
        return featureRepository.findById(id).map(feature -> {
            feature.setName(updated.getName());
            feature.setCode(updated.getCode());
            feature.setType(updated.getType());
            feature.setOrder(updated.getOrder());
            feature.setRoutePath(updated.getRoutePath());
            feature.setIcon(updated.getIcon());
            feature.setParent(updated.getParent());
            feature.setBStatus(updated.getBStatus());
            feature.setUpdatedAt(LocalDateTime.now());
            return featureRepository.save(feature);
        }).orElseThrow(() -> new RuntimeException("Feature not found with ID " + id));
    }

    public void deleteFeature(Long id) {
        featureRepository.deleteById(id);
    }
}
