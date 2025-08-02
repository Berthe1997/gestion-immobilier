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

import com.news.beans.Arrieres;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class ArrieresDI implements ArrieresDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO arrieres (code,site,matricule,montant,ans,mois,date) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE arrieres SET code=?,site=?,matricule=?,montant=?,ans=?,mois=?,date=? WHERE id=?";

	@Override 
	  public boolean creerArrieres(Arrieres arrieres, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, arrieres.getCode());
				  req.setString(2, arrieres.getSite());
				  req.setString(3, arrieres.getMatricule());
				  req.setInt(4, arrieres.getMontant());
				  req.setString(5, arrieres.getAns());
				  req.setString(6, arrieres.getMois());
				  req.setString(7, arrieres.getDate());	
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
	  public boolean supprimerArrieres(Arrieres arrieres, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM arrieres WHERE id=?");
					req.setLong(1, arrieres.getId());

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
	  public boolean modifierArrieres(Arrieres arrieres, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, arrieres.getCode());
				  req.setString(2, arrieres.getSite());
				  req.setString(3, arrieres.getMatricule());
				  req.setInt(4, arrieres.getMontant());
				  req.setString(5, arrieres.getAns());
				  req.setString(6, arrieres.getMois());
				  req.setString(7, arrieres.getDate());					
				  req.setLong(8, arrieres.getId());
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
	public Arrieres getArrieres(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Arrieres arrieres = null;
	
		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM arrieres WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					arrieres = UserMap(res);
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
		return arrieres;
	}
	
	@Override
	public Arrieres getArrieres(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Arrieres arrieres = null;
	
		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM arrieres WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				if (res.next()) {
					arrieres = UserMap(res);
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
		return arrieres;
	}
	
	@Override
	public Boolean getVerifierArrieres(String code,String site,String matricule,String ans,String mois) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM arrieres WHERE code=? and site=? and matricule=? and ans=? and mois=?");
				req.setString(1, code);	
				req.setString(2, site);	
				req.setString(3, matricule);	
				req.setString(4, ans);	
				req.setString(5, mois);	
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
	public List<Arrieres> getAllArrieres(String site,String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		List<Arrieres> arrieres = new ArrayList<Arrieres>();

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM arrieres WHERE site=? and matricule=?");
				req.setString(1, site);
				req.setString(2, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 arrieres.add(UserMap(res));
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
		return arrieres;
	}
	
	private static Arrieres UserMap(ResultSet res) throws SQLException {
		Arrieres arrieres = new Arrieres();
		LocataireDI locataireDI = new LocataireDI();

		  arrieres.setId(res.getLong("id"));
		  arrieres.setCode(res.getString("code"));
		  arrieres.setSite(res.getString("site"));
		  arrieres.setMatricule(res.getString("matricule"));
		  arrieres.setMontant(res.getInt("montant"));
		  arrieres.setAns(res.getString("ans"));
		  arrieres.setMois(res.getString("mois"));
		  arrieres.setDate(res.getString("date"));
		  
		  arrieres.setLocataire(locataireDI.getLocataire(res.getString("matricule")));
			
			return arrieres;
		}

}
