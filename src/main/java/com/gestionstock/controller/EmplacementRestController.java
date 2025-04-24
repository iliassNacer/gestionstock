package com.gestionstock.controller;

import com.gestionstock.model.Emplacement;
import com.gestionstock.service.EmplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/emplacements")
public class EmplacementRestController {
    @Autowired
    private EmplacementService emplacementService;

    @GetMapping
    public List<Emplacement> getAllEmplacements() {
        return emplacementService.getAllEmplacements();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Emplacement> getEmplacementById(@PathVariable Long id) {
        return emplacementService.getEmplacementById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Emplacement createEmplacement(@RequestBody Emplacement emplacement) {
        return emplacementService.createEmplacement(emplacement);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emplacement> updateEmplacement(@PathVariable Long id, @RequestBody Emplacement emplacement) {
        Emplacement updated = emplacementService.updateEmplacement(id, emplacement);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmplacement(@PathVariable Long id) {
        emplacementService.deleteEmplacement(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/entrepot/{entrepotId}")
    public List<Emplacement> getEmplacementsByEntrepot(@PathVariable Long entrepotId) {
        return emplacementService.getEmplacementsByEntrepot(entrepotId);
    }
} 