package com.stockmaster.producto;

import java.util.List;

public interface ProductoService {
	
	// Listar - Registrar - Actualizar - buscar por ID - desactivar
	
	List<Producto> listar();
	void registrar(Producto producto);
	void actualizar(Producto producto);
	Producto buscarPorId(long id);
	void desactivar(long id);

}
