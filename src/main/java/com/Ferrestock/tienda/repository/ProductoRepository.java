
package com.Ferrestock.tienda.repository;

import com.Ferrestock.tienda.model.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {

    List<Producto> findByNombre(String texto);

}
