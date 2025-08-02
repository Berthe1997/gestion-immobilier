package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Gestion_service;
import com.news.controllers.CrudGestion_service;

public class Gestion_serviceDI implements Gestion_serviceDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO gestion_service (code,site,agent,matricule,service,type,montant,date,heure) VALUES (?,?,?,?,?,?,?,?,?)";
	//private static final String SQL_UPDATE 			= 
		//	"UPDATE gestion_service SET code=?,site=?,cnps=?,agent=?,matricule=?,service=?,type=?,montant=?,date=?,heure=?  WHERE id=?";

	@Override 
	  public boolean creerGestion_service(Gestion_service gestion_service, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, gestion_service.getCode());
				  req.setString(2, gestion_service.getSite());
				  req.setString(3, gestion_service.getAgent());
				  req.setString(4, gestion_service.getMatricule());
				  req.setString(5, gestion_service.getService());
				  req.setString(6, gestion_service.getType());
				  req.setInt(7, gestion_service.getMontant());			
				  req.setString(8, gestion_service.getDate());
				  req.setString(9, gestion_service.getHeure());
				  
				  return (1==req.executeUpdate());			
			  } catch (SQLException ex) {
				  Logger.getLogger(CrudGestion_service.class.getName()).log(Level.SEVERE, null, ex);
				  errorMsg.set(ex.getMessage());
			  } finally {
				  try {
					  req.close();
					  cn.close();
				  } catch (SQLException ex) {
					  Logger.getLogger(CrudGestion_service.class.getName()).log(Level.SEVERE, null, ex);
					  errorMsg.set(ex.getMessage());
				  }
			  }
		  }
		  return false;
	  }
	
	@Override
	public boolean supprimerGestion_service(Gestion_service gestion_service, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM gestion_service WHERE id=?");
				req.setLong(1, gestion_service.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CrudGestion_service.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CrudGestion_service.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public Gestion_service getGestion_service(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Gestion_service gestion_service = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM gestion_service WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 gestion_service = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CrudGestion_service.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CrudGestion_service.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return gestion_service;
	}
	
	@Override
	public Gestion_service getGestion_service(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Gestion_service gestion_service = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM gestion_service WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 gestion_service = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CrudGestion_service.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CrudGestion_service.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return gestion_service;
	}
	
	private static Gestion_service map(ResultSet res) throws SQLException {
		Gestion_service gestion_service = new Gestion_service();

			gestion_service.setId(res.getLong("id"));
			gestion_service.setCode(res.getString("code"));
			gestion_service.setSite(res.getString("site"));
			gestion_service.setAgent(res.getString("agent"));
			gestion_service.setMatricule(res.getString("matricule"));
			gestion_service.setService(res.getString("service"));
			gestion_service.setType(res.getString("type"));
			gestion_service.setMontant(res.getInt("montant"));
			gestion_service.setDate(res.getString("date"));
			
			return gestion_service;
		}
}
