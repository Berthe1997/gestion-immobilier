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
		<title> GESTIONS DES ARRIERES</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
           
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <div class="container-fluid">
      <!-- Page Heading -->
      <!-- Log on to codeastro.com for more projects -->
      <!-- Basic Card Example -->
      <div class="card shadow mb-4" style="width:600px;margin-left:200px;">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER UN ARRIERE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_arrieres"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
	      <div class="form-group row">
	       <div class="col-sm-12 mb-8 mb-sm-0">
	        <label for="platbus" class="">NOM PRENOM LOCATAIRE</label>
	        <sql:query var="listProp" dataSource="jdbc/gestions_imm">
		     select * from locataire where code="${sessionScope.agence.code}" and site="${sessionScope.site.site}"
	       </sql:query>
	        <select name="matricule" class="form-control">
				<option value="<c:out value="${arrieres.matricule}"/>" selected><c:out value="${arrieres.locataire.nom} ${arrieres.locataire.prenom}"/></option>
				<c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
				  <option value="<c:out value="${mapProp.matricule}"/>"> <c:out value="${mapProp.nom} ${mapProp.prenom}"/></option>
				 </c:forEach>											
			</select>
	        </div>                 
	      </div>
	       <div class="form-group row">             
	         <div class="col-sm-4 mb-3 mb-sm-0">
	        <label for="platbus" class="">ANNEE</label>
	         <input type="text" name="ans" class="form-control" value="<c:out value="${arrieres.ans}"/>" onkeypress="return onlyNumberKey(event)" maxlength="4">
	        </div>
	        <div class="col-sm-4 mb-2 mb-sm-0">
	        <label for="platbus" class="">MOIS</label>	
			  <select name="mois" class="form-control">
				<option value="<c:out value="${arrieres.mois}"/>" selected><c:out value="${arrieres.mois}"/></option>
				<option value="JANVIER">JANVIER</option>
				<option value="FEVRIER">FEVRIER</option>
				<option value="MARS">MARS</option>
				<option value="AVRIL">AVRIL</option>
				<option value="MAI">MAI</option>
				<option value="JUIN">JUIN</option>
				<option value="JUILLET">JUILLET</option>
				<option value="AÔUT">AÔUT</option>
				<option value="SEPTEMBRE">SEPTEMBRE</option>
				<option value="OCTOBRE">OCTOBRE</option>
				<option value="NOVEMBRE">NOVEMBRE</option>
				<option value="DECEMBRE">DECEMBRE</option>									
			</select>
	       </div> 
	        <div class="col-sm-4 mb-2 mb-sm-0">
	          <label for="platbus" class="">MONTANT LOYER </label>
		      <input type="text" name="montant" class="form-control" value="<c:out value="${arrieres.montant}"/>" onkeyup="montantSaisi('saisi');" id="mttSaisi">
	        </div> 
	      </div>
     
       <input type="hidden" name="id" value="<c:out value="${arrieres.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=gestion_arrieres"/>"> Retour</a>
             &nbsp;&nbsp;
         <c:if test="${sessionScope.rolePr.modif_ari==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>      
             </c:if>      
            </div>
           </form>
          </div>       
      </div>
           	     
    </div>
    
      
     <div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
		</div>
	</div>
   
   <c:import url="/include/base_footer.jsp"></c:import>  

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
</body>
</html>