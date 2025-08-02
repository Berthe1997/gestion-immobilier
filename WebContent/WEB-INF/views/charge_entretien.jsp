<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listDep" dataSource="jdbc/gestions_imm">
	select * from depot where site="${sessionScope.site.site}"  ORDER BY id DESC LIMIT 1
</sql:query>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from maison where site="${sessionScope.site.site}"
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
		<title>GESTION CHARGES-ENTRETIENS</title>
				
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">GESTION CHARGES-ENTRETIENS <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=comptabilite"/>"> Retour</a></h6>
        </div>
        <div class="card-body">  
        <div id="alert"  class="alert-danger" style="display:none;position:absolute;width:450px;text-align:center;padding:5px;font-weight:bold;"> </div>                     
          <div class="card-body">           	     
		  <div class="form-group row">
		      <div class="col-sm-12 mb-10 mb-sm-0">
		      
		      </div>
		  </div>
		  <div class="form-group row">
		      <div class="col-sm-3 mb-2 mb-sm-0">
		       <label for="seat" class="m-0 font-weight-bold text-gray">Solde Compte Decaissement</label>	       
			  </div>
			  <div class="col-sm-2 mb-1 mb-sm-0">	
			   <c:forEach items="${listDep.rows}" var="mapDep" varStatus="boucle">    		     
		       <input type="text" class="form-control" required name="password" value="<c:out value="${mapDep.montantR}"/>" disabled/>
			 </c:forEach>
			  </div>
			  
		<!-------------------------------------------------- POPUP NUMERO 1----------------------------------------- --> 	  			  
			  <div class="col-sm-4 mb-2 mb-sm-0" style="text-align:right;">	  
			    <button onclick="ouvFermer('ouvNiv');" class="btn btn-primary">Effectuer Un Decaissement</button>
			  <div class="suppPop" id="niveauxMat" style="margin-left:12px;">
				    <div class="mesSupp">
					<div class="header"><i class="fas fa-solid fa-coins"></i><span> EFFECTUER UN DECAISSEMENT</span></div>
					<form action="<c:url value="/ChargesE"/>" method="post" style="padding:30px 15px;">
						
						<c:forEach items="${listDep.rows}" var="mapDep" varStatus="boucle">								  							
							         <input type="hidden" name="montantR"  value="<c:out value="${mapDep.montantR}"/>" id="solde">
							         <input type="hidden" name="idD"  value="<c:out value="${mapDep.id}"/>">
							 </c:forEach>
						
						 <div class="form-group row">
						  <div class="col-sm-5 mb-4 mb-sm-0">
						     <label for="seat" class="">NUMERO PORTE</label>				             
							</div>
							<div class="col-sm-7 mb-6 mb-sm-0">
							 <select class="form-control" name="porte" >
								<option value=" "></option>
								<option value="<c:out value="${maison.maison}"/>" selected><c:out value="${maison.maison}"/></option>
								<c:forEach  items="${listProp.rows}" var="mapProp">
									<option value="${mapProp.maison}">${mapProp.maison}</option>
								</c:forEach>
							</select>
							</div>
						 </div>
						  <div class="form-group row">
			              <div class="col-sm-9 mb-8 mb-sm-0">
							<label for="seat" class="">CHARGES-ENTRETIEN</label>
				            <input type="text" class="form-control" required  name="charge" value="<c:out value="${utilisateur.email}"/>" id="email">
						   </div>
						   <div class="col-sm-3 mb-2 mb-sm-0">
						     <label for="seat" class="">MONTANT</label>
				              <input type="text" class="form-control" style="width:85px;" required name="montantDe" value="" onkeyup="montantSaisi('saisi');" id="mttSaisi"/>
							</div>
						</div>
						<div class="lesBtn" style="text-align:right;margin-top:10px;">
							<button class="btn-default" name="crud" value="DECAISSEMENT" onclick="return montantSaisi('envoi');">DECAISSER</button>
							<button class="btn-default" onclick="ouvFermer('ferNiv');">cancel</button>
						</div>
					</form>
				</div>
	         </div>			    		     		      
			 </div>	
			 
	<!-------------------------------------------------- POPUP NUMERO 2----------------------------------------- -->  		  
			 <div class="col-sm-3 mb-3 mb-sm-0" style="text-align:right;">			    
                <button onclick="return ouvFermer('ajoutE');" class="btn btn-info">Effectuer Un Depôt</button> 
                
                  <div class="suppPop" id="supp" style="margin-left:40px;">
					   <div class="mesSupp"style="width:210px;height:160px;">
						<div class="header"><i class="fa fa-dollar-sign"></i><span> EFFECTUER UN DEPÔT</span></div>
						<form action="<c:url value="/ChargesE"/>" method="post" style="padding:30px 15px;">							
							<input type="hidden" name="page" id="page" value="<c:out value="${page}"/>">
							<c:forEach items="${listDep.rows}" var="mapDep" varStatus="boucle">								  							
							         <input type="hidden" name="montantR"  value="<c:out value="${mapDep.montantR}"/>">
						     </c:forEach>
							 <div class="form-group row">
				              <div class="col-sm-1 mb-1 mb-sm-0">
								<input type="text" name="montantD" value="" onkeypress="return onlyNumberKey(event)"   style="width:120px;"/>
							   </div>						   
							</div>
							<div class="lesBtn" style="text-align:left;margin-top:20px;">
								<button class="btn-default" name="crud" value="DEPOT">VALIDER</button>
								<button class="btn-default" onclick="return ouvFermer('ferme')">cancel</button>
							</div>
						</form>
					</div>
				</div>
			  </div>			      
	      </div>                   
      </div> 
        <!-------------------------------------------------- TABLEAUX----------------------------------------- -->      
         <div class="card-body" style="margin-top:-40px;">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-light">
                <tr>
                  <th>N°</th>                  
                  <th>NUMERO PORTE</th>    
                  <th>CHARGE-ENTRETIEN</th>                               
                  <th>MONTANT-DECAISSER</th>
                  <th>DATE</th>                                  
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
             <sql:query var="listDec" dataSource="jdbc/gestions_imm">
				select * from charges_entretiens where site="${sessionScope.site.site}" order by mois desc ;
			</sql:query>
              <c:forEach items="${listDec.rows}" var="mapDec" varStatus="boucle">
                <tr>              
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapDec.porte}</td> 
                  <td>${mapDec.type_charge}</td>  
                  <td>${mapDec.montantD}</td>  
                  <td>${mapDec.date}</td>                                                                  
                  <td align="center">
                   <button class="" onclick="document.location.href='<c:url value="/ChargesE?id=${mapDec.id}&montantDe=${mapDec.montantD}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
                  </td>
               
              </tr>
             </c:forEach>
            </tbody>
          </table>
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