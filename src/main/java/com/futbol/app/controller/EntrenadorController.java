package com.futbol.app.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.futbol.app.entity.Entrenador;
import com.futbol.app.report.EntrenadorExporterExcel;
import com.futbol.app.services.EntrenadorServices;

@Controller
public class EntrenadorController {

	@Autowired
	private EntrenadorServices entrenadorService;
	
	@GetMapping({ "/entrenador" })
	public String listarEntrenador(Model model) {
		model.addAttribute("entrenadores", entrenadorService.finAll());
		model.addAttribute("titulo", "Listado de Entrenador");
		return "mostrarentrenador";
	}
	
	@GetMapping({ "/entrenador/insertar" })
	public String CrearEntrenador(Map<String, Object> model) {
		Entrenador entrenador = new Entrenador();
		model.put("entrenador", entrenador);
		model.put("titulo", "Registro de Entrenador");
		return "insertarEntrenador";
	}
	
	@PostMapping("/entrenador/insertar")
	public String guardarEntrenador(@Valid Entrenador entrenador, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Registro de Entrenador");
			return "insertarEntrenador";
		}

		String mensaje = (entrenador.getId() != null) ? "El entrenador a sido editado con exito"
				: "Entrenador registrado";

		entrenadorService.save(entrenador);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/entrenador";
	}
	
	@GetMapping("/entrenador/insertar/{id}")
	public String EditarEntrenador(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Entrenador entrenador = null;
		if (id > 0) {
			entrenador = entrenadorService.findOne(id);
			if (entrenador == null) {
				flash.addFlashAttribute("error", "El entrenador no existe en la base de datos");
				return "redirect:/entrenador";
			}
		} else {
			flash.addFlashAttribute("error", "El Id del entrenador no puede ser 0");
			return "redirect:/entrenador";
		}
		model.put("entrenador", entrenador);
		model.put("titulo", "Edición de entrenador");
		return "insertarEntrenador";
	}
	
	@GetMapping({ "/entrenador/eliminar/{id}" })
	public String EliminarEntrenador(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			entrenadorService.delete(id);
			flash.addFlashAttribute("success", "Entrenador Eliminado con exito");
		}
		return "redirect:/entrenador";
	}

	@GetMapping( "/entrenador/exportarExcel" )
	public void exportarListadoDeEntrenadoresExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename = Entrenadores_" + fechaActual + ".xlsx";

		response.setHeader(cabecera, valor);

		List<Entrenador> entrenadores = entrenadorService.finAll();

		EntrenadorExporterExcel exporter = new EntrenadorExporterExcel(entrenadores);
		exporter.exporta(response);

	}
}
