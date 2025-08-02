package com.news.ajax;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import javax.json.Json;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.news.beans.*;
import com.news.beans_GT.Terrain;
import com.news.beans_M.Bien_meuble;
import com.news.dao.*;
import com.news.dao_GT.TerrainDI;
import com.news.dao_M.Bien_meubleDI;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

@WebServlet("/ajax")
public class Ajax extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private static final String FORMAT_DATE = "yyy-MM-dd";
    
    
     Agence agence = new Agence();
	 Site site = new Site();
	 Users users	=	new Users();
   
    CaisseDI caisseDI = new CaisseDI();
    SiteDI siteDI = new SiteDI();
    AgenceDI agenceDI = new AgenceDI();
    
    Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
	Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
	
	ArrieresDI arrieresDI = new ArrieresDI();
	Arrieres arrieres = new Arrieres();
	
	  VilleDI villeDI = new VilleDI();	  	  
	  CommuneDI communeDI = new CommuneDI();	  
	  ZoneDI zoneDI = new ZoneDI();		
	  
	  Bien_meubleDI bien_meubleDI= new Bien_meubleDI();
	  Bien_meuble bien_meuble= new Bien_meuble();
	  
		TerrainDI terrainDI= new TerrainDI();
		Terrain terrain= new Terrain();

    AtomicReference<String> errorMsg = new AtomicReference<>("");

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String crud = request.getParameter("crud");
        String id = request.getParameter("id");
        String matricule	=	request.getParameter("matricule");     
        String mois	=	request.getParameter("mois");
        String arriere	=	request.getParameter("arriere");
        String libelle	=	request.getParameter("libelle");
        String ville	=	request.getParameter("ville");
        String commune	=	request.getParameter("commune");
        String vill	=	request.getParameter("vill");
        String commun	=	request.getParameter("commun");
        String bien	=	request.getParameter("bien");
        String matri	=	request.getParameter("matri");
        String matr	=	request.getParameter("matr");
        String ans	=	request.getParameter("ans");
        String code	=	request.getParameter("code");

        agence = (Agence) session.getAttribute("agence");
     	users = (Users) session.getAttribute("users");
     	site = (Site) session.getAttribute("site");

        DateTime dt = new DateTime();
        DateTimeFormatter formatter = DateTimeFormat.forPattern(FORMAT_DATE);
        String dating = dt.toString(formatter);

        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        Gson gson = new Gson();

        
        if(crud.equals("lesCaisses")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Caisse> listeCaisse = caisseDI.getAllCalC(agence.getCode());
			for(Caisse ca : listeCaisse) {
				if(ca.getOf() == 0) {
					objectBuilder.add("cd", ca.getCodeCaisse()+"");
					objectBuilder.add("libelle", ca.getLibCaisse()+"");
					arrayBuilder.add(objectBuilder);
				}
			}
		}
    // ============ ============ ============ LISTE METHODE ZONE ============ ============ =========================== //         
        if(crud.equals("villes")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Zone> listeVil = zoneDI.getAllZoneV(ville);
			for(Zone sit : listeVil) {
				objectBuilder.add("ville", sit.getCommune()+"");
				objectBuilder.add("vill", sit.getCommune()+"");
				arrayBuilder.add(objectBuilder);
			}
		}
        
        if(crud.equals("communes")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Zone> listeCom = zoneDI.getAllZoneC(commune);
			for(Zone sit : listeCom) {
				objectBuilder.add("commune", sit.getZone()+"");
				objectBuilder.add("commun", sit.getZone()+"");
				arrayBuilder.add(objectBuilder);
			}
		}
        
        if(crud.equals("villS")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Zone> listeVil = zoneDI.getAllZoneV(vill);
			for(Zone sit : listeVil) {				
				objectBuilder.add("vill", sit.getCommune()+"");
				arrayBuilder.add(objectBuilder);
			}
		}
        
        if(crud.equals("communS")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Zone> listeCom = zoneDI.getAllZoneC(commun);
			for(Zone sit : listeCom) {
				objectBuilder.add("commun", sit.getZone()+"");
				arrayBuilder.add(objectBuilder);
			}
		}

     // ============ ============ ============ LISTE METHODE DOC_COMPT ============ ============ =========================== // 
        if(crud.equals("proprietaire")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Site> listeSite = siteDI.getAllSiteM(matricule);
			for(Site sit : listeSite) {
				objectBuilder.add("matricule", sit.getSite()+"");
				arrayBuilder.add(objectBuilder);
			}
		}
     // ============ ============ ============ LISTE METHODE ARRIERE ============ ============ =========================== //        
        if(crud.equals("locataireC")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Calendrier_paiement> listeSite = calendrier_paiementDI.getAllCalendrier_paiement(matricule);
			for(Calendrier_paiement sit : listeSite) {
				objectBuilder.add("matricule", sit.getAnnee()+"");
				objectBuilder.add("matri", sit.getMatricule()+"");				
				arrayBuilder.add(objectBuilder);
			}
		}
        
        if(crud.equals("ansC")) {       	
        	int ansU = Integer.parseInt(ans);
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Calendrier_paiement> listeSite = calendrier_paiementDI.getAllCalendrier_paiementAR(ansU,matri);
			for(Calendrier_paiement sit : listeSite) {
				objectBuilder.add("ans", sit.getMois()+"");	
				objectBuilder.add("matr", sit.getMatricule()+"");
				arrayBuilder.add(objectBuilder);
			}
		}
        
        if(crud.equals("moisC")) {       	
			calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(mois,matr);
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("montant", calendrier_paiement.getMontant_R());							
			arrayBuilder.add(objectBuilder);
		}
        

   // ============ ============ ============ LISTE METHODE AUTRE ============ ============ =========================== //        
        if(crud.equals("agence")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Site> listeSite = siteDI.getAllSiteC(agence.getCode());
			for(Site sit : listeSite) {
				objectBuilder.add("code", sit.getSite()+"");
				arrayBuilder.add(objectBuilder);
			}
		}
        
        if(crud.equals("getCalendrier")) {
        	int moisU = Integer.parseInt(mois);
			calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(moisU);
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("montant", calendrier_paiement.getMontant_R());	
			objectBuilder.add("mois", calendrier_paiement.getMois());	
			objectBuilder.add("an", calendrier_paiement.getAnnee());	
			arrayBuilder.add(objectBuilder);
		}
        
        if(crud.equals("getArrieres")) {
        	int arriereU = Integer.parseInt(arriere);
        	arrieres = arrieresDI.getArrieres(arriereU);
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("montantAR", arrieres.getMontant());	
			objectBuilder.add("moisAR", arrieres.getMois());	
			objectBuilder.add("anAR", arrieres.getAns());	
			arrayBuilder.add(objectBuilder);
		}
        
        if(crud.equals("getBiens")) {
        	bien_meuble = bien_meubleDI.getBien_meuble(bien);
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("type", bien_meuble.getType());	
			objectBuilder.add("adresse", bien_meuble.getAdresse());
			objectBuilder.add("descrip", bien_meuble.getDescription());	
			objectBuilder.add("loyerN", bien_meuble.getLoyer_nuit());
			arrayBuilder.add(objectBuilder);
			
        }
        
        if(crud.equals("toutDem")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Ville> listC = villeDI.getAllVille();
			for(Ville cl : listC) {
				objectBuilder.add("libelle", cl.getVille()+"");
				arrayBuilder.add(objectBuilder);
			}
			
		}
        
       
        if(crud.equals("adCom")) {
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			List<Commune> listC = communeDI.getAllCommune(libelle);
			for(Commune cl : listC) {
				objectBuilder.add("libelle", cl.getCommune()+"");
				arrayBuilder.add(objectBuilder);
			}
						
		}
        
        if(crud.equals("getTerrain")) {
			 terrain = terrainDI.getTerrain(code);
			JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
			objectBuilder.add("prix", terrain.getPrixG());
			objectBuilder.add("solde", terrain.getPrixG());
			arrayBuilder.add(objectBuilder);
					
		        }
        
        response.setContentType("application/json; charset=utf8");
        response.getWriter().write(arrayBuilder.build().toString());
    }
}
