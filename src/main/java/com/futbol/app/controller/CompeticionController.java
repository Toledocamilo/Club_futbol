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

import com.futbol.app.entity.Competicion;
import com.futbol.app.report.CompeticionExporterExcel;
import com.futbol.app.services.CompeticionServices;

@Controller
public class CompeticionController {

	@Autowired
	private CompeticionServices competicionService;
	
	@GetMapping({ "/competicion" })
	public String listarCompeticion(Model model) {
		model.addAttribute("competiciones", competicionService.finAll());
		model.addAttribute("titulo", "Listado de Competencias");
		return "mostrarcompeticion";
	}
	
	@GetMapping({ "/competicion/insertar" })
	public String CrearCompeticion(Map<String, Object> model) {
		Competicion competicion = new Competicion();
		model.put("competicion", competicion);
		model.put("titulo", "Registro de competencia");
		return "insertarCompeticion";
	}
	
	@PostMapping("/competicion/insertar")
	public String guardarCompeticion(@Valid Competicion competicion, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Registro de competencia");
			return "insertarCompeticion";
		}

		String mensaje = (competicion.getId() != null) ? "La competencia a sido editado con exito"
				: "Competencia registrado";

	competicionService.save(competicion);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/competicion";
	}
	
	@GetMapping("/competicion/insertar/{id}")
	public String EditarCompeticion(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Competicion competicion = null;
		if (id > 0) {
			competicion = competicionService.findOne(id);
			if (competicion == null) {
				flash.addFlashAttribute("error", "La competencia no existe en la base de datos");
				return "redirect:/competicion";
			}
		} else {
			flash.addFlashAttribute("error", "El Id de la competencia no puede ser 0");
			return "redirect:/competencia";
		}
		model.put("competicion", competicion);
		model.put("titulo", "Edición de competencia");
		return "insertarCompeticion";
	}
	
	@GetMapping({ "/competicion/eliminar/{id}" })
	public String EliminarCompeticion(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			competicionService.delete(id);
			flash.addFlashAttribute("success", "Competencia eliminada con exito");
		}
		return "redirect:/competicion";
	}
	
	@GetMapping( "/competicion/exportarExcel" )
	public void exportarListadoDeCompetenciasExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename = Competencias_" + fechaActual + ".xlsx";

		response.setHeader(cabecera, valor);

		List<Competicion> competencias = competicionService.finAll();

		CompeticionExporterExcel exporter = new CompeticionExporterExcel(competencias);
		exporter.exporta(response);

	}
}
