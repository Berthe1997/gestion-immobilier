<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listCom" dataSource="jdbc/gestions_imm">
	select * from commune 
</sql:query>
<sql:query var="listVil" dataSource="jdbc/gestions_imm">
	select * from ville 
</sql:query>

<sql:query var="listPays" dataSource="jdbc/gestions_imm">
	select * from pays 
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
		<title>GESTION DES QUARTIERS</title>
		

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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER QUARTIER   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_quartier"/>" method="post"  class="myFormulaire">
             
          <div class="card-body">
          <div class="form-group">
        <label for="platbus" class="">LIBELLE PAYS</label>
        <select name="pays" required class="form-control">
		 <option value="<c:out value="${quartier.pays}"/>"selected ><c:out value="${quartier.pays}"/></option>
		 <c:forEach  items="${listPays.rows}" var="mapPays">
		  <option value="<c:out value="${mapPays.pays}"/>"> <c:out value="${mapPays.pays}"/></option>
		</c:forEach>							    
	  </select>
      </div>
      <div class="form-group">
        <label for="platbus" class="">LIBELLE VILLE </label>
        <select name="ville" required class="form-control">
			<option value="<c:out value="${quartier.ville}"/>"selected ><c:out value="${quartier.ville}"/></option>
			<c:forEach  items="${listVil.rows}" var="mapVil">
			 <option value="<c:out value="${mapVil.ville}"/>"> <c:out value="${mapVil.ville}"/></option>
		    </c:forEach>							    
		</select>
      </div>
      <div class="form-group">
        <label for="seat" class="">LIBELLE COMMUNE</label>
        <select name="commune" required class="form-control">
			<option value="<c:out value="${quartier.commune}"/>"selected ><c:out value="${quartier.commune}"/></option>
			<c:forEach  items="${listCom.rows}" var="mapCom">
			  <option value="<c:out value="${mapCom.commune}"/>"> <c:out value="${mapCom.commune}"/></option>
			</c:forEach>							    
		</select>
      </div>  
      <div class="form-group">
        <label for="seat" class="">LIBELLE QUARTIER</label>
         <input type="text" name="nom" class="form-control" required value="<c:out value="${quartier.quartier}"/>">
      </div>    
      <input type="hidden" name="id" value="<c:out value="${quartier.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=quartier"/>"> Retour</a>
             &nbsp;&nbsp;
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
                                    
            </div>
          </div>
        </form>
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