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

import com.news.beans.Paiement_service;
import com.news.controllers.CRUDConnexion;


public class Paiement_serviceDI implements Paiement_serviceDao {
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO paiement_service (locataire,matricule_locataire,propriete,montant_loyer,ans,mois,type) VALUES (?,?,?,?,?,?,?)";
	
	
	@Override
	public boolean creerPaiement_service(Paiement_service paiement_service, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
				 req.setString(1, paiement_service.getLocataire());	            
	             req.setString(2, paiement_service.getMatricule_locataire());
	             req.setString(3, paiement_service.getPropriete());	            
	             req.setInt(4, paiement_service.getMontant_loyer());
	             req.setString(5, paiement_service.getAns());
	             req.setString(6, paiement_service.getMois());
	             req.setString(7, paiement_service.getType());
	            
	             
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
	public boolean supprimerPaiement_service(Paiement_service paiement_service, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM paiement_service WHERE id=?");
				req.setLong(1, paiement_service.getId());

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
	public Paiement_service getPaiement_service(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Paiement_service paiement_service = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM paiement_service WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					paiement_service = map(res);
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
		return paiement_service;
	}
	
	@Override
	public Paiement_service getPaiement_service(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Paiement_service paiement_service = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM paiement_service WHERE matricule_locataire=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				if (res.next()) {
					paiement_service = map(res);
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
		return paiement_service;
	}
	
	
	
	@Override
	public  Boolean getVerifierPaiement_service(String propriete,String matricule,String ans,String mois) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM paiement_service WHERE propriete=? AND matricule_locataire=? AND ans=? AND mois=?");
				req.setString(1, propriete);
				req.setString(2, matricule);
				req.setString(3, ans);
				req.setString(4, mois);
				res = req.executeQuery();
				if(res.next()) {
					verifie = true;
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
		return verifie;
	}
	
	@Override
	public List<Paiement_service> getAllPaiement_service(String propriete,String matricule) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Paiement_service> paiement_service = new ArrayList<Paiement_service>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM paiement_service WHERE propriete=? AND matricule_locataire=?");
					req.setString(1, propriete);
					req.setString(2, matricule);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				paiement_service.add( map( res ) );
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
	        return paiement_service;
	}
	
		
	private static Paiement_service map( ResultSet resultSet ) throws SQLException {
		Paiement_service paiement_service = new Paiement_service();
		
		paiement_service.setId(resultSet.getLong("id"));
		paiement_service.setLocataire(resultSet.getString("locataire"));
		paiement_service.setMatricule_locataire( resultSet.getString( "matricule_locataire" ) );
		paiement_service.setPropriete(resultSet.getString("propriete"));
		paiement_service.setMontant_loyer( resultSet.getInt( "montant_loyer" ) );
		paiement_service.setAns( resultSet.getString( "ans" ) );
		paiement_service.setMois( resultSet.getString( "mois" ) );
		paiement_service.setType( resultSet.getString( "type" ) );
		
			
		return paiement_service;
	}

}
