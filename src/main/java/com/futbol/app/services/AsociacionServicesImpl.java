package com.futbol.app.services;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futbol.app.entity.Asociacion;
import com.futbol.app.repository.AsociacionRepository;

@Service
public class AsociacionServicesImpl implements AsociacionServices {

	@Autowired
	private AsociacionRepository asociacionRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Asociacion> finAll() {
		return (List<Asociacion>) asociacionRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Asociacion asociacion) {
		asociacionRepository.save(asociacion);
	}

	@Override
	@Transactional(readOnly = true)
	public Asociacion findOne(Long id) {
		return asociacionRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		asociacionRepository.deleteById(id);
	}

}
