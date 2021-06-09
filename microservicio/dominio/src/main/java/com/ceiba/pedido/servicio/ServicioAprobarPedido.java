package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido_producto.modelo.dto.DtoPedidoProducto;
import com.ceiba.pedido_producto.puerto.dao.DaoPedidoProducto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class ServicioAprobarPedido {

    private static final String REFERENCIA_NO_EXISTE_EN_EL_SISTEMA = "Para la referencia no existe pedido registrado en el sistema";

    private final RepositorioPedido repositorioPedido;
    private final DaoPedidoProducto daoPedidoProducto;
    public ServicioAprobarPedido(RepositorioPedido repositorioPedido, DaoPedidoProducto daoPedidoProducto) {
        this.repositorioPedido = repositorioPedido;
        this.daoPedidoProducto = daoPedidoProducto;
    }

    public void ejecutar(Pedido pedido) {
        validarExistencia(pedido);
        List<DtoPedidoProducto> listaProducto = daoPedidoProducto.listar(pedido.getReferencia());
        pedido.setTotal(calcularTotal(listaProducto,pedido.getFecha_creacion().getDayOfMonth()));
        pedido.setFecha_aprobacion(LocalDateTime.now());
        pedido.setFecha_entrega(calcularFechaEntrega(pedido.getFecha_creacion()));

        this.repositorioPedido.aprobar(pedido);
    }

    private void validarExistencia(Pedido pedido) {
        boolean existe = this.repositorioPedido.existe(pedido.getReferencia());
        if(!existe) {
            throw new ExcepcionDuplicidad(REFERENCIA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private Double calcularTotal(List<DtoPedidoProducto> listaProducto, int fechaCreacion){
        double total = listaProducto.stream().mapToDouble(DtoPedidoProducto::getPrecio).sum();
        if(fechaCreacion <= 3) total = total * 0.5;
        if(fechaCreacion == 15) total = total * 0.7;

        return total;
    }

    private LocalDateTime calcularFechaEntrega(LocalDateTime date) {
        LocalDateTime result = date;
        int addedDays = 0;
        while (addedDays < 15) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
}
