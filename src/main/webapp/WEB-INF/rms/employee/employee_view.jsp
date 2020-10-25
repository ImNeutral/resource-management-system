<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:main_page>

<jsp:body>
    
    <div class="container">
    
		<div class="card">
			<div class="card-header projects-card">
				Employee
			</div>
			<div class="card-body">
				
				<t:message />
				
				<div class="col-sm-10 offset-sm-1">
					<dl class="row">
					<dt class="col-sm-3 pull-right">Name</dt>
					<dd class="col-sm-9">${ employee.fullName }</dd>
					<dt class="col-sm-3 pull-right">Designation</dt>
					<dd class="col-sm-9">${ employee.designation.value }</dd>
					<dt class="col-sm-3 pull-right">Level</dt>
					<dd class="col-sm-9">${ employee.level.value }</dd>
				</dl>
				</div>

				<ul class="nav nav-tabs col-sm-10 offset-sm-1" id="myTab" role="tablist">
				    <li class="nav-item">
				        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Current Projects</a>
				    </li>
				    <li class="nav-item">
				        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Previous Projects</a>
				    </li>
				</ul>


				<div class="tab-content mb-4 col-sm-10 offset-sm-1" id="myTabContent">
				    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
						<div class="mt-4 mb-4">
							Current Projects
						</div>
						<div id="members">
							
							<!-- List of Project members that are not active -->
							<t:employee_projects_table active="true" viewOnly="true" showProjects="true"></t:employee_projects_table>					
							
						</div>
					</div>
				    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
						<div class="mt-4 mb-4">
							Previous Projects
						</div>
						<div id="previousMembers">
						
							<!-- List of Project members that are not active -->
							<t:employee_projects_table active="false" viewOnly="true" showProjects="true"></t:employee_projects_table>
				
						</div>
					</div>
				</div>

				
				<div class="col-md-4 float-right">
					<a href="/employees/${ employee.id }/edit" class="btn btn-primary float-right col-sm-4 offset-sm-1">Edit</a>
					<a href="/employees" class="btn btn-light float-right col-sm-4">Back</a>
				</div>
				
			</div>
		</div>

    </div>
	
</jsp:body>
</t:main_page>