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
        Long usuarioId = resultSet.getLong("usuario_id");
        Double total = resultSet.getDouble("total");
        LocalDateTime fechaCreacion = extraerLocalDateTime(resultSet, "fecha_creacion");
        LocalDateTime fechaAprobacion = extraerLocalDateTime(resultSet, "fecha_aprobacion");
        LocalDateTime fechaEntrega = extraerLocalDateTime(resultSet, "fecha_entrega");

        return new DtoPedido(id,referencia,estado,usuarioId,total,fechaCreacion,fechaAprobacion,fechaEntrega);
    }

}
