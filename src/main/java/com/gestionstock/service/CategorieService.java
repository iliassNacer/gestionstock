package com.gestionstock.service;

import com.gestionstock.model.Categorie;
import com.gestionstock.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategorieService {
    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(Long id) {
        return categorieRepository.findById(id).orElse(null);
    }

    public Categorie createCategorie(Categorie categorie) {
        if (categorie.getId() == null) {
            categorie.setId(System.currentTimeMillis());
        }
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(Long id, Categorie categorie) {
        if (categorieRepository.existsById(id)) {
            categorie.setId(id);
            return categorieRepository.save(categorie);
        }
        return null;
    }

    public void deleteCategorie(Long id) {
        categorieRepository.deleteById(id);
    }
} 