package com.gestionstock.service;

import com.gestionstock.model.Produit;
import com.gestionstock.repository.ProduitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

@Service
public class ProduitService {
    private static final Logger logger = LoggerFactory.getLogger(ProduitService.class);
    
    @Autowired
    private ProduitRepository produitRepository;

    public List<Produit> getAllProduits() {
        try {
            logger.debug("Récupération de tous les produits");
            return produitRepository.findAll();
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des produits", e);
            throw e;
        }
    }

    public Optional<Produit> getProduitById(Long id) {
        try {
            logger.debug("Récupération du produit avec l'ID: {}", id);
            return produitRepository.findById(id);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération du produit avec l'ID: {}", id, e);
            throw e;
        }
    }

    public Produit createProduit(Produit produit) {
        try {
            logger.debug("Création d'un nouveau produit: {}", produit);
            if (produit.getId() == null) {
                produit.setId(System.currentTimeMillis());
            }
            // Initialiser les seuils si non définis
            if (produit.getSeuilMinimum() == null) {
                produit.setSeuilMinimum(10);
            }
            if (produit.getSeuilCritique() == null) {
                produit.setSeuilCritique(5);
            }
            return produitRepository.save(produit);
        } catch (Exception e) {
            logger.error("Erreur lors de la création du produit", e);
            throw e;
        }
    }

    public Produit updateProduit(Long id, Produit produit) {
        try {
            logger.debug("Mise à jour du produit avec l'ID: {}", id);
            if (produitRepository.existsById(id)) {
                produit.setId(id);
                return produitRepository.save(produit);
            }
            return null;
        } catch (Exception e) {
            logger.error("Erreur lors de la mise à jour du produit avec l'ID: {}", id, e);
            throw e;
        }
    }

    public void deleteProduit(Long id) {
        try {
            logger.debug("Suppression du produit avec l'ID: {}", id);
            produitRepository.deleteById(id);
        } catch (Exception e) {
            logger.error("Erreur lors de la suppression du produit avec l'ID: {}", id, e);
            throw e;
        }
    }

    public List<Produit> getProduitsByCategorie(Long categorieId) {
        try {
            logger.debug("Récupération des produits de la catégorie: {}", categorieId);
            return produitRepository.findByCategorieId(categorieId);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des produits de la catégorie: {}", categorieId, e);
            throw e;
        }
    }

    public List<Produit> getProduitsEnRupture() {
        try {
            logger.debug("Récupération des produits en rupture de stock");
            List<Produit> produits = produitRepository.findByQuantiteLessThanEqual(0);
            logger.info("{} produits en rupture de stock trouvés", produits.size());
            return produits;
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des produits en rupture de stock", e);
            throw e;
        }
    }

    public List<Produit> getProduitsApprochantSeuil() {
        try {
            logger.debug("Récupération des produits approchant le seuil critique");
            List<Produit> produits = produitRepository.findAll().stream()
                .filter(p -> p.getQuantite() <= p.getSeuilCritique())
                .toList();
            logger.info("{} produits approchant le seuil critique trouvés", produits.size());
            return produits;
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des produits approchant le seuil critique", e);
            throw e;
        }
    }

    public List<Produit> getProduitsParEmplacement(Long emplacementId) {
        try {
            logger.debug("Récupération des produits de l'emplacement: {}", emplacementId);
            return produitRepository.findByEmplacementId(emplacementId);
        } catch (Exception e) {
            logger.error("Erreur lors de la récupération des produits de l'emplacement: {}", emplacementId, e);
            throw e;
        }
    }
} 