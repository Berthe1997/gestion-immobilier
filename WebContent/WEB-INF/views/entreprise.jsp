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
		<title>GESTIONS ENTREPRISE</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTIONS ENTREPRISE</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3"> 
         <c:if test="${sessionScope.rolePr.ajout_AG==1}">
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UNE ENTREPRISE
          </button>
         </c:if>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>ENTREPRISE</th>
                  <th>ADRESSE</th>
                  <th>CONTACT</th>
                  <th>EMAIL</th>
                  <th>SITE WEB</th>
                  <th>SIEGE SOCIAL</th>
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <sql:query var="listProp" dataSource="jdbc/gestions_imm">
				select * from entreprise 
			</sql:query>
			  <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle"> 
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.entreprise}</td>
                  <td>${mapProp.adresse}</td>
                  <td>${mapProp.tel}</td>
                  <td>${mapProp.email}</td>
                  <td>${mapProp.site_web}</td>
                  <td> ${mapProp.siege_social}</td>                                   
                  <td align="center"><a  onclick="document.location.href='<c:url value="/view_entreprise?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                   <c:if test="${sessionScope.rolePr.supp_AG==1}">
                   <button onclick="document.location.href='<c:url value="/entreprise?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
<div class="modal-content" style="width:980px;margin-left:-55px;">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UNE ENTREPRISE </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/entreprise"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">LIBELLE ENTREPRISE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="entreprises" class="form-control" required  value="">
        </div>               
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">COMPTE CONTRIBUABLE</label>
        <input type="text" name="compte_contrib" class="form-control" value="">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">SITE WEB</label>
        <input type="text" name="site_web" class="form-control" value="">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">SIEGE SOCIAL</label>
        <input type="text" name="siege_social" class="form-control" value="">
        </div>
      </div>            
      <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 1</label>
		<input type="text" name="tel" class="form-control" value="">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 2</label>
		<input type="text" name="tels" class="form-control" value="">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE POSTALE</label>
        <input type="text" name="adresse" class="form-control" value="">
        </div>             
      </div>
      <div class="form-group row">      
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">N° CNPS </label>
	     <input type="text" name="cnps" class="form-control" value="">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">N°FIBRE OPTIQUE</label>
		<input type="text" name="fibre" class="form-control" value="">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">N° COMPTEUR CIE</label>
	     <input type="text" name="cie" class="form-control" value="">  
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">N° COMPTEUR SODECI</label>
	     <input type="text" name="sodeci" class="form-control" value="">  
        </div>
      </div>
       <div class="form-group row">      
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">N° COMPTE BANQUE 1</label>
	     <input type="text" name="banque_1" class="form-control" value="">
       </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">RIB BANQUE 1 </label>
		<input type="text" name="rib_1" class="form-control" value="">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">N° COMPTE BANQUE 2</label>
		<input type="text" name="banque_2" class="form-control" value="">
       </div>       
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">RIB BANQUE 2</label>
		<input type="text" name="rib_2" class="form-control" value="">
       </div>       
      </div>      
      <div class="form-group row">      
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TYPE LOYER </label>	
	     <select class="form-control" name="type_L" required>					 
			<option value="propriétaire">propriétaire</option>
			<option value="locataire">locataire</option>
		</select>
        </div>
       <div class="col-sm-4 mb-4 mb-sm-0">
        <label for="platbus" class="">POSITION GPS</label>
		<input type="text" name="gps" class="form-control" value="">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">TELECHARGE LE LOGO </label>
	      <img alt="${entreprise.logo}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${entreprise.logo!=null?entreprise.logo:'erreur.jpg'}" style="font-family:bold; color:#000;">
		 <input type="file" id="file" name="logo" onchange="addPhoto('ecole');" accept=".png,.jpg,.jpeg" >
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