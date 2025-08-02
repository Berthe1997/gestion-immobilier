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

import com.news.beans.Urgence_p;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class Urgence_pDI implements  Urgence_pDao{
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO urgence_p (proprietaire,nom,prenom,sexe,email,fonction,adresse,tel,tels,matricule) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE urgence_p SET proprietaire=?,nom=?,prenom=?,sexe=?,email=?,fonction=?,adresse=?,tel=?,tels=? WHERE id=?";

	@Override 
	  public boolean creerUrgence_p(Urgence_p urgence_p, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, urgence_p.getProprietaire());
				  req.setString(2, urgence_p.getNom());
				  req.setString(3, urgence_p.getPrenom());
				  req.setString(4, urgence_p.getSexe());
				  req.setString(5, urgence_p.getEmail());
				  req.setString(6, urgence_p.getFonction());
				  req.setString(7, urgence_p.getAdresse());
				  req.setString(8, urgence_p.getTel());
				  req.setString(9, urgence_p.getTels());
				  req.setString(10, urgence_p.getMatricule());
				  
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
	  public boolean supprimerUrgence_p(Urgence_p urgence_p, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM urgence_p WHERE id=?");
					req.setLong(1, urgence_p.getId());

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
	  public boolean modifierUrgence_p(Urgence_p urgence_p, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, urgence_p.getProprietaire());
				  req.setString(2, urgence_p.getNom());
				  req.setString(3, urgence_p.getPrenom());
				  req.setString(4, urgence_p.getSexe());
				  req.setString(5, urgence_p.getEmail());
				  req.setString(6, urgence_p.getFonction());
				  req.setString(7, urgence_p.getAdresse());
				  req.setString(8, urgence_p.getTel());
				  req.setString(9, urgence_p.getTels());
				  req.setLong(10, urgence_p.getId());
				  
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
		public Urgence_p getUrgence_p(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Urgence_p urgence_p = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM urgence_p WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						urgence_p = UserMap(res);
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
			return urgence_p;
		}
	 
	 @Override
		public Urgence_p getUrgence_p(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Urgence_p urgence_p = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM urgence_p WHERE proprietaire=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						urgence_p = UserMap(res);
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
			return urgence_p;
		}
		
		 @Override
			public List<Urgence_p> getAllUrgence_p(String proprietaire) {
				PreparedStatement req = null;
				 ResultSet res = null;
			     Connection cn=GetConnexion.getConnection();
			     
				List<Urgence_p> urgence_p = new ArrayList<Urgence_p>();
			        
			        if (null!=cn) {
			            try {
			            	req = cn.prepareStatement("SELECT * FROM urgence_p WHERE proprietaire=? ");
							req.setString(1, proprietaire);			
			                res = req.executeQuery();
			    			while ( res.next() ) {
			    				urgence_p.add( UserMap( res ) );
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
			        return urgence_p;
			}
			
	 
	
	 private static Urgence_p UserMap(ResultSet res) throws SQLException {
		 Urgence_p urgence_p = new Urgence_p();

			urgence_p.setId(res.getLong("id"));
			urgence_p.setProprietaire(res.getString("proprietaire"));
			urgence_p.setNom(res.getString("nom"));
			urgence_p.setPrenom(res.getString("prenom"));
			urgence_p.setSexe(res.getString("sexe"));
			urgence_p.setEmail(res.getString("email"));
			urgence_p.setFonction(res.getString("fonction"));
			urgence_p.setAdresse(res.getString("adresse"));
			urgence_p.setTel(res.getString("tel"));
			urgence_p.setTels(res.getString("tels"));
			urgence_p.setMatricule(res.getString("matricule"));
			
			return urgence_p;
		}


}
