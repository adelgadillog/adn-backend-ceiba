package com.ceiba.producto.comando.fabrica;

import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.producto.comando.ComandoProducto;
import com.ceiba.producto.modelo.entidad.Producto;
import org.springframework.stereotype.Component;

@Component
public class FabricaProducto {

    public Producto crear(ComandoProducto ComandoProducto) {
        return new Producto(ComandoProducto.getId(), ComandoProducto.getReferencia(), ComandoProducto.getEstado(),
                ComandoProducto.getUsuario_id(), ComandoProducto.getTotal(), ComandoProducto.getFecha_creacion(),
                ComandoProducto.getFecha_aprobacion(), ComandoProducto.getFecha_entrega());
    }

}
