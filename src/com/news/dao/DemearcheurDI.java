package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Demearcheur;
import com.news.controllers.CRUDUser;

public class DemearcheurDI implements DemearcheurDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO demarcheur (matricule,nom,prenom,date_ajout,tels,tel,zone,code) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE demarcheur SET matricule=?,nom=?,prenom=?,tels=?,tel=?,zone=?,code=? WHERE id=?";

	 @Override 
	  public boolean creerDemearcheur(Demearcheur demearcheur, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, demearcheur.getMatricule());
				  req.setString(2, demearcheur.getNom());
				  req.setString(3, demearcheur.getPrenom());
				  req.setString(4, demearcheur.getDate_ajout());
				  req.setString(5, demearcheur.getTels());
				  req.setString(6, demearcheur.getTel());
				  req.setString(7, demearcheur.getZone());
				  req.setString(8, demearcheur.getCode());
				  				  
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
		public boolean supprimerDemearcheur(Demearcheur demearcheur, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM demarcheur WHERE id=?");
					req.setLong(1, demearcheur.getId());

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
		public boolean modifierDemearcheur(Demearcheur demearcheur, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			 if (null!=cn) {
				 try {
					 req=cn.prepareStatement(SQL_UPDATE);
					 req.setString(1, demearcheur.getMatricule());
					  req.setString(2, demearcheur.getNom());
					  req.setString(3, demearcheur.getPrenom());
					  req.setString(4, demearcheur.getTels());
					  req.setString(5, demearcheur.getTel());
					  req.setString(6, demearcheur.getZone());
					  req.setString(7, demearcheur.getCode());
		              req.setLong(8, demearcheur.getId());
		              
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
		public Demearcheur getDemearcheur(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Demearcheur demearcheur = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM demarcheur WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					 while (res.next()) {
						 demearcheur = map(res);
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
			return demearcheur;
		}
	 
	 @Override
		public Demearcheur getDemearcheur(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Demearcheur demearcheur = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM demarcheur WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 demearcheur = map(res);
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
			return demearcheur;
		}
	 
	 @Override
		public Demearcheur getDemearcheurC(String code) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Demearcheur demearcheur = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM demarcheur WHERE code=?");
					req.setString(1, code);
					res = req.executeQuery();
					 while (res.next()) {
						 demearcheur = map(res);
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
			return demearcheur;
		}
	 
	 @Override
		public Boolean getDemearcheurVerifie(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM demarcheur WHERE matricule=?");
					req.setString(1, matricule);
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
	 
	 @Override
		public List<Demearcheur> getAllDemearcheur(String code) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Demearcheur> demearcheur = new ArrayList<Demearcheur>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM demarcheur WHERE code=? ");
					req.setString(1, code);					
					res = req.executeQuery();
					 while (res.next()) {
						 demearcheur.add(map(res));
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
			return demearcheur;
		}
	 
	 private static Demearcheur map(ResultSet res) throws SQLException {
		 Demearcheur demearcheur = new Demearcheur();

			demearcheur.setId(res.getLong("id"));
			demearcheur.setMatricule(res.getString("matricule"));
			demearcheur.setNom(res.getString("nom"));
			demearcheur.setPrenom(res.getString("prenom"));
			demearcheur.setDate_ajout(res.getString("date_ajout"));
			demearcheur.setTels(res.getString("tels"));
			demearcheur.setTel(res.getString("tel"));
			demearcheur.setZone(res.getString("zone"));
			demearcheur.setCode(res.getString("code"));
			
	 
			return demearcheur;
		}
	 
	
}
