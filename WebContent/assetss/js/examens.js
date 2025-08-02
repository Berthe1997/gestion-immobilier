$(document).ready(function(){
	$(".desactive").attr("disabled","disabled");
	$(".none").css("display","none");
});
function filtrer(){
	var filtre,tbody,ligne,cellule,cellule_0,i,texte_1,texte_2;
	filtre = document.getElementById("filtre").value.toUpperCase();
	tbody = document.getElementById("tbody");
	ligne = tbody.querySelectorAll('div');
	
	for(i = 0; i < ligne.length; i++){
		cellule_0 = ligne[i].getElementsByTagName("div")[0];
		cellule = ligne[i].getElementsByTagName("div")[1];
		if(cellule_0){
			texte_1 = cellule_0.innerText;
			texte_2 = cellule.innerText;
			if (texte_1.toUpperCase().indexOf(filtre) > -1){
				ligne[i].style.display = "";
			} else {
				if (texte_2.toUpperCase().indexOf(filtre) > -1){
					ligne[i].style.display = "";
				} else {
					ligne[i].style.display = "none";
				}
			}
		}
	}
}
function rechercheEtab(){
	var dren = document.getElementById("drenEt").value;
	var groupe = document.getElementById("groupeEt").value;
	$.ajax({
		type : 'post',
		url : 'ajax',
		dataType: 'json',
		data : {
			dren: dren,
			groupe: groupe,
			crud: "searchEtab"
		}, success : function(result){
			document.getElementById("tbodyEtab").innerHTML ="";
			var  boucle = 0;
			$.each(result, function(index, value){
				boucle += 1; var ligne	=	"";
				if(boucle % 2 == 0) {ligne = "pair"}else{ligne = "impair"}
				var tr1 = '<div class="tr '+ligne+'">';
					tr1 += '<div class="td" style="width:10%;text-align:center;"><input type="checkbox" name="check'+boucle+'" value="1"></div>';
					tr1 += '<div class="td" style="width:15%;text-align:center;"><input type="hidden" name="code'+boucle+'">'+value.drens+'</div>';
					tr1 += '<div class="td" style="width:20%;text-align:center;"><input type="hidden" name="groupe'+boucle+'">'+value.groupes+'</div>';
					tr1 += '<div class="td" style="width:65%;text-align:center;"><input type="hidden" name="etab'+boucle+'">'+value.etabs+'</div></div>';
				$("#tbodyEtab").append(tr1);
			});
			document.getElementById("To").innerHTML =boucle;
			document.getElementById("Tos").value = boucle;
		}
	});
}