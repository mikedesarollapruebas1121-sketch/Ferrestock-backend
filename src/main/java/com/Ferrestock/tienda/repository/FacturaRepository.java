
package com.Ferrestock.tienda.repository;

import com.Ferrestock.tienda.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacturaRepository
        extends JpaRepository<Factura, Integer> {
}
