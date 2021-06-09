update producto
set estado = :estado,
	total = :total,
	fecha_aprobacion = :fecha_aprobacion,
	fecha_entrega = :fecha_entrega
where id = :id