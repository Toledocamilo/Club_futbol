package com.futbol.app.services;

import java.util.List;

import com.futbol.app.entity.Equipo;

public interface EquipoServices {
	public List<Equipo> finAll();

	public void save(Equipo equipo);

	public Equipo findOne(Long id);

	public void delete(Long id);
}
