package com.monstarlab.rms.dto;

import java.util.List;

import javax.validation.constraints.Size;

import com.monstarlab.rms.model.ProjectEmployee;

public class ProjectDto {

    private Long id;
    
	@Size(min = 1, message="Please enter a project name.")
    private String name;

	@Size(min = 10, message="First Name should not be less than 10 Characters.")
    private String description;

	private List<ProjectEmployee> projectEmployee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProjectEmployee> getProjectEmployee() {
		return projectEmployee;
	}

	public void setProjectEmployee(List<ProjectEmployee> projectEmployee) {
		this.projectEmployee = projectEmployee;
	}

	
	

}
