package com.ceiba.producto.puerto.dao;

import com.ceiba.producto.modelo.dto.DtoProducto;

import java.util.List;

public interface DaoProducto {

    /**
     * Permite listar productos
     * @return los productos
     */
    List<DtoProducto> listar();
}
