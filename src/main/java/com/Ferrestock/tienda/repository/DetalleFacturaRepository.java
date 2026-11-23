
package com.Ferrestock.tienda.repository;

import com.Ferrestock.tienda.model.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaRepository
        extends JpaRepository<DetalleFactura, Integer> {
}
