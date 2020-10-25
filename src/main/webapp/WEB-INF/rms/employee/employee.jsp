<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:main_page>

<jsp:body>
    
    <div class="container">
    
		<div class="card">
			<div class="card-header projects-card">
				Employees
			</div>
			<div class="card-body">
			
				<t:message />
				
				<div>
					<a href="employees/add" class="btn btn-sm btn-primary float-sm-right">Add Employee</a>
				</div>
				
					<table class="data-tables table table-bordered table-hover mt-5">
						<thead class="bg-light">
							<tr>
								<th scope="col">#</th>
								<th scope="col">Name</th>
								<th scope="col">Designation</th>
								<th scope="col">Level</th>
								<th scope="col">Action</th>
							</tr>
						</thead>
						<tbody>
							
							<c:set var="count" value="1" scope="page" />
							<c:forEach items="${employees}" var="employee">
							 	<tr>
									<th scope="row">${ count }</th>
									<td>${ employee.fullName }</td>
									<td>${ employee.designation.value }</td>
									<td>${ employee.level.value }</td>
									<td>
										<a href="/employees/${ employee.id }"><i class="material-icons text-primary" data-toggle="tooltip" data-placement="top" title="List Projects">
											view_headline
										</i></a>
										<a href="/employees/${ employee.id }/edit"><i class="material-icons text-warning"  data-toggle="tooltip" data-placement="top" title="Edit">
											edit
										</i></a>
										<a><i class="material-icons text-danger" onclick="archive(${ employee.id })"  data-toggle="tooltip" data-placement="top" title="Archive">
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
	<t:confirm_remove_modal itemName="employee" targetUrlBeforeId="employees/" targetUrlAfterId="/archive"></t:confirm_remove_modal>
	
</jsp:body>

</t:main_page>