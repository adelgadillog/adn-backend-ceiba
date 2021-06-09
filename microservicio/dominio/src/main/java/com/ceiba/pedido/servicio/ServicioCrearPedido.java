package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;


public class ServicioCrearPedido {

    private static final String EL_PEDIDO_YA_EXISTE_EN_EL_SISTEMA = "El pedido ya existe en el sistema";

    private final RepositorioPedido repositorioPedido;

    public ServicioCrearPedido(RepositorioPedido repositorioPedido) {
        this.repositorioPedido = repositorioPedido;
    }

    public Long ejecutar(Pedido pedido) {
        validarExistenciaPrevia(pedido);
        return this.repositorioPedido.crear(pedido);
    }

    private void validarExistenciaPrevia(Pedido pedido) {
        boolean existe = this.repositorioPedido.existe(pedido.getReferencia());
        if(existe) {
            throw new ExcepcionDuplicidad(EL_PEDIDO_YA_EXISTE_EN_EL_SISTEMA);
        }
    }
}
