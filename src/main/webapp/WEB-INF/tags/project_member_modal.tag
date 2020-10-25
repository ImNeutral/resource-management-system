<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@tag language="java" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<div class="modal fade" id="saveMember" tabindex="-1" role="dialog" aria-labelledby="saveMember" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Member</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <form:form method="POST" action="members/add" modelAttribute="projectEmployee">
	            <div class="modal-body">

					<div>
						<form:hidden path="id" id="memberId"></form:hidden>
						<div class="form-group mx-sm-2 mb-2 ml-2">
							<label>Employee: </label>
							<br/>
							<form:select path="employee" class="selectpicker" required="required">
								<option value="" selected="true" disabled="true">Select Employee</option>
								<form:options items="${employeeList}" itemValue="id" itemLabel="fullName" />
							</form:select>
						</div>
					    
						<div class="form-group mx-sm-2 mb-2 ml-2">
							<label>Allocation: </label>
							<div class="col-xs-4">
							    <input type="number" name="allocation" class="form-control" id="allocation" placeholder="Allocation" step=".25" min=".25" max="1" required />
							</div>
						</div> 
						<div class="form-group mx-sm-2 mb-2 ml-2">
							<label>Date Start: </label>
							<div class="col-xs-4">
							    <input type="date" name="dateFrom" class="form-control" id="dateFrom" required/>
							</div>
						</div> 
						<div class="form-group mx-sm-2 mb-2 ml-2">
							<label>Date End: </label>
							<div class="col-xs-4">
							    <input type="date" name="dateTo" class="form-control" id="dateTo" required/>
							</div>
						</div> 
					</div>

	            </div>
	            <div class="modal-footer">
	                <button type="button" class="btn btn-light" data-dismiss="modal">Cancel</button>
	                <button type="submit" href="" class="btn btn-primary" >Save</button>
	            </div>
            </form:form>
        </div>
    </div>
</div>