package com.ceiba.pedido.detalle.puerto.dao;

import com.ceiba.pedido.detalle.modelo.dto.DtoDetallePedido;

import java.util.List;

public interface DaoDetallePedido {

    /**
     * Permite listar detalle pedido
     * @return los detalle pedido
     */

    List<DtoDetallePedido> listar(String referencia);
}
