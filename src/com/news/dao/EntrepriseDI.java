package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Entreprise;
import com.news.controllers.CRUDEntreprise;
import com.news.controllers.CRUDUser;

public class EntrepriseDI implements EntrepriseDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO entreprise (entreprise,compte_contrib,cnps,banque_1,banque_2,rib_1,rib_2,email,tel,tels,site_web,siege_social,adresse,logo,fibre,cie,sodeci,type_L,gps) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE entreprise SET entreprise=?,compte_contrib=?,cnps=?,banque_1=?,banque_2=?,rib_1=?,rib_2=?,email=?,tel=?,tels=?,site_web=?,siege_social=?,adresse=?,logo=?,fibre=?,cie=?,sodeci=?,type_L=?,gps=? WHERE id=?";

	@Override 
	  public boolean creerEntreprise(Entreprise entreprise, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, entreprise.getEntreprise());
				  req.setString(2, entreprise.getCnps());
				  req.setString(3, entreprise.getTel());
				  req.setString(4, entreprise.getBanque_1());
				  req.setString(5, entreprise.getBanque_2());			
				  req.setString(6, entreprise.getRib_1());
				  req.setString(7, entreprise.getRib_2());
				  req.setString(8, entreprise.getEmail());
				  req.setString(9, entreprise.getTel());				  
				  req.setString(10, entreprise.getTels());	
				  req.setString(11, entreprise.getSite_web());
				  req.setString(12, entreprise.getSiege_social());				  				
				  req.setString(13, entreprise.getAdresse());
				  req.setString(14, entreprise.getLogo());
				  req.setString(15, entreprise.getFibre());
				  req.setString(16, entreprise.getCie());
				  req.setString(17, entreprise.getSodeci());				 
				  req.setString(18, entreprise.getType_L());
				  req.setString(19, entreprise.getGps());
				  
				  
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
	public boolean supprimerEntreprise(Entreprise entreprise, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM entreprise WHERE id=?");
				req.setLong(1, entreprise.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDEntreprise.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDEntreprise.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean modifierEntreprise(Entreprise entreprise, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, entreprise.getEntreprise());
				  req.setString(2, entreprise.getCnps());
				  req.setString(3, entreprise.getTel());
				  req.setString(4, entreprise.getBanque_1());
				  req.setString(5, entreprise.getBanque_2());			
				  req.setString(6, entreprise.getRib_1());
				  req.setString(7, entreprise.getRib_2());
				  req.setString(8, entreprise.getEmail());
				  req.setString(9, entreprise.getTel());				  
				  req.setString(10, entreprise.getTels());	
				  req.setString(11, entreprise.getSite_web());
				  req.setString(12, entreprise.getSiege_social());				  				
				  req.setString(13, entreprise.getAdresse());
				  req.setString(14, entreprise.getLogo());
				  req.setString(15, entreprise.getFibre());
				  req.setString(16, entreprise.getCie());
				  req.setString(17, entreprise.getSodeci());				 
				  req.setString(18, entreprise.getType_L());
				  req.setString(19, entreprise.getGps());
	              req.setLong(20, entreprise.getId());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDEntreprise.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDEntreprise.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public Entreprise getEntreprise(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Entreprise entreprise = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM entreprise WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 entreprise = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDEntreprise.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDEntreprise.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return entreprise;
	}
	
	@Override
	public Boolean getEntrepriseVerifie(String entreprise) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM entreprise WHERE entreprise=?");
				req.setString(1, entreprise);
				res = req.executeQuery();
				 while (res.next()) {
					 verifie = true;
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDEntreprise.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDEntreprise.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return verifie;
	}
	
	private static Entreprise map(ResultSet res) throws SQLException {
		 Entreprise entreprise = new Entreprise();

			entreprise.setId(res.getLong("id"));
			entreprise.setEntreprise(res.getString("entreprise"));
			entreprise.setCompte_contrib(res.getString("compte_contrib"));
			entreprise.setCnps(res.getString("cnps"));
			entreprise.setBanque_1(res.getString("banque_1"));
			entreprise.setBanque_2(res.getString("banque_2"));
			entreprise.setRib_1(res.getString("rib_1"));
			entreprise.setRib_2(res.getString("rib_2"));
			entreprise.setEmail(res.getString("email"));
			entreprise.setTel(res.getString("tel"));
			entreprise.setTels(res.getString("tels"));
			entreprise.setSite_web	(res.getString("site_web"));
			entreprise.setSiege_social(res.getString("siege_social"));
			entreprise.setAdresse(res.getString("adresse"));
			entreprise.setLogo(res.getString("logo"));
			entreprise.setFibre(res.getString("fibre"));
			entreprise.setCie(res.getString("cie"));
			entreprise.setSodeci(res.getString("sodeci"));
			entreprise.setType_L(res.getString("type_L"));
			entreprise.setGps(res.getString("gps"));

			return entreprise;
		}
	
}
