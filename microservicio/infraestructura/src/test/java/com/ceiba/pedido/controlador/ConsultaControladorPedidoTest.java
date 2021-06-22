package com.ceiba.pedido.controlador;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ceiba.ApplicationMock;
import com.ceiba.pedido.comando.ComandoPedido;
import com.ceiba.pedido.servicio.testdatabuilder.ComandoPedidoTestDataBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ConsultaControladorPedido.class)
public class ConsultaControladorPedidoTest {

    @Autowired
    private MockMvc mocMvc;

    @Test
    public void listar() throws Exception {
        // arrange

        // act - assert
        mocMvc.perform(get("/pedidos")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].referencia", is("00001")));
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void consultarDetallePedido() throws Exception{
        // arrange
        String referencia= "00001";
        // act - assert
        mocMvc.perform(get("/pedidos/detalle/{referencia}",referencia)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombreProducto", is("Audifonos")))
                .andExpect(jsonPath("$[1].nombreProducto", is("Teclado")));
    }

}
