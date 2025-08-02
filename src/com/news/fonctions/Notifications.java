package com.news.fonctions;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Agence;
import com.news.beans.Calendrier_paiement;
import com.news.beans.Demearcheur;
import com.news.beans.Maison;
import com.news.beans.Reception_client;
import com.news.beans.Site;
import com.news.beans.Users;
import com.news.beans.Zone;
import com.news.beans_M.Client_bien;
import com.news.beans_M.Reservation;
import com.news.dao.Calendrier_paiementDI;
import com.news.dao.DemearcheurDI;
import com.news.dao.MaisonDI;
import com.news.dao.Reception_clientDI;
import com.news.dao.SiteDI;
import com.news.dao.ZoneDI;
import com.news.dao_M.Client_bienDI;
import com.news.dao_M.ReservationDI;


public class Notifications {
    private static final Logger LOGGER = LoggerFactory.getLogger(Notifications.class);
    private static final OkHttpClient CLIENT = new OkHttpClient(); // Client réutilisable
    private static final String API_BASE_URL = "https://apis.letexto.com/v1/messages/send"; // HTTPS
    
     private final Calendrier_paiementDI calendrier_paiementDI = new Calendrier_paiementDI();
    // private final Paiement_loyerDI paiement_loyerDI = new Paiement_loyerDI();
     private final  DemearcheurDI demearcheurDI = new DemearcheurDI();
     MaisonDI maisonDI = new MaisonDI();
     ZoneDI zoneDI = new ZoneDI();
     Reception_clientDI reception_clientDI = new Reception_clientDI();
     Client_bienDI client_bienDI= new Client_bienDI();
     ReservationDI reservationDI= new ReservationDI();
     SiteDI siteDI = new SiteDI();

	
     Site site = new Site();
	 Users users	=	new Users();
    
    public Notifications() {}
    
    public void reception_dem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
            			
		String idM = request.getParameter("idM");
		   
		
		Agence agence = (Agence) session.getAttribute("agence");
		site = (Site) session.getAttribute("site");
		
		List<Demearcheur> demarcheur	=	demearcheurDI.getAllDemearcheur(agence.getCode());
		for(Demearcheur dem : demarcheur) {
			
			String	matri = dem.getMatricule();
			String	contact = dem.getTels();
			String	nomD = dem.getNom()+" "+dem.getPrenom();
			
			Maison maison = maisonDI.getMaison(Integer.parseInt(idM));
			
			String	chambre = maison.getPiece();
			String	prix = maison.getPrix() + " F";
									
			 // Validation de l'ID
	        if (matri == null) {
	            LOGGER.error("ID invalide fourni: {}", matri);
	            throw new IllegalArgumentException("ID invalide: " + matri);
	        }
	        
	        // Récupération des données de session
	     
	        if (agence == null) {
	            LOGGER.error("Session agence non trouvée");
	            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
	        }
	        
	        // Récupération du paiement
	        Demearcheur demearcheur = demearcheurDI.getDemearcheur(matri);
	        if (demearcheur == null || demearcheur.getMatricule() == null) {
	            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", matri);
	            throw new IllegalStateException("Données de paiement non disponibles");
	        }
	        	        
	        // Validation du numéro de téléphone
	        if (contact == null || contact.isEmpty() || !contact.matches("\\d+")) {
	            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", nomD);
	            throw new IllegalArgumentException("Numéro de téléphone invalide");
	        }
	        
	        String numeroDestinataire = "225" + contact;
	        String sender = agence.getSender();
	        String	telA = agence.getTel();
	        String	siteS = site.getCommune()+" "+site.getQuartier();
	        
	        // Utilisation du sender de l'agence ou valeur par défaut
	        if (sender == null || sender.isEmpty()) {
	            sender = "NAMY GROUP"; // Valeur par défaut
	            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
	        }
	        
	        String message ="Maison "+chambre+" en finitions à "+siteS+", "+prix+"/mois. Caution 2 mois.\n"			 
					+"\n"+sender+" IMMOBILIER\r\n" + 
					""+telA+" ";
	        	      	        
	     // Envoi du SMS
	        envoyerSMS(sender, numeroDestinataire, message);
	    
		}
				
    } 
    
		
    public void reception_cli(HttpServletRequest request, HttpServletResponse response) throws IOException {
    	HttpSession session = request.getSession();
        String agent	=	request.getParameter("agent");   
        String montant	=	request.getParameter("montant");		
               		
		Agence agence = (Agence) session.getAttribute("agence");
		
		Reception_client client = reception_clientDI.getReception_client(agent);
		
		String	contact = client.getTel();
		
		// Validation de l'ID
        if (agent == null) {
            LOGGER.error("ID invalide fourni: {}", agent);
            throw new IllegalArgumentException("ID invalide: " + agent);
        }
        
        // Récupération des données de session
        if (agence == null) {
            LOGGER.error("Session agence non trouvée");
            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
        }
        
        if (client == null || client.getMatricule() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", agent);
            throw new IllegalStateException("Données de paiement non disponibles");
        }
        
        // Validation du numéro de téléphone
        if (contact == null || contact.isEmpty() || !contact.matches("\\d+")) {
            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", contact);
            throw new IllegalArgumentException("Numéro de téléphone invalide");
        }
        
        String numeroDestinataire = "225" + contact;
        String sender = agence.getSender();
        String	telA = agence.getTel();
        
        // Utilisation du sender de l'agence ou valeur par défaut
        if (sender == null || sender.isEmpty()) {
            sender = "NAMY GROUP"; // Valeur par défaut
            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
        }
        
        String message ="Paiement de "+montant+" FCFA reçu pour frais de recherche/ visite de maison. Merci pour votre confiance.\n"
				+"Nous restons à votre service !\n"
				+"\n"+sender+" IMMOBILIER\r\n" + 
				""+telA+" ";
        
     // Envoi du SMS
        envoyerSMS(sender, numeroDestinataire, message);
        System.out.println(message);
		
		
    }
    
    
    public void reception_cliEX(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String agent	=	request.getParameter("agent");     
               		
		Agence agence = (Agence) session.getAttribute("agence");
		
		Reception_client EX = reception_clientDI.getReception_client(agent);
		
		String piece = EX.getChambre();			
		String zone = EX.getZone();
		String prix = EX.getPrix() + " F"; 
		
		List<Zone> demarcheur	=	zoneDI.getAllZone(agence.getCode(),zone);
		for(Zone dem : demarcheur) {
			
			String	matri = dem.getMatricule();
			String	contact = dem.getTel();
						
			 // Validation de l'ID
	        if (matri == null) {
	            LOGGER.error("ID invalide fourni: {}", matri);
	            throw new IllegalArgumentException("ID invalide: " + matri);
	        }
	        
	        // Récupération des données de session
	     
	        if (agence == null) {
	            LOGGER.error("Session agence non trouvée");
	            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
	        }
	        
	        // Récupération du paiement
	        Demearcheur demearcheur = demearcheurDI.getDemearcheur(matri);
	        if (demearcheur == null || demearcheur.getMatricule() == null) {
	            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", matri);
	            throw new IllegalStateException("Données de paiement non disponibles");
	        }
	        	        
	        // Validation du numéro de téléphone
	        if (contact == null || contact.isEmpty() || !contact.matches("\\d+")) {
	            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", contact);
	            throw new IllegalArgumentException("Numéro de téléphone invalide");
	        }
	        
	        String numeroDestinataire = "225" + contact;
	        String sender = agence.getSender();
	        String	telA = agence.getTel();
	        
	        // Utilisation du sender de l'agence ou valeur par défaut
	        if (sender == null || sender.isEmpty()) {
	            sender = "NAMY GROUP"; // Valeur par défaut
	            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
	        }
	        
	        String message ="Besion rapide maison "+piece+" à "+zone+" budget "+prix+".\n"
					+"Client sérieux. Priorité aux maisons propre. Merci d'agir vite !\n" 				 
					+"\n"+sender+" IMMOBILIER\r\n" + 
					""+telA+" ";
	        
	     // Envoi du SMS
	        envoyerSMS(sender, numeroDestinataire, message);
	        System.out.println(message);
	       
		}
				
    } 
    
    
    public void bienvenue(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String idc = request.getParameter("idc");
        
        
        // Validation de l'ID
        if (idc == null || !idc.matches("\\d+")) {
            LOGGER.error("ID invalide fourni: {}", idc);
            throw new IllegalArgumentException("ID invalide: " + idc);
        }
        
        // Récupération des données de session
        Agence agence = (Agence) session.getAttribute("agence");
        if (agence == null) {
            LOGGER.error("Session agence non trouvée");
            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
        }
        
        // Récupération du paiement
        Calendrier_paiement calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(Integer.parseInt(idc));
        if (calendrier_paiement == null || calendrier_paiement.getLocataire() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", idc);
            throw new IllegalStateException("Données de paiement non disponibles");
        }
        
        // Préparation des données
        String montant = calendrier_paiement.getMontant_S() + " F";
        String mois = calendrier_paiement.getMois();
        String nomLocataire = calendrier_paiement.getLocataires().getNom() + " " + calendrier_paiement.getLocataires().getPrenom();
        String tel = calendrier_paiement.getLocataires().getTel();
        int ans = calendrier_paiement.getAnnee();
        
        // Validation du numéro de téléphone
        if (tel == null || tel.isEmpty() || !tel.matches("\\d+")) {
            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", nomLocataire);
            throw new IllegalArgumentException("Numéro de téléphone invalide");
        }
        
        String numeroDestinataire = "225" + tel;
        String sender = agence.getSender();
        String	telA = agence.getTel();
        
        // Utilisation du sender de l'agence ou valeur par défaut
        if (sender == null || sender.isEmpty()) {
            sender = "NAMY GROUP"; // Valeur par défaut
            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
        }
        
        String message = "Cher(e) " + nomLocataire + ", votre règlement de " + montant + 
                         " pour le mois de " + mois + " " + ans + "  est bien enregistré. Nous vous remercions.\n"
                         +"\n"+sender+" IMMOBILIER\r\n" + 
					     ""+telA+" ";
        
        // Envoi du SMS
        envoyerSMS(sender, numeroDestinataire, message);
    }
    
    public void reservationP(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String client = request.getParameter("client");
        
        site = (Site) session.getAttribute("site");   
        // Validation de l'ID
        if (site == null) {
            LOGGER.error("ID invalide fourni: {}", site);
            throw new IllegalArgumentException("ID invalide: " + site);
        }
        
        // Récupération des données de session
        Agence agence = (Agence) session.getAttribute("agence");
      
        if (agence == null) {
            LOGGER.error("Session agence non trouvée");
            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
        }
        
        // Récupération du paiement
        Site sites = siteDI.getSite(site.getSite());
        if (sites == null || sites.getMatricule() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}");
            throw new IllegalStateException("Données de paiement non disponibles");
        }
            
        
     // Récupération du paiement
        Reservation reservation = reservationDI.getReservationC(client);
        if (reservation == null || reservation.getClient() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", client);
            throw new IllegalStateException("Données de paiement non disponibles");
        }
      
       
      //  String mois = calendrier_paiement.getMois();
        String nomLocataire = sites.getNom_prenom();
        String tel = sites.getProprietaires().getTel();       
        String montant_net =reservation.getMontant_net()+ " FCFA";
        String dure = reservation.getNombre()+ " jours"; 
        
        // Validation du numéro de téléphone
        if (tel == null || tel.isEmpty() || !tel.matches("\\d+")) {
            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", nomLocataire);
            throw new IllegalArgumentException("Numéro de téléphone invalide");
        }
        
        String numeroDestinataire = "225" + tel;
        String sender = agence.getSender();
        String	telA = agence.getTel();
        String	siter = site.getSituation_geo();
        
        // Utilisation du sender de l'agence ou valeur par défaut
        if (sender == null || sender.isEmpty()) {
            sender = "NAMY GROUP"; // Valeur par défaut
            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
        }
        
        String message = "M. " + nomLocataire + ", votre bien situe à "+siter+" "+ 
                         "est actuellement occupé. Durée du séjour "+dure+" "+
                          "\n Montant payé : " +montant_net+" \n "
                         +"\n"+sender+" IMMOBILIER\r\n" + 
					     ""+telA+" ";
        
        // Envoi du SMS
        envoyerSMS(sender, numeroDestinataire, message);
      
        
    }
    
    public void reservation(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String client = request.getParameter("client");
        
        
        // Validation de l'ID
        if (client == null) {
            LOGGER.error("ID invalide fourni: {}", client);
            throw new IllegalArgumentException("ID invalide: " + client);
        }
        
        // Récupération des données de session
        Agence agence = (Agence) session.getAttribute("agence");
        site = (Site) session.getAttribute("site");
        if (agence == null) {
            LOGGER.error("Session agence non trouvée");
            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
        }
        
        // Récupération du paiement
        Client_bien client_bien = client_bienDI.getClient_bien(client);
        if (client_bien == null || client_bien.getMatricule() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", client);
            throw new IllegalStateException("Données de paiement non disponibles");
        }
        
     // Récupération du paiement
        Reservation reservation = reservationDI.getReservationC(client);
        if (reservation == null || reservation.getClient() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", client);
            throw new IllegalStateException("Données de paiement non disponibles");
        }
        
        // Préparation des données
      
       
      //  String mois = calendrier_paiement.getMois();
        String nomLocataire = client_bien.getNom() + " " + client_bien.getPrenom();
        String tel = client_bien.getTel();
        String dateE =reservation.getDate_entree();
        String dateS =reservation.getDate_sortie();
        String montant_net =reservation.getMontant_net()+ " F";
        
        // Validation du numéro de téléphone
        if (tel == null || tel.isEmpty() || !tel.matches("\\d+")) {
            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", nomLocataire);
            throw new IllegalArgumentException("Numéro de téléphone invalide");
        }
        
        String numeroDestinataire = "225" + tel;
        String sender = agence.getSender();
        String	telA = agence.getTel();
        String	sites = site.getSite();
        
        // Utilisation du sender de l'agence ou valeur par défaut
        if (sender == null || sender.isEmpty()) {
            sender = "NAMY GROUP"; // Valeur par défaut
            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
        }
        
        String message = "M. " + nomLocataire + ", nous avons bien reçu votre paiement de "+montant_net+" "+ 
                         " pour le séjour du "
                         + "\n" +dateE+ " au " +dateS+ ". Merci de votre confiance -" +sites+" \n "
                         +"\n"+sender+" IMMOBILIER\r\n" + 
					     ""+telA+" ";
        
        // Envoi du SMS
        envoyerSMS(sender, numeroDestinataire, message);
        
    }
    
    public void avance(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String matricule = request.getParameter("matricule");
        String nbr = request.getParameter("nbr");
        String montantA = request.getParameter("montantA");
        
        // Validation de l'ID
        if (matricule == null) {
            LOGGER.error("ID invalide fourni: {}", matricule);
            throw new IllegalArgumentException("ID invalide: " + matricule);
        }
        
        // Récupération des données de session
        Agence agence = (Agence) session.getAttribute("agence");
        if (agence == null) {
            LOGGER.error("Session agence non trouvée");
            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
        }
        
        // Récupération du paiement
        Calendrier_paiement calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(matricule);
        if (calendrier_paiement == null || calendrier_paiement.getLocataire() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", matricule);
            throw new IllegalStateException("Données de paiement non disponibles");
        }
        
        // Préparation des données
        String montant = Integer.parseInt(montantA)*Integer.parseInt(nbr) + " F";
        System.out.println(montant);
      //  String mois = calendrier_paiement.getMois();
        String nomLocataire = calendrier_paiement.getLocataires().getNom() + " " + calendrier_paiement.getLocataires().getPrenom();
        String tel = calendrier_paiement.getLocataires().getTel();
        
        // Validation du numéro de téléphone
        if (tel == null || tel.isEmpty() || !tel.matches("\\d+")) {
            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", nomLocataire);
            throw new IllegalArgumentException("Numéro de téléphone invalide");
        }
        
        String numeroDestinataire = "225" + tel;
        String sender = agence.getSender();
        String	telA = agence.getTel();
        
        // Utilisation du sender de l'agence ou valeur par défaut
        if (sender == null || sender.isEmpty()) {
            sender = "NAMY GROUP"; // Valeur par défaut
            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
        }
        
        String message = "Cher(e) " + nomLocataire + ", votre règlement de " + montant + 
                         " pour l'avance de " + nbr + " mois est bien enregistré. Nous vous remercions.\n"
                         +"\n"+sender+" IMMOBILIER\r\n" + 
					     ""+telA+" ";
        
        // Envoi du SMS
        envoyerSMS(sender, numeroDestinataire, message);
    }
    
    public void caution(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String matricule = request.getParameter("matricule");
        String montantNB = request.getParameter("montantNB");
        String montantC = request.getParameter("montantC");
        
        // Validation de l'ID
        if (matricule == null) {
            LOGGER.error("ID invalide fourni: {}", matricule);
            throw new IllegalArgumentException("ID invalide: " + matricule);
        }
        
        // Récupération des données de session
        Agence agence = (Agence) session.getAttribute("agence");
        if (agence == null) {
            LOGGER.error("Session agence non trouvée");
            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
        }
        
        // Récupération du paiement
        Calendrier_paiement calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(matricule);
        if (calendrier_paiement == null || calendrier_paiement.getLocataire() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", matricule);
            throw new IllegalStateException("Données de paiement non disponibles");
        }
        
        // Préparation des données
        String montant = Integer.parseInt(montantC)*Integer.parseInt(montantNB) + " F";
        System.out.println(montant);
      //  String mois = calendrier_paiement.getMois();
        String nomLocataire = calendrier_paiement.getLocataires().getNom() + " " + calendrier_paiement.getLocataires().getPrenom();
        String tel = calendrier_paiement.getLocataires().getTel();
        
        // Validation du numéro de téléphone
        if (tel == null || tel.isEmpty() || !tel.matches("\\d+")) {
            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", nomLocataire);
            throw new IllegalArgumentException("Numéro de téléphone invalide");
        }
        
        String numeroDestinataire = "225" + tel;
        String sender = agence.getSender();
        String	telA = agence.getTel();
        
        // Utilisation du sender de l'agence ou valeur par défaut
        if (sender == null || sender.isEmpty()) {
            sender = "NAMY GROUP"; // Valeur par défaut
            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
        }
        
        String message = "Cher(e) " + nomLocataire + ", votre règlement de " + montant + 
                         " pour la caution est bien enregistré. Nous vous remercions.\n"
                         +"\n"+sender+" IMMOBILIER\r\n" + 
					     ""+telA+" ";
        
        // Envoi du SMS
        envoyerSMS(sender, numeroDestinataire, message);
    }
    
    public void arrieres(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String matricule = request.getParameter("matricule");
        String montantAR = request.getParameter("montantAR");
        String ansAR = request.getParameter("ansAR");
		String moisAR= request.getParameter("moisAR");
        
        // Validation de l'ID
        if (matricule == null) {
            LOGGER.error("ID invalide fourni: {}", matricule);
            throw new IllegalArgumentException("ID invalide: " + matricule);
        }
        
        // Récupération des données de session
        Agence agence = (Agence) session.getAttribute("agence");
        if (agence == null) {
            LOGGER.error("Session agence non trouvée");
            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
        }
        
        // Récupération du paiement
        Calendrier_paiement calendrier_paiement = calendrier_paiementDI.getCalendrier_paiement(matricule);
        if (calendrier_paiement == null || calendrier_paiement.getLocataire() == null) {
            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", matricule);
            throw new IllegalStateException("Données de paiement non disponibles");
        }
        
        // Préparation des données
        String montant = Integer.parseInt(montantAR) + " F";
       
      //  String mois = calendrier_paiement.getMois();
        String nomLocataire = calendrier_paiement.getLocataires().getNom() + " " + calendrier_paiement.getLocataires().getPrenom();
        String tel = calendrier_paiement.getLocataires().getTel();
        
        // Validation du numéro de téléphone
        if (tel == null || tel.isEmpty() || !tel.matches("\\d+")) {
            LOGGER.error("Numéro de téléphone invalide pour le locataire: {}", nomLocataire);
            throw new IllegalArgumentException("Numéro de téléphone invalide");
        }
        
        String numeroDestinataire = "225" + tel;
        String sender = agence.getSender();
        String	telA = agence.getTel();
        
        // Utilisation du sender de l'agence ou valeur par défaut
        if (sender == null || sender.isEmpty()) {
            sender = "NAMY GROUP"; // Valeur par défaut
            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
        }
                       
        String message = "Cher(e) " + nomLocataire + ", votre règlement de " + montant + 
                " pour l'arriéré du " + moisAR + " " + ansAR + "  est bien enregistré. Nous vous remercions.\n"
                 +"\n"+sender+" IMMOBILIER\r\n" + 
				 ""+telA+" ";
        
        // Envoi du SMS
        envoyerSMS(sender, numeroDestinataire, message);
    }   
    
    
    /**
     * Méthode d'envoi de SMS via l'API Letexto
     */
    private void envoyerSMS(String expediteur, String destinataire, String message) throws IOException {
        try {
            // Récupération du token depuis la configuration (idéalement)
            String token = "14d107b41006827414721d8fc6fd4c18";
            
            // Encodage des paramètres
            String from = URLEncoder.encode(expediteur, "UTF-8");
            String to = URLEncoder.encode(destinataire, "UTF-8");
            String content = URLEncoder.encode(message, "UTF-8");
            
            // Construction de l'URL
            String apiUrl = API_BASE_URL + "?from=" + from + "&to=" + to + 
                           "&content=" + content + "&token=" + token;
            
            // Création et exécution de la requête
            Request requestApi = new Request.Builder()
                    .url(apiUrl)
                    .get()
                    .build();
            
            try (Response apiResponse = CLIENT.newCall(requestApi).execute()) {
                if (apiResponse.isSuccessful()) {
                    String responseBody = Objects.requireNonNull(apiResponse.body()).string();
                    LOGGER.info("SMS envoyé avec succès à {}", destinataire);
                    LOGGER.debug("Réponse API: {}", responseBody);
                } else {
                    LOGGER.error("Échec de l'envoi du SMS. Code: {}", apiResponse.code());
                }
            }
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    /**
     * Récupère le token d'API depuis la configuration
     */
    
}