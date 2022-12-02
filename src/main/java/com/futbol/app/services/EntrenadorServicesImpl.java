package com.futbol.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futbol.app.entity.Entrenador;
import com.futbol.app.repository.EntrenadorRepository;

@Service
public class EntrenadorServicesImpl implements EntrenadorServices {

	@Autowired
	private EntrenadorRepository entrenadorRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Entrenador> finAll() {
		return (List<Entrenador>) entrenadorRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Entrenador entrenador) {
		entrenadorRepository.save(entrenador);
	}

	@Override
	@Transactional(readOnly = true)
	public Entrenador findOne(Long id) {
		return entrenadorRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		entrenadorRepository.deleteById(id);
	}

}
