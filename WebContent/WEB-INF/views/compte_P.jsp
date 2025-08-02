<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="zxx" class="no-js">

  

<style>
        .formulaire {
            display: none;
        }
        .formulaire.active {
            display: block;
        }
        
          .messageAlertN {
            position: absolute;
            display: none;
            background-color: rgb(0, 0, 0);
            background-color: rgba(0, 0, 0, 0.4);
            top: 0;
            z-index: 80000;
            width: 100%;
            height: 100%;
        }

        .msgS {
            width: 400px;
            margin: 15% auto;
            border-radius: 5px 5px;
            text-align: center;
        }
    </style>

<head>

   <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
		<!-- Log on to codeastro.com for more projects -->
		<!-- Site Title -->
		<title>GESTION DES PROFILES</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTION DES PROFILES </h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN PROFILE 
          </button>
           &nbsp;&nbsp;  &nbsp;&nbsp;
           <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan1">
          TEST 
          </button>
          
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>PROFILE</th>                
                  <th>COMMENTAIRE</th>                  
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
               <c:forEach items="${listProfil}" var="lister" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }  <input type="text" name="test" value="${ boucle.index + 1 } "></td>
                  <td>${lister.profil}</td>
                  <td>${lister.commentaire}</td>                                                             
                  <td align="center">
                   <button   onclick="document.location.href='<c:url value="/profil?id=${lister.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
<div class="modal-content">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel">AJOUTER UN PROFILE</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form  action="<c:url value="/profil"/>" method="post"  class="myFormulaire">
      <div class="form-group">
        <label for="platbus" class="">LIBELLE PROFILE</label>
         <input type="text" name="profil" required class="form-control" value="<c:out value="${profil.profil}"/>">
      </div>
      <div class="form-group">
        <label for="seat" class="">COMMENTAIRE</label>
        <textarea rows="5" cols="40" class="form-control" name="commentaire"><c:out
				value="" />${profil.commentaire}</textarea>
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
		<div class="mesSupp">
			<div class="header"><i class="fa fa-warning fa-lg"></i><span> Alert Message</span></div>
			<div class="bodySupp">
				<p>Etes vous sure de vouloir supprimer cet élément?</p>
				<p>Pour confirmer clicker sur ok sinon cancel pour annuler.</p>
			</div>
			<form action="<c:url value="/paiements"/>" method="post" class="btnSupp" style='margin-top:-10px;'>
				<input type="text" name="id" value="<c:out value="${profil.id}"/>">
				<input type="text" name="page" id="page" value="<c:out value="${page}"/>">
				<input type="text" name="type" id="typeSup" value="">
				<input type="text" name="idS" id="idS" value="">
				<button class="btn-default" name="crud" value="SUPPRIMER">ok</button>
				<button class="btn-default" onclick="return ouvFermer('ferme')">cancel</button>
			</form>
		</div>
	</div>
	
	
<div class="modal fade" id="ModalTujuan1" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
 <div class="formulaire active" id="formulaire1">
<div class="modal-dialog" role="document">
<div class="modal-content">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel">AJOUTER 1</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form   id="myForm"  class="myFormulaire">
      <div class="form-group">
        <label for="platbus" class="">LIBELLE PROFILE</label>
         <input type="text" name="profil" id="profil" required onkeyup="this.value=this.value.toUpperCase()" class="form-control" value="<c:out value="${profil.profil}"/>" oninput="updateInputs()">
      </div>
      <div class="form-group">
        <label for="seat" class="">COMMENTAIRE</label>
        <textarea rows="5" cols="40" class="form-control" name="commentaire" id="commentaire"><c:out
				value="" />${profil.commentaire}</textarea>
      </div>
          
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
        <button name="crud" value="ECHEANCE" class="btn btn-success" id="myB" onclick="return ajouter(2);">Sauvegarder</button>
      </div>
    </form>
  </div>
</div>
</div>

  </div>

<div class="formulaire" id="formulaire2">
<div class="modal-dialog" role="document">
<div class="modal-content">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel">AJOUTER 2</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form  id="myFormS"  class="myFormulaire">
      <div class="form-group">
        <label for="platbus" class="">LIBELLE PROFILE</label>
         <input type="text" name="profil" required class="form-control" id="profil" value="<c:out value="${profil.profil}"/>">
      </div>
      <div class="form-group">
        <label for="seat" class="">COMMENTAIRE</label>
        <textarea rows="5" cols="40" class="form-control" name="commentaire"><c:out
				value="" />${profil.commentaire}</textarea>
      </div>
    
    <input type="text" name="test" id="input2" readonly>
      
                 
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
        <button type="button" value="AJOUTERS" onclick="return ajouterS(3);" class="btn btn-success">Sauvegarder</button>
        
          <button type="button" onclick="return ajouter(1);">Précédent</button>
      </div>
    </form>
  </div>
</div>
</div>

  </div>
  
  
<div class="formulaire" id="formulaire3">
<div class="modal-dialog" role="document">
<div class="modal-content">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel">AJOUTER 3</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form  id="myFormA"  class="myFormulaire">
      <div class="form-group">
        <label for="platbus" class="">LIBELLE PROFILE</label>
         <input type="text" name="profil" required class="form-control" value="<c:out value="${profil.profil}"/>">
      </div>
      <div class="form-group">
        <label for="seat" class="">COMMENTAIRE</label>
        <textarea rows="5" cols="40" class="form-control" name="commentaire"><c:out
				value="" />${profil.commentaire}</textarea>
      </div>
    
    <input type="text" name="test" id="input3" readonly>
      
                 
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
        <button type="button" value="AJOUTERA"  class="btn btn-success">Sauvegarder</button>
        
          <button type="button" onclick="return ajouterS(2);">Précédent</button>
      </div>
    </form>
  </div>
</div>
</div>

  </div>  
  
  
 
</div>

  <div class="messageAlertN ${pen}" id="msgN">
    <div class="msgS alert-info" id="smsAl">
        <div id="headSMS">Alert Message<span onclick="clse();">X</span></div>
        <p id="tes">${res}</p>
    </div>
</div>


<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
  <script>
        function ajouter(idFormulaire) {
            // Cacher tous les formulaires
            const formulaires = document.querySelectorAll('.formulaire');
            formulaires.forEach(formulaire => {
                formulaire.classList.remove('active');
            });

            // Afficher le formulaire sélectionné
            const formulaireActif = document.getElementById('formulaire' + idFormulaire);
            formulaireActif.classList.add('active');
            
            if($("#profil").val().length === 0){
                $("#profil").after('<span id ="erreur_nom" style="color:red;font-size:12px;">Merci de remplir ce champ</span>');
               
                setTimeout(function() {
         			  document.getElementById('erreur_nom').innerHTML="";
         			},2000);
                
                event.preventDefault(); 
            } 
            else{
        		var data =$("#myForm").serializeArray();
        		 data.push({name:"submit",value:"AJOUTER"});
        		 $.ajax({
        				type: 'post',
        				url: 'test',
        				dataType: 'json',
        				data: data , 
        				success: function(result){
        					 $.each(result, function (index, value) {                           	
                               	 document.getElementById("tes").innerHTML=value.res;
                                        	 
                               	if(value.res !== undefined )document.getElementById("msgN").style.display = "block";
                               	                           
                                                         
                                })
        				}
        			});
        	}	
return false;
}
        
        function ajouterS(idFormulaire) {
            // Cacher tous les formulaires
            const formulaires = document.querySelectorAll('.formulaire');
            formulaires.forEach(formulaire => {
                formulaire.classList.remove('active');
            });

            // Afficher le formulaire sélectionné
            const formulaireActif = document.getElementById('formulaire' + idFormulaire);
            formulaireActif.classList.add('active');
            
            if($("#profil").val().length === 0){
                $("#profil").after('<span id ="erreur_nom" style="color:red;font-size:12px;">Merci de remplir ce champ</span>');
               
                setTimeout(function() {
         			  document.getElementById('erreur_nom').innerHTML="";
         			},2000);
                
                event.preventDefault(); 
            } 
            else{
        		var data =$("#myFormS").serializeArray();
        		 data.push({name:"submit",value:"AJOUTERS"});
        		 $.ajax({
        				type: 'post',
        				url: 'test',
        				dataType: 'json',
        				data: data , 
        				success: function(result){
        					 $.each(result, function (index, value) {                           	
                               	 document.getElementById("tes").innerHTML=value.res;
                                        	 
                               	if(value.res !== undefined )document.getElementById("msgN").style.display = "block";
                               	                           
                                                         
                                })
        				}
        			});
        	}	
return false;
}        
        
        
        // Récupérer la valeur de l'input principal    
        function updateInputs() {
            // Récupérer la valeur de l'input principal
            var inputValue = document.getElementById('profil').value;
            
            // Mettre cette valeur dans les deux autres inputs
            document.getElementById('input2').value = inputValue;
            document.getElementById('input3').value = inputValue;
        }

       
       

        
     	// formulaire de message du popup,        
        function clse() {
            document.getElementById("msgN").style.display = 'none';
        }

        function opn() {
            document.getElementById("messageAlertN").style.display = 'block';
        }     
        
    </script>
 
 
</body>
</html>