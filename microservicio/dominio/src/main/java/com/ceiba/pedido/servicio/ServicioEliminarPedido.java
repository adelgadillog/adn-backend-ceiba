package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;

public class ServicioEliminarPedido {

    private final RepositorioPedido repositorioPedido;
    private static final String REFERENCIA_NO_EXISTE_EN_EL_SISTEMA = "Para la referencia no existe pedido registrado en el sistema";

    public ServicioEliminarPedido(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public void ejecutar(String referencia) {
        validarExistenciaPorReferencia(referencia);
        this.repositorioPedido.eliminar(referencia);
    }

    private void validarExistenciaPorReferencia(String referencia) {
        boolean existe = this.repositorioPedido.existe(referencia);
        if(!existe) {
            throw new ExcepcionDuplicidad(REFERENCIA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
