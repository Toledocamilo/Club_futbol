package com.futbol.app.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "competiciones")
public class Competicion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String nombre;

	@NotNull
	@Column(nullable = false, length = 50)
	private int montopremio;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechainicio", nullable = false)
	@NotNull
	private Date fechainicio;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fechafin")
	@NotNull
	private Date fechafin;

	public Competicion() {
		super();
	}

	public Competicion(Long id, @NotEmpty String nombre, @NotNull int montopremio, @NotNull Date fechainicio,
			@NotNull Date fechafin) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.montopremio = montopremio;
		this.fechainicio = fechainicio;
		this.fechafin = fechafin;
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

	public int getMontopremio() {
		return montopremio;
	}

	public void setMontopremio(int montopremio) {
		this.montopremio = montopremio;
	}

	public Date getFechainicio() {
		return fechainicio;
	}

	public void setFechainicio(Date fechainicio) {
		this.fechainicio = fechainicio;
	}

	public Date getFechafin() {
		return fechafin;
	}

	public void setFechafin(Date fechafin) {
		this.fechafin = fechafin;
	}

	@Override
	public String toString() {
		return "Competicion id=" + id + ", nombre=" + nombre + ", montopremio=" + montopremio + ", fechainicio="
				+ fechainicio + ", fechafin=" + fechafin;
	}

}
