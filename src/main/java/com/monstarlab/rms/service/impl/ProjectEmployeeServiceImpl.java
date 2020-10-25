package com.monstarlab.rms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monstarlab.rms.model.ProjectEmployee;
import com.monstarlab.rms.repository.ProjectEmployeeRepository;
import com.monstarlab.rms.service.ProjectEmployeeService;

@Service
@Transactional
public class ProjectEmployeeServiceImpl implements ProjectEmployeeService {

	@Autowired
	private ProjectEmployeeRepository projectEmployeeRepository;

	@Override
	public List<ProjectEmployee> findAll() {
		return this.projectEmployeeRepository.findAll();
	} 
	
	@Override
	public Optional<ProjectEmployee> findById(Long id) {
		return this.projectEmployeeRepository.findById(id);
	}

	@Override
	@Transactional
	public ProjectEmployee save(ProjectEmployee projectEmployee) {
		return this.projectEmployeeRepository.save(projectEmployee);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.projectEmployeeRepository.deleteById(id);
	} 

}
