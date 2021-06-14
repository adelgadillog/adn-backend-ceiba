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

}
