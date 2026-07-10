package com.stockmaster.rol;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/roles")
public class RolController {

	private final RolService rolService;
	public RolController(RolService rolService) {
		this.rolService = rolService;
	}
	
	// Obtiene la lista y lo envia a la vista
	@GetMapping("/lista")
	public String listar(Model model) {
		List<Rol> lista = rolService.listar();
		// Obtiene los roles activos y los envia a la lista
		int activo = 0;
		for(Rol rol : lista) {
			if("ACTIVO".equals(rol.getEstado())) {
				activo++;
			}
		}
		// Obtiene los roles inactivos y los envia a la lista
		int inactivo = 0;
		for(Rol rol : lista) {
			if("INACTIVO".equals(rol.getEstado())) {
				inactivo++;
			}
		}
		model.addAttribute("roles", lista);
		model.addAttribute("totalRoles", lista.size());
		model.addAttribute("totalActivos", activo);
		model.addAttribute("totalInactivos", inactivo);
		return "roles/lista";
	}
	
	// Procesa el formulario y registra un nuevo rol
	@PostMapping("/registrar")
	public String registrar(Rol rol, RedirectAttributes redirect) {
		try {
			rolService.registrar(rol);
			redirect.addFlashAttribute("success", "Rol registrado correctamente");
			return "redirect:/roles/lista";
		} catch (Exception e) {
			redirect.addFlashAttribute("error", "Error al registrar");
			return "redirect:/roles/lista";
		}
	}
	
	// Procesa el formulario y actualiza un rol
	@PostMapping("/actualizar")
	public String actualizar(Rol rol, RedirectAttributes redirect) {
		try {
			rolService.actualizar(rol);
			redirect.addFlashAttribute("success", "Rol actualizado correctamente");
			return "redirect:/roles/lista";
		} catch (Exception e) {
			redirect.addFlashAttribute("error", "Error al actualizar");
			return "redirect:/roles/lista";
		}
	}
	
	// Obtiene el rol mediante su ID
	@GetMapping("/buscar/{id}")
	@ResponseBody
	public Rol buscarPorId(@PathVariable Long id) {
		return rolService.buscarPorId(id);	
	}
	
	// Desactiva un rol mediante la eliminacion logica
	@GetMapping("/desactivar/{id}")
	public String desactivar(@PathVariable Long id, RedirectAttributes redirect) {
		try {
			rolService.desactivar(id);
			redirect.addFlashAttribute("success", "Rol desactivado correctamente");
			return "redirect:/roles/lista";
		} catch (Exception e) {
			redirect.addFlashAttribute("error", "Error al desactivar");
			return "redirect:/roles/lista";
		}
	}
}
