package com.stockmaster.categoria;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements CategoriaService {

	private final CategoriaRepository categoriaRepository;

	public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
		this.categoriaRepository = categoriaRepository;
	}

	@Override
	public List<Categoria> listar() {
		return categoriaRepository.findAll();
	}

	@Override
	public void registrar(Categoria c) {
		try {
			categoriaRepository.save(c);
		} catch (Exception e) {
		}
	}

	@Override
	public void actualizar(Categoria c) {
		try {
			categoriaRepository.save(c);
		} catch (Exception e) {
		}
	}

	@Override
	public void desactivar(Long id) {
		Categoria c = buscarPorId(id);
		if(c != null) {
			c.setEstado("INACTIVO");
			categoriaRepository.save(c);
		}
	}

	@Override
	public Categoria buscarPorId(Long id) {
		return categoriaRepository.findById(id).orElse(null);
	}
}
		
