package com.ceiba.pedido.controlador;

import java.util.List;

import com.ceiba.pedido.consulta.ManejadorListarPedidos;
import com.ceiba.pedido.detalle.modelo.dto.DtoDetallePedido;
import org.springframework.web.bind.annotation.*;
import com.ceiba.pedido.modelo.dto.DtoPedido;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedidos")
@Api(tags={"Controlador consulta pedidos"})
@CrossOrigin("*")
public class ConsultaControladorPedido {

    private final ManejadorListarPedidos manejadorListarPedidos;

    public ConsultaControladorPedido(ManejadorListarPedidos manejadorListarPedidos) {
        this.manejadorListarPedidos = manejadorListarPedidos;
    }

    @GetMapping
    @ApiOperation("Listar Pedidos")
    public List<DtoPedido> listar() {
        return this.manejadorListarPedidos.ejecutar();
    }

    @GetMapping(value="/detalle/{referencia}")
    @ApiOperation("consultar detalle Pedido")
    public List<DtoDetallePedido> consultar(@PathVariable String referencia){
        return this.manejadorListarPedidos.consultar(referencia);
    }

}
