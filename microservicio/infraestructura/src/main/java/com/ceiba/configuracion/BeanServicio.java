package com.ceiba.configuracion;

import com.ceiba.pedido.puerto.repositorio.RepositorioPedido;
import com.ceiba.pedido.servicio.ServicioAprobarPedido;
import com.ceiba.pedido.detalle.puerto.dao.DaoDetallePedido;
import com.ceiba.producto.puerto.repositorio.RepositorioProducto;
import com.ceiba.producto.servicio.ServicioCrearProducto;
import com.ceiba.pedido.servicio.ServicioEliminarPedido;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanServicio {

    @Bean
    public ServicioCrearProducto servicioCrearProducto(RepositorioProducto repositorioProducto) {
        return new ServicioCrearProducto(repositorioProducto);
    }



    @Bean
    public ServicioEliminarPedido servicioEliminarPedido(RepositorioPedido repositorioPedido) {
        return new ServicioEliminarPedido(repositorioPedido);
    }

    @Bean
    public ServicioAprobarPedido servicioActualizarPedido(RepositorioPedido repositorioPedido, DaoDetallePedido daoDetallePedido) {
        return new ServicioAprobarPedido(repositorioPedido, daoDetallePedido);
    }
	

}
