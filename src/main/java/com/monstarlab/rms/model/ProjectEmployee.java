package com.monstarlab.rms.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.ZoneId;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.monstarlab.rms.util.DateUtil;

@Entity
@Table(name = "project_employee")
public class ProjectEmployee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id")
    private Employee employee;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

	@Column(name="date_from")
	private Date dateFrom;
	
	@Column(name="date_to")
	private Date dateTo;
	
	@Column(name="allocation", precision=10, scale=2)
	private BigDecimal allocation;
	
	@Column(name="active")
	private Boolean active = true;
	
	public Integer calculateProjectAllotedHours(Integer month, Integer year) {
		Long workingDays = 0L;
		Integer totalHoursForThisProject = 0;
		
		try {
			LocalDate startDate = DateUtil.convertStartDate(month, year);
			LocalDate endDate = DateUtil.convertEndDate(month, year);
			LocalDate dateFromLocal = this.dateFrom.toLocalDate();
			LocalDate dateToLocal = this.dateTo.toLocalDate();
			
			if( DateUtil.isInsideDates(dateFromLocal, startDate, endDate) 
					|| DateUtil.isInsideDates(dateToLocal, startDate, endDate) ) {
				// start and end date to check working dates between the two
				Date start = this.dateFrom;
				Date end = this.dateTo;
				
				if( this.dateFrom.toLocalDate().isBefore( startDate ) ) {
					start = Date.valueOf( startDate );
				}
				
				if( this.dateTo.toLocalDate().isAfter( endDate ) ) {
					end = Date.valueOf( endDate );;
				}
				
				workingDays = DateUtil.workingDays(start, end);
				workingDays += 1;
				
				totalHoursForThisProject = (int) (( workingDays.doubleValue() * 8.0) * this.allocation.doubleValue());
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return totalHoursForThisProject;
	}
	
	/* Getters and Setters */
	
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

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public BigDecimal getAllocation() {
		return allocation;
	}

	public void setAllocation(BigDecimal allocation) {
		this.allocation = allocation;
	}
	
}