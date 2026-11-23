
package com.Ferrestock.tienda.service;

import com.Ferrestock.tienda.model.Factura;
import com.Ferrestock.tienda.repository.FacturaRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

    private final FacturaRepository facturaRepository;

    public FacturaService(FacturaRepository fr) {
        this.facturaRepository = fr;
    }

    public List<Factura> listarFacturas() {
        return facturaRepository.findAll();
    }

    public Factura consultarFactura(Integer id) {
        return facturaRepository.findById(id)
            .orElseThrow(() -> new
                org.springframework.web.server.ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND,
                    "Factura no encontrada"));
    }
}
