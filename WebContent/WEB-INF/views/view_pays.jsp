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
		<title>GESTION DES PAYS</title>
		
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER PAYS   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_pays"/>" method="post"  class="myFormulaire">
             
          <div class="card-body">
           <div class="form-group">
        <label for="platbus" class="">LIBELLE PAYS</label>
       <input type="text" name="nom" class="form-control" required value="<c:out value="${pays.pays }"/>">
      </div>
      <div class="form-group">
        <label for="platbus" class="">CAPITAL PAYS</label>
        <input type="text" name="capital" class="form-control" required value="<c:out value="${pays.capital }"/>">
      </div>
      <div class="form-group">
        <label for="seat" class="">PREFIXE TELEPHONIQUE</label>
       <input type="text" name="prefix" class="form-control" required value="<c:out value="${pays.prefix }"/>" >
      </div>
      <div class="form-group">
        <label for="seat" class="">CONTINENT</label>
        <select name="continent" class="form-control">
		  <option value="<c:out value="${pays.continent }"/>"selected>${pays.continent }</option>
		  <option value="<c:out value="AFRIQUE"/>">AFRIQUE</option> 
		  <option value="<c:out value="ASIE"/>">ASIE</option>
		  <option value="<c:out value="AMERIQUE DU NORD"/>">AMERIQUE DU NORD</option> 
          <option value="<c:out value="AMERIQUE DU SUD"/>">AMERIQUE DU SUD</option>
		  <option value="<c:out value="EUROPE"/>">EUROPE</option>
		  <option value="<c:out value="OCEANIE"/>">OCEANIE</option> 
		  <option value="<c:out value="ATLANTIQUE"/>">ATLANTIQUE</option>							
		</select>
      </div>
      <input type="hidden" name="id" value="<c:out value="${pays.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=pays"/>"> Retour</a> 
             &nbsp;&nbsp;
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
                                    
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