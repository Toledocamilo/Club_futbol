package com.futbol.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.futbol.app.entity.Competicion;

public interface CompeticionRepository extends PagingAndSortingRepository<Competicion, Long> {

}
