<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from gestionnaire 
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
		<title>PERSONNEL</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">PERSONNEL</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_GE==1}">
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN PERSONNEL
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
                  <th>PRENOM</th>
                  <th>SEXE</th>
                  <th>LIEU RESIDENCE</th>
                  <th>CONTACT</th>
                  <th>DATE RECRUT</th>               
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
                  <td>${mapProp.date_embauchema}</td>                                                
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_gestionnaire?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                   <c:if test="${sessionScope.rolePr.supp_GE==1}">
                   <button onclick="document.location.href='<c:url value="/gestionnaire?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN PERSONNEL </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/gestionnaire"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="prenom" class="form-control" required  value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">GENRE<span class="text-danger ml-2">*</span></label>
        <select name="sexe" required class="form-control">
			<option value="" selected></option>
			<option value="MASCULIN">MASCULIN</option>
			<option value="FEMININ">FEMININ</option>
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
        <label for="platbus" class="">SITUATION MATRIMON</label>
        <select name="situationM" required class="form-control">
			<option value="" selected></option>
			<option value="Celibataire">Celibataire</option>
			<option value="Marie">Marie</option>
			<option value="Divorcé">Divorcé</option>
		</select>  
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
      </div>
      <div class="form-group row">         
       <div class="col-sm-3 mb-2 mb-sm-0">
       <label for="platbus" class="">LIEU DE RESIDENCE</label>
	   <input type="text" name="lieu_residence" class="form-control" value="">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">NUMERO CNPS</label>
		  <input type="text" name="num_cnps" class="form-control" value="">
		</div>
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="">
       </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE DE RECRUTEMENT </label>
		 <input type="date" name="date_emb"   class="form-control" value="">
		</div>            
      </div>
      <div class="form-group row">        		 
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">ADRESSE POSTAL</label>
		 <input type="text" name="adresseP" class="form-control" value="">
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
         <label for="platbus" class="">CNI </label>
	     <input type="text" name="cni" class="form-control" value="">
       </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">CMU </label>
	     <input type="text" name="cmu" class="form-control" value="">
       </div> 
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">N° COMPTE BANQUE </label>
	     <input type="text" name="banque_1" class="form-control" value="">
       </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">RIB BANQUE  </label>
		<input type="text" name="rib_1" class="form-control" value="">
       </div>
      </div> 
      <div class="form-group row">   
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">TELECHARGE LA PHOTO</label>
	      <img alt="${gestionnaire.logo}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${gestionnaire.photo!=null?entreprise.photo:'erreur.jpg'}" style="font-family:bold; color:#000;">
		 <input type="file" id="file" name="photo" onchange="addPhoto('ecole');" class="form-control" accept=".png,.jpg,.jpeg" >
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