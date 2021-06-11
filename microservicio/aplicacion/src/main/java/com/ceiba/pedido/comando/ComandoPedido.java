package com.ceiba.pedido.comando;

import java.time.LocalDateTime;
import java.util.List;

import com.ceiba.pedido_producto.modelo.dto.DtoDetallePedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ComandoPedido {

    private String referencia;
    private Long estado;
    private Long usuarioId;
    private Double total;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAprobacion;
    private LocalDateTime fechaEntrega;
    private List<DtoDetallePedido> detallePedido;
}
