package com.ceiba.producto.puerto.dao;

import com.ceiba.producto.modelo.entidad.Producto;

import java.util.List;

public interface DaoProducto {

    /**
     * Permite listar productos
     * @return los productos
     */
    List<Producto> listar();
}
