function liens(mode){
	if(mode == 'anneescolaires')
		document.location.href='<c:url value="/menus?menu='+mode+'&ch=v"/>';
}