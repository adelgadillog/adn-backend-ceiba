package com.ceiba.pedido_producto.dao;

import com.ceiba.infraestructura.jdbc.CustomNamedParameterJdbcTemplate;
import com.ceiba.infraestructura.jdbc.sqlstatement.SqlStatement;
import com.ceiba.pedido_producto.modelo.dto.DtoPedidoProducto;
import com.ceiba.pedido_producto.puerto.dao.DaoPedidoProducto;
import com.ceiba.producto.puerto.dao.DaoProducto;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DaoPedidoProductoMysql implements DaoPedidoProducto {

    private final CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate;

    @SqlStatement(namespace="producto", value="listaProductosPedido")
    private static String sqlListaProductosPedido;

    public DaoPedidoProductoMysql(CustomNamedParameterJdbcTemplate customNamedParameterJdbcTemplate) {
        this.customNamedParameterJdbcTemplate = customNamedParameterJdbcTemplate;
    }

    @Override
    public List<DtoPedidoProducto> listar(String referenciaPedido) {
        MapSqlParameterSource paramSource = new MapSqlParameterSource();
        paramSource.addValue("referenciaPedido", referenciaPedido);
        return this.customNamedParameterJdbcTemplate.getNamedParameterJdbcTemplate().query(sqlListaProductosPedido,paramSource, new MapeoPedidoProducto());
    }
}
