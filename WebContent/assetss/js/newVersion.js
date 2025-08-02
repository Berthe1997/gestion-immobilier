$(document).ready(function(){
	$(".desactive").attr("disabled","disabled");
	$(".desactiveRole").attr("disabled","disabled");
	$(".none").css("display","none");
});
function envoiForm(){
	document.forms["formSearch"].submit();
}

function getCash(mode) {
	if(mode == 'cashdispoF'){
		var email	=	document.getElementById("email").value;
		var libelleC	=	document.getElementById("codeCaisse").value;
		var page = "ouvertureCaisse";
		$.ajax({
			type : 'get',
			url : 'CRUDOuvFer?cruds=tester',
			dataType: 'json',
			data : {
				nomCaissier: email,
				libelleC: libelleC,
				page: page
			}, success : function(result){
				$.each(result, function(index, value){
					document.getElementById("cashO").value = "";
					document.getElementById("diff").value = "";
					document.getElementById("dateo").value = "";
					
					var montt = value.montant;
					var convertir = montt.toLocaleString("fr-FR");
					document.getElementById("cashF").value	=	montt;
				});
			}
		});
	}
}

function pad2(n) {
	 return (n < 10 ? '0' : '') + n;
}
function getDiff() {
	var cashF = document.getElementById("cashF").value;
	var cashO = document.getElementById("cashO").value
	var input = document.getElementById("cashO").value.replace(/[\D\s\._\ - ] +/g, '');
	
	var cashOs = parseInt(input, 10);
	
	var date = new Date();
	var month = pad2(date.getMonth()+1);
	var day = pad2(date.getDate());
	var year= date.getFullYear();

	var formattedDate =  year+"-"+month+"-"+day;
	
	/*====== un petit test =======*/
	var taille	=	cashO.length;
	var quotient = taille / 3;
	var qt	=	parseInt(quotient, 10);
	var reste	=	taille % 3;
	var a =0;
	if(reste!=0)
		a = cashO.substring(0,reste);
	else
		a = cashO.substring(0,3);
	var dispoc =a;
	if(qt!=0){
		for(let i=1;i<qt+1;i++)
			var j = i*3;
			if(j==3)
				var b = cashO.substring(reste,j+reste);
				if(reste==0)
					dispoc = a;
				else
					dispoc = a+" "+b;

	}
	/*======== la fin du test =====*/
	
	var diff = cashF - cashOs;
	if(isNaN(cashO)){
		document.getElementById("cashO").value = "";
		document.getElementById("diff").value = "";
	}else{
		document.getElementById("cashO").value = cashOs;
		document.getElementById("diff").value = diff;
	}
	document.getElementById("dateo").value = formattedDate;
}	

function envoiCash(){
	var cashF = document.getElementById("cashF").value;
	var cashO = document.getElementById("cashO").value
	if(cashF > cashO){
		document.getElementById("dif").style.display = "block";
		return false;
	}
}

function ouvrirS(tag){
	var parent	=	$(tag).parent();
	var valeur = parent.find("input").val();
	document.getElementById("supp").style.display = "block";
	document.getElementById("typeSup").value = "supS";
	document.getElementById("idS").value = valeur;
}

function montantSaisi(mode){
	if(mode=='saisi'){
		var mttSaisi = document.getElementById("mttSaisi").value.replace(/[\D\s\._\ - ] +/g, '');
		if(isNaN(parseInt(mttSaisi, 10)))
			document.getElementById("mttSaisi").value = 0;
		else
			document.getElementById("mttSaisi").value = parseInt(mttSaisi, 10);
		
		document.getElementById("alert").style.display="none";
	}
	
	if(mode=='envoi'){
		var solde = document.getElementById("solde").value;
		var mttSaisi = document.getElementById("mttSaisi").value.replace(/[\D\s\._\ - ] +/g, '');
		
		var input	=	parseInt(solde, 10);
		var input1 = parseInt(mttSaisi, 10);
		if(input1 > input){
			document.getElementById("alert").style.display="block";
			document.getElementById("alert").innerHTML="Le montant saisi est supérieur au solde.";
			return false;
		}
	}
	
	if(mode=='fermer'){
		document.getElementById("pop").style.display="none";
	}
}

function montantSaisiA(mode){
	if(mode=='saisiA'){
		var mttSaisiA = document.getElementById("mttSaisiA").value.replace(/[\D\s\._\ - ] +/g, '');
		if(isNaN(parseInt(mttSaisiA, 10)))
			document.getElementById("mttSaisiA").value = 0;
		else
			document.getElementById("mttSaisiA").value = parseInt(mttSaisiA, 10);
		
		document.getElementById("alertA").style.display="none";
	}
	
	if(mode=='envoiA'){
		var soldeAR = document.getElementById("soldeAR").value;
		var mttSaisiA = document.getElementById("mttSaisiA").value.replace(/[\D\s\._\ - ] +/g, '');
		
		var input	=	parseInt(soldeAR, 10);
		var input1 = parseInt(mttSaisiA, 10);
		if(input1 > input){
			document.getElementById("alertA").style.display="block";
			document.getElementById("alertA").innerHTML="Le montant saisi est supérieur au solde.";
			return false;
		}
	}
	
	if(mode=='fermer'){
		document.getElementById("pop").style.display="none";
	}
}

function fer(){$("#messageAlert").toggle();}

function ouvFermer(mode){
	if(mode=='ouvNiv'){
		document.getElementById("niveauxMat").style.display = "block";
	}
	if(mode=='ferNiv'){
		document.getElementById("niveauxMat").style.display = "none";
		return false;
	}
	if(mode=='ouvS'){
		document.getElementById("supp").style.display = "block";
		document.getElementById("typeSup").value = "supP";
	}
	
	if(mode=='ferme'){
		document.getElementById("supp").style.display = "none";
		return false;
	}
	
	if(mode=='ajoutE'){
		document.getElementById("supp").style.display = "block";
		return false;
	}
	
	
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
	
}

function toggles(mode){	
	if(mode == 'docPop_1'){
		var docPop = document.getElementById(mode);
		if(docPop.style.display ==="none"){
			$(".docPop").css("display","none");
			docPop.style.display ="block";
		} else $(".docPop").css("display","none");
	}	
	if(mode == 'docPop_2'){
		var docPop = document.getElementById(mode);
		if(docPop.style.display ==="none"){
			$(".docPop").css("display","none");
			docPop.style.display ="block";
		} else $(".docPop").css("display","none");
	}	
	if(mode == 'docPop_3'){
		var docPop = document.getElementById(mode);
		if(docPop.style.display ==="none"){
			$(".docPop").css("display","none");
			docPop.style.display ="block";
		} else $(".docPop").css("display","none");
	}
	if(mode == 'docPop_4'){
		var docPop = document.getElementById(mode);
		if(docPop.style.display ==="none"){
			$(".docPop").css("display","none");
			docPop.style.display ="block";
		} else $(".docPop").css("display","none");
	}
	if(mode == 'docPop_5'){
		var docPop = document.getElementById(mode);
		if(docPop.style.display ==="none"){
			$(".docPop").css("display","none");
			docPop.style.display ="block";
		} else $(".docPop").css("display","none");
	}
	if(mode == 'docPop_6'){
		var docPop = document.getElementById(mode);
		if(docPop.style.display ==="none"){
			$(".docPop").css("display","none");
			docPop.style.display ="block";
		} else $(".docPop").css("display","none");
	}
	
}	

function documentation(mode){
	
	if(mode == 'journaliers') document.formBilans.submit();
	if(mode == 'generals') document.formBilans.submit();
	if(mode == 'dates'){
		document.getElementById("debuts").removeAttribute('disabled');
		document.getElementById("fins").setAttribute('disabled','disabled');
	}
	if(mode == 'customs'){
		document.getElementById("debuts").removeAttribute('disabled');
		document.getElementById("fins").removeAttribute('disabled');
	}
	if(mode == 'debs'){
		var type = document.getElementById("typeBilans").value;
		if( type == 'Bilan_par_dates'){
			document.getElementById("debuts").onchange = function() { document.formBilans.submit(); };
		}
	}
	if(mode == 'fns'){
		var type = document.getElementById("typeBilans").value;
		if( type == 'Bilan_customises'){
			document.getElementById("fins").onchange = function() { document.formBilans.submit(); };
		}
	}
	
}
	// ============ ============ ============ DEUXIEME CODE  ============ ============ =========================== --> 
function documentations(mode){
	if(mode == 'journalier') document.formBilan.submit();
	if(mode == 'general') document.formBilan.submit();
	if(mode == 'date'){
		document.getElementById("debut").removeAttribute('disabled');
		document.getElementById("fin").setAttribute('disabled','disabled');
	}
	if(mode == 'custom'){
		document.getElementById("debut").removeAttribute('disabled');
		document.getElementById("fin").removeAttribute('disabled');
	}
	if(mode == 'deb'){
		var type = document.getElementById("typeBilan").value; 
		if( type == 'Bilan_par_date'){
			document.getElementById("debut").onchange = function() { document.formBilan.submit(); };
		}
	}
	if(mode == 'fn'){
		var type = document.getElementById("typeBilan").value;
		if( type == 'Bilan_customise'){
			document.getElementById("fin").onchange = function() { document.formBilan.submit(); };
		}
	}
	
		
	if(mode == 'effectif') document.formSynthese.submit();
	if(mode == 'paiement') document.formSynthese.submit();
	if(mode == 'il') document.formSynthese.submit();
	if(mode == 'droitP') document.formSynthese.submit();
	if(mode == 'droitI') document.formSynthese.submit();
	if(mode == 'school1') document.formListAff.submit();
	if(mode == 'choix_2') document.formListAff.submit();
	if(mode == 'school2') document.formServiceStr.submit();
	if(mode == 'choix_3') document.formServiceStr.submit();
	if(mode == 'school3') document.formRelance.submit();
	
	if(mode == 'choix_11') document.formListClas.submit();
	if(mode == 'schools1') document.formListClas.submit();
	if(mode == 'choix_P') document.formListParent.submit();
	if(mode == 'schoolP') document.formListParent.submit();
	if(mode == 'choix_Af') document.formListAffNaf.submit();
	if(mode == 'schoolAf') document.formListAffNaf.submit();
	if(mode == 'genres') document.formListGenre.submit();
	if(mode == 'genre') document.formListGenre.submit();
	if(mode == 'inscrit') document.formListInscrit.submit();
	if(mode == 'excel') document.formListExcel.submit();
	
}

function suppPM(tag){
	var nbre = parseInt(document.getElementById("nbre").value, 10) - 1;
	var parent	=	$(tag).parent().parent();
	parent.remove();
	document.getElementById("nbre").value = nbre;
}


//============ ============ ============ LISTE METHODE ZONE  ============ ============ =========================== -->
function ajax(mode){
	
	if(mode=='villes'){
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				ville: document.getElementById("ville").value,
				crud: mode
			}, success : function(result){
				var option = '<option value="" selected> </option>';
				$.each(result, function(index, value){
					option += '<option value="'+value.ville+'">'+value.ville+'</option>';
					
				});
				document.getElementById("commune").innerHTML = option;
			}
		});
	}
	
    if(mode=='communes'){
    	$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				commune: document.getElementById("commune").value,
				crud: mode
			}, success : function(result){
				var option = '<option value="" selected> </option>';
				$.each(result, function(index, value){
					option += '<option value="'+value.commune+'">'+value.commune+'</option>';
					
				});
				document.getElementById("zone").innerHTML = option;
			}
		});
	}

    	
    	if(mode=='villS'){
    		$.ajax({
    			type : 'post',
    			url : 'ajax',
    			dataType: 'json',
    			data : {
    				vill: document.getElementById("vill").value,
    				crud: mode
    			}, success : function(result){
    				var option = '<option value="" selected> </option>';
    				$.each(result, function(index, value){
    					option += '<option value="'+value.vill+'">'+value.vill+'</option>';
    					
    				});
    				document.getElementById("commun").innerHTML = option;
    			}
    		});
    	}
    	
        if(mode=='communS'){
        	$.ajax({
    			type : 'post',
    			url : 'ajax',
    			dataType: 'json',
    			data : {
    				commun: document.getElementById("commun").value,
    				crud: mode
    			}, success : function(result){
    				var option = '<option value="" selected> </option>';
    				$.each(result, function(index, value){
    					option += '<option value="'+value.commun+'">'+value.commun+'</option>';
    					
    				});
    				document.getElementById("zon").innerHTML = option;
    			}
    		});
    	}
    
  // ============ ============ ============ LISTE METHODE DOC_COMPT ============ ============ =========================== // 	
	if(mode=='proprietaire'){
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				matricule: document.getElementById("matricule").value,
				crud: mode
			}, success : function(result){
				var option = '<option value="" selected> </option>';
				$.each(result, function(index, value){
					option += '<option value="'+value.matricule+'">'+value.matricule+'</option>';
					
				});
				document.getElementById("sites").innerHTML = option;
			}
		});
	}
	
	// ============ ============ ============ LISTE METHODE ARRIERE ============ ============ =========================== // 
	if(mode=='locataireC'){
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				matricule: document.getElementById("matricule").value,
				crud: mode
			}, success : function(result){
				var option = '<option value="" selected> </option>';
				$.each(result, function(index, value){
					option += '<option value="'+value.matricule+'">'+value.matricule+'</option>';
					
					document.getElementById("matri").value = value.matri;	
				});
				document.getElementById("ans").innerHTML = option;
			}
		});
	}
	
	if(mode=='ansC'){
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				ans: document.getElementById("ans").value,
				matri: document.getElementById("matri").value,
				crud: mode
			}, success : function(result){
				var option = '<option value="" selected> </option>';
				$.each(result, function(index, value){
					option += '<option value="'+value.ans+'">'+value.ans+'</option>';
					document.getElementById("matr").value = value.matr;		
				});
				document.getElementById("mois").innerHTML = option;
			}
		});
	}
	
	if(mode=='moisC'){
		var mois = document.getElementById("mois").value;
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				mois: mois,
				matr: document.getElementById("matr").value,
				crud: mode
			}, success : function(result){
				$.each(result, function(index, value){ 
					
				document.getElementById("montant").value = value.montant;
				document.getElementById("mont").value = value.montant;
				
				});
			}
		});
	}
	
	
// ============ ============ ============ LISTE METHODE AUTRE ============ ============ =========================== // 	
	if(mode=='agence'){
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				code: document.getElementById("code").value,
				crud: mode
			}, success : function(result){
				var option = '<option value="" selected> </option>';
				$.each(result, function(index, value){
					option += '<option value="'+value.code+'">'+value.code+'</option>';
					
				});
				document.getElementById("sites").innerHTML = option;
			}
		});
	}
	
	if(mode=='getCalendrier'){
		var mois = document.getElementById("mois").value;
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				mois: mois,
				crud: mode
			}, success : function(result){
				$.each(result, function(index, value){ 
					
				document.getElementById("solde").value = value.montant;
				document.getElementById("soldes").value = value.montant;
				document.getElementById("mttSaisi").value = value.montant;	
				document.getElementById("moi").value = value.mois;	
				document.getElementById("an").value = value.an;	
				});
			}
		});
	}
	
	if(mode=='getArrieres'){
		var arriere = document.getElementById("arriere").value;
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				arriere: arriere,
				crud: mode
			}, success : function(result){
				$.each(result, function(index, value){ 
					
				document.getElementById("soldeARs").value = value.montantAR;
				document.getElementById("soldeAR").value = value.montantAR;				
				document.getElementById("moiAR").value = value.moisAR;	
				document.getElementById("anAR").value = value.anAR;	
				});
			}
		});
	}
	
	if(mode=='getBiens'){
		var bien = document.getElementById("bien").value;
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				bien: bien,
				crud: mode
			}, success : function(result){
				$.each(result, function(index, value){ 					
				document.getElementById("types").value = value.type;
				document.getElementById("adresses").value = value.adresse;
				document.getElementById("descrip").value = value.descrip;
				document.getElementById("loyerN").value = value.loyerN;
					
				});
			}
		});
	}
	
	
	if(mode=='getTerrain'){
		var code = document.getElementById("code").value;
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				code: code,
				crud: mode
			}, success : function(result){
				$.each(result, function(index, value){ 					
				document.getElementById("prix").value = value.prix;
				document.getElementById("solde").value = value.solde;
					
				});
			}
		});
	}
	
}


$("input").focus(function(){document.getElementById("alert").innerHTML="";});