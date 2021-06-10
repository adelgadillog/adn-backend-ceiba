package com.ceiba.pedido_producto.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido_producto.modelo.dto.DtoPedidoProducto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoPedidoProducto implements RowMapper<DtoPedidoProducto>, MapperResult {

    @Override
    public DtoPedidoProducto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        String referencia = resultSet.getString("referenciaPedido");
        String nombre = resultSet.getString("nombre");
        Long cantidad = resultSet.getLong("cantidad");
        Double precio = resultSet.getDouble("precio");
        Long idProducto = resultSet.getLong("idProducto");

        return new DtoPedidoProducto(referencia,nombre,cantidad,precio,idProducto);
    }

}
