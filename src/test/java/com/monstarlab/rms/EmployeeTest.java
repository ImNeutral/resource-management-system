package com.monstarlab.rms;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.monstarlab.rms.model.Employee;
import com.monstarlab.rms.model.ProjectEmployee;
import com.monstarlab.rms.service.EmployeeService;

import ch.qos.logback.classic.Logger;

@RunWith(SpringRunner.class)
@SpringBootTest 
public class EmployeeTest {

	static final Logger LOGGER  = (Logger) LoggerFactory.getLogger(EmployeeTest.class);
	
	@Autowired
	private EmployeeService employeeService;
	
	// SELECT AN EMPLOYEE AND check if alloted hours is correct
	@Test
	public void isCorrectAllotedWorkingHoursTest() {
		Optional<Employee> employeeOpt = employeeService.findById(1L);
		Employee employee = null;
		
		if(employeeOpt.isPresent()) {
			employee = employeeOpt.get();
			Long totalAllotedHours = 0L;
			
			for (ProjectEmployee projectEmployee : employee.getProjectEmployee() ) {
				if(projectEmployee.getActive()) {
					Integer allotedHours = projectEmployee.calculateProjectAllotedHours(4, 2019);
					LOGGER.info("Per Project:");
					LOGGER.info(allotedHours.toString());
					totalAllotedHours += allotedHours;	
				}
			}
			LOGGER.info("All:");
			LOGGER.info( totalAllotedHours.toString() );
		}
	}
	
	// SELECT AN EMPLOYEE AND check if Free allotment is correct
	@Test
	public void isCorrectFreeAllotmentTest() {
		Optional<Employee> employeeOpt = employeeService.findById(1L);
		Employee employee = null;
		
		if(employeeOpt.isPresent()) {
			employee = employeeOpt.get();
			
			LOGGER.info("===========================");
			LOGGER.info( employee.getFreeAllotment(4, 2019).toString() );
			LOGGER.info( employee.getFreeAllotment(7, 2019).toString() );
			LOGGER.info("===========================");
		}
	}
	
	
}
