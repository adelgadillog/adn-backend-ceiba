package com.ceiba.producto.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.producto.comando.ComandoProducto;
import com.ceiba.producto.comando.manejador.ManejadorActualizarProducto;
import com.ceiba.producto.comando.manejador.ManejadorCrearProducto;
import com.ceiba.producto.comando.manejador.ManejadorEliminarProducto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@Api(tags = { "Controlador comando producto"})
public class ComandoControladorProducto {

    private final ManejadorCrearProducto manejadorCrearProducto;
	private final ManejadorEliminarProducto manejadorEliminarProducto;
	private final ManejadorActualizarProducto manejadorActualizarProducto;

    @Autowired
    public ComandoControladorProducto(ManejadorCrearProducto manejadorCrearProducto,
                                      ManejadorEliminarProducto manejadorEliminarProducto,
                                      ManejadorActualizarProducto manejadorActualizarProducto) {
        this.manejadorCrearProducto = manejadorCrearProducto;
		this.manejadorEliminarProducto = manejadorEliminarProducto;
		this.manejadorActualizarProducto = manejadorActualizarProducto;
    }

    @PostMapping
    @ApiOperation("Crear producto")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoProducto comandoProducto) {
        return manejadorCrearProducto.ejecutar(comandoProducto);
    }

}
