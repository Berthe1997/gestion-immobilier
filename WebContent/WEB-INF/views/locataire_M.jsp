<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from locataire where code="${sessionScope.agence.code}" and type="moral" and archive="0"
</sql:query>

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
        
        	.mesSuppN{
	background: #ffffff;
	border: 1px solid #e7b2c7;
	border-radius: 5px 5px;
	width: 400px;
	height: 200px;
	margin: 15% auto;
}

.headerN{
	background:#0080ff;
	padding: 5px;
	text-align: center;
	color: #FFF;
}

.headerN span{font-size:16px;font-weight:bold;}
.headerN i{float:left;margin-top:5px;}
    </style>

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
		<title>GESTIONS DES LOCATAIRES</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTIONS DES LOCATAIRES-MORAL</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_LO==1}">
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN LOCATAIRE MORAL
          </button>
          </c:if>       
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>NOM </th>                              
                  <th>CONTACT</th>                 
                  <th>DATE D'ENTREE</th>
                  <th>NUM PORTE</th>
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
             <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.nom}</td>                  
                  <td>${mapProp.tel}</td>               
                  <td> ${mapProp.date_entree}</td>    
                  <td> ${mapProp.num_porte}</td>                                   
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_locataire?id=${mapProp.id}&page=${page}&crud=idM" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                  <c:if test="${sessionScope.rolePr.supp_LO==1}">
                   <button onclick="document.location.href='<c:url value="/locataire_M?id=${mapProp.id}&matri=${mapProp.matricule}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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

<!--=========================================== FORMULAIRE DE LOCATAIRE ================================================= -->
<div class="modal fade" id="ModalTujuan" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
 <div class="formulaire active" id="formulaire1">
<div class="modal-dialog" role="document">
<div class="modal-content" style="width:910px;margin-left:-35px;">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN LOCATAIRE-MORAL</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form id="myFormL"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-4 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM <span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" id="nom" class="form-control" required  value="">
        </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">CONTACT</label>
		 <input type="text" name="tel" id="tel" class="form-control" value="" oninput="updateInputs()">
        </div>
         <div class="col-sm-4 mb-2 mb-sm-0">
           <label for="platbus" class="">TEL WHATSAPP</label>
		   <input type="text" name="telW"  class="form-control" value="" >       
        </div>       
      </div>      
      
      <div class="form-group row">        
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">EMAIL </label>
	     <input type="text" name="email" id="email" class="form-control" value="">
       </div>
        <div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">TYPE DE CONTRAT </label>
		 <select name="type_contrat" id="type_contrat" class="form-control">
			<option value="<c:out value="${locataire.type_contrat}"/>" selected><c:out value="${locataire.type_contrat}"/></option>
			<option value="BAIL LOCATIF">BAIL LOCATIF</option>
			<option value="CONTRAT">CONTRAT</option>	
			<option value="COMMERCIAL">COMMERCIAL</option>	   								
		</select>
		</div>
		<div class="col-sm-4 mb-2 mb-sm-0">
         <label for="platbus" class="">DATE D'ENTREE </label>
		  <div class='input-group date' id='datepicker'>
           <input type='date' name="date_entree" id="date_entree" class="form-control" value=""  required/>
               
           </div>
		</div>
	   </div>		
		<div class="form-group row">      
	       <div class="col-sm-4 mb-2 mb-sm-0">
	       <label for="platbus" class="">CAUTION</label>
		   <input type="checkbox" class="form-control"  name="p_C"  value="1"  />
	       </div>
	       <div class="col-sm-4 mb-2 mb-sm-0">
	        <label for="platbus" class="">AVANCE</label>
			 <input type="checkbox" class="form-control"  name="p_A"  value="1"  />
	       </div>
	        <div class="col-sm-4 mb-2 mb-sm-0">       
	        <label for="platbus" class="">COMMISSION D'ENTREE </label>
		    <input type="checkbox" class="form-control"  name="p_CO"  value="1"  /> 
	       </div>
      			
      </div>              
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
        <button type="submit"  value="AJOUTERL" class="btn btn-success"  onclick="return ajouter(2);">Sauvegarder</button>
      </div>
    </form>
  </div>
</div>
</div>
</div>

<!--=========================================== FORMULAIRE DE CONTRAT DE LOYER ================================================= -->
<div class="formulaire" id="formulaire2">
    <div class="modal-dialog" role="document">
                     <div class="modal-content" style="width:600px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Generer le Calendrier de Paie</h3>
                      </div>
                      <form id="myFormC" class="myFormulaire">
                      <input type="hidden" name="locataire" id="locataire" >					    					                    
                        <div class="modal-body">                           
                         <div class="form-group row"> 
                             <div class="col-sm-4 mb-2 mb-sm-0">
						       <label for="platbus" class="">TYPE CONTRAT</label>	
						        <select name="type" id="type" class="form-control">									
									<option value="ANNEE" selected>ANNEE</option>
									<option value="MOIS">MOIS</option>	  								
								</select>						
						       </div>
						       <div class="col-sm-4 mb-2 mb-sm-0">
						        <label for="platbus" class="">DUREE CONTRAT</label>	
						        <input type="text" name="dure" id="dure" class="form-control" value="" onkeypress="return onlyNumberKey(event)">								
						       </div> 
						       <div class="col-sm-4 mb-2 mb-sm-0">
						        <label for="platbus" class="">DATE ECHEANCE</label>
						         <input type='text' name="date_entrees" id="date_entrees" class="form-control datepicker" value="" placeholder="Date..." required/>
						         <span class="input-group-addon">
				                 <span class="glyphicon glyphicon-calendar"></span>
				                 </span>	
						       </div>   
                          </div>  
                          
                           <input type="hidden" id="tel2" name="tel2"  value="" >                                                    
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                       <button type="button" class="btn btn-info" onclick="return ajouter(1);">Précédent</button> 
                        <button type="submit"  value="AJOUTERS" class="btn btn-success" onclick="return ajouterS(3);" >Enregistre</button>
                      </div>
                  </form>
                 </div>
               </div>

</div>

<!--=========================================== FORMULAIRE DE MAISON ================================================= -->
<div class="formulaire" id="formulaire3">

<div class="modal-dialog" role="document">
<div class="modal-content" style="width:650px;height:500px;">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel">AJOUTER UNE PORTE</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
     <div class="inputCherche" style="width:40%">
                    <input type="text" class="form-control" placeholder="Rechercher" id="filtre"
                           onkeyup="filtrer();"  name="keyword"/>                         
                    <span class="highlight var"></span>
                    <span class="bar var"></span>                   
                </div>
         <div class="tablets" id="firstTable" style="width:100%">
			    <div id="myTableEtd" class="myTableEleve" style="border: 1px solid #a9dcce;">
                    <div class="table">
                        <div class="thead">
                            <div class="tr ht">
                                <div class="td stick1 hdr1" style="width:10%;">N°</div>
                                <div class="td stick1 hdr2" style="width:20%;">PORTE</div>
                                <div class="td stick1 hdr3" style="width:20%">PIECE</div>
                                <div class="td stick1 hdr1" style="width:40%;">CHAMBRE</div>
                                <div class="td stick1 hdr2" style="width:20%;">ETAGE</div>
                                <div class="td stick1 hdr3" style="width:20%">BALCON</div> 
                                <div class="td stick1 hdr2" style="width:20%;">TERRASSE</div>
                                <div class="td stick1 hdr3" style="width:20%">PRIX</div>    
                                <div class="td stick1 hdr3" style="width:20%">ACTION</div>                                
                            </div>
                        </div>
                        <div class="tbody" id="tbody">
                        <sql:query var="listProp" dataSource="jdbc/gestions_imm">
							select * from maison where site="${sessionScope.site.site}" and occupe ='0'
					  </sql:query>
                            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">                               
                                    <div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">                                       
                                        <div class="td text-center" style="width:10%;">${ boucle.index + 1 }</div>
                                        <div class="td text-center" style="width:20%;"> ${mapProp.maison} </div> 
                                        <div class="td text-center" style="width:20%;"> ${mapProp.piece} </div>
                                        <div class="td text-center" style="width:20%;"> ${mapProp.chambre} </div> 
                                        <div class="td text-center" style="width:20%;"> ${mapProp.etage} </div>
                                        <div class="td text-center" style="width:20%;"> ${mapProp.balcon==1?'Oui':'Non'} </div> 
                                        <div class="td text-center" style="width:20%;"> ${mapProp.terrasse==1?'Oui':'Non'} </div>
                                        <div class="td text-center" style="width:20%;"> ${mapProp.prix} </div> 
                                        <div class="td text-center" style="width:20%;"> 
                                          <input type="hidden" value="${ mapProp.id}"/>                                           
                                           <c:if test="${sessionScope.rolePr.supp_AP==1}">
							                   <button class="btn-primary" onclick="ouvrirAM(this);">
									                ajouter 
									           </button>
							                  </c:if>
                                         </div>                                                                                                                                                                                                                              
                                    </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
			  </div>             
  </div>
  
  
  <!----------------AJOUTER MAISONS--------------------------------->
<div class="suppPop" id="supp">
		<div class="mesSuppN">
			<div class="headerN"><i class="fa fa-warning fa-lg"></i><span> Alert Message</span></div>
			<div class="bodySupp">
				<p>Etes vous sure de vouloir d'ajouter cet élément?</p>
				<p>Pour confirmer clicker sur ok sinon cancel pour annuler.</p>
			</div>
			<form action="<c:url value="/view_maison"/>" method="post" class="btnSupp" style='margin-top:-10px;'>				
				<input type="hidden" name="page" id="page" value="<c:out value="${page}"/>">
				<input type="hidden" name="types" id="typeSup" value="">
				<input type="hidden" name="idM" id="idM" value="">				
				 <input type="hidden" id="matriculeM" name="matriculeM"  value="" >
				<button class="btn btn-danger" name="crud" value="MODIFIERMM">ok</button>
				<button class="btn btn-primary" onclick="return ouvFermer('ferme')">cancel</button>
			</form>
		</div>
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
 <script type="text/javascript">
 
//--------------FONCTION FILTRE-------------------------------//
	function filtrer() {
     var filtre, tbody, ligne, cellule, cellule_0, i, texte_1, texte_2;
     filtre = document.getElementById("filtre").value.toUpperCase();
     tbody = document.getElementById("tbody");
     ligne = tbody.querySelectorAll('div');

     for (i = 0; i < ligne.length; i++) {
         cellule_0 = ligne[i].getElementsByTagName("div")[0];
         cellule = ligne[i].getElementsByTagName("div")[1];
         cellule_2 = ligne[i].getElementsByTagName("div")[2];
         if (cellule_0) {
             texte_1 = cellule_0.innerText;
             texte_2 = cellule.innerText;
             texte_3 = cellule_2.innerText;
             if (texte_1.toUpperCase().indexOf(filtre) > -1) {
                 ligne[i].style.display = "";
             } else {
                 if (texte_2.toUpperCase().indexOf(filtre) > -1) {
                     ligne[i].style.display = "";
                 } else {
                     if (texte_3.toUpperCase().indexOf(filtre) > -1) {
                         ligne[i].style.display = "";
                     } else {
                         ligne[i].style.display = "none";
                     }
                 }
             }
         }
     }
 }
 
	//=======================================SUPPRIMER=======================================================//
	function ouvrirAM(tag){
	var parent	=	$(tag).parent();
	var valeur = parent.find("input").val();
	document.getElementById("supp").style.display = "block";
	document.getElementById("typeSup").value = "supS";
	document.getElementById("idM").value = valeur;
	document.getElementById("maisons").value = valeur;
	document.getElementById("prix").value = valeur;
	
	
}
 
	//=======================================NOMBRE=======================================================//	
	function onlyNumberKey(evt) {
	     
	     // Only ASCII character in that range allowed
	     var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	     if (ASCIICode > 31 && (ASCIICode <48 ||ASCIICode > 57))
	         return false;
	     return true;
	 }

 //=======================================FONCTION DATE=======================================================//
	  $(function(){
          var date = new Date();
          date.setDate(date.getDate());

        $(".datepicker").datepicker({
            format: 'yyyy-mm-dd',
            autoclose: true,
        });
       });
	  
	//============================================== Récupérer la valeur de l'input principal==============================//    
      function updateInputs() {
          // Récupérer la valeur de l'input principal
          var inputValue = document.getElementById('tel').value;
          
          // Mettre cette valeur dans les deux autres inputs
          document.getElementById('tel2').value = inputValue;
          document.getElementById('tel3').value = inputValue;
      }

   //=======================================ENREGITREMENT FORMULAIRE1=======================================================//
	  function ajouter(idFormulaire) {
		     // Cacher tous les formulaires
		     const formulaires = document.querySelectorAll('.formulaire');
		     formulaires.forEach(formulaire => {
		         formulaire.classList.remove('active');
		     });
		     
		   //  var locataire = document.getElementById("locataire").value; 

		     // Afficher le formulaire sélectionné
		     const formulaireActif = document.getElementById('formulaire' + idFormulaire);
		     formulaireActif.classList.add('active');
		
		     var data =$("#myFormL").serializeArray();
			 data.push({name:"submit",value:"AJOUTER"});
    		 $.ajax({
    				type: 'post',
    				url: 'locataire_M',
    				dataType: 'json',
    				data: data , 
    				success: function(result){    				
    					 $.each(result, function (index, value) {                           	
                           	 document.getElementById("tes").innerHTML=value.res;
                                    	 
                           	if(value.res !== undefined )document.getElementById("msgN").style.display = "block";
                                                 
                           	var mat = value.matricule;    
                           	document.getElementById("locataire").value	=	mat;
                           	
                           	var matM = value.matricule;    
                           	document.getElementById("matriculeM").value	=	matM;
                            })
                           
    				}    		 
    			});    
return false;
	  
	  }
	  
	//=======================================ENREGITREMENT FORMULAIRE2=======================================================//
	  function ajouterS(idFormulaire) {
		     // Cacher tous les formulaires
		     const formulaires = document.querySelectorAll('.formulaire');
		     formulaires.forEach(formulaire => {
		         formulaire.classList.remove('active');
		     });

		     // Afficher le formulaire sélectionné
		     const formulaireActif = document.getElementById('formulaire' + idFormulaire);
		     formulaireActif.classList.add('active');
		
		     var data =$("#myFormC").serializeArray();
		     data.push({name:"submit",value:"AJOUTERS"});
 		 $.ajax({
 				type: 'post',
 				url: 'locataire_M',
 				dataType: 'json',
 				data: data , 
 				success: function(result){
 					 $.each(result, function (index, value) {                           	
                        	 document.getElementById("tes").innerHTML=value.res;
                                 	 
                        	if(value.res !== undefined )document.getElementById("msgN").style.display = "block";
                        	                           
                                                  
                         })
 				}
 			});    
return false;
	  
	  }
	  
	  
	
   	//=========================================formulaire de message du popup====================================//,        
      function clse() {
          document.getElementById("msgN").style.display = 'none';
      }

      function opn() {
          document.getElementById("messageAlertN").style.display = 'block';
      }     
			

	</script>  
</body>
</html>