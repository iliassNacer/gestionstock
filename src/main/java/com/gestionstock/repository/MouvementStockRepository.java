package com.gestionstock.repository;

import com.gestionstock.model.MouvementStock;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MouvementStockRepository extends MongoRepository<MouvementStock, Long> {
    List<MouvementStock> findByProduitId(Long produitId);
    List<MouvementStock> findByDateMouvementBetween(java.time.LocalDateTime debut, java.time.LocalDateTime fin);
    List<MouvementStock> findByType(String type);
} 