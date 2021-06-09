package com.ceiba.producto.servicio.testdatabuilder;

import com.ceiba.producto.modelo.entidad.Producto;

public class ProductoTestDataBuilder {

    private Long id;
    private String nombre;
    private Long cantidadDisponible;
    private Double precio;

    public ProductoTestDataBuilder() {
        nombre = "Auriculares";
        cantidadDisponible = 17L;
        precio = 1000d;
    }



    public ProductoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Producto build() {
        return new Producto(id,nombre,cantidadDisponible,precio);
    }

}
