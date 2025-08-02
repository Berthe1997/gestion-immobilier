package com.news.dao_GT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;


import com.news.beans_GT.Client_acheteur;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Client_acheteurDI implements Client_acheteurDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO client_acheteur (nom,prenom,matricule,cni,Email,terrain,prix,type,date,acanal_ac,sexe,contact,honoraire,date_naissance,nationnalite,profession,adresse,depot_DATTC,dossier_techn_CT,dossier_techn_D,attes_CD,intro_dem_ACD,date_BM,n_DM,trans_DM,bornage_cont,transp_pv_bc,creat_ACD,notif_ACD,pf_ACD,ref_ACD,situation_matr,creat_TF,trans_TF) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE client_acheteur SET nom=?,prenom=?,matricule=?,cni=?,Email=?,terrain=?,type=?,date=?,acanal_ac=?,sexe=?,contact=?,honoraire=?,prix=?,date_naissance=?,nationnalite=?,profession=?,adresse=?,depot_DATTC=?,dossier_techn_CT=?,dossier_techn_D=?,attes_CD=?,intro_dem_ACD=?,date_BM=?,n_DM=?,trans_DM=?,bornage_cont=?,transp_pv_bc=?,creat_ACD=?,notif_ACD=?,pf_ACD=?,ref_ACD=?,situation_matr=?,creat_TF=?,trans_TF=? WHERE id=?";

	@Override 
	 public boolean creerClient_acheteur(Client_acheteur client_acheteur, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, client_acheteur.getNom());
				  req.setString(2, client_acheteur.getPrenom());
				  req.setString(3, client_acheteur.getMatricule());
				  req.setString(4, client_acheteur.getCni());
				  req.setString(5, client_acheteur.getEmail());
				  req.setString(6, client_acheteur.getTerrain());
				  req.setInt(7, client_acheteur.getPrix());
				  req.setString(8, client_acheteur.getType());
				  req.setString(9, client_acheteur.getDate());
				  req.setString(10, client_acheteur.getAcanal_ac());
				  req.setString(11, client_acheteur.getSexe());
				  req.setString(12, client_acheteur.getContact());
				  req.setInt(13, client_acheteur.getHonoraire());
				//---------NEW CHAMPS----------------------//
				  req.setString(14, client_acheteur.getDate_naissance());
				  req.setString(15, client_acheteur.getNationnalite());
				  req.setString(16, client_acheteur.getProfession());
				  req.setString(17, client_acheteur.getAdresse());
				  req.setInt(18, client_acheteur.getDepot_DATTC());
				  req.setInt(19, client_acheteur.getDossier_techn_CT());
				  req.setInt(20, client_acheteur.getDossier_techn_D());				  
				  req.setInt(21, client_acheteur.getAttes_CD());
				  req.setInt(22, client_acheteur.getIntro_dem_ACD());
				  req.setInt(23, client_acheteur.getDate_BM());
				  req.setInt(24, client_acheteur.getN_DM());
				  req.setInt(25, client_acheteur.getTrans_DM());
				  req.setInt(26, client_acheteur.getBornage_cont());				  
				  req.setInt(27, client_acheteur.getTransp_pv_bc());
				  req.setInt(28, client_acheteur.getCreat_ACD());
				  req.setInt(29, client_acheteur.getNotif_ACD());
				  req.setInt(30, client_acheteur.getPf_ACD());
				  req.setInt(31, client_acheteur.getRef_ACD());
				  req.setString(32, client_acheteur.getSituation_matr()); 
				  req.setInt(33, client_acheteur.getCreat_TF());
				  req.setInt(34, client_acheteur.getTrans_TF());
								  				  
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
	public boolean supprimerClient_acheteur(Client_acheteur client_acheteur, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM client_acheteur WHERE id=?");
				req.setLong(1, client_acheteur.getId());

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
	public boolean modifierClient_acheteur(Client_acheteur client_acheteur, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, client_acheteur.getNom());
				  req.setString(2, client_acheteur.getPrenom());
				  req.setString(3, client_acheteur.getMatricule());
				  req.setString(4, client_acheteur.getCni());
				  req.setString(5, client_acheteur.getEmail());
				  req.setString(6, client_acheteur.getTerrain());
				  req.setString(7, client_acheteur.getType());
				  req.setString(8, client_acheteur.getDate());
				  req.setString(9, client_acheteur.getAcanal_ac());	
				  req.setString(10, client_acheteur.getSexe());
				  req.setString(11, client_acheteur.getContact());
				  req.setInt(12, client_acheteur.getHonoraire());
				  req.setInt(13, client_acheteur.getPrix());
				//---------NEW CHAMPS----------------------//
				  req.setString(14, client_acheteur.getDate_naissance());
				  req.setString(15, client_acheteur.getNationnalite());
				  req.setString(16, client_acheteur.getProfession());
				  req.setString(17, client_acheteur.getAdresse());
				  req.setInt(18, client_acheteur.getDepot_DATTC());
				  req.setInt(19, client_acheteur.getDossier_techn_CT());
				  req.setInt(20, client_acheteur.getDossier_techn_D());				  
				  req.setInt(21, client_acheteur.getAttes_CD());
				  req.setInt(22, client_acheteur.getIntro_dem_ACD());
				  req.setInt(23, client_acheteur.getDate_BM());
				  req.setInt(24, client_acheteur.getN_DM());
				  req.setInt(25, client_acheteur.getTrans_DM());
				  req.setInt(26, client_acheteur.getBornage_cont());				  
				  req.setInt(27, client_acheteur.getTransp_pv_bc());
				  req.setInt(28, client_acheteur.getCreat_ACD());
				  req.setInt(29, client_acheteur.getNotif_ACD());
				  req.setInt(30, client_acheteur.getPf_ACD());
				  req.setInt(31, client_acheteur.getRef_ACD()); 
				  req.setString(32, client_acheteur.getSituation_matr()); 
				  req.setInt(33, client_acheteur.getCreat_TF());
				  req.setInt(34, client_acheteur.getTrans_TF());
	              req.setLong(35, client_acheteur.getId());
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
	public Client_acheteur getClient_acheteur(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Client_acheteur client_acheteur = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM client_acheteur WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 client_acheteur = map(res);
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
		return client_acheteur;
	}
	
	@Override
	public Client_acheteur getClient_acheteur(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Client_acheteur client_acheteur = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM client_acheteur WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 client_acheteur = map(res);
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
		return client_acheteur;
	}
	
	@Override
	public Boolean getClient_acheteurVerifie(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM client_acheteur WHERE matricule=?");
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
	
	 @Override
		public Integer getLastIndex(String code) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Integer nbre = 0;
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM client_acheteur WHERE matricule like ? order by id desc limit 1");
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
	 
	 @Override
		public List<Client_acheteur> getAllClient_acheteur() {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Client_acheteur> client_acheteur = new ArrayList<Client_acheteur>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM client_acheteur order by nom");		
					res = req.executeQuery();
					 while (res.next()) {
						 client_acheteur.add(map(res));
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
			return client_acheteur;
		}
		
		@Override
		public List<Client_acheteur> getAllClient_acheteurAN() {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Client_acheteur> client_acheteur = new ArrayList<Client_acheteur>();
			 LocalDate today = LocalDate.now();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM client_acheteur WHERE DAY(date_naissance)=? AND MONTH(date_naissance)=?");		
					req.setInt(1, today.getDayOfMonth());
			        req.setInt(2, today.getMonthValue());
					res = req.executeQuery();
					 while (res.next()) {
						 client_acheteur.add(map(res));
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
			return client_acheteur;
		}
		
		private static Client_acheteur map(ResultSet res) throws SQLException {
			Client_acheteur client_acheteur= new Client_acheteur();
			TerrainDI terrainDI= new TerrainDI();

			
				client_acheteur.setId(res.getLong("id"));
				client_acheteur.setNom(res.getString("nom"));
				client_acheteur.setPrenom(res.getString("prenom"));
				client_acheteur.setMatricule(res.getString("matricule"));
				client_acheteur.setCni(res.getString("cni"));
				client_acheteur.setEmail(res.getString("Email"));
				client_acheteur.setTerrain(res.getString("terrain"));
				client_acheteur.setPrix(res.getInt("prix"));
				client_acheteur.setType(res.getString("type"));
				client_acheteur.setDate(res.getString("date"));
				client_acheteur.setAcanal_ac(res.getString("acanal_ac"));
				client_acheteur.setSexe(res.getString("sexe"));
				client_acheteur.setContact(res.getString("contact"));
				client_acheteur.setHonoraire(res.getInt("honoraire"));
				//---------NEW CHAMPS----------------------//
				client_acheteur.setDate_naissance(res.getString("date_naissance"));
				client_acheteur.setNationnalite(res.getString("nationnalite"));
				client_acheteur.setProfession(res.getString("profession"));
				client_acheteur.setAdresse(res.getString("adresse"));			
				client_acheteur.setDepot_DATTC(res.getInt("depot_DATTC"));
				client_acheteur.setDossier_techn_CT(res.getInt("dossier_techn_CT"));
				client_acheteur.setDossier_techn_D(res.getInt("dossier_techn_D"));
				client_acheteur.setAttes_CD(res.getInt("attes_CD"));
				client_acheteur.setIntro_dem_ACD(res.getInt("intro_dem_ACD"));
				client_acheteur.setDate_BM(res.getInt("date_BM"));
				client_acheteur.setN_DM(res.getInt("n_DM"));
				client_acheteur.setTrans_DM(res.getInt("trans_DM"));			
				client_acheteur.setBornage_cont(res.getInt("bornage_cont"));
				client_acheteur.setTransp_pv_bc(res.getInt("transp_pv_bc"));
				client_acheteur.setCreat_ACD(res.getInt("creat_ACD"));
				client_acheteur.setNotif_ACD(res.getInt("notif_ACD"));
				client_acheteur.setPf_ACD(res.getInt("pf_ACD"));
				client_acheteur.setRef_ACD(res.getInt("ref_ACD"));
				client_acheteur.setSituation_matr(res.getString("situation_matr"));
				client_acheteur.setCreat_TF(res.getInt("creat_TF"));
				client_acheteur.setTrans_TF(res.getInt("trans_TF"));
				
				client_acheteur.setTerrains(terrainDI.getTerrain(res.getString( "terrain" )));		
				
				return client_acheteur;
				
		}
	
}
