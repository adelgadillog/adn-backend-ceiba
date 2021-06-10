package com.ceiba.pedido.servicio;

import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido_producto.modelo.dto.DtoPedidoProducto;
import com.ceiba.pedido_producto.puerto.dao.DaoPedidoProducto;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

        validarCantidadDisponible(listaProductoDto);
        pedido.setTotal(calcularTotal(listaProductoDto,pedido.getFechaCreacion().getDayOfMonth()));
        pedido.setFechaAprobacion(LocalDateTime.now());
        pedido.setFechaEntrega(calcularFechaEntrega(pedido.getFechaCreacion()));

        this.repositorioPedido.aprobar(pedido);
    }


    private void validarExistencia(Pedido pedido) {
        boolean existe = this.repositorioPedido.existe(pedido.getReferencia());
        if(!existe) {
            throw new ExcepcionDuplicidad(REFERENCIA_NO_EXISTE_EN_EL_SISTEMA);
        }
    }

    private Double calcularTotal(List<DtoPedidoProducto> listaProducto, int fechaCreacion){
        double total = listaProducto.stream().mapToDouble(p->p.getPrecio()*p.getCantidad()).sum();
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

    private void validarCantidadDisponible(List<DtoPedidoProducto> listaProductoDto) {
        HashMap<Long,Long> map = new HashMap<>();
        List<Long> listaIdsProducto = listaProductoDto.stream().map(DtoPedidoProducto::getIdProducto).collect(Collectors.toList());
        List<DtoProducto> listaProducto = daoProducto.listar(listaIdsProducto);
        listaProductoDto.forEach(p ->
                {
                    if (!map.containsKey(p.getIdProducto())) map.put(p.getIdProducto(), p.getCantidad());
                }
        );
        listaProducto.forEach(p ->
                {
                    if(map.containsKey(p.getId()) &&  map.get(p.getId()) > p.getCantidadDisponible()) {
                        throw new ExcepcionDuplicidad(EL_PRODUCTO_NO_CUENTA_CON_UNIDADES_DISPONIBLES);
                    }
                }
        );
    }
}
