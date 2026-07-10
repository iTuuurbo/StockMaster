package com.stockmaster.producto;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stockmaster.categoria.CategoriaService;

@Controller
@RequestMapping("/productos")
public class ProductoController {

	private final ProductoService productoService;
	private final CategoriaService categoriaService;
	
	public ProductoController(ProductoService productoService, CategoriaService categoriaService) {
		this.productoService = productoService;
		this.categoriaService = categoriaService;
	}
	
	// LISTAR - REGISTRAR - ACTUALIZAR - BUSCAR POR ID - DESACTIVAR
	
	
	// Obtiene los productos y los envia la vista
	@GetMapping("/lista")
	public String lista(Model model) {
		List<Producto> lista = productoService.listar();
		// Obtiene el estado activo
		int activos = 0;
		for(Producto producto : lista) {
			if("ACTIVO".equals(producto.getEstado())) {
				activos++;
			}
		}
		// Obtiene el estado inactivo
		int inactivo = 0;
		for(Producto producto : lista) {
			if("INACTIVO".equals(producto.getEstado())) {
				inactivo++;
			}
		}
		
		model.addAttribute("productos", lista);
		model.addAttribute("producto", new Producto());
		model.addAttribute("categorias", categoriaService.listar());
		model.addAttribute("totalProductos", lista.size());
		model.addAttribute("totalActivos", activos);
		model.addAttribute("totalInactivos", inactivo);
		return "productos/lista";
	}
	
	// Procesa el formulario y registrar un nuevo producto
	@PostMapping("/registrar")
	public String registrar(Producto producto, RedirectAttributes redirect) {
		try {
			productoService.registrar(producto);
			redirect.addFlashAttribute("success", "Producto registrado correctamente");
			return "redirect:/productos/lista";
		} catch (Exception e) {
			redirect.addFlashAttribute("error", "Error al registrar producto");
			return "redirect:/productos/lista";
		}
	}
	
	// Procesa el formulario y actualiza el producto seleccionado
	@PostMapping("/actualizar")
	public String actualizar(Producto producto, RedirectAttributes redirect) {
		try {
			productoService.actualizar(producto);
			redirect.addFlashAttribute("success", "Producto actualizado correctamente");
			return "redirect:/productos/lista";
		} catch (Exception e) {
			redirect.addFlashAttribute("error", "Error al actualizar el producto");
			return "redirect:/productos/lista";
		}
	}
	
	// Obtiene el producto segun su ID
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public Producto buscarPorId(@PathVariable Long id) {
		return productoService.buscarPorId(id);
	}
	
	// Desactiva un producto mediante la eliminacion logica
	@GetMapping("/desactivar/{id}")
	public String desactivar(@PathVariable Long id, RedirectAttributes redirect) {
		try {
			productoService.desactivar(id);
			redirect.addFlashAttribute("success", "Producto desactivado correctamente");
			return "redirect:/productos/lista";
		} catch (Exception e) {
			redirect.addFlashAttribute("error", "Error al desactivar el producto");
			return "redirect:/productos/lista";
		}
	}
	
	
	
}
