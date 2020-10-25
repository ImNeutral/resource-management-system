<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ tag language="java" pageEncoding="ISO-8859-1"%>

<c:if test='${ status == 0 && message > " "}'>
	<div class="alert alert-danger" role="alert">
		${ message }
	</div>
</c:if>
<c:if test='${ status != 0 && message > " "}'>
	<div class="alert alert-primary" role="alert">
		${ message }
	</div>
</c:if>


<c:remove var="message" scope="session" /> 
<c:remove var="status" scope="session" /> 