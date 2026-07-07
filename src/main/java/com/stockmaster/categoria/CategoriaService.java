package com.stockmaster.categoria;

import java.util.List;

public interface CategoriaService {
	
	// METODOS
	// LISTAR
	List<Categoria> listar();
	// REGISTRAR
	void registrar(Categoria c);
	// ACTUALIZAR
	void actualizar(Categoria c);
	// DESACTIVAR
	void desactivar(Long id);
	// BUSCAR POR ID
	Categoria buscarPorId(Long id);
	
	
}
