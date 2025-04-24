package com.gestionstock.controller;

import com.gestionstock.model.Produit;
import com.gestionstock.model.Categorie;
import com.gestionstock.service.ProduitService;
import com.gestionstock.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Controller
@RequestMapping("/produits")
public class ProduitController {
    private static final Logger logger = LoggerFactory.getLogger(ProduitController.class);
    
    @Autowired
    private ProduitService produitService;
    
    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public String listProduits(Model model) {
        model.addAttribute("produits", produitService.getAllProduits());
        return "produits/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("produit", new Produit());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "produits/form";
    }

    @PostMapping("/save")
    public String saveProduit(@ModelAttribute Produit produit) {
        if (produit.getId() != null) {
            produitService.updateProduit(produit.getId(), produit);
        } else {
            produitService.createProduit(produit);
        }
        return "redirect:/produits";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Produit> produitOpt = produitService.getProduitById(id);
        if (produitOpt.isEmpty()) {
            return "redirect:/produits";
        }
        model.addAttribute("produit", produitOpt.get());
        model.addAttribute("categories", categorieService.getAllCategories());
        return "produits/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduit(@PathVariable Long id) {
        logger.info("Tentative de suppression du produit avec l'ID: {}", id);
        try {
            Optional<Produit> produitOpt = produitService.getProduitById(id);
            if (produitOpt.isPresent()) {
                produitService.deleteProduit(id);
                logger.info("Produit supprimé avec succès: {}", id);
            } else {
                logger.warn("Tentative de suppression d'un produit inexistant: {}", id);
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du produit: {}", id, e);
        }
        return "redirect:/produits";
    }
} 