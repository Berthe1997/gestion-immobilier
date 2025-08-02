package com.news.fonctions;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.news.beans.Agence;
import com.news.beans_GT.Client_acheteur;
import com.news.beans_GT.Dossier_client;
import com.news.beans_GT.Offre_terrain;
import com.news.beans_GT.Paiement_terrain;
import com.news.beans_GT.Terrain;
import com.news.dao_GT.Client_acheteurDI;
import com.news.dao_GT.Dossier_clientDI;
import com.news.dao_GT.Offre_terrainDI;
import com.news.dao_GT.Paiement_terrainDI;
import com.news.dao_GT.TerrainDI;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Sms_Terrain {
	 private static final Logger LOGGER = LoggerFactory.getLogger(Notifications.class);
	 private static final OkHttpClient CLIENT = new OkHttpClient(); // Client réutilisable
	 private static final String API_BASE_URL = "https://apis.letexto.com/v1/messages/send"; // HTTPS
	 
	 Paiement_terrainDI paiement_terrainDI= new Paiement_terrainDI();
	 Client_acheteurDI client_acheteurDI= new Client_acheteurDI();
	 TerrainDI terrainDI= new TerrainDI();
	 Dossier_clientDI dossier_clientDI= new Dossier_clientDI();
	 Offre_terrainDI offre_terrainDI= new Offre_terrainDI();
		
	 public void offre_DL(HttpServletRequest request, HttpServletResponse response,String num_offre) throws IOException {
	        HttpSession session = request.getSession();
	     
	     // Récupération des données de session
	        Agence agence = (Agence) session.getAttribute("agence");
	        if (agence == null) {
	            LOGGER.error("Session agence non trouvée");
	            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
	        }
	        
	        Offre_terrain offre= offre_terrainDI.getOffre_terrain(num_offre);
	        
	        List<Dossier_client> dossier_clientS	=	dossier_clientDI.getAllDossier_client();
			for(Dossier_client cli : dossier_clientS) {
					        
	        // Validation de l'ID
	        if (num_offre == null) {
	            LOGGER.error("ID invalide fourni: {}", num_offre);
	            throw new IllegalArgumentException("ID invalide: " + num_offre);
	        }
	            
	        
	        // Récupération du paiement
	        Dossier_client dossier_client = dossier_clientDI.getDossier_client(cli.getMatricule());
	        if (dossier_client == null || dossier_client.getMatricule() == null) {
	            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", cli.getMatricule());
	            throw new IllegalStateException("Données de paiement non disponibles");
	        }
	        
	      
	        
	        // Préparation des données
	        String nomLocataire = dossier_client.getNom() + " " + dossier_client.getPrenom();
	        String tel = dossier_client.getTel();
	        String caract = offre.getCaracteristique();
	        String lieu = offre.getLieu();
	        String lot = offre.getLot();
	        String ilot = offre.getIlot();	        
	        String nature = offre.getNature();
	        String superficie = offre.getSuperficie();	        
	        String prix = offre.getPrix() + " FCFA";
	        String doc = offre.getDocument();

	        
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
	            sender = "DIMCO"; // Valeur par défaut
	            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
	        }
	        
	        String message = "🏡 NOUVELLE OFFRE IMMOBILIÈRE - DIMCO\r\n" + 
	        		"\r\n Numéro de l'offre : "+num_offre+  
	        		"\r\n Nature du bien : " +nature+"Localisation : "+lieu+
	        		"\r\n Ilot : "+ilot+ " Lot : "+lot+
	        		"\r\n Superficie : "+superficie+ " Prix : "+prix+ "\n"+	        		
	        		"\r\n 📞Contact : "+telA+ 
	        		"\r\n Documentation disponible : "+doc+ "\r\n" + 	        		
	        		"";
	        
	        // Envoi du SMS
	        envoyerSMS(sender, numeroDestinataire, message);
	        
	    }
	 }
	 
	 public void offre_CL(HttpServletRequest request, HttpServletResponse response,String num_offre) throws IOException {
	        HttpSession session = request.getSession();
	     
	     // Récupération des données de session
	        Agence agence = (Agence) session.getAttribute("agence");
	        if (agence == null) {
	            LOGGER.error("Session agence non trouvée");
	            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
	        }
	        
	        Offre_terrain offre= offre_terrainDI.getOffre_terrain(num_offre);
	        
	        List<Client_acheteur> client_acheteurS	=	client_acheteurDI.getAllClient_acheteur();
			for(Client_acheteur cli : client_acheteurS) {
					        
	        // Validation de l'ID
	        if (num_offre == null) {
	            LOGGER.error("ID invalide fourni: {}", num_offre);
	            throw new IllegalArgumentException("ID invalide: " + num_offre);
	        }
	            
	        
	        // Récupération du paiement
	        Client_acheteur client_acheteur = client_acheteurDI.getClient_acheteur(cli.getMatricule());
	        if (client_acheteur == null || client_acheteur.getMatricule() == null) {
	            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", cli.getMatricule());
	            throw new IllegalStateException("Données de paiement non disponibles");
	        }
	        
	      
	        
	        // Préparation des données
	        String nomLocataire = client_acheteur.getNom() + " " + client_acheteur.getPrenom();
	        String tel = client_acheteur.getContact();
	        String caract = offre.getCaracteristique();
	        String lieu = offre.getLieu();
	        String lot = offre.getLot();
	        String ilot = offre.getIlot();	        
	        String nature = offre.getNature();
	        String superficie = offre.getSuperficie();	        
	        String prix = offre.getPrix() + " FCFA";
	        String doc = offre.getDocument();

	        
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
	            sender = "DIMCO"; // Valeur par défaut
	            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
	        }
	        
	        String message = "🏡 NOUVELLE OFFRE IMMOBILIÈRE - DIMCO\r\n" + 
	        		"\r\n Numéro de l'offre : "+num_offre+  
	        		"\r\n Nature du bien : " +nature+"Localisation : "+lieu+
	        		"\r\n Ilot : "+ilot+ " Lot : "+lot+
	        		"\r\n Superficie : "+superficie+ " Prix : "+prix+ "\n"+	        		
	        		"\r\n 📞Contact : "+telA+ 
	        		"\r\n Documentation disponible : "+doc+ "\r\n" + 	        		
	        		"";
	        
	        // Envoi du SMS
	        envoyerSMS(sender, numeroDestinataire, message);
	        System.out.println(message);
	        System.out.println(numeroDestinataire);
	    }
	 }
	 
	 
	 public void bienvenue(HttpServletRequest request, HttpServletResponse response) throws IOException {
	        HttpSession session = request.getSession();
	        String terrains = request.getParameter("terrain");
	        
	        
	        // Validation de l'ID
	        if (terrains == null) {
	            LOGGER.error("ID invalide fourni: {}", terrains);
	            throw new IllegalArgumentException("ID invalide: " + terrains);
	        }
	        
	        // Récupération des données de session
	        Agence agence = (Agence) session.getAttribute("agence");
	        if (agence == null) {
	            LOGGER.error("Session agence non trouvée");
	            throw new IllegalStateException("Impossible de récupérer les informations de l'agence");
	        }
	        
	        // Récupération du paiement
	        Paiement_terrain paiement_terrain = paiement_terrainDI.getPaiement_terrainC(terrains);
	        if (paiement_terrain == null || paiement_terrain.getClient() == null) {
	            LOGGER.error("Paiement ou locataire non trouvé pour l'ID: {}", terrains);
	            throw new IllegalStateException("Données de paiement non disponibles");
	        }
	        
	        Terrain terrain= terrainDI.getTerrain(terrains);
	        Client_acheteur client_acheteur= client_acheteurDI.getClient_acheteur(paiement_terrain.getClient());
	        
	        // Préparation des données
	        String montant = paiement_terrain.getMontantP() + " FCFA";
	        String montantR = paiement_terrain.getMontantR() + " FCFA";
	        String nomLocataire = client_acheteur.getNom() + " " + client_acheteur.getPrenom();
	        String tel = client_acheteur.getContact();
	        String tere = terrain.getLotissement();
	        String lot = terrain.getLot();
	        String ilot = terrain.getIlot();
	      
	        
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
	            sender = "DIMCO"; // Valeur par défaut
	            LOGGER.warn("Utilisation du sender par défaut car non défini pour l'agence");
	        }
	        
	        String message = "Cher(e) " + nomLocataire + ", Nous accusons réception de votre paiement de " + montant +
	                         " pour le terrain situé à " + tere +" Lot: " +lot+" Ilot: "+ilot+".\n"
	                         +"Il vous reste " +montantR+
	                         "\nMerci de votre confiance \n"+
	                         "\n"+sender+" IMMOBILIER\r\n" + 
						     ""+telA+" ";
	        
	        // Envoi du SMS
	        envoyerSMS(sender, numeroDestinataire, message);
	        System.out.println(message);
	        System.out.println(numeroDestinataire);
	    }
	 
	 
		 
	// 🎉 Envoi SMS anniversaire automatique
	    public void envoyerAnniversaireClients() {
	    	
	        try {
	            List<Client_acheteur> clients = client_acheteurDI.getAllClient_acheteurAN();

	            for (Client_acheteur client : clients) {
	                String numero = client.getContact();
	                if (numero != null && numero.matches("\\d+")) {
	                    String numeroDestinataire = "225" + numero;
	                    String nom = client.getNom() + " " + client.getPrenom();
	                    String message = "🎉 Joyeux anniversaire " + nom + " ! Toute l'équipe de DIAKITE Immobilier Et Conseil vous souhaite bonheur, santé et réussite.";

	                    envoyerSMS("DIMCO", numeroDestinataire, message);
	                    System.out.println("SMS anniversaire envoyé à " + numeroDestinataire);
	                }
	            }
	        } catch (Exception e) {
	            LOGGER.error("Erreur lors de l'envoi des SMS anniversaire", e);
	        }
	    }
	
	 // 🎉 Envoi SMS anniversaire automatique
	    public void envoyerAnniversaireClientD() {
	        try {
	            List<Dossier_client> clients = dossier_clientDI.getAllDossier_clientAN();

	            for (Dossier_client client : clients) {
	                String numero = client.getTel();
	                if (numero != null && numero.matches("\\d+")) {
	                    String numeroDestinataire = "225" + numero;
	                    String nom = client.getNom() + " " + client.getPrenom();
	                    String message = "🎉 Joyeux anniversaire " + nom + " ! Toute l'équipe de DIAKITE Immobilier Et Conseil vous souhaite bonheur, santé et réussite.";

	                    envoyerSMS("DIMCO", numeroDestinataire, message);
	                    System.out.println("SMS anniversaire envoyé à " + numeroDestinataire);
	                }
	            }
	        } catch (Exception e) {
	            LOGGER.error("Erreur lors de l'envoi des SMS anniversaire", e);
	        }
	    }
	
	
	 /**
     * Méthode d'envoi de SMS via l'API Letexto
     */
    private void envoyerSMS(String expediteur, String destinataire, String message) throws IOException {
        try {
            // Récupération du token depuis la configuration (idéalement)
            String token = "9ed88e5b58a79c19c8f61ec933ce2230";
            
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
