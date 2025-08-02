package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Proprietaire;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class ProprietaireDI implements  ProprietaireDao{
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO proprietaire (matricule,nom,prenom,sexe,date_naiss,lieu_naiss,lieu_residence,tel,email,adresse_postal,nationalite,situation_matri,date_ajout,tel_whatsapp,"
			+ "tels,cni,cmu,banque_1,banque_2,rib_1,rib_2,profession,image_cni,photo,taux,dure_contrat,type_contrat,image_contrat) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE proprietaire SET matricule=?,nom=?,prenom=?,sexe=?,date_naiss=?,lieu_naiss=?,lieu_residence=?,tel=?,email=?,adresse_postal=?,nationalite=?,situation_matri=?,date_ajout=?,tel_whatsapp=?,"
			+ "tels=?,cni=?,cmu=?,banque_1=?,banque_2=?,rib_1=?,rib_2=?,profession=?,image_cni=?,photo=?,taux=?,dure_contrat=?,type_contrat=?,image_contrat=?  WHERE id=?";

	 @Override 
	  public boolean creerProprietaire(Proprietaire proprietaire, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, proprietaire.getMatricule());
				  req.setString(2, proprietaire.getNom());
				  req.setString(3, proprietaire.getPrenom());
				  req.setString(4, proprietaire.getSexe());
				  req.setString(5, proprietaire.getDate_naiss());
				  req.setString(6, proprietaire.getLieu_naiss());
				  req.setString(7, proprietaire.getLieu_residence());
				  req.setString(8, proprietaire.getTel());
				  req.setString(9, proprietaire.getEmail());
				  req.setString(10, proprietaire.getAdresse_postal());
				  req.setString(11, proprietaire.getNationalite());
				  req.setString(12, proprietaire.getSituation_matri());
				  req.setString(13, proprietaire.getDate_ajout());
				  req.setString(14, proprietaire.getTel_whatsapp()); 
				//==============DEUXIEME===============================//	
				  req.setString(15, proprietaire.getTels());
				  req.setString(16, proprietaire.getCni());
				  req.setString(17, proprietaire.getCmu());
				  req.setString(18, proprietaire.getBanque_1());
				  req.setString(19, proprietaire.getBanque_2()); 
				  req.setString(20, proprietaire.getRib_1());
				  req.setString(21, proprietaire.getRib_2());
				  req.setString(22, proprietaire.getProfession());
				  req.setString(23, proprietaire.getImage_cni());
				  req.setString(24, proprietaire.getPhoto()); 
				  
				  req.setInt(25, proprietaire.getTaux());
				  req.setInt(26, proprietaire.getDure_contrat());
				  req.setString(27, proprietaire.getType_contrat());
				  req.setString(28, proprietaire.getImage_contrat()); 
				  
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
	  public boolean supprimerProprietaire(Proprietaire proprietaire, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM proprietaire WHERE id=?");
					req.setLong(1, proprietaire.getId());

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
	  public boolean modifierProprietaire(Proprietaire proprietaire, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, proprietaire.getMatricule());
				  req.setString(2, proprietaire.getNom());
				  req.setString(3, proprietaire.getPrenom());
				  req.setString(4, proprietaire.getSexe());
				  req.setString(5, proprietaire.getDate_naiss());
				  req.setString(6, proprietaire.getLieu_naiss());
				  req.setString(7, proprietaire.getLieu_residence());
				  req.setString(8, proprietaire.getTel());
				  req.setString(9, proprietaire.getEmail());
				  req.setString(10, proprietaire.getAdresse_postal());
				  req.setString(11, proprietaire.getNationalite());
				  req.setString(12, proprietaire.getSituation_matri());
				  req.setString(13, proprietaire.getDate_ajout());
				  req.setString(14, proprietaire.getTel_whatsapp()); 
				//==============DEUXIEME===============================//	
				  req.setString(15, proprietaire.getTels());
				  req.setString(16, proprietaire.getCni());
				  req.setString(17, proprietaire.getCmu());
				  req.setString(18, proprietaire.getBanque_1());
				  req.setString(19, proprietaire.getBanque_2()); 
				  req.setString(20, proprietaire.getRib_1());
				  req.setString(21, proprietaire.getRib_2());
				  req.setString(22, proprietaire.getProfession());
				  req.setString(23, proprietaire.getImage_cni());
				  req.setString(24, proprietaire.getPhoto()); 
				  
				  req.setInt(25, proprietaire.getTaux());
				  req.setInt(26, proprietaire.getDure_contrat());
				  req.setString(27, proprietaire.getType_contrat());
				  req.setString(28, proprietaire.getImage_contrat()); 
				  req.setLong(29, proprietaire.getId());
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
		public Proprietaire getProprietaire(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Proprietaire proprietaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM proprietaire WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						proprietaire = UserMap(res);
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
			return proprietaire;
		}
	 
	 @Override
		public Proprietaire getProprietaire(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Proprietaire proprietaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM proprietaire WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						proprietaire = UserMap(res);
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
			return proprietaire;
		}
	 
	 @Override
		public Boolean getVerifierProprietaire(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM proprietaire WHERE matricule=?");
					req.setString(1, matricule);
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
	 
	 private static Proprietaire UserMap(ResultSet res) throws SQLException {
		 Proprietaire proprietaire = new Proprietaire();

			proprietaire.setId(res.getLong("id"));
			proprietaire.setMatricule(res.getString("matricule"));
			proprietaire.setNom(res.getString("nom"));
			proprietaire.setPrenom(res.getString("prenom"));
			proprietaire.setSexe(res.getString("sexe"));
			proprietaire.setDate_naiss(res.getString("date_naiss"));
			proprietaire.setLieu_naiss(res.getString("lieu_naiss"));
			proprietaire.setLieu_residence(res.getString("lieu_residence"));
			proprietaire.setTel(res.getString("tel"));
			proprietaire.setEmail(res.getString("email"));
			proprietaire.setAdresse_postal(res.getString("adresse_postal"));
			proprietaire.setNationalite(res.getString("nationalite"));			
			proprietaire.setSituation_matri(res.getString("situation_matri"));			
			proprietaire.setDate_ajout(res.getString("date_ajout"));
			proprietaire.setTel_whatsapp(res.getString("tel_whatsapp"));	
			//==============DEUXIEME===============================//	
			proprietaire.setTels(res.getString("tels"));
			proprietaire.setCni(res.getString("cni"));
			proprietaire.setCmu(res.getString("cmu"));
			proprietaire.setBanque_1(res.getString("banque_1"));
			proprietaire.setBanque_2(res.getString("banque_2"));
			proprietaire.setRib_1(res.getString("rib_1"));			
			proprietaire.setRib_2(res.getString("rib_2"));			
			proprietaire.setProfession(res.getString("profession"));
			proprietaire.setImage_cni(res.getString("image_cni"));
			proprietaire.setPhoto(res.getString("photo"));
			
			proprietaire.setTaux(res.getInt("taux"));			
			proprietaire.setDure_contrat(res.getInt("dure_contrat"));
			proprietaire.setType_contrat(res.getString("type_contrat"));
			proprietaire.setImage_contrat(res.getString("image_contrat"));
			
			return proprietaire;
		}

}
