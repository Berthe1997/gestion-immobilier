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

import com.news.beans.Droit_p;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class Droit_pDI implements  Droit_pDao{
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO droit_p (proprietaire,nom,prenom,sexe,email,fonction,adresse,tel,tels,matricule) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE droit_p SET proprietaire=?,nom=?,prenom=?,sexe=?,email=?,fonction=?,adresse=?,tel=?,tels=? WHERE id=?";

	@Override 
	  public boolean creerDroit_p(Droit_p droit_p, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, droit_p.getProprietaire());
				  req.setString(2, droit_p.getNom());
				  req.setString(3, droit_p.getPrenom());
				  req.setString(4, droit_p.getSexe());
				  req.setString(5, droit_p.getEmail());
				  req.setString(6, droit_p.getFonction());
				  req.setString(7, droit_p.getAdresse());
				  req.setString(8, droit_p.getTel());
				  req.setString(9, droit_p.getTels());
				  req.setString(10, droit_p.getMatricule());
				  
				  return (1==req.executeUpdate());			
			  } catch (SQLException ex) {
				  Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
				  errorMsg.set(ex.getMessage());
			  } finally {
				  try {
					  req.close();
					  cn.close();
				  } catch (SQLException ex) {
					  Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
					  errorMsg.set(ex.getMessage());
				  }
			  }
		  }
		  return false;
	  }
	
	
	 @Override 
	  public boolean supprimerDroit_p(Droit_p droit_p, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM droit_p WHERE id=?");
					req.setLong(1, droit_p.getId());

					return (1 == req.executeUpdate());
				} catch (SQLException ex) {
					Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				} finally {
					try {
						req.close();
						cn.close();
					} catch (SQLException ex) {
						Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
						errorMsg.set(ex.getMessage());
					}
				}
			}
			return false;
	  }
	 
	 @Override 
	  public boolean modifierDroit_p(Droit_p droit_p, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, droit_p.getProprietaire());
				  req.setString(2, droit_p.getNom());
				  req.setString(3, droit_p.getPrenom());
				  req.setString(4, droit_p.getSexe());
				  req.setString(5, droit_p.getEmail());
				  req.setString(6, droit_p.getFonction());
				  req.setString(7, droit_p.getAdresse());
				  req.setString(8, droit_p.getTel());
				  req.setString(9, droit_p.getTels());
				  req.setLong(10, droit_p.getId());
				  
				  return (1==req.executeUpdate());			
			  } catch (SQLException ex) {
				  Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
				  errorMsg.set(ex.getMessage());
			  } finally {
				  try {
					  req.close();
					  cn.close();
				  } catch (SQLException ex) {
					  Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
					  errorMsg.set(ex.getMessage());
				  }
			  }
		  }
		  return false; 
	  }
	 
	 @Override
		public Droit_p getDroit_p(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Droit_p droit_p = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM droit_p WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						droit_p = UserMap(res);
					}
		
				} catch (SQLException ex) {
					Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
				} finally {
					try {
						req.close();
						cn.close();
					} catch (SQLException ex) {
						Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
			return droit_p;
		}
	 
	 @Override
		public Droit_p getDroit_p(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Droit_p droit_p = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM droit_p WHERE proprietaire=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						droit_p = UserMap(res);
					}
		
				} catch (SQLException ex) {
					Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
				} finally {
					try {
						req.close();
						cn.close();
					} catch (SQLException ex) {
						Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}
			return droit_p;
		}
	 
	 @Override
		public List<Droit_p> getAllDroit_p(String proprietaire) {
			PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Droit_p> droit_p = new ArrayList<Droit_p>();
		        
		        if (null!=cn) {
		            try {
		            	req = cn.prepareStatement("SELECT * FROM droit_p WHERE proprietaire=? ");
						req.setString(1, proprietaire);			
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				droit_p.add( UserMap( res ) );
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
		        return droit_p;
		}
		
	 
	
	 private static Droit_p UserMap(ResultSet res) throws SQLException {
		 Droit_p droit_p = new Droit_p();

			droit_p.setId(res.getLong("id"));
			droit_p.setProprietaire(res.getString("proprietaire"));
			droit_p.setNom(res.getString("nom"));
			droit_p.setPrenom(res.getString("prenom"));
			droit_p.setSexe(res.getString("sexe"));
			droit_p.setEmail(res.getString("email"));
			droit_p.setFonction(res.getString("fonction"));
			droit_p.setAdresse(res.getString("adresse"));
			droit_p.setTel(res.getString("tel"));
			droit_p.setTels(res.getString("tels"));
			droit_p.setMatricule(res.getString("matricule"));
			
			return droit_p;
		}

}
