<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="active" required="true" type="java.lang.Boolean"%>
<%@ attribute name="viewOnly" required="true" type="java.lang.Boolean"%>

<table class="table table-bordered table-hover">
	<thead class="bg-light">
		<tr>
			<th scope="col">#</th>
			<th scope="col">Name</th>
			<th scope="col">Allocation</th>
			<th scope="col">Start Date</th>
			<th scope="col">End Date</th>
			
			 <c:if test = "${ viewOnly != true }">
				<th scope="col">Action</th>
			 </c:if>
		</tr>
	</thead>
	<tbody>
		
		<c:set var="count" value="1" scope="page" />
		<c:forEach items="${ project.projectEmployee }" var="projectEmployee">
			 <c:if test = "${ projectEmployee.active == active}">
			 	<tr id="project_member_details" data-id="${ projectEmployee.id }">
					<th scope="row">${ count }</th>
					<td id="employee" data-value="${ projectEmployee.employee.id }">${ projectEmployee.employee.fullName }</td>
					<td id="allocation" data-value="${ projectEmployee.allocation }">${ projectEmployee.allocation }</td>
					<td id="dateFrom" data-value="${ projectEmployee.dateFrom }">${ projectEmployee.dateFrom }</td>
					<td id="dateTo" data-value="${ projectEmployee.dateTo }">${ projectEmployee.dateTo }</td>
					
					 <c:if test = "${ viewOnly != true }">
						<td>
							<a ><i class="material-icons text-warning" id="editMember">edit</i></a>
							<a><i class="material-icons text-danger" onclick="archive(${ projectEmployee.id })">remove_circle_outline</i></a>
						</td>
					</c:if>
				</tr>
				<c:set var="count" value="${count + 1}" scope="page"/>
			</c:if>
		</c:forEach> 
		
	</tbody>
</table>	