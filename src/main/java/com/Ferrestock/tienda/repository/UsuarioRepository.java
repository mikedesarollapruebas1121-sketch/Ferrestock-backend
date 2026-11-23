
package com.Ferrestock.tienda.repository;

import com.Ferrestock.tienda.model.Usuario;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    List<Usuario> findByNombre(String texto);

    Optional<Usuario> findByCorreo(String correo);

    boolean existsByCorreo(String correo);
}
