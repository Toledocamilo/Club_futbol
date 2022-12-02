package com.futbol.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "asociaciones")
public class Asociacion {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String nombre;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String pais;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String presidente;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String siglas;

	public Asociacion() {
		super();
	}

	public Asociacion(Long id, @NotEmpty String nombre, @NotEmpty String pais, @NotEmpty String presidente,
			@NotEmpty String siglas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.pais = pais;
		this.presidente = presidente;
		this.siglas = siglas;
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getPresidente() {
		return presidente;
	}

	public void setPresidente(String presidente) {
		this.presidente = presidente;
	}

	public String getSiglas() {
		return siglas;
	}

	public void setSiglas(String siglas) {
		this.siglas = siglas;
	}

	@Override
	public String toString() {
		return "Asociacion id=" + id + ", nombre=" + nombre + ", pais=" + pais + ", presidente=" + presidente
				+ ", siglas=" + siglas;
	}

}
