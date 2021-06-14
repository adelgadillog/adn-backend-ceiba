package com.ceiba.pedido.modelo.entidad;


import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.detalle.modelo.dto.DtoDetallePedido;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static com.ceiba.dominio.ValidadorArgumento.validarObligatorio;

@Getter
@Setter
public class Pedido {

    private static final String EL_PEDIDO_DEBE_TENER_UNA_REFERENCIA = "El pedido debe tener una referencia";
    private static final String EL_PEDIDO_DEBE_TENER_UN_USUARIO_ASOCIADO = "El pedido debe tener un usuario asociado";
    private static final String EL_PEDIDO_DEBE_TENER_UNA_FECHA_CREACION_ASOCIADA = "El pedido debe tener una fecha de creacion asociada";
    private static final String EL_PRODUCTO_NO_CUENTA_CON_UNIDADES_DISPONIBLES = "No existen unidades disponibles para el producto: ";
    private static final int FECHA_CREACION_TRES = 3;
    private static final double DESCUENTO_FECHA_CREACION_TRES = 0.5D;
    private static final int FECHA_CREACION_QUINCE = 15;
    private static final double DESCUENTO_FECHA_CREACION_QUINCE = 0.7D;
    private static final int DIAS_HABILES_ENTREGA = 15;
    private static final String EL_PEDIDO_DEBE_TENER_UN_DETALLE = "El pedido debe tener un detalle asociado" ;

    private enum Estado {
        PENDIENTE(1L),
        APROBADO(2L);

        private final Long value;
        Estado(final Long newValue) {
            value = newValue;
        }
    }

    private Long id;
    private String referencia;
    private Long estado;
    private Long usuarioId;
    private Double total;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaAprobacion;
    private LocalDateTime fechaEntrega;

    private List<DtoDetallePedido> detallePedido;

    public Pedido(String referencia, Long estado, Long usuarioId, Double total, LocalDateTime fechaCreacion,List<DtoDetallePedido> detallePedido) {

        validarObligatorio(referencia,EL_PEDIDO_DEBE_TENER_UNA_REFERENCIA);
        validarObligatorio(usuarioId,EL_PEDIDO_DEBE_TENER_UN_USUARIO_ASOCIADO);
        validarObligatorio(fechaCreacion, EL_PEDIDO_DEBE_TENER_UNA_FECHA_CREACION_ASOCIADA);
        this.referencia = referencia;
        this.estado = estado;
        this.usuarioId = usuarioId;
        this.fechaCreacion = fechaCreacion;
        this.total = total;
        this.detallePedido = Collections.unmodifiableList(detallePedido);
        if(Estado.APROBADO.value.equals(this.estado)){
            validarObligatorio(detallePedido,EL_PEDIDO_DEBE_TENER_UN_DETALLE);
            validarCantidadDisponible();
            calcularTotal();
            calcularFechaEntrega();
            this.fechaAprobacion = LocalDateTime.now();
        }



    }

    private Double calcularTotal(){

        if(this.fechaCreacion.getDayOfMonth() <= FECHA_CREACION_TRES){
            this.total = this.total * DESCUENTO_FECHA_CREACION_TRES;
        } else if(this.fechaCreacion.getDayOfMonth() == FECHA_CREACION_QUINCE){
            total = total * DESCUENTO_FECHA_CREACION_QUINCE;
        }
        return total;
    }

    private void calcularFechaEntrega() {

        int addedDays = 0;
        while (addedDays < DIAS_HABILES_ENTREGA) {
            this.fechaCreacion = this.fechaCreacion.plusDays(1);
            if (!(this.fechaCreacion.getDayOfWeek() == DayOfWeek.SATURDAY || this.fechaCreacion.getDayOfWeek() == DayOfWeek.SUNDAY)) {
                ++addedDays;
            }
        }
    }

    private void validarCantidadDisponible() {

        this.detallePedido.forEach(p-> {
            if(p.getCantidadPedido() > p.getCantidadDisponible())  throw new ExcepcionDuplicidad(EL_PRODUCTO_NO_CUENTA_CON_UNIDADES_DISPONIBLES + p.getNombreProducto() );
        });
    }

}
