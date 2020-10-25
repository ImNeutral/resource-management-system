package com.monstarlab.rms.controller;

import java.util.ArrayList;
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

import com.monstarlab.rms.dto.ProjectDto;
import com.monstarlab.rms.dto.ProjectEmployeeDto;
import com.monstarlab.rms.model.Employee;
import com.monstarlab.rms.model.Project;
import com.monstarlab.rms.model.ProjectEmployee;
import com.monstarlab.rms.service.EmployeeService;
import com.monstarlab.rms.service.ProjectEmployeeService;
import com.monstarlab.rms.service.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {
	private static final String PROJECT_PAGE = "project/project";
	private static final String PROJECT_SAVE_PAGE = "project/project_save";
	private static final String PROJECT_VIEW_PAGE = "project/project_view";
	private static final String CURRENT_NAV = "project";
	
	@Autowired
	private ProjectService projectService;

    @Autowired
    private EmployeeService employeeService;

	@Autowired
	private ProjectEmployeeService projectEmployeeService;
    
	@GetMapping
	public String showProjects(Model model) {
		model.addAttribute("projects",this.projectService.findAllUnarchived());
		model.addAttribute("nav_name", CURRENT_NAV);
		return PROJECT_PAGE;
	}
	
	@GetMapping("/{id}")
	public String showProject(@PathVariable Long id, Model model) {
		String returnPage = "";
		Optional<Project> proj = this.projectService.findById(id);
		
		if(proj.isPresent()) {
			model.addAttribute("project", proj.get());
			model.addAttribute("nav_name", CURRENT_NAV);
			returnPage = PROJECT_VIEW_PAGE;
		} else {
			returnPage = this.showProjects(model);
		}
		return returnPage;
	}
	
	@GetMapping("/add")
	public String createProject(Model model) {
		ProjectDto proj = new ProjectDto();
		ProjectEmployee projectEmployee = new ProjectEmployee();
		
		model.addAttribute("project", proj);
		model.addAttribute("nav_name", CURRENT_NAV);
		model.addAttribute("projectEmployee", projectEmployee);
		return PROJECT_SAVE_PAGE;
	}
	
	@PostMapping("/add")
	public String storeProject(@Valid @ModelAttribute("project") ProjectDto projDto, 
				BindingResult result, Model model, HttpSession session) {
		return this.saveProject(projDto, result, model, session);
	}
	
	@GetMapping("/{id}/edit")
	public String editProject(@PathVariable Long id, Model model) {
		String returnPage = "";
		Optional<Project> proj = this.projectService.findById(id);
		ProjectEmployee projectEmployee = new ProjectEmployee();
		
		if(proj.isPresent()) {
			model.addAttribute("project", proj.get());
			model.addAttribute("projectEmployee", projectEmployee);
			model.addAttribute("nav_name", CURRENT_NAV);
			returnPage = PROJECT_SAVE_PAGE;
		} else {
			returnPage = this.showProjects(model);
		}
		
		return returnPage;
	}

	@PostMapping("/{id}/edit")
	public String updateProject(@PathVariable Long id, @Valid @ModelAttribute("project") ProjectDto projDto, 
									BindingResult result, Model model, HttpSession session) {
		return this.saveProject(projDto, result, model, session);
	}
	
	@PostMapping("/{id}/members/add")
	public String addProjectMember(@PathVariable Long id, @Valid @ModelAttribute("projectEmployee") ProjectEmployeeDto projectEmployeeDto, 
									BindingResult result, Model model, HttpSession session) {
		Optional<Project> project = this.projectService.findById(id);
		List<ProjectEmployee> projectEmployees = new ArrayList<ProjectEmployee>();
		ProjectEmployee projectEmployee = this.transferProjectEmployee(projectEmployeeDto);
		String returnString = "redirect:/projects/" + id.toString() + "/edit";
		
		if(project.isPresent()) {
			Project proj = project.get();
			projectEmployee.setProject(proj);
			projectEmployeeService.save(projectEmployee);
			
			if(proj.getId() != null) {	
				session.setAttribute("message", "Successfully added new member.");
			} else {
				session.setAttribute("message", "Successfully updated member.");
			}
			
			session.setAttribute("status", "1");
		} else {
			session.setAttribute("message", "Failed to add new member.");
			session.setAttribute("status", "0");
		}
		return returnString;
	}

	@GetMapping("/{id}/members/{memberId}/archive")
	public String archiveMember(@PathVariable Long id, @PathVariable Long memberId, Model model, HttpSession session) {
		Optional<ProjectEmployee> projEm = this.projectEmployeeService.findById(memberId);
		String returnString = "redirect:/projects/" + id.toString() + "/edit";		
		
		if(projEm.isPresent()) {
			ProjectEmployee projectEmployee = projEm.get();
			projectEmployee.setActive(false);
			this.projectEmployeeService.save(projectEmployee);
			session.setAttribute("message", "Successfully archived member.");
			session.setAttribute("status", "1");
		}
		
		return returnString;
	}

	
	@GetMapping("/{id}/archive")
	public String archiveProject(@PathVariable Long id, Model model, HttpSession session) {
		Optional<Project> proj = this.projectService.findById(id);
		
		if(proj.isPresent()) {
			Project project = proj.get();
			project.setArchived(true);
			this.projectService.save(project);
			session.setAttribute("message", "Successfully archived project.");
			session.setAttribute("status", "1");
		}
		
		return "redirect:/projects";
	}
	
	// this method will be called by storeProject and updateProject, they do the same.
	public String saveProject( ProjectDto projDto, BindingResult result, Model model, HttpSession session) {
		String returnPage = "";
		ProjectEmployee projectEmployee = new ProjectEmployee();
		
		model.addAttribute("project", projDto);
		model.addAttribute("projectEmployee", projectEmployee);
		model.addAttribute("nav_name", CURRENT_NAV);
		if(result.hasErrors()) {
			if(projDto.getId() != null) {
				session.setAttribute("message", "Failed to update project.");	
			} else {
				session.setAttribute("message", "Failed to add project.");
			}
			session.setAttribute("status", "0");			
			returnPage = PROJECT_SAVE_PAGE;
		} else {
			Project proj = this.transferProject(projDto);
			this.projectService.save(proj);
			
			session.setAttribute("status", "1");
			
			if(projDto.getId() != null) {
				session.setAttribute("message", "Successfully updated project.");
				returnPage = PROJECT_SAVE_PAGE;	
			} else {
				session.setAttribute("message", "Successfully added project.");
				returnPage = this.createProject(model);
			} 
		}
		return returnPage;
	}
	
	// transfer projectDTO to project object.
	public Project transferProject(ProjectDto projDto) {
		Project proj = new Project();
		proj.setId(projDto.getId());
		proj.setName(projDto.getName());
		proj.setDescription(projDto.getDescription());
		return proj;
	}
	
	// transfer projectEMployeeDto to projectEmployee object
	public ProjectEmployee transferProjectEmployee(ProjectEmployeeDto projectEmployeeDto) {
		ProjectEmployee projectEmployee = new ProjectEmployee();
		projectEmployee.setId(projectEmployeeDto.getId());
		projectEmployee.setEmployee(projectEmployeeDto.getEmployee());
		projectEmployee.setProject(projectEmployeeDto.getProject());
		projectEmployee.setAllocation(projectEmployeeDto.getAllocation());
		projectEmployee.setDateFrom(projectEmployeeDto.getDateFrom());
		projectEmployee.setDateTo(projectEmployeeDto.getDateTo());
		
		return projectEmployee;
	}
	
	@ModelAttribute("employeeList")
    public List<Employee> employeeLevelList() {
    	List<Employee> list = this.employeeService.findAllUnarchived();
        return list;
    }
}
