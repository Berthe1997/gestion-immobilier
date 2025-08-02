package com.news.dao_GT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_GT.Paiement_terrain;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Paiement_terrainDI implements Paiement_terrainDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO paiement_terrain (terrain,client,montantP,montantR,modeP,dateP) VALUES (?,?,?,?,?,?)";

	@Override 
	 public boolean creerPaiement_terrain(Paiement_terrain paiement_terrain, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, paiement_terrain.getTerrain());
				  req.setString(2, paiement_terrain.getClient());
				  req.setInt(3, paiement_terrain.getMontantP());
				  req.setInt(4, paiement_terrain.getMontantR());
				  req.setString(5, paiement_terrain.getModeP());
				  req.setString(6, paiement_terrain.getDateP());
				 				  				  
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
	public boolean supprimerPaiement_terrain(Paiement_terrain paiement_terrain, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM paiement_terrain WHERE id=?");
				req.setLong(1, paiement_terrain.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean supprimerPaiement_terrainS(Paiement_terrain paiement_terrain, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM paiement_terrain WHERE client=?");
				 req.setString(1, paiement_terrain.getClient());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public Paiement_terrain getPaiement_terrain(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Paiement_terrain paiement_terrain = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM paiement_terrain WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 paiement_terrain = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return paiement_terrain;
	}
	
	@Override
	public Paiement_terrain getPaiement_terrain(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Paiement_terrain paiement_terrain = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM paiement_terrain WHERE client=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 paiement_terrain = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return paiement_terrain;
	}
	
	@Override
	public Paiement_terrain getPaiement_terrainC(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Paiement_terrain paiement_terrain = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM paiement_terrain WHERE terrain=?");
				req.setString(1, code);
				res = req.executeQuery();
				 while (res.next()) {
					 paiement_terrain = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDAgence.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return paiement_terrain;
	}
	
	private static Paiement_terrain map(ResultSet res) throws SQLException {
		Paiement_terrain paiement_terrain= new Paiement_terrain();

			paiement_terrain.setId(res.getLong("id"));
			paiement_terrain.setTerrain(res.getString("terrain"));
			paiement_terrain.setClient(res.getString("client"));
			paiement_terrain.setMontantP(res.getInt("montantP"));
			paiement_terrain.setMontantR(res.getInt("montantR"));
			paiement_terrain.setModeP(res.getString("modeP"));
			paiement_terrain.setDateP(res.getString("dateP"));
									
			return paiement_terrain;
		}
	

}
