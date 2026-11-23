
package com.Ferrestock.tienda.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Data;

@Data
@Entity
@Table(name = "facturas")
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idfacturas")
    private Integer idFactura;

    private LocalDate fecha;
    private BigDecimal total;

    @Column(name = "usuarios_idusuarios")
    private Integer idUsuario;

    @Column(name = "clientes_idclientes")
    private Integer idCliente;
}
