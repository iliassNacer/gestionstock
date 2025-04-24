package com.gestionstock.controller;

import com.gestionstock.model.Categorie;
import com.gestionstock.service.CategorieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/categories")
public class CategorieController {
    private static final Logger logger = LoggerFactory.getLogger(CategorieController.class);
    
    @Autowired
    private CategorieService categorieService;

    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categorieService.getAllCategories());
        return "categories/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("categorie", new Categorie());
        return "categories/form";
    }

    @PostMapping("/save")
    public String saveCategorie(@ModelAttribute Categorie categorie) {
        if (categorie.getId() != null) {
            categorieService.updateCategorie(categorie.getId(), categorie);
        } else {
            categorieService.createCategorie(categorie);
        }
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Categorie categorie = categorieService.getCategorieById(id);
        if (categorie == null) {
            return "redirect:/categories";
        }
        model.addAttribute("categorie", categorie);
        return "categories/form";
    }

    @PostMapping("/delete/{id}")
    public String deleteCategorie(@PathVariable Long id) {
        logger.info("Tentative de suppression de la catégorie avec l'ID: {}", id);
        try {
            Categorie categorie = categorieService.getCategorieById(id);
            if (categorie != null) {
                categorieService.deleteCategorie(id);
                logger.info("Catégorie supprimée avec succès: {}", id);
            } else {
                logger.warn("Tentative de suppression d'une catégorie inexistante: {}", id);
            }
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de la catégorie: {}", id, e);
        }
        return "redirect:/categories";
    }
} 