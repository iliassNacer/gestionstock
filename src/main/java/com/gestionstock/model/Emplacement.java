package com.gestionstock.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Data
@Document(collection = "emplacements")
public class Emplacement {
    @Id
    private Long id;
    private String nom;
    private String description;
    private Integer capacite;
    
    @DBRef
    private Entrepot entrepot;
} 