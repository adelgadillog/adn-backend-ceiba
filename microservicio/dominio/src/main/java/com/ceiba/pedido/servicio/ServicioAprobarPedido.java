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
import java.util.HashMap;
import java.util.List;

public class ServicioAprobarPedido {

    private static final String REFERENCIA_NO_EXISTE_EN_EL_SISTEMA = "Para la referencia no existe pedido registrado en el sistema";
    private static final String EL_PRODUCTO_NO_CUENTA_CON_UNIDADES_DISPONIBLES = "El producto no cuenta con unidades disponibles";

    private final RepositorioPedido repositorioPedido;
    private final DaoProducto daoProducto;
    private final DaoPedidoProducto daoPedidoProducto;
    public ServicioAprobarPedido(RepositorioPedido repositorioPedido, DaoPedidoProducto daoPedidoProducto,DaoProducto daoProducto) {
        this.repositorioPedido = repositorioPedido;
        this.daoProducto = daoProducto;
        this.daoPedidoProducto = daoPedidoProducto;
    }

    public void ejecutar(Pedido pedido) {
        validarExistencia(pedido);
        List<DtoPedidoProducto> listaProductoDto = daoPedidoProducto.listar(pedido.getReferencia());
        List<DtoProducto> listaProducto = daoProducto.listar();
        validarCantidadDisponible(listaProductoDto,listaProducto);
        pedido.setTotal(calcularTotal(listaProductoDto,pedido.getFecha_creacion().getDayOfMonth()));
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

    private void validarCantidadDisponible(List<DtoPedidoProducto> listaProductoDto, List<DtoProducto> listaProducto) {
        HashMap<Long,Long> map = new HashMap<>();
        listaProductoDto.stream().forEach((p) ->
                {
                    if (!map.containsKey(p.getIdProducto())) map.put(p.getIdProducto(), p.getCantidad());
                }
        );
        listaProducto.stream().forEach((p) ->
                {
                    if(map.containsKey(p.getId()) &&  map.get(p.getId()) > p.getCantidadDisponible()) {
                        throw new ExcepcionDuplicidad(EL_PRODUCTO_NO_CUENTA_CON_UNIDADES_DISPONIBLES);
                    }
                }
        );
    }
}
