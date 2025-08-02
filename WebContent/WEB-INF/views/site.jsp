<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from site where site="${sessionScope.site.site}"
</sql:query>
<sql:query var="listQua" dataSource="jdbc/gestions_imm">
	select * from quartier 
</sql:query>
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
		<title>GESTION DES SITES</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTION DES SITES </h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
         <c:if test="${sessionScope.rolePr.ajout_SI==1}">
           <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN SITE
          </button>
         </c:if>             
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>SITE</th>
                  <th>TYPE</th>
                  <th>NBRE PORTE</th>
                  <th>CITE</th>
                  <th>DATE CREATION</th>
                  <th>PROPRIETAIRE</th>
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
             <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle"> 
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.site}</td>
                  <td>${mapProp.type_site}</td>
                  <td>${mapProp.nombre_porte}</td>
                  <td>${mapProp.cite}</td>
                  <td>${mapProp.date_ajout}</td>
                  <td> ${mapProp.nom_prenom}</td>                                   
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_site?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                    <c:if test="${sessionScope.rolePr.supp_SI==1}">
                   <button onclick="document.location.href='<c:url value="/site?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN SITE </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/site"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SITE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="sites" class="form-control" required  value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TYPE SITE<span class="text-danger ml-2">*</span></label>
       <select name="type" class="form-control" required>
			<option value="" selected></option>
			<option value="APPARTEMENT">APPARTEMENT</option>
			<option value="MIXTE APPARTEMENT">MIXTE APPARTEMENT</option>
			<option value="IMMEUBLE">IMMEUBLE</option>
			<option value="MAGASIN">MAGASIN</option>
			<option value="VILLA-BAS">VILLA-BAS</option>
			<option value="COUR COMMUNE">COUR COMMUNE</option>
			<option value="MAISON">MAISON</option>
			<option value="BUREAU">BUREAU</option>			   								
	   </select>
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="seat" class="">NOMBRE PORTE<span class="text-danger ml-2">*</span></label>
         <input type="number" required id="seat" name="nombre" class="form-control" value="">
        </div>
      </div>
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CITE SITE</label>
        <input type="text" name="cite" class="form-control" value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NUMERO ACD</label>
        <input type="text" name="num_acd" class="form-control" value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NUMERO LOT </label>
	     <input type="text" name="num_lot" class="form-control" value="">  
        </div>
      </div>
      <div class="form-group row">      
       <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">NUMERO ILLOT</label>
	   <input type="text" name="num_illot" class="form-control" value="">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">PERMIS DE CONSTRUCTION</label>
		<input type="text" name="permis_c" class="form-control" value="">
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">SUPERFICIE </label>
	     <input type="text" name="superficie" class="form-control" value="">
       </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE OBTENTION </label>
		 <input type="date" name="date_ob"   class="form-control" value="">
		</div>
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">NUMERO CNPS</label>
		  <input type="text" name="num_cnps" class="form-control" value="">
		</div> 
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">NUMERO IMPOT</label>
		 <input type="text" name="num_impot" class="form-control" value="">
		</div>
      </div>
      <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">VILLE<span class="text-danger ml-2">*</span></label>
		 <select name="ville" class="form-control" required>
		   <option value=""selected ></option>
			<c:forEach  items="${listVil.rows}" var="mapVil">
			 <option value="<c:out value="${mapVil.ville}"/>"> <c:out value="${mapVil.ville}"/></option>
		    </c:forEach>		   								
		 </select>
		</div> 
		 <div class="col-sm-4 mb-2 mb-sm-0">
          <label for="platbus" class="">COMMUNE<span class="text-danger ml-2">*</span></label>
		  <select name="commune" class="form-control" required>
			<option value=""selected ></option>
			<c:forEach  items="${listCom.rows}" var="mapCom">
			  <option value="<c:out value="${mapCom.commune}"/>"> <c:out value="${mapCom.commune}"/></option>
			</c:forEach>			   								
		</select>
		</div>
		 <div class="col-sm-4 mb-2 mb-sm-0">
           <label for="platbus" class="">QUARTIER<span class="text-danger ml-2">*</span></label>
	       <select name="quartier" class="form-control" required>
		     <option value=""selected ></option>
			<c:forEach  items="${listQua.rows}" var="mapQua">
			  <option value="<c:out value="${mapQua.quartier}"/>"> <c:out value="${mapQua.quartier}"/></option>
			</c:forEach>			   								
		</select>   
		</div> 
      </div>
      <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
           <label for="platbus" class="">SITUATION GEOGRAPHIQUE<span class="text-danger ml-2">*</span></label>
		 <input type="text" name="situation_geo"   class="form-control" value="">
		</div> 
		 <div class="col-sm-4 mb-2 mb-sm-0">
          <label for="platbus" class="">DATE AJOUT<span class="text-danger ml-2">*</span></label>
		  <input type="date" name="date_ajout" required  class="form-control" value="">
		</div>
		 <div class="col-sm-4 mb-2 mb-sm-0">
           <label for="platbus" class="">description</label>
           <textarea rows="2" cols="40" class="form-control" name="description"><c:out
						value="" /></textarea>
		</div> 
      </div>
      <div class="form-group row">
        <div class="col-sm-12 mb-6 mb-sm-0">
           <label for="platbus" class="">NOM PROPRIETAIRE<span class="text-danger ml-2">*</span></label>
           <sql:query var="listProp" dataSource="jdbc/gestions_imm">
				SELECT nom,prenom,matricule FROM gestions_immobilier.proprietaire
				union 
				SELECT nom,prenom,email FROM gestions_immobilier.proprietaire_m;
		   </sql:query> 
            <select class="form-control" name="matricule" required>   
             <option value="" selected></option>
				<c:forEach  items="${listProp.rows}" var="mapProp">
					<option value="${mapProp.matricule}">${mapProp.nom} ${mapProp.prenom}</option>
				</c:forEach>   
		    </select>                            
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