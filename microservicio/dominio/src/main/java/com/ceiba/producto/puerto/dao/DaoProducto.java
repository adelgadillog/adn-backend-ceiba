package com.ceiba.producto.puerto.dao;

import com.ceiba.pedido.modelo.dto.DtoPedido;
import com.ceiba.producto.modelo.dto.DtoProducto;

import java.util.List;

public interface DaoProducto {

    /**
     * Permite listar pedidos
     * @return los pedidos
     */
    List<DtoProducto> listar();
}
