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
	background: #00ff00;
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">GESTION DES RAPPORTS</h6>
        </div>
        <div class="card-body">
          <div class="form-group row bmenu">
             <div class="col-sm-4 mb-3 mb-sm-0"> 
	             <c:if test="${sessionScope.rolePr.coutM==1}">
	                <a class="btn btn-info" href="#lap_jual_pertanggal_1" data-toggle="modal">Côut Mensuelle Des Loyer</a>
	             </c:if>                 
                 <c:if test="${sessionScope.rolePr.coutM==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if> 
             </div>            
             <div class="col-sm-4 mb-3 mb-sm-0">    
                <c:if test="${sessionScope.rolePr.etat_FL==1}">
	               <a class="btn btn-info" href="#lap_jual_pertanggal_2" data-toggle="modal">Etat Financier Par Locataire</a>
	             </c:if> 
	              <c:if test="${sessionScope.rolePr.etat_FL==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>                         
             </div>
              <div class="col-sm-4 mb-3 mb-sm-0">
                 <c:if test="${sessionScope.rolePr.etat_FS==1}">
	                   <a class="btn btn-info" href="#lap_jual_pertanggal_3" data-toggle="modal">Etat Financier Par Site</a>
	             </c:if>  
	              <c:if test="${sessionScope.rolePr.etat_FS==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>             
             </div>                                       
          </div>
          
          <!-- ============ ============ ============ COUT MENSUELLE DES LOYERS  ============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_1" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Côut Mensuelle Des Loyer Par Site</h3>
                      </div>
                      <form action="<c:url value="/Etat_Bilan"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Libelle Site</label>      
                           </div>
                            <div class="col-sm-8 mb-3 mb-sm-0">
                            <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from site where site="${sessionScope.site.site}" 
							 </sql:query>
					         <select name="site" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.site}">${mapS.site}</option>
									  </c:forEach>
							 </select>	
							  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="coutM" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>
              
          <!-- ============ ============ ============ ETAT FINACIER INDIVIDUEL============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_2" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat Financier Par Locataire</h3>
                      </div>
                      <form action="<c:url value="/Etat_Financier"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-5 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Nom Prenom</label>      
                           </div>
                            <div class="col-sm-7 mb-4 mb-sm-0">
                            <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from locataire where site="${sessionScope.site.site}" 
							 </sql:query>
					         <select name="matE" required class="form-control">
									  <option value="<c:out value="${locataire.matricule}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.matricule}">${mapS.nom} ${mapS.prenom}</option>
									  </c:forEach>
							 </select>	 							
							  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>						
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button class="btn btn-success" name="type" value="locataire"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>
       
        <!-- ============ ============ ============ ETAT FINACIER PAR SITE============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_3" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat Financier Par Site</h3>
                      </div>
                      <form action="<c:url value="/Etat_Financier"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Libelle Site</label>      
                           </div>
                            <div class="col-sm-8 mb-4 mb-sm-0">
                             <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from site where site="${sessionScope.site.site}" 
							 </sql:query>
					         <select name="site" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.site}">${mapS.site}</option>
									  </c:forEach>
							 </select>	
							    <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="site" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>   
          
        <!------------------------------------------ DEUXIEME LIGNE----------------------------------------- -->  
          <div class="form-group row bmenu">
              <div class="col-sm-4 mb-3 mb-sm-0">  
                <c:if test="${sessionScope.rolePr.etat_FA==1}">
	               <a class="btn btn-info" href="#lap_jual_pertanggal_4" data-toggle="modal">Etat Financier Annuelle par Locataire</a>
	             </c:if>  
	             <c:if test="${sessionScope.rolePr.etat_FA==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>                               
             </div>            
             <div class="col-sm-4 mb-3 mb-sm-0">  
               <c:if test="${sessionScope.rolePr.etat_FM==1}">
	                <a class="btn btn-info" href="#lap_jual_pertanggal_5" data-toggle="modal">Etat Financier Mensuelle par Locataire</a>
	             </c:if>  
	             <c:if test="${sessionScope.rolePr.etat_FM==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>                           
             </div>
              <div class="col-sm-4 mb-3 mb-sm-0">
                  <c:if test="${sessionScope.rolePr.fiche_ID==1}">
	              <a class="btn btn-info" href="#lap_jual_pertanggal_6" data-toggle="modal">Fiche d'Identification Par Site</a>
	             </c:if>
	              <c:if test="${sessionScope.rolePr.fiche_ID==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>              
             </div>
          </div>
          
          <!-- ============ ============ ============ ETAT FINACIER PAR ANNEE============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_4" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat Financier Par Annee</h3>
                      </div>
                      <form action="<c:url value="/Etat_Financier"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-7 mb-5 mb-sm-0">
                            <label for="date_naiss" class="">Libelle Site</label>    
                              <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from site where site="${sessionScope.site.site}" 
							 </sql:query>
					         <select name="site" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.site}">${mapS.site}</option>
									  </c:forEach>
							 </select>	  
                           </div>
                            <div class="col-sm-5 mb-3 mb-sm-0">
                               <label for="date_naiss" class="">Calendrier d'Entrée</label>
                                <sql:query var="Cal" dataSource="jdbc/gestions_imm">
								select * from calendrier_paiement where site="${sessionScope.site.site}" group by annee 
							 </sql:query>
					         <select name="ans" required class="form-control">
									  <option value="<c:out value="${calendrier_paiement.annee}"/>" selected></option>
									  <c:forEach items="${Cal.rows}" var="mapCal" varStatus="boucle">
										<option value="${mapCal.annee}">${mapCal.annee}</option>
									  </c:forEach>
							 </select>	 
							    <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="annee" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>   
              
           <!-- ============ ============ ============ ETAT FINACIER PAR MOIS============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_5" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat Financier Par Mois</h3>
                      </div>
                      <form action="<c:url value="/Etat_Financier"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-6 mb-3 mb-sm-0">
                             <label for="date_naiss" class="">Calendrier d'Entrée</label>
                                <sql:query var="Cal" dataSource="jdbc/gestions_imm">
								select * from calendrier_paiement where site="${sessionScope.site.site}" group by annee 
							 </sql:query>
					         <select name="ans" required class="form-control">
									  <option value="<c:out value="${calendrier_paiement.annee}"/>" selected></option>
									  <c:forEach items="${Cal.rows}" var="mapCal" varStatus="boucle">
										<option value="${mapCal.annee}">${mapCal.annee}</option>
									  </c:forEach>
							 </select>	  
                           </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                               <label for="date_naiss" class="">Calendrier Mois</label>
                                <sql:query var="Cal" dataSource="jdbc/gestions_imm">
								select * from calendrier_paiement where site="${sessionScope.site.site}" group by mois 
							 </sql:query>
					         <select name="mois" required class="form-control">
									  <option value="<c:out value="${calendrier_paiement.mois}"/>" selected></option>
									  <c:forEach items="${Cal.rows}" var="mapCal" varStatus="boucle">
										<option value="${mapCal.mois}">${mapCal.mois}</option>
									  </c:forEach>
							 </select>	 
							 <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="moisC" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>       
         
       <!-- ============ ============ ============ FICHE D'IDENTIFICATION PAR SITE============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_6" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Fiche d'Identification Par Site</h3>
                      </div>
                      <form action="<c:url value="/Etat_Bilan"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Libelle Site</label>      
                           </div>
                            <div class="col-sm-8 mb-4 mb-sm-0">
                             <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from site where site="${sessionScope.site.site}" 
							 </sql:query>
					         <select name="site" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.site}">${mapS.site}</option>
									  </c:forEach>
							 </select>  
							   <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="ficheI" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>      
          
          
           <!------------------------------------------ TROISIEME LIGNE----------------------------------------- -->  
          <div class="form-group row bmenu">
             <div class="col-sm-4 mb-3 mb-sm-0">  
                <c:if test="${sessionScope.rolePr.tableau_SU==1}">
	                <a class="btn btn-info" href="#lap_jual_pertanggal_7" data-toggle="modal">Tableaux de Suivie des Paiement</a>
	             </c:if>  
	             <c:if test="${sessionScope.rolePr.tableau_SU==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>                              
             </div>            
             <div class="col-sm-4 mb-3 mb-sm-0"> 
                <c:if test="${sessionScope.rolePr.etat_LI==1}">
	                <a class="btn btn-info" href="#lap_jual_pertanggal_8" data-toggle="modal">Etat Des Loyers Impayés</a>
	             </c:if>    
	              <c:if test="${sessionScope.rolePr.etat_LI==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>                              
             </div>
              <div class="col-sm-4 mb-3 mb-sm-0">
                 <c:if test="${sessionScope.rolePr.bilan_CH==1}">
	                 <a class="btn btn-info" href="#lap_jual_pertanggal_9" data-toggle="modal">Bilan Charge Entretien</a>
	             </c:if>
	              <c:if test="${sessionScope.rolePr.bilan_CH==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>            
             </div>
          </div>
          
           <!-- ============ ============ ============ TABLEAUX DE SUIVI DES PAIEMENTS============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_7" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Tableaux de Suivie des Paiement Mensuelle</h3>
                      </div>
                      <form action="<c:url value="/Etat_Bilan"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                           <div class="form-group row">
                           <div class="col-sm-7 mb-5 mb-sm-0">
                            <label for="date_naiss" class="">Libelle Site</label>    
                              <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from site where site="${sessionScope.site.site}" 
							 </sql:query>
					          <select name="site" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.site}">${mapS.site}</option>
									  </c:forEach>
							 </select> 	  
                           </div>
                            <div class="col-sm-5 mb-3 mb-sm-0">
                               <label for="date_naiss" class="">Calendrier Mois</label>
                                <sql:query var="Cal" dataSource="jdbc/gestions_imm">
								select * from calendrier_paiement where site="${sessionScope.site.site}" group by mois 
							 </sql:query>
					         <select name="mois" required class="form-control">
									  <option value="<c:out value="${calendrier_paiement.mois}"/>" selected></option>
									  <c:forEach items="${Cal.rows}" var="mapCal" varStatus="boucle">
										<option value="${mapCal.mois}">${mapCal.mois}</option>
									  </c:forEach>
							 </select>		
							  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>
                           </div>     
                         </div>               
                      </div>
                      <div class="modal-footer"> 
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="TableauS" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>       
          
          <!-- ============ ============ ============ ETAT DES LOYES IMPAYES============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_8" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat Des Loyers Impayés</h3>
                      </div>
                      <form action="<c:url value="/Etat_Bilan"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                           <div class="form-group row">
                          <div class="col-sm-6 mb-3 mb-sm-0">
                               <label for="date_naiss" class="">Calendrier Annee</label>
                                <sql:query var="Cal" dataSource="jdbc/gestions_imm">
								select * from calendrier_paiement where site="${sessionScope.site.site}" group by annee 
							 </sql:query>
					         <select name="ans" required class="form-control">
									  <option value="<c:out value="${calendrier_paiement.annee}"/>" selected></option>
									  <c:forEach items="${Cal.rows}" var="mapCal" varStatus="boucle">
										<option value="${mapCal.annee}">${mapCal.annee}</option>
									  </c:forEach>
							 </select>	  
                           </div>     
                            <div class="col-sm-6 mb-3 mb-sm-0">
                               <label for="date_naiss" class="">Statut Loyé </label>
                                <sql:query var="Cal" dataSource="jdbc/gestions_imm">
								select * from calendrier_paiement where site="${sessionScope.site.site}" group by statut 
							 </sql:query>
					         <select name="statut" required class="form-control">
									  <option value="" selected></option>
									  <option value="impaye" >impaye</option>
							 </select>	
							    <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>
                           </div>     
                         </div>               
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="loyerI" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>       
              
          <!-- ============ ============ ============ ETAT CHARGE ENTRETIENS============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_9" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat Charges Entretiens </h3>
                      </div>
                      <form action="<c:url value="/Etat_Bilan"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                           <div class="form-group row">
                          <div class="col-sm-6 mb-3 mb-sm-0">
                             <label for="date_naiss" class="">Année</label>
                                <sql:query var="Cal" dataSource="jdbc/gestions_imm">
								select * from charges_entretiens where site="${sessionScope.site.site}" group by ans 
							 </sql:query>
					         <select name="ans" required class="form-control">
									  <option value="<c:out value="${charges_entretiens.ans}"/>" selected></option>
									  <c:forEach items="${Cal.rows}" var="mapCal" varStatus="boucle">
										<option value="${mapCal.ans}">${mapCal.ans}</option>
									  </c:forEach>
							 </select>	  
                           </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                               <label for="date_naiss" class=""> Mois</label>
                                <sql:query var="Cal" dataSource="jdbc/gestions_imm">
								select * from charges_entretiens where site="${sessionScope.site.site}" group by mois 
							 </sql:query>
					         <select name="mois" required class="form-control">
									  <option value="<c:out value="${charges_entretiens.mois}"/>" selected></option>
									  <c:forEach items="${Cal.rows}" var="mapCal" varStatus="boucle">
										<option value="${mapCal.mois}">${mapCal.mois}</option>
									  </c:forEach>
							 </select>	 
							 <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">  
							 <input type="hidden" name="code" value="${mapS.code}">
							   </c:forEach>
                           </div>    
                         </div>               
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="chargeE" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
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
</body>
</html>