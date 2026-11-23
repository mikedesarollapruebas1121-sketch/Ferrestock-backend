package com.Ferrestock.tienda.service;

import com.Ferrestock.tienda.model.Producto;
import com.Ferrestock.tienda.repository.ProductoRepository;
import java.util.List;
import java.math.BigDecimal;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository p) {
        this.productoRepository = p;
    }

    public List<Producto> ListarProductos() {
        return productoRepository.findAll();
    }

    public Producto unProducto(Integer id) {
        return productoRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Producto no existente"
            )
        );
    }

    public Producto registrarProducto(Producto prod) {
        validarProducto(prod);
        return productoRepository.save(prod);
    }

    public void borrarProducto(Integer id) {
        productoRepository.delete(unProducto(id));
    }

    public Producto modificarProducto(Integer id, Producto prod) {
        validarProducto(prod);

        Producto actualOExistente = unProducto(id);

        actualOExistente.setNombre(prod.getNombre());
        actualOExistente.setDescripcion(prod.getDescripcion());
        actualOExistente.setPrecio(prod.getPrecio());
        actualOExistente.setStock(prod.getStock());
        actualOExistente.setIdCategoria(prod.getIdCategoria());

        return productoRepository.save(actualOExistente);
    }

    private void validarProducto(Producto prod) {

        if (prod == null ||
            prod.getNombre() == null ||
            prod.getNombre().isBlank()) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Nombre del producto obligatorio"
            );
        }

        if (prod.getPrecio() == null ||
            prod.getPrecio().compareTo(BigDecimal.ZERO) < 0) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El precio debe ser mayor o igual a cero"
            );
        }

        if (prod.getStock() == null || prod.getStock() < 0) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "El stock no puede ser negativo"
            );
        }

        if (prod.getIdCategoria() == null) {

            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "La categoría es obligatoria"
            );
        }

        prod.setNombre(prod.getNombre().trim());
    }
}

