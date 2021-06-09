package com.ceiba.pedido.adaptador.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ceiba.infraestructura.jdbc.MapperResult;
import com.ceiba.pedido.modelo.dto.DtoPedido;
import org.springframework.jdbc.core.RowMapper;

public class MapeoPedido implements RowMapper<DtoPedido>, MapperResult {

    @Override
    public DtoPedido mapRow(ResultSet resultSet, int rowNum) throws SQLException {

        Long id = resultSet.getLong("id");
        String referencia = resultSet.getString("referencia");
        Long estado = resultSet.getLong("estado");
        Long usuario_id = resultSet.getLong("usuario_id");
        Double total = resultSet.getDouble("total");
        LocalDateTime fecha_creacion = extraerLocalDateTime(resultSet, "fecha_creacion");
        LocalDateTime fecha_aprobacion = extraerLocalDateTime(resultSet, "fecha_aprobacion");
        LocalDateTime fecha_entrega = extraerLocalDateTime(resultSet, "fecha_entrega");

        return new DtoPedido(id,referencia,estado,usuario_id,total,fecha_creacion,fecha_aprobacion,fecha_entrega);
    }

}
