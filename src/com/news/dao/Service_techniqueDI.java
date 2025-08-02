package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Service_technique;
import com.news.controllers.CRUDService_technique;
import com.news.controllers.CRUDUser;

public class Service_techniqueDI implements Service_techniqueDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO service_technique (code,site,service,designation,quantite,prixU,total,date) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE service_technique SET code=?,site=?,service=?,designation=?,quantite=?,prixU=?,total=?,date=? WHERE id=?";

	@Override 
	  public boolean creerService_technique(Service_technique service_technique, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, service_technique.getCode());
				  req.setString(2, service_technique.getSite());
				  req.setString(3, service_technique.getService());
				  req.setString(4, service_technique.getDesignation());
				  req.setInt(5, service_technique.getQuantite());			
				  req.setInt(6, service_technique.getPrixU());
				  req.setInt(7, service_technique.getTotal());
				  req.setString(8, service_technique.getDate());
				 					  
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
	public boolean supprimerService_technique(Service_technique service_technique, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM service_technique WHERE id=?");
				req.setLong(1, service_technique.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDService_technique.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDService_technique.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean modifierService_technique(Service_technique service_technique, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, service_technique.getCode());
				  req.setString(2, service_technique.getSite());
				  req.setString(3, service_technique.getService());
				  req.setString(4, service_technique.getDesignation());
				  req.setInt(5, service_technique.getQuantite());			
				  req.setInt(6, service_technique.getPrixU());
				  req.setInt(7, service_technique.getTotal());
				  req.setString(8, service_technique.getDate());	
				  req.setLong(9, service_technique.getId());
		             return (1==req.executeUpdate());
						
				 } catch (SQLException ex) {
		               Logger.getLogger(CRUDService_technique.class.getName()).log(Level.SEVERE, null, ex);
		                errorMsg.set(ex.getMessage());
		            } finally {
		                try {
		                    req.close();
		                    cn.close();
		                } catch (SQLException ex) {
		                   Logger.getLogger(CRUDService_technique.class.getName()).log(Level.SEVERE, null, ex);
		                   errorMsg.set(ex.getMessage());
		                }
		            }
			 }
			 return false;
		}
	
	@Override
	public Service_technique getService_technique(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Service_technique service_technique = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM service_technique WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 service_technique = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDService_technique.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDService_technique.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return service_technique;
	}
	
	private static Service_technique map(ResultSet res) throws SQLException {
		Service_technique service_technique = new Service_technique();

			service_technique.setId(res.getLong("id"));
			service_technique.setCode(res.getString("code"));
			service_technique.setSite(res.getString("site"));
			service_technique.setService(res.getString("service"));
			service_technique.setDesignation(res.getString("designation"));
			service_technique.setQuantite(res.getInt("quantite"));
			service_technique.setPrixU(res.getInt("prixU"));
			service_technique.setTotal(res.getInt("total"));
			service_technique.setDate(res.getString("date"));
			
					
			return service_technique;
	}
	
}
