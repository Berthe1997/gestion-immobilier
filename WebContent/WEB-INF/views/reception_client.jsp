<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from reception_client 
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
		<title>RECEPTION CLIENT</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">RECEPTION CLIENT</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_recli==1}">
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUT RECEPTION CLIENT
          </button>
          </c:if> 
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>NOM PRENOM</th>
                  <th>CONTACT</th>
                  <th>CHAMBRE</th>               
                  <th>DESCRIPTION BIEN</th>
                  <th>BUDGET</th>   
                  <th>DATE APPEL</th>  
                  <th>STATUT</th>               
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.nom}</td>
                  <td>${mapProp.tel}</td>
                  <td>${mapProp.chambre}</td>
                  <td>${mapProp.zone}</td>
                  <td>${mapProp.prix}</td>
                  <td>${mapProp.date_appel}</td>      
                  <td>${mapProp.statut}</td>                                                  
                  <td align="center">
                  <input type="hidden" value="${mapProp.id}"/>
                  <button class="btn-primary" onclick="ouvrirAM(this);"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></button> 
					<c:if test="${sessionScope.rolePr.supp_recli==1}"> 					           
                   <a onclick="document.location.href='<c:url value="/reception_cli?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></a>
                 </c:if> 
                  </td>
               
              </tr>
             </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- /.container-fluid -->
  </div>
  <!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<!-- Footer --><!-- Log on to codeastro.com for more projects -->
 <c:import url="/include/base_footer.jsp"></c:import>
<!-- End of Footer -->
<!-- Modal -->
<div class="modal fade" id="ModalTujuan" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content" style="width:810px;margin-left:-35px;">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUT RECEPTION CLIENT </h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/reception_cli"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM CLIENT<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">CONTACT<span class="text-danger ml-2">*</span></label>
         <input type="text" name="tel" class="form-control" required  value="">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">NOMBRE PIECE</label>
	      <select name="piece" class="form-control">
			<option value="" selected></option>
			<option value="1 piece">1 piece</option>
			<option value="2 pieces">2 pieces</option>			
			<option value="3 pieces">3 pieces</option>  
		    <option value="4 pieces">4 pieces</option> 	
			<option value="5 pieces">5 pieces</option>
			<option value="6 pieces">6 pieces</option>
			<option value="7 pieces">7 pieces</option>
			<option value="8 pieces">8 pieces</option>
			<option value="9 pieces">9 pieces</option>
			<option value="10 pieces">10 pieces</option>												
		</select>
       </div>         
      </div>    
      <div class="form-group row">         
       <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">VILLE<span class="text-danger ml-2">*</span></label>
          <sql:query var="Vil" dataSource="jdbc/gestions_imm">
			select * from zone   group by ville
		  </sql:query>
		   <select id="ville" name="ville" class="form-control" onchange="ajax('villes')">	
		     <option value="" selected></option>											 
		     <c:forEach items="${Vil.rows}" var="mapVil" varStatus="boucle">
				<option value="${mapVil.ville}">${mapVil.ville}</option>
			  </c:forEach>
		  </select>
        </div>   		
		 <div class="col-sm-4 mb-2 mb-sm-0">
	         <label for="platbus" class="">COMMUNE<span class="text-danger ml-2">*</span></label>
	         <select id="commune" name="commune" class="form-control" onchange="ajax('communes')">
										
			 </select>	
        </div> 
         <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">ZONE<span class="text-danger ml-2">*</span></label>
          <select id="zone" name="zone" class="form-control">
										
			 </select>		
        </div> 		
      </div> 
      <div class="form-group row"> 
       <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">STATUT</label>
		   <select name="statut" required class="form-control">
			<option value="" selected></option>
			<option value="Interne">INTERNE</option>
			<option value="Externe">EXTERNE</option>
		</select>  
		</div>	                 		
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">PRIX</label>
		   <input type="text" name="prix" class="form-control" value="" onkeyup="montantSaisi('saisi');" id="mttSaisi">
		</div>		
      </div>    
         
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button> 
        <button name="crud" value="AJOUTER" class="btn btn-success">Sauvegarder</button>
      </div>
    </form>
  </div>
</div>
</div>
</div>


<div class="suppPop" id="supp">
   <div class="card shadow mb-4" style="width:830px;margin-left:400px;margin-top:80px;" >
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">AJOUTER UNE NOUVELLE DEMANDE </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/reception_cli"/>" method="post"  class="myFormulaire">            
           <div class="card-body">	       
	        <div class="form-group row">         
		       <div class="col-sm-4 mb-2 mb-sm-0">
		         <label for="platbus" class="">VILLE<span class="text-danger ml-2">*</span></label>
		          <sql:query var="Vil" dataSource="jdbc/gestions_imm">
					select * from zone   group by ville
				  </sql:query>
				   <select id="vill" name="ville" class="form-control" onchange="ajax('villS')">	
				     <option value="" selected></option>											 
				     <c:forEach items="${Vil.rows}" var="mapVil" varStatus="boucle">
						<option value="${mapVil.ville}">${mapVil.ville}</option>
					  </c:forEach>
				  </select>
		        </div>   		
				 <div class="col-sm-4 mb-2 mb-sm-0">
			         <label for="platbus" class="">COMMUNE<span class="text-danger ml-2">*</span></label>
			         <select id="commun" name="commune" class="form-control" onchange="ajax('communS')">
												
					 </select>	
		        </div> 
		         <div class="col-sm-4 mb-2 mb-sm-0">
		         <label for="platbus" class="">ZONE<span class="text-danger ml-2">*</span></label>
		          <select id="zon" name="zone" class="form-control">
												
					 </select>		
		        </div> 		
		      </div> 
		      <div class="form-group row"> 
		       <div class="col-sm-4 mb-2 mb-sm-0">
		         <label for="platbus" class="">NOMBRE PIECE</label>
			      <select name="piece" class="form-control">
					<option value="" selected></option>
					<option value="1 piece">1 piece</option>
					<option value="2 pieces">2 pieces</option>			
					<option value="3 pieces">3 pieces</option>  
				    <option value="4 pieces">4 pieces</option> 	
					<option value="5 pieces">5 pieces</option>
					<option value="6 pieces">6 pieces</option>
					<option value="7 pieces">7 pieces</option>
					<option value="8 pieces">8 pieces</option>
					<option value="9 pieces">9 pieces</option>
					<option value="10 pieces">10 pieces</option>												
				</select>
		       </div>  
		       <div class="col-sm-4 mb-2 mb-sm-0">
		         <label for="platbus" class="">STATUT</label>
				   <select name="statut" required class="form-control">
					<option value="" selected></option>
					<option value="Interne">INTERNE</option>
					<option value="Externe">EXTERNE</option>
				</select>  
				</div>	                 		
				<div class="col-sm-4 mb-2 mb-sm-0">
		         <label for="platbus" class="">PRIX</label>
				   <input type="text" name="prix" class="form-control" value="" onkeyup="montantSaisi('saisi');" id="mttSaisi">
				</div>		
		      </div>    
     	      
           <hr><button class="btn btn-primary" onclick="return ouvFermer('ferme')">cancel</button>
         
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>          
            </div>           
            <input type="hidden" name="page" id="page" value="<c:out value="${page}"/>">	
			<input type="hidden" name="types" id="typeSup" value="">
			<input type="hidden" name="id" id="idM" value="">
				            
           </form>
          </div>       
      </div>
</div>


 <div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
		</div>
	</div>

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
 
 <script type="text/javascript">
 
 //=======================================SUPPRIMER=======================================================//
	function ouvrirAM(tag){
	var parent	=	$(tag).parent();
	var valeur = parent.find("input").val();	
	document.getElementById("supp").style.display = "block";
	document.getElementById("typeSup").value = "supS";
	document.getElementById("idM").value = valeur;
	
 }
	</script> 

</body>
</html>