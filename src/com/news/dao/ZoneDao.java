package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Zone;

public interface ZoneDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerZone(Zone zone, AtomicReference<String> errorMsg);	
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerZone(Zone zone, AtomicReference<String> errorMsg);
		   
		/* ++++ LES METHODES TROUVER ++++ */
		public Zone getZone(int id);
		public Zone getZone(String matricule);
		public Boolean getZoneVerifie(String matricule,String zones);
		
		public Zone getZoneC(String code,String zones);
		
		/* ++++ LES METHODES LISTER ++++ */
	    public List<Zone> getAllZone(String code,String zones);
	    public List<Zone> getAllZoneV(String ville);
	    public List<Zone> getAllZoneC(String commune);
}
