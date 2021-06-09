package com.ceiba.pedido.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.pedido.servicio.ServicioEliminarPedido;
import org.springframework.stereotype.Component;


@Component
public class ManejadorEliminarPedido implements ManejadorComando<String> {

    private final ServicioEliminarPedido servicioEliminarPedido;

    public ManejadorEliminarPedido(ServicioEliminarPedido servicioEliminarPedido) {
        this.servicioEliminarPedido = servicioEliminarPedido;
    }

    public void ejecutar(String referencia) {
        this.servicioEliminarPedido.ejecutar(referencia);
    }
}
