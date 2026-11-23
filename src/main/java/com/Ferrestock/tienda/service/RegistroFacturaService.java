
package com.Ferrestock.tienda.service;

import com.Ferrestock.tienda.model.*;
import com.Ferrestock.tienda.repository.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class RegistroFacturaService {

    private final FacturaRepository facturas;
    private final DetalleFacturaRepository detalles;
    private final ProductoService productos;

    public RegistroFacturaService(FacturaRepository f,
            DetalleFacturaRepository d, ProductoService p) {
        facturas = f;
        detalles = d;
        productos = p;
    }

    @Transactional
    public Factura registrar(SolicitudFactura solicitud) {
        if (solicitud == null || solicitud.getProductos() == null
                || solicitud.getProductos().isEmpty())
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Carrito vacío");

        BigDecimal total = BigDecimal.ZERO;

        for (ItemFactura item : solicitud.getProductos()) {
            if (item == null || item.getIdProducto() == null
                    || item.getCantidad() == null || item.getCantidad() <= 0)
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Producto o cantidad inválida");

            Producto p = productos.unProducto(item.getIdProducto());

            if (p.getStock() < item.getCantidad())
                throw new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "Stock insuficiente");

            total = total.add(p.getPrecio().multiply(
                    BigDecimal.valueOf(item.getCantidad())));
        }

        Factura factura = new Factura();
        factura.setFecha(LocalDate.now());
        factura.setIdUsuario(solicitud.getIdUsuario());
        factura.setIdCliente(solicitud.getIdCliente());
        factura.setTotal(total);
        factura = facturas.save(factura);

        for (ItemFactura item : solicitud.getProductos()) {
            Producto p = productos.unProducto(item.getIdProducto());

            DetalleFactura detalle = new DetalleFactura();
            detalle.setIdFactura(factura.getIdFactura());
            detalle.setIdProducto(item.getIdProducto());
            detalle.setCantidad(item.getCantidad());
            detalle.setPrecioUnitario(p.getPrecio());

            detalles.save(detalle);
        }

        return factura;
    }
}
