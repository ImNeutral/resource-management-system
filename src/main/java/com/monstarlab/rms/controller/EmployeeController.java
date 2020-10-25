package com.monstarlab.rms.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monstarlab.rms.dto.EmployeeDto;
import com.monstarlab.rms.model.Employee;
import com.monstarlab.rms.model.SystemCodes;
import com.monstarlab.rms.service.EmployeeService;
import com.monstarlab.rms.service.SystemCodesService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	
	private static final String EMPLOYEE_PAGE = "employee/employee";
	private static final String EMPLOYEE_SAVE_PAGE = "employee/employee_save";
	private static final String EMPLOYEE_VIEW_PAGE = "employee/employee_view";
	private static final String CURRENT_NAV = "employee";
	
    @Autowired
    private EmployeeService employeeService;
    
    @Autowired
    private SystemCodesService systemCodesService;
	
	@GetMapping
	public String showEmployees(Model model) {
		model.addAttribute("employees", this.employeeService.findAllUnarchived());
		model.addAttribute("nav_name", CURRENT_NAV);
		return EMPLOYEE_PAGE;
	}
	
	@GetMapping("/{id}")
	public String showEmployee(@PathVariable Long id, Model model) {
		String returnPage = "";
		Optional<Employee> emp = this.employeeService.findById(id);
		
		if(emp.isPresent()) {
			model.addAttribute("employee", emp.get());
			model.addAttribute("nav_name", CURRENT_NAV);
			returnPage = EMPLOYEE_VIEW_PAGE;
		} else {
			returnPage = this.showEmployees(model);
		}
		
		return returnPage;
	}
	
	@GetMapping("/{id}/edit")
	public String editEmployee(@PathVariable Long id, Model model) {
		String returnPage = "";
		Optional<Employee> emp = this.employeeService.findById(id);
		
		if(emp.isPresent()) {
			model.addAttribute("employee", emp.get());
			model.addAttribute("nav_name", CURRENT_NAV);
			returnPage = EMPLOYEE_SAVE_PAGE;
		} else {
			returnPage = this.showEmployees(model);
		}
		
		return returnPage;
	}

	@PostMapping("/{id}/edit")
	public String updateEmployee(@PathVariable Long id, @Valid @ModelAttribute("employee") EmployeeDto empDto, 
									BindingResult result, Model model, HttpSession session) {
		return this.saveEmployee(empDto, result, model, session);
	}
	
	@GetMapping("/add")
	public String createEmployee(Model model) {
		EmployeeDto emp = new EmployeeDto();
		model.addAttribute("employee", emp);
		model.addAttribute("nav_name", CURRENT_NAV);
		return EMPLOYEE_SAVE_PAGE;
	}
	
	@PostMapping("/add")
	public String storeEmployee(@Valid @ModelAttribute("employee") EmployeeDto empDto, 
				BindingResult result, Model model, HttpSession session) {
		return this.saveEmployee(empDto, result, model, session);
	}
	
	@GetMapping("/{id}/archive")
	public String getEmployee(@PathVariable Long id, Model model, HttpSession session) {
		Optional<Employee> emp = this.employeeService.findById(id);
		
		if(emp.isPresent()) {
			Employee employee = emp.get();
			employee.setArchived(true);
			this.employeeService.save(employee);
			session.setAttribute("message", "Successfully archived employee.");
			session.setAttribute("status", "1");
		}
		
		return "redirect:/employees";
	}
	
	// this method will be called by storeEmployee and updateEmployee, they do the same.
	public String saveEmployee( EmployeeDto empDto, BindingResult result, Model model, HttpSession session) {
		String returnPage = "";
		model.addAttribute("employee", empDto);
		model.addAttribute("nav_name", CURRENT_NAV);
		if(result.hasErrors()) {
			if(empDto.getId() != null) {
				session.setAttribute("message", "Failed to update employee.");	
			} else {
				session.setAttribute("message", "Failed to add employee.");
			}
			session.setAttribute("status", "0");			
			returnPage = EMPLOYEE_SAVE_PAGE;
		} else {
			Employee emp = this.transferEmployee(empDto);
			this.employeeService.save(emp);
			
			session.setAttribute("status", "1");
			
			if(empDto.getId() != null) {
				session.setAttribute("message", "Successfully updated employee.");
				returnPage = EMPLOYEE_SAVE_PAGE;	
			} else {
				session.setAttribute("message", "Successfully added employee.");
				returnPage = this.createEmployee(model);
			} 
		}
		return returnPage;
	}
	
	// transfer employeeDTO to employee object.
	public Employee transferEmployee(EmployeeDto empDto) {
		Employee emp = new Employee();
		emp.setId(empDto.getId());
		emp.setFirstName(empDto.getFirstName());
		emp.setMiddleName(empDto.getMiddleName());
		emp.setLastName(empDto.getLastName());
		emp.setDesignation(empDto.getDesignation());
		emp.setLevel(empDto.getLevel());
		return emp;
	}
	
    @ModelAttribute("employeeDesignationList")
    public List<SystemCodes> employeeDesignationList() {
    	List<SystemCodes> designationList = this.systemCodesService.findByCategory("DESIGNATION");
        return designationList;
    }

    @ModelAttribute("employeeLevelList")
    public List<SystemCodes> employeeLevelList() {
    	List<SystemCodes> levelList = this.systemCodesService.findByCategory("LEVEL");
        return levelList;
    }
}
