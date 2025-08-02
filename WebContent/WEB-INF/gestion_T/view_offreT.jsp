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
		<title> OFFRE TERRAIN</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER OFFRE TERRAIN </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_offreT"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
       <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NATURE BIEN<span class="text-danger ml-2">*</span></label>
         <select class="form-control" required name="nature">
		   <option value="<c:out value="${offre_terrain.nature}"/>" selected><c:out value="${offre_terrain.nature}"/></option>
		  <option value="appartement">Appartement</option>
		  <option value="entrepôt">Entrepôt</option>		 
		  <option value="villa">Villa</option>
		  <option value="terrain">Terrain</option>
		  <option value="magasin">Magasin</option>
		  <option value="bureau">Bureau</option>
		</select>
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LIEU<span class="text-danger ml-2">*</span></label>
         <input type="text" name="lieu" class="form-control" required  value="<c:out value="${offre_terrain.lieu}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SUPERFICIE<span class="text-danger ml-2">*</span></label>
          <input type="text" required  name="superficie" class="form-control" value="<c:out value="${offre_terrain.superficie}"/>">		
        </div>                
      </div>      
      <div class="form-group row">         
        <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">LOT</label>
	   <input type="text" name="lot" class="form-control" value="<c:out value="${offre_terrain.lot}"/>">
       </div>        
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">ILOT</label>
       <input type="text" name="ilot" class="form-control" value="<c:out value="${offre_terrain.ilot}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">PRIX</label>
		  <input type="text" name="prix" class="form-control" value="<c:out value="${offre_terrain.prix}"/>" onkeypress="return onlyNumberKey(event)">
		</div>
      </div>
     <div class="form-group row"> 
        <div class="col-sm-3 mb-2 mb-sm-0">
       <label for="platbus" class="">CARACTERISTIQUE</label>
	    <textarea rows="5" cols="40" class="form-control" name="caracteristique"><c:out
				value="" />${offre_terrain.caracteristique}</textarea>
       </div>               
       <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">DOCUMENT </label>
		  <input type="text" name="document" class="form-control" value="<c:out value="${offre_terrain.document}"/>">
		</div>
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE DE CREATION</label>
	       <input type="date" name="date_ajout" class="form-control" value="<c:out value="${offre_terrain.date_ajout}"/>">
       </div>  
        <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">STATUT</label>
		 <input type="checkbox" class="form-control"  name="statut" ${offre_terrain.statut==1?'checked':''} value="1"  />
       </div>	                   
      </div>
      <input type="hidden" name="id" value="<c:out value="${offre_terrain.id}"/>">
       <input type="hidden" name="num_offre" value="<c:out value="${offre_terrain.num_offre}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=offre_terrain"/>"> Retour</a>
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