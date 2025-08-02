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
		<title>GESTION OUVERTURE CAISSE</title>
		
	<style>		
		

 #flash {
      
       height: 60px;
      margin: 10px auto;
      background-color: red;
      text-align: center;
      color: white;
      font-size: 20px;
      line-height: 60px;
      transition: background-color 0.5s ease;
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
     <div class="modal-dialog" role="document" >
				<div class="modal-content" style="width:980px;margin-left:-265px;">
				  <div class="card-header py-3">
			          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">OUVERTURE DE VOTRE CAISSE <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=comptabilite"/>"> Retour</a></h6>
			       </div>
				  <div class="modal-body">
				    <form action="<c:url value="/CRUDOuvFer?page=ouvertureCaisse"/>" method="post"  class="myFormulaire">
				        <div class="modal-body">
				          <div class="row">
				            <div class="col-sm-6">
				               <div class="row form-group">
			                    <label for="nama" class="col-sm-4 control-label">Nom Caissier</label>
			                    <div class="col-sm-8">
			                     <input type="text" class="form-control" readonly required name="nomCaissier" value="<c:out value="${sessionScope.users.nom} "/>" />
			                      <input type="hidden" class="form-control" id="email" required name="email" value="<c:out value="${sessionScope.users.email}"/>" />
			                    </div>
			                   </div>
			                   <div class="row form-group">
			                    <label for="nama" class="col-sm-4 control-label">Libelle Caisse</label>
			                    <div class="col-sm-8">
			                     <select class="form-control" id="codeCaisse" onchange="getCash('cashdispoF');" name="libelleC" required>
										<!--  <option value="" selected></option>
										<c:forEach items="${listCaisse.rows}" var="mapCaisse">
											<option value="${mapCaisse.code_caisse}">${mapCaisse.libelle}</option>
										</c:forEach>-->
									</select>
			                    </div>
			                   </div>
			                   <div class="row form-group">
			                    <label for="nama" class="col-sm-4 control-label">Solde Précédent</label>
			                    <div class="col-sm-8">
			                     <input type="text" class="form-control" readonly required name="cashF" id="cashF" />
			                    </div>
			                   </div>
			                   <div class="row form-group">
			                    <label for="nama" class="col-sm-4 control-label">Solde Initiale</label>
			                    <div class="col-sm-8">
			                     <input type="text" class="form-control" name="cashO" required maxlength="18" id="cashO" onkeyup="getDiff();" />
			                    </div>
			                   </div>
			                    <div class="row form-group">
			                    <label for="nama" class="col-sm-4 control-label">Differrence Solde</label>
			                    <div class="col-sm-8">
			                      <input type="text" class="form-control" readonly required name="difference" id="diff" />
			                    </div>
			                   </div>
			                   <div class="row form-group">
			                    <label for="nama" class="col-sm-4 control-label">Date d'Ouverture</label>
			                    <div class="col-sm-8">
			                    <input type="text" readonly class="form-control" required name="dateO" id="dateo"  />
			                    </div>
			                   </div>
			                   			                   
			                   
			             </div>
			             
			            <div class="col-sm-6">
		                  <div class="row form-group">
		                  <div class="alert alert-warning" role="alert">
		                    <h4 class="alert-heading" id="flash"><svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" fill="currentColor" class="bi bi-exclamation-triangle-fill flex-shrink-0 me-2" viewBox="0 0 16 16" role="img" aria-label="Warning:">
		                   <path d="M8.982 1.566a1.13 1.13 0 0 0-1.96 0L.165 13.233c-.457.778.091 1.767.98 1.767h13.713c.889 0 1.438-.99.98-1.767L8.982 1.566zM8 5c.535 0 .954.462.9.995l-.35 3.507a.552.552 0 0 1-1.1 0L7.1 5.995A.905.905 0 0 1 8 5zm.002 6a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
		                    </svg>Confirmer l'ouverture de votre caisse!!</h4>
		                    <p>votre caisse n'est pas encore ouverte. <br> il vous faut obligatoirement ouvrir votre caisse avant de pouvoir faire une quelconque opération.</p>
		                    <hr>                   
		                  </div>
		                    
		                  </div>
		                   <div class="row form-group">
		                     <div id="difference">
										<p class="alert-info ${message.msgColor}" id="alert">${message.message}</p>
										<div class="dif" id="dif">
											<p>VOTRE LIQUIDITE ACTUELLE EST INFERIEURE < Différence de montant entre les deux sessions >
											A LA LIQUIDITE QUI DEVRAIT ETRE DISPONIBLE DANS LA CAISSE QUE VOUS AVEZ CHOISIE. LE MIEUX EST D'APPELER VOTRE SUPERIEUR POUR CONSTATER CETTE DIFFERENCE.
											 SI VOUS VALIDEZ L'OUVERTURE DE LA CAISSE, CETTE DIFFERENCE RISQUE DE VOUS ETES IMPUTABLE AU MOMENT DU BILAN COMPTABLE.</p>
											<div id="liq"style='margin-top:-5px;'>
												 <button type="button" class="btn btn-primary" name="cruds" value="OUVRIR">VALIDER</button>
												 <button type="button" class="btn btn-warning" name="cruds" value="ANNULERO">ANNULER</button>
											 </div>
										</div>
									</div>
		                  </div>
		              </div>
			             
		                </div>
		              </div>
				      
				      <div class="modal-footer">
				        <button type="button" class="btn btn-danger" onclick="liens('comptabilite');">Retour</button>
				        <button name="cruds" value="OUVRIR" onclick="return envoiCash();" class="btn btn-success">Ouvrir la Caisse</button>
				      </div>
				      
				      	<input type="hidden" name="id" value="<c:out value="${c}"/>" />
				    </form>
				  </div>
				</div>
				</div>
           	     
    </div>
    
       
   <c:import url="/include/base_footer.jsp"></c:import>  

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
 
 <script>
 
 function liens(mode) {
		
		if (mode == 'comptabilite')
			document.location.href = '<c:url value="/menus?menu='+mode+'&ch=v"/>';
 }
 
 function startFlash() {
	    const flashElement = document.getElementById('flash');  
	    let flashing = true;

	    setInterval(() => {
	      if (flashing) {
	        flashElement.style.backgroundColor = 'yellow';
	      } else {
	        flashElement.style.backgroundColor = 'red';
	      }
	      flashing = !flashing;  // Alterne l'état
	    }, 500);  // Change toutes les 500 ms
	  }
	//Démarre le flash après 1 seconde
	  setTimeout(startFlash, 1000);
	
	  $(document).ready(function(){
			$("#1").attr("disabled","disabled");
			$.ajax({
				type : 'post',
				url : 'ajax',
				dataType: 'json',
				data : {
					crud: 'lesCaisses'
				}, success : function(result){
					var option = '<option selected></option>';
					$.each(result, function(index, value){
						option +='<option value="'+value.cd+'">'+value.libelle+'</option>';
					});
					document.getElementById("codeCaisse").innerHTML = option;
				}
			});
		});
 </script>
</body>
</html>