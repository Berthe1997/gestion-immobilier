function miseAJour(mode){
	if(mode == 'soldes'){
		
		document.getElementById("loadIcons").style.display = "block";
		$.ajax({
			type : 'post',
			url : 'ajax',
			dataType: 'json',
			data : {
				crud: mode
			},
	        success:function(response){
	        	
	        }
	    });
		setTimeout(function() {
			document.getElementById("loadIcons").style.display = "none";
		}, 10000);
		
	}
	if(mode == 'moyenne'){
		document.getElementById("loadIcons").style.display = "block";
		var trim = document.getElementById("periode").value;
		var codeA = document.getElementById("codeA").value;
		var codeEc = document.getElementById("codeEc").value;
		$.ajax({
			type : 'post',
			url : 'MAJM',
			dataType: 'json',
			data : {
				codeA: codeA,
				codeEc: codeEc,
				trim: trim
			},
	        success:function(response){
	        	document.getElementById("loadIcons").style.display = "none";
	        }
	    });
	}
}