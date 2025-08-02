<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>



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
		<title> LISTE RESERVATION</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">LISTE RESERVATION   <a class="btn btn-danger float-left" href="<c:url value="/menus?menu=reservation"/>"> Retour</a></h6>
        </div>
        <div class="card-body">
           <div class="form-group row">
              
            <div class="col-sm-12 mb-10 mb-sm-0"> 
                 <div class="table-responsive">
		            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
		            <thead class="thead-light">
		                <tr>		                  
		                  <th>nom client</th>
		                  <th>contact</th>
		                  <th>bien</th>                                
		                  <th>description</th> 
		                  <th>loyer-nuit</th>
		                  <th>adresse</th>   
		                  <th>total séjour</th>  
		                  <th>net-payer</th>                                                
		                  <th>Action</th>
		                </tr>
                  </thead>
                  <tbody>
             
              <sql:query var="listLoc" dataSource="jdbc/gestions_imm">
				select * from client_bien as c ,reservation as r, bien_meuble as b  
				where c.matricule=r.client and b.bien=r.code_bien   and montant_net<>'0'
			</sql:query>
              <c:forEach items="${listLoc.rows}" var="mapLoc" varStatus="boucle">
		                <tr>              
		                  <td>${mapLoc.nom} ${mapLoc.prenom}</td>
		                  <td>${mapLoc.tel}</td> 
		                  <td>${mapLoc.code_bien}</td>  	
		                  <td>${mapLoc.description}</td> 
		                  <td>${mapLoc.loyer_nuit} FCFA</td> 
		                  <td>${mapLoc.adresse}</td>
		                  <td>${mapLoc.nombre} jour</td>
		                  <td>${mapLoc.montant_net}</td>		              		                  		                 		                                                                     
		                  <td align="center">		                 
		                   <a target="_BLANK" href="<c:url value="/Recu_reserve?id=${mapLoc.id}&matE=${mapLoc.matricule}&montantS=${mapLoc.montant}&code=${mapPaiL.code}"/>" style="border:1px solid grey;border-radius:3px;padding:4px;color:#000;"><i class="fa fa-print fa-lg"></i></a>
							 <c:if test="${sessionScope.rolePr.supp_PL==1}">
							<button onclick="document.location.href='<c:url value="/reserve_R?idl=${mapLoc.id}&matricule=${mapLoc.client}&code=${mapLoc.code_reservation}&date=${mapLoc.date_A}&heure=${mapLoc.heure}&soldeP=${mapLoc.montant_P}&soldeA=${mapLoc.commissionA}&page=${page}&crud=PaiL" />'">
							<i class="fas fa-trash fa-lg"></i>
							</button>
							</c:if> 										
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