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
                  <td>${ boucle.index + 1 }</td>
                  <td>${lister.profil}</td>
                  <td>${lister.commentaire}</td>                                                             
                  <td align="center">
                   <button  onclick="document.location.href='<c:url value="/profil?id=${lister.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
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

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
 
</body>
</html>