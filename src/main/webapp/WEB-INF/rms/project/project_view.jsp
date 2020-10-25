<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:main_page>

<jsp:body>
    
    <div class="container">
    
		<div class="card">
			<div class="card-header projects-card">
				Project
			</div>
			<div class="card-body">
				
				<t:message />
				
				<div class="col-sm-10 offset-sm-1">
					<dl class="row">
						<dt class="col-sm-3 pull-right">Project Name</dt>
						<dd class="col-sm-9">${ project.name }</dd>
						<dt class="col-sm-3 pull-right">Description</dt>
						<dd class="col-sm-9">${ project.description }</dd>
					</dl>
				</div>
				
				
			    <ul class="nav nav-tabs col-sm-10 offset-sm-1" id="myTab" role="tablist">
				    <li class="nav-item">
				        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Current Members</a>
				    </li>
				    <li class="nav-item">
				        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Previous Members</a>
				    </li>
				</ul>

				<div class="tab-content mb-4 col-sm-10 offset-sm-1" id="myTabContent">
				    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
						<div class="mt-4 mb-4">
							Current Members
						</div>
						<div id="members">
							
							<!-- List of Project members that are active -->
							<t:project_members_table active="true" viewOnly="true"></t:project_members_table>					
							
						</div>
					</div>
				    <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
						<div class="mt-4 mb-4">
							Previous Members
						</div>
						<div id="previousMembers">
						
							<!-- List of Project members that are not active -->
							<t:project_members_table active="false" viewOnly="true"></t:project_members_table>
				
						</div>
					</div>
				</div>
		
				
				<div class="col-md-4 float-right">
					<a href="/projects/${ project.id }/edit" class="btn btn-primary float-right col-sm-4 offset-sm-1">Edit</a>
					<a href="/projects" class="btn btn-light float-right col-sm-4">Back</a>
				</div>
				
			</div>
		</div>

    </div>
	
</jsp:body>
</t:main_page>