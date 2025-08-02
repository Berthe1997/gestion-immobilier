<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listT_G" dataSource="jdbc/gestions_imm">
	select * from gestionnaire where site="${sessionScope.site.site}"
</sql:query>

<sql:query var="listT_Loc" dataSource="jdbc/gestions_imm">
	select * from locataire where site="${sessionScope.site.site}"
</sql:query>

<sql:query var="listT_RL" dataSource="jdbc/gestions_imm">
	SELECT sum(montant_loyer) as montant_loyer FROM calendrier_paiement
   where year(date_paiement)=year(curdate())
   and month(date_paiement)=month(curdate())
  and site="${sessionScope.site.site}"
</sql:query>

<sql:query var="listT_TR" dataSource="jdbc/gestions_imm">
SELECT sum(montant_P) as montant_P,
sum(montant_loyer) as montant_loyer,
ROUND((SUM(montant_P) / sum(montant_loyer)) * 100, 2) as taux
FROM gestions_immobilier.calendrier_paiement
where year(date_paiement)=year(curdate())
and month(date_paiement)=month(curdate())
and site="${sessionScope.site.site}"
</sql:query>

<sql:query var="listT_MaiO" dataSource="jdbc/gestions_imm">
	select * from maison where site="${sessionScope.site.site}" and occupe='1'
</sql:query>
<sql:query var="listT_MaiV" dataSource="jdbc/gestions_imm">
	select * from maison where site="${sessionScope.site.site}" and occupe='0'
</sql:query>

 

<!DOCTYPE html>
<html lang="zxx" class="no-js">

<head>

   <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  <link href="assetss/images/logo.jpg" rel="icon">
		<!-- Log on to codeastro.com for more projects -->
		<!-- Site Title -->
		<title>ACCUEIL</title>
		

    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
  <!-- navbar --><!-- Log on to codeastro.com for more projects -->
               
   <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->    
          
        <!-- Begin Page Content -->
        <div class="container-fluid">

          <!-- Page Heading -->
          <h1 class="h3 mb-4 text-gray-800">Dashboard</h1>
          <!-- Content Row -->
          <div class="row">

            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                  <c:forEach items="${listT_Loc.rows}" var="lister" varStatus="boucle">
						<c:set var="nbTL" value="${boucle.index + 1}"/>
				  </c:forEach>
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1" style="font-size:20px;"> Locataire</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800" style="font-size:50px;">${nbTL}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-restroom fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            <!-- Earnings (Monthly) Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                  <c:forEach items="${listT_RL.rows}" var="lister" varStatus="boucle">
					<c:set var="nbTM" value="${boucle.index + 1}"/>
				  </c:forEach>
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1" style="font-size:20px;"> Revenu locatif</div>
                       <c:forEach items="${listT_RL.rows}" var="lister" varStatus="boucle">
                      <div class="h5 mb-0 font-weight-bold text-gray-800" style="font-size:50px;">${lister.montant_loyer}</div>
                       </c:forEach>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-dollar-sign fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <!-- Earnings (Monthly) Card Example -->
         <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">                 
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-danger text-uppercase mb-1" style="font-size:20px;"> Taux de Recouvrement</div>
                       <c:forEach items="${listT_TR.rows}" var="lister" varStatus="boucle">
                      <div class="h5 mb-0 font-weight-bold text-gray-800" style="font-size:50px;">${lister.taux}</div>
                       </c:forEach>
                    </div>
                    <div class="col-auto">
                      <i class="fa fa-percent fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>

          </div>


          <div class="row">

            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-info shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                  <c:forEach items="${listT_MaiO.rows}" var="lister" varStatus="boucle">
					<c:set var="nbTMO" value="${boucle.index + 1}"/>
				 </c:forEach>
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-info text-uppercase mb-1" style="font-size:20px;">Maison Occupé </div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800" style="font-size:50px;">${nbTMO}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-university fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div><!-- Log on to codeastro.com for more projects -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-danger shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                  <c:forEach items="${listT_MaiV.rows}" var="lister" varStatus="boucle">
					<c:set var="nbTMD" value="${boucle.index + 1}"/>
				 </c:forEach>
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-danger text-uppercase mb-1"  style="font-size:20px;">Maison Disponible</div>
                      <div class="h5 mb-0 mr-3 font-weight-bold text-gray-800" style="font-size:50px;">${nbTMD}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-university fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            

            <!-- Pending Requests Card Example -->
            <div class="col-xl-4 col-md-6 mb-4">
              <div class="card border-left-success shadow h-100 py-2">
                <div class="card-body">
                  <div class="row no-gutters align-items-center">
                  <c:forEach items="${listT_G.rows}" var="lister" varStatus="boucle">
					<c:set var="nbG" value="${boucle.index + 1}"/>
				  </c:forEach>
                    <div class="col mr-2">
                      <div class="text-xs font-weight-bold text-success text-uppercase mb-1" style="font-size:20px;"> Total Gestionnaire</div>
                      <div class="h5 mb-0 font-weight-bold text-gray-800" style="font-size:50px;">${nbG}</div>
                    </div>
                    <div class="col-auto">
                      <i class="fas fa-user-tie fa-2x text-gray-300"></i>
                    </div>
                  </div>
                </div>
              </div>
            </div>
            



          </div>

        </div>
      
        <!-- /.container-fluid -->
         </div>

      <!-- End of Main Content -->

      <!-- Footer -->
     <c:import url="/include/base_footer.jsp"></c:import>
      <!-- End of Footer -->

  <!-- End of Content Wrapper -->

 
  <!-- End of Page Wrapper -->
  <!-- Log on to codeastro.com for more projects -->
  <!-- Scroll to Top Button-->
  <a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
  </a>
  <!-- js -->
   <c:import url="/include/base_js.jsp"></c:import>
   

</body>
</html>