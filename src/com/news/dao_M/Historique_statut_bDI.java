package com.news.dao_M;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_M.Historique_statut_b;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Historique_statut_bDI implements Historique_statut_bDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO historique_statut_b (agence,bien,ancien_statut,nouveau_statut,date_changement,motif) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE historique_statut_b SET agence=?,bien=?,ancien_statut=?,nouveau_statut=?,date_changement=?,motif=? WHERE id=?";

	@Override 
	 public boolean creerHistorique_statut_b(Historique_statut_b historique_statut_b, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, historique_statut_b.getAgence());
				  req.setString(2, historique_statut_b.getBien());
				  req.setString(3, historique_statut_b.getAncien_statut());
				  req.setString(4, historique_statut_b.getNouveau_statut());
				  req.setString(5, historique_statut_b.getDate_changement());
				  req.setString(6, historique_statut_b.getMotif());
						  				  
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
	public boolean supprimerHistorique_statut_b(Historique_statut_b historique_statut_b, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM historique_statut_b WHERE id=?");
				req.setLong(1, historique_statut_b.getId());

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
	public boolean modifierHistorique_statut_b(Historique_statut_b historique_statut_b, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, historique_statut_b.getAgence());
				  req.setString(2, historique_statut_b.getBien());
				  req.setString(3, historique_statut_b.getAncien_statut());
				  req.setString(4, historique_statut_b.getNouveau_statut());
				  req.setString(5, historique_statut_b.getDate_changement());
				  req.setString(6, historique_statut_b.getMotif());
	              req.setLong(7, historique_statut_b.getId());
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
	public Historique_statut_b getHistorique_statut_b(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Historique_statut_b historique_statut_b = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM historique_statut_b WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 historique_statut_b = map(res);
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
		return historique_statut_b;
	}
	
	@Override
	public Historique_statut_b getHistorique_statut_b(String bien) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Historique_statut_b historique_statut_b = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM historique_statut_b WHERE bien=?");
				req.setString(1, bien);
				res = req.executeQuery();
				 while (res.next()) {
					 historique_statut_b = map(res);
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
		return historique_statut_b;
	}
	
	 @Override
		public Boolean getHistorique_statut_bVerifie(String bien) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM historique_statut_b WHERE bien=?");
					req.setString(1, bien);
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
	 
	 private static Historique_statut_b map(ResultSet res) throws SQLException {
		 Historique_statut_b historique_statut_b= new Historique_statut_b();

			historique_statut_b.setId(res.getLong("id"));
			historique_statut_b.setAgence(res.getString("agence"));
			historique_statut_b.setBien(res.getString("bien"));
			historique_statut_b.setAncien_statut(res.getString("ancien_statut"));
			historique_statut_b.setNouveau_statut(res.getString("nouveau_statut"));
			historique_statut_b.setDate_changement(res.getString("date_changement"));
			historique_statut_b.setMotif(res.getString("motif"));
			
						
			return historique_statut_b;
		}
	
}
