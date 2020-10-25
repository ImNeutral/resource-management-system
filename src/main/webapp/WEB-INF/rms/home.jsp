<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<t:main_page>

<jsp:body>
    
<div class="container">
    
		<div class="card">
			<div class="card-header projects-card">
				Available Resources
				
			</div>
			<div class="card-body">
			
				<t:message />
				
				<div>
					<a href="" id="print" class="btn btn-sm btn-primary float-sm-right"  data-toggle="tooltip" data-placement="top" title="Print">
						<i class="material-icons">
							print
						</i> 
					</a>
				</div>
			
				<table class="data-tables table table-bordered table-hover mt-5">
					<thead class="bg-light">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Name</th>
							<th scope="col">Designation</th>
							<th scope="col">Level</th>
							<th scope="col">${ monthNames[months[0] - 1] }</th>
							<th scope="col">${ monthNames[months[1] - 1] }</th>
							<th scope="col">${ monthNames[months[2] - 1] }</th>
							<th scope="col">${ monthNames[months[3] - 1] }</th>
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
								<td> ${ employee.getFreeAllotment4Months()[0] } </td>
								<td> ${ employee.getFreeAllotment4Months()[1] } </td>
								<td> ${ employee.getFreeAllotment4Months()[2] } </td>
								<td> ${ employee.getFreeAllotment4Months()[3] } </td>
							</tr>
							<c:set var="count" value="${count + 1}" scope="page"/>
						</c:forEach> 
						
					</tbody>
				</table>
			
			</div>
		</div>

    </div>
		

</jsp:body>
</t:main_page>