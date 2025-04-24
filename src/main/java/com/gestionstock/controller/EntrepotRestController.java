package com.gestionstock.controller;

import com.gestionstock.model.Entrepot;
import com.gestionstock.service.EntrepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entrepots")
public class EntrepotRestController {
    @Autowired
    private EntrepotService entrepotService;

    @GetMapping
    public List<Entrepot> getAllEntrepots() {
        return entrepotService.getAllEntrepots();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Entrepot> getEntrepotById(@PathVariable Long id) {
        return entrepotService.getEntrepotById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Entrepot createEntrepot(@RequestBody Entrepot entrepot) {
        return entrepotService.createEntrepot(entrepot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Entrepot> updateEntrepot(@PathVariable Long id, @RequestBody Entrepot entrepot) {
        Entrepot updated = entrepotService.updateEntrepot(id, entrepot);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntrepot(@PathVariable Long id) {
        entrepotService.deleteEntrepot(id);
        return ResponseEntity.ok().build();
    }
} 