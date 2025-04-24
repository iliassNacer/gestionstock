package com.gestionstock.repository;

import com.gestionstock.model.Emplacement;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EmplacementRepository extends MongoRepository<Emplacement, Long> {
    List<Emplacement> findByEntrepotId(Long entrepotId);
} 