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
		<title> GESTION DOCUMENT LOCATAIRE</title>
<style>		
		.bmenu a {
	background: #00ff00;
	margin-bottom: 10px;
	padding: 10px;
	width: 100%;
	color: #FFF;
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
      <div class="card shadow mb-4">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">GESTIONS DOCUMENT LOCATAIRE</h6>
        </div>
        <div class="card-body">
          <div class="form-group row bmenu">
             <div class="col-sm-4 mb-3 mb-sm-0"> 
	             <c:if test="${sessionScope.rolePr.coutM==1}">
	                <a class="btn btn-info" href="#lap_jual_pertanggal_1" data-toggle="modal">Contrat Arrivé Locataire</a>
	             </c:if>                 
                 <c:if test="${sessionScope.rolePr.coutM==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if> 
             </div>            
             <div class="col-sm-4 mb-3 mb-sm-0">    
                <c:if test="${sessionScope.rolePr.etat_FL==1}">
	               <a class="btn btn-info" href="#lap_jual_pertanggal_2" data-toggle="modal">Information Locataire</a>
	             </c:if> 
	              <c:if test="${sessionScope.rolePr.etat_FL==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>                         
             </div>
              <div class="col-sm-4 mb-3 mb-sm-0">
                 <c:if test="${sessionScope.rolePr.etat_FS==1}">
	                   <a class="btn btn-info" href="#lap_jual_pertanggal_33" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>  
	              <c:if test="${sessionScope.rolePr.etat_FS==0}">
	                <a class="btn btn-info" href="" data-toggle="modal">/+++++++++++++++++++/</a>
	             </c:if>             
             </div>                                       
          </div>
          
          <!-- ============ ============ ============ COUT MENSUELLE DES LOYERS  ============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_1" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Côut Mensuelle Des Loyer Par Site</h3>
                      </div>
                      <form action="<c:url value="/Etat_Bilan"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Libelle Site</label>      
                           </div>
                            <div class="col-sm-8 mb-3 mb-sm-0">
                            <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from site where site="${sessionScope.site.site}" 
							 </sql:query>
					         <select name="site" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.site}">${mapS.site}</option>
									  </c:forEach>
							 </select>	  
							 <input type="hidden" name="code" value="AGIL">
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="coutM" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>
              
          <!-- ============ ============ ============ ETAT FINACIER INDIVIDUEL============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_2" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat Financier Par Locataire</h3>
                      </div>
                      <form action="<c:url value="/Etat_Financier"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-5 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Nom Prenom</label>      
                           </div>
                            <div class="col-sm-7 mb-4 mb-sm-0">
                            <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from locataire where site="${sessionScope.site.site}" 
							 </sql:query>
					         <select name="matE" required class="form-control">
									  <option value="<c:out value="${locataire.matricule}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.matricule}">${mapS.nom} ${mapS.prenom}</option>
									  </c:forEach>
							 </select>	 							
							  <input type="hidden" name="code" value="AGIL">							
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button class="btn btn-success" name="type" value="locataire"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>
       
        <!-- ============ ============ ============ ETAT FINACIER PAR SITE============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_3" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Etat Financier Par Site</h3>
                      </div>
                      <form action="<c:url value="/Etat_Financier"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-4 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Libelle Site</label>      
                           </div>
                            <div class="col-sm-8 mb-4 mb-sm-0">
                             <sql:query var="Site" dataSource="jdbc/gestions_imm">
								select * from site where site="${sessionScope.site.site}" 
							 </sql:query>
					         <select name="site" required class="form-control">
									  <option value="<c:out value="${site.site}"/>" selected></option>
									  <c:forEach items="${Site.rows}" var="mapS" varStatus="boucle">
										<option value="${mapS.site}">${mapS.site}</option>
									  </c:forEach>
							 </select>	
							   <input type="hidden" name="code" value="AGIL">
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>
                        <button name="type" value="site" class="btn btn-success"><span class="fa fa-print"></span> Print</button>
                      </div>
                  </form>
                 </div>
               </div>
              </div>   
          
       
          
      </div>
      </div>    	     
    </div>
     
   <c:import url="/include/base_footer.jsp"></c:import>  

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
</body>
</html>