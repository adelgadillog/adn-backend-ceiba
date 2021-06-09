package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioActualizarPedido {

    private static final String REFERENCIA_NO_EXISTE_EN_EL_SISTEMA = "Para la referencia no existe  pedido registgrado en el sistema";

    private final RepositorioPedido repositorioPedido;

    public ServicioActualizarPedido(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public void ejecutar(Pedido pedido) {
        validarExistencia(pedido);
        this.repositorioPedido.actualizar(pedido);
    }

    private void validarExistencia(Pedido pedido) {
        boolean existe = this.repositorioPedido.existe(pedido.getReferencia());
        if(!existe) {
            throw new ExcepcionDuplicidad(REFERENCIA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
