package com.monstarlab.rms.service;

import java.util.List;
import java.util.Optional;

import com.monstarlab.rms.model.SystemCodes;

public interface SystemCodesService {
	List<SystemCodes> findAll();

	List<SystemCodes> findByCategory(String category);
	
	Optional<SystemCodes> findById(final Long id);

	SystemCodes save(final SystemCodes systemCodes);

	void delete(final Long id);
}
