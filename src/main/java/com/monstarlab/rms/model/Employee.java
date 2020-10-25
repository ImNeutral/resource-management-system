package com.monstarlab.rms.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.monstarlab.rms.util.DateUtil;

@Entity
@Table(name = "employees")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "archived")
	private Boolean archived = false;
	
	@ManyToOne
	@JoinColumn(name = "designation")
	private SystemCodes designation;

	@ManyToOne
	@JoinColumn(name = "level")
	private SystemCodes level;

	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER)
	private List<ProjectEmployee> projectEmployee;
	
	public String getFullName() {
		return this.firstName + " " + this.middleName + " " + this.lastName; 
	}
	
	// get free allotment, returns 0.0, 0.25, 0.5, 0.75, 1.0
	public Double getFreeAllotment(Integer month, Integer year) {
		Long totalAllotedHours = 0L;
		Double freeTime = 0d;
		Double freeAllotment = 0d;
		
		for (ProjectEmployee projectEmployee : this.getProjectEmployee() ) {
			if(projectEmployee.getActive()) {
				Integer allotedHours = projectEmployee.calculateProjectAllotedHours(month, 2019);
				totalAllotedHours += allotedHours;	
			}
		}
		
		freeTime = 1.0 - (totalAllotedHours / 160d);
		
		if( freeTime >= 0.0 && freeTime <= 0.19 ) {
			freeAllotment = 0.0;
		} else if( freeTime > 0.19 && freeTime <= 0.44 ) {
			freeAllotment = 0.25;
		} else if( freeTime > 0.44 && freeTime <= 0.69 ) {
			freeAllotment = 0.5;
		} else if( freeTime > 0.69 && freeTime <= 0.94 ){
			freeAllotment = 0.75;
		} else if ( freeTime > 0.94 ) {
			freeAllotment = 1.0;
		}
		
		return freeAllotment;
	}
	
	public List<Double> getFreeAllotment4Months() {
		Calendar c = Calendar.getInstance();
		Integer year = c.get(Calendar.YEAR);
		List<Integer> months = DateUtil.getNextMonths(4);
		List<Double> freeAllotments = new ArrayList<Double>();
		
		for (Integer month : months) {
			freeAllotments.add( this.getFreeAllotment(month, year) );
			month += 1;
		}
		
		return freeAllotments;
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

	public List<ProjectEmployee> getProjectEmployee() {
		return projectEmployee;
	}

	public void setProjectEmployee(List<ProjectEmployee> projectEmployee) {
		this.projectEmployee = projectEmployee;
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

	public Boolean getArchived() {
		return archived;
	}

	public void setArchived(Boolean archived) {
		this.archived = archived;
	}

}
