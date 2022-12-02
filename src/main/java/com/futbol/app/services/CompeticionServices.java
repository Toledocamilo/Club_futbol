package com.futbol.app.services;

import java.util.List;

import com.futbol.app.entity.Competicion;

public interface CompeticionServices {

	public List<Competicion> finAll();

	public void save(Competicion competicion);

	public Competicion findOne(Long id);

	public void delete(Long id);
}
