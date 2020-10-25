<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<t:main_page>

<jsp:body>
    
    <div class="container">
    
		<div class="card">
			<div class="card-header projects-card">
				Employees
			</div>
			<div class="card-body">
				
				<t:message />
				
				<form:form class="col-sm-10 offset-sm-1" method="POST" modelAttribute="employee">
					<form:hidden path="id" value="${ id }"/>
				    <div class="form-group row">
				        <label for="first_name" class="col-sm-2 col-form-label">First Name</label>
				        <div class="col-sm-10">
				            <form:input type="text" class="form-control text-capitalize" path="firstName" id="first_name" placeholder="First Name" required="true"/>
							<form:errors path="firstName" class="text-danger"/>
				        </div>
				    </div>
				    <div class="form-group row">
				        <label for="middle_name" class="col-sm-2 col-form-label">Middle Name</label>
				        <div class="col-sm-10">
				            <form:input type="text" class="form-control text-capitalize" path="middleName" id="middle_name" placeholder="Middle Name" />
				        </div>
				    </div>
				    <div class="form-group row">
				        <label for="last_name" class="col-sm-2 col-form-label">Last Name</label>
				        <div class="col-sm-10">
				            <form:input type="text" class="form-control text-capitalize" path="lastName" id="last_name" placeholder="last Name" required="true" />
							<form:errors path="lastName" class="text-danger"/>
				        </div>
				    </div>
				    <div class="form-group row">
				        <label for="designation" class="col-sm-2 col-form-label">Designation</label>
				        <div class="col-sm-10">
							<form:select path="designation" class="selectpicker" required="required">
								<form:option value="" label="Select Designation" selected="true" disabled="true"/>
								<form:options items="${employeeDesignationList}" itemValue="id" itemLabel="value" />
							</form:select>
							<form:errors path="designation" class="text-danger"/>
				        </div>
				    </div>
				    <div class="form-group row">
				        <label for="level" class="col-sm-2 col-form-label">Level</label>
				        <div class="col-sm-10">
							<form:select path="level" class="selectpicker" required="required">
								<form:option value="" label="Select Level" selected="true" disabled="true"/>
								<form:options items="${employeeLevelList}" itemValue="id" itemLabel="value"/>	
							</form:select>
							<form:errors path="level" class="text-danger"/>
				        </div>
				    </div> 
				    
				    <!-- Add projects here! -->
				    <!-- Make use of tags! -->

					<div class="col-md-4 float-right">
						<button type="submit" class="btn btn-primary float-right col-sm-4 offset-sm-1">Submit</button>
						<a href="/employees" class="btn btn-light float-right col-sm-4">Back</a>
					</div>
				</form:form>
				
			</div>
		</div>

    </div>
	
</jsp:body>
</t:main_page>