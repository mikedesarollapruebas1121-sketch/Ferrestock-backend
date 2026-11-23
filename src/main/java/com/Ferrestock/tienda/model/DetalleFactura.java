package com.Ferrestock.tienda.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import lombok.Data;

@Data
@Entity
@Table(name = "detalle_factura")
public class DetalleFactura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "iddetalle_factura")
    private Integer idDetalleFactura;

    private Integer cantidad;

    @Column(name = "productos_idproductos")
    private Integer idProducto;

    @Column(name = "facturas_idfacturas")
    private Integer idFactura;

    @Column(name = "precio_unitario")
    private BigDecimal precioUnitario;
}
