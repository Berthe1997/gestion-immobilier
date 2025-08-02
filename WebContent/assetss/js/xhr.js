 /*
function emploi(mode) {
    document.getElementById("messageAlertN").style.display = 'block';
    var classeA = document.getElementById("classe").value;
    //  var profMatA = document.getElementById("profMat").value;
    if (mode === 'niveaux') {
        classeA = '';
      //  matss = '';
    }
    $.ajax({
        type: 'post',
        url: 'xhr',
        dataType: 'json',
        data: {
            niveaux: document.getElementById("niveaux").value,
            classe: classeA + "",
            page: 'saisi_emploiT',
            mode: mode
        }, success: function (result) {
        	 document.getElementById("tbody").innerHTML = "";
             var thecount = 0;
             for (var key of Object.keys(result)) {
            
            	 if (key === '0') {
            		 for (var cle of Object.keys(result[key])) {
            			 thecount += 1;
                         var ligne = "";
                         if (thecount % 2 == 0) {
                             ligne = "pair"
                         } else {
                             ligne = "impair"
                         }
                         
                         var tr1 = '<div class="tr ' + ligne + '">';  
                         /* === PREMIERE LIGNE DU TABLEAU ++++
                         tr1 += '<div class="td" style="width:10%;">'+
                         '<input type="hidden" id="id_' +thecount+ '" name="id_' +thecount+ '" value="' + result[key][cle].id + '"> '+
                   	     '<input type="text" name="heurDF' +thecount+ '" id="heurDF' +thecount+ '" required class="input-sm" value=" ' + result[key][cle].heurD + '-' +result[key][cle].heurF + ' " style="width:100%;height:30px;text-align:center;"> </div>';
                   	   
                         /* === DEUXIEME LIGNE DU TABLEAU ++++
                         tr1 +=' <div class="td" style="width:14%">'+														 
							'<select name="matprof1_' +thecount+ '" id="matprof1_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
							  '<option value="" selected>choix du professeur</option>'+
							  '<option value='+ result[key][cle].professeur+'>'+ result[key][cle].professeur+' </option>'+							 			             			
							  '</select>'+ 	
							  
							  '<select name="nommat1_' +thecount+ '" id="nommat1_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
                              '<option value="" selected>choix de la matière</option>'+
								'<option value="'+ result[key][cle].matiere+' ">'+ result[key][cle].matiere+'</option>'+								       			
							 '</select> '+
						  ' </div>';
                         
                         /* === TROISIEME LIGNE DU TABLEAU ++++
                         tr1 +=' <div class="td" style="width:14%">'+							
							'<select name="matprof2_' +thecount+ '" id="matprof2_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
							  '<option value="" selected>choix du professeur</option>'+
							  '<option value='+ result[key][cle].professeur+' >'+ result[key][cle].professeur+' </option>'+							 			             			
							  '</select>'+ 		
							  
							  '<select name="nommat2_' +thecount+ '" id="nommat2_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
	                            '<option value="" selected>choix de la matière</option>'+
									'<option value="'+ result[key][cle].matiere+' ">'+ result[key][cle].matiere+'</option>'+								       			
								 '</select> '+								 
						  ' </div>';
                         
                         /* === QUATRIEME LIGNE DU TABLEAU ++++
                         tr1 +=' <div class="td" style="width:14%">'+							 
							'<select name="matprof3_' +thecount+ '" id="matprof3_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
							  '<option value="" selected>choix du professeur</option>'+
							  '<option value='+ result[key][cle].professeur+' >'+ result[key][cle].professeur+' </option>'+							 			             			
							  '</select>'+ 	
							  
							  '<select name="nommat3_' +thecount+ '" id="nommat3_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
	                            '<option value="" selected>choix de la matière</option>'+
									'<option value="'+ result[key][cle].matiere+' ">'+ result[key][cle].matiere+'</option>'+								       			
							 '</select> '+
						  ' </div>';
                         
                         /* === CINQUIEME LIGNE DU TABLEAU ++++ 
                         tr1 +=' <div class="td" style="width:14%">'+														 
							'<select name="matprof4_' +thecount+ '" id="matprof4_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
							  '<option value="" selected>choix du professeur</option>'+
							  '<option value='+ result[key][cle].professeur+' >'+ result[key][cle].professeur+' </option>'+							 			             			
							  '</select>'+ 	
							  
							  '<select name="nommat4_' +thecount+ '" id="nommat4_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
	                            '<option value="" selected>choix de la matière</option>'+
									'<option value="'+ result[key][cle].matiere+' ">'+ result[key][cle].matiere+'</option>'+								       			
								 '</select> '+
						  ' </div>';
                         
                         /* === SIXIEME LIGNE DU TABLEAU ++++
                         tr1 +=' <div class="td" style="width:14%">'+														 
							'<select name="matprof5_' +thecount+ '" id="matprof5_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
							  '<option value="" selected>choix du professeur</option>'+
							  '<option value='+ result[key][cle].professeur+' >'+ result[key][cle].professeur+' </option>'+							 			             			
							  '</select>'+ 	
							  
							  '<select name="nommat5_' +thecount+ '" id="nommat5_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
	                            '<option value="" selected>choix de la matière</option>'+
									'<option value="'+ result[key][cle].matiere+' ">'+ result[key][cle].matiere+'</option>'+								       			
								 '</select> '+
						  ' </div>';
                         
                         /* === SEPTIEME LIGNE DU TABLEAU ++++
                         tr1 +=' <div class="td" style="width:14%">'+														 
							'<select name="matprof6_' +thecount+ '" id="matprof6_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
							  '<option value="" selected>choix du professeur</option>'+
							  '<option value="'+ result[key][cle].professeur+'" >'+ result[key][cle].professeur+' </option>'+							 			             			
							  '</select>'+ 	
							  
							  '<select name="nommat6_' +thecount+ '" id="nommat6_' +thecount+ '" class="input-sm"  style="width:100%;text-align:center;">'+
	                            '<option value="" selected>choix de la matière</option>'+
									'<option value="'+ result[key][cle].matiere+' ">'+ result[key][cle].matiere+'</option>'+								       			
								 '</select> '+
						  ' </div>';
                         $("#tbody").append(tr1);
						
            		 }
            	 }
            	 
            	 
            	 if (key === '1') {
                     if (mode === 'niveaux') {
                         document.getElementById("classe").innerHTML = "";
                         var optionSelected = '<option value="" selected>----choix----</option>';
                         $("#classe").append(optionSelected);
                     }
                     for (var cle of Object.keys(result[key])) {
                         var option = '<option value="' + result[key][cle].nomClasse + '">' + result[key][cle].nomClasse + '</option>';
                         $("#classe").append(option);
                     }

                 }
            	
            	 if (key === '2') {
                     if (mode === 'classe') {
                         document.getElementById("nommat1_").innerHTML = "";
                         var optionSelected = '<option value="" selected>----choix----</option>';
                         $("#nommat1_").append(optionSelected);
                     }
                     for (var cle of Object.keys(result[key])) {
                         var option = '<option value="' + result[key][cle].matiere + '">' + result[key][cle].matiere + '</option>';
                         $("#nommat1_").append(option);
                     }

                 }
            	 
                
             }
             document.getElementById("messageAlertN").style.display = 'none';
         }
    });
}

*/


//---------------------------------------BERTHE OUSAMNE--------------------------------------------------------//

function sendNote() {
    var XHR = new XMLHttpRequest();
    alert("OOOH LALA une erreur s'est produite");
}

function iniInput(tag) {
    /*var reg = /[^0-9\.]+/g;*/
    var input = $(tag).val().replace(/[^0-9\.\,]+/g, '');
    var nbre = input.replace(".", "").length;

    var nt = input.replace(",", ".");

    var nNote = tag.id.substring(tag.id.lastIndexOf('T') + 1, tag.id.lastIndexOf('-'));
    var id = tag.id.substring(tag.id.lastIndexOf('-') + 1, tag.id.length);
    var coeff = parseInt(document.getElementById("coef" + nNote).value, 10);
    var maxim = parseInt(document.getElementById("sur" + nNote).value, 10);
    if (!coeff !== 0 && maxim !== 0) {
        if (nbre >= 1) {
            var flt = parseFloat(nt);
            if (flt < maxim + 1) {
                $(tag).val(input);
            }
            if (flt > maxim) {
                document.getElementById("AlP").innerHTML = "Veuillez saisir un nombre inferueur au maxi.";
                document.getElementById("msgN").style.display = "block";
                $(tag).val('');
            }
        }
        if (nbre < 1) $(tag).val('');
    } else {
        $(tag).val('');
    }
    updateMoyenneNote(parseInt(id, 10));
}

function ajInitInput(tag) {
    var input = $(tag).val().replace(/[^0-9\.\,-]+/g, '');
    var nNote = input.replace(",", ".");
    var id = tag.id.substring(tag.id.lastIndexOf('-') + 1, tag.id.length);
    var MF = document.getElementById("moy_m-" + id).value;
    var ss = 0;
    var nbr = parseFloat(nNote) + parseFloat(MF.replace(',', '.'));
    if (nbr <= 21) {
        if (parseFloat(nNote) <= 21) {
            if (parseFloat(nNote) >= 21) {
                ss = rounded('21');
            } else {
                var nbr = parseFloat(nNote) + parseFloat(MF.replace(',', '.'));
                if (nbr < 20) ss = rounded(nbr);
                else ss = rounded('20');
            }
            document.getElementById("moy_p-" + id).value = ss;
            // $(tag).val(rounded(nNote));
        } else {
            $(tag).val('');
            document.getElementById("moy_p-" + id).value = MF;
        }
    } else {
        if ($(tag).val() === '-') {
            $(tag).val(input);
        } else {
            $(tag).val('');
            document.getElementById("moy_p-" + id).value = MF;
        }
    }

}

function ajInitOutput(tag) {
    var input = $(tag).val().replace(/[^0-9\.\,-]+/g, '');
    var nNote = input.replace(",", ".");
    var id = tag.id.substring(tag.id.lastIndexOf('-') + 1, tag.id.length);
    var MF = document.getElementById("moy_m-" + id).value;
    var ids = document.getElementById("id-" + id).value;
    var mats = document.getElementById("mat-" + id).value;
    var ini = document.getElementById("ini-" + id).value;
    var ss = 0;
    var initP = parseFloat(ini) + parseFloat(nNote);
    if (parseFloat(nNote) <= 21) {
        if (parseFloat(nNote) >= 21) {
            ss = rounded('21');
        } else {
            var nbr = parseFloat(nNote) + parseFloat(MF.replace(',', '.'));
            if (nbr <= 20) ss = rounded(nbr);
            else ss = rounded('20');
        }
        var val = ss.replace(',', '.');
        $.ajax({
            type: 'post',
            url: 'xhr',
            dataType: 'json',
            data: {
                page: document.getElementById("page").value,
                periode: document.getElementById("periode").value,
                matiere: document.getElementById("matiere").value,
                mode: 'AJOUTER',
                plus: initP + '',
                my: val + '',
                id: ids + '',
                mat: mats + ''
            },
            success: function (result) {
                document.getElementById("moy_p-" + id).value = ss;
                document.getElementById("moy_m-" + id).value = ss;
                document.getElementById("ini-" + id).value = initP;
                $(tag).val('');
            }
        });
    }
}

function outInput(tag) {
    var input = $(tag).val().replace(/[^0-9\.\,-]+/g, '');
    var nNote = input.replace(",", ".");
    var id = tag.id.substring(tag.id.lastIndexOf('-') + 1, tag.id.length);
    var sur = tag.id.substring(2, tag.id.lastIndexOf('-'));

    var MF = document.getElementById("moy_n-" + id).value;
    var ids = document.getElementById("id-" + id).value;
    var mats = document.getElementById("mat-" + id).value;
    var plus = document.getElementById("ini-" + id).value;
    var maxi = document.getElementById("sur" + sur).value;
    var coef = document.getElementById("coef" + sur).value;
    var type = document.getElementById("type" + sur).value;

    var moyenneNote = MF.replace(',', '.');

    var ss = 0;
    var mys = parseFloat(plus) + parseFloat(moyenneNote);
    if (parseFloat(nNote) <= maxi && input !== '') {
        $.ajax({
            type: 'post',
            url: 'xhr',
            dataType: 'json',
            data: {
                page: 'saisi_notes',
                periode: document.getElementById("periode").value,
                matiere: document.getElementById("matiere").value,
                mode: 'OUPUT',
                my: moyenneNote + '',
                myp: mys + '',
                nts: nNote+'',
                num: sur+'',
                max: maxi+'',
                coe: coef+'',
                tpe: type+'',
                id: ids + '',
                mat: mats + ''
            },
            success: function (result) {
                $(tag).val(rounded(nNote));
            }
        });
    } else {
        // document.getElementById("AlP").innerHTML = "Veuillez saisir un nombre inferueur au maxi.";
        // document.getElementById("msgN").style.display = "block";
        $(tag).val('');
    }
}

function notg(tag) {
    var input = $(tag).val().replace(/[^0-9\.]+/g, '');
    var nbre = input.replace(".", "").length;
    var nNote = document.getElementById("nnote").value;
    var nb = nNote.substring(2, nNote.lastIndexOf('-'));
    var sur = document.getElementById("sur" + nb).value;
    var max = document.getElementById("Gmax").value;
    var signe = document.getElementById("signe").value;
    if (signe == 'globale') {
        if (max == '') {
            $(tag).val('');
        } else {
            if (nbre >= 1) {
                var flt = parseFloat(input);
                if (flt < parseInt(max, 10) + 1) $(tag).val(flt);
                if (flt > parseInt(max, 10)) $(tag).val('');
            } else $(tag).val('');
        }
    } else {
        if (signe == 'init') {
            $(tag).val('');
        } else {
            if (sur == '') {
                $(tag).val('');
            } else {
                if (nbre >= 1) {
                    var flt = parseFloat(input);
                    if (flt < parseInt(sur, 10) + 1) $(tag).val(flt);
                    if (flt > parseInt(sur, 10)) $(tag).val('');
                } else $(tag).val('');
            }
        }
    }
}

function iniInputs(tag) {
    var reg = /[^0-9\.]+/g;
    var input = $(tag).val().replace(/[^0-9\.]+/g, '');
    var nbre = input.split(".").length - 1;
    if (nbre <= 1) {
        var flt = parseFloat(input);
        if (flt < 10) {
            $(tag).val(input);
        }
        if (flt > 10) {
            $(tag).val(0);
        }

    }
    if (nbre > 1) $(tag).val(0);
}

function envoiNote() {
    document.getElementById("messageAlertN").style.display = 'block';
    var data = $("#former").serializeArray();
    data.push({name: $("#valider").attr("name"), value: $("#valider").val()});
    data.push({name: 'mode', value: 'former'});
    $.ajax({
        type: 'post',
        url: 'xhr',
        dataType: 'json',
        data: data,
        success: function (result) {
            document.getElementById("valider").style.display = 'none';
            document.getElementById("AlP").innerHTML = 'value.succees';
            document.getElementById("messageAlertN").style.display = 'none';
            document.getElementById("msgN").style.display = 'block';
        }
    });
    return false;
}

function envoiMoy() {
    document.getElementById("messageAlertN").style.display = 'block';
    var formNote = document.getElementById("formeMoy");
    formNote.addEventListener("submit", function (event) {
        event.preventDefault();
        var data = $("#formeMoy").serializeArray();
        data.push({name: $("#valider").attr("name"), value: $("#valider").val()});
        data.push({name: 'mode', value: 'formMY'});
        $.ajax({
            type: 'post',
            url: 'xhr',
            dataType: 'json',
            data: data,
            success: function (result) {
                document.getElementById("tbodyT1").innerHTML = "";
                var boucle = 0;
                $.each(result, function (index, value) {
                    console.log(result);
                    if (value.element == 'moyy') {
                        boucle += 1;
                        var ligne = "";
                        if (boucle % 2 == 0) {
                            ligne = "pair"
                        } else {
                            ligne = "impair"
                        }
                        var tr1 = '<div class="tr ' + ligne + '">';
                        tr1 += '<div class="td" style="width:15%;text-align:center;"><input type="hidden" name="id-' + boucle + '" value="' + value.id + '"><input type="hidden" name="mat-' + boucle + '" value="' + value.matriculeEc + '">' + value.matricule + '</div>';
                        tr1 += '<div class="td" style="width:40%;">' + value.nom + '</div>';
                        tr1 += '<div class="td" style="width:15%;"><input type="text" disabled id="moy-' + boucle + '" class="outL" value="' + value.MO + '"></div>';
                        tr1 += '<div class="td" style="width:15%;"><input type="hidden" id="ini-' + boucle + '" value="' + value.PL + '"/><input type="text" oninput="iniInput(this);" maxLength="5" id="NT-' + boucle + '" name="NT-' + boucle + '" class="outL" value="' + value.PL + '"></div>';
                        tr1 += '<div class="td" style="width:15%;"><input type="text" readonly id="moy_p-' + boucle + '" name="moy_p-' + boucle + '" class="outL" value="' + value.MOY + '"></div></div>';
                        $("#tbodyT1").append(tr1);
                    }
                    if (value.element == 'sc') {
                        document.getElementById("AlP").innerHTML = value.succes;
                    }
                });
                document.getElementById("messageAlertN").style.display = 'none';
                document.getElementById("msgN").style.display = 'block';
            }
        });
    });
}

function envoiModif() {
    document.getElementById("messageAlertN").style.display = 'block';
    var formNote = document.getElementById("formeModif");
    formNote.addEventListener("submit", function (event) {
        event.preventDefault();
        var data = $("#formeModif").serializeArray();
        data.push({name: $("#valider").attr("name"), value: $("#valider").val()});
        data.push({name: 'mode', value: 'formMD'});
        $.ajax({
            type: 'post',
            url: 'xhr',
            dataType: 'json',
            data: data,
            success: function (result) {
                setTimeout(function () {
                    document.getElementById("messageAlertN").style.display = 'none';
                }, 1000);
                document.getElementById("messageAlertN").style.display = 'none';
            }
        });
    });
}

function envoiAbsence() {
    document.getElementById("messageAlertN").style.display = 'block';
    var formAbs = document.getElementById("formAbs");
    formAbs.addEventListener("submit", function (event) {
        event.preventDefault();
        var data = $("#formAbs").serializeArray();
        data.push({name: $("#valider").attr("name"), value: $("#valider").val()});
        data.push({name: 'mode', value: 'formAB'});
        $.ajax({
            type: 'post',
            url: 'xhr',
            dataType: 'json',
            data: data,
            success: function (result) {
                document.getElementById("tbodyT1").innerHTML = "";
                document.getElementById("listDebut").innerHTML = "";
                document.getElementById("listFin").innerHTML = "";
                var boucle = 0;
                $.each(result, function (index, value) {
                    if (value.type == 'classe') {
                        boucle += 1;
                        var ligne = "";
                        if (boucle % 2 == 0) {
                            ligne = "pair"
                        } else {
                            ligne = "impair"
                        }
                        var tr1 = '<div class="tr ' + ligne + '">';
                        tr1 += '<div class="td" style="width:10%;text-align:center;"><input type="hidden" name="mat-' + boucle + '" value="' + value.matriculeEc + '">' + value.matricule + '</div>';
                        tr1 += '<div class="td" style="width:24%;">' + value.nom + '</div>';
                        tr1 += '<div class="td" style="width:8%;"><input type="time" name="lun' + boucle + '" value="' + value.lu + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:8%;"><input type="time" name="mar' + boucle + '" value="' + value.ma + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:8%;"><input type="time" name="mer' + boucle + '" value="' + value.me + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:8%;"><input type="time" name="jeu' + boucle + '" value="' + value.je + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:8%;"><input type="time" name="ven' + boucle + '" value="' + value.ve + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:8%;"><input type="time" name="sam' + boucle + '" value="' + value.sa + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:8%;text-align:center;"><input type="hidden" name="id-' + boucle + '" value="' + value.id + '" >' + value.to + '</div></div>';
                        $("#tbodyT1").append(tr1);
                    }
                    if (value.type == 'date') {
                        var lid = '<li onclick="changeDate(this);">' + value.debut + '</li>';
                        var lif = '<li onclick="changeDate(this);">' + value.fin + '</li>';
                        $("#listDebut").append(lid);
                        $("#listFin").append(lif);
                    }
                });
                document.getElementById("entier").value = boucle;
                document.getElementById("messageAlertN").style.display = 'none';
            }
        });
    });
}

function plusMoins(mode) {
    var eff = parseInt(document.getElementById("entier").value, 10);
    // var plus = parseInt(document.getElementById("note").value, 10);
    var plus = parseFloat((document.getElementById("note").value).replace(',', '.'));
    var signe = document.getElementById("signe").value;
    if (mode == 'numero') {
        var choix = document.getElementById("nnote").value;
        if (choix != 'choix') $(".rem").removeAttr("disabled");
        else $(".rem").attr("disabled", "disabled");
    }
    if (mode == 'globe') {
        var signe = document.getElementById("signe").value;
        if (signe == 'globale') {
            document.getElementById("boxing1").style.display = "none";
            document.getElementById("boxing2").style.display = "block";
        } else {
            document.getElementById("boxing1").style.display = "block";
            document.getElementById("boxing2").style.display = "none";
        }
    }
    if (mode == 'envoi') {
        var nNote = document.getElementById("nnote").value;
        var nbs = nNote.substring(2, nNote.lastIndexOf('-'));
        var sur = parseInt(document.getElementById("sur" + nbs).value, 10);
        var checks = parseInt(document.getElementById("Tos").value, 10);
        // if (checks > 0) {
        if (signe == 'globale') {
            var Gtype = document.getElementById("Gtype").value;
            var Gmax = document.getElementById("Gmax").value;
            var Gcoef = document.getElementById("Gcoef").value;
            if (Gtype == '') {
                document.getElementById("AlP").innerHTML = "Veuillez s�lectionner le type.";
                document.getElementById("msgN").style.display = "block";
                return false;
            }
            if (Gmax == '') {
                document.getElementById("AlP").innerHTML = "Veuillez s�lectionner la note maxi.";
                document.getElementById("msgN").style.display = "block";
                return false;
            }
            if (Gcoef == '') {
                document.getElementById("AlP").innerHTML = "Veuillez s�lectionner le coefficient.";
                document.getElementById("msgN").style.display = "block";
                return false;
            }
            if (isNaN(plus)) {
                document.getElementById("AlP").innerHTML = "Veuillez saisir la note.";
                document.getElementById("msgN").style.display = "block";
                return false;
            }
            document.getElementById("coef" + nbs).value = Gcoef;
            document.getElementById("type" + nbs).value = Gtype;
            document.getElementById("sur" + nbs).value = Gmax;
            for (var i = 1; i < eff + 1; i++) {
                // var check = document.getElementById("checks-" + i).checked;
                // if (check == true) {
                document.getElementById(nNote + '' + i).value = rounded(plus);
                updateMoyenneNote(i);
                // }
            }
        } else {
            if (signe == 'init') {

            } else {
                if (isNaN(plus)) {
                    document.getElementById("AlP").innerHTML = "Veuillez saisir la note.";
                    document.getElementById("msgN").style.display = "block";
                    return false;
                }
                for (var i = 1; i < eff + 1; i++) {
                    // var check = document.getElementById("checks-" + i).checked;
                    // if (check == true) {
                    // var nte = parseInt(document.getElementById(nNote + '' + i).value, 10);
                    var nte =parseFloat((document.getElementById(nNote + '' + i).value).replace(',', '.'));
                    if (!isNaN(nte)) {
                        if (signe == 'plus') {
                            if (nte > 0 && nte < 20.01) {
                                if ((nte + plus) > sur) {
                                    document.getElementById(nNote + '' + i).value = rounded(sur);
                                } else {
                                    document.getElementById(nNote + '' + i).value = rounded(nte + plus);
                                }
                            }
                        } else {
                            if ((nte - plus) < 0) {
                                document.getElementById(nNote + '' + i).value = rounded(0);
                            } else {
                                document.getElementById(nNote + '' + i).value = rounded(nte - plus);
                            }
                        }
                        updateMoyenneNote(i);
                    }
                    // }
                }
            }
        }
        document.getElementById("messageAlertN").style.display = 'block';
        var data = $("#former").serializeArray();
        data.push({name: 'mode', value: 'former'});
        $.ajax({
            type: 'post',
            url: 'xhr',
            dataType: 'json',
            data: data,
            success: function (result) {
                document.getElementById("AlP").innerHTML = 'value.succees';
                document.getElementById("messageAlertN").style.display = 'none';
                document.getElementById("msgN").style.display = 'block';
            }
        });
        document.getElementById("note").value = "";
        return false;
    }

    if (mode == 'addMoy') {
        var nn = 0;
        for (var i = 1; i < eff + 1; i++) {
            var MF = document.getElementById("moy_m-" + i).value;
            var nte = parseFloat(MF.replace(',', '.'));

            if (isNaN(nte)) nte = 0;

            if (signe == 'plus') {
                var nbr = nte + plus;
                var ss = 0;
                if (nbr <= 21) {
                    if (plus > 0) {
                        if (nbr < 20) ss = rounded(nbr);
                        else ss = rounded('20');

                        document.getElementById("NT-" + i).value = rounded(plus);
                        document.getElementById("moy_p-" + i).value = ss;
                    }
                }
            }
            if (signe == 'moins') {
                if (nte != 0 && nte > plus) {
                    var nbr = nte - plus;
                    var ss = 0;
                    if (plus > 0) {
                        ss = rounded(nbr);
                        document.getElementById("NT-" + i).value = '-' + rounded(plus);
                        document.getElementById("moy_p-" + i).value = ss;
                    }
                }
            }
            if (signe == 'init') {
                document.getElementById("NT-" + i).value = '';
                document.getElementById("moy_p-" + i).value = MF;
                nn = 1;
            }
        }
        if (nn === 0) document.getElementById("valider").style.display = "block";
        if (nn === 1) document.getElementById("valider").style.display = "none";
    }
    return false;
}

function getNote(mode) {
    document.getElementById("messageAlertN").style.display = 'block';
    var matss = document.getElementById("matiere").value;
    var classeA = document.getElementById("classe").value;
    if (mode === 'niveaux') {
        classeA = '';
        matss = '';
    }
    if (mode === 'classe') {
        matss = '';
    }
    $.ajax({
        type: 'post',
        url: 'xhr',
        dataType: 'json',
        data: {
            niveaux: document.getElementById("niveaux").value,
            classe: classeA + "",
            periode: document.getElementById("periode").value,
            matiere: matss + "",
            page: 'saisi_notes',
            chx: document.getElementById("prf").value + '',
            mode: mode
        }, success: function (result) {
            document.getElementById("tbodyT2").innerHTML = "";
            var boucle = 0;
            for (var key of Object.keys(result)) {
                if (key === '0') {
                    for (var cle of Object.keys(result[key])) {
                        boucle += 1;
                        var ligne = "";
                        if (boucle % 2 == 0) {
                            ligne = "pair"
                        } else {
                            ligne = "impair"
                        }
                        var disabe = (matss === "" ? "disabled" : "");

                        let nt1 = '', nt2 = '', nt3 = '', nt4 = '', nt5 = '', nt6 = '', nt7 = '', nt8 = '';
                        let nt9 = '', nt10 = '', nt11 = '', nt12 = '', nt13 = '', nt14 = '', nt15 = '';

                        let mm = '', mp = '', ps = '';

                        let cof1 = 1, cof2 = 1, cof3 = 1, cof4 = 1, cof5 = 1, cof6 = 1, cof7 = 1, cof8 = 1;
                        let cof9 = 1, cof10 = 1, cof11 = 1, cof12 = 1, cof13 = 1, cof14 = 1, cof15 = 1;

                        let tp1 = 'Devoir', tp2 = 'Devoir', tp3 = 'Devoir', tp4 = 'Devoir', tp5 = 'Devoir', tp6 = 'Devoir', tp7 = 'Devoir', tp8 = 'Devoir';
                        let tp9 = 'Devoir', tp10 = 'Devoir', tp11 = 'Devoir', tp12 = 'Devoir', tp13 = 'Devoir', tp14 = 'Devoir', tp15 = 'Devoir';

                        let max1 = 20, max2 = 20, max3 = 20, max4 = 20, max5 = 20, max6 = 20, max7 = 20, max8 = 20;
                        let max9 = 20, max10 = 20, max11 = 20, max12 = 20, max13 = 20, max14 = 20, max15 = 20;

                        var signed = '-';
                        if (result[key][cle].plus > 0 && result[key][cle].plus < 21) signed = '+';

                        var outPut = '';
                        var disp = 'none';

                        var mask = 'none';
                        if (matss !== "") {
                            disp = 'block';
                            // ==== COEF ====
                            cof1 = (result[key][cle].coef1 === undefined ? 1 : result[key][cle].coef1 == 0?1:result[key][cle].coef1);
                            cof2 = (result[key][cle].coef2 === undefined ? 1 : result[key][cle].coef2 == 0?1:result[key][cle].coef2);
                            cof3 = (result[key][cle].coef3 === undefined ? 1 : result[key][cle].coef3 == 0?1:result[key][cle].coef3);
                            cof4 = (result[key][cle].coef4 === undefined ? 1 : result[key][cle].coef4 == 0?1:result[key][cle].coef4);
                            cof5 = (result[key][cle].coef5 === undefined ? 1 : result[key][cle].coef5 == 0?1:result[key][cle].coef5);
                            cof6 = (result[key][cle].coef6 === undefined ? 1 : result[key][cle].coef6 == 0?1:result[key][cle].coef6);
                            cof7 = (result[key][cle].coef7 === undefined ? 1 : result[key][cle].coef7 == 0?1:result[key][cle].coef7);
                            cof8 = (result[key][cle].coef8 === undefined ? 1 : result[key][cle].coef8 == 0?1:result[key][cle].coef8);
                            cof9 = (result[key][cle].coef9 === undefined ? 1 : result[key][cle].coef9 == 0?1:result[key][cle].coefcoef9);
                            cof10 = (result[key][cle].coef10 === undefined ? 1 : result[key][cle].coef10 == 0?1:result[key][cle].coef10);
                            cof11 = (result[key][cle].coef11 === undefined ? 1 : result[key][cle].coef11 == 0?1:result[key][cle].coef11);
                            cof12 = (result[key][cle].coef12 === undefined ? 1 : result[key][cle].coef12 == 0?1:result[key][cle].coef12);
                            cof13 = (result[key][cle].coef13 === undefined ? 1 : result[key][cle].coef13 == 0?1:result[key][cle].coef13);
                            cof14 = (result[key][cle].coef14 === undefined ? 1 : result[key][cle].coef14 == 0?1:result[key][cle].coef14);
                            cof15 = (result[key][cle].coef15 === undefined ? 1 : result[key][cle].coef15 == 0?1:result[key][cle].coef15);
                            // ==== TYPE ====
                            tp1 = (result[key][cle].type1 === undefined ? 'Devoir' : result[key][cle].type1);
                            tp2 = (result[key][cle].type2 === undefined ? 'Devoir' : result[key][cle].type2);
                            tp3 = (result[key][cle].type3 === undefined ? 'Devoir' : result[key][cle].type3);
                            tp4 = (result[key][cle].type4 === undefined ? 'Devoir' : result[key][cle].type4);
                            tp5 = (result[key][cle].type5 === undefined ? 'Devoir' : result[key][cle].type5);
                            tp6 = (result[key][cle].type6 === undefined ? 'Devoir' : result[key][cle].type6);
                            tp7 = (result[key][cle].type7 === undefined ? 'Devoir' : result[key][cle].type7);
                            tp8 = (result[key][cle].type8 === undefined ? 'Devoir' : result[key][cle].type8);
                            tp9 = (result[key][cle].type9 === undefined ? 'Devoir' : result[key][cle].type9);
                            tp10 = (result[key][cle].type10 === undefined ? 'Devoir' : result[key][cle].type10);
                            tp11 = (result[key][cle].type11 === undefined ? 'Devoir' : result[key][cle].type11);
                            tp12 = (result[key][cle].type12 === undefined ? 'Devoir' : result[key][cle].type12);
                            tp13 = (result[key][cle].type13 === undefined ? 'Devoir' : result[key][cle].type13);
                            tp14 = (result[key][cle].type14 === undefined ? 'Devoir' : result[key][cle].type14);
                            tp15 = (result[key][cle].type15 === undefined ? 'Devoir' : result[key][cle].type15);
                            // ==== MAXI ====
                            max1 = (result[key][cle].sur1 === undefined ? 20 : result[key][cle].sur1 == 0?20:result[key][cle].sur1);
                            max2 = (result[key][cle].sur2 === undefined ? 20 : result[key][cle].sur2 == 0?20:result[key][cle].sur2);
                            max3 = (result[key][cle].sur3 === undefined ? 20 : result[key][cle].sur3 == 0?20:result[key][cle].sur3);
                            max4 = (result[key][cle].sur4 === undefined ? 20 : result[key][cle].sur4 == 0?20:result[key][cle].sur4);
                            max5 = (result[key][cle].sur5 === undefined ? 20 : result[key][cle].sur5 == 0?20:result[key][cle].sur5);
                            max6 = (result[key][cle].sur6 === undefined ? 20 : result[key][cle].sur6 == 0?20:result[key][cle].sur6);
                            max7 = (result[key][cle].sur7 === undefined ? 20 : result[key][cle].sur7 == 0?20:result[key][cle].sur7);
                            max8 = (result[key][cle].sur8 === undefined ? 20 : result[key][cle].sur8 == 0?20:result[key][cle].sur8);
                            max9 = (result[key][cle].sur9 === undefined ? 20 : result[key][cle].sur9 == 0?20:result[key][cle].sur9);
                            max10 = (result[key][cle].sur10 === undefined ? 20 : result[key][cle].sur10 == 0?20:result[key][cle].sur10);
                            max11 = (result[key][cle].sur11 === undefined ? 20 : result[key][cle].sur11 == 0?20:result[key][cle].sur11);
                            max12 = (result[key][cle].sur12 === undefined ? 20 : result[key][cle].sur12 == 0?20:result[key][cle].sur12);
                            max13 = (result[key][cle].sur13 === undefined ? 20 : result[key][cle].sur13 == 0?20:result[key][cle].sur13);
                            max14 = (result[key][cle].sur14 === undefined ? 20 : result[key][cle].sur14 == 0?20:result[key][cle].sur14);
                            max15 = (result[key][cle].sur15 === undefined ? 20 : result[key][cle].sur15 == 0?20:result[key][cle].sur15);

                            nt1 = rounded((result[key][cle].n1 !== undefined ? result[key][cle].n1 < 20.01?result[key][cle].n1:0 : 0));
                            nt2 = rounded((result[key][cle].n2 !== undefined ? result[key][cle].n2 < 20.01?result[key][cle].n2:0 : 0));
                            nt3 = rounded((result[key][cle].n3 !== undefined ? result[key][cle].n3 < 20.01?result[key][cle].n3:0 : 0));
                            nt4 = rounded((result[key][cle].n4 !== undefined ? result[key][cle].n4 < 20.01?result[key][cle].n4:0 : 0));
                            nt5 = rounded((result[key][cle].n5 !== undefined ? result[key][cle].n5 < 20.01?result[key][cle].n5:0 : 0));
                            nt6 = rounded((result[key][cle].n6 !== undefined ? result[key][cle].n6 < 20.01?result[key][cle].n6:0 : 0));
                            nt7 = rounded((result[key][cle].n7 !== undefined ? result[key][cle].n7 < 20.01?result[key][cle].n7:0 : 0));
                            nt8 = rounded((result[key][cle].n8 !== undefined ? result[key][cle].n8 < 20.01?result[key][cle].n8:0 : 0));
                            nt9 = rounded((result[key][cle].n9 !== undefined ? result[key][cle].n9 < 20.01?result[key][cle].n9:0 : 0));
                            nt10 = rounded((result[key][cle].n10 !== undefined ? result[key][cle].n10 < 20.01?result[key][cle].n10:0 : 0));
                            nt11 = rounded((result[key][cle].n11 !== undefined ? result[key][cle].n11 < 20.01?result[key][cle].n11:0 : 0));
                            nt12 = rounded((result[key][cle].n12 !== undefined ? result[key][cle].n12 < 20.01?result[key][cle].n12:0 : 0));
                            nt13 = rounded((result[key][cle].n13 !== undefined ? result[key][cle].n13 < 20.01?result[key][cle].n13:0 : 0));
                            nt14 = rounded((result[key][cle].n14 !== undefined ? result[key][cle].n14 < 20.01?result[key][cle].n14:0 : 0));
                            nt15 = rounded((result[key][cle].n15 !== undefined ? result[key][cle].n15 < 20.01?result[key][cle].n15:0 : 0));

                            mm = rounded((result[key][cle].moy !== undefined ? result[key][cle].moy < 20.01?result[key][cle].moy:0 : 0));
                            mp = rounded((result[key][cle].moyP !== undefined ? result[key][cle].moyP < 20.01?result[key][cle].moyP:0 : 0));

                            ps = rounded((result[key][cle].plus !== undefined ? result[key][cle].plus : 0));

                            if (result[key][cle].matiere !== undefined && result[key][cle].trim !== undefined) {
                                outPut = 'onblur="outInput(this);"';
                                disp = 'none'
                            }

                        }

                        if (matss !== '') {
                            var co1 = document.getElementById("coef1");
                            co1.options[8].value = cof1;
                            co1.options[8].label = cof1;
                            co1.options[8].selected = true;
                            var ty1 = document.getElementById("type1").options[3];
                            ty1.value = tp1;
                            ty1.label = tp1;
                            ty1.selected = true;
                            var ma1 = document.getElementById("sur1").options[6];
                            ma1.value = max1;
                            ma1.label = max1;
                            ma1.selected = true;

                            var co2 = document.getElementById("coef2").options[8];
                            co2.value = cof2;
                            co2.label = cof2;
                            co2.selected = true;
                            var ty2 = document.getElementById("type2").options[3];
                            ty2.value = tp2;
                            ty2.label = tp2;
                            ty2.selected = true;
                            var ma2 = document.getElementById("sur2").options[6];
                            ma2.value = max2;
                            ma2.label = max2;
                            ma2.selected = true;

                            var co3 = document.getElementById("coef3").options[8];
                            co3.value = cof3;
                            co3.label = cof3;
                            co3.selected = true;
                            var ty3 = document.getElementById("type3").options[3];
                            ty3.value = tp3;
                            ty3.label = tp3;
                            ty3.selected = true;
                            var ma3 = document.getElementById("sur3").options[6];
                            ma3.value = max3;
                            ma3.label = max3;
                            ma3.selected = true;

                            var co4 = document.getElementById("coef4").options[8];
                            co4.value = cof4;
                            co4.label = cof4;
                            co4.selected = true;
                            var ty4 = document.getElementById("type4").options[3];
                            ty4.value = tp4;
                            ty4.label = tp4;
                            ty4.selected = true;
                            var ma4 = document.getElementById("sur4").options[6];
                            ma4.value = max4;
                            ma4.label = max4;
                            ma4.selected = true;

                            var co5 = document.getElementById("coef5").options[8];
                            co5.value = cof5;
                            co5.label = cof5;
                            co5.selected = true;
                            var ty5 = document.getElementById("type5").options[3];
                            ty5.value = tp5;
                            ty5.label = tp5;
                            ty5.selected = true;
                            var ma5 = document.getElementById("sur5").options[6];
                            ma5.value = max5;
                            ma5.label = max5;
                            ma5.selected = true;

                            var co6 = document.getElementById("coef6").options[8];
                            co6.value = cof6;
                            co6.label = cof6;
                            co6.selected = true;
                            var ty6 = document.getElementById("type6").options[3];
                            ty6.value = tp6;
                            ty6.label = tp6;
                            ty6.selected = true;
                            var ma6 = document.getElementById("sur6").options[6];
                            ma6.value = max6;
                            ma6.label = max6;
                            ma6.selected = true;

                            var co7 = document.getElementById("coef7").options[8];
                            co7.value = cof7;
                            co7.label = cof7;
                            co7.selected = true;
                            var ty7 = document.getElementById("type7").options[3];
                            ty7.value = tp7;
                            ty7.label = tp7;
                            ty7.selected = true;
                            var ma7 = document.getElementById("sur7").options[6];
                            ma7.value = max7;
                            ma7.label = max7;
                            ma7.selected = true;

                            var co8 = document.getElementById("coef8").options[8];
                            co8.value = cof8;
                            co8.label = cof8;
                            co8.selected = true;
                            var ty8 = document.getElementById("type8").options[3];
                            ty8.value = tp8;
                            ty8.label = tp8;
                            ty8.selected = true;
                            var ma8 = document.getElementById("sur8").options[6];
                            ma8.value = max8;
                            ma8.label = max8;
                            ma8.selected = true;

                            var co9 = document.getElementById("coef9").options[8];
                            co9.value = cof9;
                            co9.label = cof9;
                            co9.selected = true;
                            var ty9 = document.getElementById("type9").options[3];
                            ty9.value = tp9;
                            ty9.label = tp9;
                            ty9.selected = true;
                            var ma9 = document.getElementById("sur9").options[6];
                            ma9.value = max9;
                            ma9.label = max9;
                            ma9.selected = true;

                            var co10 = document.getElementById("coef10").options[8];
                            co10.value = cof10;
                            co10.label = cof10;
                            co10.selected = true;
                            var ty10 = document.getElementById("type10").options[3];
                            ty10.value = tp10;
                            ty10.label = tp10;
                            ty10.selected = true;
                            var ma10 = document.getElementById("sur10").options[6];
                            ma10.value = max10;
                            ma10.label = max10;
                            ma10.selected = true;

                            var co11 = document.getElementById("coef11").options[8];
                            co11.value = cof11;
                            co11.label = cof11;
                            co11.selected = true;
                            var ty11 = document.getElementById("type11").options[3];
                            ty11.value = tp11;
                            ty11.label = tp11;
                            ty11.selected = true;
                            var ma11 = document.getElementById("sur11").options[6];
                            ma11.value = max11;
                            ma11.label = max11;
                            ma11.selected = true;

                            var co12 = document.getElementById("coef12").options[8];
                            co12.value = cof12;
                            co12.label = cof12;
                            co12.selected = true;
                            var ty12 = document.getElementById("type12").options[3];
                            ty12.value = tp12;
                            ty12.label = tp12;
                            ty12.selected = true;
                            var ma12 = document.getElementById("sur12").options[6];
                            ma12.value = max12;
                            ma12.label = max12;
                            ma12.selected = true;

                            var co13 = document.getElementById("coef13").options[8];
                            co13.value = cof13;
                            co13.label = cof13;
                            co13.selected = true;
                            var ty13 = document.getElementById("type13").options[3];
                            ty13.value = tp13;
                            ty13.label = tp13;
                            ty13.selected = true;
                            var ma13 = document.getElementById("sur13").options[6];
                            ma13.value = max13;
                            ma13.lab5el = max13;
                            ma13.selected = true;

                            var co14 = document.getElementById("coef14").options[8];
                            co14.value = cof14;
                            co14.label = cof14;
                            co14.selected = true;
                            var ty14 = document.getElementById("type14").options[3];
                            ty14.value = tp14;
                            ty14.label = tp14;
                            ty14.selected = true;
                            var ma14 = document.getElementById("sur14").options[6];
                            ma14.value = max14;
                            ma14.label = max14;
                            ma14.selected = true;

                            var co15 = document.getElementById("coef15").options[8];
                            co15.value = cof15;
                            co15.label = cof15;
                            co15.selected = true;
                            var ty15 = document.getElementById("type15").options[3];
                            ty15.value = tp15;
                            ty15.label = tp15;
                            ty15.selected = true;
                            var ma15 = document.getElementById("sur15").options[6];
                            ma15.value = max15;
                            ma15.label = max15;
                            ma15.selected = true;

                            if (result[key][cle].plus > 0 && result[key][cle].plus < 21 || result[key][cle].plus < 0) mask = 'block';
                        }
                        var tr1 = '<div class="tr ' + ligne + '">';
                        tr1 += '<div class="td stick1" style="width:1px;">' +
                            '<input type="checkbox" disabled checked onclick="checkable()" value="' + result[key][cle].eleve.matriculeEcole + '" id="checks-' + boucle + '"/>' +
                            '<input type="hidden" id="ini-' + boucle + '" name="ini-' + boucle + '" value="' + (result[key][cle].plus === undefined?0:result[key][cle].plus) + '"/>' +
                            '<input type="hidden" id="moy_p-' + boucle + '" name="moy_p-' + boucle + '" value="' + (result[key][cle].moyP === undefined?0:result[key][cle].moyP) + '"/>' +
                            '<input type="hidden" id="moy_n-' + boucle + '" name="moy_n-' + boucle + '" value="' + mm + '"/>' +
                            '<input type="hidden" id="moy_n_ini-' + boucle + '" name="moy_n_ini-' + boucle + '" value="' + (result[key][cle].moy === undefined?0:result[key][cle].moy) + '"/></div>';
                        tr1 += '<div class="td stick2" style="width:4px;text-align:center;">' +
                            '<input type="hidden" id="id-' + boucle + '" name="id-' + boucle + '" value="' + result[key][cle].id + '">' +
                            '<input type="hidden" id="mat-'+boucle+'" name="mat-' + boucle + '" value="' + result[key][cle].eleve.matriculeEcole + '">' + result[key][cle].eleve.matricule + '</div>';
                        tr1 += '<div class="td stick3" style="width:200px;padding-left: 15px;">' + result[key][cle].eleve.nom + ' ' + result[key][cle].eleve.prenom + '</div>';
                        tr1 += '<div class="td text-center stick4" style="width:2px;"><span id="MOY-' + boucle + '">' + (mm === '21,00'?'':mm === '0,00'?'':mm) + '</span><span class="pls ' + mask + '">' + signed + '' + ps + '</span></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur1-ini" value="'+max1+'"/><input type="text" ' + disabe + ' value="' + (nt1 === '0,00'?'':nt1) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT1-' + boucle + '" name="NT1-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur2-ini" value="'+max2+'"/><input type="text" ' + disabe + ' value="' + (nt2 === '0,00'?'':nt2) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT2-' + boucle + '" name="NT2-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur3-ini" value="'+max3+'"/><input type="text" ' + disabe + ' value="' + (nt3 === '0,00'?'':nt3) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT3-' + boucle + '" name="NT3-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur4-ini" value="'+max4+'"/><input type="text" ' + disabe + ' value="' + (nt4 === '0,00'?'':nt4) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT4-' + boucle + '" name="NT4-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur5-ini" value="'+max5+'"/><input type="text" ' + disabe + ' value="' + (nt5 === '0,00'?'':nt5) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT5-' + boucle + '" name="NT5-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur6-ini" value="'+max6+'"/><input type="text" ' + disabe + ' value="' + (nt6 === '0,00'?'':nt6) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT6-' + boucle + '" name="NT6-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur7-ini" value="'+max7+'"/><input type="text" ' + disabe + ' value="' + (nt7 === '0,00'?'':nt7) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT7-' + boucle + '" name="NT7-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur8-ini" value="'+max8+'"/><input type="text" ' + disabe + ' value="' + (nt8 === '0,00'?'':nt8) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT8-' + boucle + '" name="NT8-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur9-ini" value="'+max9+'"/><input type="text" ' + disabe + ' value="' + (nt9 === '0,00'?'':nt9) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT9-' + boucle + '" name="NT9-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur10-ini" value="'+max10+'"/><input type="text" ' + disabe + ' value="' + (nt10 === '0,00'?'':nt10) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT10-' + boucle + '" name="NT10-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur11-ini" value="'+max11+'"/><input type="text" ' + disabe + ' value="' + (nt11 === '0,00'?'':nt11) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT11-' + boucle + '" name="NT11-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur12-ini" value="'+max12+'"/><input type="text" ' + disabe + ' value="' + (nt12 === '0,00'?'':nt12) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT12-' + boucle + '" name="NT12-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur13-ini" value="'+max13+'"/><input type="text" ' + disabe + ' value="' + (nt13 === '0,00'?'':nt13) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT13-' + boucle + '" name="NT13-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur14-ini" value="'+max14+'"/><input type="text" ' + disabe + ' value="' + (nt14 === '0,00'?'':nt14) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT14-' + boucle + '" name="NT14-' + boucle + '" class="outL"></div>';
                        tr1 += '<div class="td" style="width:10px;"><input type="hidden" id="sur15-ini" value="'+max15+'"/><input type="text" ' + disabe + ' value="' + (nt15 === '0,00'?'':nt15) + '" oninput="iniInput(this);" '+outPut+'  maxLength="5" id="NT15-' + boucle + '" name="NT15-' + boucle + '" class="outL"></div></div>';
                        $("#tbodyT2").append(tr1);

                    }
                    document.getElementById("entier").value = boucle;
                    if (matss !== '') {
                        $("#nnote").removeAttr("disabled");
                        // $("#valider").removeAttr("disabled");
                    } else {
                        $("#nnote").attr("disabled", "disabled");
                        $(".rem").attr("disabled", "disabled");
                        // $("#valider").attr("disabled", "disabled");
                    }
                    document.getElementById("valider").style.display = disp;

                    document.getElementById("entier").value = boucle;
                    document.getElementById("To").innerHTML = 0;
                    document.getElementById("To1").innerHTML = boucle;
                }
                if (key === '1') {
                    if (mode === 'niveaux') {
                        document.getElementById("classe").innerHTML = "";
                        var optionSelected = '<option value="" selected>----choix----</option>';
                        $("#classe").append(optionSelected);
                    }
                    for (var cle of Object.keys(result[key])) {
                        var option = '<option value="' + result[key][cle].nomClasse + '">' + result[key][cle].nomClasse + '</option>';
                        $("#classe").append(option);
                    }

                }
                if (key === '2') {
                    if (mode === 'niveaux' || mode === 'classe') {
                        document.getElementById("matiere").innerHTML = "";
                        var optionSelected = '<option value="" selected>----choix----</option>';
                        $("#matiere").append(optionSelected);
                    }
                    for (var cle of Object.keys(result[key])) {
                        var option = '<option value="' + result[key][cle] + '">' + result[key][cle] + '</option>';
                        $("#matiere").append(option);
                    }
                }
            }
            document.getElementById("messageAlertN").style.display = 'none';
        }
    });

}

function changeMoyenne(mode) {
    document.getElementById("messageAlertN").style.display = 'block';
    var matss = document.getElementById("matiere").value;
    var classeA = document.getElementById("classe").value;
    if (mode === 'niveaux') {
        classeA = '';
        matss = '';
    }
    if (mode === 'classe') {
        matss = '';
    }
    $.ajax({
        type: 'post',
        url: 'xhr',
        dataType: 'json',
        data: {
            niveaux: document.getElementById("niveaux").value,
            classe: classeA + "",
            periode: document.getElementById("periode").value,
            matiere: matss + "",
            page: document.getElementById("page").value,
            mode: mode
        }, success: function (result) {
            document.getElementById("tbodyT1").innerHTML = "";
            var boucle = 0;
            for (var key of Object.keys(result)) {
                if (key === '0') {
                    for (var cle of Object.keys(result[key])) {
                        boucle += 1;
                        var ligne = "";
                        if (boucle % 2 == 0) {
                            ligne = "pair"
                        } else {
                            ligne = "impair"
                        }
                        var disabe = (matss === "" ? "disabled" : "");

                        let mr = rounded((matss !== "" ? result[key][cle].moy : 0));
                        let mm = rounded((matss !== "" ? result[key][cle].moyP : 0));

                        var tr1 = '<div class="tr ' + ligne + '">';
                        tr1 += '<div class="td" style="width:15%;text-align:center;"><input type="hidden" id="id-' + boucle + '" name="id-' + boucle + '" value="' + result[key][cle].id + '"><input type="hidden" id="mat-' + boucle + '" name="mat-' + boucle + '" value="' + result[key][cle].eleve.matriculeEcole + '">' + result[key][cle].eleve.matricule + '</div>';
                        tr1 += '<div class="td px-3" style="width:40%;padding-left: 5px;">' + result[key][cle].eleve.nom + ' ' + result[key][cle].eleve.prenom + '</div>';
                        tr1 += '<div class="td" style="width:15%;"><input type="text" disabled id="moy-' + boucle + '" class="outL" value="' + mr + '"></div>';
                        tr1 += '<div class="td" style="width:10%;"><input type="text" disabled id="moy_m-' + boucle + '" class="outL" value="' + mm + '"></div>';
                        tr1 += '<div class="td" style="width:10%;"><input type="hidden" id="ini-' + boucle + '" name="ini-' + boucle + '" value="' + result[key][cle].plus + '"/><input type="text" ' + disabe + ' oninput="ajInitInput(this);" onblur="ajInitOutput(this);" maxLength="5" id="NT-' + boucle + '" name="NT-' + boucle + '" class="outL" value=""></div>';
                        tr1 += '<div class="td" style="width:10%;"><input type="text" readonly id="moy_p-' + boucle + '" name="moy_p-' + boucle + '" class="outL" value="' + mm + '"></div></div>';
                        $("#tbodyT1").append(tr1);
                    }
                    document.getElementById("entier").value = boucle;
                    if (matss !== '') $(".rem").removeAttr("disabled");
                    else $(".rem").attr("disabled", "disabled");
                }
                if (key === '1') {
                    if (mode === 'niveaux') {
                        document.getElementById("classe").innerHTML = "";
                        var optionSelected = '<option value="" selected>----choix----</option>';
                        $("#classe").append(optionSelected);
                    }
                    for (var cle of Object.keys(result[key])) {
                        var option = '<option value="' + result[key][cle].nomClasse + '">' + result[key][cle].nomClasse + '</option>';
                        $("#classe").append(option);
                    }

                }
                ;
                if (key === '2') {
                    if (mode === 'niveaux' || mode === 'classe') {
                        document.getElementById("matiere").innerHTML = "";
                        var optionSelected = '<option value="" selected>----choix----</option>';
                        $("#matiere").append(optionSelected);
                    }
                    for (var cle of Object.keys(result[key])) {
                        var option = '<option value="' + result[key][cle] + '">' + result[key][cle] + '</option>';
                        $("#matiere").append(option);
                    }
                }
                ;

            }
            document.getElementById("messageAlertN").style.display = 'none';
        }
    });
}

function moyenneMat(mode) {
    document.getElementById("messageAlertN").style.display = 'block';
    var matss = document.getElementById("matiere").value;
    var data = $("#formeMoy").serializeArray();
    data.push({name: $("#valider").attr("name"), value: $("#valider").val()});
    data.push({name: 'page', value: '' + document.getElementById("page").value});
    data.push({name: 'mode', value: '' + mode});
    $.ajax({
        type: 'post',
        url: 'xhr',
        dataType: 'json',
        data: data,
        success: function (result) {
            document.getElementById("tbodyT1").innerHTML = "";
            var boucle = 0;
            for (var key of Object.keys(result)) {
                if (key === '0') {
                    for (var cle of Object.keys(result[key])) {
                        boucle += 1;
                        var ligne = "";
                        if (boucle % 2 == 0) {
                            ligne = "pair"
                        } else {
                            ligne = "impair"
                        }
                        var disabe = (matss === "" ? "disabled" : "");

                        let mr = rounded((matss !== "" ? result[key][cle].moy : 0));
                        let mm = rounded((matss !== "" ? result[key][cle].moyP : 0));

                        var tr1 = '<div class="tr ' + ligne + '">';
                        tr1 += '<div class="td" style="width:15%;text-align:center;"><input type="hidden" id="id-' + boucle + '" name="id-' + boucle + '" value="' + result[key][cle].id + '"><input type="hidden" id="mat-' + boucle + '" name="mat-' + boucle + '" value="' + result[key][cle].eleve.matriculeEcole + '">' + result[key][cle].eleve.matricule + '</div>';
                        tr1 += '<div class="td px-3" style="width:40%;padding-left: 5px;">' + result[key][cle].eleve.nom + ' ' + result[key][cle].eleve.prenom + '</div>';
                        tr1 += '<div class="td" style="width:15%;"><input type="text" disabled id="moy-' + boucle + '" class="outL" value="' + mr + '"></div>';
                        tr1 += '<div class="td" style="width:10%;"><input type="text" disabled id="moy_m-' + boucle + '" class="outL" value="' + mm + '"></div>';
                        tr1 += '<div class="td" style="width:10%;"><input type="hidden" id="ini-' + boucle + '" name="ini-' + boucle + '" value="' + result[key][cle].plus + '"/><input type="text" ' + disabe + ' oninput="ajInitInput(this);" onblur="ajInitOutput(this);" maxLength="5" id="NT-' + boucle + '" name="NT-' + boucle + '" class="outL" value=""></div>';
                        tr1 += '<div class="td" style="width:10%;"><input type="text" readonly id="moy_p-' + boucle + '" name="moy_p-' + boucle + '" class="outL" value="' + mm + '"></div></div>';
                        $("#tbodyT1").append(tr1);
                    }
                    document.getElementById("entier").value = boucle;
                }

            }
            // document.getElementById("signe").textContent = 'PLUS';
            // document.getElementById("note").textContent = '0';
            document.getElementById("valider").style.display = "none";
            document.getElementById("messageAlertN").style.display = 'none';
        }
    });
    return false;
}

function getAffichage(mode) {
    document.getElementById("messageAlertN").style.display = 'block';
    $.ajax({
        type: 'post',
        url: 'xhr',
        dataType: 'json',
        data: {
            niveaux: document.getElementById("niveaux").value,
            classe: document.getElementById("classe").value,
            periode: document.getElementById("periode").value,
            matiere: document.getElementById("matiere").value,
            page: document.getElementById("page").value,
            mode: mode
        }, success: function (result) {
            document.getElementById("tbodyT1").innerHTML = "";
            var option = '<option value="" selected>----choix----</option>';
            var boucle = 0;
            $.each(result, function (index, value) {
                if (mode == 'niveaux') {
                    option += '<option value="' + value.libelle + '">' + value.libelle + '</option>';
                }
                if (mode == 'classe') {
                    if (value.element == "eleve") {
                        boucle += 1;
                        var ligne = "";
                        if (boucle % 2 == 0) {
                            ligne = "pair"
                        } else {
                            ligne = "impair"
                        }
                        var tr1 = '<div class="tr ' + ligne + '">';
                        tr1 += '<div class="td" style="width:10%;text-align:center;"><input type="hidden" name="mat-' + boucle + '" value="' + value.matriculeEc + '">' + value.matricule + '</div>';
                        tr1 += '<div class="td" style="width:26%;">' + value.nom + '</div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:4%;"><input type="text" disabled class="outStyle" ></div></div>';
                        $("#tbodyT1").append(tr1);
                    }
                    if (value.element == "matiere") {
                        option += '<option value="' + value.matieres + '">' + value.matieres + '</option>';
                    }
                }
                if (mode == 'matiere' || mode == 'periode') {
                    boucle += 1;
                    var ligne = "";
                    if (boucle % 2 == 0) {
                        ligne = "pair"
                    } else {
                        ligne = "impair"
                    }
                    var tr1 = '<div class="tr ' + ligne + '">';
                    tr1 += '<div class="td" style="width:10%;text-align:center;"><input type="hidden" name="mat-' + boucle + '" value="' + value.matriculeEc + '">' + value.matricule + '</div>';
                    tr1 += '<div class="td" style="width:26%;">' + value.nom + '</div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N1 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N2 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N3 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N4 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N5 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N6 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N7 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N8 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N9 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N10 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N11 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N12 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N13 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N14 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.N15 + '" disabled class="outStyle" ></div>';
                    tr1 += '<div class="td" style="width:4%;"><input type="text" value="' + value.MOY + '" disabled class="outStyle" ></div></div>';
                    $("#tbodyT1").append(tr1);
                }
            });
            document.getElementById("entier").value = boucle;
            if (mode == 'niveaux') {
                document.getElementById("matiere").innerHTML = "";
                document.getElementById("classe").innerHTML = option;
            }
            if (mode == 'classe') {
                document.getElementById("matiere").innerHTML = option;
            }
            document.getElementById("messageAlertN").style.display = 'none';
        }
    });

}

function getAbsences(mode) {
    document.getElementById("messageAlertN").style.display = 'block';
    $.ajax({
        type: 'post',
        url: 'xhr',
        dataType: 'json',
        data: {
            niveaux: document.getElementById("niveaux").value,
            classe: document.getElementById("classe").value,
            periode: document.getElementById("periode").value,
            page: document.getElementById("page").value,
            mode: mode
        }, success: function (result) {
            document.getElementById("tbodyT1").innerHTML = "";
            document.getElementById("listDebut").innerHTML = "";
            document.getElementById("listFin").innerHTML = "";
            var boucle = 0;
            $.each(result, function (index, value) {
                if (mode == 'classe') {
                    if (value.type == 'classe') {
                        boucle += 1;
                        var ligne = "";
                        if (boucle % 2 == 0) {
                            ligne = "pair"
                        } else {
                            ligne = "impair"
                        }
                        var tr1 = '<div class="tr ' + ligne + '">';
                        tr1 += '<div class="td" style="width:10%;text-align:center;"><input type="hidden" name="mat-' + boucle + '" value="' + value.matriculeEc + '">' + value.matricule + '</div>';
                        tr1 += '<div class="td" style="width:24%;">' + value.nom + '</div>';
                        tr1 += '<div class="td" style="width:7%;"><input type="time" name="lun' + boucle + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:7%;"><input type="time" name="mar' + boucle + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:7%;"><input type="time" name="mer' + boucle + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:7%;"><input type="time" name="jeu' + boucle + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:7%;"><input type="time" name="ven' + boucle + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:7%;"><input type="time" name="sam' + boucle + '" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:7%;"><input type="text" disabled placeholder="00:00" class="outStyle" ></div>';
                        tr1 += '<div class="td" style="width:7%;"><input type="time" name="jus' + boucle + '" class="outStyle" ></div></div>';
                        $("#tbodyT1").append(tr1);
                    }
                    if (value.type == 'date') {
                        var lid = '<li onclick="changeDate(this);">' + value.debut + '</li>';
                        var lif = '<li onclick="changeDate(this);">' + value.fin + '</li>';
                        $("#listDebut").append(lid);
                        $("#listFin").append(lif);
                    }

                }
                if (mode == 'classeAbs') {
                    boucle += 1;
                    var ligne = "";
                    if (boucle % 2 == 0) {
                        ligne = "pair"
                    } else {
                        ligne = "impair"
                    }
                    var tr1 = '<div class="tr ' + ligne + '">';
                    tr1 += '<div class="td" style="width:10%;text-align:center;"><input type="hidden" name="mat-' + boucle + '" value="' + value.matriculeEc + '">' + value.matricule + '</div>';
                    tr1 += '<div class="td" style="width:24%;">' + value.nom + '</div>';
                    tr1 += '<div class="td" style="width:7%;text-align:center;">' + value.lu + '</div>';
                    tr1 += '<div class="td" style="width:7%;text-align:center;">' + value.ma + '</div>';
                    tr1 += '<div class="td" style="width:7%;text-align:center;">' + value.me + '</div>';
                    tr1 += '<div class="td" style="width:7%;text-align:center;">' + value.je + '</div>';
                    tr1 += '<div class="td" style="width:7%;text-align:center;">' + value.ve + '</div>';
                    tr1 += '<div class="td" style="width:7%;text-align:center;">' + value.sa + '</div>';
                    tr1 += '<div class="td" style="width:7%;text-align:center;"><input type="hidden" name="id-' + boucle + '" value="' + value.id + '" >' + value.to + '</div>';
                    tr1 += '<div class="td" style="width:7%;text-align:center;">' + value.ju + '</div>';
                    $("#tbodyT1").append(tr1);
                }
            });
            document.getElementById("entier").value = boucle;
            if (mode == 'classe') {
                document.getElementById("debut").value = "";
                document.getElementById("fin").value = "";
            }
            document.getElementById("messageAlertN").style.display = 'none';
        }
    });
}

function changeCoMa(tag, mod) {
    var eff = parseInt(document.getElementById("entier").value, 10);
    if (mod === 'sur' || mod === 'coef') {
        var nb = '';
        if (mod === 'sur') nb = tag.id.substring(3, tag.id.length);
        if (mod === 'coef') nb = tag.id.substring(4, tag.id.length);
        // var maximIni = parseInt(document.getElementById(tag.id + '-ini').value, 10);
        // var maxim = parseInt(document.getElementById(tag.id + '').value, 10);
        maximIni = parseInt(document.getElementById('sur'+ nb + '-ini').value, 10);
        maxim = parseInt(document.getElementById('sur'+ nb).value, 10);

        var coeff = parseInt(document.getElementById('coef' + nb).value, 10);
        var typer = parseInt(document.getElementById('type' + nb).value, 10);
        for (var i = 1; i < eff + 1; i++) {
            var ids = document.getElementById("id-" + i).value;
            var mats = document.getElementById("mat-" + i).value;
            var plus = document.getElementById("ini-" + i).value;
            var nte = document.getElementById('NT' + nb + '-' + i).value;
            var ntes = parseFloat(nte.replace(',', '.'));
            var ntt = ntes;
            if (mod === 'sur'){
                if (ntes > 0) {
                    ntt = (ntes * maxim) / maximIni;
                    document.getElementById('NT' + nb + '-' + i).value = rounded(ntt);
                }else {
                    document.getElementById('NT' + nb + '-' + i).value = '';
                }
            }

            updateMoyenneNote(i);

            var ntss = document.getElementById('NT' + nb + '-' + i).value;
            var MF = document.getElementById("moy_n-" + i).value;

            var moyenneNote = MF.replace(',', '.');

            var mys = parseFloat(plus) + parseFloat(moyenneNote);

            $.ajax({
                type: 'post',
                url: 'xhr',
                dataType: 'json',
                data: {
                    page: 'saisi_notes',
                    periode: document.getElementById("periode").value,
                    matiere: document.getElementById("matiere").value,
                    mode: 'OUPUTSC',
                    my: moyenneNote + '',
                    myp: mys + '',
                    nts: ntss.replace(',', '.')+'',
                    num: nb+'',
                    max: maxim+'',
                    coe: coeff+'',
                    tpe: typer+'',
                    id: ids + '',
                    mat: mats + '',
                    md: mod + ''
                },
                success: function (result) {
                    // $(tag).val(rounded(nNote));
                }
            });
        }
        if (mod === 'sur') document.getElementById(tag.id + '-ini').value = maxim;
    }
    if (mod === 'type') {
        var nb = tag.id.substring(4, tag.id.length);
        var typer = document.getElementById('type' + nb).value;
        for (var i = 1; i < eff + 1; i++) {
            var mats = document.getElementById("mat-" + i).value;
            var ids = document.getElementById("id-" + i).value;
            console.log('========== '+ mats);
            $.ajax({
                type: 'post',
                url: 'xhr',
                dataType: 'json',
                data: {
                    page: 'saisi_notes',
                    periode: document.getElementById("periode").value,
                    matiere: document.getElementById("matiere").value,
                    mode: 'OUPUTTY',
                    num: nb + '',
                    tpe: typer + '',
                    mat: mats + '',
                    id: ids + ''
                },
                success: function (result) {
                    //console.log('OK ========== '+ ids);
                }
            });
        }
    }
}

function updateMoyenneNote(nbNote) {
    var moy = 0, Qt = 0, Div = 0;
    // var check = document.getElementById("checks-" + nbNote).checked;
    // if (check == true) {
    for (var i = 1; i < 16; i++) {


        var coeff = parseInt(document.getElementById("coef" + i).value, 10);
        var maxim = parseInt(document.getElementById("sur" + i).value, 10);

        var nte = document.getElementById('NT' + i + '-' + nbNote).value;
        //var plus = document.getElementById('ini-' + nbNote).value;

        var ntes = parseFloat(nte.replace(',', '.'));
        // var pl = parseFloat(plus.replace(',', '.'));

        if (coeff !== 0 && maxim !== 0) {
            if (ntes > 0) {

                var q = ntes * coeff;
                var d = coeff * (maxim / 20);
                Qt += q;
                Div += d;
            }
        }
    }
    //console.log("==== " + Qt + " ===== " + Div);
    moy = Qt / Div;

    var plus = document.getElementById('ini-' + nbNote).value;
    var mni = document.getElementById('moy_n_ini-' + nbNote).value;

    moy = (Div == 0 ? mni : moy);

    var mmp = rounded(moy + plus);
    document.getElementById('MOY-' + nbNote).innerHTML = rounded(moy);
    document.getElementById('moy_n-' + nbNote).value = parseFloat(moy);
    document.getElementById('moy_p-' + nbNote).value = parseFloat(mmp);
    // }
}

function rounded(num) {
    let number = "" + num;
    let retour = number;
    if (number.lastIndexOf('.') !== -1) {
        let nb = number + "0";
        var nt = nb.substring(0, number.lastIndexOf('.') + 3);
        retour = nt.replace('.', ',');
    } else {
        retour = number + ',00';
    }
    return retour;
}