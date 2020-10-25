package com.monstarlab.rms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.monstarlab.rms.model.ProjectEmployee;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployee, Long>{

}
