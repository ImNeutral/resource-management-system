<!DOCTYPE html>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@tag description="Generic page template to use all common resources"
	pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true"%>
<%@attribute name="footer" fragment="true"%>
<%@attribute name="title" required="false"%>

<c:url var="custom_css" value="/css/style.css" />
<c:url var="bootstrap_css" value="/lib/bootstrap/css/bootstrap.min.css" />
<c:url var="bootstrap_select_css" value="/lib/bootstrap-select/css/bootstrap-select.min.css" />
<c:url var="data_tables_bootstrap_css" value="/lib/data-tables/css/dataTables.bootstrap.min.css" />
<c:url var="material_icon_css" value="https://fonts.googleapis.com/icon?family=Material+Icons" />

<c:url value="/js/script.js" var="custom_js" />
<c:url var="jquery_js" value="/lib/jquery/js/jquery.min.js" />
<c:url var="bootstrap_js" value="/lib/bootstrap/js/bootstrap.bundle.min.js" />
<c:url var="bootstrap_select_js" value="/lib/bootstrap-select/js/bootstrap-select.min.js" />
<c:url var="data_tables_js" value="/lib/data-tables/js/jquery.dataTables.min.js" />
<c:url var="data_tables_bootstrap_js" value="/lib/data-tables/js/dataTables.bootstrap4.min.js" />


<html lang="en">
<head>
<meta charset="UTF-8">

<c:choose>
	<c:when test="${ (title > ' ') }">
		<title>${ title }</title>
	</c:when>
	<c:otherwise>
		<title>
			<spring:eval expression="@environment.getProperty('rms.title')" />
		</title>
	</c:otherwise>
</c:choose>

<link href="${ bootstrap_css }" rel="stylesheet" />
<link href="${ bootstrap_select_css }" rel="stylesheet" />
<link href="${ material_icon_css }" rel="stylesheet" />
<link href="${ data_tables_bootstrap_css }" rel="stylesheet" />
<link href="${ custom_css }" rel="stylesheet" />

<script src="${ jquery_js }"></script>

<jsp:invoke fragment="header" />

</head>
<body>
	<div class="d-flex flex-column flex-md-row align-items-center p-3 px-md-4 mb-3 border-bottom shadow-sm text-light navigation-main">
		<h5 class="my-0 font-weight-normal">
			<spring:eval expression="@environment.getProperty('rms.system-name')" />
		</h5>
		
		<nav class="my-2 my-md-0 ml-md-5">		
			<a class="p-3 text-light navigation-item" id="nav-home" href="/home">Home</a> 
			<a class="p-3 text-light navigation-item" id="nav-project" href="/projects">Project</a>
			<a class="p-3 text-light navigation-item" id="nav-employee" href="/employees">Employee</a>
		</nav>
	</div>
	
	<div id="body">
		<jsp:doBody />
	</div>

	<div id="pagefooter">

		<script src="${ data_tables_js }"></script>
		<script src="${ data_tables_bootstrap_js }"></script>
		<script src="${ bootstrap_js }"></script>
		<script src="${ bootstrap_select_js }"></script>
		<script src="${ custom_js }"></script>
		<jsp:invoke fragment="footer" />
		
		<script>
			$(document).ready(function(){
				// activate current nav
				$(".navigation-item").removeClass("active");
				$("#nav-${ nav_name > ' '?  nav_name : 'home'  }").addClass("active");		
				
				// activate data-tables
				var table = $(".data-tables").DataTable({
					responsive: true,
					columnDefs: [
						   { orderable: false, width: "15%", targets: -1 }
						]
				});
				
				$(function () {
				  $('[data-toggle="tooltip"]').tooltip()
				})
			});
			 
			/*
			** this is to prevent form resubmission on refresh. */
			if ( window.history.replaceState ) {
		        window.history.replaceState( null, null, window.location.href );
		    }
			
		</script>
	</div>
</body>
</html>