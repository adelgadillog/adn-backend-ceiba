package com.ceiba.pedido.servicio.testdatabuilder;

import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.producto.modelo.entidad.Producto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComandoPedidoTestDataBuilder {

    private Long id;
    private String referencia;
    private Long estado;
    private Long usuario_id;
    private Double total;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_aprobacion;
    private LocalDateTime fecha_entrega;

    public ComandoPedidoTestDataBuilder() {
        referencia = "00001";
        estado = 2L;
        usuario_id = 17L;
        total = 1000D;
        fecha_creacion  = LocalDateTime.now();

    }



    public ComandoPedido build() {
        return new ComandoPedido( id, referencia,  estado,  usuario_id, total, fecha_creacion, fecha_aprobacion, fecha_entrega);
    }
}