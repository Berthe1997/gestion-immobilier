package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Gestion_caution;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class Gestion_cautionDI implements Gestion_cautionDao {

	private static final String SQL_INSERT_PAIEMENT_PER			= 
			"INSERT INTO gestion_caution (code,site,mois) VALUES (?,?,?)";
	
	@Override
	public boolean creerGestion_caution(Gestion_caution gestion_caution, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_INSERT_PAIEMENT_PER);				
				req.setString(1, gestion_caution.getCode());
				req.setString(2, gestion_caution.getSite());
				req.setInt(3, gestion_caution.getMois());
				
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
	public boolean supprimerGestion_caution(Gestion_caution gestion_caution, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM gestion_caution WHERE id=?");
				req.setLong(1, gestion_caution.getId());

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
	public Boolean getVerifierGestion_caution(String code,String site) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM gestion_caution WHERE code=? AND site=?");
				req.setString(1, code);
				req.setString(2, site);
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
	public Gestion_caution getGestion_caution(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Gestion_caution gestion_caution = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM gestion_caution WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 gestion_caution = map(res);
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
		return gestion_caution;
	}
	
	private static Gestion_caution map( ResultSet resultSet ) throws SQLException {
		Gestion_caution gestion_caution = new Gestion_caution();
		
		gestion_caution.setId(resultSet.getLong("id"));
		gestion_caution.setCode(resultSet.getString("code"));
		gestion_caution.setSite(resultSet.getString("site"));
		gestion_caution.setMois(resultSet.getInt("mois"));
		
		return gestion_caution;
		}

}
