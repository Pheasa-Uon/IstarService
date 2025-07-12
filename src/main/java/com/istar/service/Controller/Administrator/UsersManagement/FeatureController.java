package com.istar.service.Controller.Administrator.UsersManagement;

import com.istar.service.Entity.Administrator.UsersManagment.Feature;
import com.istar.service.Service.Administrator.UsersManagement.FeatureService;
import com.istar.service.dto.Administrator.UsersManagement.TreeNodeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/features")
public class FeatureController {

    @Autowired
    private FeatureService featureService;

    @GetMapping
    public ResponseEntity<List<Feature>> getAllFeatures() {
        return ResponseEntity.ok(featureService.getAllFeatures());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Feature> getFeatureById(@PathVariable Long id) {
        return featureService.getFeatureById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Feature> createFeature(@RequestBody Feature feature) {
        return ResponseEntity.ok(featureService.createFeature(feature));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Feature> updateFeature(@PathVariable Long id, @RequestBody Feature feature) {
        return ResponseEntity.ok(featureService.updateFeature(id, feature));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeature(@PathVariable Long id) {
        featureService.deleteFeature(id);
        return ResponseEntity.ok().build();
    }

//    @GetMapping("/treetable")
//    public ResponseEntity<List<TreeNodeDTO>> getFeatureTreeTable() {
//        List<Feature> features = featureService.getAllFeatures();
//        List<TreeNodeDTO> tree = buildTree(features);
//        return ResponseEntity.ok(tree);
//    }

    // Add this endpoint
    @GetMapping("/treetable")
    public ResponseEntity<List<TreeNodeDTO>> getFeatureTreeTable() {
        List<Feature> features = featureService.getAllFeatures();
        Map<Long, TreeNodeDTO> nodeMap = new HashMap<>();
        List<TreeNodeDTO> rootNodes = new ArrayList<>();

        // Build node map
        for (Feature f : features) {
            TreeNodeDTO node = new TreeNodeDTO();
            node.setKey(String.valueOf(f.getId()));
            TreeNodeDTO.TreeNodeData data = new TreeNodeDTO.TreeNodeData();
            data.setName(f.getName());
            data.setDescription(f.getCode());
            node.setData(data);
            nodeMap.put(f.getId(), node);
        }

        // Build tree structure
        for (Feature f : features) {
            TreeNodeDTO currentNode = nodeMap.get(f.getId());
            if (f.getParent() != null && f.getParent().getId() != null) {
                TreeNodeDTO parentNode = nodeMap.get(f.getParent().getId());
                if (parentNode != null) {
                    parentNode.getChildren().add(currentNode);
                } else {
                    rootNodes.add(currentNode); // Orphan handling
                }
            } else {
                rootNodes.add(currentNode);
            }
        }

        return ResponseEntity.ok(rootNodes);
    }

}
