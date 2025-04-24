package com.gestionstock.controller;

import com.gestionstock.model.Produit;
import com.gestionstock.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;

@Controller
@RequestMapping("/alertes")
public class AlerteController {
    private static final Logger logger = LoggerFactory.getLogger(AlerteController.class);
    
    @Autowired
    private ProduitService produitService;

    @GetMapping
    public String listAlertes(Model model) {
        try {
            logger.info("Accès à la page des alertes");
            
            // Récupérer les produits en rupture de stock
            List<Produit> produitsEnRupture = produitService.getProduitsEnRupture();
            model.addAttribute("produitsEnRupture", produitsEnRupture);
            logger.info("{} produits en rupture de stock trouvés", produitsEnRupture.size());
            
            // Récupérer les produits approchant le seuil critique
            List<Produit> produitsSeuilCritique = produitService.getProduitsApprochantSeuil();
            model.addAttribute("produitsSeuilCritique", produitsSeuilCritique);
            logger.info("{} produits approchant le seuil critique trouvés", produitsSeuilCritique.size());
            
            // Ajouter des messages d'information
            if (produitsEnRupture.isEmpty() && produitsSeuilCritique.isEmpty()) {
                model.addAttribute("message", "Aucune alerte de stock à afficher");
            } else {
                model.addAttribute("message", "Alertes de stock mises à jour");
            }
            
            return "alertes";
        } catch (Exception e) {
            logger.error("Erreur lors de l'accès à la page des alertes", e);
            model.addAttribute("error", "Une erreur est survenue lors de la récupération des alertes");
            return "error";
        }
    }
} 