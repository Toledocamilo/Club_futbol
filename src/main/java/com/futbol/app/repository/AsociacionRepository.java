package com.futbol.app.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.futbol.app.entity.Asociacion;

public interface AsociacionRepository  extends PagingAndSortingRepository<Asociacion, Long>{
	
}
