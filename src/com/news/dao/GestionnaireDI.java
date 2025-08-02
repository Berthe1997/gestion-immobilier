package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Gestionnaire;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class GestionnaireDI implements  GestionnaireDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO gestionnaire (matricule,nom,prenom,sexe,date_naiss,lieu_naiss,situation_matrimonial,nationalite,lieu_residence,tel,email,num_cnps,adresse_postal,date_embauchema,anciennete,tel_whatsapp,tels,cni,cmu,banque_1,rib_1,image_cni,photo,site,code) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE gestionnaire SET matricule=?,nom=?,prenom=?,sexe=?,date_naiss=?,lieu_naiss=?,situation_matrimonial=?,nationalite=?,lieu_residence=?,tel=?,email=?,num_cnps=?,adresse_postal=?,date_embauchema=?,anciennete=?,tel_whatsapp=?,tels=?,cni=?,cmu=?,banque_1=?,rib_1=?,image_cni=?,photo=?,site=? WHERE id=?";

	private static final String SQL_UPDATES 			= 
			"UPDATE gestionnaire SET service=? WHERE matricule=?";

	
	 @Override 
	  public boolean creerGestionnaire(Gestionnaire gestionnaire, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, gestionnaire.getMatricule());
				  req.setString(2, gestionnaire.getNom());
				  req.setString(3, gestionnaire.getPrenom());
				  req.setString(4, gestionnaire.getSexe());
				  req.setString(5, gestionnaire.getDate_naiss());
				  req.setString(6, gestionnaire.getLieu_naiss());
				  req.setString(7, gestionnaire.getSituation_matrimonial());
				  req.setString(8, gestionnaire.getNationalite());
				  req.setString(9, gestionnaire.getLieu_residence());
				  req.setString(10, gestionnaire.getTel());
				  req.setString(11, gestionnaire.getEmail());
				  req.setString(12, gestionnaire.getNum_cnps());
				  req.setString(13, gestionnaire.getAdresse_postal());
				  req.setString(14, gestionnaire.getDate_embauchema());
				  req.setString(15, gestionnaire.getAnciennete());
				//==============DEUXIEME===============================//
				  req.setString(16, gestionnaire.getTel_whatsapp()); 						
				  req.setString(17, gestionnaire.getTels());
				  req.setString(18, gestionnaire.getCni());
				  req.setString(19, gestionnaire.getCmu());
				  req.setString(20, gestionnaire.getBanque_1());					
				  req.setString(21, gestionnaire.getRib_1());				
				  req.setString(22, gestionnaire.getImage_cni());
				  req.setString(23, gestionnaire.getPhoto()); 
				  req.setString(24, gestionnaire.getSite());
				  req.setString(25, gestionnaire.getCode());
				  
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
	  public boolean supprimerGestionnaire(Gestionnaire gestionnaire, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM gestionnaire WHERE id=?");
					req.setLong(1, gestionnaire.getId());

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
	  public boolean modifierGestionnaire(Gestionnaire gestionnaire, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, gestionnaire.getMatricule());
				  req.setString(2, gestionnaire.getNom());
				  req.setString(3, gestionnaire.getPrenom());
				  req.setString(4, gestionnaire.getSexe());
				  req.setString(5, gestionnaire.getDate_naiss());
				  req.setString(6, gestionnaire.getLieu_naiss());
				  req.setString(7, gestionnaire.getSituation_matrimonial());
				  req.setString(8, gestionnaire.getNationalite());
				  req.setString(9, gestionnaire.getLieu_residence());
				  req.setString(10, gestionnaire.getTel());
				  req.setString(11, gestionnaire.getEmail());
				  req.setString(12, gestionnaire.getNum_cnps());
				  req.setString(13, gestionnaire.getAdresse_postal());
				  req.setString(14, gestionnaire.getDate_embauchema());
				  req.setString(15, gestionnaire.getAnciennete());
				//==============DEUXIEME===============================//
				  req.setString(16, gestionnaire.getTel_whatsapp()); 						
				  req.setString(17, gestionnaire.getTels());
				  req.setString(18, gestionnaire.getCni());
				  req.setString(19, gestionnaire.getCmu());
				  req.setString(20, gestionnaire.getBanque_1());					
				  req.setString(21, gestionnaire.getRib_1());				
				  req.setString(22, gestionnaire.getImage_cni());
				  req.setString(23, gestionnaire.getPhoto()); 
				  req.setString(24, gestionnaire.getSite());
				  req.setLong(25, gestionnaire.getId());
				 
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
	  public boolean modifierGestionnaireS(Gestionnaire gestionnaire, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATES);				 
				  req.setString(1, gestionnaire.getService());
				  req.setString(2, gestionnaire.getMatricule());
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
		public Gestionnaire getGestionnaire(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Gestionnaire gestionnaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM gestionnaire WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						gestionnaire = UserMap(res);
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
			return gestionnaire;
		}
	 
	 @Override
		public Gestionnaire getGestionnaire(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Gestionnaire gestionnaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM gestionnaire WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						gestionnaire = UserMap(res);
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
			return gestionnaire;
		}
	 
	 @Override
		public Boolean getVerifierGestionnaire(String site,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM gestionnaire WHERE site=? and matricule=? ");
					req.setString(1, site);
					req.setString(2, matricule);
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
	 
	 
	 @Override
		public Integer getLastIndex(String code) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Integer nbre = 0;
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM gestionnaire WHERE matricule like ? order by id desc limit 1");
					req.setString(1, "%"+code+"%");
					res = req.executeQuery();
					if (res.next()) {
						String matSite = res.getString("matricule");
						String leNombre = matSite.substring(matSite.lastIndexOf("-") + 1, matSite.lastIndexOf("."));
						nbre = Integer.parseInt(leNombre) + 1;
					} else nbre = 1;

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
			return nbre;
		}
	 
	 private static Gestionnaire UserMap(ResultSet res) throws SQLException {
		 Gestionnaire gestionnaire = new Gestionnaire();

			gestionnaire.setId(res.getLong("id"));
			gestionnaire.setMatricule(res.getString("matricule"));
			gestionnaire.setNom(res.getString("nom"));
			gestionnaire.setPrenom(res.getString("prenom"));
			gestionnaire.setSexe(res.getString("sexe"));
			gestionnaire.setDate_naiss(res.getString("date_naiss"));
			gestionnaire.setLieu_naiss(res.getString("lieu_naiss"));
			gestionnaire.setSituation_matrimonial(res.getString("situation_matrimonial"));			
			gestionnaire.setNationalite(res.getString("nationalite"));
			gestionnaire.setLieu_residence(res.getString("lieu_residence"));
			gestionnaire.setTel(res.getString("tel"));
			gestionnaire.setEmail(res.getString("email"));
			gestionnaire.setNum_cnps(res.getString("num_cnps"));
			gestionnaire.setAdresse_postal(res.getString("adresse_postal"));			
			gestionnaire.setDate_embauchema(res.getString("date_embauchema"));
			gestionnaire.setAnciennete(res.getString("anciennete"));
			gestionnaire.setService(res.getString("service"));			
			//==============DEUXIEME===============================//
			gestionnaire.setTel_whatsapp(res.getString("tel_whatsapp"));	
			gestionnaire.setTels(res.getString("tels"));
			gestionnaire.setCni(res.getString("cni"));
			gestionnaire.setCmu(res.getString("cmu"));
			gestionnaire.setBanque_1(res.getString("banque_1"));			
			gestionnaire.setRib_1(res.getString("rib_1"));			
			gestionnaire.setImage_cni(res.getString("image_cni"));
			gestionnaire.setPhoto(res.getString("photo"));
			gestionnaire.setSite(res.getString("site"));
			gestionnaire.setCode(res.getString("code"));
			
			return gestionnaire;
		}

}
