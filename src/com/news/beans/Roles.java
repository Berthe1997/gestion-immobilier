package com.news.beans;

public class Roles {

	private Long id;
	private String site;
	private String profil;	
	private int agence;
	private int code_site;
	private int porte;
	private int rapport;
	private int ressourceH;
	private int gestionnaire;
	private int locataire;
	private int proprietaire;
	private int comptabilite;
	private int paiementL;
	private int chargeE;	
	private int parametre;
	private int utilisateur;
	private int profile;
	private int pays;
	private int ville;
	private int commune;	
	private int quartier;
	private int pourcentage;
	private int service;
	private int role;
	private int accueil;
	
  /* ==== LES BOUTONS ==== */
	private int ajout_AG;	
	private int modif_AG;
	private int supp_AG;
	private int ajout_SI;
	private int modif_SI;
	private int supp_SI;
	private int ajout_AP;	
	private int modif_AP;
	private int supp_AP;
	private int ajout_GE;
	private int modif_GE;
	private int supp_GE;
	private int ajout_LO;
	private int modif_LO;	
	private int supp_LO;
	private int ajout_PR;
	private int modif_PR;
	private int supp_PR;
	private int ajout_PL;
	private int supp_PL;
	
	/* ==== LES BOUTONS PARAMETRE ==== */
	private int ajout_UT;
	private int modif_UT;	
	private int supp_UT;
	private int ajout_SV;
	private int modif_SV;	
	private int supp_SV;
	private int ajout_PO;
	private int modif_PO;	
	private int supp_PO;
	
	
	/* ==== LES BOUTONS RAPPORT ==== */
	private int coutM;
	private int etat_FL;	
	private int etat_FS;
	private int etat_FA;
	private int etat_FM;	
	private int fiche_ID;
	private int tableau_SU;
	private int etat_LI;	
	private int bilan_CH;
	private int etat_FAP;
	private int etat_FMP;	
	private int etat_FAG;
	private int etat_FMG;
	private int etat_FIL;	
	private int commissionE;
	
	/* ==== LES BOUTONS PARAMETRE ==== */
	private int partenaire;
	private int service_tech;	
	private int g_caution;
	private int archive;
	
	/* ==== LES BOUTONS NEW ==== */
	private int archive_cli;
	private int client;	
	private int ajout_cli;
	private int modif_cli;
	private int supp_cli;	
	private int demarcheur;
	private int ajout_dem;
	private int modif_dem;	
	private int supp_dem;
	private int reception_cli;
	private int ajout_recli;	
	private int modif_recli;
	private int supp_recli;
	private int notification;	
	private int arriere;
	private int ajout_ari;
	private int modif_ari;	
	private int supp_ari;
	private int bien_meuble;
	private int bien;	
	private int ajout_b;
	private int modif_b;
	private int supp_b;	
	private int paiement_bm;
	private int gestion_service;
	private int rapport_c;	
				
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSite() {
		return site;
	}
	public void setSite(String site) {
		this.site = site;
	}
	public String getProfil() {
		return profil;
	}
	public void setProfil(String profil) {
		this.profil = profil;
	}
	public int getAgence() {
		return agence;
	}
	public void setAgence(int agence) {
		this.agence = agence;
	}
	public int getCode_site() {
		return code_site;
	}
	public void setCode_site(int code_site) {
		this.code_site = code_site;
	}
	public int getPorte() {
		return porte;
	}
	public void setPorte(int porte) {
		this.porte = porte;
	}
	public int getRapport() {
		return rapport;
	}
	public void setRapport(int rapport) {
		this.rapport = rapport;
	}
	public int getRessourceH() {
		return ressourceH;
	}
	public void setRessourceH(int ressourceH) {
		this.ressourceH = ressourceH;
	}
	public int getGestionnaire() {
		return gestionnaire;
	}
	public void setGestionnaire(int gestionnaire) {
		this.gestionnaire = gestionnaire;
	}
	public int getLocataire() {
		return locataire;
	}
	public void setLocataire(int locataire) {
		this.locataire = locataire;
	}
	public int getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(int proprietaire) {
		this.proprietaire = proprietaire;
	}
	public int getComptabilite() {
		return comptabilite;
	}
	public void setComptabilite(int comptabilite) {
		this.comptabilite = comptabilite;
	}
	public int getPaiementL() {
		return paiementL;
	}
	public void setPaiementL(int paiementL) {
		this.paiementL = paiementL;
	}
	public int getChargeE() {
		return chargeE;
	}
	public void setChargeE(int chargeE) {
		this.chargeE = chargeE;
	}
	public int getParametre() {
		return parametre;
	}
	public void setParametre(int parametre) {
		this.parametre = parametre;
	}
	public int getUtilisateur() {
		return utilisateur;
	}
	public void setUtilisateur(int utilisateur) {
		this.utilisateur = utilisateur;
	}
	public int getProfile() {
		return profile;
	}
	public void setProfile(int profile) {
		this.profile = profile;
	}
	public int getPays() {
		return pays;
	}
	public void setPays(int pays) {
		this.pays = pays;
	}
	public int getVille() {
		return ville;
	}
	public void setVille(int ville) {
		this.ville = ville;
	}
	public int getCommune() {
		return commune;
	}
	public void setCommune(int commune) {
		this.commune = commune;
	}
	public int getQuartier() {
		return quartier;
	}
	public void setQuartier(int quartier) {
		this.quartier = quartier;
	}
	public int getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(int pourcentage) {
		this.pourcentage = pourcentage;
	}
	public int getService() {
		return service;
	}
	public void setService(int service) {
		this.service = service;
	}
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	public int getAccueil() {
		return accueil;
	}
	public void setAccueil(int accueil) {
		this.accueil = accueil;
	}
	
	
	/* ==== LES ACCES ==== */
	public int getAjout_AG() {
		return ajout_AG;
	}
	public void setAjout_AG(int ajout_AG) {
		this.ajout_AG = ajout_AG;
	}
	public int getModif_AG() {
		return modif_AG;
	}
	public void setModif_AG(int modif_AG) {
		this.modif_AG = modif_AG;
	}
	public int getSupp_AG() {
		return supp_AG;
	}
	public void setSupp_AG(int supp_AG) {
		this.supp_AG = supp_AG;
	}
	public int getAjout_SI() {
		return ajout_SI;
	}
	public void setAjout_SI(int ajout_SI) {
		this.ajout_SI = ajout_SI;
	}
	public int getModif_SI() {
		return modif_SI;
	}
	public void setModif_SI(int modif_SI) {
		this.modif_SI = modif_SI;
	}
	public int getSupp_SI() {
		return supp_SI;
	}
	public void setSupp_SI(int supp_SI) {
		this.supp_SI = supp_SI;
	}
	public int getAjout_AP() {
		return ajout_AP;
	}
	public void setAjout_AP(int ajout_AP) {
		this.ajout_AP = ajout_AP;
	}
	public int getModif_AP() {
		return modif_AP;
	}
	public void setModif_AP(int modif_AP) {
		this.modif_AP = modif_AP;
	}
	public int getSupp_AP() {
		return supp_AP;
	}
	public void setSupp_AP(int supp_AP) {
		this.supp_AP = supp_AP;
	}
	public int getAjout_GE() {
		return ajout_GE;
	}
	public void setAjout_GE(int ajout_GE) {
		this.ajout_GE = ajout_GE;
	}
	public int getModif_GE() {
		return modif_GE;
	}
	public void setModif_GE(int modif_GE) {
		this.modif_GE = modif_GE;
	}
	public int getSupp_GE() {
		return supp_GE;
	}
	public void setSupp_GE(int supp_GE) {
		this.supp_GE = supp_GE;
	}
	public int getAjout_LO() {
		return ajout_LO;
	}
	public void setAjout_LO(int ajout_LO) {
		this.ajout_LO = ajout_LO;
	}
	public int getModif_LO() {
		return modif_LO;
	}
	public void setModif_LO(int modif_LO) {
		this.modif_LO = modif_LO;
	}
	public int getSupp_LO() {
		return supp_LO;
	}
	public void setSupp_LO(int supp_LO) {
		this.supp_LO = supp_LO;
	}
	public int getAjout_PR() {
		return ajout_PR;
	}
	public void setAjout_PR(int ajout_PR) {
		this.ajout_PR = ajout_PR;
	}
	public int getModif_PR() {
		return modif_PR;
	}
	public void setModif_PR(int modif_PR) {
		this.modif_PR = modif_PR;
	}
	public int getSupp_PR() {
		return supp_PR;
	}
	public void setSupp_PR(int supp_PR) {
		this.supp_PR = supp_PR;
	}
	public int getAjout_PL() {
		return ajout_PL;
	}
	public void setAjout_PL(int ajout_PL) {
		this.ajout_PL = ajout_PL;
	}
	public int getSupp_PL() {
		return supp_PL;
	}
	public void setSupp_PL(int supp_PL) {
		this.supp_PL = supp_PL;
	}
	
	
	/* ==== LES BOUTONS PARAMETRE ==== */
	public int getAjout_UT() {
		return ajout_UT;
	}
	public void setAjout_UT(int ajout_UT) {
		this.ajout_UT = ajout_UT;
	}
	public int getModif_UT() {
		return modif_UT;
	}
	public void setModif_UT(int modif_UT) {
		this.modif_UT = modif_UT;
	}
	public int getSupp_UT() {
		return supp_UT;
	}
	public void setSupp_UT(int supp_UT) {
		this.supp_UT = supp_UT;
	}
	public int getAjout_SV() {
		return ajout_SV;
	}
	public void setAjout_SV(int ajout_SV) {
		this.ajout_SV = ajout_SV;
	}
	public int getModif_SV() {
		return modif_SV;
	}
	public void setModif_SV(int modif_SV) {
		this.modif_SV = modif_SV;
	}
	public int getSupp_SV() {
		return supp_SV;
	}
	public void setSupp_SV(int supp_SV) {
		this.supp_SV = supp_SV;
	}
	public int getAjout_PO() {
		return ajout_PO;
	}
	public void setAjout_PO(int ajout_PO) {
		this.ajout_PO = ajout_PO;
	}
	public int getModif_PO() {
		return modif_PO;
	}
	public void setModif_PO(int modif_PO) {
		this.modif_PO = modif_PO;
	}
	public int getSupp_PO() {
		return supp_PO;
	}
	public void setSupp_PO(int supp_PO) {
		this.supp_PO = supp_PO;
	}
	
	
	/* ==== LES BOUTONS RAPPOT ==== */
	public int getCoutM() {
		return coutM;
	}
	public void setCoutM(int coutM) {
		this.coutM = coutM;
	}
	public int getEtat_FL() {
		return etat_FL;
	}
	public void setEtat_FL(int etat_FL) {
		this.etat_FL = etat_FL;
	}
	public int getEtat_FS() {
		return etat_FS;
	}
	public void setEtat_FS(int etat_FS) {
		this.etat_FS = etat_FS;
	}
	public int getEtat_FA() {
		return etat_FA;
	}
	public void setEtat_FA(int etat_FA) {
		this.etat_FA = etat_FA;
	}
	public int getEtat_FM() {
		return etat_FM;
	}
	public void setEtat_FM(int etat_FM) {
		this.etat_FM = etat_FM;
	}
	public int getFiche_ID() {
		return fiche_ID;
	}
	public void setFiche_ID(int fiche_ID) {
		this.fiche_ID = fiche_ID;
	}
	public int getTableau_SU() {
		return tableau_SU;
	}
	public void setTableau_SU(int tableau_SU) {
		this.tableau_SU = tableau_SU;
	}
	public int getEtat_LI() {
		return etat_LI;
	}
	public void setEtat_LI(int etat_LI) {
		this.etat_LI = etat_LI;
	}
	public int getBilan_CH() {
		return bilan_CH;
	}
	public void setBilan_CH(int bilan_CH) {
		this.bilan_CH = bilan_CH;
	}
	public int getEtat_FAP() {
		return etat_FAP;
	}
	public void setEtat_FAP(int etat_FAP) {
		this.etat_FAP = etat_FAP;
	}
	public int getEtat_FMP() {
		return etat_FMP;
	}
	public void setEtat_FMP(int etat_FMP) {
		this.etat_FMP = etat_FMP;
	}
	public int getEtat_FAG() {
		return etat_FAG;
	}
	public void setEtat_FAG(int etat_FAG) {
		this.etat_FAG = etat_FAG;
	}
	public int getEtat_FMG() {
		return etat_FMG;
	}
	public void setEtat_FMG(int etat_FMG) {
		this.etat_FMG = etat_FMG;
	}
	public int getEtat_FIL() {
		return etat_FIL;
	}
	public void setEtat_FIL(int etat_FIL) {
		this.etat_FIL = etat_FIL;
	}
	public int getCommissionE() {
		return commissionE;
	}
	public void setCommissionE(int commissionE) {
		this.commissionE = commissionE;
	}
	
	/* ==== LES BOUTONS NEW ==== */
	public int getPartenaire() {
		return partenaire;
	}
	public void setPartenaire(int partenaire) {
		this.partenaire = partenaire;
	}
	public int getService_tech() {
		return service_tech;
	}
	public void setService_tech(int service_tech) {
		this.service_tech = service_tech;
	}
	public int getG_caution() {
		return g_caution;
	}
	public void setG_caution(int g_caution) {
		this.g_caution = g_caution;
	}
	public int getArchive() {
		return archive;
	}
	public void setArchive(int archive) {
		this.archive = archive;
	}
	
	
	/* ==== LES BOUTONS NEW ==== 26*/
	public int getArchive_cli() {
		return archive_cli;
	}
	public void setArchive_cli(int archive_cli) {
		this.archive_cli = archive_cli;
	}
	public int getClient() {
		return client;
	}
	public void setClient(int client) {
		this.client = client;
	}
	public int getAjout_cli() {
		return ajout_cli;
	}
	public void setAjout_cli(int ajout_cli) {
		this.ajout_cli = ajout_cli;
	}
	public int getModif_cli() {
		return modif_cli;
	}
	public void setModif_cli(int modif_cli) {
		this.modif_cli = modif_cli;
	}
	public int getSupp_cli() {
		return supp_cli;
	}
	public void setSupp_cli(int supp_cli) {
		this.supp_cli = supp_cli;
	}
	public int getDemarcheur() {
		return demarcheur;
	}
	public void setDemarcheur(int demarcheur) {
		this.demarcheur = demarcheur;
	}
	public int getAjout_dem() {
		return ajout_dem;
	}
	public void setAjout_dem(int ajout_dem) {
		this.ajout_dem = ajout_dem;
	}
	public int getModif_dem() {
		return modif_dem;
	}
	public void setModif_dem(int modif_dem) {
		this.modif_dem = modif_dem;
	}
	public int getSupp_dem() {
		return supp_dem;
	}
	public void setSupp_dem(int supp_dem) {
		this.supp_dem = supp_dem;
	}
	public int getReception_cli() {
		return reception_cli;
	}
	public void setReception_cli(int reception_cli) {
		this.reception_cli = reception_cli;
	}
	public int getAjout_recli() {
		return ajout_recli;
	}
	public void setAjout_recli(int ajout_recli) {
		this.ajout_recli = ajout_recli;
	}
	public int getModif_recli() {
		return modif_recli;
	}
	public void setModif_recli(int modif_recli) {
		this.modif_recli = modif_recli;
	}
	public int getSupp_recli() {
		return supp_recli;
	}
	public void setSupp_recli(int supp_recli) {
		this.supp_recli = supp_recli;
	}
	public int getNotification() {
		return notification;
	}
	public void setNotification(int notification) {
		this.notification = notification;
	}
	public int getArriere() {
		return arriere;
	}
	public void setArriere(int arriere) {
		this.arriere = arriere;
	}
	public int getAjout_ari() {
		return ajout_ari;
	}
	public void setAjout_ari(int ajout_ari) {
		this.ajout_ari = ajout_ari;
	}
	public int getModif_ari() {
		return modif_ari;
	}
	public void setModif_ari(int modif_ari) {
		this.modif_ari = modif_ari;
	}
	public int getSupp_ari() {
		return supp_ari;
	}
	public void setSupp_ari(int supp_ari) {
		this.supp_ari = supp_ari;
	}
	public int getBien_meuble() {
		return bien_meuble;
	}
	public void setBien_meuble(int bien_meuble) {
		this.bien_meuble = bien_meuble;
	}
	public int getBien() {
		return bien;
	}
	public void setBien(int bien) {
		this.bien = bien;
	}
	public int getAjout_b() {
		return ajout_b;
	}
	public void setAjout_b(int ajout_b) {
		this.ajout_b = ajout_b;
	}
	public int getModif_b() {
		return modif_b;
	}
	public void setModif_b(int modif_b) {
		this.modif_b = modif_b;
	}
	public int getSupp_b() {
		return supp_b;
	}
	public void setSupp_b(int supp_b) {
		this.supp_b = supp_b;
	}
	public int getPaiement_bm() {
		return paiement_bm;
	}
	public void setPaiement_bm(int paiement_bm) {
		this.paiement_bm = paiement_bm;
	}
	public int getGestion_service() {
		return gestion_service;
	}
	public void setGestion_service(int gestion_service) {
		this.gestion_service = gestion_service;
	}
	public int getRapport_c() {
		return rapport_c;
	}
	public void setRapport_c(int rapport_c) {
		this.rapport_c = rapport_c;
	}
	
}
