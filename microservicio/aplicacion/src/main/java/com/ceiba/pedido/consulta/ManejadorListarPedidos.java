package com.ceiba.pedido.consulta;

import java.util.List;

import com.ceiba.pedido.detalle.modelo.dto.DtoDetallePedido;
import com.ceiba.pedido.detalle.puerto.dao.DaoDetallePedido;
import com.ceiba.pedido.puerto.dao.DaoPedido;
import org.springframework.stereotype.Component;
import com.ceiba.pedido.modelo.dto.DtoPedido;

@Component
public class ManejadorListarPedidos {

    private final DaoPedido daoPedido;
    private final DaoDetallePedido daoDetallePedido;

    public ManejadorListarPedidos(DaoPedido daoPedido,DaoDetallePedido daoDetallePedido){
        this.daoPedido = daoPedido;
        this.daoDetallePedido = daoDetallePedido;
    }

    public List<DtoPedido> ejecutar(){
        return this.daoPedido.listar();
    }
    public List<DtoDetallePedido> consultar(String referencia){
        return this.daoDetallePedido.listar(referencia);
    }
}
