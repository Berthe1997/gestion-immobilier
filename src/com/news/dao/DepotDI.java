package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Depot;
import com.news.controllers.CrudChargesE;

public class DepotDI implements DepotDao {

	private static final String SQL_INSERT_PAIEMENT_PER			= 
			"INSERT INTO depot (site,ans,montantD,montantR,date,heur,mois,code) "
			+ "VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_PAIEMENT_PER	 			= 
			"UPDATE depot SET montantR=? WHERE id=? ORDER BY id DESC LIMIT 1";
	
	@Override
	public boolean creerDepot(Depot depot, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_INSERT_PAIEMENT_PER);
				req.setString(1, depot.getSite());
				req.setString(2, depot.getAns());
				req.setInt(3, depot.getMontantD());
				req.setInt(4, depot.getMontantR());
				req.setString(5, depot.getDate());
				req.setString(6, depot.getHeur());
				req.setString(7, depot.getMois());
				req.setString(8, depot.getCode());
				
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
	public boolean supprimerDepot(Depot depot, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM depot WHERE id=?");
				req.setLong(1, depot.getId());

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
	public boolean modifierDepot(Depot depot, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_UPDATE_PAIEMENT_PER);
				req.setInt(1, depot.getMontantR());				
				req.setLong(2, depot.getId());
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
	public Depot getDepot(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Depot depot = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM depot WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					depot = map(res);
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
		return depot;
	}
	
	@Override
	public Depot getDepotS(String site) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Depot depot = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM depot WHERE site=? ORDER BY id DESC LIMIT 1");
				req.setString(1, site);
				res = req.executeQuery();
				if (res.next()) {
					depot = map(res);
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
		return depot;
	}
	
	private static Depot map( ResultSet resultSet ) throws SQLException {
		Depot depot = new Depot();
		
		depot.setId(resultSet.getLong("id"));
		depot.setSite(resultSet.getString("site"));
		depot.setAns(resultSet.getString("ans"));
		depot.setMontantD(resultSet.getInt("montantD"));
		depot.setMontantR(resultSet.getInt("montantR"));
		depot.setDate(resultSet.getString("date"));
		depot.setHeur(resultSet.getString("heur"));
		depot.setMois(resultSet.getString("mois"));
		depot.setCode(resultSet.getString("code"));
				
		return depot;
	}

}
