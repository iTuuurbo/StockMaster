package com.stockmaster.rol;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class RolServiceImpl implements RolService{

	private final RolRepository rolRepository;
	public RolServiceImpl(RolRepository rolRepository) {
		this.rolRepository = rolRepository;
	}
	
	@Override
	public List<Rol> listar() {
		return rolRepository.findAll();
	}

	@Override
	public void registrar(Rol rol) {
		try {
			rolRepository.save(rol);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void actualizar(Rol rol) {
		try {
			rolRepository.save(rol);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Rol buscarPorId(Long id) {
		return rolRepository.findById(id).orElse(null);
	}

	@Override
	public void desactivar(Long id) {
		try {
			Rol rol = buscarPorId(id);
			if(rol != null)
				rol.setEstado("INACTIVO");
				rolRepository.save(rol);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
