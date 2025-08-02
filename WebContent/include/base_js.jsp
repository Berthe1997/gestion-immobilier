<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

    <script src="<c:url value="/assetss/js/jquery.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/assetss/js/bootstrap.min.js" />" type="text/javascript"></script>
	<script src="<c:url value="/assetss/js/newVersion.js" />" type="text/javascript"></script>

<!-- Bootstrap core JavaScript-->
<script src="<c:url value="/assets/backend/vendor/jquery/jquery.min.js" />"></script>
<script src="<c:url value="/assets/backend/vendor/bootstrap/js/bootstrap.bundle.min.js" />"></script> 

<!-- Core plugin JavaScript-->
<script src="<c:url value="/assets/backend/vendor/jquery-easing/jquery.easing.min.js" />"></script> 

<!-- Custom scripts for all pages-->
<script src="<c:url value="/assets/backend/js/sb-admin-2.min.js" />"></script> 

<!-- Page level plugins -->
<script src="<c:url value="/assets/backend/vendor/datatables/jquery.dataTables.min.js" />"></script> 
<script src="<c:url value="/assets/backend/vendor/datatables/dataTables.bootstrap4.min.js" />"></script> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script> 
<!-- Page level custom scripts -->
<script src="<c:url value="/assets/backend/js/demo/datatables-demo.js" />"></script> 
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="<c:url value="/assets/datepicker/dist/js/bootstrap-datepicker.min.js" />"></script> 

<script type="text/javascript">
	$(document).ready(function () {
		$(".preloader").fadeOut();
	});
  
	$(":submit").click(function (e) {
		window.addEventListener("beforeunload", function (event) {
			$(".preloader").show();
		});
	});

</script>
<!-- Modal -->
<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
	aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title" id="exampleModalLabel">A PROPOS DU PROJET</h5>
				<button type="button" class="close" data-dismiss="modal" aria-label="Close">
					<span aria-hidden="true">&times;</span>
				</button>
			</div>
			<div class="modal-body">
				APPLICATION DE GESTION IMMOBILIERE <br>
				Developer Par Berthe Ousmane
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-danger" data-dismiss="modal">fermer</button>
			</div>
		</div>
	</div>
</div>
