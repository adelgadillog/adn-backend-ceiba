package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.detalle.modelo.dto.DtoDetallePedido;
import com.ceiba.pedido.detalle.puerto.dao.DaoDetallePedido;
import com.ceiba.trm.modelo.DtoTrm;
import com.ceiba.trm.puerto.dao.DaoTrm;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
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
    private static final String TRM_NO_DISPONIBLE = "El servicio TRM no esta disponible";
    private final RepositorioPedido repositorioPedido;
    private final DaoDetallePedido daoDetallePedido;
    private final DaoTrm daoTrm;

    public ServicioAprobarPedido(RepositorioPedido repositorioPedido, DaoDetallePedido daoDetallePedido,DaoTrm daoTrm) {
        this.repositorioPedido = repositorioPedido;
        this.daoDetallePedido = daoDetallePedido;
        this.daoTrm = daoTrm;
    }

    public void ejecutar(Pedido pedido) {
        validarExistencia(pedido);
        List<DtoDetallePedido> detallePedido = daoDetallePedido.listar(pedido.getReferencia());
        Pedido pedidoAprobado = new Pedido(pedido.getReferencia(),Estado.APROBADO.value, pedido.getUsuarioId(),
                totalPedido(pedido.getReferencia()),pedido.getFechaCreacion(),detallePedido);
        this.repositorioPedido.aprobar(pedidoAprobado);
    }


    private Double totalPedido(String referencia) {
        DtoTrm trm;
        try{
            trm = this.daoTrm.obtener();
            return repositorioPedido.total(referencia) * new Double(trm.getValor());
        }catch (Exception e){
            throw new ExcepcionSinDatos(TRM_NO_DISPONIBLE);
        }
    }

    private void validarExistencia(Pedido pedido) {
        boolean existe = this.repositorioPedido.existe(pedido.getReferencia());
        if(!existe) {
            throw new ExcepcionSinDatos(REFERENCIA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }
}
