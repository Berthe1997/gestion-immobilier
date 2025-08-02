<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from client_bien 
</sql:query>

<sql:query var="listR" dataSource="jdbc/gestions_imm">
	select * from bien_meuble where statut <>'occupe'
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
		<title>CLIENT</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">CLIENT</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_cli==1}">
          <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
          AJOUTER UN CLIENT
          </button>
          </c:if> 
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>REFERENCE</th>
                  <th>NOM </th>
                  <th>PRENOM</th>                                
                  <th>CONTACT</th>
                  <th>EMAIL</th>    
                  <th>DATE AJOUT </th>           
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.matricule}</td>
                  <td>${mapProp.nom}</td>
                  <td>${mapProp.prenom}</td>                 
                  <td>${mapProp.tel}</td>
                  <td>${mapProp.email}</td>
                  <td>${mapProp.date_ajout}</td>                                                
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_clientB?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                    <c:if test="${sessionScope.rolePr.supp_cli==1}">
                   <button onclick="document.location.href='<c:url value="/archive_CR?id=${mapProp.id}&matri=${mapProp.matricule}&bien=${mapProp.bien}&page=${page}&crud=archive" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
 <div class="formulaire active" id="formulaire1">
	<div class="modal-dialog" role="document">
	<div class="modal-content" style="width:810px;margin-left:-35px;">
	  <div class="modal-header">
	    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN CLIENT </h5>
	    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	    </button>
	  </div>
	  <div class="modal-body">
	    <form id="myFormCL"  class="myFormulaire">
	      <div class="form-group row">
	       <div class="col-sm-4 mb-2 mb-sm-0">
	        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
	        <input type="text" name="nom" id="nom" class="form-control" required  value="">
	        </div>
	         <div class="col-sm-4 mb-2 mb-sm-0">
	         <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
	         <input type="text" name="prenom" id="prenom" class="form-control" required  value="">
	        </div>
	         <div class="col-sm-4 mb-2 mb-sm-0">
	        <label for="platbus" class="">CONTACT<span class="text-danger ml-2">*</span></label>
	          <input type="text" name="tel" id="tel" class="form-control" required  value="">
	        </div>      
	      </div>    
	      <div class="form-group row">
	         <div class="col-sm-4 mb-2 mb-sm-0">
	         <label for="platbus" class="">EMAIL</label>
		     <input type="text" name="email" id="email" class="form-control" required  value="">
	       </div>       		
			<div class="col-sm-4 mb-4 mb-sm-0">
	         <label for="platbus" class="">NUMERO CNI</label>
			   <input type="text" name="num_cni" id="num_cni" class="form-control" required  value="">
			</div>	
			<div class="col-sm-4 mb-4 mb-sm-0">
	         <label for="platbus" class="">NATIONALITE</label>
			  <sql:query var="listPays" dataSource="jdbc/gestions_imm">
				select * from pays 
			</sql:query>
	         <select name="nationalite" id="nationalite" required class="form-control">
			   <option value="" selected></option>
			   <c:forEach items="${listPays.rows}" var="mapP" varStatus="boucle">
				 <option value="${mapP.pays}" class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">${mapP.pays}</option>
				</c:forEach>
			 </select>
			</div>		
	      </div>
	      <div class="form-group row"> 
		      <div class="col-sm-5 mb-2 mb-sm-0">
		         <label for="platbus" class="">Document</label>
			      <img alt="${client_bien.document}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${client_bien.document!=null?client_bien.document:'erreur.jpg'}" style="font-family:bold; color:#000;">
				 <input type="file" id="file" name="document" id="document" onchange="addPhoto('ecole');" accept=".png,.jpg,.jpeg" >
		       </div> 
		       <div class="col-sm-4 mb-2 mb-sm-0">
		        <label for="date_naiss" class="">DATE DE CREATION<span class="text-danger ml-2">*</span></label>
		         <input type="date" required  name="date" id="date" class="form-control" value="">
		        </div>
		 </div> 
	            
	      <div class="modal-footer">
	        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
	        <button type="submit" value="AJOUTER" class="btn btn-success" onclick="return ajouter(2);">Sauvegarder</button>
	      </div>
	    </form>
	  </div>
	</div>
	</div>
  </div>
  
  <!--=========================================== FORMULAIRE DE CONTRAT DE LOYER ================================================= -->
<div class="formulaire" id="formulaire2">
    <div class="modal-dialog" role="document">
                     <div class="modal-content" style="width:800px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">RESERVATION CLIENT</h3>
                      </div>
                      <form action="<c:url value="/reserve"/>" method="post"  class="myFormulaire">
                      <input type="hidden" name="client" id="client" >					    					                    
                        <div class="modal-body">                           
                         <div class="form-group row">
					       <div class="col-sm-4 mb-2 mb-sm-0">
					        <label for="platbus" class="">BIEN<span class="text-danger ml-2">*</span></label>
					          <select name="bien" id="bien" required class="form-control" onchange="ajax('getBiens');">
								 <option value="" selected ></option>
								 <c:forEach  items="${listR.rows}" var="mapR">
								  <option value="<c:out value="${mapR.bien}"/>"> <c:out value="${mapR.bien}"/></option>
								</c:forEach>							    
							  </select>
					        </div>
					         <div class="col-sm-4 mb-2 mb-sm-0">
					        <label for="platbus" class="">TYPE BIEN<span class="text-danger ml-2">*</span></label>
					         <input type="text" name="type" id="types" class="form-control"   value="">
					        </div>
					         <div class="col-sm-4 mb-2 mb-sm-0">
					        <label for="platbus" class="">ADRESSE <span class="text-danger ml-2">*</span></label>
					         <input type="text" name="adresse" id="adresses" class="form-control"   value="">
					        </div>      
					      </div>    					      
					      <div class="form-group row">  
					       <div class="col-sm-4 mb-4 mb-sm-0">
					         <label for="platbus" class="">DATE D'ENTREE</label>
							   <input type="date" name="dateE" id="date1" class="form-control" value="">
							</div>
							<div class="col-sm-4 mb-4 mb-sm-0">
					         <label for="platbus" class="">DATE SORTIE</label>
							   <input type="date" name="dateS" id="date2" class="form-control" value="">
							</div> 
							<div class="col-sm-4 mb-4 mb-sm-0">
					         <label for="platbus" class="">NOMBRE SEJOUR</label>
							   <input type="text" name="nombre" id="resultat" class="form-control" value="" >
							</div>						       
					    </div> 
					    <div class="form-group row">
					         <div class="col-sm-4 mb-2 mb-sm-0">
					           <label for="seat" class="">DESCRIPTION BIEN</label>
					            <textarea rows="2" cols="40" class="form-control" name="description" id="descrip"><c:out
									value="" /></textarea>
					           </div> 
					         <div class="col-sm-4 mb-2 mb-sm-0">
					         <label for="platbus" class="">LOYER NUIT</label>
						     <input type="text" name="loyerN" id="loyerN" class="form-control" value="">
					       </div>       																								
					      </div>                                                                                                      
                      </div>
                      <div class="modal-footer">                      
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button type="button" class="btn btn-info" onclick="return ajouter(1);">Précédent</button>                         
                        <button class="btn btn-success" name="crud"  value="RESERVER" >Enregistre</button>
                      </div>
                      
                  </form>
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
 
 // CALCULE EN TRE DEUX DATES
const date1Input = document.getElementById("date1");
    const date2Input = document.getElementById("date2");
    const resultatInput = document.getElementById("resultat");

    function calculerJours() {
        const val1 = date1Input.value;
        const val2 = date2Input.value;

        if (val1 && val2) {
            const date1 = new Date(val1);
            const date2 = new Date(val2);
            const diffMs = date2 - date1;
            const jours = Math.floor(diffMs / (1000 * 60 * 60 * 24));
            resultatInput.value = Math.abs(jours);
        } else {
            resultatInput.value = '';
        }
    }

    date1Input.addEventListener("change", calculerJours);
    date2Input.addEventListener("change", calculerJours);

 
  //=======================================NOMBRE=======================================================//	
	function onlyNumberKey(evt) {
	     
	     // Only ASCII character in that range allowed
	     var ASCIICode = (evt.which) ? evt.which : evt.keyCode
	     if (ASCIICode > 31 && (ASCIICode <48 ||ASCIICode > 57))
	         return false;
	     return true;
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
		
		     var data =$("#myFormCL").serializeArray();
			 data.push({name:"submit",value:"AJOUTER"});
  		 $.ajax({
  				type: 'post',
  				url: 'clientB',
  				dataType: 'json',
  				data: data , 
  				success: function(result){    				
  					 $.each(result, function (index, value) {                           	
                         	 document.getElementById("tes").innerHTML=value.res;
                                  	 
                         	if(value.res !== undefined )document.getElementById("msgN").style.display = "block";
                                               
                         	var mat = value.matricule;    
                         	document.getElementById("client").value	=	mat;
                         	                      
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