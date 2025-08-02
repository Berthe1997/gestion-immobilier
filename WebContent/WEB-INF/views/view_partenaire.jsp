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
		<title> PARTENAIRE</title>
		
	
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
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER PARTENAIRE </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_partenaire"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
       <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="<c:out value="${partenaire.nom}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="prenom" class="form-control" required  value="<c:out value="${partenaire.prenom}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">TYPE PARTENAIRE<span class="text-danger ml-2">*</span></label>
        <select name="type" required class="form-control">
			<option value="<c:out value="${partenaire.type}"/>" selected>${partenaire.type}</option>
			<option value="FOURNISSEUR">FOURNISSEUR</option>
			<option value="CLIENT">CLIENT</option>
			<option value="DISTRIBUTEUR">DISTRIBUTEUR</option>
		</select>		
        </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">ADRESSE</label>
		 <input type="text" name="adresse" class="form-control" value="<c:out value="${partenaire.adresse}"/>">
		</div>
      </div>    
      <div class="form-group row">
         <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="<c:out value="${partenaire.email}"/>">
       </div>       
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">TELEPHONE</label>
		  <input type="text" name="tel" class="form-control" value="<c:out value="${partenaire.tel}"/>">
		</div> 
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">SITE WEB</label>
		 <input type="text" name="site_web" class="form-control" value="<c:out value="${partenaire.site_web}"/>">
		</div>
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE DE CREATION</label>
		 <input type="date" name="date_crea"   class="form-control" value="<c:out value="${partenaire.date_crea}"/>">
		</div>
      </div>    
      <input type="hidden" name="id" value="<c:out value="${partenaire.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=partenaire"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_GE==1}">
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