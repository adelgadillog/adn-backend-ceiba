package com.ceiba.producto.modelo.entidad;


import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.ceiba.dominio.ValidadorArgumento.validarMenor;
import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@AllArgsConstructor
public class Producto {
    private static final String EL_PRODUCTO_DEBE_TENER_UN_NOMBRE = "El producto debe tener un nombre";
    private static final String EL_PRODUCTO_DEBE_TENER_UN_CANTIDAD = "El producto debe tener cantidad disponible";
    private static final String EL_PRODUCTO_DEBE_TENER_UN_CANTIDAD_MAYOR_QUE_CERO = "El producto debe tener cantidad disponible mayor que cero";
    private static final String EL_PRODUCTO_DEBE_TENER_UN_PRECIO_MAYOR_QUE_CERO = "El producto debe tener un precio mayor que cero";
    private static final String EL_PRODUCTO_DEBE_TENER_UN_PRECIO = "El producto debe tener un precio";
    private static final Long CANTIDAD_MINIMA = 1L;
    private static final Long VALOR_MINIMO = 1L;
    private static final Long CERO = 0L;

    private Long id;
    private String nombre;
    private Long cantidadDisponible;
    private Double precio;

    public Producto(String nombre, Long cantidadDisponible, Double precio) {
        validaciones(nombre, cantidadDisponible, precio);
        this.nombre = nombre;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
    }

    private void validaciones(String nombre, Long cantidadDisponible, Double precio) {
        validarObligatorio(nombre,EL_PRODUCTO_DEBE_TENER_UN_NOMBRE);
        validarObligatorio(cantidadDisponible,EL_PRODUCTO_DEBE_TENER_UN_CANTIDAD);
        validarMenor(CANTIDAD_MINIMA, cantidadDisponible,EL_PRODUCTO_DEBE_TENER_UN_CANTIDAD_MAYOR_QUE_CERO);
        validarObligatorio(precio,EL_PRODUCTO_DEBE_TENER_UN_PRECIO);
        validarMenor(VALOR_MINIMO, precio.longValue(),EL_PRODUCTO_DEBE_TENER_UN_PRECIO_MAYOR_QUE_CERO);
    }


}
