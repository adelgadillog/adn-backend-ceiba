select  id, nombre,  precio,  cantidadDisponible
from producto where id in (:listaIdProducto)