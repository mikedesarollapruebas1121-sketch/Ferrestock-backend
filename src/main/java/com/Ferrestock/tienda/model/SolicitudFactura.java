
package com.Ferrestock.tienda.model;

import java.util.List;
import lombok.Data;

@Data
public class SolicitudFactura {

    private Integer idUsuario;
    private Integer idCliente;
    private List<ItemFactura> productos;
}
