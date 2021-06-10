select  pp.referenciaPedido, pro.nombre,  pp.cantidad,  pro.precio, pp.idproducto
from producto as pro join pedido_producto as pp on pp.idproducto = pro.id
where pp.referenciaPedido = :referenciaPedido