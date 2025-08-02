package com.news.dao;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

import com.news.beans.Operation;


public interface OperationDao {
	/* ++++ LES METHODES DE CREATION ++++ */
	public boolean creerOperation(Operation operation, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE SUPPRESSION ++++ */
	public boolean supprimerOperation(Operation Operation, AtomicReference<String> errorMsg);
	public boolean supprimerOperationR(Operation Operation, AtomicReference<String> errorMsg);
	public boolean supprimerOperationVM(Operation Operation, AtomicReference<String> errorMsg);
	
	/* ++++ LES METHODES DE MODIFICATION ++++ */
 //   public boolean modifierOperation(Operation operation, AtomicReference<String> errorMsg);
  
    /* ++++ LES METHODES TROUVER ++++ */
    
    public Operation getOperation(int id);    
    public Boolean getVerifierOperation(String site);
    
    /* ++++ LES METHODES LISTER ++++ */  
    public List<Operation> getAllOperation(String site,String matricule);
    public List<Operation> getAllOperation(String code,String date,String caisse);
    
    /* ++++++++++++++++++++ synthèse operation ++++ */
    public List<Operation> getAllDateE(String code);
    public List<Operation> getAllPaieDateE(String code,String date);
    public List<Operation> getAllPaieCustomE(String code, String deb,String fn);
    
    public List<Operation> getAllDateES(String code,String site);
    public List<Operation> getAllPaieDateES(String code,String date,String site);
    public List<Operation> getAllPaieCustomES(String code, String deb,String fn,String site);
    
    public List<Operation> getAllDateEP(String code,String proprietaire);
    public List<Operation> getAllPaieDateEP(String code,String date,String proprietaire);
    public List<Operation> getAllPaieCustomEP(String code, String deb,String fn,String proprietaire);
    
    /* ++++++++++++++++++++ detail operation ++++ */
    public List<Operation> getAllDate(String code);
    public List<Operation> getAllPaieDate(String code,String date);
    public List<Operation> getAllPaieCustom(String code, String deb,String fn);
    
    public List<Operation> getAllDateS(String code,String site);
    public List<Operation> getAllPaieDateS(String code,String date,String site);
    public List<Operation> getAllPaieCustomS(String code, String deb,String fn,String site);
    
    public List<Operation> getAllDateP(String code,String proprietaire);
    public List<Operation> getAllPaieDateP(String code,String date,String proprietaire);
    public List<Operation> getAllPaieCustomP(String code, String deb,String fn,String proprietaire);
    
}
