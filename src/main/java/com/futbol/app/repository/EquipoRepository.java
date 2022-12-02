package com.futbol.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.futbol.app.entity.Equipo;

public interface EquipoRepository  extends PagingAndSortingRepository<Equipo, Long>{

}
