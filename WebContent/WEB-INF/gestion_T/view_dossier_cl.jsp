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
          <form action="<c:url value="/view_dossier"/>" method="post"  class="myFormulaire" >
             
       <div class="card-body">       
       <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="<c:out value="${dossier_client.nom}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="prenom" class="form-control" required  value="<c:out value="${dossier_client.prenom}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">GENRE<span class="text-danger ml-2">*</span></label>
        <select name="sexe" required class="form-control">
			<option value="<c:out value="${dossier_client.sexe}"/>" selected>${dossier_client.sexe}</option>
			<option value="MASCULIN">MASCULIN</option>
			<option value="FEMININ">FEMININ</option>
		</select>		
        </div> 
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">SITUATION MATRIMO</label>
        <select name="situation_matr"  class="form-control">
			<option value="<c:out value="${dossier_client.situation_matr}"/>" selected>${dossier_client.situation_matr}</option>
			<option value="Celibataire">Celibataire</option>
			<option value="Marie">Marie</option>
			<option value="Divorcer">Divorcer</option>
		</select> 
        </div> 
      </div>  
      <div class="form-group row">
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">DATE NAISSANCE<span class="text-danger ml-2">*</span></label>
         <input type="date" name="date_naissance" class="form-control" required  value="<c:out value="${dossier_client.date_naissance}"/>">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
           <label for="platbus" class="">NATIONALITE </label>
	          <sql:query var="listPays" dataSource="jdbc/gestions_imm">
				select * from pays 
			</sql:query>
           <select name="nationnalite"  required class="form-control">
				  <option value="<c:out value="${dossier_client.nationnalite}"/>" selected>${dossier_client.nationnalite}</option>
				  <c:forEach items="${listPays.rows}" var="mapP" varStatus="boucle">
					<option value="${mapP.pays}">${mapP.pays}</option>
				  </c:forEach>
		 </select>        
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PROFESSION</label>
         <input type="text" name="profession" class="form-control" value="<c:out value="${dossier_client.profession}"/>">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
          <label for="platbus" class="">CONTACT</label>
          <input type="text" name="contact" class="form-control" value="<c:out value="${dossier_client.tel}"/>">       
        </div>                                    
      </div>
       <div class="form-group row">         
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE</label>
        <input type="text" name="adresse" class="form-control" value="<c:out value="${dossier_client.adresse}"/>">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">LOT</label>
        <input type="text" name="lot" class="form-control" value="<c:out value="${dossier_client.lot}"/>">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ILOT</label>
        <input type="text" name="ilot" class="form-control" value="<c:out value="${dossier_client.ilot}"/>">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">LOTISSEMENT</label>
        <input type="text" name="lotissement" class="form-control" value="<c:out value="${dossier_client.lotissement}"/>">
        </div>	
        <div class="col-sm-3 mb-2 mb-sm-0">
		 <label for="platbus" class="">DATE ACHAT </label>
		 <input type="date" name="date_achat" class="form-control" value="<c:out value="${dossier_client.date_achat}"/>">
	    </div>  		             
      </div> 
      <div class="form-group row">   
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">attes_CDisp </label>
	    <input type="checkbox" class="form-control"  name="attes_CD" ${dossier_client.attes_CD==1?'checked':''} value="1"  /> 
       </div> 
       <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">intro_dem_ACD</label>
	    <input type="checkbox" class="form-control"  name="intro_dem_ACD" ${dossier_client.intro_dem_ACD==1?'checked':''} value="1"  /> 
       </div>  
       <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">date_BM</label>
	    <input type="checkbox" class="form-control"  name="date_BM" ${dossier_client.date_BM==1?'checked':''} value="1"  /> 
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">n_DM</label>
	   <input type="checkbox" class="form-control"  name="n_DM" ${dossier_client.n_DM==1?'checked':''} value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">trans_DM</label>
		 <input type="checkbox" class="form-control"  name="trans_DM" ${dossier_client.trans_DM==1?'checked':''} value="1"  />
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">bornage_cont </label>
	    <input type="checkbox" class="form-control"  name="bornage_cont" ${dossier_client.bornage_cont==1?'checked':''} value="1"  /> 
       </div>
        </div>
       <div class="form-group row">   
         <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">transp_pv_bc </label>
	    <input type="checkbox" class="form-control"  name="transp_pv_bc" ${dossier_client.transp_pv_bc==1?'checked':''} value="1"  /> 
       </div> 
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">creat_ACD</label>
	    <input type="checkbox" class="form-control"  name="creat_ACD" ${dossier_client.creat_ACD==1?'checked':''} value="1"  /> 
       </div>                         
        <div class="col-sm-2 mb-2 mb-sm-0">      
        <label for="platbus" class="">notif_ACD</label>
	    <input type="checkbox" class="form-control"  name="notif_ACD" ${dossier_client.notif_ACD==1?'checked':''} value="1"  /> 
       </div>  
       <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">pf_ACD</label>
	   <input type="checkbox" class="form-control"  name="pf_ACD" ${dossier_client.pf_ACD==1?'checked':''} value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">ref_ACD</label>
		 <input type="checkbox" class="form-control"  name="ref_ACD" ${dossier_client.ref_ACD==1?'checked':''} value="1" />
       </div> 
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">creat_TF</label>
		 <input type="checkbox" class="form-control"  name="creat_TF" ${dossier_client.creat_TF==1?'checked':''} value="1" />
       </div>                             
      </div>  
      <div class="form-group row">          
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">trans_TF</label>
		 <input type="checkbox" class="form-control"  name="trans_TF" ${dossier_client.trans_TF==1?'checked':''} value="1" />
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">depot_DATTC</label>
	   <input type="checkbox" class="form-control"  name="depot_DATTC" ${dossier_client.depot_DATTC==1?'checked':''} value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">dossier_techn_CT</label>
		 <input type="checkbox" class="form-control"  name="dossier_techn_CT" ${dossier_client.dossier_techn_CT==1?'checked':''} value="1"  />
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">dossier_techn_D </label>
	    <input type="checkbox" class="form-control"  name="dossier_techn_D" ${dossier_client.dossier_techn_D==1?'checked':''} value="1"  /> 
       </div> 
      </div>     
        <input type="hidden" name="client" value="<c:out value="${dossier_client.matricule}"/>">
         <input type="hidden" name="id" value="<c:out value="${dossier_client.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=dossier_cl"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_GE==1}">
               <button name="submit" value="MODIFIER" class="btn btn-success">Modifier</button>
                <button name="crud" value="AJOUTER" class="btn btn-warning float-right" disabled>Ajout Document</button>
              </c:if>                                                             
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
						select * from document where client="${dossier_client.matricule}" 
					</sql:query>
					<c:forEach items="${listDoc.rows}" var="mapDoc" varStatus="boucle">
						<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
							<div class="td tdCenter" style="width:20%;">${mapDoc.type_doc}</div>
							<div class="td tdCenter" style="width:20%;">${mapDoc.date_ajout}</div>										
							<div class="td tdCenter" style="width:10%;">
								<a target="_BLANK" href="<c:url value="/view_dossier?id=${mapDoc.id}&crud=doc"/>" style="border:1px solid grey;border-radius:3px;padding:4px;color:#000;"><i class="fa fa-print fa-lg"></i></a>
								 <button onclick="document.location.href='<c:url value="/view_dossier?id=${mapDoc.id}&matri=${mapDoc.client}&page=${page}&crud=ids" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
							</div>
						</div>
								</c:forEach>
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