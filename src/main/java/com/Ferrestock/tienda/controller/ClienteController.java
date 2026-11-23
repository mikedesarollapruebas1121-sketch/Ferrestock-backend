
package com.Ferrestock.tienda.controller;

import com.Ferrestock.tienda.model.Cliente;
import com.Ferrestock.tienda.service.ClienteService;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/cliente")

public class ClienteController {

    private final ClienteService clienteService;

    public ClienteController(ClienteService cs) {
        this.clienteService = cs;
    }

    @GetMapping("/")
    public List<Cliente> listar() {
        return clienteService.ListarClientes();
    }

    @GetMapping("/{id}")
    public Cliente mostrarCliente(@PathVariable Integer id) {
        return clienteService.unCliente(id);
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente registrar(@RequestBody Cliente cli) {
        return clienteService.registrarCliente(cli);
    }

    @PutMapping("/{id}")
    public Cliente actualizar(@PathVariable Integer id,
                              @RequestBody Cliente cli) {
        return clienteService.modificarCliente(id, cli);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void suprimir(@PathVariable Integer id) {
        clienteService.borrarCliente(id);
    }
}
