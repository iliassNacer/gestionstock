package com.gestionstock.service;

import com.gestionstock.model.Emplacement;
import com.gestionstock.repository.EmplacementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmplacementService {
    @Autowired
    private EmplacementRepository emplacementRepository;

    public List<Emplacement> getAllEmplacements() {
        return emplacementRepository.findAll();
    }

    public Optional<Emplacement> getEmplacementById(Long id) {
        return emplacementRepository.findById(id);
    }

    public Emplacement createEmplacement(Emplacement emplacement) {
        if (emplacement.getId() == null) {
            emplacement.setId(System.currentTimeMillis());
        }
        return emplacementRepository.save(emplacement);
    }

    public Emplacement updateEmplacement(Long id, Emplacement emplacement) {
        if (emplacementRepository.existsById(id)) {
            emplacement.setId(id);
            return emplacementRepository.save(emplacement);
        }
        return null;
    }

    public void deleteEmplacement(Long id) {
        emplacementRepository.deleteById(id);
    }

    public List<Emplacement> getEmplacementsByEntrepot(Long entrepotId) {
        return emplacementRepository.findByEntrepotId(entrepotId);
    }
} 