package com.ceiba.producto.controlador;

import com.ceiba.ComandoRespuesta;
import com.ceiba.producto.comando.ComandoProducto;
import com.ceiba.producto.comando.manejador.ManejadorCrearProducto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
@Api(tags = { "Controlador comando producto"})
@CrossOrigin("*")
public class ComandoControladorProducto {

    private final ManejadorCrearProducto manejadorCrearProducto;

    @Autowired
    public ComandoControladorProducto(ManejadorCrearProducto manejadorCrearProducto) {
        this.manejadorCrearProducto = manejadorCrearProducto;
    }

    @PostMapping
    @ApiOperation("Crear producto")
    public ComandoRespuesta<Long> crear(@RequestBody ComandoProducto comandoProducto) {
        return manejadorCrearProducto.ejecutar(comandoProducto);
    }

}
