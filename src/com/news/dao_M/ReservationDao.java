package com.news.dao_M;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Reservation;

public interface ReservationDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerReservation(Reservation reservation, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerReservation(Reservation reservation, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierReservation(Reservation reservation, AtomicReference<String> errorMsg);
	public boolean modifierReservationR(Reservation reservation, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Reservation getReservation(int id);
	public Reservation getReservation(String code);
	public Reservation getReservationC(String client);
	public Boolean getReservationVerifie(String code);
}
