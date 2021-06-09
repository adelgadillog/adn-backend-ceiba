package com.ceiba.pedido.puerto.repositorio;

import com.ceiba.pedido.modelo.entidad.Pedido;

public interface RepositorioPedido {
    /**
     * Permite crear un pedido
     * @param pedido
     * @return el id generado
     */
    Long crear(Pedido pedido);

    /**
     * Permite actualizar un pedido
     * @param pedido
     */
    void actualizar(Pedido pedido);

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
