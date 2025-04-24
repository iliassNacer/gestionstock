package com.gestionstock.repository;

import com.gestionstock.model.Entrepot;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface EntrepotRepository extends MongoRepository<Entrepot, Long> {
} 