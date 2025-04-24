package com.gestionstock.service;

import com.gestionstock.model.MouvementStock;
import com.gestionstock.repository.MouvementStockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.time.LocalDateTime;

@Service
public class MouvementStockService {
    private static final Logger logger = LoggerFactory.getLogger(MouvementStockService.class);
    
    @Autowired
    private MouvementStockRepository mouvementStockRepository;

    public List<MouvementStock> getAllMouvements() {
        try {
            logger.debug("Récupération de tous les mouvements");
            return mouvementStockRepository.findAll();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des mouvements", e);
            throw e;
        }
    }

    public Optional<MouvementStock> getMouvementById(Long id) {
        try {
            logger.debug("Récupération du mouvement avec l'ID: {}", id);
            return mouvementStockRepository.findById(id);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du mouvement avec l'ID: {}", id, e);
            throw e;
        }
    }

    public MouvementStock createMouvement(MouvementStock mouvement) {
        try {
            logger.debug("Création d'un nouveau mouvement: {}", mouvement);
            if (mouvement.getId() == null) {
                mouvement.setId(System.currentTimeMillis());
            }
            return mouvementStockRepository.save(mouvement);
        } catch (Exception e) {
            logger.error("Erreur lors de la création du mouvement", e);
            throw e;
        }
    }

    public MouvementStock updateMouvement(Long id, MouvementStock mouvement) {
        try {
            logger.debug("Mise à jour du mouvement avec l'ID: {}", id);
            if (mouvementStockRepository.existsById(id)) {
                mouvement.setId(id);
                return mouvementStockRepository.save(mouvement);
            }
            return null;
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour du mouvement avec l'ID: {}", id, e);
            throw e;
        }
    }

    public void deleteMouvement(Long id) {
        try {
            logger.debug("Suppression du mouvement avec l'ID: {}", id);
            mouvementStockRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du mouvement avec l'ID: {}", id, e);
            throw e;
        }
    }

    public List<MouvementStock> getMouvementsByProduit(Long produitId) {
        try {
            logger.debug("Récupération des mouvements pour le produit avec l'ID: {}", produitId);
            return mouvementStockRepository.findByProduitId(produitId);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des mouvements pour le produit avec l'ID: {}", produitId, e);
            throw e;
        }
    }

    public List<MouvementStock> getMouvementsByDateRange(LocalDateTime debut, LocalDateTime fin) {
        try {
            logger.debug("Récupération des mouvements entre {} et {}", debut, fin);
            return mouvementStockRepository.findByDateMouvementBetween(debut, fin);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des mouvements entre {} et {}", debut, fin, e);
            throw e;
        }
    }

    public List<MouvementStock> getMouvementsByType(String type) {
        try {
            logger.debug("Récupération des mouvements de type: {}", type);
            return mouvementStockRepository.findByType(type);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des mouvements de type: {}", type, e);
            throw e;
        }
    }
} 