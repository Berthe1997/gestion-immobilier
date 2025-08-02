<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



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
		<title> GESTIONS PAIEMENT</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">
          GESTION PAIEMENT   <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=comptabilite"/>"> Retour</a>          
          </h6> 
         <!--  <b> ${locataire.nom} ${locataire.prenom}</b> -->           
        </div>
        <div class="card-body">
           <div class="form-group row">
            <div class="col-sm-12 mb-10 mb-sm-0"> 
                 <div class="table-responsive">
		            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
		            <thead class="thead-light">
		                <tr>		                  
		                  <th>NOM PRENOM</th>                                  
		                  <th>FONCTION</th>	                                                   
		                  <th>ACTION</th>
		                </tr>
                  </thead>
                  <tbody>
             <sql:query var="listLoc" dataSource="jdbc/gestions_imm">
				select * from locataire where site="${sessionScope.site.site}" AND num_porte <>'' AND archive="0"  order by nom  ;
			</sql:query>
              <c:forEach items="${listLoc.rows}" var="mapLoc" varStatus="boucle">
		                <tr>              
		                  <td>${mapLoc.nom} ${mapLoc.prenom}</td>
		                  <td>${mapLoc.fonction}</td>  	                 		                                                                     
		                  <td align="center">
		                   <button class="btn-success" onclick="document.location.href='<c:url value="/paiement?id=${mapLoc.id}&matricule=${mapLoc.matricule}&page=${page}&crud=Loct" />'">
		                     payé
		                   </button>
		                   <button class="btn-primary" onclick="document.location.href='<c:url value="/view_paiement_loyer?id=${mapLoc.id}&matricule=${mapLoc.matricule}&page=${page}&crud=Loct" />'">
		                    detail
		                   </button>
		                  </td>
		               
		               </tr>
             </c:forEach>
	            </tbody>
	          </table>
	        </div>
            </div>
          </div>   
          
 <!-- ======================== LES BOUTONS PAIEMENT SERVICES ======================================== */    -->             
         
 <!-- ======================== LES BOUTONS PAIEMENT LOYER ======================================== */    -->   
  <c:if test="${locataire != null }">	
          
          <div class="form-group row">
                  <div class="col-sm-12 mb-10 mb-sm-0"> 
                   <div id="alertA"  class="alert-danger" style="display:none;position:absolute;width:450px;text-align:center;padding:5px;font-weight:bold;"> </div> 
                   <div id="alert"  class="alert-danger" style="display:none;position:absolute;width:450px;text-align:center;padding:5px;font-weight:bold;"> </div> 
                     <div class="menuNavPay">
						<div class="headerServ"><span><i class="fa fa-dollar-sign"></i>EFFECTUER UN PAIEMENT</span></div>
							<form action="<c:url value="/paiement?pages=paiement_loyer"/>" method="post" style="border:2px solid #c0c0c0;background-color:lightgray;">
							     <input type="hidden" name="id" value="<c:out value="${locataire.id}"/>">							     							     
							       <input type="hidden" name="matricule" value="<c:out value="${calendrier_paiement.matricule}"/>">								       			    					  
									<input type="hidden" name="montantS" value="<c:out value="${maison.prix}"/>">
									  <input type="hidden" name="ans" value="<c:out value="${calendrier_paiement.annee}"/>">	
														
									<div class="row" style="margin-top:6px;">
									 <div class="col-sm-12">
										 <div class="form-group row">
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Type de Paiement</label>
										    </div>
										    <div class="col-sm-9 mb-6 mb-sm-0">
										       <select name="type"  class="form-control" id="formSelect">	
										        <option value="">choix</option>									         
										          <c:if test="${arrieres != null}">	
										         <option value="arriéres">arriéres</option>
										          </c:if>
										          <c:if test="${locataire.p_C == 0 }">	
										         <option value="caution">Caution</option>
										          </c:if>
										          <c:if test="${locataire.p_CO == 0 }">	
										         <option value="commission">Commission</option>	
										          </c:if>
										          <c:if test="${locataire.p_A == 0 }"> 
												 <option value="avance">Avance</option>		
												  </c:if>
												  <c:if test="${locataire != null }">	
												  <option value="loyer">Loyer</option>	
												  </c:if>			                   							                   							                    					    
											  </select>										         
										    </div>										     										     										    
										  </div>
										 </div>
										 		
		  <!-- ==================== ============ ============ PAIEMENT ARRIERES============ ============ ============================================== --> 							 
										 <div class="col-sm-12 " id="arriéres" style="display: none;">
										  <div class="form-group row">
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Calendrier Arriére</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										     
										       <select class="form-control" id="arriere"  name="idar"  onchange="ajax('getArrieres');">												
												 <option value="01"selected >choix</option>
												 <c:forEach  items="${listAR}" var="mapAR">
											         <option value="<c:out value="${mapAR.id}"/>" > <c:out value="${mapAR.mois}"/></option>
												 </c:forEach>						    
												</select>										         
										    </div>
										     <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Solde Arriére</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">										      
										        <input type="text" name="" style="color:red;" class="form-control" id="soldeARs" disabled  >
								              <input type="hidden" name="" id="soldeAR"  value="">		
								              <input type="hidden" name="moisAR" id="moiAR"  value="">	
								               <input type="hidden" name="ansAR" id="anAR" value="">						   
										    </div>										     										    
										  </div>										 										 
										  <div class="form-group row">	
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<label class="" for="platbus" >Montant</label>
										  </div>
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<input type="text" class="form-control" style="color:red;" name="montantAR" value="" onkeyup="montantSaisiA('saisiA');" id="mttSaisiA" />											   
										   </div>									 	
										  <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Mode de Paiement</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <select class="form-control"name="modeR">
								                    <option value="virement">Virement Bancaire</option>
								                    <option value="carte">Carte Bancaire</option>
								                    <option value="espece">Espèce</option>
								                    <option value="mobile money">Mobile Money</option>
								                </select>
										    </div>	
										   </div>	
										   <div class="form-group row">									  									   									   										 										
									       <div class="col-sm-3 mb-2 mb-sm-0">
									       <label for="platbus" class="">Taux Impôt</label>
											    <sql:query var="pourcentage" dataSource="jdbc/gestions_imm">
													select * from pourcentage where pourcentage='IMPÔT' 
											  </sql:query>
											  <c:forEach items="${pourcentage.rows}" var="lister" varStatus="boucle">
											    <input type="checkbox" checked name="pourcIR"  class="form-control" value="<c:out value="${lister.taux}"/>">
											  </c:forEach>
									       </div>
									       <div class="col-sm-3 mb-2 mb-sm-0">
									        <label for="platbus" class="">Taux Agence</label>
											 <sql:query var="pourcentage" dataSource="jdbc/gestions_imm">
													select * from pourcentage where pourcentage='AGENCE' 
											  </sql:query>
											  <c:forEach items="${pourcentage.rows}" var="lister" varStatus="boucle">
											    <input type="checkbox" checked name="pourcAR"  class="form-control" value="<c:out value="${lister.taux}"/>">
											  </c:forEach>
											  
									       </div>									       
									      </div>	
									       									           
										<div class="form-group row">										  
										   <div class="col-sm-12 mb-8 mb-sm-0" style="text-align:right;">
										   <c:if test="${sessionScope.rolePr.ajout_PL==1}">
										   <button class="btn btn-primary ${locataire.id!=null?'':'disabled'}" name="crud" onclick="return montantSaisiA('envoiA');" value="ARRIERES">ENREGISTRER</button>										
                                            </c:if> 
										  </div>
										</div>	
										  </div>
										
										 								 										 										 
			 <!-- ==================== ============ ============ PAIEMENT CAUTION============ ============ ============================================== --> 							 
										 <div class="col-sm-12 " id="caution" style="display: none;">										 
										  <div class="form-group row">
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<label class="" for="platbus" >Montant</label>
										  </div>
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<input type="text" class="form-control" style="color:red;"  value="<c:out value="${maison.prix}"/> FR" disabled/>											   
										    <input type="hidden" name="montantC" value="<c:out value="${maison.prix}"/>">	
										   </div>	
										  <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Mode de Paiement</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <select class="form-control"name="modeC">
								                    <option value="virement">Virement Bancaire</option>
								                    <option value="carte">Carte Bancaire</option>
								                    <option value="espece">Espèce</option>
								                    <option value="mobile money">Mobile Money</option>
								                </select>
										    </div>												    										    
										     <sql:query var="pourcentage" dataSource="jdbc/gestions_imm">
												select * from  gestion_caution where code="${sessionScope.agence.code}" and site="${sessionScope.site.site}"
											 </sql:query>
											 <c:forEach items="${pourcentage.rows}" var="lister" varStatus="boucle">
											   <input type="hidden" name="montantNB" value="<c:out value="${lister.mois}"/>">
											  </c:forEach>										    														    								  									   									   										 
										</div>																														
										<div class="form-group row">										  
										   <div class="col-sm-12 mb-8 mb-sm-0" style="text-align:right;">
										    <c:if test="${sessionScope.rolePr.ajout_PL==1}">
										   <button class="btn btn-primary ${locataire.id!=null?'':'disabled'}" name="crud" value="CAUTION">ENREGISTRER</button>
										   </c:if> 
										  </div>
										</div>										
									</div>
			
			 <!-- ==================== ============ ============ PAIEMENT COMMISSION============ ============ ============================================== --> 							 
										 <div class="col-sm-12" id="commission" style="display: none;">										 
										  <div class="form-group row">
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<label class="" for="platbus" >Montant</label>
										  </div>
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<input type="text" class="form-control" style="color:red;"  value="<c:out value="${maison.prix}"/> FR" disabled  />											   
										     <input type="hidden" name="commiss" value="<c:out value="${maison.prix}"/>">	
										   </div>	
										  <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Mode de Paiement</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <select class="form-control"name="modeCO">
								                    <option value="virement">Virement Bancaire</option>
								                    <option value="carte">Carte Bancaire</option>
								                    <option value="espece">Espèce</option>
								                    <option value="mobile money">Mobile Money</option>
								                </select>
										    </div>	
										    					  									   									   										 
										</div>																														
										<div class="form-group row">										  
										   <div class="col-sm-12 mb-8 mb-sm-0" style="text-align:right;">
										    <c:if test="${sessionScope.rolePr.ajout_PL==1}">
										   <button class="btn btn-primary ${locataire.id!=null?'':'disabled'}" name="crud" value="COMMISSION">ENREGISTRER</button>
										   </c:if> 
										  </div>
										</div>										
									</div>						
		
		<!-- ==================== ============ ============ PAIEMENT AVANCE============ ============ ============================================== --> 							 
										 <div class="col-sm-12" id="avance" style="display: none;">										 
										  <div class="form-group row">
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<label class="" for="platbus" >Montant</label>
										  </div>
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<input type="text"style="color:red;" class="form-control" disabled value="<c:out value="${maison.prix}"/> FR">											   
										    <input type="hidden" name="montantA" value="<c:out value="${maison.prix}"/>">
										   </div>	
										  <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Mode de Paiement</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <select class="form-control"name="modeAV">
								                    <option value="virement">Virement Bancaire</option>
								                    <option value="carte">Carte Bancaire</option>
								                    <option value="espece">Espèce</option>
								                    <option value="mobile money">Mobile Money</option>
								                </select>
										    </div>										  									   									   										 
										</div>										
										<div class="form-group row">      
									       <div class="col-sm-3 mb-2 mb-sm-0">
									       <label for="platbus" class="">Taux Impôt</label>
										   <sql:query var="pourcentage" dataSource="jdbc/gestions_imm">
													select * from pourcentage where pourcentage='IMPÔT' 
											  </sql:query>
											  <c:forEach items="${pourcentage.rows}" var="lister" varStatus="boucle">
											    <input type="checkbox" checked name="pourcIAV"  class="form-control" value="<c:out value="${lister.taux}"/>">
											  </c:forEach>
									       </div>
									       <div class="col-sm-3 mb-2 mb-sm-0">
									        <label for="platbus" class="">Taux Agence</label>
											 <sql:query var="pourcentage" dataSource="jdbc/gestions_imm">
													select * from pourcentage where pourcentage='AGENCE' 
											  </sql:query>
											  <c:forEach items="${pourcentage.rows}" var="lister" varStatus="boucle">
											    <input type="checkbox" checked name="pourcAV"  class="form-control" value="<c:out value="${lister.taux}"/>">
											  </c:forEach>
									       </div>
									        <div class="col-sm-3 mb-2 mb-sm-0">       
									        <label for="platbus" class="">Nombre de Mois </label>										  
									       </div>
									        <div class="col-sm-3 mb-2 mb-sm-0">       									     
										    <input type="text" class="form-control" name="nbr" value="0" onkeypress="return onlyNumberKey(event)"/> 
									       </div>
									      </div>
										
										<div class="form-group row">										  
										   <div class="col-sm-12 mb-8 mb-sm-0" style="text-align:right;">
										   <c:if test="${sessionScope.rolePr.ajout_PL==1}">
										   <button class="btn btn-primary ${locataire.id!=null?'':'disabled'}" name="crud" value="AVANCE">ENREGISTRER</button>
										  </c:if> 
										  </div>
										</div>										
									</div>							
									
  <!-- ==================== ============ ============ PAIEMENT LOYER============ ============ ============================================== --> 									
									 <div class="col-sm-12" id="loyer" style="display: none;">										 
										  <div class="form-group row">
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Calendrier Paie</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										     
										       <select class="form-control" id="mois"  name="idc"  onchange="ajax('getCalendrier');">												
												 <option value="01"selected >choix</option>
												 <c:forEach  items="${listCP}" var="mapCP">
											         <option value="<c:out value="${mapCP.id}"/>" > <c:out value="${mapCP.mois}"/></option>
												 </c:forEach>						    
												</select>										         
										    </div>
										     <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Solde Loyer</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">										      
										        <input type="text" name="" style="color:red;" class="form-control" id="soldes" disabled value="<c:out value="${calendrier_paiement.montant_R}"/> FR">
								              <input type="hidden" name="montant_R" id="solde"  value="">		
								              <input type="hidden" name="mois" id="moi"  value="">	
								               <input type="hidden" name="an" id="an" value="">						   
										    </div>										     										    
										  </div>										 										 
										  <div class="form-group row">
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<label class="" for="platbus" >Montant</label>
										  </div>
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<input type="text" class="form-control" style="color:red;" name="montanT" value="" onkeyup="montantSaisi('saisi');" id="mttSaisi" />											   
										   </div>	
										  <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Mode de Paiement</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <select class="form-control"name="mode">
								                    <option value="virement">Virement Bancaire</option>
								                    <option value="carte">Carte Bancaire</option>
								                    <option value="espece">Espèce</option>
								                    <option value="mobile money">Mobile Money</option>
								                </select>
										    </div>										  									   									   										 
										</div>										
										<div class="form-group row">  
										   <div class="col-sm-3 mb-2 mb-sm-0">       
									        <label for="platbus" class="">Penalite</label>										  
									       </div>
									        <div class="col-sm-3 mb-2 mb-sm-0">       									     
										    <input type="text" name="penalite" style="color:red;" class="form-control"  value="<c:out value="${calendrier_paiement.penalite}"/> FR" disabled>
									       </div> 
									       <div class="col-sm-3 mb-2 mb-sm-0">
									       <label for="platbus" class="">Taux Impôt</label>
											    <sql:query var="pourcentage" dataSource="jdbc/gestions_imm">
													select * from pourcentage where pourcentage='IMPÔT' 
											  </sql:query>
											  <c:forEach items="${pourcentage.rows}" var="lister" varStatus="boucle">
											    <input type="checkbox" checked name="pourcI"  class="form-control" value="<c:out value="${lister.taux}"/>">
											  </c:forEach>
									       </div>
									       <div class="col-sm-3 mb-2 mb-sm-0">
									        <label for="platbus" class="">Taux Agence</label>
											 <sql:query var="pourcentage" dataSource="jdbc/gestions_imm">
													select * from pourcentage where pourcentage='AGENCE' 
											  </sql:query>
											  <c:forEach items="${pourcentage.rows}" var="lister" varStatus="boucle">
											    <input type="checkbox" checked name="pourcA"  class="form-control" value="<c:out value="${lister.taux}"/>">
											  </c:forEach>
											  
									       </div>									       
									      </div>	
									       									           
										<div class="form-group row">										  
										   <div class="col-sm-12 mb-8 mb-sm-0" style="text-align:right;">
										   <c:if test="${sessionScope.rolePr.ajout_PL==1}">
										   <button class="btn btn-primary ${locataire.id!=null?'':'disabled'}" name="crud" onclick="return montantSaisi('envoi');" value="ENREGISTRER">ENREGISTRER</button>										
                                            </c:if> 
										  </div>
										</div>										
									</div>
									
																		
									</div>							
							</form>
					 </div>
                  </div>
               </div>
        
         </c:if>    
          
           
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
 
 <script type="text/javascript">
 	
	function onlyNumberKey(evt) {
	     
	     // Only ASCII character in that range allowed
	     var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	     if (ASCIICode > 31 && (ASCIICode <48 ||ASCIICode > 57))
	         return false;
	     return true;
	 }
	
		
	 // Récupérer les éléments
	 const select = document.getElementById("formSelect");
	 const forms = {			
			 arriéres: document.getElementById("arriéres"),
			 caution: document.getElementById("caution"),
			 commission: document.getElementById("commission"),
			 avance: document.getElementById("avance"),
			 loyer: document.getElementById("loyer")
			
	 };

	 // Ajouter un écouteur d'événements sur le champ select
	 select.addEventListener("change", function() {
	     // Cacher tous les formulaires
	     for (const key in forms) {
	         forms[key].style.display = "none";
	     }

	     // Afficher le formulaire sélectionné si une option est choisie
	     const selectedForm = select.value;
	     if (forms[selectedForm]) {
	         forms[selectedForm].style.display = "block";
	     }
	 });
	 
	 
	</script>   
</body>
</html>