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
		<title>GESTIONS DES SERVICE</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTIONS DES SERVICE</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_SV==1}">
           <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
            AJOUTER UN SERVICE
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
                  <th>TYPE SERVICE</th>                    
                  <th>STATUT</th>
                  <th>MONTANT</th>               
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
               <sql:query var="Service" dataSource="jdbc/gestions_imm">
					select * from service where site="${sessionScope.site.site}"
				 </sql:query>
            <c:forEach items="${Service.rows}" var="mapService" varStatus="boucle"> 
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapService.service}</td>                
                  <td>${mapService.type_service}</td>
                  <td>${mapService.statut}</td>                                         
                  <td> ${mapService.montant}</td>                                                 
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_service?id=${mapService.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                 <c:if test="${sessionScope.rolePr.supp_SV==1}">
                   <button onclick="document.location.href='<c:url value="/service?id=${mapService.id}&matricule=${mapService.matricule}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
<div class="modal-content" style="width:610px;margin-left:-35px;">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN SERVICE </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/service"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-6 mb-3 mb-sm-0">
        <label for="platbus" class="">SERVICE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="services" class="form-control" required  value="">
        </div>
         <div class="col-sm-6 mb-3 mb-sm-0">
        <label for="platbus" class="">TYPE SERVICE</label>
		 <select name="type" class="form-control">
			<option value="${service.type}" selected>${service.type}</option>
			<option value="Facultatif">Facultatif</option>
			<option value="Obligatoire">Obligatoire</option> 								
		</select>
       </div>       
      </div>
      <div class="form-group row">      
       <div class="col-sm-6 mb-3 mb-sm-0">
        <label for="platbus" class="">STATUT</label>
         <select class="form-control" name="statut" required>
			 <option value="<c:out value="${service.statut}"/>" selected>${service.statut}</option>
			 <option value="Interne">Interne</option>
			 <option value="Externe">Externe</option>
	     </select>
        </div>
         <div class="col-sm-6 mb-3 mb-sm-0">
          <label for="platbus" class="">MONTANT </label>
	      <input type="text" name="montant" class="form-control" value="" onkeyup="montantSaisi('saisi');" id="mttSaisi">
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

</body>
</html>