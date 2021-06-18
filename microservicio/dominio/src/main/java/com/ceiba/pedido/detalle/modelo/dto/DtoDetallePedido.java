package com.ceiba.pedido.detalle.modelo.dto;

import lombok.Getter;

@Getter
public class DtoDetallePedido {
    private Long idProducto;
    private String nombreProducto;
    private Long cantidadPedido;
    private Long cantidadDisponible;
    private Double precio;

    public DtoDetallePedido(Long idProducto, String nombreProducto, Long cantidadPedido, Long cantidadDisponible, Double precio) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidadPedido = cantidadPedido;
        this.cantidadDisponible = cantidadDisponible;
        this.precio = precio;
    }
}
