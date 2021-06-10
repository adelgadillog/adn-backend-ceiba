package com.ceiba.producto.puerto.repositorio;

import com.ceiba.producto.modelo.entidad.Producto;

import java.util.List;

public interface RepositorioProducto {
    /**
     * Permite crear un producto
     * @param producto
     * @return el id generado
     */
    Long crear(Producto producto);



    /**
     * Permite validar si existe un producto con un id
     * @param id
     * @return si existe o no
     */
    boolean existe(Long id);


    /**
     * Permite obtener la lista de productos
     * @return lista producutos
     */
    List<Producto> listar();


}
