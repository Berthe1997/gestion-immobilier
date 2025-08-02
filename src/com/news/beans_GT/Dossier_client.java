package com.news.beans_GT;

public class Dossier_client {
	private Long id;
	private String matricule;
	private String nom;
	private String prenom;
	private String tel;
	private String adresse;
	private String date;
	private String sexe;
	//---------NEW CHAMPS----------------------//
	private String date_naissance;
	private String nationnalite;
	private String profession;
	private int depot_DATTC;
	private int dossier_techn_CT;
	private int dossier_techn_D;
	private int attes_CD;
	private int intro_dem_ACD;
	private int date_BM;
	private int n_DM;
	private int trans_DM;
	private int bornage_cont;
	private int transp_pv_bc;
	private int creat_ACD;
	private int notif_ACD;
	private int pf_ACD;
	private int ref_ACD;
	private String situation_matr;
	private int creat_TF;
	private int trans_TF;
	
	private String lot;
	private String ilot;
	private String lotissement;
	private String date_achat;
		
				
	public String getLot() {
		return lot;
	}
	public void setLot(String lot) {
		this.lot = lot;
	}
	public String getIlot() {
		return ilot;
	}
	public void setIlot(String ilot) {
		this.ilot = ilot;
	}
	public String getLotissement() {
		return lotissement;
	}
	public void setLotissement(String lotissement) {
		this.lotissement = lotissement;
	}
	public String getDate_achat() {
		return date_achat;
	}
	public void setDate_achat(String date_achat) {
		this.date_achat = date_achat;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	public String getDate_naissance() {
		return date_naissance;
	}
	public void setDate_naissance(String date_naissance) {
		this.date_naissance = date_naissance;
	}
	public String getNationnalite() {
		return nationnalite;
	}
	public void setNationnalite(String nationnalite) {
		this.nationnalite = nationnalite;
	}
	public String getProfession() {
		return profession;
	}
	public void setProfession(String profession) {
		this.profession = profession;
	}
	public int getDepot_DATTC() {
		return depot_DATTC;
	}
	public void setDepot_DATTC(int depot_DATTC) {
		this.depot_DATTC = depot_DATTC;
	}
	public int getDossier_techn_CT() {
		return dossier_techn_CT;
	}
	public void setDossier_techn_CT(int dossier_techn_CT) {
		this.dossier_techn_CT = dossier_techn_CT;
	}
	public int getDossier_techn_D() {
		return dossier_techn_D;
	}
	public void setDossier_techn_D(int dossier_techn_D) {
		this.dossier_techn_D = dossier_techn_D;
	}
	public int getAttes_CD() {
		return attes_CD;
	}
	public void setAttes_CD(int attes_CD) {
		this.attes_CD = attes_CD;
	}
	public int getIntro_dem_ACD() {
		return intro_dem_ACD;
	}
	public void setIntro_dem_ACD(int intro_dem_ACD) {
		this.intro_dem_ACD = intro_dem_ACD;
	}
	public int getDate_BM() {
		return date_BM;
	}
	public void setDate_BM(int date_BM) {
		this.date_BM = date_BM;
	}
	public int getN_DM() {
		return n_DM;
	}
	public void setN_DM(int n_DM) {
		this.n_DM = n_DM;
	}
	public int getTrans_DM() {
		return trans_DM;
	}
	public void setTrans_DM(int trans_DM) {
		this.trans_DM = trans_DM;
	}
	public int getBornage_cont() {
		return bornage_cont;
	}
	public void setBornage_cont(int bornage_cont) {
		this.bornage_cont = bornage_cont;
	}
	public int getTransp_pv_bc() {
		return transp_pv_bc;
	}
	public void setTransp_pv_bc(int transp_pv_bc) {
		this.transp_pv_bc = transp_pv_bc;
	}
	public int getCreat_ACD() {
		return creat_ACD;
	}
	public void setCreat_ACD(int creat_ACD) {
		this.creat_ACD = creat_ACD;
	}
	public int getNotif_ACD() {
		return notif_ACD;
	}
	public void setNotif_ACD(int notif_ACD) {
		this.notif_ACD = notif_ACD;
	}
	public int getPf_ACD() {
		return pf_ACD;
	}
	public void setPf_ACD(int pf_ACD) {
		this.pf_ACD = pf_ACD;
	}
	public int getRef_ACD() {
		return ref_ACD;
	}
	public void setRef_ACD(int ref_ACD) {
		this.ref_ACD = ref_ACD;
	}
	public String getSituation_matr() {
		return situation_matr;
	}
	public void setSituation_matr(String situation_matr) {
		this.situation_matr = situation_matr;
	}
	public int getCreat_TF() {
		return creat_TF;
	}
	public void setCreat_TF(int creat_TF) {
		this.creat_TF = creat_TF;
	}
	public int getTrans_TF() {
		return trans_TF;
	}
	public void setTrans_TF(int trans_TF) {
		this.trans_TF = trans_TF;
	}
}
