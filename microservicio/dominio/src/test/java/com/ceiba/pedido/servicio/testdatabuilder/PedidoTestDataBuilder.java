package com.ceiba.pedido.servicio.testdatabuilder;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.detalle.modelo.dto.DtoDetallePedido;
import com.ceiba.producto.modelo.dto.DtoProducto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class PedidoTestDataBuilder {

    private Long id;
    private String referencia;
    private Long estado;
    private Long usuarioId;
    private Double total;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAprobacion;
    private LocalDateTime fechaEntrega;
    private List<DtoDetallePedido> detallePedido;

    public PedidoTestDataBuilder() {
        referencia = "00001";
        estado = 1L;
        usuarioId = 17L;
        fechaCreacion = LocalDateTime.now();

    }



    public PedidoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Pedido build() {
        return new Pedido(referencia,  estado, usuarioId, total, fechaCreacion,detallePedido);
    }

    public PedidoTestDataBuilder conFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }



    public List<DtoDetallePedido> listarDetallePedido(){
        List<DtoDetallePedido> lista = new ArrayList<>();
        lista.add(new DtoDetallePedido(1L,"Auriculares", 2L, 3L,100000D ));
        lista.add(new DtoDetallePedido(2L,"Celular", 2L, 3L,9000000D ));
        return lista;
    }

    public List<DtoDetallePedido> listarPedidoProductoNoExisteCantidad(){
        List<DtoDetallePedido> lista = new ArrayList<>();
        lista.add(new DtoDetallePedido(1L,"Auriculares", 5L, 3L,500000D ));
        lista.add(new DtoDetallePedido(2L,"Celular", 2L, 3L,9000000D ));
        return lista;
    }




}
