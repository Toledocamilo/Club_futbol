package com.futbol.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futbol.app.entity.Jugador;
import com.futbol.app.repository.JugadorRepository;

@Service
public class JugadorServicesImpl implements JugadorServices {

	@Autowired
	private JugadorRepository jugadorRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Jugador> finAll() {
		return (List<Jugador>) jugadorRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Jugador jugador) {
		jugadorRepository.save(jugador);
	}

	@Override
	@Transactional(readOnly = true)
	public Jugador findOne(Long id) {
		return jugadorRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		jugadorRepository.deleteById(id);
	}

}
