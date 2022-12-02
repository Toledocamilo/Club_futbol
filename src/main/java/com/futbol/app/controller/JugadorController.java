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

import com.futbol.app.entity.Jugador;
import com.futbol.app.report.JugadorExporterExcel;
import com.futbol.app.services.JugadorServices;

@Controller
public class JugadorController {

	@Autowired
	private JugadorServices jugadorService;
	
	@GetMapping({ "/jugador" })
	public String listarJugador(Model model) {
		model.addAttribute("jugadores", jugadorService.finAll());
		model.addAttribute("titulo", "Listado de Jugador");
		return "mostrarjugador";
	}
	
	@GetMapping({ "/jugador/insertar" })
	public String CrearJugador(Map<String, Object> model) {
		Jugador jugador = new Jugador();
		model.put("jugador", jugador);
		model.put("titulo", "Registro de jugador");
		return "insertarJugador";
	}
	
	@PostMapping("/jugador/insertar")
	public String guardarJugador(@Valid Jugador jugador, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Registro de Jugador");
			return "insertarJugador";
		}

		String mensaje = (jugador.getId() != null) ? "El jugador a sido editado con exito"
				: "Jugador registrado";

		jugadorService.save(jugador);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/jugador";
	}
	
	@GetMapping("/jugador/insertar/{id}")
	public String EditarJugador(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Jugador jugador = null;
		if (id > 0) {
			jugador = jugadorService.findOne(id);
			if (jugador == null) {
				flash.addFlashAttribute("error", "El jugador no existe en la base de datos");
				return "redirect:/jugador";
			}
		} else {
			flash.addFlashAttribute("error", "El Id del jugador no puede ser 0");
			return "redirect:/jugador";
		}
		model.put("jugador", jugador);
		model.put("titulo", "Edición de jugador");
		return "insertarJugador";
	}
	

	@GetMapping({ "/jugador/eliminar/{id}" })
	public String EliminarJugador(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			jugadorService.delete(id);
			flash.addFlashAttribute("success", "Jugador eliminada con exito");
		}
		return "redirect:/jugador";
	}
	
	@GetMapping( "/jugador/exportarExcel" )
	public void exportarListadoDeJugadoresExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename = Jugadores_" + fechaActual + ".xlsx";

		response.setHeader(cabecera, valor);

		List<Jugador> jugadores = jugadorService.finAll();

		JugadorExporterExcel exporter = new JugadorExporterExcel(jugadores);
		exporter.exporta(response);

	}
}
