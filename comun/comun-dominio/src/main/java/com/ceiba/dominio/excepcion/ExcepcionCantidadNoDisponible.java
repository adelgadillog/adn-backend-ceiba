package com.ceiba.dominio.excepcion;

public class ExcepcionCantidadNoDisponible extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ExcepcionCantidadNoDisponible(String mensaje) {
        super(mensaje);
    }
}
