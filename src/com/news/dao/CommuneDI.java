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

import com.news.beans.Commune;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class CommuneDI implements CommuneDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO commune (commune,pays,ville,commentaire) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE commune SET commune=?,pays=?,ville=?,commentaire=? WHERE id=?";

	@Override 
	  public boolean creerCommune(Commune commune, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, commune.getCommune());
				  req.setString(2, commune.getPays());
				  req.setString(3, commune.getVille());
				  req.setString(4, commune.getCommentaire());			  
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
	  public boolean supprimerCommune(Commune commune, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM commune WHERE id=?");
					req.setLong(1, commune.getId());

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
	  public boolean modifierCommune(Commune commune, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, commune.getCommune());
				  req.setString(2, commune.getPays());
				  req.setString(3, commune.getVille());
				  req.setString(4, commune.getCommentaire());					
				  req.setLong(5, commune.getId());
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
	public Commune getCommune(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Commune commune = null;
	
		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM commune WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					commune = UserMap(res);
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
		return commune;
	}
	
	@Override
	public Boolean getVerifierCommune(String commun) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM commune WHERE commune=?");
				req.setString(1, commun);				
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
		public List<Commune> getAllCommune(String ville) {
			 PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Commune> commune = new ArrayList<Commune>();
		        
		        if (null!=cn) {
		            try {
		                req=cn.prepareStatement("SELECT * FROM commune WHERE ville=?");
		                req.setString(1, ville);
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				commune.add( UserMap( res ) );
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
		        return commune;
		}
	
	private static Commune UserMap(ResultSet res) throws SQLException {
		Commune commune = new Commune();

		  commune.setId(res.getLong("id"));		 
		  commune.setCommune(res.getString("commune"));
		  commune.setPays(res.getString("pays"));
		  commune.setVille(res.getString("ville"));
		  commune.setCommentaire(res.getString("commentaire"));
			
			return commune;
		}
	
}
