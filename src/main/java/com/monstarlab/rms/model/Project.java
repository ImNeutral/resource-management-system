package com.monstarlab.rms.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projects")
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    
    @Column(name="description")
    private String description;
    
    @Column(name="arcived")
    private Boolean archived = false;

	@OneToMany(mappedBy = "project")
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

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}
	
}
