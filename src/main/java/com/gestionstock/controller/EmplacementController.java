package com.gestionstock.controller;

import com.gestionstock.model.Emplacement;
import com.gestionstock.model.Entrepot;
import com.gestionstock.service.EmplacementService;
import com.gestionstock.service.EntrepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Optional;

@Controller
@RequestMapping("/emplacements")
public class EmplacementController {
    private static final Logger logger = LoggerFactory.getLogger(EmplacementController.class);
    
    @Autowired
    private EmplacementService emplacementService;
    
    @Autowired
    private EntrepotService entrepotService;

    @GetMapping
    public String listEmplacements(Model model) {
        try {
            model.addAttribute("emplacements", emplacementService.getAllEmplacements());
            return "emplacements/list";
        } catch (Exception e) {
            logger.error("Erreur lors de l'accès à la liste des emplacements", e);
            model.addAttribute("error", "Une erreur est survenue lors de la récupération des emplacements");
            return "error";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        try {
            model.addAttribute("emplacement", new Emplacement());
            model.addAttribute("entrepots", entrepotService.getAllEntrepots());
            return "emplacements/form";
        } catch (Exception e) {
            logger.error("Erreur lors de l'accès au formulaire de création d'emplacement", e);
            model.addAttribute("error", "Une erreur est survenue lors de l'accès au formulaire");
            return "error";
        }
    }

    @PostMapping("/save")
    public String saveEmplacement(@ModelAttribute Emplacement emplacement) {
        try {
            if (emplacement.getId() != null) {
                emplacementService.updateEmplacement(emplacement.getId(), emplacement);
            } else {
                emplacementService.createEmplacement(emplacement);
            }
            return "redirect:/emplacements";
        } catch (Exception e) {
            logger.error("Erreur lors de la sauvegarde de l'emplacement", e);
            return "redirect:/emplacements?error=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Optional<Emplacement> emplacementOpt = emplacementService.getEmplacementById(id);
            if (emplacementOpt.isEmpty()) {
                return "redirect:/emplacements";
            }
            model.addAttribute("emplacement", emplacementOpt.get());
            model.addAttribute("entrepots", entrepotService.getAllEntrepots());
            return "emplacements/form";
        } catch (Exception e) {
            logger.error("Erreur lors de l'édition de l'emplacement", e);
            return "redirect:/emplacements?error=true";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteEmplacement(@PathVariable Long id) {
        try {
            emplacementService.deleteEmplacement(id);
            return "redirect:/emplacements";
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de l'emplacement", e);
            return "redirect:/emplacements?error=true";
        }
    }
} 