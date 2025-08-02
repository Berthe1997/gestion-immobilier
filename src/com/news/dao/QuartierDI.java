package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Quartier;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class QuartierDI implements QuartierDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO quartier (quartier,pays,ville,commune,commentaire) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE quartier SET quartier=?,pays=?,ville=?,commune=?,commentaire=? WHERE id=?";

	@Override 
	  public boolean creerQuartier(Quartier quartier, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, quartier.getQuartier());
				  req.setString(2, quartier.getPays());
				  req.setString(3, quartier.getVille());
				  req.setString(4, quartier.getCommune());
				  req.setString(5, quartier.getCommentaire());	
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
	  public boolean supprimerQuartier(Quartier quartier, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM quartier WHERE id=?");
					req.setLong(1, quartier.getId());

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
	  public boolean modifierQuartier(Quartier quartier, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, quartier.getQuartier());
				  req.setString(2, quartier.getPays());
				  req.setString(3, quartier.getVille());
				  req.setString(4, quartier.getCommune());
				  req.setString(5, quartier.getCommentaire());					
				  req.setLong(6, quartier.getId());
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
	public Quartier getQuartier(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Quartier quartier = null;
	
		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM quartier WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					quartier = UserMap(res);
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
		return quartier;
	}
	
	@Override
	public Boolean getVerifierQuartier(String quartie) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM quartier WHERE quartier=?");
				req.setString(1, quartie);				
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
	
	private static Quartier UserMap(ResultSet res) throws SQLException {
		Quartier quartier = new Quartier();

		quartier.setId(res.getLong("id"));
		quartier.setPays(res.getString("pays"));
		quartier.setQuartier(res.getString("quartier"));
		quartier.setVille(res.getString("ville"));
		quartier.setCommune(res.getString("commune"));
		quartier.setCommentaire(res.getString("commentaire"));
			
			return quartier;
		}
	
}
