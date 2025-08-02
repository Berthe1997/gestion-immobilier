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
		<title> GESTIONS ARCHIVE LOCATAIRE</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">DETAIL ARCHIVE LOCATAIRE   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_locataire"/>" method="post"  class="myFormulaire">
             
           <div class="card-body">
       <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="<c:out value="${archive_locataire.nom}"/>">
        </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM PRENOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="prenom" class="form-control" required  value="<c:out value="${archive_locataire.prenom}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">GENRE<span class="text-danger ml-2">*</span></label>
        <select name="sexe" required class="form-control">
			<option value="<c:out value="${archive_locataire.sexe}"/>" selected><c:out value="${archive_locataire.sexe}"/></option>
			<option value="MASCULIN">MASCULIN</option>
			<option value="FEMININ">FEMININ</option>
		</select>		
        </div>        
      </div>
      <div class="form-group row">
        <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="date_naiss" class="">DATE DE NAISSANCE</label>
         <input type="date" required  name="date_naiss" class="form-control" value="<c:out value="${archive_locataire.date_naiss}"/>">
        </div>
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">LIEU DE NAISSANCE</label>
        <input type="text" name="lieu_naiss" class="form-control" value="<c:out value="${archive_locataire.lieu_naiss}"/>">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">SITUATION MATRIMONAILE</label>
        <select name="situation_matr" required class="form-control">
			<option value="<c:out value="${locataire.situation_matr}"/>" selected><c:out value="${archive_locataire.situation_matr}"/></option>
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
				  <option value="<c:out value="${archive_locataire.nationalite}"/>" selected><c:out value="${archive_locataire.nationalite}"/></option>
				  <c:forEach items="${listPays.rows}" var="mapP" varStatus="boucle">
					<option value="${mapP.pays}">${mapP.pays}</option>
				  </c:forEach>
		 </select>	     
        </div>  
       <div class="col-sm-3 mb-2 mb-sm-0">
       <label for="platbus" class="">FONCTION</label>
	   <input type="text" name="fonction" class="form-control" value="<c:out value="${archive_locataire.fonction}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT</label>
		<input type="text" name="tel" class="form-control" value="<c:out value="${archive_locataire.tel}"/>">
       </div>
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">TEL WHATSAPP</label>
		<input type="text" name="telW"  class="form-control" value="<c:out value="${archive_locataire.tel_whatsapp}"/>">
       </div>            
      </div>
      <div class="form-group row">   
         <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" class="form-control" value="<c:out value="${archive_locataire.email}"/>">
         </div> 
         <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">TYPE DE CONTRAT </label>
		 <select name="type_contrat" class="form-control">
			<option value="<c:out value="${locataire.type_contrat}"/>" selected><c:out value="${archive_locataire.type_contrat}"/></option>
			<option value="BAIL LOCATIF">BAIL LOCATIF</option>
			<option value="CONTRAT">CONTRAT</option>	
			<option value="COMMERCIAL">COMMERCIAL</option>	   								
		</select>
		</div>    
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">NUMERO PORTE </label>
         <input type="hidden" name="num_porte"   class="form-control" value="<c:out value="${archive_locataire.num_porte}"/>" >
		 <input type="text" name=""   class="form-control" value="<c:out value="${locataire.num_porte}"/>" disabled>
		</div>		
		<div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE D'ENTREE </label>		
		  <div class='input-group date' id='datepicker'>
           <input type='date' name="date_entree" class="form-control datepicker" value="<c:out value="${archive_locataire.date_entree}"/>" required/>               
           </div>
		</div>				
      </div> 
      <div class="form-group row">
        <div class="col-sm-3 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE SORTIE</label>
		 <input type="date" name="date_sortie" class="form-control" value="<c:out value="${archive_locataire.date_sortie}"/>">
		</div>
        <div class="col-sm-3 mb-2 mb-sm-0">
	       <label for="platbus" class="">CAUTION</label>
		   <input type="checkbox" class="form-control" ${archive_locataire.p_C==1?'checked':''} name="p_C"  value="1"  />
	       </div>
	       <div class="col-sm-3 mb-2 mb-sm-0">
	        <label for="platbus" class="">AVANCE</label>
			 <input type="checkbox" class="form-control" ${archive_locataire.p_A==1?'checked':''}  name="p_A"  value="1"  />
	       </div>
	        <div class="col-sm-3 mb-2 mb-sm-0">       
	        <label for="platbus" class="">COMMISSION D'ENTREE </label>
		    <input type="checkbox" class="form-control" ${archive_locataire.p_CO==1?'checked':''}  name="p_CO"  value="1"  /> 
	       </div>					
      </div> 
		
      </div>    
      <input type="hidden" name="matricule" value="<c:out value="${archive_locataire.matricule}"/>">
       <input type="hidden" name="id" value="<c:out value="${locataire.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=archive_locataire"/>"> Retour</a>
             &nbsp;&nbsp;
           <a  class="btn btn btn-info float-right" href="#etat_6" data-toggle="modal">Document Locataire</a> 
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
   
    <!-- ============ ============ ============ ETAT Commission d'Entree============ ============ =========================== --> 
           <div class="modal fade  ${block}" id="etat_6" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:800px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Gestion Document Locataire</h3>
                      </div>                   
                        <div class="modal-body">                           
                           <div class="form-group row bmenu">
				             <div class="col-sm-4 mb-3 mb-sm-0"> 					           
					                <a class="btn btn-info" href="#lap_jual_pertanggal_1" data-toggle="modal">Contrat Arrivé Locataire</a>					             
				             </div>            
				             <div class="col-sm-4 mb-3 mb-sm-0">    
					               <a class="btn btn-info" href="#lap_jual_pertanggal_2" data-toggle="modal">Information Locataire</a>					                                    
				             </div>
				              <div class="col-sm-4 mb-3 mb-sm-0">				                 
					                   <a class="btn btn-info" href="#lap_jual_pertanggal_33" data-toggle="modal">Rapport de sortie</a>					                      
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