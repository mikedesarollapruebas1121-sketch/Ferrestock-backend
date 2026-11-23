
package com.Ferrestock.tienda.controller;

import com.Ferrestock.tienda.model.Producto;
import com.Ferrestock.tienda.service.ProductoService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/producto")

public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService ps) {
        this.productoService = ps;
    }

    @GetMapping("/")
    public List<Producto> listar() {
        return productoService.ListarProductos();
    }

    @GetMapping("/{id}")
    public Producto mostrarProducto(@PathVariable Integer id) {
        return productoService.unProducto(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Producto registrar(@RequestBody Producto prod) {
        return productoService.registrarProducto(prod);
    }

    @PutMapping("/{id}")
    public Producto actualizar(@PathVariable Integer id,
                               @RequestBody Producto prod) {
        return productoService.modificarProducto(id, prod);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void suprimir(@PathVariable Integer id) {
        productoService.borrarProducto(id);
    }
}
