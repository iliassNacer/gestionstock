package com.gestionstock.repository;

import com.gestionstock.model.Produit;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProduitRepository extends MongoRepository<Produit, Long> {
    List<Produit> findByQuantiteLessThan(int seuil);
    List<Produit> findByCategorieId(Long categorieId);
    List<Produit> findByQuantiteLessThanEqual(int seuil);
    List<Produit> findByEmplacementId(Long emplacementId);
} 