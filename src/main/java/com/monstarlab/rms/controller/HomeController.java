package com.monstarlab.rms.controller;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.monstarlab.rms.model.Employee;
import com.monstarlab.rms.service.EmployeeService;
import com.monstarlab.rms.util.DateUtil;
import com.monstarlab.rms.util.JasperReportUtil;

import net.sf.jasperreports.engine.JRException;

@Controller
@RequestMapping({"/home", "/"})
public class HomeController {

	private static final String CURRENT_NAV = "home";
	
	@Autowired
    private EmployeeService employeeService;
	
	@GetMapping
	public String getAllProjects(Model model) {
		Calendar c = Calendar.getInstance();
		Integer year = c.get(Calendar.YEAR);
		
		model.addAttribute("employees", this.employeeService.findByUnArchivedOrderByIdAsc());
		model.addAttribute("year", year);
		model.addAttribute("months", DateUtil.getNextMonths(4));
		model.addAttribute("monthNames", DateUtil.getMonthNames());
		model.addAttribute("nav_name", CURRENT_NAV);

		return "home";
	}
	
	@GetMapping("/download-resources")
	public void downloadResources(HttpServletResponse response) throws JRException, IOException {
		Collection<Employee> employees = this.employeeService.findByUnArchivedOrderByIdAsc();
		
		JasperReportUtil.generate("resources", employees, response);
	}

}
