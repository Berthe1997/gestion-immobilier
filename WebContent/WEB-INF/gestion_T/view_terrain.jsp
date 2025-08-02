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
		<title> TERRAIN</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER TERRAIN </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_terrain"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
        <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LIBELLE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="libelle" class="form-control" required  value="<c:out value="${terrain.libelle}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="adresse" class="form-control" required  value="<c:out value="${terrain.adresse}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SUPERFICIE<span class="text-danger ml-2">*</span></label>
          <input type="text" required  name="superficie" class="form-control" value="<c:out value="${terrain.superficie}"/>">		
        </div>                
      </div>
      <div class="form-group row">   
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="seat" class="">TYPE TERRAIN<span class="text-danger ml-2">*</span></label>
        <select name="type" required class="form-control">
			<option value="<c:out value="${terrain.type}"/>" selected>${terrain.type}</option>
			<option value="Celibataire">Résidentiel</option>
			<option value="Commercial">Commercial</option>
			<option value="Agricole">Agricole</option>
		</select>
        </div>   
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">PROPRIETAIRE</label>
        <input type="text" name="proprietaire" class="form-control" value="<c:out value="${terrain.proprietaire}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT</label>
        <input type="text" name="contact" class="form-control" value="<c:out value="${terrain.contact}"/>">
        </div>                          
      </div>
      <div class="form-group row">  
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">STATUT JURIDIQUE</label>
        <select name="statut_ju" required class="form-control">
			<option value="<c:out value="${terrain.statut_ju}"/>" selected>${terrain.statut_ju}</option>
			<option value="privée">privée</option>
			<option value="public">public</option>
			<option value="indivision">indivision</option>
		</select>  
        </div>  
        <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">LOT</label>
	   <input type="text" name="lot" class="form-control" value="<c:out value="${terrain.lot}"/>">
       </div>        
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">ILOT</label>
       <input type="text" name="ilot" class="form-control" value="<c:out value="${terrain.ilot}"/>">
        </div>
       </div> 
      <div class="form-group row"> 
        <div class="col-sm-3 mb-2 mb-sm-0">
       <label for="platbus" class="">LOTISSEMENT</label>
	   <input type="text" name="lotissement" class="form-control" value="<c:out value="${terrain.lotissement}"/>">
       </div> 
       <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">PRIX PROPRIETAIRE</label>
		  <input type="text" name="prix" class="form-control" value="<c:out value="${terrain.prix}"/>" onkeypress="return onlyNumberKey(event)">
		</div>
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">PRIX GESTIONNAIRE </label>
	     <input type="text" name="prixG" class="form-control" value="<c:out value="${terrain.prixG}"/>" onkeypress="return onlyNumberKey(event)">
       </div> 
       <div class="col-sm-3 mb-2 mb-sm-0">
	        <label for="platbus" class="">DATE ACHAT</label>
	       <input type="date" name="date_achat" class="form-control" value="<c:out value="${terrain.date_achat}"/>">
        </div>                  
      </div>
      <input type="hidden" name="id" value="<c:out value="${terrain.id}"/>">
       <input type="hidden" name="code" value="<c:out value="${terrain.code}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=terrain"/>"> Retour</a>
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