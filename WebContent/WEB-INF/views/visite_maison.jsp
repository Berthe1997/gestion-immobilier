<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listV" dataSource="jdbc/gestions_imm">
	select * from visite_maison  where site="${sessionScope.site.site}"
</sql:query>

<sql:query var="listGes" dataSource="jdbc/gestions_imm">
	select * from gestionnaire where site="${sessionScope.site.site}"
</sql:query>


<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>

   <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
		<!-- Log on to codeastro.com for more projects -->
		<!-- Site Title -->
		<title>VISITE DE MAISON</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTION VISITE DE MAISON</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=comptabilite"/>"> Retour</a>
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UNE VISITE 
          </button>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>NOM AGENT</th>
                  <th>TYPE VISITE</th>
                  <th>MONTANT</th>
                  <th>DATE</th>                  
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
             <c:forEach items="${listV.rows}" var="mapQua" varStatus="boucle"> 
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapQua.agent}</td>
                  <td>${mapQua.type}</td>
                  <td>${mapQua.montant}</td> 
                  <td>${mapQua.date}</td>                                                  
                  <td align="center"> 
                   <button  onclick="document.location.href='<c:url value="/visiteM?id=${mapQua.id}&type=${mapQua.type}&montant=${mapQua.montant}&date=${mapQua.date}&heure=${mapQua.heure}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
                  </td>
               
              </tr>
             </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- /.container-fluid -->
  </div>
  <!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<!-- Footer --><!-- Log on to codeastro.com for more projects -->
 <c:import url="/include/base_footer.jsp"></c:import>
<!-- End of Footer -->
<!-- Modal -->
<div class="modal fade" id="ModalTujuan" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel">AJOUTER UNE VISITE </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/visiteM"/>" method="post"  class="myFormulaire">
      <div class="form-group">
        <label for="platbus" class="">NOM AGENT </label>
        <input type="text" name="agent" class="form-control" required  value="">
      </div>    
      <div class="form-group">
        <label for="seat" class="">TYPE VISITE</label>
       <select name="type" required class="form-control">
			<option value="" selected></option>
			<option value="physique">physique</option>
			<option value="visuelle">visuelle</option>
		</select>
      </div> 
      <div class="form-group">
        <label for="platbus" class="">MONTANT VISITE</label>
        <input type="text" name="montant" class="form-control" required value=""onkeypress="return onlyNumberKey(event)">
      </div> 
      <div class="form-group">
        <label for="seat" class="">DATE VISITE</label>
         <input type="date" name="date"   class="form-control" value="">
      </div>    
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
        <button name="crud" value="AJOUTER" class="btn btn-success">Sauvegarder</button>
      </div>
    </form>
  </div>
</div>
</div>
</div>
<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
 <script type="text/javascript">
 	
	function onlyNumberKey(evt) {
	     
	     // Only ASCII character in that range allowed
	     var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	     if (ASCIICode > 31 && (ASCIICode <48 ||ASCIICode > 57))
	         return false;
	     return true;
	 }
	</script>  
</body>
</html>