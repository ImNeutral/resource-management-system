<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ attribute name="itemName" required="false" type="java.lang.String"%>
<%@ attribute name="targetUrlBeforeId" required="true" type="java.lang.String"%>
<%@ attribute name="targetUrlAfterId" required="true" type="java.lang.String"%>

<div class="modal fade" id="confirmModal" tabindex="-1" role="dialog" aria-labelledby="confirmModal" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">Confirm</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
				Are you sure you want to archive(remove) this ${ itemName }?
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-light" data-dismiss="modal">No</button>
                <a href="" id="yesConfirm" class="btn btn-primary" >Yes</a>
            </div>
        </div>
    </div>
</div>

<script>
	
	var current_id = 0;
	var $confirmModal = $("#confirmModal");
	var $yesConfirm = $("#yesConfirm");
	
	// called when archive button is clicked
	function archive(id) {
		current_id = id;	
		$yesConfirm.attr("href","${ targetUrlBeforeId }" + id + "${ targetUrlAfterId }");
		$confirmModal.modal('toggle');
	}
		
</script>