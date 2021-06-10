package com.ceiba.pedido.servicio.testdatabuilder;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido_producto.modelo.dto.DtoPedidoProducto;
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

    public PedidoTestDataBuilder() {
        referencia = "00001";
        estado = 1L;
        usuarioId = 17L;
        total = 1000d;
        fechaCreacion = LocalDateTime.now();
    }



    public PedidoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Pedido build() {
        return new Pedido( id, referencia,  estado, usuarioId, total, fechaCreacion, fechaAprobacion, fechaEntrega);
    }

    public PedidoTestDataBuilder conFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
        return this;
    }

    public List<DtoPedidoProducto> listarPedidoProducto(){
        List<DtoPedidoProducto> lista = new ArrayList<>();
        lista.add(new DtoPedidoProducto("00001","Auriculares",2L,100000D,1L));
        lista.add(new DtoPedidoProducto("00001","Celular",1L,9000000D,2L));
        return lista;
    }

    public List<DtoProducto> listarProducto(){
        List<DtoProducto> lista = new ArrayList<>();
        lista.add(new DtoProducto(1L,"Auriculares",1L,100000D));
        lista.add(new DtoProducto(1L,"Celular",2L,9000000D));
        return lista;
    }
}
