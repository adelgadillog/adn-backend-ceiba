package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido_producto.modelo.dto.DtoPedidoProducto;
import com.ceiba.pedido_producto.puerto.dao.DaoPedidoProducto;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;

public class ServicioActualizarPedido {

    private static final String REFERENCIA_NO_EXISTE_EN_EL_SISTEMA = "Para la referencia no existe  pedido registrado en el sistema";

    private final RepositorioPedido repositorioPedido;
    private final DaoPedidoProducto daoPedidoProducto;
    public ServicioActualizarPedido(RepositorioPedido repositorioPedido,DaoPedidoProducto daoPedidoProducto) {
        this.repositorioPedido = repositorioPedido;
        this.daoPedidoProducto = daoPedidoProducto;
    }

    public void ejecutar(Pedido pedido) {
        validarExistencia(pedido);
        List<DtoPedidoProducto> listaProducto = daoPedidoProducto.listar(pedido.getReferencia());
        pedido.setTotal(calcularTotal(listaProducto,pedido.getFecha_creacion()));
        pedido.setFecha_aprobacion(LocalDateTime.now());
        pedido.setFecha_entrega(calcularFechaEntrega(pedido.getFecha_creacion(),15));

        this.repositorioPedido.actualizar(pedido);
    }

    private void validarExistencia(Pedido pedido) {
        boolean existe = this.repositorioPedido.existe(pedido.getReferencia());
        if(!existe) {
            throw new ExcepcionDuplicidad(REFERENCIA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private Double calcularTotal(List<DtoPedidoProducto> listaProducto, LocalDateTime fechaCreacion){
        Double total = listaProducto.stream().mapToDouble(p -> p.getPrecio()).sum();
        if(fechaCreacion.getDayOfMonth() <= 3) total = total * 0.5;
        if(fechaCreacion.getDayOfMonth() == 15) total = total * 0.7;

        return total;
    }

    private LocalDateTime calcularFechaEntrega(LocalDateTime date, int days) {
        LocalDateTime result = date;
        int addedDays = 0;
        while (addedDays < days) {
            result = result.plusDays(1);
            if (!(result.getDayOfWeek() == DayOfWeek.SATURDAY || result.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
        return result;
    }
}
