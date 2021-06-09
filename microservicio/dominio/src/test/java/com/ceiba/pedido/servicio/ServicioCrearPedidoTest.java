package com.ceiba.pedido.servicio;

import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.dominio.excepcion.ExcepcionLongitudValor;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;
import org.junit.Test;
import org.mockito.Mockito;

import com.ceiba.BasePrueba;

public class ServicioCrearPedidoTest {



    @Test
    public void validarPedidoExistenciaPreviaTest() {
        // arrange
        Pedido pedido = new PedidoTestDataBuilder().build();
        RepositorioPedido repositorioPedido = Mockito.mock(RepositorioPedido.class);
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        ServicioCrearPedido servicioCrearPedido = new ServicioCrearPedido(repositorioPedido);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearPedido.ejecutar(pedido), ExcepcionDuplicidad.class,"El usuario ya existe en el sistema");
    }
}
