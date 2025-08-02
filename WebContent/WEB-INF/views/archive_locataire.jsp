<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from archive_locataire 
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
		<title>ARCHIVE LOCATAIRE</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">ARCHIVE LOCATAIRE</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">       
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>NOM </th>
                   <th>PRENOM</th>
                  <th>SEXE</th>     
                  <th>FONCTION</th>             
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
                  <td>${mapProp.prenom}</td>
                  <td>${mapProp.sexe}</td>
                  <td>${mapProp.fonction}</td>
                  <td>${mapProp.tel}</td>               
                  <td>${mapProp.date_entree}</td>    
                  <td>${mapProp.num_porte}</td>                                   
                  <td align="center"><a onclick="document.location.href='<c:url value="/view_locataire?id=${mapProp.id}&page=${page}&crud=idA" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a>                                                   
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

 <div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
		</div>
	</div>

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>

</body>
</html>