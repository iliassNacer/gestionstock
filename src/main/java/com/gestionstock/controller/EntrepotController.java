package com.gestionstock.controller;

import com.gestionstock.model.Entrepot;
import com.gestionstock.service.EntrepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/entrepots")
public class EntrepotController {
    private static final Logger logger = LoggerFactory.getLogger(EntrepotController.class);
    
    @Autowired
    private EntrepotService entrepotService;

    @GetMapping
    public String listEntrepots(Model model) {
        try {
            logger.info("Accès à la liste des entrepôts");
            List<Entrepot> entrepots = entrepotService.getAllEntrepots();
            model.addAttribute("entrepots", entrepots);
            return "entrepots/list";
        } catch (Exception e) {
            logger.error("Erreur lors de l'accès à la liste des entrepôts", e);
            model.addAttribute("error", "Une erreur est survenue lors de la récupération des entrepôts");
            return "error";
        }
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("entrepot", new Entrepot());
        return "entrepots/form";
    }

    @PostMapping("/save")
    public String saveEntrepot(@ModelAttribute Entrepot entrepot) {
        try {
            if (entrepot.getId() != null) {
                entrepotService.updateEntrepot(entrepot.getId(), entrepot);
            } else {
                entrepotService.createEntrepot(entrepot);
            }
            return "redirect:/entrepots";
        } catch (Exception e) {
            logger.error("Erreur lors de la sauvegarde de l'entrepôt", e);
            return "redirect:/entrepots?error=true";
        }
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        try {
            Optional<Entrepot> entrepotOpt = entrepotService.getEntrepotById(id);
            if (entrepotOpt.isEmpty()) {
                return "redirect:/entrepots";
            }
            model.addAttribute("entrepot", entrepotOpt.get());
            return "entrepots/form";
        } catch (Exception e) {
            logger.error("Erreur lors de l'édition de l'entrepôt", e);
            return "redirect:/entrepots?error=true";
        }
    }

    @PostMapping("/delete/{id}")
    public String deleteEntrepot(@PathVariable Long id) {
        try {
            entrepotService.deleteEntrepot(id);
            return "redirect:/entrepots";
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de l'entrepôt", e);
            return "redirect:/entrepots?error=true";
        }
    }
} 