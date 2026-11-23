
package com.Ferrestock.tienda.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "clientes")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idclientes")
    private Integer idCliente;

    @Column(name = "nombre", length = 100, nullable = false)
    private String nombre;

    @Column(name = "documento", length = 30,
            nullable = false, unique = true)
    private String documento;

    @Column(name = "telefono", length = 20)
    private String telefono;

}
