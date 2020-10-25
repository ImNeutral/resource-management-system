package com.monstarlab.rms.dto;

import java.math.BigDecimal;
import java.sql.Date;

import com.monstarlab.rms.model.Employee;
import com.monstarlab.rms.model.Project;

public class ProjectEmployeeDto {
	private Long id;
	 
    private Employee employee;
	 
    private Project project;
 
	private Date dateFrom;
	 
	private Date dateTo;
	
	private BigDecimal allocation;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Date getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}

	public Date getDateTo() {
		return dateTo;
	}

	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}

	public BigDecimal getAllocation() {
		return allocation;
	}

	public void setAllocation(BigDecimal allocation) {
		this.allocation = allocation;
	}
	 
}
