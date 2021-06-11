package com.ceiba.pedido_producto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoDetallePedido {
    private Long idProducto;
    private String nombreProducto;
    private Long cantidadPedido;
    private Long cantidadDisponible;
    private Double precio;

}
