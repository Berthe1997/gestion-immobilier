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
		<title>GESTIONS NOTIFICATIONS</title>
<style>			
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
				
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">     
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3"> 
           <h1 class="h5 text-gray-800">GESTIONS NOTIFICATIONS DEMARCHEUR / PARTENAIRE</h1>
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>PORTE</th>
                  <th>PIECE</th>
                  <th>CHAMBRE</th>
                  <th>SALON</th>
                  <th>PRIX</th>                 
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
             <sql:query var="listProp" dataSource="jdbc/gestions_imm">
				select * from maison where site="${sessionScope.site.site}" and occupe ='0'
			 </sql:query>			 
			  <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle"> 
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.maison}</td>
                  <td>${mapProp.piece}</td>
                  <td>${mapProp.chambre}</td>
                  <td>${mapProp.salon}</td>
                  <td>${mapProp.prix}</td>                                                     
                  <td align="center">
                  <input type="hidden" value="${mapProp.id}"/>
                   <button class="btn-primary" onclick="ouvrirAM(this);">
					<i class="fas fa-comment-dots" style=" width: 35px;"></i> 
					</button>                 
                  </td>               
              </tr>
             </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    
    
     <!----------------AJOUTER MAISONS--------------------------------->
<div class="suppPop" id="supp">
		<div class="mesSuppN">
			<div class="headerN"><i class="fas fa-comment-dots"></i><span> Envoi de Notification</span></div>
			<div class="bodySupp">
				<p>Vous le vous envoyer une notification aux Demarcheurs?</p>
				<p>Pour confirmer clicker sur ok sinon cancel pour annuler.</p>
			</div>
			<form action="<c:url value="/view_maison"/>" method="post" class="btnSupp" style='margin-top:-10px;'>				
				<input type="hidden" name="page" id="page" value="<c:out value="${page}"/>">	
				<input type="hidden" name="types" id="typeSup" value="">
				<input type="hidden" name="idM" id="idM" value="">														
				<button class="btn btn-danger" name="crud" value="SMS">ok</button>
				<button class="btn btn-primary" onclick="return ouvFermer('ferme')">cancel</button>
			</form>
		</div>
	</div>
    
    <!-- /.container-fluid -->
  </div>
  <!-- /.container-fluid -->

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
 <script type="text/javascript">
 
 //=======================================SUPPRIMER=======================================================//
	function ouvrirAM(tag){
	var parent	=	$(tag).parent();
	var valeur = parent.find("input").val();
	document.getElementById("supp").style.display = "block";
	document.getElementById("typeSup").value = "supS";
	document.getElementById("idM").value = valeur;
 }
	</script> 

</body>
</html>