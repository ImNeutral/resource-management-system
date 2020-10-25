// Variables Holding an Element - with $ before variable name.
var $addMember = $("#addMember");
var $editMember = $("#editMember ");
var $members = $("#members");
var $saveMemberModal = $("#saveMember");
var $print = $("#print");


// show member form when (+) is clicked
$addMember.on("click", function(){
	$saveMemberModal.modal("toggle");
});

// edit a member data, this transfers member data into the modal
$editMember.on("click", function() {
	$projectMemberDetails = $(this).closest("#project_member_details");
	
	$saveMemberModal.find("#employee").val( $projectMemberDetails.find("#employee").attr("data-value") ).change();
	$saveMemberModal.find("#allocation").val( $projectMemberDetails.find("#allocation").attr("data-value") );
	$saveMemberModal.find("#dateFrom").val( $projectMemberDetails.find("#dateFrom").attr("data-value") );
	$saveMemberModal.find("#dateTo").val( $projectMemberDetails.find("#dateTo").attr("data-value") );
	
	$saveMemberModal.find("#memberId").val( $projectMemberDetails.attr("data-id") );

	$saveMemberModal.modal("toggle");
});

// click print button
$print.on("click", function( event ){
	event.preventDefault();
	window.location.assign("/download-resources");
});