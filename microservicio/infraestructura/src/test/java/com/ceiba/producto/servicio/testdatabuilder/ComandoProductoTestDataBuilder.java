package com.ceiba.producto.servicio.testdatabuilder;

import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.producto.comando.ComandoProducto;

import java.time.LocalDateTime;

public class ComandoProductoTestDataBuilder {

    private Long id;
    private String referencia;
    private Long estado;
    private Long usuario_id;
    private Double total;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_aprobacion;
    private LocalDateTime fecha_entrega;

    public ComandoProductoTestDataBuilder() {
        referencia = "00001";
        estado = 2L;
        usuario_id = 17L;
        total = 1000D;
        fecha_aprobacion = LocalDateTime.now();
        fecha_entrega = LocalDateTime.now();
    }

   

    public ComandoProducto build() {
        return new ComandoProducto( id, referencia,  estado,  usuario_id, total, fecha_creacion, fecha_aprobacion, fecha_entrega);
    }
}
