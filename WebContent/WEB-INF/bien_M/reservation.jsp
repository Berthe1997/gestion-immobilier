<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from client_bien as c ,reservation as r  where c.matricule=r.client  and montant_net='0'
        
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
		<title>RESERVATION</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">
           PAIEMENT RESERVATION   <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=comptabilite"/>"> Retour</a> <button class="btn btn-info float-right"  onclick="document.location.href='<c:url value="/reserve_R?"/>'"> Liste Reservation</button>          
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
		                  <th>NOM CLIENT</th>
		                  <th>CONTACT</th>
		                  <th>BIEN</th>                                
		                  <th>DESCRIPTION</th> 
		                  <th>LOYER</th>
		                  <th>ADRESSE</th>   
		                  <th>TOTAL SEJOUR</th>                                                   
		                  <th>ACTION</th>
		                </tr>
                  </thead>
                  <tbody>
             <sql:query var="listLoc" dataSource="jdbc/gestions_imm">
				select * from client_bien as c ,reservation as r, bien_meuble as b  
				where c.matricule=r.client and b.bien=r.code_bien   and montant_net='0'
			</sql:query>
              <c:forEach items="${listLoc.rows}" var="mapLoc" varStatus="boucle">
		                <tr>              
		                  <td>${mapLoc.nom} ${mapLoc.prenom}</td>
		                  <td>${mapLoc.tel}</td> 
		                  <td>${mapLoc.code_bien}</td>  	
		                  <td>${mapLoc.description}</td> 
		                  <td>${mapLoc.loyer_nuit} FCFA</td> 
		                  <td>${mapLoc.adresse}</td>
		                  <td>${mapLoc.nombre} jour</td>                      		                                                                     
		                  <td align="center">
		                   <button class="btn-success" onclick="document.location.href='<c:url value="/reserve?id=${mapLoc.id}&bien=${mapLoc.bien}&code=${mapLoc.code_reservation}&page=${page}&crud=Loct" />'">
		                     payé
		                   </button>		                   
		                  </td>
		               
		               </tr>
             </c:forEach>
	            </tbody>
	          </table>
	        </div>
            </div>
          </div> 
          
          <div class="form-group row">
                  <div class="col-sm-12 mb-10 mb-sm-0">                   
                   <div id="alert"  class="alert-danger" style="display:none;position:absolute;width:450px;text-align:center;padding:5px;font-weight:bold;"> </div> 
                     <div class="menuNavPay">
						<div class="headerServ"><span><i class="fa fa-dollar-sign"></i>EFFECTUER UN PAIEMENT</span></div>
							<form action="<c:url value="/reserve?pages=reservation"/>" method="post" style="border:2px solid #c0c0c0;background-color:lightgray;">
							     <input type="hidden" name="client" value="<c:out value="${reservation.client}"/>">
							     <input type="hidden" name="code_R" value="<c:out value="${reservation.code_reservation}"/>">							     							     
							       
							    <div class="row" style="margin-top:6px;">
							      <div class="col-sm-12">																				 
										  <div class="form-group row">
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Loyer Nuit</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										      <input type="text" name="" style="color:red;" class="form-control"  disabled value="<c:out value="${bien_meuble.loyer_nuit}"/> FR">										         
										    </div>
										     <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Montant Total</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">										      
										        <input type="text" name="" id="solde" style="color:red;" class="form-control"   value="<c:out value="${reservation.montant_T}"/>" readonly>					              				   
										    </div>										     										    
										  </div>										 										 
										  <div class="form-group row">
										   <div class="col-sm-3 mb-2 mb-sm-0">
											<label class="" for="platbus" >Reduction</label>
										  </div>
										  <div class="col-sm-3 mb-2 mb-sm-0">										      
										        <input type="text" name="reduction" onkeyup="montantSaisi('saisi');" id="mttSaisi" style="color:red;" class="form-control"   value="<c:out value="${calendrier_paiement.montant_R}"/>">					              				   
										    </div>	
										   <div class="col-sm-3 mb-2 mb-sm-0">
											<label class="" for="platbus" >Montant Net</label>
										  </div>
										  <div class="col-sm-3 mb-2 mb-sm-0">										      
										        <input type="text" name="montant_net" style="color:red;" class="form-control" id="diffe" value="" readonly>					              				   
										    </div>	
										  </div>
										  <div class="form-group row">
										   <div class="col-sm-3 mb-2 mb-sm-0">
											<label class="" for="platbus" >Taux Agence</label>
										  </div>
										  <div class="col-sm-3 mb-2 mb-sm-0">
											<input type="text" class="form-control"  name="taux" value="10" onkeypress="return onlyNumberKey(event)"/>											   
										   </div>	
										  <div class="col-sm-3 mb-2 mb-sm-0">
										       <label class="" for="platbus" >Mode de Paiement</label>
										    </div>
										    <div class="col-sm-3 mb-2 mb-sm-0">
										       <select class="form-control"name="mode">
								                    <option value="virement">Virement Bancaire</option>
								                    <option value="carte">Carte Bancaire</option>
								                    <option value="espece">Espèce</option>
								                </select>
										    </div>										  									   									   										 
										</div>	
										<div class="form-group row">										  
										   <div class="col-sm-12 mb-8 mb-sm-0" style="text-align:right;">
										   <c:if test="${sessionScope.rolePr.ajout_PL==1}">
										   <button class="btn btn-primary ${bien_meuble.id!=null?'':'disabled'}" name="crud" onclick="return montantSaisi('envoi');" value="ENREGISTRER">ENREGISTRER</button>										
                                            </c:if> 
										  </div>
										</div>	
										 				
								  </div>
							     </div>							   
							  </form>
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
 
 const m1 = document.getElementById('solde');
 const m2 = document.getElementById('mttSaisi');
 const diff = document.getElementById('diffe');

 function calculerDifference() {
   const val1 = parseFloat(m1.value) || 0;
   const val2 = parseFloat(m2.value) || 0;
   diff.value = val1 - val2;
 }

 m1.addEventListener('input', calculerDifference);
 m2.addEventListener('input', calculerDifference);

 
  //=======================================NOMBRE=======================================================//	
	function onlyNumberKey(evt) {
	     
	     // Only ASCII character in that range allowed
	     var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	     if (ASCIICode > 31 && (ASCIICode <48 ||ASCIICode > 57))
	         return false;
	     return true;
	 }
	</script> 
</body>
</html>