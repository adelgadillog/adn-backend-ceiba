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

import java.time.LocalDateTime;

@RunWith(JUnit4.class)
public class ServicioAprobarPedidoTest {

    private Pedido pedido;
    private RepositorioPedido repositorioPedido;
    private DaoDetallePedido daoDetallePedido;
    private ServicioAprobarPedido servicioAprobarPedido;

    @Before
    public void setUp() {
        // arrange
        this.pedido = new PedidoTestDataBuilder().conId(1L).build();
        this.repositorioPedido = Mockito.mock(RepositorioPedido.class);
        this.daoDetallePedido = Mockito.mock(DaoDetallePedido.class);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);
    }

    @Test
    public void ejecutarAprobar(){
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarPedidoProductoCantidad());
        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));

    }

    @Test
    public void existenciaDebeRetornarExcepcion(){
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(false);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);

        // act - assert
        BasePrueba.assertThrows(() -> servicioAprobarPedido.ejecutar(pedido), ExcepcionDuplicidad.class,"Para la referencia no existe pedido registrado en el sistema");

    }

    @Test
    public void existenciaCuandoExistePedido(){
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);

        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));

    }

    @Test
    public void calcularTotalElDiaEsTres(){
        // arrange
        this.pedido = new PedidoTestDataBuilder().conFechaCreacion(LocalDateTime.of(2021,6,2,5,30,12,122)).build();
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        //.when(daoPedidoProducto.total(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarPedidoProducto());
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);

        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));
    }

    @Test
    public void calcularTotalElDiaEsQuince(){
        //arrange
        this.pedido = new PedidoTestDataBuilder().conFechaCreacion(LocalDateTime.of(2021,6,15,5,30,12,122)).build();
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
       // Mockito.when(daoPedidoProducto.total(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarPedidoProducto());
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);

        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));
    }

    @Test
    public void ejecutarAprobarNoExisteCantidadSuficiente(){
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);
        //Mockito.when(daoProducto.listar(Mockito.anyList())).thenReturn(new PedidoTestDataBuilder().listarProducto());
        //Mockito.when(daoPedidoProducto.total(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarPedidoProducto());
        // act - assert
        BasePrueba.assertThrows(() -> servicioAprobarPedido.ejecutar(pedido), ExcepcionDuplicidad.class,"El producto no cuenta con unidades disponibles");
    }

    @Test
    public void ejecutarAprobarProductoDuplicadoEnPedido(){
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);
       // Mockito.when(daoPedidoProducto.total(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarPedidoProductoRepedito());
        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));
    }
}