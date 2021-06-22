package com.ceiba.producto.controlador;

import com.ceiba.ApplicationMock;
import com.ceiba.producto.comando.ComandoProducto;
import com.ceiba.producto.servicio.testdatabuilder.ComandoProductoTestDataBuilder;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorProducto.class)
public class ComandoControladorProductoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;


    @Test
    public void crear() throws Exception{
        // arrange
        ComandoProducto producto = new ComandoProductoTestDataBuilder().build();

        // act - assert
        mocMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 3}"));
    }

    @Test
    public void crearCantidadCeroDebeDevolverError() throws Exception{
        // arrange
        ComandoProducto producto = new ComandoProductoTestDataBuilder().conCantidadDisponible(0L).build();

        // act - assert
        mocMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'mensaje': 'El producto debe tener cantidad disponible mayor que cero'}"));
    }

    @Test
    public void crearCantidadMenorQueCeroDebeDevolverError() throws Exception{
        // arrange
        ComandoProducto producto = new ComandoProductoTestDataBuilder().conCantidadDisponible(-1L).build();

        // act - assert
        mocMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'mensaje': 'El producto debe tener cantidad disponible mayor que cero'}"));
    }

    @Test
    public void crearPrecioCeroDebeDevolverError() throws Exception{
        // arrange
        ComandoProducto producto = new ComandoProductoTestDataBuilder().conPrecio(0D).build();

        // act - assert
        mocMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'mensaje': 'El producto debe tener un precio mayor que cero'}"));
    }

    @Test
    public void crearPrecioMenorQueCeroDebeDevolverError() throws Exception{
        // arrange
        ComandoProducto producto = new ComandoProductoTestDataBuilder().conPrecio(-1D).build();

        // act - assert
        mocMvc.perform(post("/productos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(producto)))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{'mensaje': 'El producto debe tener un precio mayor que cero'}"));
    }

}
