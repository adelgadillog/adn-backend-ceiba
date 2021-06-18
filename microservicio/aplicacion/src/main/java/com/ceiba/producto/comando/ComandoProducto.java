package com.ceiba.producto.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoProducto {

    private String nombre;
    private Long cantidadDisponible;
    private Double precio;
}
