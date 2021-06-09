package com.ceiba.pedido.comando.manejador;

import com.ceiba.manejador.ManejadorComando;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.servicio.ServicioAprobarPedido;
import org.springframework.stereotype.Component;

import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.comando.fabrica.FabricaPedido;

@Component
public class ManejadorActualizarPedido implements ManejadorComando<ComandoPedido> {

    private final FabricaPedido fabricaPedido;
    private final ServicioAprobarPedido servicioAprobarPedido;

    public ManejadorActualizarPedido(FabricaPedido fabricaPedido, ServicioAprobarPedido servicioAprobarPedido) {
        this.fabricaPedido = fabricaPedido;
        this.servicioAprobarPedido = servicioAprobarPedido;
    }

    public void ejecutar(ComandoPedido comandoPedido) {
        Pedido pedido = this.fabricaPedido.crear(comandoPedido);
        this.servicioAprobarPedido.ejecutar(pedido);
    }
}
