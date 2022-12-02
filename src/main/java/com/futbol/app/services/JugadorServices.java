package com.futbol.app.services;

import java.util.List;

import com.futbol.app.entity.Jugador;

public interface JugadorServices {
	
	public List<Jugador> finAll();

	public void save(Jugador jugador);

	public Jugador findOne(Long id);

	public void delete(Long id);

}
