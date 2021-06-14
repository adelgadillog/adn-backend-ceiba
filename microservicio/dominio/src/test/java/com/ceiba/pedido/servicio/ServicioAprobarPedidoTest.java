package com.ceiba.pedido.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;
import com.ceiba.pedido.detalle.puerto.dao.DaoDetallePedido;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.time.LocalDateTime;

@RunWith(MockitoJUnitRunner.class)
public class ServicioAprobarPedidoTest {

    private static final Double TOTAL_DIA_TRES_ESPERADO = 100000D;
    private static final Double TOTAL_DIA_QUINCE_ESPERADO = 140000D;

    private Pedido pedido;
    private DaoDetallePedido daoDetallePedido;
    private ServicioAprobarPedido servicioAprobarPedido;

    @Captor
    ArgumentCaptor<Pedido> pedidoArgumentCaptor;

    @Mock
    private RepositorioPedido repositorioPedido;

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
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarDetallePedido());
        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));

    }

    @Test
    public void ejecutarAprobarNoExistePedidoDebeRetornarExcepcion(){
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(false);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);

        // act - assert
        BasePrueba.assertThrows(() -> servicioAprobarPedido.ejecutar(pedido), ExcepcionDuplicidad.class,"Para la referencia no existe pedido registrado en el sistema");

    }

   

    @Test
    public void calcularTotalElDiaEsTres(){
        // arrange
        this.pedido = new PedidoTestDataBuilder().conFechaCreacion(LocalDateTime.of(2021,6,2,5,30,12,122)).build();
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioPedido.total(Mockito.anyString())).thenReturn(200000D);
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarDetallePedido());
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);

        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));
        Mockito.verify(repositorioPedido).aprobar(pedidoArgumentCaptor.capture());
        Double total = pedidoArgumentCaptor.getValue().getTotal();
        BasePrueba.assertEquals(TOTAL_DIA_TRES_ESPERADO,total);


    }

    @Test
    public void calcularTotalElDiaEsQuince(){

        //arrange
        this.pedido = new PedidoTestDataBuilder().conFechaCreacion(LocalDateTime.of(2021,6,15,5,30,12,122)).build();
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioPedido.total(Mockito.anyString())).thenReturn(200000D);
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarDetallePedido());
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);

        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));
        Mockito.verify(repositorioPedido).aprobar(pedidoArgumentCaptor.capture());
        Double total = pedidoArgumentCaptor.getValue().getTotal();
        BasePrueba.assertEquals(TOTAL_DIA_QUINCE_ESPERADO,total);
    }

    @Test
    public void ejecutarAprobarNoExisteCantidadSuficiente(){
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarPedidoProductoNoExisteCantidad());
        // act - assert
        BasePrueba.assertThrows(() -> servicioAprobarPedido.ejecutar(pedido), ExcepcionDuplicidad.class,"No existen unidades disponibles para el producto: Auriculares");
    }


}