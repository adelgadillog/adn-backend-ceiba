package com.ceiba.pedido_producto.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido_producto.modelo.dto.DtoDetallePedido;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MapeoDetallePedido implements RowMapper<DtoDetallePedido>, MapperResult {

    @Override
    public DtoDetallePedido mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        Long idProducto = resultSet.getLong("idProducto");
        String nombreProducto = resultSet.getString("nombreProducto");
        Long cantidadPedido = resultSet.getLong("cantidadPedido");
        Long cantidadDisponible = resultSet.getLong("cantidadDisponible");
        Double precio = resultSet.getDouble("precio");


        return new DtoDetallePedido(idProducto, nombreProducto,cantidadPedido ,cantidadDisponible, precio);
    }

}
