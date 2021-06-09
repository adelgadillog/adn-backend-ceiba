package com.ceiba.producto.adaptador.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.producto.modelo.dto.DtoProducto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoProductoMysql implements DaoProducto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="producto", value="listaProductosPedido")
    private static String sqlListaProductosPedido;

    public DaoProductoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoProducto> listar(String referencia) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("referenciaPedido", referencia);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListaProductosPedido,paramSource, new MapeoProducto());
    }
}
