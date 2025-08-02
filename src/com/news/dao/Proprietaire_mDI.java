package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Proprietaire_m;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;

public class Proprietaire_mDI implements Proprietaire_mDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO proprietaire_m (matricule,nom,adresse,tel,tel_fax,email,site_web,directeur,date_ajout,logo,prenom,localisation,adresse_postal,tels,gps,taux,dure_contrat,type_contrat) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE proprietaire_m SET matricule=?,nom=?,adresse=?,tel=?,tel_fax=?,email=?,site_web=?,directeur=?,date_ajout=?,logo=?,prenom=?,localisation=?,adresse_postal=?,tels=?,gps=?,taux=?,dure_contrat=?,type_contrat=? WHERE id=?";

	 @Override 
	  public boolean creerProprietaire_m(Proprietaire_m proprietaire_m, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, proprietaire_m.getMatricule());
				  req.setString(2, proprietaire_m.getNom());
				  req.setString(3, proprietaire_m.getAdresse());
				  req.setString(4, proprietaire_m.getTel());
				  req.setString(5, proprietaire_m.getTel_fax());
				  req.setString(6, proprietaire_m.getEmail());
				  req.setString(7, proprietaire_m.getSite_web());
				  req.setString(8, proprietaire_m.getDirecteur());
				  req.setString(9, proprietaire_m.getDate_ajout());
				  req.setString(10, proprietaire_m.getLogo());				 
				  req.setString(11, proprietaire_m.getCode());				  
				  req.setString(12, proprietaire_m.getLocalisation());
				  req.setString(13, proprietaire_m.getAdresse_postal());
				  req.setString(14, proprietaire_m.getTels());
				  req.setString(15, proprietaire_m.getGps());				  
				  req.setInt(16, proprietaire_m.getTaux());
				  req.setInt(17, proprietaire_m.getDure_contrat());
				  req.setString(18, proprietaire_m.getType_contrat());
				  
				  
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
		  }		  return false;
	  }
	 
	 @Override
		public boolean supprimerProprietaire_m(Proprietaire_m proprietaire_m, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM proprietaire_m WHERE id=?");
					req.setLong(1, proprietaire_m.getId());

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
		public boolean modifierProprietaire_m(Proprietaire_m proprietaire_m, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			 if (null!=cn) {
				 try {
					 req=cn.prepareStatement(SQL_UPDATE);
					 req.setString(1, proprietaire_m.getMatricule());
					  req.setString(2, proprietaire_m.getNom());
					  req.setString(2, proprietaire_m.getAdresse());
					  req.setString(3, proprietaire_m.getTel());
					  req.setString(4, proprietaire_m.getTel_fax());
					  req.setString(5, proprietaire_m.getEmail());
					  req.setString(6, proprietaire_m.getSite_web());
					  req.setString(7, proprietaire_m.getDirecteur());
					  req.setString(8, proprietaire_m.getDate_ajout());
					  req.setString(9, proprietaire_m.getLogo());				 
					  req.setString(11, proprietaire_m.getCode());				  
					  req.setString(12, proprietaire_m.getLocalisation());
					  req.setString(13, proprietaire_m.getAdresse_postal());
					  req.setString(14, proprietaire_m.getTels());
					  req.setString(15, proprietaire_m.getGps());				  
					  req.setInt(16, proprietaire_m.getTaux());
					  req.setInt(17, proprietaire_m.getDure_contrat());
					  req.setString(18, proprietaire_m.getType_contrat());
		              req.setLong(19, proprietaire_m.getId());
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
		public Proprietaire_m getProprietaire_m(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Proprietaire_m proprietaire_m = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM proprietaire_m WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					 while (res.next()) {
						 proprietaire_m = map(res);
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
			return proprietaire_m;
		}
	 
	 @Override
		public Proprietaire_m getProprietaire_m(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Proprietaire_m proprietaire_m = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM proprietaire_m WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 proprietaire_m = map(res);
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
			return proprietaire_m;
		}
	 
	 @Override
		public Boolean getVerifierProprietaire_m(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM proprietaire_m WHERE matricule=?");
					req.setString(1, matricule);
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
	 
	 private static Proprietaire_m map(ResultSet res) throws SQLException {
		 Proprietaire_m proprietaire_m = new Proprietaire_m();

			proprietaire_m.setId(res.getLong("id"));
			proprietaire_m.setMatricule(res.getString("matricule"));
			proprietaire_m.setNom(res.getString("nom"));
			proprietaire_m.setAdresse(res.getString("adresse"));
			proprietaire_m.setTel(res.getString("tel"));
			proprietaire_m.setTel_fax(res.getString("tel_fax"));
			proprietaire_m.setEmail(res.getString("email"));
			proprietaire_m.setSite_web(res.getString("site_web"));
			proprietaire_m.setDirecteur(res.getString("directeur"));
			proprietaire_m.setDate_ajout(res.getString("date_ajout"));
			proprietaire_m.setLogo(res.getString("logo"));
			proprietaire_m.setCode(res.getString("prenom"));
//===================NEW CHAMP=======================================//			
			proprietaire_m.setLocalisation(res.getString("localisation"));
			proprietaire_m.setAdresse_postal(res.getString("adresse_postal"));
			proprietaire_m.setTels(res.getString("tels"));
			proprietaire_m.setGps(res.getString("gps"));
			proprietaire_m.setTaux(res.getInt("taux"));			
			proprietaire_m.setDure_contrat(res.getInt("dure_contrat"));
			proprietaire_m.setType_contrat(res.getString("type_contrat"));
			
			return proprietaire_m;
		}

}
