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
		<title> SERVICE TECHNIQUE</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER SERVICE TECHNIQUE </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_serviceT"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
      <div class="form-group row">      
         <div class="col-sm-3 mb-2 mb-sm-0">
         <sql:query var="listServ" dataSource="jdbc/gestions_imm">
	            select * from service 
             </sql:query>
        <label for="platbus" class="">TYPE SERVICE<span class="text-danger ml-2">*</span></label>
       <select name="service" required class="form-control">
		 <option value="<c:out value="${service_technique.service}"/>"selected ><c:out value="${service_technique.service}"/></option>
		 <c:forEach  items="${listServ.rows}" var="mapServ">
	         <option value="<c:out value="${mapServ.service}"/>"> <c:out value="${mapServ.service}"/></option>
		 </c:forEach>							    
		</select>		
        </div>
        <div class="col-sm-9 mb-2 mb-sm-0">
        <label for="platbus" class="">DESIGNATION<span class="text-danger ml-2">*</span></label>
         <input type="text" name="designation" class="form-control" required   value="<c:out value="${service_technique.designation}"/>">
        </div>       
      </div>    
      <div class="form-group row">
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">QUANTITE </label>
	     <input type="text" name="quantite" class="form-control"  value="<c:out value="${service_technique.quantite}"/>" onkeypress="return onlyNumberKey(event)"/>
       </div>       
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">PRIX UNITAIRE</label>
		  <input type="text" name="prixU" class="form-control"  value="<c:out value="${service_technique.prixU}"/>" onkeypress="return onlyNumberKey(event)"/>
		</div> 		
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE</label>
		 <input type="date" name="date"   class="form-control"  value="<c:out value="${service_technique.date}"/>">
		</div>
      </div>    
      <input type="hidden" name="id" value="<c:out value="${service_technique.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=service_technique"/>"> Retour</a>
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