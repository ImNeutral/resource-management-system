package com.monstarlab.rms.service;

import java.util.List;
import java.util.Optional;

import com.monstarlab.rms.model.ProjectEmployee;

public interface ProjectEmployeeService {

	List<ProjectEmployee> findAll(); 
	
	Optional<ProjectEmployee> findById(final Long id);

	ProjectEmployee save(final ProjectEmployee projectEmployee);

	void delete(final Long id);
}