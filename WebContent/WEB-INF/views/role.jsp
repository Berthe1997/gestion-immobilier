<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<sql:query var="listRole" dataSource="jdbc/gestions_imm">
	SELECT * FROM role where site="${sessionScope.agence.code}"
</sql:query>
<sql:query var="listProfil" dataSource="jdbc/gestions_imm">
	SELECT * FROM profil
</sql:query>
<html>
<head>
    <meta name="viewport" charset="UTF-8" content="width=device-width, initial-scale=1.0"/>
    <title>GESTIONS DES ROLES</title>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assetss/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assetss/css/headers.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/assetss/css/styliste.css" />"/>
    <link rel="stylesheet" href="assetss/fontes/font-awesome.min.css">
    <style>
        .details {
            margin: 15px;
            padding: 5px;
            border: 3px solid grey;
        }

        .messageAlertN {
            position: absolute;
            display: none;
            background-color: rgb(0, 0, 0);
            background-color: rgba(0, 0, 0, 0.4);
            top: 0;
            z-index: 80000;
            width: 100%;
            height: 100%;
        }

        .msgS {
            width: 400px;
            margin: 15% auto;
            border-radius: 5px 5px;
            text-align: center;
        }

        #headSMS {
            position: relative;
            background: #D64AF4;
            margin-bottom: 10px;
            color: #FFF;
            font-size: 17px;
            font-weight: bold;

        }

        #headSMS span {
            position: absolute;
            background: #F41111;
            border-radius: 10px 10px;
            right: 5px;
            color: #000;
            padding: 0 5px;
        }

        #headSMS span:hover {
            background: #F76666;
            color: #FFF;
            cursor: pointer;
        }

        .msgS p {
            font-size: 18px;
            padding-bottom: 10px;
        }

        #eco {
            background: #59c1bc;
            text-align: center;
            font-size: 20px;
            font-weight: bold;
            color: #FFF;
            margin: 0;
        }

        #ibo {
            font-size: 40px;
        }

        .gdEntete h3 {
            font-weight: bold;
            text-align: center;
            border-bottom: 3px solid #c3d007;
        }

        .gdEntete span {
            position: absolute;
            color: #009ba8;
            left: 40px;
        }

        .gdEntete span:hover {
            color: #00d4dc;
            cursor: pointer;
        }

        .mesTableaux {
            display: flex;
            gap: 20px;
            padding: 0 8px;
            height: 300px;
        }

        .tablet {
            width: 50%;
        }

        #secondTable .bulHead {
            background: #b880e1;
            text-align: center;
            font-size: 17px;
            font-weight: bold;
            color: #FFF;
            margin: 0;
            padding: 6px;
        }

        .inputCherche {
            position: relative;
        }

        .inputCherche input {
            height: 35px;
            font-size: 14px;
            font-size: max(14px, 1em);
            font-weight: bolder;
            font-family: inherit;
            margin: 0;
            padding: 0.20em 0.5em;
            background-color: lightgray;
            border: 2px solid #8b8a8b;
            border-top: 0;
            border-radius: 0;
            box-shadow: 5px 5px #8796af;
        }

        .inputCherche input:focus ~ span:before {
            transform: scaleX(1);
            transform-origin: left;
            transition: transform 0.5s ease-in-out;
        }

        .inputCherche span.var {
            position: absolute;
            bottom: 0;
            right: 0;
            display: block;
            background: #555;
            width: 100%;
            height: 2px;
        }

        .inputCherche span.var:before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: #00b0ff;
            transform: scaleX(0);
            transform-origin: right;
            transition: transform 0.5s ease-in-out;
        }

        #myTableEtd, myTableMat {
            overflow-y: auto;
        }

        .ht .td {
            position: sticky;
            top: 0;
            background: darkblue !important;
        }

        .pair {
            background-color: #ccecee;
        }

        .impair {
            background-color: #e1edee;
        }

        .pair:hover .td, .impair:hover .td {
            background-color: #d39a56;
            border-bottom: 2px solid greenyellow;
        }

        .pair.active .td, .impair.active .td {
            background-color: #d39a56;
            border-bottom: 2px solid greenyellow;
        }

        .customTrim {
            display: flex;
            background: #d2d8f1;
        }

        .customTrim .trims {
            display: flex;
            width: 50%;
        }

        .customTrim .trims label {
            width: 40%;
            font-size: 18px;
            font-weight: bold;
            color: #0f0f38;
            padding-left: 5px;
        }

        .customTrim .trims div {
            width: 60%;
        }

        .customTrim .trims div select {
            width: 100%;
            height: 30px;
        }

        .customTrim .impression {
            width: 50%;
        }

        .coll4 input, .coll2 input {
            background: lightgray;
            border: none;
        }

        .coll4 input:focus {
            outline: none;
        }

        .coll2 input:focus {
            background: lightcyan;
        }

        .infoEtd {
            background: #fbf9fb;
            border: 1px solid #e7b2c7;
            border-radius: 5px 5px;
            width: 100%;
        }

        .Detail {
            display: table;
            width: 100%;
            margin: 0;
            border-collapse: separate;
            border-spacing: 8px;
        }

        .Detail td {
            background: #d2deef;
            color: #7030a0;
            font-size: 15px;
            font-weight: bold;
            padding-left: 5px;
        }

        .Detail td span {
            color: #000;
        }
        li {color:black;
        font-size:17px;}
    </style>
</head>
<body style="background-image: url('assetss/images/synchroniser.jpg');">
<div class="container-fluid" style="">
    <div class="details" style="padding:0;height:650px;">
        <div id="eco">${sessionScope.site.site}</div>
        <div class="gdEntete" style="margin:10px;">
            <h3 style="margin-top:0;">
                <span onclick="liens('home');">
                    <i class="fa fa-arrow-circle-o-left" style="font-size:30px;"></i>
                </span>
                <i class="fa fa-book" id="ibo"></i> GESTION DES ACCES UTILISATEURS
            </h3>
        </div>
        
        <div class="row">
				<div class="col-sm-12  col-sm-a">
					<div class="row rw">
						<div class="col-sm-6">
							<form action="<c:url value="/roles"/>" method="post" name="formPro" id="formSearch">
								<input type="hidden" name="page" id="page" value="<c:out value="${page}"/>">
								<input type="hidden" name="crud" value="get">
								<div class="control-group">
									<label class="control-label col-sm-3">Profil :</label>
									<div class="controls col-sm-9">
										<select class="input-sm" name="id" onchange="envoieForm();" required>
											<option value="<c:out value="${role.id}"/>">${role.profil}</option>
											<c:forEach items="${listRole.rows}" var="mapRole">
												<option value="${mapRole.id}">${mapRole.profil}</option>
											</c:forEach>
										</select>
									</div>
								</div>
							</form>
						</div>
						<div class="col-sm-4"><span class="badge bd" onclick="ouvFermer('ajoutE');">Creer un Role</span></div>
						<div class="col-sm-2"></div>
					</div>
					<div class="row">
						<div class="col-sm-12">
							<form action="<c:url value="/roles"/>" method="post" id="formAcces">
								<input type="hidden" name="page" id="page" value="<c:out value="${page}"/>">
								<input type="hidden" name="id" value="<c:out value="${role.id}"/>">
								<div class="row" id="lesAcces">
									<div class="col-sm-3">
										<div class="lesUl">
											<ul class="first">
												<li>-<input type="checkbox" name="accueil" ${role.accueil==1?'checked':''} value="1"> ACCUEIL</li>
												<li>-<input type="checkbox" name="agence" ${role.agence==1?'checked':''} value="1"> GESTION AGENCE
												     <ul class="second">
												                <li>-<input type="checkbox" name="ajout_AG" ${role.ajout_AG==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_AG" ${role.modif_AG==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_AG" ${role.supp_AG==1?'checked':''} value="1"> SUPPRIMER</li>														
													</ul>
												</li>
												<li>-<input type="checkbox" name="partenaire" ${role.partenaire==1?'checked':''} value="1"> GESTION PARTENAIRE
												     <ul class="second">
												                <li>-<input type="checkbox" name="" ${role.partenaire==1?'checked':''}  value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="" ${role.partenaire==1?'checked':''}  value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="" ${role.partenaire==1?'checked':''} value="1"> SUPPRIMER</li>														
													</ul>
												</li>
												<li>-<input type="checkbox" name="service_tech" ${role.service_tech==1?'checked':''} value="1">SERVICE TECHNIQUE
												     <ul class="second">
												                <li>-<input type="checkbox" name=""  value="1">AJOUTER </li>
																<li>-<input type="checkbox" name=""  value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name=""  value="1"> SUPPRIMER</li>														
													</ul>
												</li>
												<li>-<input type="checkbox" name="code_site" ${role.code_site==1?'checked':''} value="1"> GESTION SITE
													<ul class="second">
												                <li>-<input type="checkbox" name="ajout_SI" ${role.ajout_SI==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_SI" ${role.modif_SI==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_SI" ${role.supp_SI==1?'checked':''}  value="1"> SUPPRIMER</li>														
													</ul>	     
												 </li>																																																						  
												<li>-<input type="checkbox" name="porte" ${role.porte==1?'checked':''} value="1"> GESTION PORTE
												      <ul class="second">
												                <li>-<input type="checkbox" name="ajout_AP" ${role.ajout_AP==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_AP" ${role.modif_AP==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_AP" ${role.supp_AP==1?'checked':''} value="1"> SUPPRIMER</li>														
													  </ul>
												</li>	
												<li>-<input type="checkbox" name="bien_meuble" ${role.bien_meuble==1?'checked':''} value="1"> GESTION BIEN MEUBLEE
												      <ul class="second">
												                <li>-<input type="checkbox" name="bien" ${role.bien==1?'checked':''} value="1">BIEN MEUBLEE
												                     <ul class="second">
														                <li>-<input type="checkbox" name="ajout_b" ${role.ajout_b==1?'checked':''} value="1">AJOUTER </li>
																		<li>-<input type="checkbox" name="modif_b" ${role.modif_b==1?'checked':''} value="1"> MODIFIER</li>	
																		<li>-<input type="checkbox" name="supp_b" ${role.supp_b==1?'checked':''} value="1"> SUPPRIMER</li>														
															          </ul>
												                 </li>
																<li>-<input type="checkbox" name="client" ${role.client==1?'checked':''} value="1"> CLIENT
																      <ul class="second">
														                <li>-<input type="checkbox" name="ajout_cli" ${role.ajout_cli==1?'checked':''} value="1">AJOUTER </li>
																		<li>-<input type="checkbox" name="modif_cli" ${role.modif_cli==1?'checked':''} value="1"> MODIFIER</li>	
																		<li>-<input type="checkbox" name="supp_cli" ${role.supp_cli==1?'checked':''} value="1"> SUPPRIMER</li>														
															          </ul>
																</li>	
																														
													  </ul>
												</li>	
												<li>-<input type="checkbox" name="notification" ${role.notification==1?'checked':''} value="1"> NOTIFICATION	</li>																					
											</ul>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="lesUl">
											<ul class="first">											    											
												<li>-<input type="checkbox" name="ressourceH" ${role.ressourceH==1?'checked':''} value="1"> RESSOURCES HUMAINES
													<ul class="second"> 
												        <li>-<input type="checkbox" name="gestionnaire" ${role.gestionnaire==1?'checked':''} value="1"> GESTIONNAIRE
												              <ul class="second">
												                <li>-<input type="checkbox" name="ajout_GE" ${role.ajout_GE==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_GE" ${role.modif_GE==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_GE" ${role.supp_GE==1?'checked':''} value="1"> SUPPRIMER</li>														
													          </ul>
												        </li>
														<li>-<input type="checkbox" name="locataire" ${role.locataire==1?'checked':''} value="1">LOCATAIRE
														      <ul class="second">
												                <li>-<input type="checkbox" name="ajout_LO" ${role.ajout_LO==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_LO" ${role.modif_LO==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_LO" ${role.supp_LO==1?'checked':''} value="1"> SUPPRIMER</li>														
													          </ul>
														</li>
														<li>-<input type="checkbox" name="proprietaire" ${role.proprietaire==1?'checked':''} value="1">PROPRIETAIRE
														      <ul class="second">
												                <li>-<input type="checkbox" name="ajout_PR" ${role.ajout_PR==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_PR" ${role.modif_PR==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_PR" ${role.supp_PR==1?'checked':''} value="1"> SUPPRIMER</li>														
													          </ul>
														</li>
														<li>-<input type="checkbox" name="archive" ${role.archive==1?'checked':''} value="1">ARCHIVE LOCATAIRE
														      <ul class="second">
												                <li>-<input type="checkbox" name=" " ${role.archive==1?'checked':''} value="1">Document Locataire</li>																													
													          </ul>
														</li>
														<li>-<input type="checkbox" name="archive_cli" ${role.archive_cli==1?'checked':''} value="1">ARCHIVE CLIENT		</li>												     																
														<li>-<input type="checkbox" name="demarcheur" ${role.demarcheur==1?'checked':''} value="1">DEMARCHEUR
														      <ul class="second">
												                <li>-<input type="checkbox" name="ajout_dem" ${role.ajout_dem==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_dem" ${role.modif_dem==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_dem" ${role.supp_dem==1?'checked':''} value="1"> SUPPRIMER</li>														
													          </ul>
														</li>
														<li>-<input type="checkbox" name="reception_cli" ${role.reception_cli==1?'checked':''} value="1">RECEPTION CLIENT
														      <ul class="second">
												                <li>-<input type="checkbox" name="ajout_recli" ${role.ajout_recli==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_recli" ${role.modif_recli==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_recli" ${role.supp_recli==1?'checked':''} value="1"> SUPPRIMER</li>														
													          </ul>
														</li>												
												    </ul>
												</li>												
												<li>-<input type="checkbox" name="comptabilite" ${role.comptabilite==1?'checked':''} value="1"> COMPTABILITES
													<ul class="second">
														<li>-<input type="checkbox" name="paiementL" ${role.paiementL==1?'checked':''} value="1"> PAIEMENTS LOYER
														    <ul class="second">
												                <li>-<input type="checkbox" name="ajout_PL" ${role.ajout_PL==1?'checked':''} value="1">AJOUTER </li>																	
																<li>-<input type="checkbox" name="supp_PL" ${role.supp_PL==1?'checked':''}  value="1"> SUPPRIMER</li>														
													         </ul>
														</li>
														<li>-<input type="checkbox" name="paiement_bm" ${role.paiement_bm==1?'checked':''} value="1"> PAIEMENT BIEN MEUBLEE</li>	
														<li>-<input type="checkbox" name="arriere" ${role.arriere==1?'checked':''} value="1">GESTION ARRIERE 
														      <ul class="second">
												                <li>-<input type="checkbox" name="ajout_ari" ${role.ajout_ari==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_ari" ${role.modif_ari==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_ari" ${role.supp_ari==1?'checked':''} value="1"> SUPPRIMER</li>														
													          </ul>
														</li>
														<li>-<input type="checkbox" name="chargeE" ${role.chargeE==1?'checked':''} value="1"> CHARGE ENTRETIEN</li>	
														<li>-<input type="checkbox" name="gestion_service" ${role.gestion_service==1?'checked':''} value="1"> GESTION SERVICE</li>	
														<li>-<input type="checkbox" name="rapport_c" ${role.rapport_c==1?'checked':''} value="1"> RAPPORTS</li>														
													</ul>
												</li>											
											</ul>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="lesUl">
											<ul class="first">												
												<li>-<input type="checkbox" name="parametre" ${role.parametre==1?'checked':''} value="1"> PARAMETRES
													<ul class="second">
														<li>-<input type="checkbox" name="utilisateur" ${role.utilisateur==1?'checked':''} value="1"> COMPTE UTILISATEUR
														    <ul class="second">
												                <li>-<input type="checkbox" name="ajout_UT" ${role.ajout_UT==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_UT" ${role.modif_UT==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_UT" ${role.supp_UT==1?'checked':''} value="1"> SUPPRIMER</li>														
													          </ul>
														</li>
														<li>-<input type="checkbox" name="profile" ${role.profile==1?'checked':''} value="1"> PROFILS UTILISATEURS</li>
														<li>-<input type="checkbox" name="roleS" ${role.role==1?'checked':''} value="1"> ROLES</li>
														<li>-<input type="checkbox" name="pays" ${role.pays==1?'checked':''} value="1"> PAYS</li>
														<li>-<input type="checkbox" name="ville" ${role.ville==1?'checked':''} value="1"> VILLES</li>
														<li>-<input type="checkbox" name="commune" ${role.commune==1?'checked':''} value="1"> COMMUNES</li>
														<li>-<input type="checkbox" name="quartier" ${role.quartier==1?'checked':''} value="1"> QUARTIERS</li>														
														<li>-<input type="checkbox" name="pourcentage" ${role.pourcentage==1?'checked':''} value="1"> GESTION POURCENTAGE
														     <ul class="second">
												                <li>-<input type="checkbox" name="ajout_PO" ${role.ajout_PO==1?'checked':''} value="1">AJOUTER </li>
																<li>-<input type="checkbox" name="modif_PO" ${role.modif_PO==1?'checked':''} value="1"> MODIFIER</li>	
																<li>-<input type="checkbox" name="supp_PO" ${role.supp_PO==1?'checked':''} value="1"> SUPPRIMER</li>														
													          </ul>
														</li>
														<li>-<input type="checkbox" name="service" ${role.service==1?'checked':''} value="1"> SERVICES</li>	
														<li>-<input type="checkbox" name="g_caution" ${role.g_caution==1?'checked':''} value="1"> GESTION CAUTION</li>																									
														<li>-<input type="checkbox" name="propos" value="1"> A PROPOS</li>
													</ul>
												</li>
											</ul>
										</div>
									</div>
									<div class="col-sm-3">
										<div class="lesUl">
											<ul class="first">	
											  <li>-<input type="checkbox" name="rapport" ${role.rapport==1?'checked':''} value="1"> GESTION RAPPORT
											      <ul class="second">
											           <li>-<input type="checkbox" name="coutM" ${role.coutM==1?'checked':''} value="1"> Côut Mensuelle Des Loyer</li>
														<li>-<input type="checkbox" name="etat_FL" ${role.etat_FL==1?'checked':''} value="1"> Etat Financier Par Locataire</li>
														<li>-<input type="checkbox" name="etat_FS" ${role.etat_FS==1?'checked':''} value="1"> Etat Financier Par Site</li>
														<li>-<input type="checkbox" name="etat_FA" ${role.etat_FA==1?'checked':''} value="1"> Etat Financier Annuelle par Locataire</li>
														<li>-<input type="checkbox" name="etat_FM" ${role.etat_FM==1?'checked':''} value="1"> Etat Financier Mensuelle par Locataire</li>
														<li>-<input type="checkbox" name="fiche_ID" ${role.fiche_ID==1?'checked':''} value="1"> Fiche d'Identification Par Site</li>
														<li>-<input type="checkbox" name="tableau_SU" ${role.tableau_SU==1?'checked':''} value="1"> Tableaux de Suivie des Paiement</li>
														<li>-<input type="checkbox" name="etat_LI" ${role.etat_LI==1?'checked':''} value="1"> Etat Des Loyers Impayés</li>
														<li>-<input type="checkbox" name="bilan_CH" ${role.bilan_CH==1?'checked':''} value="1"> Bilan Charge Entretien</li>
														<li>-<input type="checkbox" name="etat_FAP" ${role.etat_FAP==1?'checked':''} value="1"> Etat Financier Annuelle du Proprietaire</li>
														<li>-<input type="checkbox" name="etat_FMP" ${role.etat_FMP==1?'checked':''} value="1"> Etat Financier Mensuelle du Proprietaire</li>
														<li>-<input type="checkbox" name="etat_FAG" ${role.etat_FAG==1?'checked':''} value="1"> Etat Financier Annuelle du Gestionnaire</li>
														<li>-<input type="checkbox" name="etat_FMG" ${role.etat_FMG==1?'checked':''} value="1"> Etat Financier Mensuelle du Gestionnaire</li>
														<li>-<input type="checkbox" name="etat_FIL" ${role.etat_FIL==1?'checked':''} value="1"> Etat Financier Impôt Loyer </li>
														<li>-<input type="checkbox" name="commissionE" ${role.commissionE==1?'checked':''} value="1"> Commission d'Entrée</li>																									  												  
												   </ul>	
											  </li>
									       </ul>
										</div>
									</div>	
								</div>
								<div class="row">
									<div class="mesBoutons" style="text-align:center;">
										<button class="baj ${role.id!=null?'active':'desactive'}" name="crud" value="AJOUTER">AJOUTER</button>
									</div>
								</div>
							</form>
						</div>
					</div>
				</div>
				
			</div>
        
        
    </div>
</div>
<div class="suppPop" id="supp">
		<div class="mesSupp">
			<div class="header"><i class="fa fa-school fa-lg"></i><span> AJOUT ROLE</span></div>
			<form action="<c:url value="/roles"/>" method="post" style="padding:30px 15px;">
				<input type="hidden" name="page" id="page" value="<c:out value="${page}"/>">
				<div class="row rw">
					<label class="control-label col-sm-2">PROFIL :</label>
					<div class="controls col-sm-10">
						<select class="input-sm" name="profil" required>
							<option selected></option>
							<c:forEach items="${listProfil.rows}" var="mapProfil">
								<option value="<c:out value="${mapProfil.profil}"/>"><c:out value="${mapProfil.profil}"/></option>
							</c:forEach>
						</select>
					</div>
				</div>
				<div class="lesBtn" style="text-align:right;margin-top:50px;">
					<button class="btn-default" name="crud" value="ENREGISTRER">ENREGISTRER</button>
					<button class="btn-default" onclick="return ouvFermer('ferme')">cancel</button>
				</div>
			</form>
		</div>
	</div>
<script src="<c:url value="/assetss/js/jquery.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/assetss/js/bootstrap.min.js" />" type="text/javascript"></script>
<script src="<c:url value="/assetss/js/newVersion.js" />" type="text/javascript"></script>
<script>
  function envoieForm(){
	document.formPro.submit();
   }

    function liens(mode) {
        if (mode == 'home')
            document.location.href = '<c:url value="/menus?menu='+mode+'&ch=sv"/>';
    }

</script>
</body>
</html>
