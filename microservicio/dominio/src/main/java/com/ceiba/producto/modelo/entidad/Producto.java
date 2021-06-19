package com.ceiba.producto.modelo.entidad;


import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@AllArgsConstructor
public class Producto {
    private static final String EL_PRODUCTO_DEBE_TENER_UN_NOMBRE = "El producto debe tener un nombre";
    private static final String EL_PRODUCTO_DEBE_TENER_UN_CANTIDAD = "El producto debe tener cantidad disponible";
    private static final String EL_PRODUCTO_DEBE_TENER_UN_PRECIO = "El producto debe tener un nombre";

    private Long id;
    private String nombre;
    private Long cantidadDisponible;
    private Double precio;

    public Producto(String nombre, Long cantidadDisponible, Double precio) {
        validarObligatorio(nombre,EL_PRODUCTO_DEBE_TENER_UN_NOMBRE);
        validarObligatorio(cantidadDisponible,EL_PRODUCTO_DEBE_TENER_UN_CANTIDAD);
        validarObligatorio(precio,EL_PRODUCTO_DEBE_TENER_UN_PRECIO);
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
    }


}
