package com.futbol.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.futbol.app.entity.Competicion;
import com.futbol.app.repository.CompeticionRepository;

@Service
public class CompeticionServicesImpl implements CompeticionServices {

	@Autowired
	private CompeticionRepository competicionRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Competicion> finAll() {
		return (List<Competicion>) competicionRepository.findAll();
	}

	@Override
	@Transactional
	public void save(Competicion competicion) {
		competicionRepository.save(competicion);
	}

	@Override
	@Transactional(readOnly = true)
	public Competicion findOne(Long id) {
		return competicionRepository.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		competicionRepository.deleteById(id);
	}

}
