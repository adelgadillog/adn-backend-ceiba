create table pedido (
 id int(11) not null auto_increment,
 referencia varchar(100) not null,
 estado int(11),
 usuario_id int(11),
 total double,
 fecha_creacion datetime null,
 fecha_aprobacion datetime null,
 fecha_entrega datetime null,
 primary key (id)
);

create table producto (
 id int(11) not null auto_increment,
 nombre varchar(100) not null,
 cantidadDisponible int(11),
 precio double,
 primary key (id)
);

create table pedido_producto(

    referenciaPedido varchar(100) not null,
    idproducto int(11) not null,
    cantidad int(11) not null

);