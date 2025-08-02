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
		<title>CLIENT</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER CLIENT   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_bien_M"/>" method="post"  class="myFormulaire">            
           <div class="card-body">                 
		      <div class="form-group row">
		        <div class="col-sm-4 mb-2 mb-sm-0">
		          <label for="platbus" class="">LIBELLE BIEN<span class="text-danger ml-2">*</span></label>
		         <input type="text" name="bien" class="form-control" required  value="<c:out value="${bien_meuble.bien}"/>">
		        </div>
		       <div class="col-sm-4 mb-2 mb-sm-0">
		        <label for="platbus" class="">TYPE BIEN<span class="text-danger ml-2">*</span></label>
		          <select name="type" class="form-control">
					<option value="<c:out value="${bien_meuble.type}"/>" selected></option>
					<option value="Villa">Villa</option>
					<option value="Appartement">Appartement</option>			
					<option value="Studio">Studio</option>
					<option value="Chambre">Chambre</option>
				  </select>
		        </div>
		         <div class="col-sm-4 mb-2 mb-sm-0">
		          <label for="platbus" class="">ADREESE<span class="text-danger ml-2">*</span></label>
		         <input type="text" name="adresse" class="form-control" required  value="<c:out value="${bien_meuble.adresse}"/>">
		        </div>            
		      </div>    
		      <div class="form-group row">
		       <div class="col-sm-4 mb-2 mb-sm-0">
		        <label for="platbus" class="">STATUT <span class="text-danger ml-2">*</span></label>
		        <select name="statut" class="form-control">
					<option value="<c:out value="${bien_meuble.statut}"/>" selected></option>
					<option value="Libre">Libre</option>
					<option value="Occupé">Occupé</option>			
					<option value="En maintenance">En maintenance</option>
					<option value="Réservé">Réservé</option>
				  </select>
		        </div> 
		         <div class="col-sm-4 mb-2 mb-sm-0">
		         <label for="platbus" class="">LOYER NUIT</label>
			     <input type="text" name="loyerN" class="form-control" value="<c:out value="${bien_meuble.loyer_nuit}"/>" onkeypress="return onlyNumberKey(event)">
		       </div>       		
				<div class="col-sm-4 mb-4 mb-sm-0">
		         <label for="platbus" class="">CAUTION</label>
				   <input type="text" name="caution" class="form-control" value="<c:out value="${bien_meuble.caution}"/>" onkeypress="return onlyNumberKey(event)">
				</div>					
		      </div> 
		      <div class="form-group row">   
		        <div class="col-sm-4 mb-4 mb-sm-0">
		           <label for="seat" class="">DESCRIPTION BIEN</label>
		            <textarea rows="3" cols="40" class="form-control" name="description">
						<c:out	value="" /> ${bien_meuble.description}</textarea>
		           </div> 
		        <div class="col-sm-4 mb-4 mb-sm-0">
		           <label for="platbus" class="">DATE CREATION</label>
				   <input type="date" name="" class="form-control" value="<c:out value="${bien_meuble.date_ajout}"/>">
				</div>
		      </div> 
    
        </div>
      <input type="hidden" name="id" value="<c:out value="${bien_meuble.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=bienM"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_b==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
              </c:if>  
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