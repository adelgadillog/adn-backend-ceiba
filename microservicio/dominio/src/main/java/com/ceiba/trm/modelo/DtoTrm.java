package com.ceiba.trm.modelo;

import lombok.Getter;

@Getter
public class DtoTrm {

    private String valor;
    private String mensaje;

    public DtoTrm(String valor, String mensaje) {
        this.valor = valor;
        this.mensaje = mensaje;
    }
}
