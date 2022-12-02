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

import com.futbol.app.entity.Asociacion;
import com.futbol.app.report.AsociacionExporterExcel;
import com.futbol.app.services.AsociacionServices;

@Controller
public class AsociacionController {

	@Autowired
	private AsociacionServices asociacionService;

	@GetMapping({ "/asociacion" })
	public String listarAsociacion(Model model) {
		model.addAttribute("asociaciones", asociacionService.finAll());
		model.addAttribute("titulo", "Listado de Asociación");
		return "mostrarasociacion";
	}

	@GetMapping({ "/asociacion/insertar" })
	public String CrearAsociacion(Map<String, Object> model) {
		Asociacion asociacion = new Asociacion();
		model.put("asociacion", asociacion);
		model.put("titulo", "Registro de Asociación");
		return "insertarAsociacion";
	}

	@PostMapping("/asociacion/insertar")
	public String guardarAsociacion(@Valid Asociacion asociacion, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Registro de Asociación");
			return "insertarAsociacion";
		}

		String mensaje = (asociacion.getId() != null) ? "La Asociación a sido editado con exito"
				: "Asociación registrada";

		asociacionService.save(asociacion);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/asociacion";
	}

	@GetMapping("/asociacion/insertar/{id}")
	public String EditarAsociacion(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Asociacion asociacion = null;
		if (id > 0) {
			asociacion = asociacionService.findOne(id);
			if (asociacion == null) {
				flash.addFlashAttribute("error", "La asociación no existe en la base de datos");
				return "redirect:/asociacion";
			}
		} else {
			flash.addFlashAttribute("error", "El Id de la asociación no puede ser 0");
			return "redirect:/asociacion";
		}
		model.put("asociacion", asociacion);
		model.put("titulo", "Edición de asociación");
		return "insertarAsociacion";
	}

	@GetMapping({ "/asociacion/eliminar/{id}" })
	public String EliminarAsociacion(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			asociacionService.delete(id);
			flash.addFlashAttribute("success", "Asociación Eliminada con exito");
		}
		return "redirect:/asociacion";
	}

	@GetMapping( "/asociacion/exportarExcel" )
	public void exportarListadoDeAsociacionesExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename = Asociaciones_" + fechaActual + ".xlsx";

		response.setHeader(cabecera, valor);

		List<Asociacion> asociaciones = asociacionService.finAll();

		AsociacionExporterExcel exporter = new AsociacionExporterExcel(asociaciones);
		exporter.exporta(response);

	}

}
