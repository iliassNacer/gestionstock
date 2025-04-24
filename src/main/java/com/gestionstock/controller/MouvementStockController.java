package com.gestionstock.controller;

import com.gestionstock.model.MouvementStock;
import com.gestionstock.model.Produit;
import com.gestionstock.service.MouvementStockService;
import com.gestionstock.service.ProduitService;
import com.gestionstock.service.EmplacementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Controller
@RequestMapping("/mouvements")
public class MouvementStockController {
    private static final Logger logger = LoggerFactory.getLogger(MouvementStockController.class);
    
    @Autowired
    private MouvementStockService mouvementStockService;
    
    @Autowired
    private ProduitService produitService;
    
    @Autowired
    private EmplacementService emplacementService;

    @GetMapping
    public String listMouvements(Model model) {
        try {
            logger.info("Accès à la liste des mouvements");
            model.addAttribute("mouvements", mouvementStockService.getAllMouvements());
            return "mouvements/list";
        } catch (Exception e) {
            logger.error("Erreur lors de l'accès à la liste des mouvements", e);
            model.addAttribute("error", "Une erreur est survenue lors de la récupération des mouvements");
            return "error";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        try {
            logger.info("Accès au formulaire de création de mouvement");
            model.addAttribute("mouvement", new MouvementStock());
            model.addAttribute("produits", produitService.getAllProduits());
            model.addAttribute("emplacements", emplacementService.getAllEmplacements());
            return "mouvements/form";
        } catch (Exception e) {
            logger.error("Erreur lors de l'accès au formulaire de création de mouvement", e);
            model.addAttribute("error", "Une erreur est survenue lors de l'accès au formulaire");
            return "error";
        }
    }

    @PostMapping("/save")
    public String saveMouvement(@ModelAttribute MouvementStock mouvement) {
        try {
            logger.info("Tentative de sauvegarde du mouvement");
            if (mouvement.getId() != null) {
                mouvementStockService.updateMouvement(mouvement.getId(), mouvement);
                logger.info("Mouvement mis à jour avec succès: {}", mouvement.getId());
            } else {
                mouvementStockService.createMouvement(mouvement);
                logger.info("Nouveau mouvement créé avec succès");
            }
            return "redirect:/mouvements";
        } catch (Exception e) {
            logger.error("Erreur lors de la sauvegarde du mouvement", e);
            return "redirect:/mouvements?error=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            logger.info("Accès au formulaire d'édition du mouvement: {}", id);
            Optional<MouvementStock> mouvementOpt = mouvementStockService.getMouvementById(id);
            if (mouvementOpt.isEmpty()) {
                logger.warn("Tentative d'édition d'un mouvement inexistant: {}", id);
                return "redirect:/mouvements";
            }
            model.addAttribute("mouvement", mouvementOpt.get());
            model.addAttribute("produits", produitService.getAllProduits());
            model.addAttribute("emplacements", emplacementService.getAllEmplacements());
            return "mouvements/form";
        } catch (Exception e) {
            logger.error("Erreur lors de l'édition du mouvement: {}", id, e);
            return "redirect:/mouvements?error=true";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteMouvement(@PathVariable Long id) {
        try {
            logger.info("Tentative de suppression du mouvement: {}", id);
            Optional<MouvementStock> mouvementOpt = mouvementStockService.getMouvementById(id);
            if (mouvementOpt.isPresent()) {
                mouvementStockService.deleteMouvement(id);
                logger.info("Mouvement supprimé avec succès: {}", id);
            } else {
                logger.warn("Tentative de suppression d'un mouvement inexistant: {}", id);
            }
            return "redirect:/mouvements";
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du mouvement: {}", id, e);
            return "redirect:/mouvements?error=true";
        }
    }
} 