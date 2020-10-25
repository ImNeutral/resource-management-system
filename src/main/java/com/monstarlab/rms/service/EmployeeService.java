package com.monstarlab.rms.service;

import java.util.List;
import java.util.Optional;

import com.monstarlab.rms.model.Employee;

public interface EmployeeService {

	List<Employee> findAll();
	
	List<Employee> findAllUnarchived();

	List<Employee> findByUnArchivedOrderByIdAsc();
	
	Optional<Employee> findById(final Long id);

	Employee save(final Employee developer);

	void delete(final Long id);
}
