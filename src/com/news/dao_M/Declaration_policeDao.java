package com.news.dao_M;

import java.util.concurrent.atomic.AtomicReference;

import com.news.beans_M.Declaration_police;

public interface Declaration_policeDao {

	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerDeclaration_police(Declaration_police declaration_police, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerDeclaration_police(Declaration_police declaration_police, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
	public boolean modifierDeclaration_police(Declaration_police declaration_police, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES TROUVER ++++ */
	public Declaration_police getDeclaration_police(int id);
	public Declaration_police getDeclaration_police(String code);
	public Boolean getDeclaration_policeVerifie(String code);
}
