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
		<title> GESTIONS DES LOCATAIRES PHYSIQUE</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER LOCATAIRE-PHYSIQUE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_locataire"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
       <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="<c:out value="${locataire.nom}"/>">
        </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM PRENOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="prenom" class="form-control" required  value="<c:out value="${locataire.prenom}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">GENRE<span class="text-danger ml-2">*</span></label>
        <select name="sexe" required class="form-control">
			<option value="<c:out value="${locataire.sexe}"/>" selected><c:out value="${locataire.sexe}"/></option>
			<option value="MASCULIN">MASCULIN</option>
			<option value="FEMININ">FEMININ</option>
		</select>		
        </div>        
      </div>
      <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="date_naiss" class="">DATE DE NAISSANCE</label>
         <input type="date" required  name="date_naiss" class="form-control" value="<c:out value="${locataire.date_naiss}"/>">
        </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LIEU DE NAISSANCE</label>
        <input type="text" name="lieu_naiss" class="form-control" value="<c:out value="${locataire.lieu_naiss}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SITUATION MATRIMONAILE</label>
        <select name="situation_matr" required class="form-control">
			<option value="<c:out value="${locataire.situation_matr}"/>" selected><c:out value="${locataire.situation_matr}"/></option>
			<option value="Celibataire">Celibataire</option>
			<option value="Marie">Marie</option>
		</select> 
        </div>      
      </div>
      <div class="form-group row">    
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">NATIONALITE </label>
          <sql:query var="listPays" dataSource="jdbc/gestions_imm">
			select * from pays 
		</sql:query>
         <select name="nationalite" required class="form-control">
				  <option value="<c:out value="${locataire.nationalite}"/>" selected><c:out value="${locataire.nationalite}"/></option>
				  <c:forEach items="${listPays.rows}" var="mapP" varStatus="boucle">
					<option value="${mapP.pays}">${mapP.pays}</option>
				  </c:forEach>
		 </select>	     
        </div>  
       <div class="col-sm-3 mb-2 mb-sm-0">
       <label for="platbus" class="">FONCTION</label>
	   <input type="text" name="fonction" class="form-control" value="<c:out value="${locataire.fonction}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT</label>
		<input type="text" name="tel" class="form-control" value="<c:out value="${locataire.tel}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">TEL WHATSAPP</label>
		<input type="text" name="telW"  class="form-control" value="<c:out value="${locataire.tel_whatsapp}"/>">
       </div>            
      </div>
      <div class="form-group row">   
         <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="<c:out value="${locataire.email}"/>">
         </div> 
         <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">TYPE DE CONTRAT </label>
		 <select name="type_contrat" class="form-control">
			<option value="<c:out value="${locataire.type_contrat}"/>" selected><c:out value="${locataire.type_contrat}"/></option>
			<option value="BAIL LOCATIF">BAIL LOCATIF</option>
			<option value="CONTRAT">CONTRAT</option>	
			<option value="COMMERCIAL">COMMERCIAL</option>	   								
		</select>
		</div>    
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">NUMERO PORTE </label>
         <input type="hidden" name="num_porte"   class="form-control" value="<c:out value="${locataire.num_porte}"/>" >
		 <input type="text" name=""   class="form-control" value="<c:out value="${locataire.num_porte}"/>" disabled>
		</div>		
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE D'ENTREE </label>		
		  <div class='input-group date' id='datepicker'>
           <input type='date' name="date_entree" class="form-control datepicker" value="<c:out value="${locataire.date_entree}"/>" required/>               
           </div>
		</div>				
      </div> 
      <div class="form-group row">
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE SORTIE</label>
		 <input type="date" name="date_sortie" class="form-control" value="<c:out value="${locataire.date_sortie}"/>">
		</div>
        <div class="col-sm-3 mb-2 mb-sm-0">
	       <label for="platbus" class="">CAUTION</label>
		   <input type="checkbox" class="form-control" ${locataire.p_C==1?'checked':''} name="p_C"  value="1"  />
	       </div>
	       <div class="col-sm-3 mb-2 mb-sm-0">
	        <label for="platbus" class="">AVANCE</label>
			 <input type="checkbox" class="form-control" ${locataire.p_A==1?'checked':''}  name="p_A"  value="1"  />
	       </div>
	        <div class="col-sm-3 mb-2 mb-sm-0">       
	        <label for="platbus" class="">COMMISSION D'ENTREE </label>
		    <input type="checkbox" class="form-control" ${locataire.p_CO==1?'checked':''}  name="p_CO"  value="1"  /> 
	       </div>					
      </div> 
		
      </div>    
      <input type="hidden" name="matricule" value="<c:out value="${locataire.matricule}"/>">
       <input type="hidden" name="id" value="<c:out value="${locataire.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=locataire"/>"> Retour</a>
             &nbsp;&nbsp;
           <c:if test="${sessionScope.rolePr.modif_LO==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
              </c:if>   
                  <c:if test="${sessionScope.rolePr.modif_LO==1}">             
                  <a  class="btn btn btn-warning float-center" href="#etat_6" data-toggle="modal">Ajouter Un Document</a>   
                  <a  class="btn btn btn-info float-right" href="#etat_66" data-toggle="modal">Rapport Locataire</a>                                                   
                  </c:if>
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
                     <div class="modal-content" style="width:950px;margin-left:-85px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Ajout Document</h3>
                      </div>                   
                        <div class="modal-body">                                             
                            <form action="<c:url value="/view_locataire"/>" id="formAdd"  method="post"  class="myFormulaire" enctype="multipart/form-data">
				               <div class="form-group row">        
						         <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">NOM </label>
							     <input type="text" name="nomC" class="form-control" value="<c:out value="${locataire.nom}"/>" disabled>
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">PRENOM </label>
							     <input type="text" name="prenomC" class="form-control" value="<c:out value="${locataire.prenom}"/>" disabled>
						       </div>						            		
								<div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">FONCTION</label>
								 <input type="text" name="fonctionC" class="form-control" value="<c:out value="${locataire.fonction}"/>" disabled>
								</div>		
						      </div>  
						       <div class="form-group row">        
						         <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">TYPE DOCUMENT </label>
							     <input type="text" name="type_doc" class="form-control" value="">
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">DATE AJOUT </label>
							     <input type="date" name="dats" class="form-control" value="">
						       </div>
						        <div class="col-sm-4 mb-2 mb-sm-0">
						         <label for="platbus" class="">CHEMIN DU FICHIER </label>
							     <input type="file" required  class="form-control" name="file" id="file" >
						       </div>        												
						      </div>
						       <input type="hidden" name="client" value="<c:out value="${locataire.matricule}"/>">
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
									select * from document where client="${locataire.matricule}" 
								</sql:query>
								 <c:forEach items="${listDoc.rows}" var="mapDoc" varStatus="boucle">
									<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
										<div class="td tdCenter" style="width:20%;">${mapDoc.type_doc}</div>
										<div class="td tdCenter" style="width:20%;">${mapDoc.date_ajout}</div>										
										<div class="td tdCenter" style="width:10%;">
										 <a target="_BLANK" href="<c:url value="/view_locataire?id=${mapDoc.id}&crud=doc"/>" style="border:1px solid grey;border-radius:3px;padding:4px;color:#000;"><i class="fa fa-print fa-lg"></i></a>
										 <button onclick="document.location.href='<c:url value="/view_locataire?id=${mapDoc.id}&matri=${mapDoc.client}&page=${page}&crud=idsP" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
	
	 $(function(){
         var date = new Date();
         date.setDate(date.getDate());

       $(".datepicker").datepicker({
           format: 'yyyy-mm-dd',
           autoclose: true,
       });
      });
	
	</script>  
	
</body>
</html>