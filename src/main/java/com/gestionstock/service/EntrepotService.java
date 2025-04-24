package com.gestionstock.service;

import com.gestionstock.model.Entrepot;
import com.gestionstock.repository.EntrepotRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EntrepotService {
    private static final Logger logger = LoggerFactory.getLogger(EntrepotService.class);
    
    @Autowired
    private EntrepotRepository entrepotRepository;

    public List<Entrepot> getAllEntrepots() {
        try {
            logger.debug("Récupération de tous les entrepôts");
            return entrepotRepository.findAll();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des entrepôts", e);
            throw e;
        }
    }

    public Optional<Entrepot> getEntrepotById(Long id) {
        try {
            logger.debug("Récupération de l'entrepôt avec l'ID: {}", id);
            return entrepotRepository.findById(id);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération de l'entrepôt avec l'ID: {}", id, e);
            throw e;
        }
    }

    public Entrepot createEntrepot(Entrepot entrepot) {
        try {
            logger.debug("Création d'un nouvel entrepôt: {}", entrepot);
            if (entrepot.getId() == null) {
                entrepot.setId(System.currentTimeMillis());
            }
            return entrepotRepository.save(entrepot);
        } catch (Exception e) {
            logger.error("Erreur lors de la création de l'entrepôt", e);
            throw e;
        }
    }

    public Entrepot updateEntrepot(Long id, Entrepot entrepot) {
        try {
            logger.debug("Mise à jour de l'entrepôt avec l'ID: {}", id);
            if (entrepotRepository.existsById(id)) {
                entrepot.setId(id);
                return entrepotRepository.save(entrepot);
            }
            return null;
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour de l'entrepôt avec l'ID: {}", id, e);
            throw e;
        }
    }

    public void deleteEntrepot(Long id) {
        try {
            logger.debug("Suppression de l'entrepôt avec l'ID: {}", id);
            entrepotRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression de l'entrepôt avec l'ID: {}", id, e);
            throw e;
        }
    }
} 