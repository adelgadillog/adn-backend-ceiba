package com.ceiba.pedido.comando.fabrica;

import com.ceiba.pedido.modelo.entidad.Pedido;
import org.springframework.stereotype.Component;
import com.ceiba.pedido.comando.ComandoPedido;

@Component
public class FabricaPedido {

    public Pedido crear(ComandoPedido comandoPedido) {
        return new Pedido(comandoPedido.getId(), comandoPedido.getReferencia(), comandoPedido.getEstado(),
                comandoPedido.getUsuarioId(), comandoPedido.getTotal(), comandoPedido.getFechaCreacion(),
                comandoPedido.getFechaAprobacion(), comandoPedido.getFechaEntrega());
    }

}
