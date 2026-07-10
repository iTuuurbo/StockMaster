package com.stockmaster.rol;

import java.util.List;

public interface RolService {

	List<Rol> listar();
	void registrar(Rol rol);
	void actualizar(Rol rol);
	Rol buscarPorId(Long id);
	void desactivar(Long id);
	
}
