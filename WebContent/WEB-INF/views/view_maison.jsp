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
		<title> GESTIONS DES PORTES</title>
		
	
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
      <div class="card shadow mb-4" style="width:600px;margin-left:200px;">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER PORTE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_maison"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
       <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TYPE MAISON</label>
        <select name="type" class="form-control">
			<option value="<c:out value="${maison.type}"/>" selected><c:out value="${maison.type}"/></option>
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
         <input type="text" name="maisons" class="form-control" required  value="<c:out value="${maison.maison}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOMBRE PIECE</label>
          <select name="piece" class="form-control">
			<option value="<c:out value="${maison.piece}"/>" selected><c:out value="${maison.piece}"/></option>
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
			<option value="<c:out value="${maison.chambre}"/>" selected><c:out value="${maison.chambre}"/></option>
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
			<option value="<c:out value="${maison.salon}"/>" selected><c:out value="${maison.salon}"/></option>
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
        <input type="text" name="etage" class="form-control" value="<c:out value="${maison.etage}"/>">
        </div>         
      </div>
       <div class="form-group row">  
         <div class="col-sm-4 mb-2 mb-sm-0">
          <label for="platbus" class="">PRIX </label>
	      <input type="text" name="prix" class="form-control" value="<c:out value="${maison.prix}"/>"  onkeyup="montantSaisi('saisi');" id="mttSaisi">
          <input type="hidden" name="prixA" class="form-control" value="<c:out value="${maison.prix}"/>">
        </div>    
         <div class="col-sm-4 mb-3 mb-sm-0">
        <label for="platbus" class="">NUMERO  CIE</label>
        <input type="text" name="num_compt" class="form-control" value="<c:out value="${maison.numero_compteur}"/>">
        </div>
         <div class="col-sm-4 mb-3 mb-sm-0">
          <label for="platbus" class="">NUMERO SODECI </label>
	      <input type="text" name="num_sodeci" class="form-control" value="<c:out value="${maison.numero_sodeci}"/>">
        </div>
      </div>
      <div class="form-group row">      
       <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">BALCON</label>
	   <input type="checkbox" class="form-control"  name="balcon" ${maison.balcon==1?'checked':''} value="1"  />
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TERRASSE</label>
		 <input type="checkbox" class="form-control"  name="terrasse" ${maison.terrasse==1?'checked':''} value="1"  />
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">       
        <label for="platbus" class="">ASCENSEUR </label>
	    <input type="checkbox" class="form-control"  name="ascenceur" ${maison.ascenceur==1?'checked':''} value="1"  /> 
       </div>
      </div>
      <div class="form-group row">
       <div class="col-sm-12 mb-10 mb-sm-0">
        <label for="platbus" class="">AJOUTER UN LOCATAIRE</label>
           <sql:query var="listProp" dataSource="jdbc/gestions_imm">
				select * from locataire where site="${sessionScope.site.site}" and num_porte =' ' or num_porte is null 
		   </sql:query>		  		
		   <select class="form-control" name="matricule" >
				<option value=" "></option>
				<option value="<c:out value="${maison.matricule}"/>" selected><c:out value="${maison.nom_prenom}"/></option>
				<c:forEach  items="${listProp.rows}" var="mapProp">
					<option value="${mapProp.matricule}">${mapProp.nom} ${mapProp.prenom}</option>
				</c:forEach>
			</select>
        </div>
        </div>
      <input type="hidden" name="matri" value="<c:out value="${maison.matricule}"/>">
      <input type="hidden" name="id" value="<c:out value="${maison.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=maison"/>"> Retour</a>
             &nbsp;&nbsp;
            <c:if test="${sessionScope.rolePr.modif_AP==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
              </c:if>            
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