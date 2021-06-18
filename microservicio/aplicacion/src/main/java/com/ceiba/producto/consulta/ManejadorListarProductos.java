package com.ceiba.producto.consulta;

import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ManejadorListarProductos {

    private final DaoProducto daoProducto;

    public ManejadorListarProductos(DaoProducto daoProducto){
        this.daoProducto = daoProducto;
    }

    public List<Producto> ejecutar(){
        return this.daoProducto.listar();
    }
}
