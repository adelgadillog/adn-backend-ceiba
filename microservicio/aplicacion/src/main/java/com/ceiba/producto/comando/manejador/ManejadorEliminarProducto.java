package com.ceiba.producto.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.pedido.servicio.ServicioEliminarPedido;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarProducto implements ManejadorComando<String> {

    private final ServicioEliminarPedido servicioEliminarPedido;

    public ManejadorEliminarProducto(ServicioEliminarPedido servicioEliminarPedido) {
        this.servicioEliminarPedido = servicioEliminarPedido;
    }

    public void ejecutar(String referencia) {
        this.servicioEliminarPedido.ejecutar(referencia);
    }
}
