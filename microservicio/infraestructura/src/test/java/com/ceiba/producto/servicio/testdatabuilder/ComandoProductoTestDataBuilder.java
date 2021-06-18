package com.ceiba.producto.servicio.testdatabuilder;

import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.producto.comando.ComandoProducto;

import java.time.LocalDateTime;

public class ComandoProductoTestDataBuilder {

    private String nombre;
    private Long cantidadDisponible;
    private Double precio;

    public ComandoProductoTestDataBuilder() {
        nombre = "Mouse";
        cantidadDisponible = 3L;
        precio = 1000000D;
    }

   

    public ComandoProducto build() {
        return new ComandoProducto(nombre, cantidadDisponible, precio);
    }
}
