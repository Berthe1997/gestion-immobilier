<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<sql:query var="listLoc" dataSource="jdbc/gestions_imm">
	select * from locataire where code="${sessionScope.agence.code}" and site="${sessionScope.site.site}" archive="0"
</sql:query>

<sql:query var="listGes" dataSource="jdbc/gestions_imm">
	select * from gestionnaire where code="${sessionScope.agence.code}" and site="${sessionScope.site.site}"
</sql:query>

<sql:query var="listGS" dataSource="jdbc/gestions_imm">
	select * from gestion_service where code="${sessionScope.agence.code}" and site="${sessionScope.site.site}"
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
		<title> GESTIONS DES SERVICE</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">GESTION PAIEMENT SERVICE   <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=comptabilite"/>"> Retour</a></h6> 
        </div>
        <div class="card-body">     
          <div class="form-group row">
                  <div class="col-sm-12 mb-10 mb-sm-0"> 
                   <div id="alert"  class="alert-danger" style="display:none;position:absolute;width:450px;text-align:center;padding:5px;font-weight:bold;"> </div> 
                     <div class="menuNavPay">
						<div class="headerServ"><span><i class="fa fa-dollar-sign"></i>PAYER UN SERVICE</span></div>
							<form action="<c:url value="/Gestion_S"/>" method="post" style="border:2px solid #c0c0c0;background-color:lightgray;">							    
									<div class="row" style="margin-top:6px;">
									 <div class="col-sm-12">
										 <div class="form-group row">
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Type Service</label>
										    </div>
										    <div class="col-sm-9 mb-6 mb-sm-0">
										       <select name="service"  class="form-control" id="formSelect">
										         <option value="service">service</option>
										         <option value="visite">visite</option>											        										        					                   							                   							                    					    
											  </select>										         
										    </div>										     										     										    
										  </div>
										 </div>
																				 										 
			 <!-- ==================== ============ ============ PAIEMENT SERVICE============ ============ ============================================== --> 							 
										 <div class="col-sm-12 ${block}" id="service" >										 
										  <div class="form-group row">
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Locataire</label>
										    </div>
										    <div class="col-sm-5 mb-2 mb-sm-0">
										       <select class="form-control"name="nom">
										         <option value="" selected></option>
												<c:forEach  items="${listLoc.rows}" var="mapLoc">
													<option value="${mapLoc.matricule}">${mapLoc.nom} ${mapLoc.prenom}</option>
												</c:forEach>  
								                </select>
										    </div>	
										  <div class="col-sm-2 mb-2 mb-sm-0">
											<label class="" for="platbus" >Montant</label>
										  </div>
										  <div class="col-sm-2 mb-2 mb-sm-0">
											<input type="text" class="form-control" style="color:red;" name="montantS" value="0" onkeypress="return onlyNumberKey(event)" />											   
										   </div>											 									  									   									   										 
										</div>																														
										<div class="form-group row">										  
										   <div class="col-sm-12 mb-8 mb-sm-0" style="text-align:right;">
										   <button class="btn btn-primary" name="crud" value="SERVICE">ENREGISTRER</button>
										  </div>
										</div>										
									</div>
			
			 <!-- ==================== ============ ============ PAIEMENT COMMISSION============ ============ ============================================== --> 							 
										 <div class="col-sm-12" id="visite" style="display: none;">										 
										  <div class="form-group row">										 	
										  <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Client</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										     <sql:query var="listClIN" dataSource="jdbc/gestions_imm">
												select * from reception_client where code="${sessionScope.agence.code}" and statutP="0"
											 </sql:query>
										       <select class="form-control"name="agent">
										         <option value="" selected></option>
												<c:forEach  items="${listClIN.rows}" var="mapClIN">
													<option value="${mapClIN.matricule}">${mapClIN.nom}</option>
												</c:forEach>  
								                </select>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
											 <label class="" for="platbus" >Type de Visite</label>
										   </div>
										   <div class="col-sm-3 mb-2 mb-sm-0">
											 <select name="type" class="form-control">
													<option value="" selected></option>
													<option value="physique">physique</option>
													<option value="visuelle">visuelle</option>
											   </select>
										   </div>										  									   									   										 
										</div>	
										<div class="form-group row">										 	
										  <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Montant Visite</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <input type="text" name="montant" class="form-control"  value=""onkeypress="return onlyNumberKey(event)">
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
											 <label class="" for="platbus" >Date Visite</label>
										   </div>
										   <div class="col-sm-3 mb-2 mb-sm-0">
											 <input type="date" name="date"   class="form-control" value="">										   
										   </div>										  									   									   										 
										</div>																														
										<div class="form-group row">										  
										   <div class="col-sm-12 mb-8 mb-sm-0" style="text-align:right;">
										   <button class="btn btn-primary" name="crud" value="VISITE">ENREGISTRER</button>
										  </div>
										</div>										
									</div>
									
									 		
											
									
									</div>							
							</form>
					 </div>
                  </div>
               </div>
            
             <div class="form-group row">
             <div class="col-sm-12 mb-10 mb-sm-0"> 
                 <div class="table-responsive">
		            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
		            <thead class="thead-light">
		                <tr>		                  
		                  <th>NOM PRENOM</th>                                  
		                  <th>SERVICE</th>
		                  <th>TYPE</th>	
		                  <th>MONTANT</th>	
		                  <th>DATE</th>			                                                   
		                  <th>ACTION</th>
		                </tr>
                  </thead>
                  <tbody>           
              <c:forEach items="${listGS.rows}" var="mapGS" varStatus="boucle">
		                <tr>              
		                  <td>${mapGS.agent}</td>
		                  <td>${mapGS.service}</td> 
		                  <td>${mapGS.type}</td>
		                  <td>${mapGS.montant}</td> 
		                  <td>${mapGS.date}</td>  	                 		                                                                     
		                  <td align="center">		                  
		                   <button onclick="document.location.href='<c:url value="/Gestion_S?id=${mapGS.id}&type=${mapGS.service}&montant=${mapGS.montant}&date=${mapGS.date}&heure=${mapGS.heure}&matricule=${mapGS.matricule}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
		                  </td>
		               
		               </tr>
             </c:forEach>
	            </tbody>
	          </table>
	        </div>
            </div>
          </div>  
          
           
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
			 service: document.getElementById("service"),
			 visite: document.getElementById("visite")
			
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