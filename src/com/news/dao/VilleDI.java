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

import com.news.beans.Ville;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class VilleDI implements VilleDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO ville (ville,pays,commentaire) VALUES (?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE ville SET ville=?,pays=?,commentaire=? WHERE id=?";
	
	  @Override 
	  public boolean creerVille(Ville ville, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, ville.getVille());
				  req.setString(2, ville.getPays());
				  req.setString(3, ville.getCommentaire());
						  
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
	  public boolean supprimerVille(Ville ville, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM ville WHERE id=?");
					req.setLong(1, ville.getId());

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
	  public boolean modifierVille(Ville ville, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, ville.getVille());
				  req.setString(2, ville.getPays());
				  req.setString(3, ville.getCommentaire());				
				  req.setLong(4, ville.getId());
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
		public Ville getVille(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Ville ville = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM ville WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						ville = UserMap(res);
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
			return ville;
		}
	  
	  @Override
		public Boolean getVerifierVille(String ville) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM ville WHERE ville=?");
					req.setString(1, ville);				
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
		public List<Ville> getAllVille() {
			 PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Ville> ville = new ArrayList<Ville>();
		        
		        if (null!=cn) {
		            try {
		                req=cn.prepareStatement("SELECT * FROM ville");
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				ville.add( UserMap( res ) );
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
		        return ville;
		}
	  
	  private static Ville UserMap(ResultSet res) throws SQLException {
		  Ville ville = new Ville();

		  ville.setId(res.getLong("id"));		  
		  ville.setVille(res.getString("ville"));
		  ville.setPays(res.getString("pays"));
		  ville.setCommentaire(res.getString("commentaire"));
			
			
			return ville;
		}

}
