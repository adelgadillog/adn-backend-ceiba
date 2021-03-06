package com.ceiba.pedido.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionCantidadNoDisponible;
import com.ceiba.dominio.excepcion.ExcepcionSinDatos;
import com.ceiba.pedido.modelo.entidad.Pedido;
import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.testdatabuilder.DtoTrmTestDataBuilder;
import com.ceiba.pedido.servicio.testdatabuilder.PedidoTestDataBuilder;
import com.ceiba.pedido.detalle.puerto.dao.DaoDetallePedido;
import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.trm.modelo.DtoTrm;
import com.ceiba.trm.puerto.dao.DaoTrm;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import javax.xml.rpc.ServiceException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;

@RunWith(MockitoJUnitRunner.class)
public class ServicioAprobarPedidoTest {

    private static final Double TOTAL_DIA_TRES_ESPERADO = 200D;
    private static final Double TOTAL_DIA_QUINCE_ESPERADO = 200D;
    private static final Double VALOR_TRM = 3500D;

    private Pedido pedido;
    private DaoDetallePedido daoDetallePedido;
    private DaoTrm daoTrm;
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
        this.daoTrm = Mockito.mock(DaoTrm.class);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido,daoTrm);

    }

    @Test
    public void ejecutarAprobar() throws ServiceException, RemoteException {
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarDetallePedido());
        Mockito.when(daoTrm.obtener()).thenReturn(new DtoTrmTestDataBuilder("Fecha: "+ new SimpleDateFormat("dd MM yyyy").format(new Date()),VALOR_TRM.toString()).build());
        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));

    }

    @Test
    public void ejecutarAprobarNoExistePedidoDebeRetornarExcepcion(){
        // arrange
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(false);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido,daoTrm);

        // act - assert
        BasePrueba.assertThrows(() -> servicioAprobarPedido.ejecutar(pedido), ExcepcionSinDatos.class,"Para la referencia no existe pedido registrado en el sistema");

    }



    @Test
    public void calcularTotalElDiaEsTres() throws ServiceException, RemoteException {
        // arrange
        this.pedido = new PedidoTestDataBuilder().conFechaCreacion(LocalDateTime.of(2021,6,2,5,30,12,122)).build();
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioPedido.total(Mockito.anyString())).thenReturn(200D);
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarDetallePedido());
        Mockito.when(daoTrm.obtener()).thenReturn(new DtoTrmTestDataBuilder("Fecha: "+ new SimpleDateFormat("dd MM yyyy").format(new Date()),VALOR_TRM.toString()).build());
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido,daoTrm);
        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));
        Mockito.verify(repositorioPedido).aprobar(pedidoArgumentCaptor.capture());
        Double total = pedidoArgumentCaptor.getValue().getTotal();
        BasePrueba.assertEquals((TOTAL_DIA_TRES_ESPERADO*VALOR_TRM)*0.50D,total);


    }

    @Test
    public void calcularTotalElDiaEsQuince() throws ServiceException, RemoteException {

        //arrange
        this.pedido = new PedidoTestDataBuilder().conFechaCreacion(LocalDateTime.of(2021,6,15,5,30,12,122)).build();
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        Mockito.when(repositorioPedido.total(Mockito.anyString())).thenReturn(200D);
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarDetallePedido());
        Mockito.when(daoTrm.obtener()).thenReturn(new DtoTrmTestDataBuilder("Fecha: "+ new SimpleDateFormat("dd MM yyyy").format(new Date()),VALOR_TRM.toString()).build());
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido,daoTrm);

        // act - assert
        BasePrueba.assertExecute(() -> servicioAprobarPedido.ejecutar(pedido));
        Mockito.verify(repositorioPedido).aprobar(pedidoArgumentCaptor.capture());
        Double total = pedidoArgumentCaptor.getValue().getTotal();
        BasePrueba.assertEquals((TOTAL_DIA_QUINCE_ESPERADO*VALOR_TRM)*0.70D,total);
    }

    @Test
    public void ejecutarAprobarNoExisteCantidadSuficiente() throws ServiceException, RemoteException {
        // arrange
        Mockito.when(daoTrm.obtener()).thenReturn(new DtoTrmTestDataBuilder("Fecha: "+ new SimpleDateFormat("dd MM yyyy").format(new Date()),VALOR_TRM.toString()).build());
        Mockito.when(repositorioPedido.existe(Mockito.anyString())).thenReturn(true);
        this.servicioAprobarPedido = new ServicioAprobarPedido(repositorioPedido, daoDetallePedido,daoTrm);
        Mockito.when(daoDetallePedido.listar(Mockito.anyString())).thenReturn(new PedidoTestDataBuilder().listarPedidoProductoNoExisteCantidad());

        // act - assert
        BasePrueba.assertThrows(() -> servicioAprobarPedido.ejecutar(pedido),
                ExcepcionCantidadNoDisponible.class,
                "No existen unidades disponibles para el producto: Auriculares");
    }

    @Test
    public void validarPedidoTest() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        // arrange
        Pedido pedido = Mockito.spy(new PedidoTestDataBuilder().buildCompleto());
        // act - assert
        BasePrueba.assertEquals(pedido.getId(),1L);
        BasePrueba.assertEquals(pedido.getReferencia(),"00001");
        BasePrueba.assertEquals(pedido.getEstado(),1L);
        BasePrueba.assertEquals(sdf.format(Date.from(pedido.getFechaCreacion().atZone(ZoneId.systemDefault()).toInstant())),sdf.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
        BasePrueba.assertEquals(sdf.format(Date.from(pedido.getFechaAprobacion().atZone(ZoneId.systemDefault()).toInstant())),sdf.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
        BasePrueba.assertEquals(sdf.format(Date.from(pedido.getFechaEntrega().atZone(ZoneId.systemDefault()).toInstant())),sdf.format(Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant())));
        BasePrueba.assertEquals(pedido.getUsuarioId(),17L);
        BasePrueba.assertEquals(pedido.getTotal(),900000D);
        BasePrueba.assertEquals(pedido.getDetallePedido(),new ArrayList<>());
    }

}