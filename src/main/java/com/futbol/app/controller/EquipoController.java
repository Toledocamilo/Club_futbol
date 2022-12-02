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
import com.futbol.app.entity.Competicion;
import com.futbol.app.entity.Entrenador;
import com.futbol.app.entity.Equipo;
import com.futbol.app.entity.Jugador;
import com.futbol.app.report.EquipoExporterExcel;
import com.futbol.app.services.AsociacionServices;
import com.futbol.app.services.CompeticionServices;
import com.futbol.app.services.EntrenadorServices;
import com.futbol.app.services.EquipoServices;
import com.futbol.app.services.JugadorServices;


@Controller
public class EquipoController {

	@Autowired
	private EquipoServices equipoService;
	@Autowired
	private AsociacionServices asociacionService;
	@Autowired
	private EntrenadorServices entrenadorService;
	@Autowired
	private JugadorServices jugadorService;
	@Autowired
	private CompeticionServices competicionService;
	
	@GetMapping({ "/equipo" })
	public String listarEquipo(Model model) {
		model.addAttribute("equipos", equipoService.finAll());
		model.addAttribute("titulo", "Listado de Equipo");
		
		List<Asociacion> listaasociacion = asociacionService.finAll();
		List<Entrenador> listaentrenador = entrenadorService.finAll();
		List<Jugador> listajugador = jugadorService.finAll();
		List<Competicion> listacompetencia = competicionService.finAll();
		model.addAttribute("asociaciones",listaasociacion);
		model.addAttribute("entrenadores",listaentrenador);
		model.addAttribute("jugadores",listajugador);
		model.addAttribute("competencias",listacompetencia);
		
		return "mostrarequipo";
	}
	
	@GetMapping({ "/equipo/insertar" })
	public String CrearEquipo(Map<String, Object> model) {
		List<Asociacion> listaasociacion = asociacionService.finAll();
		List<Entrenador> listaentrenador = entrenadorService.finAll();
		List<Jugador> listajugador = jugadorService.finAll();
		List<Competicion> listacompetencia = competicionService.finAll();
		Equipo equipo = new Equipo();
		model.put("equipo", equipo);
		model.put("titulo", "Registro de equipo");
		model.put("asociaciones",listaasociacion);
		model.put("entrenadores",listaentrenador);
		model.put("jugadores",listajugador);
		model.put("competencias",listacompetencia);
		return "insertarEquipo";
	}
	
	@PostMapping("/equipo/insertar")
	public String guardarEquipo(@Valid Equipo equipo, BindingResult result, Model model,
			RedirectAttributes flash, SessionStatus status) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Registro de equipo");
			
			List<Asociacion> listaasociacion = asociacionService.finAll();
			List<Entrenador> listaentrenador = entrenadorService.finAll();
			List<Jugador> listajugador = jugadorService.finAll();
			List<Competicion> listacompetencia = competicionService.finAll();
			model.addAttribute("asociaciones",listaasociacion);
			model.addAttribute("entrenadores",listaentrenador);
			model.addAttribute("jugadores",listajugador);
			model.addAttribute("competencias",listacompetencia);
			return "insertarEquipo";
		}

		String mensaje = (equipo.getId() != null) ? "El equipo a sido editado con exito"
				: "Equipo registrado";

	equipoService.save(equipo);
		status.setComplete();
		flash.addFlashAttribute("success", mensaje);
		return "redirect:/equipo";
	}
	
	@GetMapping("/equipo/insertar/{id}")
	public String EditarEquipo(@PathVariable(value = "id") Long id, Map<String, Object> model,
			RedirectAttributes flash) {
		Equipo equipo = null;
		if (id > 0) {
			equipo = equipoService.findOne(id);
			if (equipo == null) {
				flash.addFlashAttribute("error", "El equipo no existe en la base de datos");
				return "redirect:/equipo";
			}
		} else {
			flash.addFlashAttribute("error", "El Id del equipo no puede ser 0");
			return "redirect:/equipo";
		}
		model.put("equipo", equipo);
		model.put("titulo", "Edición de equipo");
		
		List<Asociacion> listaasociacion = asociacionService.finAll();
		List<Entrenador> listaentrenador = entrenadorService.finAll();
		List<Jugador> listajugador = jugadorService.finAll();
		List<Competicion> listacompetencia = competicionService.finAll();
		model.put("asociaciones",listaasociacion);
		model.put("entrenadores",listaentrenador);
		model.put("jugadores",listajugador);
		model.put("competencias",listacompetencia);
		return "insertarEquipo";
	}
	
	@GetMapping({ "/equipo/eliminar/{id}" })
	public String EliminarEquipo(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			equipoService.delete(id);
			flash.addFlashAttribute("success", "Equipo eliminado con exito");
		}
		return "redirect:/equipo";
	}
	
	@GetMapping( "/equipo/exportarExcel" )
	public void exportarListadoDeEquiposExcel(HttpServletResponse response) throws IOException {
		response.setContentType("application/octet-stream");

		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String fechaActual = dateFormatter.format(new Date());

		String cabecera = "Content-Disposition";
		String valor = "attachment; filename = Equipos_" + fechaActual + ".xlsx";

		response.setHeader(cabecera, valor);

		List<Equipo> equipos = equipoService.finAll();

		EquipoExporterExcel exporter = new EquipoExporterExcel(equipos);
		exporter.exporta(response);

	}
}
