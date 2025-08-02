package com.news.dao_GT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.joda.time.LocalDate;

import com.news.beans_GT.Dossier_client;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Dossier_clientDI implements Dossier_clientDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO dossier_client (matricule,nom,prenom,tel,adresse,date,sexe,date_naissance,nationnalite,profession,depot_DATTC,dossier_techn_CT,dossier_techn_D,attes_CD,intro_dem_ACD,date_BM,n_DM,trans_DM,bornage_cont,transp_pv_bc,creat_ACD,notif_ACD,pf_ACD,ref_ACD,situation_matr,creat_TF,trans_TF,lot,ilot,lotissement,date_achat) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE dossier_client SET matricule=?,nom=?,prenom=?,tel=?,adresse=?,date=?,sexe=?,date_naissance=?,nationnalite=?,profession=?,depot_DATTC=?,dossier_techn_CT=?,dossier_techn_D=?,attes_CD=?,intro_dem_ACD=?,date_BM=?,n_DM=?,trans_DM=?,bornage_cont=?,transp_pv_bc=?,creat_ACD=?,notif_ACD=?,pf_ACD=?,ref_ACD=?,situation_matr=?,creat_TF=?,trans_TF=?,lot=?,ilot=?,lotissement=?,date_achat=? WHERE id=?";
	
	@Override 
	 public boolean creerDossier_client(Dossier_client dossier_client, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, dossier_client.getMatricule());
				  req.setString(2, dossier_client.getNom());
				  req.setString(3, dossier_client.getPrenom());
				  req.setString(4, dossier_client.getTel());
				  req.setString(5, dossier_client.getAdresse());
				  req.setString(6, dossier_client.getDate());
				  req.setString(7, dossier_client.getSexe());
				//---------NEW CHAMPS----------------------//
				  req.setString(8, dossier_client.getDate_naissance());
				  req.setString(9, dossier_client.getNationnalite());
				  req.setString(10, dossier_client.getProfession());				 
				  req.setInt(11, dossier_client.getDepot_DATTC());
				  req.setInt(12, dossier_client.getDossier_techn_CT());
				  req.setInt(13, dossier_client.getDossier_techn_D());				  
				  req.setInt(14, dossier_client.getAttes_CD());
				  req.setInt(15, dossier_client.getIntro_dem_ACD());
				  req.setInt(16, dossier_client.getDate_BM());
				  req.setInt(17, dossier_client.getN_DM());
				  req.setInt(18, dossier_client.getTrans_DM());
				  req.setInt(19, dossier_client.getBornage_cont());				  
				  req.setInt(20, dossier_client.getTransp_pv_bc());
				  req.setInt(21, dossier_client.getCreat_ACD());
				  req.setInt(22, dossier_client.getNotif_ACD());
				  req.setInt(23, dossier_client.getPf_ACD());
				  req.setInt(24, dossier_client.getRef_ACD());
				  req.setString(25, dossier_client.getSituation_matr());
				  req.setInt(26, dossier_client.getCreat_TF());
				  req.setInt(27, dossier_client.getTrans_TF());
				  
				  req.setString(28, dossier_client.getLot());
				  req.setString(29, dossier_client.getIlot());
				  req.setString(30, dossier_client.getLotissement());
				  req.setString(31, dossier_client.getDate_achat());
				 				  				  
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
	public boolean modifierDossier_client(Dossier_client dossier_client, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, dossier_client.getMatricule());
				  req.setString(2, dossier_client.getNom());
				  req.setString(3, dossier_client.getPrenom());
				  req.setString(4, dossier_client.getTel());
				  req.setString(5, dossier_client.getAdresse());
				  req.setString(6, dossier_client.getDate());
				  req.setString(7, dossier_client.getSexe());
				//---------NEW CHAMPS----------------------//
				  req.setString(8, dossier_client.getDate_naissance());
				  req.setString(9, dossier_client.getNationnalite());
				  req.setString(10, dossier_client.getProfession());				 
				  req.setInt(11, dossier_client.getDepot_DATTC());
				  req.setInt(12, dossier_client.getDossier_techn_CT());
				  req.setInt(13, dossier_client.getDossier_techn_D());				  
				  req.setInt(14, dossier_client.getAttes_CD());
				  req.setInt(15, dossier_client.getIntro_dem_ACD());
				  req.setInt(16, dossier_client.getDate_BM());
				  req.setInt(17, dossier_client.getN_DM());
				  req.setInt(18, dossier_client.getTrans_DM());
				  req.setInt(19, dossier_client.getBornage_cont());				  
				  req.setInt(20, dossier_client.getTransp_pv_bc());
				  req.setInt(21, dossier_client.getCreat_ACD());
				  req.setInt(22, dossier_client.getNotif_ACD());
				  req.setInt(23, dossier_client.getPf_ACD());
				  req.setInt(24, dossier_client.getRef_ACD());
				  req.setString(25, dossier_client.getSituation_matr()); 
				  req.setInt(26, dossier_client.getCreat_TF());
				  req.setInt(27, dossier_client.getTrans_TF());
				  
				  req.setString(28, dossier_client.getLot());
				  req.setString(29, dossier_client.getIlot());
				  req.setString(30, dossier_client.getLotissement());
				  req.setString(31, dossier_client.getDate_achat());
	              req.setLong(32, dossier_client.getId());
	              
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
	public boolean supprimerDossier_client(Dossier_client dossier_client, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM dossier_client WHERE id=?");
				req.setLong(1, dossier_client.getId());

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
	public Dossier_client getDossier_client(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Dossier_client dossier_client = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM dossier_client WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 dossier_client = map(res);
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
		return dossier_client;
	}
	
	@Override
	public Dossier_client getDossier_client(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Dossier_client dossier_client = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM dossier_client WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 dossier_client = map(res);
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
		return dossier_client;
	}
	
	@Override
	public Boolean getDossier_clientVerifie(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM dossier_client WHERE matricule=?");
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
					req = cn.prepareStatement("SELECT * FROM dossier_client WHERE matricule like ? order by id desc limit 1");
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
		public List<Dossier_client> getAllDossier_client() {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Dossier_client> dossier_client = new ArrayList<Dossier_client>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM dossier_client order by nom");		
					res = req.executeQuery();
					 while (res.next()) {
						 dossier_client.add(map(res));
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
			return dossier_client;
		}
		
		@Override
		public List<Dossier_client> getAllDossier_clientAN() {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Dossier_client> dossier_client = new ArrayList<Dossier_client>();
			 LocalDate today = LocalDate.now();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM dossier_client WHERE DAY(date_naissance)=? AND MONTH(date_naissance)=?");		
					req.setInt(1, today.getDayOfMonth());
			        req.setInt(2, today.getMonthOfYear());
					res = req.executeQuery();
					 while (res.next()) {
						 dossier_client.add(map(res));
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
			return dossier_client;
		}
	
	private static Dossier_client map(ResultSet res) throws SQLException {
		Dossier_client dossier_client= new Dossier_client();

			dossier_client.setId(res.getLong("id"));
			dossier_client.setMatricule(res.getString("matricule"));
			dossier_client.setNom(res.getString("nom"));
			dossier_client.setPrenom(res.getString("prenom"));
			dossier_client.setTel(res.getString("tel"));
			dossier_client.setAdresse(res.getString("adresse"));
			dossier_client.setDate(res.getString("date"));
			dossier_client.setSexe(res.getString("sexe"));
			//---------NEW CHAMPS----------------------//
			dossier_client.setDate_naissance(res.getString("date_naissance"));
			dossier_client.setNationnalite(res.getString("nationnalite"));
			dossier_client.setProfession(res.getString("profession"));			
			dossier_client.setDepot_DATTC(res.getInt("depot_DATTC"));
			dossier_client.setDossier_techn_CT(res.getInt("dossier_techn_CT"));
			dossier_client.setDossier_techn_D(res.getInt("dossier_techn_D"));
			dossier_client.setAttes_CD(res.getInt("attes_CD"));
			dossier_client.setIntro_dem_ACD(res.getInt("intro_dem_ACD"));
			dossier_client.setDate_BM(res.getInt("date_BM"));
			dossier_client.setN_DM(res.getInt("n_DM"));
			dossier_client.setTrans_DM(res.getInt("trans_DM"));			
			dossier_client.setBornage_cont(res.getInt("bornage_cont"));
			dossier_client.setTransp_pv_bc(res.getInt("transp_pv_bc"));
			dossier_client.setCreat_ACD(res.getInt("creat_ACD"));
			dossier_client.setNotif_ACD(res.getInt("notif_ACD"));
			dossier_client.setPf_ACD(res.getInt("pf_ACD"));
			dossier_client.setRef_ACD(res.getInt("ref_ACD"));
			dossier_client.setSituation_matr(res.getString("situation_matr"));
			dossier_client.setCreat_TF(res.getInt("creat_TF"));
			dossier_client.setTrans_TF(res.getInt("trans_TF"));
			
			dossier_client.setLot(res.getString("lot"));
			dossier_client.setIlot(res.getString("ilot"));
			dossier_client.setLotissement(res.getString("lotissement"));
			dossier_client.setDate_achat(res.getString("date_achat"));
									
			return dossier_client;
		}
	

}
