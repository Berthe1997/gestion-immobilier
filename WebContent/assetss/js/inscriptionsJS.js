function changeImage(requiredImage) {
	var slider = document.getElementById('boxInscription');
	 	var box = 1;
  	var maxImages = 3;
    // Début de l'algorithme  .
  	if(requiredImage > maxImages){//Si nous avont spécifié une image au dela de la dernière, on revient à la première
    	  box = 1;
   	} else if(requiredImage < 1){ //Si nous avont spécifié une image 0 ou moins, on vas à la dernière image
     box = maxImages;
   	} else{
    	box = requiredImage; // Sinon, on vas à l'image spécifiée.
   }
   //On dis au slider à travers sa classe quel image il doit afficher
   slider.className = "box"+box;
    
}

function validerInfo1(){
	/*========== INFO ELEVE 1 ==========*/
	var matricule = document.getElementById("matricule").value;
	var nom = document.getElementById("nom").value;
	var prenoms = document.getElementById("prenoms").value;
	var dateNais = document.getElementById("dateNais").value;
	var classe = document.getElementById("classe").value;
	var ecoleM = document.getElementById("ecoleM").value;
	if(matricule.length >= 8 || matricule.length == 0 ){
		if(nom.length >= 1){
			if(prenoms.length >=1) {
				if(dateNais.length>=10){
					if(classe.length>1){
						if(ecoleM.length>=1){
							changeImage(2)
						} else {
							document.getElementById("mesg1").innerHTML = "Le champ école ministère doit contenir au moins 10 caractères.";
							document.getElementById("ecoleM").focus();
						}
					} else {
						document.getElementById("mesg1").innerHTML = "Le champ classe ne doit pas etre vide.";
						document.getElementById("classe").focus();
					}
				} else {
					document.getElementById("mesg1").innerHTML = "Le champ date de naissance doit contenir au moins 10 caractères.";
					document.getElementById("dateNais").focus();
				}
			} else {
				document.getElementById("mesg1").innerHTML = "Le champ prénoms doit contenir au moins 5 caractères.";
				document.getElementById("prenoms").focus();
			}
		} else {
			document.getElementById("mesg1").innerHTML = "Le champ nom doit contenir au moins 3 caractères.";
			document.getElementById("nom").focus();
		}
	} else {
		document.getElementById("mesg1").innerHTML = "Le champ matricule doit contenir au moins 8 caractères.";
		document.getElementById("matricule").focus();
	}
}
function validerInfo2(){
	/*========== INFO ELEVE 2 ==========*/
	if($("#extrait").attr('checked') == "checked"){
		changeImage(3)
	} else document.getElementById("mesg2").innerHTML = "L'extraire de naissance est obligatoire.";
}
function validerInfo3(){
	var nomP = document.getElementById("nomP").value;
	var nomM = document.getElementById("nomM").value;
	var nomT = document.getElementById("nomT").value;
	var portableP = document.getElementById("portableP").value;
	var portableM = document.getElementById("portableM").value;
	var portableT = document.getElementById("portableT").value;
	
	if(nomP.length < 2 && portableP < 10){
		if(nomM.length < 2 && portableM < 10){
			if(nomT.length < 2 && portableT < 10){
				document.getElementById("messageAlert").style.display="block";
				document.getElementById("alert").innerHTML="<P>Le nom, prénoms et téléphone doivent etre renseigner pour l'un des parents au moins.</P>"
				return false;
			}
		}
	}
	
}