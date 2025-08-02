<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sql:query var="listProp" dataSource="jdbc/gestions_imm">
	select * from dossier_client 
</sql:query>


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
		<title>DOSSIER CLIENT</title>
		
	
    <c:import url="/include/base_css.jsp"></c:import>
  </head>
  <body id="page-top">
    <!-- navbar -->
   <c:import url="/include/base_nav.jsp"></c:import>
        <!-- End of Topbar -->
                
     <!-----------------------------------------------FIN DE L'ENTÊTE------------------------------------------  -->
     
      <!-- Begin Page Content -->
    <div class="container-fluid">
      <h1 class="h5 text-gray-800">DOSSIER CLIENT</h1>
      <!-- DataTales Example -->
      <!-- Log on to codeastro.com for more projects -->
      <div class="card shadow mb-4">
        <div class="card-header py-3">
         <div class="form-group row">
            <div class="col-sm-4 mb-4 mb-sm-0"> 
              <a class="btn btn-info" href="#lap_jual_pertanggal_1" data-toggle="modal">Fiche de Suivi ACD</a> 
            </div>
             <div class="col-sm-4 mb-4 mb-sm-0">             
               <a class="btn btn-info" href="#lap_jual_pertanggal_2" data-toggle="modal">Liste Client Acheteur</a>                                               
             </div> 
              <div class="col-sm-4 mb-4 mb-sm-0">  
               <button type="button" class="btn btn-success pull-right" data-toggle="modal" data-target="#ModalTujuan"> AJOUTER UN DOSSIER CLIENT</button>            
             </div>
          </div>
        
         <!-- ============ ============ ============ Fiche de Suivi ACD============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_1" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Fiche de Suivi ACD</h3>
                      </div>
                      <form action="<c:url value="/Etat_Bilan"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-6 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Type Fiche Suivi ACD</label>      
                           </div>
                            <div class="col-sm-6 mb-3 mb-sm-0">
                            <select name="type" required class="form-control">
                               <option value="" >Choix</option>	
								<option value="FS_excel" onclick="getFichier('<c:url value="/excel?type=FSD_excel"/>');">Fichier EXCEL</option>	
								<option value="FS_pdf" onclick="getFichier('<c:url value="/pdf?type=FSD_pdf"/>');">Fichier PDF</option> 
							 </select>
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>                      
                      </div>
                  </form>
                 </div>
               </div>
              </div>
              
          <!-- ============ ============ ============ Liste Client Acheteur============ ============ =========================== --> 
           <div class="modal fade" id="lap_jual_pertanggal_2" tabindex="-1" role="dialog" aria-labelledby="largeModal" aria-hidden="true">
                   <div class="modal-dialog">
                     <div class="modal-content" style="width:400px;">
                      <div class="modal-header">
                        <h3 class="modal-title font-weight-bold text-info" id="myModalLabel" style="text-align:center;font-size:18px;">Liste Client Acheteur</h3>
                      </div>
                      <form action="<c:url value="/Etat_Financier"/>" method="post" class="myFormulaire" target="_blank">
                        <div class="modal-body">
                         <div class="form-group row">
                           <div class="col-sm-6 mb-3 mb-sm-0">
                            <label for="date_naiss" class="">Type Liste Client</label>      
                           </div>
                            <div class="col-sm-6 mb-4 mb-sm-0">                          
					         <select name="type" required class="form-control">
								<option value="" >Choix</option>	
								<option value="CL_excel" onclick="getFichier('<c:url value="/excel?type=CLD_excel"/>');">Fichier EXCEL</option>	
								<option value="CL_pdf" onclick="getFichier('<c:url value="/pdf?type=CLD_pdf"/>');">Fichier PDF</option> 
							 </select>	 														  					
                           </div>     
                         </div>                      
                      </div>
                      <div class="modal-footer">
                        <button class="btn btn-danger" data-dismiss="modal" aria-hidden="true">Close</button>                            
                      </div>
                  </form>
                 </div>
               </div>
              </div>
       
        </div>
        <div class="card-body">
          <div class="table-responsive">
            <table class="table table-bordered table-hover" id="dataTable" width="100%" cellspacing="0">
            <thead class="thead-dark">
                <tr>
                  <th>N°</th>
                  <th>MATRICULE</th>
                  <th>NOM </th>
                  <th>PRENOM</th>                 
                  <th>CONTACT</th>                
                  <th>DATE</th>               
                  <th>ACTION</th>
                </tr>
              </thead>
              <tbody>
            <c:forEach items="${listProp.rows}" var="mapProp" varStatus="boucle">
                <tr>
                  <td>${ boucle.index + 1 }</td>
                  <td>${mapProp.matricule}</td>
                  <td>${mapProp.nom}</td>
                  <td>${mapProp.prenom}</td>
                  <td>${mapProp.tel}</td>
                  <td>${mapProp.date_achat}</td>                                                
                  <td align="center">
                   <a onclick="document.location.href='<c:url value="/view_dossier?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/mod.jpg"></a> 
				   <button onclick="document.location.href='<c:url value="/dossier_cl?id=${mapProp.id}&page=${page}&crud=id" />'"><img alt=""  style=" width: 25px;" src="assets/frontend/img/supp.jpg"></button>
                  </td>
               
              </tr>
             </c:forEach>
            </tbody>
          </table>
        </div>
      </div>
    </div>
    <!-- /.container-fluid -->
  </div>
  <!-- /.container-fluid -->
</div>
<!-- End of Main Content -->
<!-- Footer --><!-- Log on to codeastro.com for more projects -->
 <c:import url="/include/base_footer.jsp"></c:import>
<!-- End of Footer -->
<!-- Modal -->
<div class="modal fade" id="ModalTujuan" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
<div class="modal-dialog" role="document">
<div class="modal-content" style="width:910px;margin-left:-35px;">
  <div class="modal-header">
    <h5 class="modal-title text-success" id="exampleModalLabel"> AJOUTER UN DOSSIER CLIENT </h5>
      <div id="alert"  class="alert-danger" style="display:none;position:absolute;width:450px;text-align:center;padding:5px;font-weight:bold;"> </div> 
    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
    <span aria-hidden="true">&times;</span>
    </button>
  </div>
  <div class="modal-body">
    <form action="<c:url value="/dossier_cl"/>" method="post"  class="myFormulaire">
      <div class="form-group row">
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">NOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="nom" class="form-control" required  value="">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PRENOM<span class="text-danger ml-2">*</span></label>
         <input type="text" name="prenom" class="form-control" required  value="">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">GENRE<span class="text-danger ml-2">*</span></label>
        <select name="sexe" required class="form-control">
			<option value="" selected></option>
			<option value="MASCULIN">MASCULIN</option>
			<option value="FEMININ">FEMININ</option>
		</select>		
        </div> 
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">SITUATION MATRIMO</label>
        <select name="situation_matr"  class="form-control">
			<option value="" selected></option>
			<option value="Celibataire">Celibataire</option>
			<option value="Marie">Marie</option>
			<option value="Divorcer">Divorcer</option>
		</select> 
        </div> 
      </div>   
      <div class="form-group row">
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">DATE NAISSANCE<span class="text-danger ml-2">*</span></label>
         <input type="date" name="date_naissance" class="form-control" required  value="">
        </div>
         <div class="col-sm-3 mb-2 mb-sm-0">
           <label for="platbus" class="">NATIONALITE </label>
	          <sql:query var="listPays" dataSource="jdbc/gestions_imm">
				select * from pays 
			</sql:query>
           <select name="nationnalite"  required class="form-control">
				  <option value="" selected></option>
				  <c:forEach items="${listPays.rows}" var="mapP" varStatus="boucle">
					<option value="${mapP.pays}">${mapP.pays}</option>
				  </c:forEach>
		 </select>        
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">PROFESSION</label>
         <input type="text" name="profession" class="form-control" value="">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
          <label for="platbus" class="">CONTACT</label>
          <input type="text" name="contact" class="form-control" value="">       
        </div>                                    
      </div>
      <div class="form-group row">         
       <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ADRESSE</label>
        <input type="text" name="adresse" class="form-control" value="">
        </div>       	
	    <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">LOT</label>
        <input type="text" name="lot" class="form-control" value="">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">ILOT</label>
        <input type="text" name="ilot" class="form-control" value="">
        </div>
        <div class="col-sm-3 mb-2 mb-sm-0">
        <label for="platbus" class="">LOTISSEMENT</label>
        <input type="text" name="lotissement" class="form-control" value="">
        </div>	
        <div class="col-sm-3 mb-2 mb-sm-0">
		 <label for="platbus" class="">DATE ACHAT </label>
		 <input type="date" name="date_achat" class="form-control" value="">
	    </div>    	          
      </div> 
      <div class="form-group row">   
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">attes_CDisp </label>
	    <input type="checkbox" class="form-control"  name="attes_CDisp"  value="1"  /> 
       </div> 
       <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">intro_dem_ACD</label>
	    <input type="checkbox" class="form-control"  name="intro_dem_ACD"  value="1"  /> 
       </div> 
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">date_BM</label>
	    <input type="checkbox" class="form-control"  name="date_BM"  value="1"  /> 
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">n_DM</label>
	   <input type="checkbox" class="form-control"  name="n_DM"  value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">trans_DM</label>
		 <input type="checkbox" class="form-control"  name="trans_DM"  value="1"  />
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">bornage_cont </label>
	    <input type="checkbox" class="form-control"  name="bornage_cont"  value="1"  /> 
       </div>
      </div>  
      <div class="form-group row">   
         <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">transp_pv_bc </label>
	    <input type="checkbox" class="form-control"  name="transp_pv_bc"  value="1"  /> 
       </div>  
       <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">creat_ACD</label>
	    <input type="checkbox" class="form-control"  name="creat_ACD"  value="1"  /> 
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">      
        <label for="platbus" class="">notif_ACD</label>
	    <input type="checkbox" class="form-control"  name="notif_ACD"  value="1"  /> 
       </div> 
       <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">pf_ACD</label>
	   <input type="checkbox" class="form-control"  name="pf_ACD"  value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">ref_ACD</label>
		 <input type="checkbox" class="form-control"  name="ref_ACD"  value="1" />
       </div> 
         <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">creat_TF</label>
		 <input type="checkbox" class="form-control"  name="creat_TF"  value="1" />
       </div>                               
      </div> 
      <div class="form-group row">         
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">trans_TF</label>
		 <input type="checkbox" class="form-control"  name="trans_TF"  value="1" />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
       <label for="platbus" class="">depot_DATTC</label>
	   <input type="checkbox" class="form-control"  name="depot_DATTC"  value="1"  />
       </div>
       <div class="col-sm-2 mb-2 mb-sm-0">
        <label for="platbus" class="">dossier_techn_CT</label>
		 <input type="checkbox" class="form-control"  name="dossier_techn_CT"  value="1"  />
       </div>
        <div class="col-sm-2 mb-2 mb-sm-0">       
        <label for="platbus" class="">dossier_techn_D </label>
	    <input type="checkbox" class="form-control"  name="dossier_techn_D"  value="1"  /> 
       </div>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
        <button name="crud" value="AJOUTER" class="btn btn-success" >Sauvegarder</button>
      </div>
    </form>
  </div>
</div>
</div>
</div>

 <div class="messageAlert ${message.msgColor!=null?'afficheMassage':''}" id="messageAlert" onclick="fer();">
		<div class="msg alert-info" id="alert">
			<p class="${message.msgColor}">${message.message}</p>
		</div>
	</div>

<!-- js -->
 <c:import url="/include/base_js.jsp"></c:import>
 
 <script type="text/javascript">
 
  function getFichier(url){
	 var a = document.createElement('a');
	 a.target="_blank";
	 a.href=url;
	 a.click();
}
</script>   

</body>
</html>