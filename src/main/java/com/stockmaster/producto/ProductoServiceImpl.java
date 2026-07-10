package com.stockmaster.producto;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class ProductoServiceImpl implements ProductoService{

	private final ProductoRepository productoRepository;
	
	public ProductoServiceImpl(ProductoRepository productoRepository) {
		this.productoRepository = productoRepository;
	}
	
	@Override
	public List<Producto> listar() {
		return productoRepository.findAll();
	}

	@Override
	public void registrar(Producto producto) {
		try {
			productoRepository.save(producto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actualizar(Producto producto) {
		try {
			productoRepository.save(producto);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public Producto buscarPorId(long id) {
		return productoRepository.findById(id).orElse(null);
	}

	@Override
	public void desactivar(long id) {
		try {
			Producto p = buscarPorId(id);
			if(p != null) {
				p.setEstado("INACTIVO");
				productoRepository.save(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
