$(document).ready(function(){
    var tht = '<div class="tht">Selectionnez une classe</div>';
    document.getElementById("ctxt").innerHTML = tht;
    $.ajax({
        type : 'post',
        url : 'cahier_texte',
        dataType: 'json',
        data : {
            matProf: document.getElementById("matPro").value,
            crud: 'windowLoad'
        },
        success : function(result){
            var btns = '<option class="firstOption" value="" selected></option>';
            var maDiv = '';
            var nc = 0, vn = 0;
            var year = new Date().getFullYear();
            $.each(result, function(index, value){
                if (value.ch == 'nv') {
                    vn += 1;
                    btns += '<option value="'+value.choix1+'">'+value.choix1+'</option>';
                }
                if (value.ch == 'tb') {
                    document.getElementById("divJour").innerHTML = value.uls;
                    document.getElementById("jourSemaine").innerHTML = value.date;
                    document.getElementById("getDay").value = value.date;
                    document.getElementById("day").value = value.jourSemaine;
                    document.getElementById("jourSemaine").style.color = value.couleur;
                    document.getElementById("sem").innerHTML = value.semaineDu;
                    document.getElementById("week").value = value.semaineDu;
                    document.getElementById("getDate").value = value.getDate;

                    year = value.getDate.substring(0,  value.getDate.indexOf('-'))
                }
                if (value.ch == 'cha') {
                    nc =+ 1;
                    maDiv += '<div id="saisirChapitre-' + index + '" class="saisirChapitre" style="border-left: 4px solid ' + value.couleur + ';">';
                    maDiv += '<div class="cardText">';
                    maDiv += '<h4 style="border-bottom: 2px dotted ' + value.couleur + '; color:' + value.couleur + '; ">' + value.classe + '</h4>';
                    maDiv += '</div>';
                    maDiv += '<div class="cardTexts">';
                    maDiv += '<div class="matPr" id="matPr-' + index + '">';
                    maDiv += '<span class="matieres" id="matieres">' + value.matiere + '</span>';
                    maDiv += '</div>';
                    maDiv += '<div id="chps-' + index + '" class="chps">';
                    maDiv += '<h4 style="color: ' + value.couleur + ';">' + value.chapitre + '</h4>';
                    maDiv += '<P>' + value.comment + '</P>';
                    maDiv += '</div>';
                    maDiv +='<span class="AT">';
                    maDiv +='<button id="btUpdate-'+value.id+'" class="btn btn-sm btn-outline-info" onclick="openBox(this)"><i class="fa fa-pencil"></i></button>&nbsp;';
                    maDiv +='<button id="btDelete-'+value.id+'" class="btn btn-sm btn-outline-danger" onclick="delForm(this)"><i class="fa fa-trash"></i></button>';
                    maDiv +='</span>';
                    maDiv += '</div>';
                    maDiv += '</div>';
                    maDiv += '<div style="padding: 15px;"></div>';
                }
            });
            //if(vn > 0) document.getElementById("niveau").innerHTML = btns;
            document.getElementById("setAn").value = year;
            if(nc > 0){
                document.getElementById("ctxt").innerHTML = maDiv;
            }else{
                document.getElementById("ctxt").innerHTML = tht;
            }

        }
    });

    $("#airdatepicker").datepicker({
        language: "fr",
        view: "months",
        minView: "months",
        dateFormat: "MM yyyy",
        position: "bottom right",
        onSelect: function (fd, d, picker) {
            var dte = document.getElementById('airdatepicker').value;
            if (document.getElementById("p1").value == ''){
                document.getElementById("AlP").innerHTML = "Veuillez selectionner la classe";
                document.getElementById("msgN").style.display = "block";
                return false;
            }
            $.ajax({
                type: 'post',
                url: 'cahier_texte',
                dataType: 'json',
                data: {
                    classe: document.getElementById("p1").value,
                    crud: 'boutons',
                    matProf: document.getElementById("matPro").value,
                    chx: 'prof',
                    send: 'getMoisYear',
                    moisAn: dte + '',
                },
                success: function (result) {
                    var maDiv = '';
                    $.each(result, function(index, value) {
                        if (value.ch == 'cha') {
                            maDiv += '<div id="saisirChapitre-' + index + '" class="saisirChapitre" style="border-left: 4px solid ' + value.couleur + ';">';
                            maDiv += '<div class="cardText">';
                            maDiv += '<h4 style="border-bottom: 2px dotted ' + value.couleur + '; color:' + value.couleur + '; "> PROF: ' + value.prof + '</h4>';
                            maDiv += '</div>';
                            maDiv += '<div class="cardTexts">';
                            maDiv += '<div class="matPr" id="matPr-' + index + '">';
                            maDiv += '<span class="matieres" id="matieres">' + value.matiere + '</span>';
                            maDiv += '</div>';
                            maDiv += '<div id="chps-' + index + '" class="chps">';
                            maDiv += '<h4 style="color: ' + value.couleur + ';">' + value.chapitre + '</h4>';
                            maDiv += '<P>' + value.comment + '</P>';
                            maDiv += '</div>';
                            maDiv += '<span class="AT">';
                            maDiv += '<button id="btUpdate-' + value.id + '" class="btn btn-sm btn-outline-info" disabled><i class="fa fa-pencil"></i></button>&nbsp;';
                            maDiv += '<button id="btDelete-' + value.id + '" class="btn btn-sm btn-outline-danger" disabled><i class="fa fa-trash"></i></button>';
                            maDiv += '</span>';
                            maDiv += '</div>';
                            maDiv += '</div>';
                            maDiv += '<div style="padding: 15px;"></div>';
                        }
                        if (value.ch == 'tb') {
                            document.getElementById("divJour").innerHTML = value.uls;
                            document.getElementById("jourSemaine").innerHTML = value.date;
                            document.getElementById("getDay").value = value.date;
                            document.getElementById("day").value = value.jourSemaine;
                            document.getElementById("jourSemaine").style.color = value.couleur;
                            document.getElementById("sem").innerHTML = value.semaineDu;
                            document.getElementById("week").value = value.semaineDu;
                            document.getElementById("getDate").value = value.getDate;
                        }
                    });

                    document.getElementById("ctxt").innerHTML = maDiv;
                }
            });

        },
        onChangeYear: function (year){
            document.getElementById("setAn").value = year;
        }
    });
    $("#airdatepicker").data('datepicker');
});

function getClasse(){
    var matricule = document.getElementById("matPro").value;
    var classe = document.getElementById("p1").value;
    $.ajax({
        type : 'post',
        url : 'cahier_texte',
        dataType: 'json',
        data : {
            matProf: matricule,
            classe: classe,
            crud: 'getClasse'
        },
        success : function(result){
            var btns = '<option class="firstOption" value="" selected></option>';
            var btnt = '<option class="firstOption" value="" selected></option>';
            document.getElementById("mats").innerHTML = btns;
            document.getElementById("chapitre").innerHTML = btnt;
            $.each(result, function(index, value){
                if (value.ch === 'mat'){
                    let opt = '<option value="'+value.matiere+'">'+value.matiere+'</option>';
                    $('#mats').append(opt);
                }
                if (value.ch === 'cha'){
                    let opt = '<option value="'+value.chapiter+'">'+value.chapiter+'</option>';
                    $('#chapitre').append(opt);
                }

            });

        }
    });
}
function getMatiere(){
    var matiere = document.getElementById("mats").value;
    var classe = document.getElementById("p1").value;
    $.ajax({
        type : 'post',
        url : 'cahier_texte',
        dataType: 'json',
        data : {
            classe: classe + '',
            matiere: matiere + '',
            crud: 'getMatiere'
        },
        success : function(result){
            var btnt = '<option class="firstOption" value="" selected></option>';
            $.each(result, function(index, value){
                btnt += '<option value="'+value.chapiter+'">'+value.chapiter+'</option>';
            });
            document.getElementById("chapitre").innerHTML = btnt;
        }
    });
}
function openBox(x){
    if (x == 'open'){
        if (document.getElementById("p1").value == ''){
            document.getElementById("AlP").innerHTML = "Veuillez selectionner la classe";
            document.getElementById("msgN").style.display = "block";
        } else {
            var mySelect = document.getElementById("mats");
            document.getElementById("mats").value = mySelect.options[mySelect.selectedIndex].text;
            document.getElementById("chapitre").value = "";
            document.getElementById("comments").value = "";
            document.getElementById("addChapitres").style.display = "block";
            document.getElementById("ajo").removeAttribute('disabled');
            document.getElementById("mod").setAttribute('disabled', 'disabled');
        }
    }
    if (x == 'close'){
        document.getElementById("addChapitres").style.display = "none";
    }
    if (x == 'closeN'){
        document.getElementById("msgN").style.display = "none";
    }
    if (x != 'open' && x != 'close' && x != 'closeN') {
        if (document.getElementById("p1").value == ''){
            document.getElementById("AlP").innerHTML = "Veuillez selectionner la classe";
            document.getElementById("msgN").style.display = "block";
        } else {
            var id = x.id.substring(x.id.lastIndexOf('-') + 1, x.id.length);

            $.ajax({
                type: 'post',
                url: 'cahier_texte',
                dataType: 'json',
                data: {
                    id: id,
                    crud: 'boutons',
                    send: 'getChapitre'
                },
                success: function (result) {
                    $.each(result, function (index, value) {
                        document.getElementById("idChap").value = value.id;
                        var mySelect = document.getElementById("mats");
                        document.getElementById("mats").value = value.matiere;
                        document.getElementById("chapitre").value = value.chapitre;
                        document.getElementById("comments").value = value.comment;
                        document.getElementById("ajo").setAttribute('disabled', 'disabled');
                        document.getElementById("mod").removeAttribute('disabled');
                        document.getElementById("addChapitres").style.display = "block";
                    });
                }
            });
        }
    }
}
function delForm(x){
    var id = x.id.substring(x.id.lastIndexOf('-') + 1, x.id.length);
    var tht = '<div class="tht">Aucune saisie pour ce jour</div>';
    $.ajax({
        type: 'post',
        url: 'cahier_texte',
        dataType: 'json',
        data: {
            id: id,
            date: document.getElementById("getDate").value,
            crud: 'boutons',
            send: 'delete'
        },
        success : function(result){
            var maDiv = '';
            if (result.length > 0) {
                $.each(result, function (index, value) {
                    maDiv += '<div id="saisirChapitre-' + index + '" class="saisirChapitre" style="border-left: 4px solid ' + value.couler + ';">';
                    maDiv += '<div class="cardText">';
                    maDiv += '<h4 style="border-bottom: 2px dotted ' + value.couler + '; color:' + value.couler + '; ">' + value.classe + '</h4>';
                    maDiv += '</div>';
                    maDiv += '<div class="cardTexts">';
                    maDiv += '<div class="matPr" id="matPr-' + index + '">';
                    maDiv += '<span class="matieres" id="matieres">' + value.matiere + '</span>';
                    maDiv += '</div>';
                    maDiv += '<div id="chps-' + index + '" class="chps">';
                    maDiv += '<h4 style="color: ' + value.couler + ';">' + value.chapitre + '</h4>';
                    maDiv += '<P>' + value.comment + '</P>';
                    maDiv += '</div>';
                    maDiv += '<span class="AT">';
                    maDiv += '<button id="btUpdate-' + value.id + '" class="btn btn-sm btn-outline-info" onclick="openBox(this)"><i class="fa fa-pencil"></i></button>&nbsp;';
                    maDiv += '<button id="btDelete-' + value.id + '" class="btn btn-sm btn-outline-danger" onclick="delForm(this)"><i class="fa fa-trash"></i></button>';
                    maDiv += '</span>';
                    maDiv += '</div>';
                    maDiv += '</div>';
                    maDiv += '<div style="padding: 15px;"></div>';
                });
                document.getElementById("ctxt").innerHTML = maDiv;
            } else {document.getElementById("ctxt").innerHTML = tht;}
        }
    });
}
function sendForm(x){
    var data = $("#addForm").serializeArray();
    var classe = document.getElementById("p1").value;
    var day = document.getElementById("day").value;
    var date = document.getElementById("getDate").value;
    data.push({name:'classe', value: classe});
    data.push({name:'jour', value: day});
    data.push({name:'date', value: date});
    data.push({name:'send', value: x});
    data.push({name:'crud', value: 'boutons'});
    var tht = '<div class="tht">Aucune saisie pour ce jour</div>';
    $.ajax({
        type : 'post',
        url : 'cahier_texte',
        dataType: 'json',
        data : data,
        success : function(result){
            var maDiv = '';
            if (result.length > 0) {
                $.each(result, function (index, value) {
                    maDiv += '<div id="saisirChapitre-' + index + '" class="saisirChapitre" style="border-left: 4px solid ' + value.couler + ';">';
                    maDiv += '<div class="cardText">';
                    maDiv += '<h4 style="border-bottom: 2px dotted ' + value.couler + '; color:' + value.couler + '; ">' + value.classe + '</h4>';
                    maDiv += '</div>';
                    maDiv += '<div class="cardTexts">';
                    maDiv += '<div class="matPr" id="matPr-' + index + '">';
                    maDiv += '<span class="matieres" id="matieres">' + value.matiere + '</span>';
                    maDiv += '</div>';
                    maDiv += '<div id="chps-' + index + '" class="chps">';
                    maDiv += '<h4 style="color: ' + value.couler + ';">' + value.chapitre + '</h4>';
                    maDiv += '<P>' + value.comment + '</P>';
                    maDiv += '</div>';
                    maDiv += '<span class="AT">';
                    maDiv += '<button id="btUpdate-' + value.id + '" class="btn btn-sm btn-outline-info" onclick="openBox(this)"><i class="fa fa-pencil"></i></button>&nbsp;';
                    maDiv += '<button id="btDelete-' + value.id + '" class="btn btn-sm btn-outline-danger" onclick="delForm(this)"><i class="fa fa-trash"></i></button>';
                    maDiv += '</span>';
                    maDiv += '</div>';
                    maDiv += '</div>';
                    maDiv += '<div style="padding: 15px;"></div>';
                });
                document.getElementById("ctxt").innerHTML = maDiv;
            } else {document.getElementById("ctxt").innerHTML = tht;}

            var mySelect = document.getElementById("mats");
            document.getElementById("mats").value = mySelect.options[mySelect.selectedIndex].text;
            document.getElementById("chapitre").value = "";
            document.getElementById("comments").value = "";
        }
    });
    document.getElementById("addChapitres").style.display = "none";
    return false;
}
function getJour(x){
    var week = document.getElementById("sem").textContent;
    var tht = '<div class="tht">Aucune saisie pour ce jour</div>';
    $.ajax({
        type : 'post',
        url : 'cahier_texte',
        dataType: 'json',
        data : {
            date: x.id,
            week: week,
            matProf: document.getElementById("matPro").value,
            crud: 'boutons',
            send: 'auto'
        },
        success : function(result){
                var maDiv = '';
                var nv = 0;
                $.each(result, function (index, value) {
                    if (value.ch == 'cha'){
                        nv += 1;
                        maDiv += '<div id="saisirChapitre-' + index + '" class="saisirChapitre" style="border-left: 4px solid ' + value.couleur + ';">';
                        maDiv += '<div class="cardText">';
                        maDiv += '<h4 style="border-bottom: 2px dotted ' + value.couleur + '; color:' + value.couleur + '; ">' + value.classe + '</h4>';
                        maDiv += '</div>';
                        maDiv += '<div class="cardTexts">';
                        maDiv += '<div class="matPr" id="matPr-' + index + '">';
                        maDiv += '<span class="matieres" id="matieres">' + value.matiere + '</span>';
                        maDiv += '</div>';
                        maDiv += '<div id="chps-' + index + '" class="chps">';
                        maDiv += '<h4 style="color: ' + value.couleur + ';">' + value.chapitre + '</h4>';
                        maDiv += '<P>' + value.comment + '</P>';
                        maDiv += '</div>';
                        maDiv +='<span class="AT">';
                        maDiv +='<button id="btUpdate-'+value.id+'" class="btn btn-sm btn-outline-info" onclick="openBox(this)"><i class="fa fa-pencil"></i></button>&nbsp;';
                        maDiv +='<button id="btDelete-'+value.id+'" class="btn btn-sm btn-outline-danger" onclick="delForm(this)"><i class="fa fa-trash"></i></button>';
                        maDiv +='</span>';
                        maDiv += '</div>';
                        maDiv += '</div>';
                        maDiv += '<div style="padding: 15px;"></div>';
                    }

                    if (value.ch == 'tb'){
                        document.getElementById("jourSemaine").innerHTML = value.date;
                        document.getElementById("jourSemaine").style.color = value.couleur;
                        document.getElementById("getDay").value = value.date;
                        document.getElementById("day").value = value.jourSemaine;
                        document.getElementById("getDate").value = value.getDate;
                    }
                });
                if (nv > 0)  document.getElementById("ctxt").innerHTML = maDiv;
                else  document.getElementById("ctxt").innerHTML = tht;


        }
    });
}

function getSemaine(x) {
    if (document.getElementById("p1").value != '') {
        var week = document.getElementById("sem").textContent;
        var moisAn = document.getElementById('setAn').value;
        var classe =  document.getElementById("p1").value;
        $.ajax({
            type: 'post',
            url: 'cahier_texte',
            dataType: 'json',
            data: {
                chx: '' + x,
                moisAn: '' + moisAn,
                week: '' + week,
                classe: '' + classe,
                matProf: document.getElementById("matPro").value,
                tps: 'prof',
                crud: 'boutons',
                send: 'getWeekly'
            },
            success: function (result) {
                var maDiv = '';
                $.each(result, function (index, value) {
                    if (value.ch == 'cha') {
                        maDiv += '<div id="saisirChapitre-' + index + '" class="saisirChapitre" style="border-left: 4px solid ' + value.couleur + ';">';
                        maDiv += '<div class="cardText">';
                        maDiv += '<h4 style="border-bottom: 2px dotted ' + value.couleur + '; color:' + value.couleur + '; "> PROF: ' + value.prof + '</h4>';
                        maDiv += '</div>';
                        maDiv += '<div class="cardTexts">';
                        maDiv += '<div class="matPr" id="matPr-' + index + '">';
                        maDiv += '<span class="matieres" id="matieres">' + value.matiere + '</span>';
                        maDiv += '</div>';
                        maDiv += '<div id="chps-' + index + '" class="chps">';
                        maDiv += '<h4 style="color: ' + value.couleur + ';">' + value.chapitre + '</h4>';
                        maDiv += '<P>' + value.comment + '</P>';
                        maDiv += '</div>';
                        maDiv += '<span class="AT">';
                        maDiv += '<button id="btUpdate-' + value.id + '" class="btn btn-sm btn-outline-info" disabled><i class="fa fa-pencil"></i></button>&nbsp;';
                        maDiv += '<button id="btDelete-' + value.id + '" class="btn btn-sm btn-outline-danger" disabled><i class="fa fa-trash"></i></button>';
                        maDiv += '</span>';
                        maDiv += '</div>';
                        maDiv += '</div>';
                        maDiv += '<div style="padding: 15px;"></div>';
                    }
                    if (value.ch == 'tb') {
                        document.getElementById("divJour").innerHTML = value.uls;
                        document.getElementById("jourSemaine").innerHTML = value.date;
                        document.getElementById("getDay").value = value.date;
                        document.getElementById("day").value = value.jourSemaine;
                        document.getElementById("jourSemaine").style.color = value.couleur;
                        document.getElementById("sem").innerHTML = value.semaineDu;
                        document.getElementById("week").value = value.semaineDu;
                        document.getElementById("getDate").value = value.getDate;
                    }
                });
                document.getElementById("ctxt").innerHTML = maDiv;
            }
        });

    } else{
        document.getElementById("AlP").innerHTML = "Veuillez selectionner la classe";
        document.getElementById("msgN").style.display = "block";
    }
}

function getFirstSunday (year, month) {

    for (var i = 1; i < 8; i++){
        let d = new Date(year,month, i);
        if (d.getDay() == 0){
            d = new Date(year,month, i);
            return d;
            break;
        }
    }
}
function getLastSunday (year, month) {
    let d = new Date(year,month+1,-1);
    d.setDate(d.getDate()-d.getDay());
    return d;
}
function getLastDayOfMonth(year, month) {
    let date = new Date(year, month + 1, 0);
    return date;
}