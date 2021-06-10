package com.ceiba.pedido_producto.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DtoPedidoProducto {

    private String referenciaPedido;
    private String nombreProducto;
    private Long cantidad;
    private Double precio;
    private Long idProducto;


}
