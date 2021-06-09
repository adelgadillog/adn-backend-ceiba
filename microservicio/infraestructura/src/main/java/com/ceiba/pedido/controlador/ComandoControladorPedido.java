package com.ceiba.pedido.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.comando.manejador.ManejadorActualizarPedido;
import com.ceiba.pedido.comando.manejador.ManejadorCrearPedido;
import com.ceiba.pedido.comando.manejador.ManejadorEliminarPedido;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/pedidos")
@Api(tags = { "Controlador comando pedido"})
public class ComandoControladorPedido {

    private final ManejadorCrearPedido manejadorCrearPedido;
	private final ManejadorEliminarPedido manejadorEliminarPedido;
	private final ManejadorActualizarPedido manejadorActualizarPedido;

    @Autowired
    public ComandoControladorPedido(ManejadorCrearPedido manejadorCrearPedido,
									ManejadorEliminarPedido manejadorEliminarPedido,
									ManejadorActualizarPedido manejadorActualizarPedido) {
        this.manejadorCrearPedido = manejadorCrearPedido;
		this.manejadorEliminarPedido = manejadorEliminarPedido;
		this.manejadorActualizarPedido = manejadorActualizarPedido;
    }

    @PostMapping
    @ApiOperation("Crear pedido")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoPedido comandoPedido) {
        return manejadorCrearPedido.ejecutar(comandoPedido);
    }

    @DeleteMapping(value="/{referencia}")
	@ApiOperation("Eliminar pedido")
	public void eliminar(@PathVariable String referencia) {
		manejadorEliminarPedido.ejecutar(referencia);
	}

	@PutMapping(value="/{referencia}")
	@ApiOperation("Actualizar pedido")
	public void actualizar(@RequestBody ComandoPedido comandoPedido, @PathVariable String referencia) {
		comandoPedido.setReferencia(referencia);
		manejadorActualizarPedido.ejecutar(comandoPedido);
	}
}
