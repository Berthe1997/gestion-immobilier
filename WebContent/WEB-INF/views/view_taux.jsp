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
		<title> GESTIONS DES POURCENTAGES</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER UN POURCENTAGE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_pourcentage"/>" method="post"  class="myFormulaire">
             
          <div class="form-group">
        <label for="platbus" class="">LIBELLE POURCENTAGE</label>
        <select name="pourcentage" required class="form-control">
			<option value="<c:out value="${pourcentages.libelle}"/>" selected><c:out value="${pourcentages.libelle}"/></option>
			<option value="AGENCE">POURCENTAGE AGENCE</option>
			<option value="IMPÔT">POURCENTAGE IMPÔT</option>
		</select>	       
      </div>
      <div class="form-group">
        <label for="platbus" class="">POURCENTAGE</label>
         <input type="text" name="taux" required class="form-control" value="<c:out value="${pourcentages.taux}"/>" onkeypress="return onlyNumberKey(event)">
      </div>
      <div class="form-group">
        <label for="seat" class="">COMMENTAIRE</label>
        <textarea rows="4" cols="40" class="form-control" name="commentaire"><c:out
				value="" />${pourcentages.commentaire}</textarea>
      </div>
     
       <input type="hidden" name="id" value="<c:out value="${pourcentages.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=pourcentage"/>"> Retour</a>
             &nbsp;&nbsp;        
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>          
            
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