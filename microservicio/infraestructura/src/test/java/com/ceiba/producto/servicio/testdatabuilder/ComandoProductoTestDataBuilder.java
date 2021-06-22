package com.ceiba.producto.servicio.testdatabuilder;

import com.ceiba.producto.comando.ComandoProducto;


public class ComandoProductoTestDataBuilder {

    private String nombre;
    private Long cantidadDisponible;
    private Double precio;

    public ComandoProductoTestDataBuilder() {
        nombre = "Mouse";
        cantidadDisponible = 3L;
        precio = 1000000D;
    }

    public ComandoProductoTestDataBuilder conCantidadDisponible(Long cantidadDisponible){
        this.cantidadDisponible = cantidadDisponible;
        return this;
    }

    public ComandoProductoTestDataBuilder conPrecio(Double precio){
        this.precio = precio;
        return this;
    }


    public ComandoProducto build() {
        return new ComandoProducto(nombre, cantidadDisponible, precio);
    }
}
