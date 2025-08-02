<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from proprietaire 
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
		<title>GESTIONS DES PROPRIETAIRES PHYSIQUES</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTIONS DES PROPRIETAIRES PHYSIQUES</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_PR==1}">
            <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN PROPRIETAIRE-PHYSIQUE
          </button>
        </c:if>        
        </div>
           
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>MATRICULE</th>
                  <th>NOM </th>
                  <th> PRENOM</th>
                  <th>SEXE</th>
                  <th>LIEU RESIDENCE</th>
                  <th>CONTACT</th>
                  <th>EMAIL</th>               
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.matricule}</td>
                  <td>${mapProp.nom}</td>
                  <td>${mapProp.prenom}</td>
                  <td>${mapProp.sexe}</td>
                  <td>${mapProp.lieu_residence}</td>
                  <td>${mapProp.tel}</td>
                  <td>${mapProp.email}</td>                                                
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_proprietaire?id=${mapProp.id}&matri=${mapProp.matricule}&matriD=${mapProp.matricule}&matriU=${mapProp.matricule}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                   <c:if test="${sessionScope.rolePr.supp_PR==1}">
                   <button onclick="document.location.href='<c:url value="/proprietaire?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN PROPRIETAIRE-PHYSIQUE </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/proprietaire"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="prenom" class="form-control" required  value="">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">GENRE<span class="text-danger ml-2">*</span></label>
        <select name="sexe" required class="form-control">
			<option value="" selected></option>
			<option value="MASCULIN">MASCULIN</option>
			<option value="FEMININ">FEMININ</option>
		</select>		
        </div>  
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">SITUATION MATRIMO</label>
        <select name="situationM" required class="form-control">
			<option value="" selected></option>
			<option value="Celibataire">Celibataire</option>
			<option value="Marie">Marie</option>
			<option value="Divorcé">Divorcé</option>
		</select> 
        </div>       
      </div>
      <div class="form-group row">
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="seat" class="">DATE DE NAISSANCE<span class="text-danger ml-2">*</span></label>
         <input type="date" required  name="date_naiss" class="form-control" value="">
        </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">LIEU DE NAISSANCE</label>
        <input type="text" name="lieu_naiss" class="form-control" value="">
        </div>       
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">NATIONALITE </label>
	     <sql:query var="listPays" dataSource="jdbc/gestions_imm">
			select * from pays 
		</sql:query>
         <select name="nationalite" required class="form-control">
				  <option value="" selected></option>
				  <c:forEach items="${listPays.rows}" var="mapP" varStatus="boucle">
					<option value="${mapP.pays}" class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">${mapP.pays}</option>
				  </c:forEach>
		 </select>  
        </div>   
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PROFESSION</label>
        <input type="text" name="profession" class="form-control" value="">
        </div>    
      </div>
      <div class="form-group row">          
       <div class="col-sm-3 mb-2 mb-sm-0">
       <label for="platbus" class="">LIEU DE RESIDENCE</label>
	   <input type="text" name="lieu_residence" class="form-control" value="">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 1</label>
		<input type="text" name="tel" class="form-control" value="">
       </div>  
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 2</label>
		<input type="text" name="tels" class="form-control" value="">
       </div> 
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">TEL WHATSAPP</label>
		<input type="text" name="telW"  class="form-control" value="" >
       </div>       
      </div>
      <div class="form-group row">        
         <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="">
       </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">CNI </label>
	     <input type="text" name="cni" class="form-control" value="">
       </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">CMU </label>
	     <input type="text" name="cmu" class="form-control" value="">
       </div>        		
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">ADRESSE POSTAL</label>
		 <input type="text" name="adresseP" class="form-control" value="">
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
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">Taux Pourcentage</label>
	     <input type="text" name="taux" class="form-control" value="0" onkeypress="return onlyNumberKey(event)"/>
       </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">Durre Contrat</label>
		<input type="text" name="dure_contrat" class="form-control" value="0" onkeypress="return onlyNumberKey(event)"/>
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">Type Contrat</label>
		<select name="type" class="form-control" required>
			<option value="" selected></option>
			<option value="CONTRAT">CONTRAT</option>							   								
	   </select>
       </div>       
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">Telecharge le Contrat</label>
		<img alt="${proprietaire.image_contrat}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${proprietaire.image_contrat!=null?entreprise.image_cni:'erreur.jpg'}" style="font-family:bold; color:#000;">
		 <input type="file" id="file" name="image" onchange="addPhoto('ecole');" accept=".png,.jpg,.jpeg" >
       </div>       
      </div>   
      <div class="form-group row"> 
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">Telecharge la Photo</label>
	      <img alt="${proprietaire.logo}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${proprietaire.photo!=null?entreprise.photo:'erreur.jpg'}" style="font-family:bold; color:#000;">
		 <input type="file" id="file" name="photo" onchange="addPhoto('ecole');" accept=".png,.jpg,.jpeg" class="form-control">
       </div>       
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE AJOUT </label>
		 <input type="date" name="date_ajout"   class="form-control" value="">
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
 
  <script>
	
	function onlyNumberKey(evt) {
	     
	     // Only ASCII character in that range allowed
	     var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	     if (ASCIICode > 31 && (ASCIICode <48 ||ASCIICode > 57))
	         return false;
	     return true;
	 }
  
 function getVisible(mode){
		if(mode == 'visible'){
			document.getElementById("decoupe").style.display = "none";
			
		}
		if(mode == 'hidden'){			
			document.getElementById("decoupe").style.display = "block";
			
		}
	}
 
 function getDiscipline(mode){
		if(mode=='ajout'){
			document.getElementById("tPM").style.display = "none";
			document.getElementById("formAdd").style.display = "block";
			document.getElementById("add").style.display = "none";
			document.getElementById("move").style.display = "inline";
			document.getElementById("val").style.display = "inline";
			
		}
		if(mode=='list'){
			document.getElementById("formAdd").style.display = "none";
			document.getElementById("tPM").style.display = "inline";
			document.getElementById("move").style.display = "none";
			document.getElementById("add").style.display = "block";
			document.getElementById("val").style.display = "none";
		}
 }	
 </script>

</body>
</html>