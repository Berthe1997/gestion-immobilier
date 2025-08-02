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
		<title> CLIENT ACHETEUR</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER CLIENT ACHETEUR </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_client_A"/>" method="post"  class="myFormulaire">            
           <div class="card-body">
       <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="<c:out value="${client_acheteur.nom}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="prenom" class="form-control" required  value="<c:out value="${client_acheteur.prenom}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">GENRE<span class="text-danger ml-2">*</span></label>
        <select name="sexe" required class="form-control">
			<option value="<c:out value="${client_acheteur.sexe}"/>" selected>${client_acheteur.sexe}</option>
			<option value="MASCULIN">MASCULIN</option>
			<option value="FEMININ">FEMININ</option>
		</select>		
        </div>  
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">SITUATION MATRIMONAILE</label>
        <select name="situation_matr"  required class="form-control">
			<option value="<c:out value="${client_acheteur.situation_matr}"/>" selected>${client_acheteur.situation_matr}</option>
			<option value="Celibataire">Celibataire</option>
			<option value="Marie">Marie</option>
			<option value="Divorcer">Divorcer</option>
		</select> 
        </div>                      
      </div>
      <div class="form-group row"> 
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="seat" class="">CNI<span class="text-danger ml-2">*</span></label>
         <input type="text"   name="cni" class="form-control" value="<c:out value="${client_acheteur.cni}"/>">
        </div> 
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">DATE NAISSANCE<span class="text-danger ml-2">*</span></label>
         <input type="date" name="date_naissance" class="form-control" required  value="<c:out value="${client_acheteur.date_naissance}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">NATIONALITE</label>
         <sql:query var="listPays" dataSource="jdbc/gestions_imm">
			select * from pays 
		</sql:query>
         <select name="nationnalite" required class="form-control">
				  <option value="<c:out value="${client_acheteur.nationnalite}"/>" selected><c:out value="${client_acheteur.nationnalite}"/></option>
				  <c:forEach items="${listPays.rows}" var="mapP" varStatus="boucle">
					<option value="${mapP.pays}">${mapP.pays}</option>
				  </c:forEach>
		 </select>        
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PROFESSION</label>
         <input type="text" name="profession" class="form-control" value="<c:out value="${client_acheteur.profession}"/>">
        </div>
       </div>
      <div class="form-group row">  
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE</label>
         <input type="text" name="adresse" class="form-control" value="<c:out value="${client_acheteur.adresse}"/>">
        </div>                 
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">EMAIL</label>
        <input type="text" name="Email" class="form-control" value="<c:out value="${client_acheteur.email}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT</label>
        <input type="text" required name="contact" class="form-control" value="<c:out value="${client_acheteur.contact}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">TERRAIN</label>
         <input type="text" disabled  class="form-control" value="<c:out value="${terrain.libelle}"/>"> 
         <input type="hidden" name="terrain" value="<c:out value="${client_acheteur.terrain}"/>">
        </div>                            
      </div>
       <div class="form-group row"> 
       <div class="col-sm-4 mb-2 mb-sm-0">
	       <label for="platbus" class="">PRIX Honoraire</label>
		   <input type="text" name="honoraire" class="form-control"  style="color:red;" value="<c:out value="${client_acheteur.honoraire}"/>" onkeypress="return onlyNumberKey(event)">
		</div>
       <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">depot_DATTC</label>
	   <input type="checkbox" class="form-control"  name="depot_DATTC" ${client_acheteur.depot_DATTC==1?'checked':''} value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">dossier_techn_CT</label>
		 <input type="checkbox" class="form-control"  name="dossier_techn_CT" ${client_acheteur.dossier_techn_CT==1?'checked':''} value="1"  />
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">dossier_techn_D </label>
	    <input type="checkbox" class="form-control"  name="dossier_techn_D" ${client_acheteur.dossier_techn_D==1?'checked':''} value="1"  /> 
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">attes_CDisp </label>
	    <input type="checkbox" class="form-control"  name="attes_CD" ${client_acheteur.attes_CD==1?'checked':''} value="1"  /> 
       </div>                                   
      </div>            
      <div class="form-group row"> 
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">intro_dem_ACD</label>
	    <input type="checkbox" class="form-control"  name="intro_dem_ACD" ${client_acheteur.intro_dem_ACD==1?'checked':''} value="1"  /> 
       </div>  
       <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">date_BM</label>
	    <input type="checkbox" class="form-control"  name="date_BM" ${client_acheteur.date_BM==1?'checked':''} value="1"  /> 
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">n_DM</label>
	   <input type="checkbox" class="form-control"  name="n_DM" ${client_acheteur.n_DM==1?'checked':''} value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">trans_DM</label>
		 <input type="checkbox" class="form-control"  name="trans_DM" ${client_acheteur.trans_DM==1?'checked':''} value="1"  />
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">bornage_cont </label>
	    <input type="checkbox" class="form-control"  name="bornage_cont" ${client_acheteur.bornage_cont==1?'checked':''} value="1"  /> 
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">transp_pv_bc </label>
	    <input type="checkbox" class="form-control"  name="transp_pv_bc" ${client_acheteur.transp_pv_bc==1?'checked':''} value="1"  /> 
       </div>        
      </div>
       <div class="form-group row"> 
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">creat_ACD</label>
	    <input type="checkbox" class="form-control"  name="creat_ACD" ${client_acheteur.creat_ACD==1?'checked':''} value="1"  /> 
       </div>                         
        <div class="col-sm-2 mb-2 mb-sm-0">      
        <label for="platbus" class="">notif_ACD</label>
	    <input type="checkbox" class="form-control"  name="notif_ACD" ${client_acheteur.notif_ACD==1?'checked':''} value="1"  /> 
       </div>  
       <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">pf_ACD</label>
	   <input type="checkbox" class="form-control"  name="pf_ACD" ${client_acheteur.pf_ACD==1?'checked':''} value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">ref_ACD</label>
		 <input type="checkbox" class="form-control"  name="ref_ACD" ${client_acheteur.ref_ACD==1?'checked':''} value="1" />
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">creat_TF</label>
		 <input type="checkbox" class="form-control"  name="creat_TF" ${client_acheteur.creat_TF==1?'checked':''} value="1" />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">trans_TF</label>
		 <input type="checkbox" class="form-control"  name="trans_TF" ${client_acheteur.trans_TF==1?'checked':''} value="1" />
       </div>
      </div>
      <div class="form-group row">   
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="seat" class="">DATE AJOUT<span class="text-danger ml-2">*</span></label>
         <input type="date"  name="date" class="form-control" value="<c:out value="${client_acheteur.date}"/>">
        </div> 
      </div>

      <input type="hidden" name="id" value="<c:out value="${client_acheteur.id}"/>">
        <input type="hidden" name="matricule" value="<c:out value="${client_acheteur.matricule}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=client_A"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_GE==1}">
             <button name="submit" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
              </c:if>    
                <a  class="btn btn btn-warning float-right" href="#etat_6" data-toggle="modal">Paiement</a>                                            
            </div>                                  
            </form>
          </div>
       
      </div>
           	     
    </div>
    
     <div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
		</div>
	</div>
	
	
   
   <c:import url="/include/base_footer.jsp"></c:import>  
   
   <!-- ============ ============ ============ DOCUMENT============ ============ =========================== --> 
           <div class="modal fade  ${block}" id="etat_6" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:600px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Ajout Paiement</h3>
                      </div>                   
                        <div class="modal-body">                                             
                            <form action="<c:url value="/view_client_A"/>"   method="post"  class="myFormulaire">
				               <div class="form-group row">    
				                 <div class="col-sm-6 mb-2 mb-sm-0">
							       <label for="platbus" class="">PRIX Honoraire</label>
								   <input type="text" name="honoraire" class="form-control" disabled style="color:red;" value="<c:out value="${client_acheteur.honoraire}"/>">
								 </div>     
						         <div class="col-sm-6 mb-2 mb-sm-0">
						         <label for="platbus" class="">MONTANT PAYER </label>
							     <input type="text" name="montantP" class="form-control" value="<c:out value="${paiement_terrain.montantP}"/>" disabled>
						       </div>						        						            												
						      </div>  
						       <div class="form-group row">        
						          <div class="col-sm-6 mb-2 mb-sm-0">
							         <label for="platbus" class="">DATE VERSEMENT</label>
									  <input type="date" name="datep" class="form-control" value="">
								  </div>
								 <div class="col-sm-6 mb-2 mb-sm-0">
						         <label for="platbus" class="">MONTANT RESTANT </label>
							     <input type="text" name="" class="form-control" value="<c:out value="${paiement_terrain.montantR}"/>" disabled>
							      <input type="hidden" name="montantR"  value="<c:out value="${paiement_terrain.montantR}"/>">
							       <input type="hidden" name="terre"  value="<c:out value="${paiement_terrain.terrain}"/>">
						        </div>							             												
						      </div>
						      <div class="form-group row">
						         <div class="col-sm-6 mb-2 mb-sm-0">
							         <label for="platbus" class="">MODE PAIEMENT</label>
								     <select class="form-control"name="modep">
							           <option value="virement">Virement Bancaire</option>
								       <option value="carte">Carte Bancaire</option>
							           <option value="espece">Espèce</option>
							           <option value="mobile money">Mobile Money</option>
							         </select>
						         </div>   
						       </div>
						       <input type="hidden" name="id" value="<c:out value="${client_acheteur.id}"/>">
						       <input type="hidden" name="client" value="<c:out value="${paiement_terrain.client}"/>">
						       <div class="modal-footer">	
						         <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>					       
						        <button name="submit" value="AJOUTER" class="btn btn-success ${paiement_terrain.montantR!=0?'':'disabled'}">Solde</button>
						      </div>
                            </form>
                            
                             <div class="table" style="margin-bottom:0;">
							 <div class="thead">
							  <div class="tr ht">
								<div class="td" style="width:20%;">montant_P</div>
								<div class="td" style="width:20%;">montant_R</div>
								<div class="td" style="width:25%;">mode paiement</div>
								<div class="td" style="width:20%;">date</div>								
								<div class="td" style="width:15%;">action</div>
							  </div>
						   </div>
						  </div>
						  						   
						    <div  id="myTableU" style="height:110px !important;">
							  <div class="table" style="margin-bottom:0;">
								<div class="tbody" id="tbody">
								<sql:query var="listPai" dataSource="jdbc/gestions_imm">
									select * from paiement_terrain where client="${client_acheteur.matricule}"  
								</sql:query>
								 <c:forEach items="${listPai.rows}" var="mapPai" varStatus="boucle">
									<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
										<div class="td tdCenter" style="width:20%;">${mapPai.montantP}</div>
										<div class="td tdCenter" style="width:20%;">${mapPai.montantR}</div>	
										<div class="td tdCenter" style="width:25%;">${mapPai.modeP}</div>
										<div class="td tdCenter" style="width:20%;">${mapPai.dateP}</div>										
										<div class="td tdCenter" style="width:15%;">
										 <a target="_BLANK" href="<c:url value="/reçu_T?id=${mapPai.id}&terrain=${mapPai.terrain}&client=${mapPai.client}"/>" style="border:1px solid grey;border-radius:3px;padding:4px;color:#000;"><i class="fa fa-print fa-lg"></i></a>
										 <button onclick="document.location.href='<c:url value="/view_client_A?id=${mapPai.id}&terrain=${mapPai.terrain}&client=${mapPai.client}&page=${page}&crud=ids" />'"><img alt=""  style=" width: 20px;" src="assets/frontend/img/supp.jpg"></button>
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