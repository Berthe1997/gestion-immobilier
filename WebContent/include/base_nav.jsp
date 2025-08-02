<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<style>		
		

 #nav {
      
       height: 30px;
      margin: 10px auto;
      background-color: red;
      text-align: center;
      color: white;
      font-size: 18px;
      line-height: 30px;
      transition: background-color 0.5s ease;
    }
    
  
    /* Lien principal */
    a.menu-principal {
      padding: 10px 15px;
      background-color: #ffffff;
      color: white;
      text-decoration: none;
      display: inline-block;
      position: relative;
    }
    
     /* Lien principal */
    a.menu-principale {
      padding: 10px 15px;
      background-color: #ffffff;
      color: white;
      text-decoration: none;
      display: inline-block;
      position: relative;
    }

    /* Sous-menu positionné par rapport au lien principal */
    .submenus {
      display: none;
      position: absolute;
      background-color: #2980b9;
      margin-top: -110px;
    }
    
    .submenu {
      display: none;
      position: absolute;
      background-color: #2980b9;
      margin-top: 0;
    }

    .submenus a {
      display: block;
      padding: 10px 15px;
      color: white;
      text-decoration: none;
    }
    
    .submenu a {
      display: block;
      padding: 10px 15px;
      color: white;
      text-decoration: none;
    }

    .submenus a:hover {
      background-color: #1abc9c;
    }
    
    .submenu a:hover {
      background-color: #1abc9c;
    }

    /* Affichage de la sous-liste quand on survole le lien principal */
    a.menu-principal:hover + .submenu,
    .submenu:hover {
      display: block;
    }
  
    
    a.menu-principale:hover + .submenus,
    .submenus:hover {
      display: block;
    } 
    
    
  /* Sous-menu positionné par rapport au lien principal */   
    
    	
</style>
 

  <!-- navbar --><!-- Log on to codeastro.com for more projects -->
<!-- Page Wrapper -->
  <div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav  sidebar sidebar-dark accordion" id="accordionSidebar" style="background: #800040;">

      <!-- Sidebar - Brand -->
      <a class="sidebar-brand d-flex align-items-center justify-content-center" href="views/home.jsp">
        <div class="sidebar-brand-icon ">
          <i class="fas fa-home"></i>
        </div>
        <div class="sidebar-brand-text mx-3">DIMCO</div>
      </a>

      <!-- Divider -->
      <hr class="sidebar-divider my-0">
      
       <!-- Sidebar Toggler (Sidebar) -->
      <div class="text-center d-none d-md-inline">
        
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
      </div>

 <!-- Divider -->
      <hr class="sidebar-divider my-0">

      <!-- Nav Item - Dashboard -->
       <li class="nav-item ${sessionScope.rolePr.accueil==1?'active':'none'}">
            <a class="nav-link collapsed" href="<c:url value="/menus?menu=home"/>">
              <i class="fas fa-fw fa-tachometer-alt"></i>
              <span>Dashboard </span>
            </a>           
       </li>
       
       <!-- 
       <li class="nav-item">
            <a class="nav-link collapsed" href="<c:url value="/menus?menu=compte_P"/>">
              <i class="fas fa-fw fa-tachometer-alt"></i>
              <span>TEXT </span>
            </a>           
       </li>
       fas fa-handshake
       -->  
   
       
                        
       <li class="nav-item ${sessionScope.rolePr.partenaire==1?'active':'none'}">
            <a class="nav-link collapsed" href="<c:url value="/menus?menu=partenaire"/>">
              <i class=" fas fa-handshake"></i>
              <span>Partenaire</span>
            </a>           
       </li>
       
       <li class="nav-item ${sessionScope.rolePr.code_site==1?'active':'none'}">
            <a class="nav-link collapsed" href="<c:url value="/menus?menu=site"/>">
              <i class="fas fa-university"></i>
             <span>Gestion Des Sites</span>
            </a>           
       </li>
       
        <li class="nav-item ${sessionScope.rolePr.porte==1?'active':'none'}" >
            <a class="nav-link collapsed"href="<c:url value="/menus?menu=maison"/>">
             <i class="fas fa-home"></i>
             <span>Gestion Des Portes</span>
            </a>           
       </li>
       
        <!--              
       <li class="nav-item ${sessionScope.rolePr.service_tech==1?'active':'none'}">
            <a class="nav-link collapsed" href="<c:url value="/menus?menu=service_technique"/>">
              <i class="fas fa-hiking"></i>
              <span>Service Technique </span>
            </a>           
       </li>
       -->
       
        <li class="nav-item ${sessionScope.rolePr.rapport==1?'active':'none'}" >
            <a class="nav-link collapsed" href="<c:url value="/menus?menu=compte_G"/>">
              <i class="fa fa fa-file"></i>
              <span>Rapport</span>
            </a>           
       </li>
                      
        <li class="nav-item ${sessionScope.rolePr.bien_meuble==1?'active':'none'}">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse1" aria-expanded="true" aria-controls="collapseTwo">
              <i class="fas fa-home"></i>
              <span>Gestion Bien Meublée</span>
            </a>
            <div id="collapse1" class="collapse" aria-labelledby="heading2" data-parent="#accordionSidebar">
              <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Sous Menu Bien Meublée</h6>
                <c:if test="${sessionScope.rolePr.bien==1}">
                 <a href="<c:url value="/menus?menu=bienM"/>" id="collapse-item" class="collapse-item ${sessionScope.rolePr.bien==1?'active':'none'}">Bien Meublée</a>  
                  </c:if> 
                 <c:if test="${sessionScope.rolePr.client==1}">             
                 <a href="<c:url value="/menus?menu=clientB"/>" id="collapse-item" class="collapse-item ${sessionScope.rolePr.client==1?'active':'none'}">Client</a>   
                 </c:if> 
                 <c:if test="${sessionScope.rolePr.archive_cli==1}">                                         
                 <a href="<c:url value="/menus?menu=archive_CR"/>" id="collapse-item" class="collapse-item ${sessionScope.rolePr.archive_cli==1?'active':'none'}">Archive Client</a>
                </c:if> 
                 <!--
                 <a href="<c:url value="/menus?menu=resident"/>" id="collapse-item" class="collapse-item">Résident</a>    
                 <a href="<c:url value="/menus?menu=facturation"/>" id="collapse-item" class="collapse-item">Facturation</a> 
                 <a href="<c:url value="/menus?menu=historique"/>" id="collapse-item" class="collapse-item">Historique Statut</a>
                 <a href="<c:url value="/menus?menu=reservation"/>" id="collapse-item" class="collapse-item">Réservation </a> 
                  --> 
              </div>
            </div>
          </li>
         
      <!--     
       <li class="nav-item ${sessionScope.rolePr.notification==1?'active':'none'}">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse2" aria-expanded="true" aria-controls="collapseTwo">
              <i class="fas fa-comment-dots"></i>
              <span>Gestion Notification</span>
            </a>
            <div id="collapse2" class="collapse" aria-labelledby="heading2" data-parent="#accordionSidebar">
              <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Sous Menu Notifications</h6>
               <!--  <a href="<c:url value="/menus?menu=rappel_loyer"/>" id="collapse-item" class="collapse-item">Rappel Loyer </a>   -->
            <!--     <c:if test="${sessionScope.rolePr.notification==1}">
                 <a href="<c:url value="/menus?menu=sms_demarcheur"/>" id="collapse-item" class="collapse-item ${sessionScope.rolePr.notification==1?'active':'none'}">sms Demarcheur/Partenaire</a>
                  </c:if>
                 <!-- 
                 <a href="<c:url value="/menus?menu="/>" id="collapse-item" class="collapse-item">Gestion Bonus</a>
                 <a href="<c:url value="/menus?menu="/>" id="collapse-item" class="collapse-item">Gestion Heure Suppl</a>                 
                   
              </div>
            </div>
          </li>
          -->
          
           <li class="nav-item ${sessionScope.rolePr.ressourceH==1?'active':'none'}">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse5" aria-expanded="true" aria-controls="collapseTwo">
              <i class="fas fa-industry"></i>
              <span>Gestion De Terrain</span>
            </a>
            <div id="collapse5" class="collapse" aria-labelledby="heading2" data-parent="#accordionSidebar">
              <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Sous Menu Gestion Terrain</h6>
                <c:if test="${sessionScope.rolePr.gestionnaire==1}">
                 <a href="<c:url value="/menus?menu=terrain"/>" class="collapse-item ${sessionScope.rolePr.gestionnaire==1?'active':'none'}">Terrain</a>                
                </c:if>
                <c:if test="${sessionScope.rolePr.locataire==1}">
                   <a href="<c:url value="/menus?menu=client_A"/>" class="collapse-item ${sessionScope.rolePr.locataire==1?'active':'none'}">Client Acheteur</a>
                </c:if>
                <c:if test="${sessionScope.rolePr.locataire==1}">
                   <a href="<c:url value="/menus?menu=dossier_cl"/>" class="collapse-item ${sessionScope.rolePr.locataire==1?'active':'none'}">Dossier Client</a>
                </c:if>
                <c:if test="${sessionScope.rolePr.locataire==1}">
                   <a href="<c:url value="/menus?menu=offre_terrain"/>" class="collapse-item ${sessionScope.rolePr.locataire==1?'active':'none'}">Offre Terrain</a>
                </c:if>
                <!--
                <c:if test="${sessionScope.rolePr.locataire==1}">
                   <a href="<c:url value="/menus?menu=fichier_T"/>" class="collapse-item ${sessionScope.rolePr.locataire==1?'active':'none'}">Fichier Excel / Pdf</a>
                </c:if>
                --> 
             </div>
            </div>
          </li>
                                                                                     
             <!-- Nav Item - Pages Collapse Menu -->                  
          <li class="nav-item ${sessionScope.rolePr.ressourceH==1?'active':'none'}">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse3" aria-expanded="true" aria-controls="collapseTwo">
              <i class="fas fa-people-carry"></i>
              <span>Ressource Humaine</span>
            </a>
            <div id="collapse3" class="collapse" aria-labelledby="heading2" data-parent="#accordionSidebar">
              <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Sous Menu RH</h6>
                <c:if test="${sessionScope.rolePr.gestionnaire==1}">
                 <a href="<c:url value="/menus?menu=gestionnaire"/>" class="collapse-item ${sessionScope.rolePr.gestionnaire==1?'active':'none'}">Personnel</a>                
                </c:if>
                <c:if test="${sessionScope.rolePr.locataire==1}">
                   <a href="<c:url value="/menus?menu=locataire"/>" class="collapse-item ${sessionScope.rolePr.locataire==1?'active':'none'}">Locataire Physique</a>
                </c:if>
                <!-- Sous-liste juste après -->
				                         
                <c:if test="${sessionScope.rolePr.proprietaire==1}">
                  <a href="<c:url value="/menus?menu=proprietaire"/>" class="collapse-item ${sessionScope.rolePr.proprietaire==1?'active':'none'}">Proprietaire Physique</a>
                </c:if>  
                 <!-- Sous-liste juste après -->
				
                 <c:if test="${sessionScope.rolePr.archive==1}">                               
                 <a href="<c:url value="/menus?menu=archive_locataire"/>" class="collapse-item ${sessionScope.rolePr.archive==1?'active':'none'}">Archive Locataire</a>
                 </c:if> 
                 <c:if test="${sessionScope.rolePr.demarcheur==1}">  
                  <a href="<c:url value="/menus?menu=demarcheur"/>" class="collapse-item ${sessionScope.rolePr.demarcheur==1?'active':'none'}">Demarcheur</a>  
                  </c:if>
                  <c:if test="${sessionScope.rolePr.reception_cli==1}"> 
                  <a href="<c:url value="/menus?menu=reception_client"/>" class="collapse-item ${sessionScope.rolePr.reception_cli==1?'active':'none'}">Reception Client</a>    
                  </c:if>            
              </div>
            </div>
          </li>
          
          <li class="nav-item ${sessionScope.rolePr.comptabilite==1?'active':'none'}">
            <a class="nav-link collapsed" href="<c:url value="/menus?menu=comptabilite"/>">
              <i class="fas fa-solid fa-coins"></i>
              <span>Comptabilite</span>
            </a>
            <!-- 
            <div id="collapse3" class="collapse" aria-labelledby="heading3" data-parent="#accordionSidebar">
              <div class="bg-white py-2 collapse-inner rounded">
                <h6 class="collapse-header">Sous Menu Comptabilite</h6>
                 <c:if test="${sessionScope.rolePr.paiementL==1}">
                 <a class="collapse-item  ${sessionScope.rolePr.paiementL==1?'active':'none'}" href="<c:url value="/menus?menu=paiement_loyer"/>">Paiement Loyer</a>                 
                 </c:if>
                 <c:if test="${sessionScope.rolePr.chargeE==1}">
                  <a class="collapse-item  ${sessionScope.rolePr.chargeE==1?'active':'none'}" href="<c:url value="/menus?menu=charge_entretien"/>">Charge-Entretien</a>            
                 </c:if>               
                 <a class="collapse-item" href="<c:url value="/menus?menu=visite_maison"/>">Visite Maison</a> 
                  
                  <a class="collapse-item" href="<c:url value="/menus?menu=comptabilite"/>">COMPTABILITE</a> 
                  <!-- <a class="collapse-item" href="<c:url value="/menus?menu=paiement_caution"/>">Paiement Caution-Avance</a> 
               
              </div>             
            </div>
             -->
          </li>
          
          <li class="nav-item ${sessionScope.rolePr.parametre==1?'active':'none'}">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapse4" aria-expanded="true">
              <i class="fa fa-cog"></i>
              <span>Parametre</span>
            </a>
            <div id="collapse4" class="collapse" aria-labelledby="heading4" data-parent="#accordionSidebar">
              <div class="bg-white py-2 collapse-inner rounded">
               <h6 class="collapse-header">Sous Menu Parametre</h6>
                <a href="<c:url value="/menus?menu=entreprise"/>" class="collapse-item ${sessionScope.rolePr.pourcentage==1?'active':'none'}">Gestion Entreprise</a> 
                
                 <c:if test="${sessionScope.rolePr.agence==1}"> 
                 <a href="<c:url value="/menus?menu=agence"/>" class="collapse-item ${sessionScope.rolePr.pourcentage==1?'active':'none'}">Gestion Agence</a> 
                 </c:if> 
                <c:if test="${sessionScope.rolePr.utilisateur==1}">
                  <a href="<c:url value="/menus?menu=utilisateurs"/>" id="collapse-item" class="collapse-item ${sessionScope.rolePr.utilisateur==1?'active':'none'}">Utilisateur</a>
                </c:if>
                <c:if test="${sessionScope.rolePr.profile==1}">
                 <a class="collapse-item ${sessionScope.rolePr.profile==1?'active':'none'}" href="<c:url value="/menus?menu=profils"/>">Profile</a>
                </c:if>
                <c:if test="${sessionScope.rolePr.role==1}">
                 <a class="collapse-item ${sessionScope.rolePr.role==1?'active':'none'}" href="<c:url value="/menus?menu=role"/>">Role Utilisateur</a>
                </c:if>
                <c:if test="${sessionScope.rolePr.pays==1}">
                  <a class="collapse-item ${sessionScope.rolePr.pays==1?'active':'none'}" href="<c:url value="/menus?menu=pays"/>">Pays</a>
                </c:if>
                <c:if test="${sessionScope.rolePr.ville==1}">
                 <a class="collapse-item ${sessionScope.rolePr.ville==1?'active':'none'}" href="<c:url value="/menus?menu=ville"/>">Ville</a>
                </c:if>
                <c:if test="${sessionScope.rolePr.commune==1}">
                  <a class="collapse-item ${sessionScope.rolePr.commune==1?'active':'none'}" href="<c:url value="/menus?menu=commune"/>">Commune</a>
                </c:if> 
                <c:if test="${sessionScope.rolePr.quartier==1}">
                  <a class="collapse-item ${sessionScope.rolePr.quartier==1?'active':'none'}" href="<c:url value="/menus?menu=quartier"/>">Quartier</a>
                </c:if> 
                <c:if test="${sessionScope.rolePr.pourcentage==1}">
                 <a class="collapse-item ${sessionScope.rolePr.pourcentage==1?'active':'none'}" href="<c:url value="/menus?menu=taux"/>">Gestion Pourcentage</a>
                </c:if>              
                <c:if test="${sessionScope.rolePr.service==1}">
                <a href="<c:url value="/menus?menu=service"/>" class="collapse-item ${sessionScope.rolePr.service==1?'active':'none'}">Service</a> 
                </c:if>
                 <c:if test="${sessionScope.rolePr.g_caution==1}">   
                <a href="<c:url value="/menus?menu=gestion_caution"/>" class="collapse-item ${sessionScope.rolePr.g_caution==1?'active':'none'}">Gestion Caution</a>              
                 </c:if>                                          
               </div>
            </div>
          </li>                
                
                            
               
             
             
             
          

        

      <!-- Divider -->
      <hr class="sidebar-divider d-none d-md-block">

     

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column" >

      <!-- Main Content -->
      <div id="content" style="background-image: url('assetss/images/dimco.jpg');"> 
      
       <!-- =========================ACCES SITE================================================= -->
      <sql:query var="listProp" dataSource="jdbc/gestions_imm">
		select * from site  order by site DESC
	 </sql:query>
	 <sql:query var="listSite" dataSource="jdbc/gestions_imm">
	   select * from user_site  where email="${sessionScope.users.email}" order by code DESC
	 </sql:query>
	  <!-- =========================ACCES AGENCE================================================= -->
	   <sql:query var="listAg" dataSource="jdbc/gestions_imm">
		select * from agence order by agence DESC
	  </sql:query>
	  <sql:query var="listAge" dataSource="jdbc/gestions_imm">
	  select a.code,agence,us.code from agence as a,user_site as us where us.code=a.code and us.email="${sessionScope.users.email}" 
	  group by agence
	 </sql:query>
	 
	       
      <form action="<c:url value="/Choose"/>" method="post" id="formSearch" name="formSearch">
			<div class="row">
				<div class="col-sm-2 col-sm"><p style="background: #800040;">AGENCES</p></div>
				<div class="col-sm-4 col-sm"><select name="codeA" onchange="envoiForm();">
					<option value="${sessionScope.agence.code}" selected>${sessionScope.agence.agence}</option>
					<c:forEach items="${sessionScope.users.role=='Admin_General'?listAg.rows:listAge.rows}" var="mapAg">
						<option value="${mapAg.code}">${mapAg.agence}</option>
					</c:forEach>
				</select></div>
				<div class="col-sm-1 col-sm"><p style="background: #800040;">SITES</p></div>
				<div class="col-sm-5 col-sm">
				 <select name="sites" onchange="envoiForm();">
                   <option value="${sessionScope.site.site}" selected>${sessionScope.site.site}</option>                   
					<c:forEach  items="${sessionScope.users.role=='Admin_General'?listProp.rows:listSite.rows}" var="mapProp">
						<option value="${mapProp.site}">${mapProp.site}</option>
					</c:forEach>
				</select>
				</div>
			</div>
			<input type="hidden" name="page" value="<c:out value="${page}"/>"/>
		</form>
		
		  <!-- --------------------------------------------MENU DE SESSION DES SITES AVEC UN FILTRE------------------------------------------  -->
          

        <!-- Topbar -->
        <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow" style="height:35px;">

          <!-- Sidebar Toggle (Topbar) -->
          <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
            <i class="fa fa-bars"></i>
          </button>

      
        <marquee behavior="alternate" id="nav">Application de Gestion Immobilière (AGIL)</marquee>
     
  
  
  <!-- --------------------------------------------FIN MENU DE SESSION DES SITES AVEC UN FILTRE------------------------------------------  -->          
          
          <!-- Topbar Navbar -->
          <ul class="navbar-nav ml-auto">

            <!-- Nav Item - Search Dropdown (Visible Only XS) -->
            <li class="nav-item dropdown no-arrow d-sm-none">
              <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <i class="fas fa-search fa-fw"></i>
              </a>
              <p id="first">ANNEE-SCOLAIRE</p>
              <!-- Dropdown - Messages -->
              <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in" aria-labelledby="searchDropdown">
                <form class="form-inline mr-auto w-100 navbar-search">
                  <div class="input-group">
                    <input type="text" class="form-control bg-light border-0 small" placeholder="Search for..." aria-label="Search" aria-describedby="basic-addon2">
                    <div class="input-group-append">
                      <button class="btn btn-primary" type="button">
                        <i class="fas fa-search fa-sm"></i>
                      </button>
                    </div>
                  </div>
                  dddS
                </form>
              </div>
            </li>

            
            <div class="topbar-divider d-none d-sm-block"></div>

            <!-- Nav Item - User Information -->
            <li class="nav-item dropdown no-arrow">
              <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="mr-2 d-none d-lg-inline text-gray-600 small">${sessionScope.users.nom} </span>
                <img class="img-profile rounded-circle" src="<c:url value="/assets/backend/img/default.png" />">
              </a>
              <!-- Dropdown - User Information -->
              <div class="dropdown-menu dropdown-menu-right shadow animated--grow-in" aria-labelledby="userDropdown">
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#exampleModal">
                  <i class="fas fa-info-circle fa-sm fa-fw mr-2 text-gray-400"></i>
                  A PROPOS
                </a>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="#" data-toggle="modal" data-target="#logoutModal">
                  <i class="fas fa-sign-out-alt fa-sm fa-fw mr-2 text-gray-400"></i>
                  DECONNEXION
                </a>
              </div>
            </li>

          </ul>

        </nav>
        <!-- End of Topbar -->
        
         <script>
         function startFlash() {
	    const flashElement = document.getElementById('nav');  
	    let flashing = true;

	    setInterval(() => {
	      if (flashing) {
	        flashElement.style.backgroundColor = 'blue';
	      } else {
	        flashElement.style.backgroundColor = 'red';
	      }
	      flashing = !flashing;  // Alterne l'état
	    }, 2000);  // Change toutes les 500 ms
	  }
	//Démarre le flash après 1 seconde
	  setTimeout(startFlash, 4000);
	  </script>
        