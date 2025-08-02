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
		<title> GESTIONS AGENCE</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER AGENCE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_agence"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
           <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LIBELLE AGENCE<span class="text-danger ml-2">*</span></label>
         <input type="text" name="agenc" class="form-control" required  value="<c:out value="${agence.agence}"/>">
        </div>
         <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">CODE AGENCE<span class="text-danger ml-2">*</span></label>
        <input type="text" name="code" required class="form-control" value="<c:out value="${agence.code}"/>">
        </div>                     
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE</label>
        <input type="text" name="adresse" class="form-control" value="<c:out value="${agence.adresse}"/>">
        </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE POSTALE</label>
        <input type="text" name="adresse_postal" class="form-control" value="<c:out value="${agence.adresse_postal}"/>">
        </div>
      </div>
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 1</label>
		<input type="text" name="tel" class="form-control" value="<c:out value="${agence.tel}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 2</label>
		<input type="text" name="tels" class="form-control" value="<c:out value="${agence.tels}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TEL FAX</label>
        <input type="text" name="tel_fax" class="form-control" value="<c:out value="${agence.tel_fax}"/>">
        </div>        
      </div>
       <div class="form-group row">      
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="<c:out value="${agence.email}"/>">
        </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LOCALISATION</label>
		<input type="text" name="localisation" class="form-control" value="<c:out value="${agence.localisation}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">POSITION GPS </label>
	     <input type="text" name="gps" class="form-control" value="<c:out value="${agence.gps}"/>">  
        </div>
      </div>
       <div class="form-group row">      
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">FIBRE OPTIQUE </label>
	     <input type="text" name="fibre" class="form-control" value="<c:out value="${agence.fibre}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">MONTANT LOYER</label>
		<input type="text" name="montant_L" class="form-control" value="<c:out value="${agence.montant_L}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TYPE LOYER </label>
	     <select class="form-control" name="type_L" required>	
	       <option value="<c:out value="${agence.type_L}"/>" selected>${agence.type_L}</option>				 
		   <option value="propriétaire">propriétaire</option>
		   <option value="locataire">locataire</option>
		</select>  
        </div>
      </div>
      <div class="form-group row">      
       <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">DIRECTEUR</label>
	   <input type="text" name="directeur" class="form-control" value="<c:out value="${agence.directeur}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">DATE CREATION</label>
		<input type="date" name="date_ajout" class="form-control" value="<c:out value="${agence.date_ajout}"/>">
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">REGISTRE DE COMMERCE</label>
	   <input type="text" name="registre_C" class="form-control" value="<c:out value="${agence.registre_C}"/>">
       </div>
      </div>
        <div class="form-group row">      
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">COMPTEUR CIE </label>
	     <input type="text" name="cie" class="form-control" value="<c:out value="${agence.cie}"/>">
       </div>
       <div class="col-sm-4 mb-4 mb-sm-0">
        <label for="platbus" class="">COMPTEUR SODECI</label>
		<input type="text" name="sodeci" class="form-control" value="<c:out value="${agence.sodeci}"/>">
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SITE WEB </label>
	     <input type="text" name="site_web" class="form-control" value="<c:out value="${agence.site_web}"/>">  
        </div>            
      </div> 
      <div class="form-group row"> 
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">TELECHARGE LE LOGO </label>
	      <img alt="${agence.logo}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${agence.logo!=null?agence.logo:'erreur.jpg'}" style="font-family:bold; color:#000;">
		 <input type="file" id="file" name="logo" onchange="addPhoto('ecole');" accept=".png,.jpg,.jpeg" >
       </div>
      </div>
    
      </div>
      <input type="hidden" name="id" value="<c:out value="${agence.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=agence"/>"> Retour</a>
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