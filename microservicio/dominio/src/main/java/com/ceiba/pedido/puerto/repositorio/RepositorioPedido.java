package com.ceiba.pedido.puerto.repositorio;

import com.ceiba.pedido.modelo.entidad.Pedido;

public interface RepositorioPedido {

    /**
     * Permite actualizar un pedido
     * @param pedido
     */
    void aprobar(Pedido pedido);

    /**
     * Permite eliminar un pedido
     * @param referencia
     */
    void eliminar(String referencia);

    /**
     * Permite validar si existe un pedido con un referencia
     * @param referencia
     * @return si existe o no
     */
    boolean existe(String referencia);



}
