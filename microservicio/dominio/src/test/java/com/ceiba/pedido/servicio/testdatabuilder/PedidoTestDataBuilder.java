package com.ceiba.pedido.servicio.testdatabuilder;

import com.ceiba.pedido.modelo.entidad.Pedido;

import java.time.LocalDateTime;

public class PedidoTestDataBuilder {

    private Long id;
    private String referencia;
    private Long estado;
    private Long usuario_id;
    private Double total;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_aprobacion;
    private LocalDateTime fecha_entrega;

    public PedidoTestDataBuilder() {
        referencia = "00001";
        estado = 1L;
        usuario_id = 17L;
        total = 1000d;
        fecha_creacion = LocalDateTime.now();
    }



    public PedidoTestDataBuilder conId(Long id) {
        this.id = id;
        return this;
    }

    public Pedido build() {
        return new Pedido( id, referencia,  estado,  usuario_id, total, fecha_creacion, fecha_aprobacion, fecha_entrega);
    }

    public PedidoTestDataBuilder conFechaCreacion(LocalDateTime fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
        return this;
    }
}
