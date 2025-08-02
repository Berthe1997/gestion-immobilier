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

import com.news.beans.Agence;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;

public class AgenceDI implements AgenceDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO agence (agence,adresse,tel,tel_fax,email,site_web,directeur,date_ajout,logo,registre_C,code,localisation,adresse_postal,tels,gps,fibre,montant_L,type_L,cie,sodeci,sender) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE agence SET agence=?,adresse=?,tel=?,tel_fax=?,email=?,site_web=?,directeur=?,date_ajout=?,logo=?,registre_C=?,code=?,localisation=?,adresse_postal=?,tels=?,gps=?,fibre=?,montant_L=?,type_L=?,cie=?,sodeci=?,sender=? WHERE id=?";

	 @Override 
	  public boolean creerAgence(Agence agence, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, agence.getAgence());
				  req.setString(2, agence.getAdresse());
				  req.setString(3, agence.getTel());
				  req.setString(4, agence.getTel_fax());
				  req.setString(5, agence.getEmail());
				  req.setString(6, agence.getSite_web());
				  req.setString(7, agence.getDirecteur());
				  req.setString(8, agence.getDate_ajout());
				  req.setString(9, agence.getLogo());
				  req.setString(10, agence.getRegistre_C());
				  req.setString(11, agence.getCode());				  
				  req.setString(12, agence.getLocalisation());
				  req.setString(13, agence.getAdresse_postal());
				  req.setString(14, agence.getTels());
				  req.setString(15, agence.getGps());				  
				  req.setString(16, agence.getFibre());
				  req.setString(17, agence.getMontant_L());
				  req.setString(18, agence.getType_L());
				  req.setString(19, agence.getCie());
				  req.setString(20, agence.getSodeci());
				  req.setString(21, agence.getSender());
				  
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
		public boolean supprimerAgence(Agence agence, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM agence WHERE id=?");
					req.setLong(1, agence.getId());

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
		public boolean modifierAgence(Agence agence, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			 if (null!=cn) {
				 try {
					 req=cn.prepareStatement(SQL_UPDATE);
					 req.setString(1, agence.getAgence());
					  req.setString(2, agence.getAdresse());
					  req.setString(3, agence.getTel());
					  req.setString(4, agence.getTel_fax());
					  req.setString(5, agence.getEmail());
					  req.setString(6, agence.getSite_web());
					  req.setString(7, agence.getDirecteur());
					  req.setString(8, agence.getDate_ajout());
					  req.setString(9, agence.getLogo());
					  req.setString(10, agence.getRegistre_C());
					  req.setString(11, agence.getCode());
					  req.setString(12, agence.getLocalisation());
					  req.setString(13, agence.getAdresse_postal());
					  req.setString(14, agence.getTels());
					  req.setString(15, agence.getGps());				  
					  req.setString(16, agence.getFibre());
					  req.setString(17, agence.getMontant_L());
					  req.setString(18, agence.getType_L());
					  req.setString(19, agence.getCie());
					  req.setString(20, agence.getSodeci());
					  req.setString(21, agence.getSender());
		              req.setLong(22, agence.getId());
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
		public Agence getAgence(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Agence agence = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM agence WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					 while (res.next()) {
						 agence = map(res);
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
			return agence;
		}
	 
	 @Override
		public Agence getAgence(String code) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Agence agence = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM agence WHERE code=?");
					req.setString(1, code);
					res = req.executeQuery();
					 while (res.next()) {
						 agence = map(res);
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
			return agence;
		}
	 
	 @Override
		public Boolean getAgenceVerifie(String agence) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM agence WHERE agence=?");
					req.setString(1, agence);
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
	 
	 @Override
		public List<Agence> getAllAgence() {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Agence> Agences = new ArrayList<Agence>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM Agence");
					res = req.executeQuery();
					 while (res.next()) {
						 Agences.add(map(res));
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
			return Agences;
		}
	 
	 private static Agence map(ResultSet res) throws SQLException {
		 Agence agence = new Agence();

			agence.setId(res.getLong("id"));
			agence.setAgence(res.getString("Agence"));
			agence.setAdresse(res.getString("adresse"));
			agence.setTel(res.getString("tel"));
			agence.setTel_fax(res.getString("tel_fax"));
			agence.setEmail(res.getString("email"));
			agence.setSite_web(res.getString("site_web"));
			agence.setDirecteur(res.getString("directeur"));
			agence.setDate_ajout(res.getString("date_ajout"));
			agence.setLogo(res.getString("logo"));
			agence.setRegistre_C(res.getString("registre_C"));
			agence.setCode(res.getString("code"));
//===================NEW CHAMP=======================================//			
			agence.setLocalisation(res.getString("localisation"));
			agence.setAdresse_postal(res.getString("adresse_postal"));
			agence.setTels(res.getString("tels"));
			agence.setGps(res.getString("gps"));
			agence.setFibre(res.getString("fibre"));
			agence.setMontant_L(res.getString("montant_L"));
			agence.setType_L(res.getString("type_L"));
			agence.setCie(res.getString("cie"));
			agence.setSodeci(res.getString("sodeci"));
			agence.setSender(res.getString("sender"));
			
			return agence;
		}
	 
	 
}
