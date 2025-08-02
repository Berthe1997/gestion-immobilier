<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from maison where site="${sessionScope.site.site}"
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
		<title>GESTIONS DES PORTES</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTIONS DES PORTES</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
         <c:if test="${sessionScope.rolePr.ajout_AP==1}">
           <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UNE PORTE
          </button>
         </c:if>         
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>PORTE</th>
                  <th>PIECE</th>
                  <th>CHAMBRE</th>
                  <th>ETAGE</th>                
                  <th>BALCON</th>
                  <th>TERRASSE</th>
                  <th>PRIX</th>
                  <th>STATUT</th>
                  <th>LOCATAIRE</th>
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle"> 
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.maison}</td>                
                  <td>${mapProp.piece}</td>
                  <td>${mapProp.chambre}</td>
                  <td>${mapProp.etage}</td>               
                  <td>${mapProp.balcon==1?'Oui':'Non'}</td>
                  <td>${mapProp.terrasse==1?'Oui':'Non'}</td>                 
                  <td> ${mapProp.prix}</td>   
                  <td>${mapProp.occupe==1?'occupé':'Disponible'}</td>  
                  <td> ${mapProp.nom_prenom}</td>                              
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_maison?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                   <c:if test="${sessionScope.rolePr.supp_AP==1}">
                   <button onclick="document.location.href='<c:url value="/maison?id=${mapProp.id}&matricule=${mapProp.matricule}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UNE PORTE </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/maison"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TYPE MAISON</label>
        <select name="type" class="form-control">
			<option value="<c:out value="${locataire.type_contrat}"/>" selected><c:out value="${locataire.type_contrat}"/></option>
			<option value="APPARTEMENT">APPARTEMENT</option>
			<option value="DUPLEXE">DUPLEXE</option>			
			<option value="VILLA">VILLA</option>  
		    <option value="STUDIO">STUDIO</option> 	
			<option value="2PIECES">2PIECES</option>
			<option value="3PIECES">3PIECES</option>
			<option value="4PIECES">4PIECES</option>												
		</select>
        </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">PORTE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="maisons" class="form-control" required  value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOMBRE PIECE</label>	
		  <select name="piece" class="form-control">
			<option value="" selected></option>
			<option value="1 piece">1 piece</option>
			<option value="2 piece">2 piece</option>			
			<option value="3 piece">3 piece</option>  
		    <option value="4 piece">4 piece</option> 	
			<option value="5 piece">5 piece</option>
			<option value="6 piece">6 piece</option>
			<option value="7 piece">7 piece</option>
			<option value="8 piece">8 piece</option>
			<option value="9 piece">9 piece</option>
			<option value="10 piece">10 piece</option>												
		</select>
       </div>        
      </div>
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">NOMBRE CHAMBRE </label>	    
	      <select name="chambre" class="form-control">
			<option value="" selected></option>
			<option value="1 CHAMBRE">1 CHAMBRE</option>
			<option value="2 CHAMBRE">2 CHAMBRE</option>			
			<option value="3 CHAMBRE">3 CHAMBRE</option>  
		    <option value="4 CHAMBRE">4 CHAMBRE</option> 	
			<option value="5 CHAMBRE">5 CHAMBRE</option>
			<option value="6 CHAMBRE">6 CHAMBRE</option>
			<option value="7 CHAMBRE">7 CHAMBRE</option>
			<option value="8 CHAMBRE">8 CHAMBRE</option>
			<option value="9 CHAMBRE">9 CHAMBRE</option>
			<option value="10 CHAMBRE">10 CHAMBRE</option>												
		</select>
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">NOMBRE SALON </label>	    
	      <select name="salon" class="form-control">
			<option value="" selected></option>
			<option value="1 SALON">1 SALON</option>
			<option value="2 SALON">2 SALON</option>			
			<option value="3 SALON">3 SALON</option>  
		    <option value="4 SALON">4 SALON</option> 	
			<option value="5 SALON">5 SALON</option>
			<option value="6 SALON">6 SALON</option>
			<option value="7 SALON">7 SALON</option>
			<option value="8 SALON">8 SALON</option>
			<option value="9 SALON">9 SALON</option>
			<option value="10 SALON">10 SALON</option>												
		</select>
       </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">ETAGE</label>
        <input type="text" name="etage" class="form-control" value="">
        </div>         
      </div>
       <div class="form-group row">  
        <div class="col-sm-4 mb-2 mb-sm-0">
          <label for="platbus" class="">PRIX </label>
	      <input type="text" name="prix" class="form-control" value="" onkeyup="montantSaisi('saisi');" id="mttSaisi">
        </div>    
         <div class="col-sm-4 mb-3 mb-sm-0">
        <label for="platbus" class="">NUMERO CIE</label>
        <input type="text" name="num_compt" class="form-control" value="">
        </div>
         <div class="col-sm-4 mb-3 mb-sm-0">
          <label for="platbus" class="">NUMERO SODECI </label>
	      <input type="text" name="num_sodeci" class="form-control" value="">
        </div>
      </div>
      <div class="form-group row">      
       <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">BALCON</label>
	   <input type="checkbox" class="form-control"  name="balcon"  value="1"  />
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TERRASSE</label>
		 <input type="checkbox" class="form-control"  name="terrasse"  value="1"  />
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">       
        <label for="platbus" class="">ASCENSEUR </label>
	    <input type="checkbox" class="form-control"  name="ascenceur"  value="1"  /> 
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