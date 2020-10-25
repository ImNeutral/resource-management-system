package com.monstarlab.rms.service;

import java.util.List;
import java.util.Optional;

import com.monstarlab.rms.model.Project;

public interface ProjectService {

	List<Project> findAll();

	List<Project> findAllUnarchived();
	
	Optional<Project> findById(final Long id);

	Project save(final Project project);

	void delete(final Long id);
}