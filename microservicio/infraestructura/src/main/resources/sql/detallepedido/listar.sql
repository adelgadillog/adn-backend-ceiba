select pro.id as idproducto,pro.nombre as nombreProducto,pp.cantidad as cantidadPedido,pro.cantidadDisponible as cantidadDisponible,pro.precio as precio
from producto as pro join pedido_producto as pp on pp.idproducto = pro.id
where pp.referenciaPedido = :referencia