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
		<title> GESTION DES RAPPORTS</title>
<style>		
		.bmenu a {
	
	margin-bottom: 10px;
	padding: 10px;
	width: 100%;
	color: #FFF;
}

 #flash {
      
       height: 60px;
      margin: 10px auto;
      background-color: red;
      text-align: center;
      color: white;
      font-size: 20px;
      line-height: 60px;
      transition: background-color 0.5s ease;
    }
    
     #flashs {
      
       height: 60px;
      margin: 10px auto;
      background-color: red;
      text-align: center;
      color: white;
      font-size: 20px;
      line-height: 60px;
      transition: background-color 0.5s ease;
    }
		
</style>	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
           
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <div class="container-fluid" style="background-image: url('assetss/images/namy.png');">
      <!-- Page Heading -->
      <!-- Log on to codeastro.com for more projects -->
      <!-- Basic Card Example -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
           <div class="form-group row">
            <div class="col-sm-4 mb-4 mb-sm-0">  
             </div>
             <div class="col-sm-4 mb-4 mb-sm-0">             
               <button class="bnav2 ${ouvert==1?fermer==1?'active':'none':'active'}" onclick="liens('ouvertureCaisse');" style="text-align:center;">OUVERTURE DE VOTRE CAISSE</button>                                   
               <button class="bnav3 ${ouvert==1?fermer==1?'none':'active':'none'}" onclick="liens('fermetureCaisse');" style="text-align:center;">FERMETURE DE VOTRE CAISSE</button>            
             </div> 
              <div class="col-sm-4 mb-4 mb-sm-0">  
             </div>
          </div>
          
        </div>
        <div class="card-body">
          <div class="form-group row bmenu">
             <div class="col-sm-4 mb-3 mb-sm-0"> 
	             <c:if test="${sessionScope.rolePr.paiementL==1}">
	                <button class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" onclick="liens('paiement_loyer');">Paiement Location</button>
	             </c:if>                 
                 <c:if test="${sessionScope.rolePr.paiementL==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if> 
             </div>            
             <div class="col-sm-4 mb-3 mb-sm-0">    
                <c:if test="${sessionScope.rolePr.paiement_bm==1}">
	               <button class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" onclick="liens('reservation');">Paiement Bien Meublée</button>
	             </c:if> 
	              <c:if test="${sessionScope.rolePr.paiement_bm==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>                         
             </div>
             <div class="col-sm-4 mb-3 mb-sm-0">  
                <c:if test="${sessionScope.rolePr.gestion_service==1}">
	                   <button class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" onclick="liens('gestion_service');">Gestions des Services</button>
	             </c:if>  
	              <c:if test="${sessionScope.rolePr.gestion_service==0}">
	                <a class="btn btn-info">/+++++++++++++++++++/</a>
	             </c:if>             
             </div>                                      
          </div>
          
         <div class="form-group row bmenu">
             <div class="col-sm-4 mb-3 mb-sm-0">  
                <c:if test="${sessionScope.rolePr.rapport_c==1}">
	                   <button class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" onclick="liens('documentations');">RAPPORT</button>
	             </c:if>  
	              <c:if test="${sessionScope.rolePr.rapport_c==0}">
	                <a class="btn btn-info">/+++++++++++++++++++/</a>
	             </c:if>             
             </div>   
              <div class="col-sm-4 mb-3 mb-sm-0">  
              <c:if test="${sessionScope.rolePr.arriere==1}">
               <a class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" onclick="liens('gestion_arrieres');">Gestions des Arriérés </a>	             
               </c:if> 
                <c:if test="${sessionScope.rolePr.arriere==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if> 
             </div>  
              <div class="col-sm-4 mb-3 mb-sm-0">                
	              <c:if test="${sessionScope.rolePr.chargeE==1}">
	               <button class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" onclick="liens('charge_entretien');">Charge-Entretien</button>
	             </c:if> 
	              <c:if test="${sessionScope.rolePr.chargeE==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>   	                    
             </div>   
          
          
          
   </div>
      </div>    	     
    </div>
   </div> 
   </div>
   <c:import url="/include/base_footer.jsp"></c:import>  

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
 
 <script>
 
 function liens(mode) {
		if (mode == 'ouvertureCaisse')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'fermetureCaisse')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'comptabilite')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'paiement_loyer')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'charge_entretien')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'visite_maison')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'gestion_service')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'documentations')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'gestion_arrieres')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'reservation')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
 }
			
 function startFlash() {
	    const flashElement = document.getElementById('flash');  
	    let flashing = true;

	    setInterval(() => {
	      if (flashing) {
	        flashElement.style.backgroundColor = 'yellow';
	      } else {
	        flashElement.style.backgroundColor = 'red';
	      }
	      flashing = !flashing;  // Alterne l'état
	    }, 500);  // Change toutes les 500 ms
	  }
	//Démarre le flash après 1 seconde
	  setTimeout(startFlash, 1000);
  
  function startFlashs() {
	    const flashsElement = document.getElementById('flashs');  
	    let flashing = true;

	    setInterval(() => {
	      if (flashing) {
	        flashsElement.style.backgroundColor = 'yellow';
	      } else {
	        flashsElement.style.backgroundColor = 'red';
	      }
	      flashing = !flashing;  // Alterne l'état
	    }, 500);  // Change toutes les 500 ms
	  }

  // Démarre le flash après 1 seconde
  setTimeout(startFlashs, 1000);
  
  $(document).ready(function(){
		$("#1").attr("disabled","disabled");
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				crud: 'lesCaisses'
			}, success : function(result){
				var option = '<option selected></option>';
				$.each(result, function(index, value){
					option +='<option value="'+value.cd+'">'+value.libelle+'</option>';
				});
				document.getElementById("codeCaisse").innerHTML = option;
			}
		});
	});
  
</script>
 
</body>
</html>         