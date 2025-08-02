package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Service;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CrudChargesE;

public class ServiceDI implements ServiceDao {

	private static final String SQL_INSERT_PAIEMENT_PER			= 
			"INSERT INTO service (site,service,type_service,statut,montant) "
			+ "VALUES (?,?,?,?,?)";
	
	private static final String SQL_UPDATE_PAIEMENT_PER	 			= 
			"UPDATE service SET service=?,type_service=?,statut=?,montant=? WHERE id=?";
	
	@Override
	public boolean creerService(Service service, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_INSERT_PAIEMENT_PER);
				req.setString(1, service.getSite());
				req.setString(2, service.getService());
				req.setString(3, service.getType_service());
				req.setString(4, service.getStatut());
				req.setInt(5, service.getMontant());
								
				return (1 == req.executeUpdate());

			} catch (SQLException ex) {
				Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	
	@Override
	public boolean supprimerService(Service service, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM service WHERE id=?");
				req.setLong(1, service.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	
	@Override
	public boolean modifierService(Service service, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_UPDATE_PAIEMENT_PER);			
				req.setString(1, service.getService());
				req.setString(2, service.getType_service());
				req.setString(3, service.getStatut());
				req.setInt(4, service.getMontant());
			    req.setLong(5, service.getId());
			    
				return (1 == req.executeUpdate());

			} catch (SQLException ex) {
				Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public Boolean getVerifierService(String site,String service) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM service WHERE site=? AND service=?");
				req.setString(1, site);
				req.setString(2, service);
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
	public Service getService(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Service service = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM service WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					service = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return service;
	}
	
	@Override
	public Service getService(String services) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Service service = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM service WHERE service=?");
				req.setString(1, services);
				res = req.executeQuery();
				if (res.next()) {
					service = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CrudChargesE.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return service;
	}
	
	private static Service map( ResultSet resultSet ) throws SQLException {
		Service service = new Service();
		
		service.setId(resultSet.getLong("id"));
		service.setSite(resultSet.getString("site"));
		service.setService(resultSet.getString("service"));
		service.setType_service(resultSet.getString("type_service"));
		service.setStatut(resultSet.getString("statut"));
		service.setMontant(resultSet.getInt("montant"));
						
		return service;
	}
	
}
