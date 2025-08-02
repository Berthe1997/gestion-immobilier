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

 
		
</style>	
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
           <div class="form-group row">
            <div class="col-sm-4 mb-4 mb-sm-0">  
             <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=comptabilite"/>"> Retour</a>
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
	                <a class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" href="#lap_jual_pertanggal_1" data-toggle="modal">Synthèse des Paiements</a>
	             </c:if>                 
                 <c:if test="${sessionScope.rolePr.paiementL==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if> 
             </div>            
             <div class="col-sm-4 mb-3 mb-sm-0">    
                <c:if test="${sessionScope.rolePr.paiementL==1}">
	               <a class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" href="#lap_jual_pertanggal_2" data-toggle="modal">Etat des Paiements des Locataires</a>
	             </c:if> 
	              <c:if test="${sessionScope.rolePr.etat_FL==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>                         
             </div>
              <div class="col-sm-4 mb-3 mb-sm-0">
                 <c:if test="${sessionScope.rolePr.paiementL==1}">
	                   <a class="btn btn-info ${ouvert==1?fermer==1?'desactive':'active':'desactive'}" href="#lap_jual_pertanggal_3" data-toggle="modal">Etat des Arriérés des Locataires</a>
	             </c:if>  
	              <c:if test="${sessionScope.rolePr.etat_FS==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>             
             </div>                                  
          </div>
          
    <!-- ============ ============ ============ Synthèse des Paiements  ============ ============ =========================== -->         
           <div class="modal fade" id="lap_jual_pertanggal_1" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Synthèse des Paiements</h3>
                      </div>
                      <form action="<c:url value="/Synthese_Paiement"/>" method="post" class="myFormulaire" target="_blank" name="formBilan">
                      <!--  <input type="hidden" name="typ" value="syntheses">  
                         <input type="hidden" name="typ" value="detail"> --> 
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Proprietaire</label>                                 
                           </div>
                           <div class="col-sm-8 mb-3 mb-sm-0">
                              <sql:query var="Pr" dataSource="jdbc/gestions_imm">
								select * from proprietaire   
							 </sql:query>
					         <select id="matricule" name="matricule" class="form-control" onchange="ajax('proprietaire')">
									  <option value="TOUS" selected>TOUS</option>									 
									  <c:forEach items="${Pr.rows}" var="mapPr" varStatus="boucle">
										<option value="${mapPr.matricule}">${mapPr.nom} ${mapPr.prenom}</option>
									  </c:forEach>
							 </select>	   
                           </div> 
                            <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Site</label>                                 
                           </div>
                           <div class="col-sm-8 mb-3 mb-sm-0">
                            <select id="sites" name="sit" class="form-control">
									
								</select>
                           </div> 
                            <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Type</label>                                 
                           </div>
                           <div class="col-sm-8 mb-3 mb-sm-0">
                             <select name="typ" required class="form-control">
									<option value="" selected></option>
									<option value="SYNTHESE DES OPERATIONS">SYNTHESE DES OPERATIONS</option>
									<option value="DETAILS DES PAIEMENTS">DETAILS DES PAIEMENTS</option>									
								</select> 
                           </div> 
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Bilan</label>                                 
                           </div>
                            <div class="col-sm-8 mb-3 mb-sm-0">
                             <select name="type" id="typeBilan" required class="form-control">
									<option value="" selected></option>
									<option value="Bilan_journalier" onclick="documentations('journalier');">Bilan Journalier</option>
									<option value="Bilan_par_date" id="parDate" onclick="documentations('date');">Bilan Par Date</option>
									<option value="Bilan_customise" onclick="documentations('custom');">Bilan Customisé</option>
									<option value="Bilan_général" onclick="documentations('general');">Bilan Général</option>
								</select> 
                           </div> 
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Date Debut</label>                                 
                           </div>
                           <div class="col-sm-8 mb-3 mb-sm-0">    
                             <input type="date" id="debut" onclick="documentations('deb');" disabled class="form-control" name="debut">
                           </div>
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Date Fin</label>                                 
                           </div>
                           <div class="col-sm-8 mb-3 mb-sm-0">    
                            <input type="date" id="fin" onclick="documentations('fn');" class="form-control" disabled name="fin">
                           </div>
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>                       
                      </div>
                  </form>
                 </div>
               </div>
              </div>
              
           <!-- ============ ============ ============ Etat des Paiements des Locataires ============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_2" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat des Paiements des Locataires</h3>
                      </div>
                      <form action="<c:url value="/Etat_Paiement"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-6 mb-3 mb-sm-0">
                             <label for="date_naiss" class="">Année</label>
                                <sql:query var="An" dataSource="jdbc/gestions_imm">
								select * from calendrier_paiement  group by annee 
							 </sql:query>
					         <select name="ans" required class="form-control">
									  <option value="<c:out value="${calendrier_paiement.annee}"/>" selected></option>
									  <c:forEach items="${An.rows}" var="mapAn" varStatus="boucle">
										<option value="${mapAn.annee}">${mapAn.annee}</option>
									  </c:forEach>
							 </select>	  
                           </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                               <label for="date_naiss" class="">Site</label>
                                <sql:query var="Sit" dataSource="jdbc/gestions_imm">
								select * from site 
							 </sql:query>
					         <select name="sit" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Sit.rows}" var="mapSit" varStatus="boucle">
										<option value="${mapSit.site}">${mapSit.site}</option>
									  </c:forEach>
							 </select>	 
							 <input type="hidden" name="typ" value="Etat des Paiements des Locataires">  
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="EtatP" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>       
              
    <!-- ============ ============ ============ Etat des arriérés des Locataires ============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_3" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat des Arriérés des Locataires</h3>
                      </div>
                      <form action="<c:url value="/Etat_Paiement"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-6 mb-3 mb-sm-0">
                             <label for="date_naiss" class="">Année</label>
                                <sql:query var="An" dataSource="jdbc/gestions_imm">
								select * from calendrier_paiement  group by annee 
							 </sql:query>
					         <select name="ans" required class="form-control">
									  <option value="<c:out value="${calendrier_paiement.annee}"/>" selected></option>
									  <c:forEach items="${An.rows}" var="mapAn" varStatus="boucle">
										<option value="${mapAn.annee}">${mapAn.annee}</option>
									  </c:forEach>
							 </select>	  
                           </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                               <label for="date_naiss" class="">Site</label>
                                <sql:query var="Sit" dataSource="jdbc/gestions_imm">
								select * from site 
							 </sql:query>
					         <select name="sit" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Sit.rows}" var="mapSit" varStatus="boucle">
										<option value="${mapSit.site}">${mapSit.site}</option>
									  </c:forEach>
							 </select>	 
							 <input type="hidden" name="typ" value="Etat des Arriérés des Locataires">  
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="EtatA" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>                  
              
              
              
              
              
              
              
              <!-- ============ ============ ============DETAILS DES PAIEMENTS   ============ ============ =========================== -->         
           <div class="modal fade" id="lap_jual_pertanggalss_2" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Détails Des Paiements </h3>
                      </div>
                      <form action="<c:url value="/Synthese_Paiement"/>" method="post" class="myFormulaire" target="_blank" name="formBilans">
                        <input type="hidden" name="typ" value="detail">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Bilan</label>                                 
                           </div>
                            <div class="col-sm-8 mb-3 mb-sm-0">
                             <select name="type" id="typeBilans" required class="form-control">
									<option value="" selected></option>
									<option value="Bilan_journalier" onclick="documentation('journaliers');">Bilan Journalier</option>
									<option value="Bilan_par_date" id="parDate" onclick="documentation('dates');">Bilan Par Date</option>
									<option value="Bilan_customise" onclick="documentation('customs');">Bilan Customisé</option>
									<option value="Bilan_général" onclick="documentation('generals');">Bilan Général</option>
								</select> 
                           </div> 
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Date Debut</label>                                 
                           </div>
                           <div class="col-sm-8 mb-3 mb-sm-0">    
                             <input type="date" id="debuts" onclick="documentation('debs');" disabled class="form-control" name="debut">
                           </div>
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Date Fin</label>                                 
                           </div>
                           <div class="col-sm-8 mb-3 mb-sm-0">    
                            <input type="date" id="fins" onclick="documentation('fns');" class="form-control" disabled name="fin">
                           </div>
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>                       
                      </div>
                  </form>
                 </div>
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
		
		if (mode == 'comptabilite')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'documentCompta')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'ouvertureCaisse')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
		if (mode == 'fermetureCaisse')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
 }
 

			  
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