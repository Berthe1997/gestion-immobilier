package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Paiement_loyer;
import com.news.controllers.CRUDConnexion;

public class Paiement_loyerDI implements Paiement_loyerDao {
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO paiement_loyer (code,site,matricule,type,montant,statut,date,caisse,caissier,annee,mois,montantA,montantI,montantP,modeP,heure) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	
	@Override
	public boolean creerPaiement_loyer(Paiement_loyer paiement_loyer, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
				 req.setString(1, paiement_loyer.getCode());	            
	             req.setString(2, paiement_loyer.getSite());
	             req.setString(3, paiement_loyer.getMatricule());	            
	             req.setString(4, paiement_loyer.getType());
	             req.setInt(5, paiement_loyer.getMontant());
	             req.setString(6, paiement_loyer.getStatut());
	             req.setString(7, paiement_loyer.getDate());
	             req.setString(8, paiement_loyer.getCaisse());
	             req.setString(9, paiement_loyer.getCaissier());
	             req.setInt(10, paiement_loyer.getAnnee());
	             req.setString(11, paiement_loyer.getMois());
	             req.setInt(12, paiement_loyer.getMontantA());
				 req.setInt(13, paiement_loyer.getMontantI());
				 req.setInt(14, paiement_loyer.getMontantP());
				 req.setString(15, paiement_loyer.getModeP());
				 req.setString(16, paiement_loyer.getHeure());
	             
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public boolean supprimerPaiement_loyer(Paiement_loyer paiement_loyer, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM paiement_loyer WHERE id=?");
				req.setLong(1, paiement_loyer.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	
	@Override
	public Paiement_loyer getPaiement_loyer(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Paiement_loyer paiement_loyer = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM paiement_loyer WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					paiement_loyer = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return paiement_loyer;
	}

	@Override
	public List<Paiement_loyer> getAllPaiement_loyer(String site,String matricule) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Paiement_loyer> paiement_loyer = new ArrayList<Paiement_loyer>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM paiement_loyer WHERE site=? AND matricule=? order by id desc");
					req.setString(1, site);	
					req.setString(2, matricule);	
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				paiement_loyer.add( map( res ) );
	    			}
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	        return paiement_loyer;
	}
	
	@Override
	public List<Paiement_loyer> getAllPaiement_loyer(String code,String site,String date,String caisse) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Paiement_loyer> paiement_loyer = new ArrayList<Paiement_loyer>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT sum(montant) as somme,date,caisse FROM paiement_loyer WHERE code=? AND site=? AND date=? AND caisse=?");
					req.setString(1, code);
					req.setString(2, site);
					req.setString(3, date);	
					req.setString(4, caisse);	
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				paiement_loyer.add( mapT( res ) );
	    			}
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	        return paiement_loyer;
	}
	
	private static Paiement_loyer map( ResultSet resultSet ) throws SQLException {
		Paiement_loyer paiement_loyer = new Paiement_loyer();
		LocataireDI locataireDI = new LocataireDI();
		
		paiement_loyer.setId(resultSet.getLong("id"));
		paiement_loyer.setCode(resultSet.getString("code"));
		paiement_loyer.setSite( resultSet.getString( "site" ) );
		paiement_loyer.setMatricule(resultSet.getString("matricule"));
		paiement_loyer.setType( resultSet.getString( "type" ) );
		paiement_loyer.setMontant( resultSet.getInt( "montant" ) );
		paiement_loyer.setStatut( resultSet.getString( "statut" ) );
		paiement_loyer.setDate( resultSet.getString( "date" ) );
		paiement_loyer.setCaisse( resultSet.getString( "caisse" ) );
		paiement_loyer.setCaissier( resultSet.getString( "caissier" ) );
		paiement_loyer.setAnnee(resultSet.getInt("annee"));
		paiement_loyer.setMois(resultSet.getString("mois"));
		paiement_loyer.setMontantA( resultSet.getInt( "montantA" ) );
		paiement_loyer.setMontantI( resultSet.getInt( "montantI" ) );
		paiement_loyer.setMontantP( resultSet.getInt( "montantP" ) );
		paiement_loyer.setModeP(resultSet.getString("modeP"));
		paiement_loyer.setHeure(resultSet.getString("heure"));
		
		paiement_loyer.setLocataire(locataireDI.getLocataire(resultSet.getString("matricule")));
			
		return paiement_loyer;
	}
	
	
	private static Paiement_loyer mapT( ResultSet resultSet ) throws SQLException {
		Paiement_loyer paiement_loyer = new Paiement_loyer();
				
		paiement_loyer.setMontant( resultSet.getInt( "somme" ) );
		paiement_loyer.setDate( resultSet.getString( "date" ) );
		paiement_loyer.setCaisse( resultSet.getString( "caisse" ) );
		return paiement_loyer;
	}
	
}
