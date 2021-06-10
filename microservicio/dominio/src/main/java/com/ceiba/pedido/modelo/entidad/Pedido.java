package com.ceiba.pedido.modelo.entidad;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class Pedido {

    private static final String SE_DEBE_INGRESAR_LA_FECHA_CREACION = "Se debe ingresar la fecha de creación";

    private Long id;
    private String referencia;
    private Long estado;
    private Long usuario_id;
    private Double total;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_aprobacion;
    private LocalDateTime fecha_entrega;

    public Pedido(Long id, String referencia, Long estado, Long usuario_id, Double total, LocalDateTime fecha_creacion, LocalDateTime fecha_aprobacion, LocalDateTime fecha_entrega) {
        this.id = id;
        this.referencia = referencia;
        this.estado = estado;
        this.usuario_id = usuario_id;
        this.total = total;
        this.fecha_creacion = fecha_creacion;
        this.fecha_aprobacion = fecha_aprobacion;
        this.fecha_entrega = fecha_entrega;
    }
}
