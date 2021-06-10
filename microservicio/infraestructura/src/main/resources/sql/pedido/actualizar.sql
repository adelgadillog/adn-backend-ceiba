update pedido
set estado = :estado,
	total = :total,
	fecha_aprobacion = :fechaAprobacion,
	fecha_entrega = :fechaEntrega
where referencia = :referencia