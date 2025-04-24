package com.gestionstock.controller;

import com.gestionstock.model.Produit;
import com.gestionstock.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/produits")
public class ProduitRestController {
    @Autowired
    private ProduitService produitService;

    @GetMapping
    public List<Produit> getAllProduits() {
        return produitService.getAllProduits();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produit> getProduitById(@PathVariable Long id) {
        return produitService.getProduitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Produit createProduit(@RequestBody Produit produit) {
        return produitService.createProduit(produit);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produit> updateProduit(@PathVariable Long id, @RequestBody Produit produit) {
        Produit updated = produitService.updateProduit(id, produit);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduit(@PathVariable Long id) {
        produitService.deleteProduit(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/categorie/{categorieId}")
    public List<Produit> getProduitsByCategorie(@PathVariable Long categorieId) {
        return produitService.getProduitsByCategorie(categorieId);
    }

    @GetMapping("/rupture")
    public List<Produit> getProduitsEnRupture() {
        return produitService.getProduitsEnRupture();
    }

    @GetMapping("/seuil-critique")
    public List<Produit> getProduitsApprochantSeuil() {
        return produitService.getProduitsApprochantSeuil();
    }

    @GetMapping("/emplacement/{emplacementId}")
    public List<Produit> getProduitsParEmplacement(@PathVariable Long emplacementId) {
        return produitService.getProduitsParEmplacement(emplacementId);
    }
} 