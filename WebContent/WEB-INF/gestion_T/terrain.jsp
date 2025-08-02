<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from terrain 
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
		<title>TERRAIN</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">TERRAIN</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_GE==1}">
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN TERRAIN
          </button>
          </c:if> 
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>LIBELLE</th>
                  <th>ADRESSE </th>
                  <th>SUPERFICIE</th>
                  <th>TYPE</th>
                  <th>PROPRIETAIRE</th>
                  <th>PRIX</th>
                  <th>PRIX GESTI</th>               
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.libelle}</td>
                  <td>${mapProp.adresse}</td>
                  <td>${mapProp.superficie}</td>
                  <td>${mapProp.type}</td>
                  <td>${mapProp.proprietaire}</td>
                  <td>${mapProp.prix}</td>
                  <td>${mapProp.prixG}</td>                                                
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_terrain?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                   <c:if test="${sessionScope.rolePr.supp_GE==1}">
                   <button onclick="document.location.href='<c:url value="/terrain?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN TERRAIN </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/terrain"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LIBELLE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="libelle" class="form-control" required  value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="adresse" class="form-control" required  value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SUPERFICIE<span class="text-danger ml-2">*</span></label>
          <input type="text" required  name="superficie" class="form-control" value="">		
        </div>                
      </div>
      <div class="form-group row">   
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="seat" class="">TYPE TERRAIN<span class="text-danger ml-2">*</span></label>
        <select name="type" required class="form-control">
			<option value="" selected></option>
			<option value="Celibataire">Résidentiel</option>
			<option value="Commercial">Commercial</option>
			<option value="Agricole">Agricole</option>
		</select>
        </div>   
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">PROPRIETAIRE</label>
        <input type="text" name="proprietaire" class="form-control" value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT</label>
        <input type="text" name="contact" class="form-control" value="">
        </div>                            
      </div>
      <div class="form-group row">  
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">STATUT JURIDIQUE</label>
        <select name="statut_ju" required class="form-control">
			<option value="" selected></option>
			<option value="privée">privée</option>
			<option value="public">public</option>
			<option value="indivision">indivision</option>
		</select>  
        </div> 
        <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">LOT</label>
	   <input type="text" name="lot" class="form-control" value="">
       </div>        
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">ILOT</label>
       <input type="text" name="ilot" class="form-control" value="">
        </div>
      </div>
     <div class="form-group row"> 
        <div class="col-sm-3 mb-2 mb-sm-0">
       <label for="platbus" class="">LOTISSEMENT</label>
	   <input type="text" name="lotissement" class="form-control" value="">
       </div>               
       <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">PRIX PROPRIETAIRE</label>
		  <input type="text" name="prix" class="form-control" value="" onkeypress="return onlyNumberKey(event)">
		</div>
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">PRIX GESTIONNAIRE </label>
	     <input type="text" name="prixG" class="form-control" value="" onkeypress="return onlyNumberKey(event)">
       </div>  
	   <div class="col-sm-3 mb-2 mb-sm-0">
	        <label for="platbus" class="">DATE ACHAT</label>
	       <input type="date" name="date_achat" class="form-control" value="">
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