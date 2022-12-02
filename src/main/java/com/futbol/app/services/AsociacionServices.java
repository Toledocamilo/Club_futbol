package com.futbol.app.services;

import java.util.List;

import com.futbol.app.entity.Asociacion;

public interface AsociacionServices {

	public List<Asociacion> finAll();

	public void save(Asociacion asociacion);

	public Asociacion findOne(Long id);

	public void delete(Long id);

}
