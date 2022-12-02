package com.futbol.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "entrenadores")
public class Entrenador {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String nombre;

	@NotNull
	@Column(nullable = false, length = 50)
	private int edad;

	@NotEmpty
	@Column(nullable = false, length = 50)
	private String nacionalidad;

	@NotEmpty
	@Email
	@Column(nullable = false, length = 50)
	private String correo;

	public Entrenador() {
		super();
	}

	public Entrenador(Long id, @NotEmpty String nombre, @NotNull int edad, @NotEmpty String nacionalidad,
			@NotEmpty @Email String correo) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.edad = edad;
		this.nacionalidad = nacionalidad;
		this.correo = correo;
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

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNacionalidad() {
		return nacionalidad;
	}

	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	@Override
	public String toString() {
		return "Entrenador id=" + id + ", nombre=" + nombre + ", edad=" + edad + ", nacionalidad=" + nacionalidad
				+ ", correo=" + correo;
	}

}
