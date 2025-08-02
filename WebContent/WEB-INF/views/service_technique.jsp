<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from service_technique where site="${sessionScope.site.site}"
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
		<title>SERVICE TECHNIQUE</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">SERVICE TECHNIQUE</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_GE==1}">
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN SERVICE TECHNIQUE
          </button>
          </c:if> 
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>SERVICE</th>
                  <th>DESIGNATION </th>
                  <th>QUANTITE</th>
                  <th>PRIX-U</th>
                  <th>TOTAL</th>                 
                  <th>DATE</th>               
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.service}</td>
                  <td>${mapProp.designation}</td>
                  <td>${mapProp.quantite}</td>
                  <td>${mapProp.prixU}</td>
                  <td>${mapProp.total}</td>
                  <td>${mapProp.date}</td>                                                
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_serviceT?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                   <c:if test="${sessionScope.rolePr.supp_GE==1}">
                   <button onclick="document.location.href='<c:url value="/serviceT?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
                  </c:if> 
                  </td>
               
              </tr>
             </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- /.container-fluid -->
  </div>
  <!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<!-- Footer --><!-- Log on to codeastro.com for more projects -->
 <c:import url="/include/base_footer.jsp"></c:import>
<!-- End of Footer -->
<!-- Modal -->
<div class="modal fade" id="ModalTujuan" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content" style="width:910px;margin-left:-35px;">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN SERVICE TECHNIQUE </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/serviceT"/>" method="post"  class="myFormulaire">
      <div class="form-group row">      
         <div class="col-sm-3 mb-2 mb-sm-0">
         <sql:query var="listServ" dataSource="jdbc/gestions_imm">
	            select * from service 
             </sql:query>
        <label for="platbus" class="">TYPE SERVICE<span class="text-danger ml-2">*</span></label>
       <select name="type" required class="form-control">
		 <option value="<c:out value="${service.service}"/>"selected ><c:out value="${service.service}"/></option>
		 <c:forEach  items="${listServ.rows}" var="mapServ">
	         <option value="<c:out value="${mapServ.service}"/>"> <c:out value="${mapServ.service}"/></option>
		 </c:forEach>							    
		</select>		
        </div>
        <div class="col-sm-9 mb-2 mb-sm-0">
        <label for="platbus" class="">DESIGNATION<span class="text-danger ml-2">*</span></label>
         <input type="text" name="designation" class="form-control" required  value="">
        </div>       
      </div>    
      <div class="form-group row">
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">QUANTITE </label>
	     <input type="text" name="quantite" class="form-control" value="" onkeypress="return onlyNumberKey(event)"/>
       </div>       
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">PRIX UNITAIRE</label>
		  <input type="text" name="prixU" class="form-control" value="" onkeypress="return onlyNumberKey(event)"/>
		</div> 		
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE</label>
		 <input type="date" name="date"   class="form-control" value="">
		</div>
      </div>    
      
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
        <button name="crud" value="AJOUTER" class="btn btn-success">Sauvegarder</button>
      </div>
    </form>
  </div>
</div>
</div>
</div>

 <div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
		</div>
	</div>

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