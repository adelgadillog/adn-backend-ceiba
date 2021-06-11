select  sum(pro.precio)
from producto as pro join pedido_producto as pp on pp.idproducto = pro.id
where pp.referenciaPedido = :referenciaPedido