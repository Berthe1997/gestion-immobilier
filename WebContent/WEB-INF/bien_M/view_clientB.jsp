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
		<title>  CLIENT</title>
		
	
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
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER  CLIENT   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_clientB"/>" method="post"  class="myFormulaire">            
           <div class="card-body">                 
		      <div class="form-group row">
		       <div class="col-sm-4 mb-2 mb-sm-0">
		        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
		        <input type="text" name="nom" class="form-control" required  value="<c:out value="${client_bien.nom}"/>">
		        </div>
		         <div class="col-sm-4 mb-2 mb-sm-0">
		         <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
		         <input type="text" name="prenom" class="form-control" required  value="<c:out value="${client_bien.prenom}"/>">
		        </div>
		         <div class="col-sm-4 mb-2 mb-sm-0">
		        <label for="platbus" class="">CONTACT<span class="text-danger ml-2">*</span></label>
		          <input type="text" name="tel" class="form-control" required  value="<c:out value="${client_bien.tel}"/>">
		        </div>      
		      </div>    
		      <div class="form-group row">
		         <div class="col-sm-4 mb-2 mb-sm-0">
		         <label for="platbus" class="">EMAIL</label>
			     <input type="text" name="email" class="form-control" required  value="<c:out value="${client_bien.email}"/>">
		       </div>       		
				<div class="col-sm-4 mb-4 mb-sm-0">
		         <label for="platbus" class="">NUMERO CNI</label>
				   <input type="text" name="num_cni" class="form-control" required  value="<c:out value="${client_bien.num_cni}"/>">
				</div>	
				<div class="col-sm-4 mb-4 mb-sm-0">
		         <label for="platbus" class="">NATIONALITE</label>
				  <sql:query var="listPays" dataSource="jdbc/gestions_imm">
					select * from pays 
				</sql:query>
		         <select name="nationalite" required class="form-control">
				   <option value="<c:out value="${client_bien.nationalite}"/>" selected>${client_bien.nationalite}</option>
				   <c:forEach items="${listPays.rows}" var="mapP" varStatus="boucle">
					 <option value="${mapP.pays}" class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">${mapP.pays}</option>
					</c:forEach>
				 </select>
				</div>		
         </div> 
         <div class="form-group row"> 
	      <div class="col-sm-4 mb-2 mb-sm-0">
	         <label for="platbus" class="">Document</label>
		      <img alt="${client_bien.document}" src="${pageContext.request.contextPath}/Disque//fichiers/logos/${client_bien.document!=null?client_bien.document:'erreur.jpg'}" style="font-family:bold; color:#000;">
			 <input type="file" id="file" name="document" onchange="addPhoto('ecole');" accept=".png,.jpg,.jpeg" >
	       </div> 
	    </div> 
    
        </div>
      <input type="hidden" name="id" value="<c:out value="${client_bien.id}"/>">
       <input type="hidden" name="matricule" value="<c:out value="${client_bien.matricule}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=clientB"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_cli==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
              <a  class="btn btn btn-info float-right" href="#clib" data-toggle="modal">Resident</a>
              </c:if>  
                </form>                                                     
            </div>
          </div>
      
      </div>
      
      
      <!-- ============ ============ ============ RESIDENT ============ ============ =========================== --> 
           <div class="modal fade  ${block}" id="clib" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:800px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Gestion Resident</h3>
                      </div>                   
                        <div class="modal-body">                           
                           <form action="<c:url value="/view_clientB"/>" id="formAdd"  method="post"  class="myFormulaire">
				               <div class="form-group row">  
				                  <div class="col-sm-6 mb-6 mb-sm-0">
						         <label for="platbus" class="">NOM </label>
							     <input type="text" name="nomC" class="form-control" value="">
						       </div>
						        <div class="col-sm-6 mb-6 mb-sm-0">
						         <label for="platbus" class="">PRENOM </label>
							     <input type="text" name="prenomC" class="form-control" value="">
						       </div>
				                </div>
				               <div class="form-group row">        						        
						        <div class="col-sm-6 mb-6 mb-sm-0">
						         <label for="platbus" class="">LIEN CLIENT </label>
							     <select name="liencli" required class="form-control">
									<option value="" selected></option>
									<option value="Ami">Ami</option>
									<option value="Epouse">Epouse</option>
									<option value="Enfant">Enfant</option>
								</select>
						       </div>        		
								<div class="col-sm-6 mb-6 mb-sm-0">
						         <label for="platbus" class="">NUMERO CNI</label>
								 <input type="text" name="num_cniC" class="form-control" value="">
								</div>		
						      </div>  						       
						       <input type="hidden" name="matriculeC" value="<c:out value="${client_bien.matricule}"/>">
						       <input type="hidden" name="cni" value="<c:out value="${client_bien.num_cni}"/>">
						       <div class="modal-footer">	
						         <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>					       
						        <button name="crud" value="resident" class="btn btn-success">Sauvegarder</button>
						      </div>
                            </form>
                         
                          <div class="table" style="margin-bottom:0;">
							 <div class="thead">
							  <div class="tr ht">
								<div class="td" style="width:20%;">NOM</div>
								<div class="td" style="width:20%;">PRENOM</div>
								<div class="td" style="width:20%;">LIEN CLIENT</div>
								<div class="td" style="width:20%;">NUMERO CNI</div>								
								<div class="td" style="width:10%;">ACTION</div>
							  </div>
						   </div>
						  </div>
						  
				      <sql:query var="listResi" dataSource="jdbc/gestions_imm">
						select * from resident_bien where code_reservation="${client_bien.matricule}" 
					 </sql:query>
						  						   
						    <div  id="myTableU" style="height:110px !important;">
							  <div class="table" style="margin-bottom:0;">
								<div class="tbody" id="tbody">
								 <c:forEach items="${listResi.rows}" var="mapResi" varStatus="boucle">
									<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
										<div class="td tdCenter" style="width:20%;">${mapResi.nom}</div>
										<div class="td tdCenter" style="width:20%;">${mapResi.prenom}</div>
										<div class="td tdCenter" style="width:20%;">${mapResi.lien_client}</div>
										<div class="td tdCenter" style="width:20%;">${mapResi.num_cni}</div>
										<div class="td tdCenter" style="width:10%;">
										 <button onclick="document.location.href='<c:url value="/view_clientB?id=${mapResi.id}&matri=${mapResi.code_reservation}&page=${page}&crud=idR" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
										</div>
									</div>
								</c:forEach>
							  </div>
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
</body>
</html>