package com.Ferrestock.tienda.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table (name ="categorias")
public class Categoria {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "idcategorias")
    private Integer idCategoria;
    private String nombre;

    
    
}
