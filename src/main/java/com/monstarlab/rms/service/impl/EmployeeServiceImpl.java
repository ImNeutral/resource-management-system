package com.monstarlab.rms.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.monstarlab.rms.model.Employee;
import com.monstarlab.rms.repository.EmployeeRepository;
import com.monstarlab.rms.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public List<Employee> findAll() {
		return this.employeeRepository.findAll();
	}
	
	@Override
	public List<Employee> findAllUnarchived() {
		return this.employeeRepository.findByArchived(false);
	}

	@Override
	public Optional<Employee> findById(final Long id) {
		return this.employeeRepository.findById(id);
	}

	@Override
	@Transactional
	public Employee save(final Employee employee) {
		return this.employeeRepository.save(employee);
	}

	@Override
	@Transactional
	public void delete(final Long id) {
		this.employeeRepository.deleteById(id);
	}

	@Override
	public List<Employee> findByUnArchivedOrderByIdAsc() {
		return this.employeeRepository.findByArchivedOrderByDesignationAsc(false);
	}
}
