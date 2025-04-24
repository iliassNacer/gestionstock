package com.gestionstock.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDateTime;

@Data
@Document(collection = "produits")
public class Produit {
    @Id
    private Long id;
    private String code;
    private String nom;
    private String description;
    private Double prix;
    private Integer quantite;
    private Integer seuilMinimum;
    private Integer seuilCritique;
    private Boolean notificationActive;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    
    @DBRef
    private Categorie categorie;
    
    @DBRef
    private Emplacement emplacement;

    public Produit() {
        this.dateCreation = LocalDateTime.now();
        this.dateModification = LocalDateTime.now();
        this.notificationActive = true;
    }
} 