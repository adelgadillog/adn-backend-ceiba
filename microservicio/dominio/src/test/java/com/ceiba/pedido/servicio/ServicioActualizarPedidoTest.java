package com.ceiba.pedido.servicio;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioActualizarPedidoTest {

    @Test
    public void validarUsuarioExistenciaPreviaTest() {
        // arrange
        Pedido pedido = new PedidoTestDataBuilder().conId(1L).build();
        RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        ServicioActualizarPedido servicioActualizarPedido = new ServicioActualizarPedido(repositorioPedido);
        // act - assert
        BasePrueba.assertThrows(() -> servicioActualizarPedido.ejecutar(pedido), ExcepcionDuplicidad.class,"Para la referencia no existe  pedido registgrado en el sistema");
    }
}
