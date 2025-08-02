package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Compte;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CrudChargesE;

public class CompteDI implements CompteDao{

	private static final String SQL_INSERT_PAIEMENT_PER			= 
			"INSERT INTO compte (code,site,matricule,soldeP,soldeA,soldeI) "
			+ "VALUES (?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_PAIEMENT_PER	 			= 
			"UPDATE compte SET soldeP=?,soldeA=?,soldeI=? WHERE code=? AND site =?";
	
	@Override
	public boolean creerCompte(Compte compte, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_INSERT_PAIEMENT_PER);
				req.setString(1, compte.getCode());
				req.setString(2, compte.getSite());
				req.setString(3, compte.getMatricule());
				req.setInt(4, compte.getSoldeP());
				req.setInt(5, compte.getSoldeA());
				req.setInt(6, compte.getSoldeI());
								
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
	public boolean modifierCompte(Compte compte, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_UPDATE_PAIEMENT_PER);
				req.setInt(1, compte.getSoldeP());
				req.setInt(2, compte.getSoldeA());
				req.setInt(3, compte.getSoldeI());
				req.setString(4, compte.getCode());
				req.setString(5, compte.getSite());
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
	public Compte getCompte(String code,String site) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Compte compte = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM compte WHERE code=? AND site=?");
				req.setString(1, code);
				req.setString(2, site);
				res = req.executeQuery();
				if (res.next()) {
					compte = map(res);
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
		return compte;
	}
	
	
	 @Override
		public  Boolean getVerifierCompte(String code,String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte WHERE code=? AND site=?");
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
	
	private static Compte map( ResultSet resultSet ) throws SQLException {
		Compte compte = new Compte();
		
		compte.setId(resultSet.getLong("id"));
		compte.setCode(resultSet.getString("code"));
		compte.setSite(resultSet.getString("site"));
		compte.setMatricule(resultSet.getString("matricule"));
		compte.setSoldeP(resultSet.getInt("soldeP"));
		compte.setSoldeA(resultSet.getInt("soldeA"));
		compte.setSoldeI(resultSet.getInt("soldeI"));
		
				
		return compte;
	}
	
}
