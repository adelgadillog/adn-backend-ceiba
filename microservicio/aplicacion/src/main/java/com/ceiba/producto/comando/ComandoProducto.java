package com.ceiba.producto.comando;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoProducto {

    private Long id;
    private String referencia;
    private Long estado;
    private Long usuario_id;
    private Double total;
    private LocalDateTime fecha_creacion;
    private LocalDateTime fecha_aprobacion;
    private LocalDateTime fecha_entrega;
}
