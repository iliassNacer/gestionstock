package com.gestionstock.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;
import java.time.LocalDateTime;

@Data
@Document(collection = "mouvements")
public class MouvementStock {
    @Id
    private Long id;
    private String type; // "ENTREE" ou "SORTIE"
    private int quantite;
    private LocalDateTime dateMouvement;
    private String motif;
    
    @DBRef
    private Produit produit;
    
    @DBRef
    private Emplacement emplacement;

    public MouvementStock() {
        this.dateMouvement = LocalDateTime.now();
    }
} 