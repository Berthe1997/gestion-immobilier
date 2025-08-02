<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select a.code,a.site,a.id,a.matricule,a.ans,a.mois,a.montant,l.nom,l.prenom,l.matricule,l.archive 
	from arrieres as a, locataire as l 
	where a.matricule=l.matricule  and a.site="${sessionScope.site.site}" AND l.archive="0"
	
</sql:query>

 <!--
	     select * from locataire where code="${sessionScope.agence.code}" and site="${sessionScope.site.site}"
	    -->


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
		<title>GESTIONS DES ARRIERES</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTIONS DES ARRIERES</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_ari==1}">
           <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN ARRIERE
          </button>  <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=comptabilite"/>"> Retour</a>
        </c:if>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>NOM PRENOM</th>
                  <th>MOIS</th>
                  <th>ANNEE</th> 
                  <th>MONTANT</th>                 
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle"> 
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.nom} ${mapProp.prenom}</td>                
                  <td>${mapProp.mois}</td>
                  <td>${mapProp.ans}</td> 
                  <td>${mapProp.montant}</td>                                              
                  <td align="center">
                  <a onclick="document.location.href='<c:url value="/view_arrieres?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a>                    
                  <c:if test="${sessionScope.rolePr.supp_ari==1}">
                   <button onclick="document.location.href='<c:url value="/arrieres?id=${mapProp.id}&matricule=${mapProp.matricule}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
<div class="modal-content" style="width:610px;margin-left:-35px;">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN ARRIERE </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/arrieres"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-12 mb-8 mb-sm-0">
        <label for="platbus" class="">NOM PRENOM LOCATAIRE</label>
        <sql:query var="listProp" dataSource="jdbc/gestions_imm">
       
	    select * from calendrier_paiement where site="${sessionScope.site.site}" and statut="impaye" limit 1	       
       </sql:query>
        <select id="matricule" name="matricule" class="form-control" onchange="ajax('locataireC')">
			<option value="" selected></option>
			<c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
			  <option value="<c:out value="${mapProp.matricule}"/>"> <c:out value="${mapProp.locataire}"/></option>
			 </c:forEach>											
		</select>
        </div>                 
      </div>
       <div class="form-group row">             
         <div class="col-sm-4 mb-3 mb-sm-0">
        <label for="platbus" class="">ANNEE</label>       
         <select id="ans" name="ans" class="form-control" onchange="ajax('ansC')">
			 <input type="hidden"  id="matri" class="form-control"   value="">						
		</select>
        </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">MOIS</label>	
		  <select id="mois" name="mois"  class="form-control" onchange="ajax('moisC')">
			<input type="hidden"  id="matr" class="form-control"   value="">									
		</select>
		
       </div> 
        <div class="col-sm-4 mb-2 mb-sm-0">
          <label for="platbus" class="">MONTANT LOYER </label>
	      <input type="text"  class="form-control" value=""  id="montant" disabled style="color:red;">
	       <input type="hidden" name="montant" id="mont" class="form-control"   value="">	
        </div> 
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
  
 //=======================================NOMBRE=======================================================//	
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