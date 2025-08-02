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
		<title> DETAIL PAIEMENT LOYER</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">DETAIL PAIEMENT LOYER    <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=paiement_caution"/>"> Retour</a></h6>
        </div>
        <div class="card-body">
           <div class="form-group row">
            
            <div class="col-sm-12 mb-10 mb-sm-0"> 
               <div class="form-group row"> 
                 <div class="col-sm-3 mb-2 mb-sm-0">  
                  <label for="platbus" class="">NOM PRENOM</label>
                   <input type="text" name="" class="form-control" disabled value="<c:out value="${locataire.nom} ${locataire.prenom}"/>">
                 </div>
                 <div class="col-sm-3 mb-2 mb-sm-0">  
                  <label for="platbus" class="">CONTACT</label>
                   <input type="text" name="" class="form-control" disabled value="<c:out value="${locataire.tel}"/>">
                 </div>             
                 <div class="col-sm-3 mb-2 mb-sm-0">  
                  <label for="platbus" class="">FONCTION</label>
                   <input type="text" name="" class="form-control" disabled value="<c:out value="${locataire.fonction}"/>">
                 </div>
                 <div class="col-sm-3 mb-2 mb-sm-0">  
                  <label for="platbus" class="">SITUATION MATRIMONAILE</label>
                  <input type="text" name="" class="form-control" disabled value="<c:out value="${locataire.situation_matr}"/>">
                 </div>                 
               </div>
               <div class="form-group row">  
                  <div class="col-sm-3 mb-2 mb-sm-0">  
                    <label for="platbus" class="">TYPE DE CONTRAT </label>
                    <input type="text" name="" class="form-control" disabled value="<c:out value="${locataire.type_contrat}"/>">
                 </div>
                 <div class="col-sm-3 mb-2 mb-sm-0">  
                   <label for="platbus" class="">PRIX LOYER </label>
                    <input type="text" name="" class="form-control" disabled value="<c:out value="${maison.prix}"/>">
                 </div>            
                  <div class="col-sm-3 mb-2 mb-sm-0">  
                  <label for="platbus" class="">N° PORTE</label>
                  <input type="text" name="" class="form-control" disabled value="<c:out value="${locataire.num_porte}"/>">
                 </div>
                  <div class="col-sm-3 mb-2 mb-sm-0">  
                     <label for="platbus" class="">DATE D'ENTREE </label>
		            <input type="date" name="date_entree"  disabled class="form-control" value="<c:out value="${locataire.date_entree}"/>">
                 </div>                
               </div>               

            </div>       
            
            <div class="col-sm-12 mb-10 mb-sm-0"> 
                 <div class="table-responsive">
		            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
		            <thead class="thead-light">
		                <tr>		                  
		                  <th>Nom Prénom  &nbsp;&nbsp;</th>                                  
		                  <th>Année</th>	
		                  <th>Mois</th>		                 
		                  <th>Montant Loyer</th>	
		                  <th>Montant Encaissé</th>	 
		                  <th>Date Paiement</th>	                                                  
		                  <th>Action</th>
		                </tr>
                  </thead>
                  <tbody>
             
              <c:forEach items="${listPaiL}" var="mapPaiL" varStatus="boucle">
		                <tr>              
		                  <td>${mapPaiL.locataire}</td>
		                  <td>${mapPaiL.annee}</td>  
		                  <td>${mapPaiL.mois}</td> 		                
		                  <td>${mapPaiL.montant_loyer}</td>
		                  <td>${mapPaiL.montant_S}</td> 
		                  <td>${mapPaiL.date_paiement}</td> 		                 		                                                                     
		                  <td align="center">		                 
		                   <a target="_BLANK" href="<c:url value="/Recu_paiement?id=${mapPaiL.id}&matE=${mapPaiL.matricule}&ans=${mapPaiL.annee}&mois=${mapPaiL.mois}&montantS=${mapPaiL.montant_S}&code=AGIL"/>" style="border:1px solid grey;border-radius:3px;padding:4px;color:#000;"><i class="fa fa-print fa-lg"></i></a>
							<button onclick="document.location.href='<c:url value="/view_paiement_loyer?idl=${mapPaiL.id}&id=${locataire.id}&matricule=${mapPaiL.matricule}&ans=${mapPaiL.annee}&mois=${mapPaiL.mois}&montantS=${mapPaiL.montant_S}&pourcA=${mapPaiL.taux_A}&pourcI=${mapPaiL.taux_I}&page=${page}&crud=PaiL" />'">
							<i class="fas fa-trash fa-lg"></i>
							</button>										
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
	</script>   
</body>
</html>