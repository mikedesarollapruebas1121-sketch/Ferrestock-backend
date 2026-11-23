
package com.Ferrestock.tienda.service;

import com.Ferrestock.tienda.model.Cliente;
import com.Ferrestock.tienda.repository.ClienteRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository c) {
        this.clienteRepository = c;
    }

    public List<Cliente> ListarClientes() {
        return clienteRepository.findAll();
    }

    public Cliente unCliente(Integer id) {
        return clienteRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Cliente no existente"
            )
        );
    }

    public Cliente registrarCliente(Cliente cli) {
        validarCliente(cli);

        if (clienteRepository.existsByDocumento(cli.getDocumento())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Documento ya registrado"
            );
        }

        return clienteRepository.save(cli);
    }

    public void borrarCliente(Integer id) {
        clienteRepository.delete(unCliente(id));
    }

    public Cliente modificarCliente(Integer id, Cliente cli) {
        validarCliente(cli);

        Cliente actualOExistente = unCliente(id);

        if (!actualOExistente.getDocumento().equals(cli.getDocumento())
                && clienteRepository.existsByDocumento(cli.getDocumento())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "Documento ya registrado"
            );
        }

        actualOExistente.setNombre(cli.getNombre());
        actualOExistente.setDocumento(cli.getDocumento());
        actualOExistente.setTelefono(cli.getTelefono());

        return clienteRepository.save(actualOExistente);
    }

    private void validarCliente(Cliente cli) {

        if (cli == null || cli.getNombre() == null
                || cli.getNombre().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Nombre del cliente obligatorio"
            );
        }

        if (cli.getDocumento() == null
                || cli.getDocumento().isBlank()) {
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Documento del cliente obligatorio"
            );
        }

        cli.setNombre(cli.getNombre().trim());
        cli.setDocumento(cli.getDocumento().trim());

        if (cli.getTelefono() != null) {
            cli.setTelefono(cli.getTelefono().trim());
        }
    }
}
