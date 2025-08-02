<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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
     
      <div class="container-fluid">
      <!-- Page Heading -->
      <!-- Log on to codeastro.com for more projects -->
      <!-- Basic Card Example -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER SITE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_site"/>" method="post"  class="myFormulaire">
             
            <div class="card-body">
              <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SITE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="sites" class="form-control" required value="<c:out value="${site.site }"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TYPE SITE<span class="text-danger ml-2">*</span></label>
       <select name="type" class="form-control" required>
			<option value="<c:out value="${site.type_site}"/>" selected><c:out value="${site.type_site}"/></option>
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
         <input type="number" required id="seat" name="nombre" class="form-control" value="<c:out value="${site.nombre_porte}"/>">
        </div>
      </div>
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CITE SITE</label>
        <input type="text" name="cite" class="form-control"  value="<c:out value="${site.cite }"/>" />
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NUMERO ACD</label>
        <input type="text" name="num_acd" class="form-control"  value="<c:out value="${site.num_acd }"/>"/>
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NUMERO LOT </label>
	     <input type="text" name="num_lot" class="form-control"  value="<c:out value="${site.num_lot }"/>"/>  
        </div>
      </div>
      <div class="form-group row">      
       <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">NUMERO ILLOT</label>
	   <input type="text" name="num_illot" class="form-control"  value="<c:out value="${site.num_illot }"/>"/>
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">PERMIS DE CONSTRUCTION</label>
		<input type="text" name="permis_c" class="form-control"  value="<c:out value="${site.permis_contruction }"/>"/>
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">SUPERFICIE </label>
	     <input type="text" name="superficie" class="form-control"  value="<c:out value="${site.superficie}"/>"/>
       </div>
      </div>
      <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE OBTENTION </label>
		 <input type="date" name="date_ob"   class="form-control" value="<c:out value="${site.date_obtention}"/>">
		</div>
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">NUMERO CNPS</label>
		  <input type="text" name="num_cnps" class="form-control"  value="<c:out value="${site.num_cnps }"/>"/>
		</div> 
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">NUMERO IMPOT</label>
		 <input type="text" name="num_impot" class="form-control"  value="<c:out value="${site.num_impot }"/>"/>
		</div>
      </div>
      <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">VILLE<span class="text-danger ml-2">*</span></label>
		 <select name="ville" class="form-control" required>
		   <option value="<c:out value="${site.ville}"/>" selected><c:out value="${site.ville}"/></option>
		   <c:forEach  items="${listVil.rows}" var="mapVil">
			 <option value="<c:out value="${mapVil.ville}"/>"> <c:out value="${mapVil.ville}"/></option>
		    </c:forEach>	   								
		 </select>
		</div> 
		 <div class="col-sm-4 mb-2 mb-sm-0">
          <label for="platbus" class="">COMMUNE<span class="text-danger ml-2">*</span></label>
		  <select name="commune" class="form-control" required>
				<option value="<c:out value="${site.commune}"/>" selected><c:out value="${site.commune}"/></option>
			   	<c:forEach  items="${listCom.rows}" var="mapCom">
			  <option value="<c:out value="${mapCom.commune}"/>"> <c:out value="${mapCom.commune}"/></option>
			</c:forEach>			   								
		</select>
		</div>
		 <div class="col-sm-4 mb-2 mb-sm-0">
           <label for="platbus" class="">QUARTIER<span class="text-danger ml-2">*</span></label>
	       <select name="quartier" class="form-control" required>
		      <option value="<c:out value="${site.quartier}"/>" selected><c:out value="${site.quartier}"/></option>
			  <c:forEach  items="${listQua.rows}" var="mapQua">
			  <option value="<c:out value="${mapQua.quartier}"/>"> <c:out value="${mapQua.quartier}"/></option>
			</c:forEach>			   								
		</select>   
		</div> 
      </div>
      <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
           <label for="platbus" class="">SITUATION GEOGRAPHIQUE<span class="text-danger ml-2">*</span></label>
		 <input type="text" name="situation_geo"   class="form-control" value="<c:out value="${site.situation_geo}"/>">
		</div> 
		 <div class="col-sm-4 mb-2 mb-sm-0">
          <label for="platbus" class="">DATE AJOUT<span class="text-danger ml-2">*</span></label>
		  <input type="date" name="date_ajout" required  class="form-control" value="<c:out value="${site.date_ajout}"/>">
		</div>
		 <div class="col-sm-4 mb-2 mb-sm-0">
           <label for="platbus" class="">description</label>
           <textarea rows="2" cols="40" class="form-control" name="description"><c:out
						value="" />${site.description}</textarea>
		</div> 
      </div>
      <div class="form-group row">
        <div class="col-sm-12 mb-6 mb-sm-0">
           <label for="platbus" class="">NOM PROPRIETAIRE<span class="text-danger ml-2">*</span></label>
            <sql:query var="listProp" dataSource="jdbc/gestions_imm">
				select * from proprietaire 
		   </sql:query> 
            <select class="form-control" name="matricule" required>   
             <option value="<c:out value="${site.matricule}"/>" selected><c:out value="${site.nom_prenom}"/></option>
				<c:forEach  items="${listProp.rows}" var="mapProp">
					<option value="${mapProp.matricule}">${mapProp.nom} ${mapProp.prenom}</option>
				</c:forEach>   
		    </select>                                       
		</div> 		
      </div>
      <input type="hidden" name="id" value="<c:out value="${site.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=site"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_SI==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
              </c:if> 
             
                    
              <a class="btn btn-primary float-right" href="" target="_blank"> Imprimé</a> 
          
                        
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