package com.monstarlab.rms.dto;

import java.util.List;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.monstarlab.rms.model.ProjectEmployee;
import com.monstarlab.rms.model.SystemCodes;

public class EmployeeDto {

	private Long id;

	@Size(min = 2, message = "First Name should not be less than 2 Characters.")
	@Size(max = 40, message = "First Name should not be greater than 40 Characters.")
	@NotNull(message = "First Name should not be empty.")
	private String firstName;

	private String middleName;

	@Size(min = 2, message = "Last Name should not be less than 2 Characters.")
	@Size(max = 40, message = "Last Name should not be greater than 40 Characters.")
	@NotNull(message = "Last Name should not be empty.")
	private String lastName;

	@NotNull(message = "Please select a designation.")
	private SystemCodes designation;

	@NotNull(message = "Please select a level.")
	private SystemCodes level;

	private List<ProjectEmployee> projectEmployee;

	public String getFullName() {
		return this.firstName + " " + this.middleName + " " + this.lastName;
	}

	/** Getters and Setters */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SystemCodes getDesignation() {
		return designation;
	}

	public void setDesignation(SystemCodes designation) {
		this.designation = designation;
	}

	public SystemCodes getLevel() {
		return level;
	}

	public void setLevel(SystemCodes level) {
		this.level = level;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public List<ProjectEmployee> getProjectEmployee() {
		return projectEmployee;
	}

	public void setProjectEmployee(List<ProjectEmployee> projectEmployee) {
		this.projectEmployee = projectEmployee;
	}

}
