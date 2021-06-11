package com.ceiba.pedido.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;
import com.ceiba.pedido_producto.puerto.dao.DaoDetallePedido;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class ServicioEliminarPedidoTest {
    private Pedido pedido;
    private RepositorioPedido repositorioPedido;
    private DaoDetallePedido daoDetallePedido;
    private ServicioEliminarPedido servicioEliminarPedido;

    @Before
    public void setUp(){
        this.pedido = new PedidoTestDataBuilder().conId(1L).build();
        this.repositorioPedido = Mockito.mock(RepositorioPedido.class);
        this.daoDetallePedido = Mockito.mock(DaoDetallePedido.class);
        this.servicioEliminarPedido = new ServicioEliminarPedido(repositorioPedido);
    }

    @Test
    public void ejecutarEliminar(){
        // act - assert
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        BasePrueba.assertExecute(() -> servicioEliminarPedido.ejecutar(pedido.getReferencia()));
    }

    @Test
    public void ejecutarEliminarConExcepcion(){
        // act - assert
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(false);
        BasePrueba.assertThrows(() -> servicioEliminarPedido.ejecutar(pedido.getReferencia()), ExcepcionDuplicidad.class,
                "Para la referencia no existe pedido registrado en el sistema");
    }
}