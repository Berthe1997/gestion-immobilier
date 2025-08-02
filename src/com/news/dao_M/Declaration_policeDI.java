package com.news.dao_M;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_M.Declaration_police;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Declaration_policeDI implements  Declaration_policeDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO  declaration_police (agence,code_reservation,date_declaration,statut_transmition) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE  declaration_police SET agence=?,code_reservation=?,date_declaration=?,statut_transmition=? WHERE id=?";

	@Override 
	 public boolean creerDeclaration_police(Declaration_police declaration_police, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, declaration_police.getAgence());
				  req.setString(2, declaration_police.getCode_reservation());
				  req.setString(3, declaration_police.getDate_declaration());
				  req.setString(4, declaration_police.getStatut_transmition());
							  				  
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
	public boolean supprimerDeclaration_police(Declaration_police declaration_police, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM declaration_police WHERE id=?");
				req.setLong(1, declaration_police.getId());

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
	public boolean modifierDeclaration_police(Declaration_police declaration_police, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, declaration_police.getAgence());
				  req.setString(2, declaration_police.getCode_reservation());
				  req.setString(3, declaration_police.getDate_declaration());
				  req.setString(4, declaration_police.getStatut_transmition());			
	              req.setLong(5, declaration_police.getId());
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
	public Declaration_police getDeclaration_police(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Declaration_police declaration_police = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM declaration_police WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 declaration_police = map(res);
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
		return declaration_police;
	}
	
	@Override
	public Declaration_police getDeclaration_police(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Declaration_police declaration_police = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM declaration_police WHERE code_reservation=?");
				req.setString(1, code);
				res = req.executeQuery();
				 while (res.next()) {
					 declaration_police = map(res);
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
		return declaration_police;
	}
	
	@Override
	public Boolean getDeclaration_policeVerifie(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM declaration_police WHERE code_reservation=?");
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
	
	private static Declaration_police map(ResultSet res) throws SQLException {
		Declaration_police declaration_police= new Declaration_police();

			declaration_police.setId(res.getLong("id"));
			declaration_police.setAgence(res.getString("agence"));
			declaration_police.setCode_reservation(res.getString("code_reservation"));
			declaration_police.setDate_declaration(res.getString("date_declaration"));
			declaration_police.setStatut_transmition(res.getString("statut_transmition"));
			
						
			return declaration_police;
		}
	
}
