<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<t:main_page>

<jsp:body>
    
    <div class="container">
    
		<div class="card">
			<div class="card-header projects-card">
				Project
			</div>
			<div class="card-body">
				
				<t:message />
				
				<form:form class="col-sm-10 offset-sm-1" method="POST" modelAttribute="project">
					<form:hidden path="id" value="${ id }"/>
				    <div class="form-group row">
				        <label for="name" class="col-sm-2 col-form-label">Project Name</label>
				        <div class="col-sm-10">
				            <form:input type="text" class="form-control text-capitalize" path="name" id="name" placeholder="Project Name" required="true"/>
							<form:errors path="name" class="text-danger"/>
				        </div>
				    </div>
				    <div class="form-group row">
				        <label for="descriptoin" class="col-sm-2 col-form-label">Description</label>
				        <div class="col-sm-10">
				            <form:input type="text" class="form-control" path="description" id="description" placeholder="Description" />
							<form:errors path="description" class="text-danger"/>
				        </div>
				    </div>

					<c:if test = "${ project.id != null }">
				    
					    <ul class="nav nav-tabs pt-4" id="myTab" role="tablist">
						    <li class="nav-item">
						        <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab" aria-controls="home" aria-selected="true">Current Members</a>
						    </li>
						    <li class="nav-item">
						        <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab" aria-controls="profile" aria-selected="false">Previous Members</a>
						    </li>
						</ul>

						<div class="tab-content mb-4" id="myTabContent">
						    <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
								<div class="mt-4 mb-4">
									Current Members
									<i class="material-icons text-primary" id="addMember">add_circle_outline</i>
								</div>
								<div id="members">
									
									<!-- List of Project members that are active -->
									<t:project_members_table active="true" viewOnly="false"></t:project_members_table>					
									
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
					</c:if>

					<div class="col-md-4 float-right">
						<button type="submit" class="btn btn-primary float-right col-sm-4 offset-sm-1">Submit</button>
						<a href="/projects" class="btn btn-light float-right col-sm-4">Back</a>
					</div>
				</form:form>
				
			</div>
		</div>

    </div>
	
	 
	<!-- Member Modal -->
	<t:project_member_modal></t:project_member_modal>
	 
	<!-- Yes or No Modal -->
	<t:confirm_remove_modal itemName="member" targetUrlBeforeId="members/" targetUrlAfterId="/archive"></t:confirm_remove_modal>
	
</jsp:body>
</t:main_page>