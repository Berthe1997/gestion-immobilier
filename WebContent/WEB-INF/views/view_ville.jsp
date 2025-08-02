<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
		<title>GESTION DES VILLES</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER VILLE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_ville"/>" method="post"  class="myFormulaire">
             
          <div class="card-body">
        <div class="form-group">
        <label for="platbus" class="">LIBELLE PAYS</label>
       <select name="pays" required class="form-control">
		 <option value="<c:out value="${ville.pays}"/>"selected ><c:out value="${ville.pays}"/></option>
		 <c:forEach  items="${listPays.rows}" var="mapPays">
	         <option value="<c:out value="${mapPays.pays}"/>"> <c:out value="${mapPays.pays}"/></option>
		 </c:forEach>							    
		</select>
      </div>
      <div class="form-group">
        <label for="platbus" class="">LIBELLE VILLE</label>
         <input type="text" name="nom" class="form-control" required value="<c:out value="${ville.ville }"/>">
      </div>
      <div class="form-group">
        <label for="seat" class="">COMMENTAIRE</label>
        <textarea rows="5" cols="40" class="form-control" name="commentaire"><c:out
				value="" />${ville.commentaire}</textarea>
      </div>
      <input type="hidden" name="id" value="<c:out value="${ville.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=ville"/>"> Retour</a>
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