package com.ceiba.pedido.controlador;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes= ApplicationMock.class)
@WebMvcTest(ComandoControladorPedido.class)
public class ComandoControladorPedidoTest {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mocMvc;



    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void aprobar() throws Exception{
        // arrange
        String referencia= "00001";
        ComandoPedido pedido = new ComandoPedidoTestDataBuilder().build();

        // act - assert
        mocMvc.perform(put("/pedidos/{referencia}",referencia)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(pedido)))
                .andExpect(status().isOk());
    }


    @Test
    public void eliminar() throws Exception {
        // arrange
        String referencia= "00001";

        // act - assert
        mocMvc.perform(delete("/pedidos/{referencia}",referencia)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
