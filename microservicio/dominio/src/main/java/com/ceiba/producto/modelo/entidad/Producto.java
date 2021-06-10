package com.ceiba.producto.modelo.entidad;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Producto {

    private Long id;
    private String nombre;
    private Long cantidadDisponible;
    private Double precio;

    public Producto(String nombre, Long cantidadDisponible, Double precio) {
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
    }


}
