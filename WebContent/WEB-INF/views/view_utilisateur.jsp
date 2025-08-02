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
		<title>GESTION DES UTILISATEURS</title>
		
	
	
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
      <div class="card shadow mb-4" style="width:600px;margin-left:200px;">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER UTILISATEURS   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_utilisateurs"/>" method="post"  class="myFormulaire">
             
          <div class="card-body">
         <div class="form-group row">
	       <div class="col-sm-12 mb-6 mb-sm-0">
	        <label for="platbus" class="">NOM UTILISATEUR</label>
	        <sql:query var="listProp" dataSource="jdbc/gestions_imm">
				select * from gestionnaire 
			</sql:query>
	        <select name="nomUser" required class="form-control">	        
			 <option value="<c:out value="${utilisateur.matricule}"/>"selected ><c:out value="${utilisateur.nom}"/></option>
			 <c:forEach  items="${listProp.rows}" var="mapProp">
			  <option value="<c:out value="${mapProp.matricule}"/>"> <c:out value="${mapProp.nom} ${mapProp.prenom}"/></option>
			</c:forEach>							    
		   </select>
		  </div>
      </div>
      <div class="form-group row">
	      <div class="col-sm-6 mb-3 mb-sm-0">
	       <label for="seat" class="">LOGIN</label>
	       <input class="form-control" required type="text" name="email" value="<c:out value="${utilisateur.email}"/>" id="email">
		  </div>
		  <div class="col-sm-6 mb-3 mb-sm-0">	    
	        <label for="seat" class="">PASSWORD</label>
	       <input type="text" class="form-control" required name="password" value="<c:out value="${utilisateur.password}"/>" />
		  </div>       
      </div>
      <div class="form-group row">
       <div class="col-sm-6 mb-3 mb-sm-0">
          <sql:query var="listRole" dataSource="jdbc/gestions_imm">
	          select * from profil 
             </sql:query>
	       <label for="seat" class="">ROLE</label>
	       <select class="form-control" name="role" onchange="afficher(this);">
			 <option value="<c:out value="${utilisateur.role}"/>" selected><c:out value="${utilisateur.role}"/></option>
			 <c:forEach items="${listRole.rows}" var="mapRole">
				<option value="<c:out value="${mapRole.profil}"/>"><c:out value="${mapRole.profil}"/></option>
			</c:forEach>
		  </select>
		  </div>
		  <div class="col-sm-6 mb-3 mb-sm-0">	    
	        <label for="seat" class="">ACTIVE</label>
	       <input type="checkbox" class="form-control"  name="active" ${utilisateur.active==1?'checked':''} value="1"  />
		  </div>           
      </div>  
      
      <input type="hidden" name="id" value="<c:out value="${utilisateur.id}"/>">
      <input type="hidden" name="emails" value="<c:out value="${utilisateur.email}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=utilisateurs"/>"> Retour</a>
             &nbsp;&nbsp;
             <c:if test="${sessionScope.rolePr.modif_UT==1}">            
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>
                   &nbsp;&nbsp; &nbsp;&nbsp; &nbsp;&nbsp;
             <button name="crud" value=""  class="btn btn-info ${utilisateur.id!=null?utilisateur.role=='Admin_General'?'desactive':'active':'desactive'}" onclick="return ouvFermer('ajoutE');">Ajout Agence-Site</button>                         
              </c:if> 
            </div>
         
        
        </form>
      </div>    
         <div class="card-body" style="margin-top:-40px;">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-light">
                <tr>
                  <th>N°</th>
                  <th>LIBELLE AGENCE</th>  
                  <th>LIBELLE SITE</th>                                 
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
              <sql:query var="listUserEtab" dataSource="jdbc/gestions_imm">
				select ue.id,ue.site,a.agence from agence as a,site as e,user_site as ue where a.code=ue.code and ue.site=e.site and ue.email="${utilisateur.email}"
			</sql:query>
              <c:forEach items="${listUserEtab.rows}" var="mapEcole" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapEcole.agence}</td>  
                  <td>${mapEcole.site}</td>                                                                  
                  <td align="center">
                   <button class="${utilisateur.role=='admin_General'?'desactive':'active'}" onclick="document.location.href='<c:url value="/view_utilisateurs?idUE=${mapEcole.id}&id=${utilisateur.id}&crud=supp"/>'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
                  </td>
               
              </tr>
             </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
        
      </div>
           	     
    </div>
    
    <div class="suppPop" id="supp">
		<div class="mesSupp">
			<div class="header"><i class="fa fa-home fa-lg"></i><span> AJOUT AGENCE-SITES</span></div>
			<form action="<c:url value="/view_utilisateurs"/>" method="post" style="padding:30px 15px;">
				<input type="hidden" name="id" value="<c:out value="${utilisateur.id}"/>">
				<input type="hidden" name="email" value="<c:out value="${utilisateur.email}"/>">
				<input type="hidden" name="page" id="page" value="<c:out value="${page}"/>">
				 <div class="form-group row" style="margin-top:-10px;">
	              <div class="col-sm-3 mb-2 mb-sm-0">
					<label class="form-control">AGENCE </label>
				   </div>
				   <div class="col-sm-9 mb-8 mb-sm-0">
				    <sql:query var="listPropA" dataSource="jdbc/gestions_imm">
						select * from agence order by agence DESC
					 </sql:query>		
						<select class="form-control" name="agence" required>
							<option selected></option>
							<c:forEach  items="${listPropA.rows}" var="mapPropA">
								<option value="${mapPropA.code}">${mapPropA.agence}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="form-group row">
	              <div class="col-sm-3 mb-2 mb-sm-0">
					<label class="form-control">SITE </label>
				   </div>
				   <div class="col-sm-9 mb-8 mb-sm-0">
				    <sql:query var="listProp" dataSource="jdbc/gestions_imm">
						select * from site order by site DESC
					 </sql:query>		
						<select class="form-control" name="site" required>
							<option selected></option>
							<c:forEach  items="${listProp.rows}" var="mapProp">
								<option value="${mapProp.site}">${mapProp.site}</option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="lesBtn" style="text-align:right;margin-top:-10px;">
					<button class="btn-default" name="crud" value="ENREGISTRER">ENREGISTRER</button>
					<button class="btn-default" onclick="return ouvFermer('ferme')">cancel</button>
				</div>
			</form>
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