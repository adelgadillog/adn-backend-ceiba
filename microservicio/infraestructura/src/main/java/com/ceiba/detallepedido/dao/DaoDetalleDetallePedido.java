package com.ceiba.detallepedido.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pedido.detalle.modelo.dto.DtoDetallePedido;
import com.ceiba.pedido.detalle.puerto.dao.DaoDetallePedido;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoDetalleDetallePedido implements DaoDetallePedido {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="detallepedido", value="listar")
    private static String sqlDetallePedido;

    public DaoDetalleDetallePedido(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }


    @Override
    public List<DtoDetallePedido> listar(String referencia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("referencia", referencia);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate()
                .query(sqlDetallePedido, paramSource,new MapeoDetallePedido());
    }
}
