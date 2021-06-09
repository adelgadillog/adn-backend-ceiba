package com.ceiba.producto.puerto.repositorio;

import com.ceiba.producto.modelo.entidad.Producto;

public interface RepositorioProducto {
    /**
     * Permite crear un producto
     * @param producto
     * @return el id generado
     */
    Long crear(Producto producto);

    /**
     * Permite actualizar un producto
     * @param producto
     */
    void actualizar(Producto producto);

   

    /**
     * Permite validar si existe un producto con un id
     * @param id
     * @return si existe o no
     */
    boolean existe(Long id);



}
