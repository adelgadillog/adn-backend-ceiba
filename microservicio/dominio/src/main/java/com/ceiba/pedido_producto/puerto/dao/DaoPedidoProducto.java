package com.ceiba.pedido_producto.puerto.dao;

import com.ceiba.pedido_producto.modelo.dto.DtoPedidoProducto;

import java.util.List;

public interface DaoPedidoProducto {

    /**
     * Permite listar pedidos_productos
     * @return los pedidos_productos
     */
    List<DtoPedidoProducto> listar(String referencia);
}
