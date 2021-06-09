package com.ceiba.pedido_producto.puerto.dao;

import com.ceiba.pedido_producto.modelo.dto.DtoPedidoProducto;
import com.ceiba.producto.modelo.dto.DtoProducto;

import java.util.List;

public interface DaoPedidoProducto {

    /**
     * Permite listar pedidos
     * @return los pedidos
     */
    List<DtoPedidoProducto> listar(String referencia);
}
