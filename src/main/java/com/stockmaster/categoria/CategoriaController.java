package com.stockmaster.categoria;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.stockmaster.producto.Producto;



@Controller
@RequestMapping("/categorias") // TODAS LAS RUTAS DE ESTA CLASE COMENZARAN CON /categorias
public class CategoriaController {
	
	// Inyeccion de dependecias mediante constructor
	private final CategoriaService categoriaService;
	
	public CategoriaController(CategoriaService categoriaService) {
		this.categoriaService = categoriaService;
	}
	
	// Obtiene las categorias y las envia a la vista
	@GetMapping("/lista")
	public String listar(Model model) { 
		List<Categoria> lista = categoriaService.listar();
		int totalCategorias = lista.size();
		
		int totalActivos = 0;
		for(Categoria cat : lista) {
			if("ACTIVO".equals(cat.getEstado())) {
				totalActivos++;
			}
		}
		
		int totalInactivos = 0;
		for(Categoria categoria : lista) {
			if("INACTIVO".equals(categoria.getEstado())) {
				totalInactivos++;
			}
		}
		
		model.addAttribute("categorias", lista);
		model.addAttribute("categoria", new Categoria());
		model.addAttribute("totalCategorias", totalCategorias);
		model.addAttribute("totalActivos", totalActivos);
		model.addAttribute("totalInactivos", totalInactivos);
		return "categorias/lista";
	}
	
	
	// Procesa el formulario y registra una nueva categoria
	@PostMapping("/registrar")
	public String registrar(Categoria categoria, RedirectAttributes redirect) {
		try {
			categoriaService.registrar(categoria);
			redirect.addFlashAttribute("success", "Categoria registrada correctamente.");
			return "redirect:/categorias/lista";
		} catch (Exception e) {
			redirect.addFlashAttribute("error", "Error al guardar la categoria");
			return "redirect:/categorias/lista";
		}
	}
	
	// Procesa el formulario y actualiza la categoria seleccionada
		@PostMapping("/actualizar")
		public String actualizar(Categoria categoria, RedirectAttributes redirect) {
			try {
				categoriaService.actualizar(categoria);
				redirect.addFlashAttribute("success", "Categoria actualizada correctamente");
				return "redirect:/categorias/lista";
			} catch (Exception e) {
				redirect.addFlashAttribute("error", "Error al actualizar la categoria");
				return "redirect:/categorias/lista";
			}
		}
	
	// Obtiene la categoría según su ID
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public Categoria buscar(@PathVariable Long id) {
		return categoriaService.buscarPorId(id);
	}
	
	// Desactiva una categoría mediante eliminación lógica
	@GetMapping("/desactivar/{id}")
	public String desactivar(@PathVariable Long id, RedirectAttributes redirect) {
		categoriaService.desactivar(id);
		redirect.addFlashAttribute("success", "Categoria desactivada correctamente.");
		return "redirect:/categorias/lista";
	}

}
