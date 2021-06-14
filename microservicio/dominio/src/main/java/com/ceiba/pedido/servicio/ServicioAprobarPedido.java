package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.detalle.modelo.dto.DtoDetallePedido;
import com.ceiba.pedido.detalle.puerto.dao.DaoDetallePedido;
import java.util.List;

public class ServicioAprobarPedido {
    private enum Estado {
        PENDIENTE(1L),
        APROBADO(2L);

        private final Long value;
        Estado(final Long newValue) {
            value = newValue;
        }
    }

    private static final String REFERENCIA_NO_EXISTE_EN_EL_SISTEMA = "Para la referencia no existe pedido registrado en el sistema";
    private final RepositorioPedido repositorioPedido;
    private final DaoDetallePedido daoDetallePedido;

    public ServicioAprobarPedido(RepositorioPedido repositorioPedido, DaoDetallePedido daoDetallePedido) {
        this.repositorioPedido = repositorioPedido;
        this.daoDetallePedido = daoDetallePedido;
    }

    public void ejecutar(Pedido pedido) {
        validarExistencia(pedido);
        List<DtoDetallePedido> detallePedido = daoDetallePedido.listar(pedido.getReferencia());
        Pedido pedidoAprobado = new Pedido(pedido.getReferencia(),Estado.APROBADO.value, pedido.getUsuarioId(),
                totalPedido(pedido.getReferencia()),pedido.getFechaCreacion(),detallePedido);
        this.repositorioPedido.aprobar(pedidoAprobado);
    }


    private Double totalPedido(String referencia){
        return repositorioPedido.total(referencia);
    }

    private void validarExistencia(Pedido pedido) {
        boolean existe = this.repositorioPedido.existe(pedido.getReferencia());
        if(!existe) {
            throw new ExcepcionDuplicidad(REFERENCIA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
