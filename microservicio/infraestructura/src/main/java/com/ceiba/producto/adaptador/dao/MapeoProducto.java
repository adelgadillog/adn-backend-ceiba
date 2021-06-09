package com.ceiba.producto.adaptador.dao;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido.modelo.dto.DtoPedido;
import com.ceiba.producto.modelo.dto.DtoProducto;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

public class MapeoProducto implements RowMapper<DtoProducto>, MapperResult {

    @Override
    public DtoProducto mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String referencia = resultSet.getString("referencia");
        Long estado = resultSet.getLong("estado");
        Long usuario_id = resultSet.getLong("usuario_id");
        Double total = resultSet.getDouble("total");
        LocalDateTime fecha_creacion = extraerLocalDateTime(resultSet, "fecha_creacion");
        LocalDateTime fecha_aprobacion = extraerLocalDateTime(resultSet, "fecha_aprobacion");
        LocalDateTime fecha_entrega = extraerLocalDateTime(resultSet, "fecha_entrega");

        return new DtoProducto(id,referencia,estado,usuario_id,total,fecha_creacion,fecha_aprobacion,fecha_entrega);
    }

}
