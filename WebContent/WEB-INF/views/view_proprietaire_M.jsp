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
		<title> GESTIONS PROPRIETAIRE-MORAL</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER PROPRIETAIRE-MORAL   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_proprietaire_m"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
           <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LIBELLE <span class="text-danger ml-2">*</span></label>
         <input type="text" name="agenc" class="form-control" required  value="<c:out value="${proprietaire_m.nom}"/>">
        </div>                           
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE</label>
        <input type="text" name="adresse" class="form-control" value="<c:out value="${proprietaire_m.adresse}"/>">
        </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE POSTALE</label>
        <input type="text" name="adresse_postal" class="form-control" value="<c:out value="${proprietaire_m.adresse_postal}"/>">
        </div>
      </div>
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 1</label>
		<input type="text" name="tel" class="form-control" value="<c:out value="${proprietaire_m.tel}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT 2</label>
		<input type="text" name="tels" class="form-control" value="<c:out value="${proprietaire_m.tels}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">TEL FAX</label>
        <input type="text" name="tel_fax" class="form-control" value="<c:out value="${proprietaire_m.tel_fax}"/>">
        </div>        
      </div>
       <div class="form-group row">      
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="<c:out value="${proprietaire_m.email}"/>">
        </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LOCALISATION</label>
		<input type="text" name="localisation" class="form-control" value="<c:out value="${proprietaire_m.localisation}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">POSITION GPS </label>
	     <input type="text" name="gps" class="form-control" value="<c:out value="${proprietaire_m.gps}"/>">  
        </div>
      </div>       
      <div class="form-group row">      
       <div class="col-sm-4 mb-2 mb-sm-0">
       <label for="platbus" class="">DIRECTEUR</label>
	   <input type="text" name="directeur" class="form-control" value="<c:out value="${proprietaire_m.directeur}"/>">
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">DATE CREATION</label>
		<input type="date" name="date_ajout" class="form-control" value="<c:out value="${proprietaire_m.date_ajout}"/>">
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SITE WEB </label>
	     <input type="text" name="site_web" class="form-control" value="<c:out value="${proprietaire_m.site_web}"/>">  
        </div> 
      </div>
      <div class="form-group row">             
          <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">Taux Pourcentage</label>
	     <input type="text" name="taux" class="form-control" value="<c:out value="${proprietaire_m.taux}"/>" onkeypress="return onlyNumberKey(event)"/>
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">Durre Contrat</label>
		<input type="text" name="dure_contrat" class="form-control" value="<c:out value="${proprietaire_m.dure_contrat}"/>" onkeypress="return onlyNumberKey(event)"/>
       </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">Type Contrat</label>
		<select name="type" class="form-control" required>
			<option value="<c:out value="${proprietaire_m.type_contrat}"/>" selected>${proprietaire_m.type_contrat}</option>
			<option value="CONTRAT">CONTRAT</option>							   								
	   </select>
       </div>           
      </div> 
      <div class="form-group row"> 
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">TELECHARGE LE LOGO </label>
	      <img alt="${proprietaire_m.logo}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${proprietaire_m.logo!=null?proprietaire_m.logo:'erreur.jpg'}" style="font-family:bold; color:#000;">
		 <input type="file" id="file" name="logo" onchange="addPhoto('ecole');" accept=".png,.jpg,.jpeg" >
       </div>
      </div>
    
      </div>
      <input type="hidden" name="id" value="<c:out value="${proprietaire_m.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=proprietaire_m"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_AG==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
              </c:if>  
              
              <c:if test="${sessionScope.rolePr.modif_LO==1}">             
                  <a  class="btn btn btn-warning float-center" href="#etat_3" data-toggle="modal">Ajouter Un Document</a> 
                    
                  <a  style="margin-left:20px;" class="btn btn-primary float-right" href="#etat_1" data-toggle="modal">EN CAS D'URGENCE</a>
                
                <a class="btn btn-info float-right" href="#etat_2" data-toggle="modal">PERSONNE AYANT DROIT</a>                                                 
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
	
	<!-- ============ ============ ============ DOCUMENT============ ============ =========================== --> 
           <div class="modal fade  ${block}" id="etat_3" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:950px;margin-left:-85px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Ajout Document</h3>
                      </div>                   
                        <div class="modal-body">                                             
                            <form action="<c:url value="/view_proprietaire_m"/>"   method="post"  class="myFormulaire" enctype="multipart/form-data">
				               <div class="form-group row">        
						         <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">LIBELLE </label>
							     <input type="text" name="nomC" class="form-control" value="<c:out value="${proprietaire_m.nom}"/>" disabled>
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">ADRESSE </label>
							     <input type="text" name="prenomC" class="form-control" value="<c:out value="${proprietaire_m.adresse}"/>" disabled>
						       </div>						            		
								<div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">DIRECTEUR</label>
								 <input type="text" name="fonctionC" class="form-control" value="<c:out value="${proprietaire_m.directeur}"/>" disabled>
								</div>		
						      </div>  
						       <div class="form-group row">        
						         <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">TYPE DOCUMENT </label>
							     <input type="text" name="type_doc" class="form-control" value="">
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">DATE AJOUT </label>
							     <input type="date" name="date" class="form-control" value="">
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">CHEMIN DU FICHIER </label>
							     <input type="file" required  class="form-control" name="file" id="file" >
						       </div>        												
						      </div>
						       <input type="hidden" name="client" value="<c:out value="${proprietaire_m.matricule}"/>">
						       <div class="modal-footer">							        
						         <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>					       
						        <button name="crud" value="AJOUTER" class="btn btn-success">Sauvegarder</button>
						      </div>
                            </form>
                            
                             <div class="table" style="margin-bottom:0;">
							 <div class="thead">
							  <div class="tr ht">
								<div class="td" style="width:20%;">TYPE DOCUMENT</div>
								<div class="td" style="width:20%;">DATE AJOUT</div>								
								<div class="td" style="width:10%;">ACTION</div>
							  </div>
						   </div>
						  </div>
						  						   
						    <div  id="myTableU" style="height:110px !important;">
							  <div class="table" style="margin-bottom:0;">
								<div class="tbody" id="tbody">
								 <sql:query var="listDoc" dataSource="jdbc/gestions_imm">
									select * from document where client="${proprietaire_m.matricule}" 
								</sql:query>
								 <c:forEach items="${listDoc.rows}" var="mapDoc" varStatus="boucle">
									<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
										<div class="td tdCenter" style="width:20%;">${mapDoc.type_doc}</div>
										<div class="td tdCenter" style="width:20%;">${mapDoc.date_ajout}</div>										
										<div class="td tdCenter" style="width:10%;">
										 <a target="_BLANK" href="<c:url value="/view_proprietaire_m?id=${mapDoc.id}&crud=doc"/>" style="border:1px solid grey;border-radius:3px;padding:4px;color:#000;"><i class="fa fa-print fa-lg"></i></a>
										 <button onclick="document.location.href='<c:url value="/view_proprietaire_m?id=${mapDoc.id}&matri=${mapDoc.client}&page=${page}&crud=ids" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
										</div>
									</div>
								</c:forEach>
							  </div>
							</div>
					    </div>
                  </div>                           
                 </div>
               </div>
              </div>     
	
	<!-- ============ ============ ============ CAS UGENCE============ ============ =========================== --> 
           <div class="modal fade  ${block}" id="etat_1" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:950px;margin-left:-85px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Personne à Contacter En Cas D'Urgence</h3>
                      </div>                   
                        <div class="modal-body">                                             
                            <form action="<c:url value="/droit_casM"/>"  method="post"  class="myFormulaire">
				               <div class="form-group row">        
						         <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">NOM </label>
							     <input type="text" name="nomC" class="form-control" value="">
						       </div>
						        <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">PRENOM </label>
							     <input type="text" name="prenomC" class="form-control" value="">
						       </div>
						        <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">SEXE </label>
							     <select name="sexeC" required class="form-control">
									<option value="" selected></option>
									<option value="MASCULIN">MASCULIN</option>
									<option value="FEMININ">FEMININ</option>
								</select>
						       </div>        		
								<div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">FONCTION</label>
								 <input type="text" name="fonctionC" class="form-control" value="">
								</div>		
						      </div>  
						       <div class="form-group row">        
						         <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">EMAIL </label>
							     <input type="text" name="emailC" class="form-control" value="">
						       </div>
						        <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">ADRESSE </label>
							     <input type="text" name="adresseC" class="form-control" value="">
						       </div>
						        <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">CONTACT 1 </label>
							      <input type="text" name="telC" class="form-control" value="">
						       </div>        		
								<div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">CONTACT 2</label>
								 <input type="text" name="telsC" class="form-control" value="">
								</div>		
						      </div>						      						     						      						      
						       <input type="hidden" name="proprietaireC" value="<c:out value="${proprietaire_m.matricule}"/>">
						       <div class="modal-footer">	
						        <a  class="btn btn btn-warning float-left" href="#etat_4" data-toggle="modal">Ajouter Un Document</a>	
						         <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>					       
						        <button name="crud" value="cas" class="btn btn-success">Sauvegarder</button>
						      </div>
                            </form>
 
                             <div class="table" style="margin-bottom:0;">
							 <div class="thead">
							  <div class="tr ht">
								<div class="td" style="width:20%;">NOM</div>
								<div class="td" style="width:20%;">PRENOM</div>
								<div class="td" style="width:20%;">FONCTION</div>
								<div class="td" style="width:15%;">CONTACT 1</div>
								<div class="td" style="width:15%;">CONTACT 2</div>
								<div class="td" style="width:10%;">ACTION</div>
							  </div>
						   </div>
						  </div>
						  						   
						    <div  id="myTableU" style="height:110px !important;">
							  <div class="table" style="margin-bottom:0;">
								<div class="tbody" id="tbody">
								<sql:query var="listUr" dataSource="jdbc/gestions_imm">
									select * from urgence_p where proprietaire="${proprietaire.matricule}" 
								</sql:query>
								 <c:forEach items="${listUr.rows}" var="mapPM" varStatus="boucle">
									<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
										<div class="td tdCenter" style="width:20%;">${mapPM.nom}</div>
										<div class="td tdCenter" style="width:20%;">${mapPM.prenom}</div>
										<div class="td tdCenter" style="width:20%;">${mapPM.fonction}</div>
										<div class="td tdCenter" style="width:15%;">${mapPM.tel}</div>
										<div class="td tdCenter" style="width:15%;">${mapPM.tels}</div>
										<div class="td tdCenter" style="width:10%;">
										 <button onclick="document.location.href='<c:url value="/view_proprietaire_m?id=${mapPM.id}&matri=${mapPM.proprietaire}&page=${page}&crud=idUr" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
										</div>
									</div>
								</c:forEach>
							  </div>
							</div>
					    </div>
					
					
                  </div>                           
                 </div>
               </div>
              </div>     
<!-- ============ ============ ============ AYANT DROIT============ ============ =========================== --> 
           <div class="modal fade  ${block}" id="etat_2" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:950px;margin-left:-85px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Personne Ayant Droit</h3>
                      </div>                   
                        <div class="modal-body">                                             
                           <form action="<c:url value="/droit_casM"/>"   method="post"  class="myFormulaire">
				               <div class="form-group row">        
						         <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">NOM </label>
							     <input type="text" name="nomA" class="form-control" value="">
						       </div>
						        <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">PRENOM </label>
							     <input type="text" name="prenomA" class="form-control" value="">
						       </div>
						        <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">SEXE </label>
							     <select name="sexeA" required class="form-control">
									<option value="" selected></option>
									<option value="MASCULIN">MASCULIN</option>
									<option value="FEMININ">FEMININ</option>
								</select>
						       </div>        		
								<div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">FONCTION</label>
								 <input type="text" name="fonctionA" class="form-control" value="">
								</div>		
						      </div>  
						       <div class="form-group row">        
						         <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">EMAIL </label>
							     <input type="text" name="emailA" class="form-control" value="">
						       </div>
						        <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">ADRESSE </label>
							     <input type="text" name="adresseA" class="form-control" value="">
						       </div>
						        <div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">CONTACT 1 </label>
							      <input type="text" name="telA" class="form-control" value="">
						       </div>        		
								<div class="col-sm-3 mb-2 mb-sm-0">
						         <label for="platbus" class="">CONTACT 2</label>
								 <input type="text" name="telsA" class="form-control" value="">
								</div>		
						      </div>
						        <input type="hidden" name="proprietaireA" value="<c:out value="${proprietaire_m.matricule}"/>">
						       <div class="modal-footer">	
						        <a  class="btn btn btn-warning float-left" href="#etat_5" data-toggle="modal">Ajouter Un Document</a>	
						         <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>					       
						        <button name="crud" value="ayantD" class="btn btn-success">Sauvegarder</button>
						      </div>
                            </form>
                            
                             <div class="table" style="margin-bottom:0;">
							 <div class="thead">
							  <div class="tr ht">
								<div class="td" style="width:20%;">NOM</div>
								<div class="td" style="width:20%;">PRENOM</div>
								<div class="td" style="width:20%;">FONCTION</div>
								<div class="td" style="width:15%;">CONTACT 1</div>
								<div class="td" style="width:15%;">CONTACT 2</div>
								<div class="td" style="width:10%;">ACTION</div>
							  </div>
						   </div>
						  </div>
						  
						    <div  id="myTableU" style="height:110px !important;">
							  <div class="table" style="margin-bottom:0;">
								<div class="tbody" id="tbody">
								   <sql:query var="listDr" dataSource="jdbc/gestions_imm">
									select * from droit_p where proprietaire="${proprietaire.matricule}" 
								</sql:query>
								 <c:forEach items="${listDr.rows}" var="mapDR" varStatus="boucle">
									<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
										<div class="td tdCenter" style="width:20%;">${mapDR.nom}</div>
										<div class="td tdCenter" style="width:20%;">${mapDR.prenom}</div>
										<div class="td tdCenter" style="width:20%;">${mapDR.fonction}</div>
										<div class="td tdCenter" style="width:15%;">${mapDR.tel}</div>
										<div class="td tdCenter" style="width:15%;">${mapDR.tels}</div>
										<div class="td tdCenter" style="width:10%;">
										 <button onclick="document.location.href='<c:url value="/view_proprietaire_m?id=${mapDR.id}&matri=${mapDR.proprietaire}&page=${page}&crud=idDR" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
										</div>
									</div>
								</c:forEach>
							  </div>
							</div>
					    </div>
                  </div>                           
                 </div>
               </div>
              </div> 
              
              
        <!-- ============ ============ ============ DOCUMENT URGENCE============ ============ =========================== --> 
           <div class="modal fade  ${block}" id="etat_4" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:950px;margin-left:-85px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Ajout Document</h3>
                      </div>                   
                        <div class="modal-body">                                             
                            <form action="<c:url value="/view_proprietaire_m"/>"   method="post"  class="myFormulaire" enctype="multipart/form-data">
				               <div class="form-group row">        
						         <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">NOM </label>
							     <input type="text" name="nomC" class="form-control" value="<c:out value="${urgence_p.nom}"/>" disabled>
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">PRENOM </label>
							     <input type="text" name="prenomC" class="form-control" value="<c:out value="${urgence_p.prenom}"/>" disabled>
						       </div>						            		
								<div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">FONCTION</label>
								 <input type="text" name="fonctionC" class="form-control" value="<c:out value="${urgence_p.fonction}"/>" disabled>
								</div>		
						      </div>  
						       <div class="form-group row">        
						         <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">TYPE DOCUMENT </label>
							     <input type="text" name="type_doc" class="form-control" value="">
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">DATE AJOUT </label>
							     <input type="date" name="date" class="form-control" value="">
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">CHEMIN DU FICHIER </label>
							     <input type="file" required  class="form-control" name="file" id="file" >
						       </div>        												
						      </div>
						       <input type="hidden" name="client" value="<c:out value="${urgence_p.matricule}"/>">
						       <div class="modal-footer">	
						         <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>					       
						        <button name="crud" value="AJOUTER" class="btn btn-success">Sauvegarder</button>
						      </div>
                            </form>
                            
                             <div class="table" style="margin-bottom:0;">
							 <div class="thead">
							  <div class="tr ht">
								<div class="td" style="width:20%;">TYPE DOCUMENT</div>
								<div class="td" style="width:20%;">DATE AJOUT</div>								
								<div class="td" style="width:10%;">ACTION</div>
							  </div>
						   </div>
						  </div>
						  						   
						    <div  id="myTableU" style="height:110px !important;">
							  <div class="table" style="margin-bottom:0;">
								<div class="tbody" id="tbody">
								 <sql:query var="listDoc" dataSource="jdbc/gestions_imm">
									select * from document where client="${proprietaire_m.matricule}" 
								</sql:query>
								 <c:forEach items="${listDoc.rows}" var="mapDoc" varStatus="boucle">
									<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
										<div class="td tdCenter" style="width:20%;">${mapDoc.type_doc}</div>
										<div class="td tdCenter" style="width:20%;">${mapDoc.date_ajout}</div>										
										<div class="td tdCenter" style="width:10%;">
										 <a target="_BLANK" href="<c:url value="/view_proprietaire_m?id=${mapDoc.id}&crud=doc"/>" style="border:1px solid grey;border-radius:3px;padding:4px;color:#000;"><i class="fa fa-print fa-lg"></i></a>
										 <button onclick="document.location.href='<c:url value="/view_proprietaire_m?id=${mapDoc.id}&matri=${mapDoc.client}&page=${page}&crud=ids" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
										</div>
									</div>
								</c:forEach>
							  </div>
							</div>
					    </div>
                  </div>                           
                 </div>
               </div>
              </div>            
              
               <!-- ============ ============ ============ DOCUMENT DROIT============ ============ =========================== --> 
           <div class="modal fade  ${block}" id="etat_5" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:950px;margin-left:-85px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Ajout Document</h3>
                      </div>                   
                        <div class="modal-body">                                             
                            <form action="<c:url value="/view_proprietaire_m"/>"   method="post"  class="myFormulaire" enctype="multipart/form-data">
				               <div class="form-group row">        
						         <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">NOM </label>
							     <input type="text" name="nomC" class="form-control" value="<c:out value="${droit_p.nom}"/>" disabled>
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">PRENOM </label>
							     <input type="text" name="prenomC" class="form-control" value="<c:out value="${droit_p.prenom}"/>" disabled>
						       </div>						            		
								<div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">FONCTION</label>
								 <input type="text" name="fonctionC" class="form-control" value="<c:out value="${droit_p.fonction}"/>" disabled>
								</div>		
						      </div>   
						       <div class="form-group row">        
						         <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">TYPE DOCUMENT </label>
							     <input type="text" name="type_doc" class="form-control" value="">
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">DATE AJOUT </label>
							     <input type="date" name="date" class="form-control" value="">
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">CHEMIN DU FICHIER </label>
							     <input type="file" required  class="form-control" name="file" id="file" >
						       </div>        												
						      </div>
						       <input type="hidden" name="client" value="<c:out value="${droit_p.matricule}"/>">
						       <div class="modal-footer">	
						         <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>					       
						        <button name="crud" value="AJOUTER" class="btn btn-success">Sauvegarder</button>
						      </div>
                            </form>
                            
                             <div class="table" style="margin-bottom:0;">
							 <div class="thead">
							  <div class="tr ht">
								<div class="td" style="width:20%;">TYPE DOCUMENT</div>
								<div class="td" style="width:20%;">DATE AJOUT</div>								
								<div class="td" style="width:10%;">ACTION</div>
							  </div>
						   </div>
						  </div>
						  						   
						    <div  id="myTableU" style="height:110px !important;">
							  <div class="table" style="margin-bottom:0;">
								<div class="tbody" id="tbody">
								 <sql:query var="listDoc" dataSource="jdbc/gestions_imm">
									select * from document where client="${proprietaire_m.matricule}" 
								</sql:query>
								 <c:forEach items="${listDoc.rows}" var="mapDoc" varStatus="boucle">
									<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
										<div class="td tdCenter" style="width:20%;">${mapDoc.type_doc}</div>
										<div class="td tdCenter" style="width:20%;">${mapDoc.date_ajout}</div>										
										<div class="td tdCenter" style="width:10%;">
										 <a target="_BLANK" href="<c:url value="/view_proprietaire_m?id=${mapDoc.id}&crud=doc"/>" style="border:1px solid grey;border-radius:3px;padding:4px;color:#000;"><i class="fa fa-print fa-lg"></i></a>
										 <button onclick="document.location.href='<c:url value="/view_proprietaire_m?id=${mapDoc.id}&matri=${mapDoc.client}&page=${page}&crud=ids" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
										</div>
									</div>
								</c:forEach>
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