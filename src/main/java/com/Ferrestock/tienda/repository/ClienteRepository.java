
package com.Ferrestock.tienda.repository;

import com.Ferrestock.tienda.model.Cliente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    List<Cliente> findByNombre(String texto);
    
    boolean existsByDocumento(String documento);
    
}
