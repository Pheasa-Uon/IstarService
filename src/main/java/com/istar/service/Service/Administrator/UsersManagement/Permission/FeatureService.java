package com.istar.service.Service.Administrator.UsersManagement.Permission;

import com.istar.service.Entity.Administrator.UsersManagment.Feature;
import com.istar.service.Repository.Administrator.UsersManagement.FeatureRepository;
import com.istar.service.dto.Administrator.UsersManagement.FeatureTreeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FeatureService {

    @Autowired
    private FeatureRepository featureRepository;

    public List<FeatureTreeDTO> getFeatureTree() {
        List<Feature> roots = featureRepository.findByParentIsNull();
        return roots.stream().map(this::convertToTreeDTO).collect(Collectors.toList());
    }

    private FeatureTreeDTO convertToTreeDTO(Feature feature) {
        FeatureTreeDTO dto = new FeatureTreeDTO();
        dto.setId(feature.getId());
        dto.setName(feature.getName());
        dto.setCode(feature.getCode());
        dto.setType(feature.getType());
        dto.setRoutePath(feature.getRoutePath());
        dto.setIcon(feature.getIcon());
        dto.setBStatus(feature.getBStatus());
        dto.setOrder(feature.getdisplayOrder());

        if (feature.getChildren() != null && !feature.getChildren().isEmpty()) {
            List<FeatureTreeDTO> childDTOs = feature.getChildren().stream()
                    .map(this::convertToTreeDTO).collect(Collectors.toList());
            dto.setChildren(childDTOs);
        }
        return dto;
    }

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
            feature.setdisplayOrder(updated.getdisplayOrder());
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
