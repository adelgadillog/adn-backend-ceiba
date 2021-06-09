package com.ceiba.pedido.comando.fabrica;

import com.ceiba.pedido.modelo.entidad.Pedido;
import org.springframework.stereotype.Component;

import com.ceiba.pedido.comando.ComandoPedido;

@Component
public class FabricaPedido {

    public Pedido crear(ComandoPedido comandoPedido) {
        return new Pedido(comandoPedido.getId(), comandoPedido.getReferencia(), comandoPedido.getEstado(),
                comandoPedido.getUsuario_id(), comandoPedido.getTotal(), comandoPedido.getFecha_creacion(),
                comandoPedido.getFecha_aprobacion(), comandoPedido.getFecha_entrega());
    }

}
