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
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">GESTION DES UTILISATEURS </h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
        <c:if test="${sessionScope.rolePr.ajout_UT==1}">
              <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan">
            AJOUTER UN UTILISATEUR 
          </button>
       </c:if>            
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>NOM UTILISATEUR</th>
                  <th>LOGIN</th>
                  <th>PASSWORD</th> 
                  <th>ROLE</th>
                  <th>ACTIVE</th>                  
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>             
              <sql:query var="listUser" dataSource="jdbc/gestions_imm">
					select * from users where site="${sessionScope.site.code}" and role<>"Admin_General"
					
			  </sql:query>
			  <c:forEach items="${listUser.rows}" var="mapUser" varStatus="boucle"> 
                <tr>
                  <td>1</td>
                  <td>${mapUser.nom} ${mapUser.prenoms}</td>
                  <td>${mapUser.email}</td>
                  <td>${mapUser.password}</td> 
                  <td>${mapUser.role}</td>
                  <td>${mapUser.active==1?'Oui':'Non'}</td>                                                  
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_utilisateurs?id=${mapUser.id_user}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
                   <c:if test="${sessionScope.rolePr.supp_UT==1}">
                   <button  onclick="document.location.href='<c:url value="/utilisateurs?id=${mapUser.id_user}&email=${mapUser.email}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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
<div class="modal-content">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel">AJOUTER UN UTILISATEUR</h5>
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/utilisateurs"/>" method="post"  class="myFormulaire">
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
       <div class="col-sm-12 mb-6 mb-sm-0">
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

<div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
		</div>
	</div>

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>


</body>
</html>