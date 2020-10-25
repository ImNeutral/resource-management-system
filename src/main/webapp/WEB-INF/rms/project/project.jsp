<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:main_page>


<jsp:body>
    
    <div class="container">
    	<!-- Main Content -->
		<div class="card">
			<div class="card-header projects-card">
				Projects
			</div>
			<div class="card-body">
			
				<t:message />
				
				<div>
					<a href="projects/add" class="btn btn-sm btn-primary float-sm-right">Add Project</a>
				</div>
				
				<table class="data-tables table table-bordered table-hover mt-5">
					<thead class="bg-light">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Project Name</th>
							<th scope="col">Description</th>
							<th scope="col">Actions</th>
						</tr>
					</thead>
					<tbody>
						<c:set var="count" value="1" scope="page" />
						<c:forEach items="${projects}" var="project">
							<tr>
								<th scope="row">${ count }</th>
								<td>${ project.name }</td>
								<td>${ project.description }</td>
								<td>
									<a href="/projects/${ project.id }"><i class="material-icons text-primary" data-toggle="tooltip" data-placement="top" title="List of Members">
											view_headline
									</i></a>
									<a href="/projects/${ project.id }/edit"><i class="material-icons text-warning" data-toggle="tooltip" data-placement="top" title="Edit">
											edit
									</i></a>
									<a><i class="material-icons text-danger" onclick="archive(${ project.id })" data-toggle="tooltip" data-placement="top" title="Archive Project">
										remove_circle_outline
									</i></a>
								</td>
							</tr>
							<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>

    </div>
	
	<!-- Yes or No Modal -->
	<t:confirm_remove_modal itemName="project" targetUrlBeforeId="projects/" targetUrlAfterId="/archive"></t:confirm_remove_modal>
	
</jsp:body>
</t:main_page>