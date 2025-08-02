package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Pourcentage;
import com.news.controllers.CRUDUser;

public class PourcentageDI implements PourcentageDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO pourcentage (pourcentage,taux,commentaire,nbre,code,site) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE pourcentage SET pourcentage=?,taux=?,commentaire=?,nbre=? WHERE id=?";
	@Override
	public boolean creerPourcentage(Pourcentage pourcentage, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
				 req.setString(1, pourcentage.getLibelle());
	             req.setInt(2, pourcentage.getTaux());
	             req.setString(3, pourcentage.getCommentaire());
	             req.setInt(4, pourcentage.getNbre());
	             req.setString(5, pourcentage.getCode());
	             req.setString(6, pourcentage.getSite());
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
	public boolean supprimerPourcentage(Pourcentage pourcentage, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM pourcentage WHERE id=?");
				req.setLong(1, pourcentage.getId());

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
	public boolean modifierPourcentage(Pourcentage pourcentage, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, pourcentage.getLibelle());
	             req.setInt(2, pourcentage.getTaux());
	             req.setString(3, pourcentage.getCommentaire());
	             req.setInt(4, pourcentage.getNbre());
	             req.setLong(5, pourcentage.getId());
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
	public Pourcentage getPourcentage(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Pourcentage pourcentage = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM pourcentage WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 pourcentage = map(res);
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
		return pourcentage;
	}
	
	@Override
	public Pourcentage getPourcentageN(int nbre) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Pourcentage pourcentage = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM pourcentage WHERE nbre=?");
				req.setLong(1, nbre);
				res = req.executeQuery();
				 while (res.next()) {
					 pourcentage = map(res);
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
		return pourcentage;
	}
	
	@Override
	public Pourcentage getPourcentage(String pourcentages) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Pourcentage pourcentage = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM pourcentage WHERE pourcentage=?");
				req.setString(1, pourcentages);
				res = req.executeQuery();
				 while (res.next()) {
					 pourcentage = map(res);
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
		return pourcentage;
	}
	
	
	@Override
	public Boolean getPourcentageVerifie(String pourcentages) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM pourcentage WHERE pourcentage=?");
				req.setString(1, pourcentages);
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
	
	private static Pourcentage map(ResultSet resultSet) throws SQLException {
		Pourcentage pourcentage = new Pourcentage();
		
		pourcentage.setId(resultSet.getLong("id"));
		pourcentage.setLibelle( resultSet.getString( "pourcentage" ) );
		pourcentage.setTaux( resultSet.getInt( "taux" ) );
		pourcentage.setCommentaire( resultSet.getString( "commentaire" ) );
		pourcentage.setNbre( resultSet.getInt( "nbre" ) );
		pourcentage.setCode( resultSet.getString( "code" ) );
		pourcentage.setSite( resultSet.getString( "site" ) );
		
		return pourcentage;
	}

}
