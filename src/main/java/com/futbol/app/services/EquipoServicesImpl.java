package com.futbol.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futbol.app.entity.Equipo;
import com.futbol.app.repository.EquipoRepository;

@Service
public class EquipoServicesImpl implements EquipoServices {

	@Autowired
	private EquipoRepository equipoRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Equipo> finAll() {
		return (List<Equipo>) equipoRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Equipo equipo) {
		equipoRepository.save(equipo);
	}

	@Override
	@Transactional(readOnly = true)
	public Equipo findOne(Long id) {
		return equipoRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		equipoRepository.deleteById(id);
	}

}
