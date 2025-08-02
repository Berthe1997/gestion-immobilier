<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<title>GESTION DES CAUTIONS</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTION DES CAUTIONS </h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_PO==1}">
            <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN POURCENTAGE 
          </button>
         </c:if>         
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>SITES</th> 
                  <th>TOTAL MOIS</th>                              
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
               <sql:query var="pourcentage" dataSource="jdbc/gestions_imm">
					select * from  gestion_caution where code="${sessionScope.agence.code}"
				 </sql:query>
               <c:forEach items="${pourcentage.rows}" var="lister" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${lister.site}</td>
                  <td>${lister.mois} MOIS</td>                                                        
                  <td align="center">
                   <c:if test="${sessionScope.rolePr.supp_PO==1}">
                   <button  onclick="document.location.href='<c:url value="/caution?id=${lister.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
                  </c:if>  
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
    <h5 class="modal-title text-success" id="exampleModalLabel">AJOUTER UNE CAUTION</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form  action="<c:url value="/caution"/>" method="post"  class="myFormulaire">
      <div class="form-group">
        <label for="platbus" class="">LIBELLE AGENCE</label>
          <sql:query var="Pr" dataSource="jdbc/gestions_imm">
				select * from agence   
		 </sql:query>
		<select id="code" name="code" class="form-control" onchange="ajax('agence')">
		    <option value="" selected></option>									 
			<c:forEach items="${Pr.rows}" var="mapPr" varStatus="boucle">
				<option value="${mapPr.code}">${mapPr.agence}</option>
			</c:forEach>
		 </select>	         
      </div>
      <div class="form-group">
        <label for="platbus" class="">LIBELLE SITE</label>
         <select id="sites" name="sit" class="form-control">
									
		</select>
      </div>
      <div class="form-group">
        <label for="seat" class="">NOMBRE DE MOIS</label>
        <select name="mois" required class="form-control">		
			<option value="" selected></option>
			<option value="1">1 MOIS</option>
			<option value="2">2 MOIS</option>			
			<option value="3">3 MOIS</option>  
		    <option value="4">4 MOIS</option> 	
			<option value="5">5 MOIS</option>
			<option value="6">6 MOIS</option>
		</select>
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

<div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
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