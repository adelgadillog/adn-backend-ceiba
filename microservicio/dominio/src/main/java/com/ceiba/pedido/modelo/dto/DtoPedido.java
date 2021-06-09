package com.ceiba.pedido.modelo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class DtoPedido {
    private Long id;
    private String referencia;
    private Long estado;
    private Long usuario_id;
    private Double total;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_aprobacion;
    private LocalDateTime fecha_entrega;


}
