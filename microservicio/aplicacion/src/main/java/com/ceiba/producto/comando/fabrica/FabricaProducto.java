package com.ceiba.producto.comando.fabrica;

import com.ceiba.producto.comando.ComandoProducto;
import com.ceiba.producto.modelo.entidad.Producto;
import org.springframework.stereotype.Component;

@Component
public class FabricaProducto {

    public Producto crear(ComandoProducto ComandoProducto) {
        return new Producto( ComandoProducto.getNombre(), ComandoProducto.getCantidadDisponible(),
                ComandoProducto.getPrecio());
    }

}
