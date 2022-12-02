package com.futbol.app.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "equipos")
public class Equipo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String nombre;

	@OneToOne
	@JoinColumn(name = "id_entrenador")
	private Entrenador entrenador;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_asociacion")
	private Asociacion asociacion;

	@ManyToMany
	private List<Competicion> competencias;

	@OneToMany
	@JoinColumn(name = "id_equipo")
	private List<Jugador> jugadores;

	public Equipo() {
		super();
	}

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Equipo(Long id, @NotEmpty String nombre, Entrenador entrenador, Asociacion asociacion,
			List<Competicion> competencias, List<Jugador> jugadores) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.entrenador = entrenador;
		this.asociacion = asociacion;
		this.competencias = competencias;
		this.jugadores = jugadores;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Entrenador getEntrenador() {
		return entrenador;
	}

	public void setEntrenador(Entrenador entrenador) {
		this.entrenador = entrenador;
	}

	public Asociacion getAsociacion() {
		return asociacion;
	}

	public void setAsociacion(Asociacion asociacion) {
		this.asociacion = asociacion;
	}

	public List<Competicion> getCompetencias() {
		return competencias;
	}

	public void setCompetencias(List<Competicion> competencias) {
		this.competencias = competencias;
	}

	@Override
	public String toString() {
		return "Equipo id=" + id + ", nombre=" + nombre + ", entrenador=" + entrenador + ", asociacion=" + asociacion
				+ ", competencias=" + competencias + ", jugadores=" + jugadores;
	}
	
	
}
