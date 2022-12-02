package com.futbol.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.futbol.app.entity.Jugador;

public interface JugadorRepository extends PagingAndSortingRepository<Jugador, Long> {

}
