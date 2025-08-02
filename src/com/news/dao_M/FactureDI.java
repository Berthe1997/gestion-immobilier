package com.news.dao_M;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_M.Facture;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class FactureDI implements FactureDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO facture (agence,code_reservation,date,montant_T,statut) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE facture SET agence=?,code_reservation=?,date=?,montant_T=?,statut=? WHERE id=?";

	@Override 
	 public boolean creerFacture(Facture facture, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, facture.getAgence());
				  req.setString(2, facture.getCode_reservation());
				  req.setString(3, facture.getDate());
				  req.setInt(4, facture.getMontant_T());
				  req.setString(5, facture.getStatut());
				 				  				  
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
	public boolean supprimerFacture(Facture facture, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM facture WHERE id=?");
				req.setLong(1, facture.getId());

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
	public boolean modifierFacture(Facture facture, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, facture.getAgence());
				  req.setString(2, facture.getCode_reservation());
				  req.setString(3, facture.getDate());
				  req.setInt(4, facture.getMontant_T());
				  req.setString(5, facture.getStatut());
	              req.setLong(6, facture.getId());
	             return (1==req.executeUpdate());
					
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
	public Facture getFacture(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Facture facture = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM facture WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 facture = map(res);
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
		return facture;
	}
	
	@Override
	public Facture getFacture(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Facture facture = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM facture WHERE code_reservation=?");
				req.setString(1, code);
				res = req.executeQuery();
				 while (res.next()) {
					 facture = map(res);
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
		return facture;
	}
	
	@Override
	public Boolean getFactureVerifie(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM facture WHERE code_reservation=?");
				req.setString(1, code);
				res = req.executeQuery();
				 while (res.next()) {
					 verifie = true;
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
		return verifie;
	}
	
	 private static Facture map(ResultSet res) throws SQLException {
		 Facture facture= new Facture();

			facture.setId(res.getLong("id"));
			facture.setAgence(res.getString("agence"));
			facture.setCode_reservation(res.getString("code_reservation"));
			facture.setDate(res.getString("date"));
			facture.setMontant_T(res.getInt("montant_T"));
			facture.setStatut(res.getString("statut"));
			
			return facture;
		}

}
