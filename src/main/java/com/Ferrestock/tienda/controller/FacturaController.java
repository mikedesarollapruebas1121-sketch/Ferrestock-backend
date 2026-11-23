
package com.Ferrestock.tienda.controller;

import com.Ferrestock.tienda.model.*;
import com.Ferrestock.tienda.service.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/factura")
@CrossOrigin(origins = "http://localhost:5173")
public class FacturaController {

    private final FacturaService facturas;
    private final RegistroFacturaService registro;

    public FacturaController(FacturaService f,
            RegistroFacturaService r) {
        facturas = f;
        registro = r;
    }

    @GetMapping("/")
    public List<Factura> listar() {
        return facturas.listarFacturas();
    }

    @PostMapping("/")
    public Factura registrar(@RequestBody SolicitudFactura solicitud) {
        return registro.registrar(solicitud);
    }
}
