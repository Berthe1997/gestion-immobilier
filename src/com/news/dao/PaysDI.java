package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Pays;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;


public class PaysDI implements PaysDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO pays (pays,capital,prefix,continent) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE pays SET pays=?,capital=?,prefix=?,continent=? WHERE id=?";
	
	  @Override 
	  public boolean creerpays(Pays pays, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, pays.getPays());
				  req.setString(2, pays.getCapital());
				  req.setString(3, pays.getPrefix());
				  req.setString(4, pays.getContinent());			  
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
	  public boolean supprimerpays(Pays pays, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM pays WHERE id=?");
					req.setLong(1, pays.getId());

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
	  public boolean modifierpays(Pays pays, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, pays.getPays());
				  req.setString(2, pays.getCapital());
				  req.setString(3, pays.getPrefix());
				  req.setString(4, pays.getContinent());				
				  req.setLong(5, pays.getId());
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
		public Pays getPays(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Pays pays = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM pays WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						pays = UserMap(res);
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
			return pays;
		}
	  
	  @Override
		public Boolean getVerifierPays(String pay) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM pays WHERE pays=?");
					req.setString(1, pay);				
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
	  
	  private static Pays UserMap(ResultSet res) throws SQLException {
		  Pays pays = new Pays();

			pays.setId(res.getLong("id"));
			pays.setPays(res.getString("pays"));
			pays.setCapital(res.getString("capital"));
			pays.setPrefix(res.getString("prefix"));
			pays.setContinent(res.getString("continent"));
			
			return pays;
		}


}
