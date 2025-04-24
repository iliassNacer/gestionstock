package com.gestionstock.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "categories")
public class Categorie {
    @Id
    private Long id;
    private String nom;
    private String description;
} 