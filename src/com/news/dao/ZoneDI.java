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

import com.news.beans.Zone;
import com.news.controllers.CRUDUser;

public class ZoneDI implements ZoneDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO zone (code,matricule,tel,ville,commune,zone) VALUES (?,?,?,?,?,?)";
	
	 @Override 
	  public boolean creerZone(Zone zone, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, zone.getCode());
				  req.setString(2, zone.getMatricule());
				  req.setString(3, zone.getTel());
				  req.setString(4, zone.getVille());
				  req.setString(5, zone.getCommune());
				  req.setString(6, zone.getZone());
				  				  				  
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
		public boolean supprimerZone(Zone zone, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM zone WHERE id=?");
					req.setLong(1, zone.getId());

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
		public Zone getZone(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Zone zone = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM zone WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					 while (res.next()) {
						 zone = map(res);
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
			return zone;
		}
	 
	 @Override
		public Zone getZone(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Zone zone = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM zone WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 zone = map(res);
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
			return zone;
		}
	 
	 @Override
		public Zone getZoneC(String code,String zones) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Zone zone = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM zone WHERE code=? and zone=?");
					req.setString(1, code);
					req.setString(2, zones);
					res = req.executeQuery();
					 while (res.next()) {
						 zone = map(res);
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
			return zone;
		}
	 
	 @Override
		public Boolean getZoneVerifie(String matricule,String zones) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM zone WHERE matricule=? and zone=?");
					req.setString(1, matricule);
					req.setString(2, zones);
					res = req.executeQuery();
					 while (res.next()) {
						 verifie = true;
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
			return verifie;
		}
	 
	 @Override
		public List<Zone> getAllZone(String code,String zones) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Zone> zone = new ArrayList<Zone>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM zone WHERE code=? and zone=?");
					req.setString(1, code);	
					req.setString(2, zones);
					res = req.executeQuery();
					 while (res.next()) {
						 zone.add(map(res));
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
			return zone;
		}
	 
	 @Override
		public List<Zone> getAllZoneV(String ville) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Zone> zone = new ArrayList<Zone>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM zone WHERE ville=? group by commune");
					req.setString(1, ville);	
					res = req.executeQuery();
					 while (res.next()) {
						 zone.add(map(res));
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
			return zone;
		}
	 
	 @Override
		public List<Zone> getAllZoneC(String commune) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Zone> zone = new ArrayList<Zone>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM zone WHERE commune=? group by zone");
					req.setString(1, commune);	
					res = req.executeQuery();
					 while (res.next()) {
						 zone.add(map(res));
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
			return zone;
		}
	 
	 private static Zone map(ResultSet res) throws SQLException {
		 Zone zone = new Zone();

			zone.setId(res.getLong("id"));
			zone.setCode(res.getString("code"));
			zone.setMatricule(res.getString("matricule"));
			zone.setTel(res.getString("tel"));
			zone.setVille(res.getString("ville"));
			zone.setCommune(res.getString("commune"));				
			zone.setZone(res.getString("zone"));
			
			
	 
			return zone;
		}

}
