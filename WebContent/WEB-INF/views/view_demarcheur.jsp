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
		<title> GESTIONS DES DEMARCHEURS</title>
		
		<sql:query var="listDem" dataSource="jdbc/gestions_imm">
			select * from zone where code="${sessionScope.agence.code}" and matricule="${demearcheur.matricule}"
		</sql:query>
		
<style>
	
legend {font-weight:bold;color:#7030a0;height:30px;}
 legend {background:white;border: 1px solid #000;padding: 1px;}
	
 .spa1{
	color: #0000ff;
	font-size:14px;	
	margin-left:250px;
}

#myTableD {
	height: 440px;
	margin-bottom: 0;
	overflow-y: auto;
	border: 1px solid gray;
}


.theadD {
	display: table-header-group;
	color: white;
	font-size: 13px;
	font-weight: normal;
	background-color: #0000ff;
}

.theadD .tdD {
	padding: 2px 4px;
	text-align: center;
	color: #FFF;
}

.tbodyD {
	display: table-row-group;
}
.tbodyD input {
	width: 100%;
	height: 20px;
	text-align: center;
}
.trD {
	display: table-row;
}
.tdD {
	display: table-cell;
	color: #000000;
	font-size: 14px;
	font-weight: bold;
}
.tdD button{
	position:;
	z-index:9;
}
.tdD button:hover{
	cursor: pointer;
}
.trD:hover,.tdD:hover{cursor:pointer;}
	#formAdd span {
			background: gray;
			padding: 5px;
			color: white;
			font-size: 20px;
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
      <div class="card shadow mb-4" style="width:830px;margin-left:180px;">
        <div class="card-header py-3">
          <h6 class="m-0 font-weight-bold text-primary" style="text-align:center;">MODIFIER UN DEMARCHEUR   </h6>
        </div>
        <div class="card-body">
          <form action="<c:url value="/view_demarcheur"/>" method="post"  class="myFormulaire">            
           <div class="card-body">	      
	      <div class="form-group row">
	       <div class="col-sm-4 mb-2 mb-sm-0">
	        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
	         <input type="text" name="nom" class="form-control" required  value="<c:out value="${demearcheur.nom}"/>">
	        </div>
	         <div class="col-sm-4 mb-2 mb-sm-0">
	        <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
	         <input type="text" name="prenom" class="form-control" required  value="<c:out value="${demearcheur.prenom}"/>">
	        </div>
	         <div class="col-sm-4 mb-2 mb-sm-0">
	        <label for="platbus" class="">TELEPHONE <span class="text-danger ml-2">*</span></label>
	        <input type="text" name="tels" class="form-control" required  value="<c:out value="${demearcheur.tels}"/>">	
	        </div>      
	      </div>    
	      <div class="form-group row">
	         <div class="col-sm-4 mb-2 mb-sm-0">
	         <label for="platbus" class="">CONTACT WhatsAPP </label>
		     <input type="text" name="tel" class="form-control" value="<c:out value="${demearcheur.tel}"/>">
	       </div>       		
			<div class="col-sm-8 mb-4 mb-sm-0">
	         <label for="platbus" class="">ADRESSE</label>
			   <input type="text" name="adresse" class="form-control" value="<c:out value="${demearcheur.zone}"/>">
			</div>		
	      </div>    
     
       <input type="hidden" name="matricule" value="<c:out value="${demearcheur.matricule}"/>">
       <input type="hidden" name="id" value="<c:out value="${demearcheur.id}"/>">
        <hr><a class="btn btn-danger float-left" href="<c:url value="/menus?menu=demarcheur"/>"> Retour</a>
             &nbsp;&nbsp;
         
            <c:if test="${sessionScope.rolePr.modif_dem==1}">
             <button name="crud" value="MODIFIER" type="submit" class="btn btn-success">Modifier</button>      
             </c:if>          
            </div>
           </form>
           
           <fieldset  style="margin-top:-15px;">
             <legend class="legendary"><span class="spa1">GESTION ZONE DEMARCHEUR</span></legend>
             <div class="table" style="margin-bottom:0;margin-top:-7px;">
								<div class="theadD">
									<div class="trD">
										<div class="tdD" style="width:30%;">VILLE</div>
										<div class="tdD" style="width:30%;">COMMUNE</div>
										<div class="tdD" style="width:30%;">ZONE</div>
										<div class="tdD" style="width:10%;">ACTION</div>
									</div>
								</div>
							</div>
							<div id="myTableD" style="height:170px !important;">
								<div  id="tPM">
									<div class="table" style="margin-bottom:0;">
										<div class="tbodyD" id="tbody">
											<c:forEach items="${listDem.rows}" var="mapPM" varStatus="boucle">
												<div class="tr ${boucle.index % 2 == 0 ? 'pair' : 'impair'}">
													<div class="td tdCenter" style="width:30%;">${mapPM.ville}</div>
													<div class="td tdCenter" style="width:30%;">${mapPM.commune}</div>
													<div class="td tdCenter" style="width:30%;">${mapPM.zone}</div>
													<div class="td tdCenter" style="width:10%;">
														<button onclick="document.location.href='<c:url value="/view_demarcheur?idPM=${mapPM.id}&page=${page}&crud=idPM" />'"><img alt=""  style=" width: 20px;" src="assets/frontend/img/supp.jpg"></button>
													</div>
												</div>
											</c:forEach>
										</div>
									</div>
								</div>
								
								<form action="<c:url value="/view_demarcheur"/>" method="post" name="formM" id="formAdd"  class="none">
									<div class="table" style="margin-bottom:0;">
										<div class="tbody" id="ajoutPM">
											<div class="tr">
												<div class="td" style="width:30%;">
												  <sql:query var="listVil" dataSource="jdbc/gestions_imm">
														select * from ville 
												   </sql:query>
													<select name="vil1" id="vil1" onchange="getDiscipline(this);" style="width:100%;" class="input-sm" required>
						   								<option ></option>	
					            						<c:forEach items="${listVil.rows}" var="mapvil" varStatus="boucle">
					            							<option class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}" value="<c:out value="${mapvil.ville}"/>">${mapvil.ville}</option>
					            						</c:forEach>
						   							</select>
												</div>
												<div class="td" style="width:30%;">
													<select name="com1" id="com1"  style="width:100%;" class="input-sm" required>
						   			
						   							</select>
												</div>
												<div class="td" style="width:30%;">
												  <input type="text" name="zon1" id="zon1" class="input-sm" value="">												 
												</div>	
												 <input type="hidden" name="crud" value="ZONED">
												<input type="hidden" name="nbre" id="nbre" value="1">
												<input type="hidden" name="matri"  value="<c:out value="${demearcheur.matricule}"/>">
												<input type="hidden" name="tels"  value="<c:out value="${demearcheur.tels}"/>">
												<input type="hidden" name="page" value="<c:out value="${page}"/>">																							
												<div class="td" style="width:10%;text-align:center;">
													<span onclick="getDiscipline('add');">+</span>
												</div>
											</div>
										</div>
									</div>
								</form>
								
							</div>
							
					<div class="mesB">
								<button class="btn btn-primary ${demearcheur.id!=null?'active':'desactive'}" id="add" onclick="getDiscipline('ajout');">Ajout</button>
								<button class="btn btn-primary none" style="color:white;" id="move" onclick="getDiscipline('list');">Liste</button>
								<button class="btn btn-danger float-right none" style="color:white;" id="val" onclick="document.formM.submit();">Valider</button>
					</div>		
           </fieldset>
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
 <script>
 function getVisible(mode){
			if(mode == 'visible'){
				document.getElementById("decouper").style.display = "none";
				document.getElementById("myForm").style.height = "445px";
				document.getElementById("arrowDown").style.display = "none";
				document.getElementById("arrowUp").style.display = "block";
			}
			if(mode == 'hidden'){
				document.getElementById("myForm").style.height = "160px";
				document.getElementById("decouper").style.display = "block";
				document.getElementById("arrowUp").style.display = "none";
				document.getElementById("arrowDown").style.display = "block";
			}
		}
		function getDiscipline(mode){
			if(mode=='ajout'){
				document.getElementById("tPM").style.display = "none";
				document.getElementById("formAdd").style.display = "block";
				document.getElementById("add").style.display = "none";
				document.getElementById("move").style.display = "inline";
				document.getElementById("val").style.display = "inline";
				
			}
			if(mode=='list'){
				document.getElementById("formAdd").style.display = "none";
				document.getElementById("tPM").style.display = "inline";
				document.getElementById("move").style.display = "none";
				document.getElementById("add").style.display = "block";
				document.getElementById("val").style.display = "none";
			}
			if(mode=='add'){
				var nbre = parseInt(document.getElementById("nbre").value, 10) + 1;
				 $.ajax({
					 type : 'post',
					 url : 'ajax',
					 dataType: 'json',
					 data :{
						crud: 'toutDem'
					},
					success : function(result){
						 var option = '<option value=""></option>';
						 $.each(result, function(index, value){
							 option += '<option value="'+value.libelle+'" onchange="" class="${boucle.index % 2 == 0 ? 'pair' : 'impair'}">'+value.libelle+'</option>';
						 });
						 var addHtml = '<div class="tr" id="ligne'+nbre+'">';
						 	addHtml	+= '<div class="td" style="width:30%;">';
							 	addHtml	+= '<select name="vil'+nbre+'" id="vil'+nbre+'" onchange="getDiscipline(this);" style="width:100%;" class="input-sm" required>';
							 	addHtml	+= '<option ></option>'+option+'</select></div>';
							addHtml	+= 	'<div class="td" style="width:30%;">';
								addHtml	+= '<select name="com'+nbre+'" id="com'+nbre+'"  style="width:100%;" class="input-sm" required>';
								addHtml	+= '</select></div>';
							addHtml	+= 	'<div class="td" style="width:30%;">';
								addHtml	+= '<input name="zon'+nbre+'" id="zon'+nbre+'" style="width:100%;" class="input-sm">';
								addHtml	+= '</div>';
							addHtml	+= '<div class="td" style="width:10%;text-align:center;">';
								addHtml	+= '<span onclick="suppPM(this);">-</span></div></div>';
						$("#ajoutPM").append(addHtml);
						document.getElementById("nbre").value = nbre;
					 },
				 });
			}
			
			if($(mode).prop('tagName') == 'SELECT'){			
				var ch = $(mode).attr('id').substring(3);
				 $.ajax({
					 type : 'post',
					 url : 'ajax',
					 dataType: 'json',
					 data :{
							libelle : document.getElementById("vil"+ch).value,
							crud: 'adCom'
					},
					 success : function(result){
						 var option = '<option value="" selected>----choix----</option>';
						 $.each(result, function(index, value){
							option += '<option value="'+value.libelle+'" onchange="">'+value.libelle+'</option>';
						 });
						 document.getElementById("com"+ch).innerHTML = option;
					 },
				 });
			}
		 }

</script>
</body>
</html>