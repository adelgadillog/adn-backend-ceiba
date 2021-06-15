insert into pedido(referencia,estado,usuario_id,fecha_creacion) values('00001',1,17,now());
insert into producto(id,nombre,cantidadDisponible,precio) values(3,'Audifonos',3,100000);
insert into producto(id,nombre,cantidadDisponible,precio) values(4,'Teclado',3,30000);
insert into pedido_producto(referenciaPedido,idProducto,cantidad) values('00001',1,2);
insert into pedido_producto(referenciaPedido,idProducto,cantidad) values('00001',2,1);
