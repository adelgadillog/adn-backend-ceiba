package com.ceiba.producto.servicio;

import com.ceiba.BasePrueba;
import com.ceiba.dominio.excepcion.ExcepcionDuplicidad;
import com.ceiba.producto.modelo.entidad.Producto;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;
import com.ceiba.producto.servicio.testdatabuilder.ProductoTestDataBuilder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

@RunWith(JUnit4.class)
public class ServicioCrearProductoTest {

    private Producto producto;
    private RepositorioProducto repositorioProducto;
    private ServicioCrearProducto servicioCrearProducto;
    private final Long idProductoEsperado = 3L;

    @Before
    public void setUp() {
        producto = new ProductoTestDataBuilder().conId(3L).build();
        this.repositorioProducto = Mockito.mock(RepositorioProducto.class);
        this.servicioCrearProducto = new ServicioCrearProducto(repositorioProducto);
    }

    @Test
    public void ejecutarCrearProducto(){
        // arrange
        Mockito.when(repositorioProducto.crear(producto)).thenReturn(idProductoEsperado);
        Long idProductoCreado = servicioCrearProducto.ejecutar(producto);
        // act - assert
        assertEquals(idProductoEsperado,idProductoCreado);
    }

    @Test
    public void existeProductoRetornaExcepcion(){
        // arrange
        Mockito.when(repositorioProducto.existe(producto.getId())).thenReturn(true);
        this.servicioCrearProducto = new ServicioCrearProducto(repositorioProducto);
        // act - assert
        BasePrueba.assertThrows(() -> servicioCrearProducto.ejecutar(producto), ExcepcionDuplicidad.class,"El producto ya existe en el sistema");

    }
}