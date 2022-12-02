package com.futbol.app.services;

import java.util.List;

import com.futbol.app.entity.Entrenador;

public interface EntrenadorServices {

	public List<Entrenador> finAll();

	public void save(Entrenador entrenador);

	public Entrenador findOne(Long id);

	public void delete(Long id);
}
