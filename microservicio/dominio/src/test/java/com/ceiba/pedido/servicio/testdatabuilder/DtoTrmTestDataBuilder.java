package com.ceiba.pedido.servicio.testdatabuilder;


import com.ceiba.trm.modelo.DtoTrm;

public class DtoTrmTestDataBuilder {

    private String mensaje;
    private String valor;

    public DtoTrmTestDataBuilder(String mensaje,String valor) {
        this.mensaje = mensaje;
        this.valor = valor;
    }


    public DtoTrm build() {
        return new DtoTrm(valor, mensaje);
    }

}
