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
		<title> GESTIONS ENTREPRISE</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER ENTREPRISE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_entreprise"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
           <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">LIBELLE ENTREPRISE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="entreprises" class="form-control" required  value="<c:out value="${entreprise.entreprise}"/>">
        </div>               
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">COMPTE CONTRIBUABLE</label>
        <input type="text" name="compte_contrib" class="form-control" value="<c:out value="${entreprise.compte_contrib}"/>">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">SITE WEB</label>
        <input type="text" name="site_web" class="form-control" value="<c:out value="${entreprise.site_web}"/>">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">SIEGE SOCIAL</label>
        <input type="text" name="siege_social" class="form-control" value="<c:out value="${entreprise.siege_social}"/>">
        </div>
      </div>            
      <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 1</label>
		<input type="text" name="tel" class="form-control" value="<c:out value="${entreprise.tel}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 2</label>
		<input type="text" name="tels" class="form-control" value="<c:out value="${entreprise.tels}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="<c:out value="${entreprise.email}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE POSTALE</label>
        <input type="text" name="adresse" class="form-control" value="<c:out value="${entreprise.adresse}"/>">
        </div>             
      </div>
      <div class="form-group row">      
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">N° CNPS </label>
	     <input type="text" name="cnps" class="form-control" value="<c:out value="${entreprise.cnps}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">N°FIBRE OPTIQUE</label>
		<input type="text" name="fibre" class="form-control" value="<c:out value="${entreprise.fibre}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">N° COMPTEUR CIE</label>
	     <input type="text" name="cie" class="form-control" value="<c:out value="${entreprise.cie}"/>">  
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">N° COMPTEUR SODECI</label>
	     <input type="text" name="sodeci" class="form-control" value="<c:out value="${entreprise.sodeci}"/>">  
        </div>
      </div>
       <div class="form-group row">      
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">N° COMPTE BANQUE 1</label>
	     <input type="text" name="banque_1" class="form-control" value="<c:out value="${entreprise.banque_1}"/>">
       </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">RIB BANQUE 1 </label>
		<input type="text" name="rib_1" class="form-control" value="<c:out value="${entreprise.rib_1}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">N° COMPTE BANQUE 2</label>
		<input type="text" name="banque_2" class="form-control" value="<c:out value="${entreprise.banque_2}"/>">
       </div>       
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">RIB BANQUE 2</label>
		<input type="text" name="rib_2" class="form-control" value="<c:out value="${entreprise.rib_2}"/>">
       </div>       
      </div>      
      <div class="form-group row">      
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TYPE LOYER </label>	
	     <select class="form-control" name="type_L" required>
	      <option value="<c:out value="${entreprise.type_L}"/>" selected>${entreprise.type_L}</option>						 
			<option value="propriétaire">propriétaire</option>
			<option value="locataire">locataire</option>
		</select>
        </div>
       <div class="col-sm-4 mb-4 mb-sm-0">
        <label for="platbus" class="">POSITION GPS</label>
		<input type="text" name="gps" class="form-control" value="<c:out value="${entreprise.gps}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">TELECHARGE LE LOGO </label>
	      <img alt="${entreprise.logo}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${entreprise.logo!=null?entreprise.logo:'erreur.jpg'}" style="font-family:bold; color:#000;">
		 <input type="file" id="file" name="logo" onchange="addPhoto('ecole');" accept=".png,.jpg,.jpeg" >
       </div>            
      </div> 
    
      </div>
      <input type="hidden" name="id" value="<c:out value="${entreprise.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=entreprise"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_AG==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
              </c:if>  
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
   
   <c:import url="/include/base_footer.jsp"></c:import>  

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
</body>
</html>