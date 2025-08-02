package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Partenaire;
import com.news.controllers.CRUDPartenaire;
import com.news.controllers.CRUDUser;

public class PartenaireDI implements PartenaireDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO partenaire (nom,prenom,matricule,type,adresse,email,tel,site_web,date_crea) VALUES (?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE partenaire SET nom=?,prenom=?,matricule=?,type=?,adresse=?,email=?,tel=?,site_web=?,date_crea=? WHERE id=?";

	@Override 
	  public boolean creerPartenaire(Partenaire partenaire, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, partenaire.getNom());
				  req.setString(2, partenaire.getPrenom());
				  req.setString(3, partenaire.getMatricule());
				  req.setString(4, partenaire.getType());
				  req.setString(5, partenaire.getAdresse());			
				  req.setString(6, partenaire.getEmail());
				  req.setString(7, partenaire.getTel());
				  req.setString(8, partenaire.getSite_web());
				  req.setString(9, partenaire.getDate_crea());	
				  
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
	public boolean supprimerPartenaire(Partenaire partenaire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM partenaire WHERE id=?");
				req.setLong(1, partenaire.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDPartenaire.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDPartenaire.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean modifierPartenaire(Partenaire partenaire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, partenaire.getNom());
				  req.setString(2, partenaire.getPrenom());
				  req.setString(3, partenaire.getMatricule());
				  req.setString(4, partenaire.getType());
				  req.setString(5, partenaire.getAdresse());			
				  req.setString(6, partenaire.getEmail());
				  req.setString(7, partenaire.getTel());
				  req.setString(8, partenaire.getSite_web());
				  req.setString(9, partenaire.getDate_crea());	
				  req.setLong(10, partenaire.getId());
		             return (1==req.executeUpdate());
						
				 } catch (SQLException ex) {
		               Logger.getLogger(CRUDPartenaire.class.getName()).log(Level.SEVERE, null, ex);
		                errorMsg.set(ex.getMessage());
		            } finally {
		                try {
		                    req.close();
		                    cn.close();
		                } catch (SQLException ex) {
		                   Logger.getLogger(CRUDPartenaire.class.getName()).log(Level.SEVERE, null, ex);
		                   errorMsg.set(ex.getMessage());
		                }
		            }
			 }
			 return false;
		}
	
	@Override
	public Partenaire getPartenaire(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Partenaire partenaire = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM partenaire WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 partenaire = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDPartenaire.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDPartenaire.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return partenaire;
	}
	
	@Override
	public Boolean getPartenaireVerifie(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM partenaire WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 verifie = true;
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDPartenaire.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDPartenaire.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return verifie;
	}
	
	private static Partenaire map(ResultSet res) throws SQLException {
		Partenaire partenaire = new Partenaire();

			partenaire.setId(res.getLong("id"));
			partenaire.setNom(res.getString("nom"));
			partenaire.setPrenom(res.getString("prenom"));
			partenaire.setMatricule(res.getString("matricule"));
			partenaire.setType(res.getString("type"));
			partenaire.setAdresse(res.getString("adresse"));
			partenaire.setEmail(res.getString("email"));
			partenaire.setTel(res.getString("tel"));
			partenaire.setSite_web(res.getString("site_web"));
			partenaire.setDate_crea(res.getString("date_crea"));
					
			return partenaire;
	}

}
