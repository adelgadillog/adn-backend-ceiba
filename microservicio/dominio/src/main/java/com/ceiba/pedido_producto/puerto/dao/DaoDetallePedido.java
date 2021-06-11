package com.ceiba.pedido_producto.puerto.dao;

import com.ceiba.pedido_producto.modelo.dto.DtoDetallePedido;
import java.util.List;

public interface DaoDetallePedido {

    /**
     * Permite listar detalle pedido
     * @return los detalle pedido
     */

    List<DtoDetallePedido> listar(String referencia);
}
