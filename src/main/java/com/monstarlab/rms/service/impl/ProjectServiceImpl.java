package com.monstarlab.rms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monstarlab.rms.model.Project;
import com.monstarlab.rms.repository.ProjectRepository;
import com.monstarlab.rms.service.ProjectService;

@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public List<Project> findAll() {
		return this.projectRepository.findAll();
	}

	@Override
	public List<Project> findAllUnarchived() {
		return this.projectRepository.findByArchived(false);
	}
	
	@Override
	public Optional<Project> findById(Long id) {
		return this.projectRepository.findById(id);
	}

	@Override
	@Transactional
	public Project save(Project project) {
		return this.projectRepository.save(project);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.projectRepository.deleteById(id);
	}

}
