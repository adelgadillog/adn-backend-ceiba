package com.ceiba;

public class ComandoRespuesta<T> {

    private T id;

    public ComandoRespuesta(T id) {
        this.id = id;
    }

    public T getId() {
        return id;
    }
}
