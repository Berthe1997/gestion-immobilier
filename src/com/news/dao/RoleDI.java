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

import com.news.beans.Roles;
import com.news.controllers.CRUDProfil;

public class RoleDI implements RoleDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO role (site,profil) VALUES (?,?)";
	
	private static final String SQL_UPDATE 			= 
			"UPDATE role SET agence=?,code_site=?,porte=?,rapport=?,ressourceH=?,gestionnaire=?,locataire=?,proprietaire=?,comptabilite=?,paiementL=?,"
			+ "chargeE=?,parametre=?,utilisateur=?,profile=?,pays=?,ville=?,commune=?,quartier=?,pourcentage=?,service=?,role=?,accueil=?,"			
			+ "ajout_AG=?,modif_AG=?,supp_AG=?,ajout_SI=?,modif_SI=?,supp_SI=?,ajout_AP=?,modif_AP=?,supp_AP=?,ajout_GE=?,modif_GE=?,supp_GE=?,"			
			+ "ajout_LO=?,modif_LO=?,supp_LO=?,ajout_PR=?,modif_PR=?,supp_PR=?,ajout_PL=?,supp_PL=?,ajout_UT=?,modif_UT=?,supp_UT=?,"
			+ "ajout_SV=?,modif_SV=?,supp_SV=?,ajout_PO=?,modif_PO=?,supp_PO=?,coutM=?,etat_FL=?,etat_FS=?,etat_FA=?,etat_FM=?,fiche_ID=?,tableau_SU=?,"
			+ "etat_LI=?,bilan_CH=?,etat_FAP=?,etat_FMP=?,etat_FAG=?,etat_FMG=?,etat_FIL=?,commissionE=?,partenaire=?,service_tech=?,g_caution=?,archive=?,"
			+ "archive_cli=?,client=?,ajout_cli=?,modif_cli=?,supp_cli=?,demarcheur=?,ajout_dem=?,modif_dem=?,supp_dem=?,reception_cli=?,ajout_recli=?,modif_recli=?,supp_recli=?,"
			+ "notification=?,arriere=?,ajout_ari=?,modif_ari=?,supp_ari=?,bien_meuble=?,bien=?,ajout_b=?,modif_b=?,supp_b=?,paiement_bm=?,gestion_service=?,rapport_c=?  WHERE id=?";
		


	@Override
	public boolean creerRole(Roles role, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
				 req.setString(1, role.getSite());
	             req.setString(2, role.getProfil());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public boolean supprimerRole(Roles role, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM role WHERE id=?");
				req.setLong(1, role.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean modifierRoleP(Roles role, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				req=cn.prepareStatement("UPDATE role SET profil=? WHERE id=?");
				req.setString(1, role.getProfil());
				req.setLong(2, role.getId());
	            return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	
	@Override
	public boolean modifierRole(Roles role, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				req=cn.prepareStatement(SQL_UPDATE);
				req.setInt(1,role.getAgence());
				req.setInt(2,role.getCode_site());				
				req.setInt(3,role.getPorte());
				req.setInt(4,role.getRapport());
				req.setInt(5,role.getRessourceH());
				req.setInt(6,role.getGestionnaire());
				req.setInt(7,role.getLocataire());
				req.setInt(8,role.getProprietaire());
				req.setInt(9,role.getComptabilite());
				req.setInt(10,role.getPaiementL());
				req.setInt(11,role.getChargeE());								
				req.setInt(12,role.getParametre());				
				req.setInt(13,role.getUtilisateur());
				req.setInt(14,role.getProfile());
				req.setInt(15,role.getPays());
				req.setInt(16,role.getVille());
				req.setInt(17,role.getCommune());
				req.setInt(18,role.getQuartier());
				req.setInt(19,role.getPourcentage());
				req.setInt(20,role.getService());
				req.setInt(21,role.getRole());
				req.setInt(22,role.getAccueil());
				/* ==== LES ACCES ==== */
				req.setInt(23,role.getAjout_AG());
				req.setInt(24,role.getModif_AG());
				req.setInt(25,role.getSupp_AG());
				req.setInt(26,role.getAjout_SI());
				req.setInt(27,role.getModif_SI());								
				req.setInt(28,role.getSupp_SI());				
				req.setInt(29,role.getAjout_AP());
				req.setInt(30,role.getModif_AP());
				req.setInt(31,role.getSupp_AP());
				req.setInt(32,role.getAjout_GE());
				req.setInt(33,role.getModif_GE());
				req.setInt(34,role.getSupp_GE());
				req.setInt(35,role.getAjout_LO());
				req.setInt(36,role.getModif_LO());
				req.setInt(37,role.getSupp_LO());
				req.setInt(38,role.getAjout_PR());
				req.setInt(39,role.getModif_PR());
				req.setInt(40,role.getSupp_PR());
				req.setInt(41,role.getAjout_PL());
				req.setInt(42,role.getSupp_PL());
				/* ==== LES ACCES PARAMETRE==== */
				req.setInt(43,role.getAjout_UT());
				req.setInt(44,role.getModif_UT());
				req.setInt(45,role.getSupp_UT());
				req.setInt(46,role.getAjout_SV());
				req.setInt(47,role.getModif_SV());
				req.setInt(48,role.getSupp_SV());
				req.setInt(49,role.getAjout_PO());
				req.setInt(50,role.getModif_PO());
				req.setInt(51,role.getSupp_PO());				
				/* ==== LES ACCES RAPPORT==== */
				req.setInt(52,role.getCoutM());
				req.setInt(53,role.getEtat_FL());
				req.setInt(54,role.getEtat_FS());
				req.setInt(55,role.getEtat_FA());
				req.setInt(56,role.getEtat_FM());
				req.setInt(57,role.getFiche_ID());								
				req.setInt(58,role.getTableau_SU());				
				req.setInt(59,role.getEtat_LI());
				req.setInt(60,role.getBilan_CH());
				req.setInt(61,role.getEtat_FAP());
				req.setInt(62,role.getEtat_FMP());
				req.setInt(63,role.getEtat_FAG());
				req.setInt(64,role.getEtat_FMG());
				req.setInt(65,role.getEtat_FIL());
				req.setInt(66,role.getCommissionE());
				/* ==== LES ACCES NEW==== */
				req.setInt(67,role.getPartenaire());
				req.setInt(68,role.getService_tech());
				req.setInt(69,role.getG_caution());
				req.setInt(70,role.getArchive());
				/* ==== LES ACCES NEW==== */
				req.setInt(71,role.getArchive_cli());
				req.setInt(72,role.getClient());
				req.setInt(73,role.getAjout_cli());
				req.setInt(74,role.getModif_cli());
				req.setInt(75,role.getSupp_cli());
				req.setInt(76,role.getDemarcheur());								
				req.setInt(77,role.getAjout_dem());				
				req.setInt(78,role.getModif_dem());
				req.setInt(79,role.getSupp_dem());
				req.setInt(80,role.getReception_cli());
				req.setInt(81,role.getAjout_recli());
				req.setInt(82,role.getModif_recli());
				req.setInt(83,role.getSupp_recli());
				req.setInt(84,role.getNotification());
				req.setInt(85,role.getArriere());
				req.setInt(86,role.getAjout_ari());
				req.setInt(87,role.getModif_ari());								
				req.setInt(88,role.getSupp_ari());				
				req.setInt(89,role.getBien_meuble());
				req.setInt(90,role.getBien());
				req.setInt(91,role.getAjout_b());
				req.setInt(92,role.getModif_b());
				req.setInt(93,role.getSupp_b());
				req.setInt(94,role.getPaiement_bm());
				req.setInt(95,role.getGestion_service());
				req.setInt(96,role.getRapport_c());
				req.setLong(97,role.getId());
				
																																										
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public Roles getRole(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Roles role = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM role WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 role = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return role;
	}


	@Override
	public Roles getRole(String site, String profil) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Roles role = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM role WHERE site=? AND profil=?");
				req.setString(1, site);
				req.setString(2, profil);
				res = req.executeQuery();
				 while (res.next()) {
					 role = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return role;
	}

	@Override
	public List<Roles> getAllRole() {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		List<Roles> roles = new ArrayList<Roles>();

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM role");
				res = req.executeQuery();
				 while (res.next()) {
					 roles.add(map(res));
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return roles;
	}
	
	@Override
	public List<Roles> getAllRole(String site, String profil) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		List<Roles> roles = new ArrayList<Roles>();

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM role WHERE site=? AND profil=?");
				req.setString(1, site);
				req.setString(2, profil);
				res = req.executeQuery();
				 while (res.next()) {
					 roles.add(map(res));
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return roles;
	}
	
	private static Roles map(ResultSet resultSet) throws SQLException {
		Roles role = new Roles();
	
		role.setId(resultSet.getLong("id"));
		role.setSite(resultSet.getString( "site" ));
		role.setProfil( resultSet.getString( "profil" ) );
		role.setAgence(resultSet.getInt( "agence" ) );
		role.setCode_site(resultSet.getInt( "code_site" ) );		
		role.setPorte(resultSet.getInt( "porte" ) );
		role.setRapport(resultSet.getInt( "rapport" ) );
		role.setRessourceH(resultSet.getInt( "ressourceH" ) );
		role.setGestionnaire(resultSet.getInt( "gestionnaire" ) );
		role.setLocataire(resultSet.getInt( "locataire" ) );
		role.setProprietaire(resultSet.getInt( "proprietaire" ) );
		role.setComptabilite(resultSet.getInt( "comptabilite" ) );
		role.setPaiementL(resultSet.getInt( "paiementL" ) );
		role.setChargeE(resultSet.getInt( "chargeE" ) );				
		role.setParametre(resultSet.getInt( "parametre" ) );		
		role.setUtilisateur(resultSet.getInt( "utilisateur" ) );
		role.setProfile(resultSet.getInt( "profile" ) );
		role.setPays(resultSet.getInt( "pays" ) );
		role.setVille(resultSet.getInt( "ville" ) );
		role.setCommune(resultSet.getInt( "commune" ) );
		role.setQuartier(resultSet.getInt( "quartier" ) );
		role.setPourcentage(resultSet.getInt( "pourcentage" ) );
		role.setService(resultSet.getInt( "service" ) );
		role.setRole(resultSet.getInt( "role" ) );
		role.setAccueil(resultSet.getInt( "accueil" ) );
		
		/* ==== LES ACCES ==== */
		role.setAjout_AG(resultSet.getInt( "ajout_AG" ) );
		role.setModif_AG(resultSet.getInt( "modif_AG" ) );
		role.setSupp_AG(resultSet.getInt( "supp_AG" ) );				
		role.setAjout_SI(resultSet.getInt( "ajout_SI" ) );		
		role.setModif_SI(resultSet.getInt( "modif_SI" ) );
		role.setSupp_SI(resultSet.getInt( "supp_SI" ) );
		role.setAjout_AP(resultSet.getInt( "ajout_AP" ) );
		role.setModif_AP(resultSet.getInt( "modif_AP" ) );
		role.setSupp_AP(resultSet.getInt( "supp_AP" ) );
		role.setAjout_GE(resultSet.getInt( "ajout_GE" ) );
		role.setModif_GE(resultSet.getInt( "modif_GE" ) );
		role.setSupp_GE(resultSet.getInt( "supp_GE" ) );
		role.setAjout_LO(resultSet.getInt( "ajout_LO" ) );
		role.setModif_LO(resultSet.getInt( "modif_LO" ) );		
		role.setSupp_LO(resultSet.getInt( "supp_LO" ) );
		role.setAjout_PR(resultSet.getInt( "ajout_PR" ) );
		role.setModif_PR(resultSet.getInt( "modif_PR" ) );
		role.setSupp_PR(resultSet.getInt( "supp_PR" ) );
		role.setAjout_PL(resultSet.getInt( "ajout_PL" ) );
		role.setSupp_PL(resultSet.getInt( "supp_PL" ) );
		
		/* ==== LES PARAMETRES ==== */
		role.setAjout_UT(resultSet.getInt( "ajout_UT" ) );
		role.setModif_UT(resultSet.getInt( "modif_UT" ) );
		role.setSupp_UT(resultSet.getInt( "supp_UT" ) );				
		role.setAjout_SV(resultSet.getInt( "ajout_SV" ) );		
		role.setModif_SV(resultSet.getInt( "modif_SV" ) );
		role.setSupp_SV(resultSet.getInt( "supp_SV" ) );
		role.setAjout_PO(resultSet.getInt( "ajout_PO" ) );
		role.setModif_PO(resultSet.getInt( "modif_PO" ) );
		role.setSupp_PO(resultSet.getInt( "supp_PO" ) );
				
		/* ==== LES RAPPORTS ==== */
		role.setCoutM(resultSet.getInt( "coutM" ) );
		role.setEtat_FL(resultSet.getInt( "etat_FL" ) );
		role.setEtat_FS(resultSet.getInt( "etat_FS" ) );
		role.setEtat_FA(resultSet.getInt( "etat_FA" ) );
		role.setEtat_FM(resultSet.getInt( "etat_FM" ) );
		role.setFiche_ID(resultSet.getInt( "fiche_ID") );
		role.setTableau_SU(resultSet.getInt( "tableau_SU" ) );
		role.setEtat_LI(resultSet.getInt( "etat_LI" ) );
		role.setEtat_FAP(resultSet.getInt( "etat_FAP" ) );
		role.setEtat_FMP(resultSet.getInt( "etat_FMP" ) );		
		role.setEtat_FAG(resultSet.getInt( "etat_FAG" ) );
		role.setEtat_FMG(resultSet.getInt( "etat_FMG" ) );
		role.setEtat_FIL(resultSet.getInt( "etat_FIL" ) );
		role.setCommissionE(resultSet.getInt( "commissionE" ) );
		
		/* ==== LES RAPPORTS ==== */
		role.setPartenaire(resultSet.getInt( "partenaire" ) );
		role.setService_tech(resultSet.getInt( "service_tech" ) );
		role.setG_caution(resultSet.getInt( "g_caution" ) );
		role.setArchive(resultSet.getInt( "archive" ) );
		
		/* ==== LES NEW MENU ==== */
		role.setArchive_cli(resultSet.getInt( "archive_cli" ) );
		role.setClient(resultSet.getInt( "client" ) );
		role.setAjout_cli(resultSet.getInt( "ajout_cli" ) );
		role.setModif_cli(resultSet.getInt( "modif_cli" ) );
		role.setSupp_cli(resultSet.getInt( "supp_cli" ) );
		role.setDemarcheur(resultSet.getInt( "demarcheur") );
		role.setAjout_dem(resultSet.getInt( "ajout_dem" ) );
		role.setModif_dem(resultSet.getInt( "modif_dem" ) );
		role.setSupp_dem(resultSet.getInt( "supp_dem" ) );
		role.setReception_cli(resultSet.getInt( "reception_cli" ) );		
		role.setAjout_recli(resultSet.getInt( "ajout_recli" ) );
		role.setModif_recli(resultSet.getInt( "modif_recli" ) );
		role.setSupp_recli(resultSet.getInt( "supp_recli" ) );
		role.setNotification(resultSet.getInt( "notification" ) );
		role.setArriere(resultSet.getInt( "arriere" ) );
		role.setAjout_ari(resultSet.getInt( "ajout_ari") );
		role.setModif_ari(resultSet.getInt( "modif_ari" ) );
		role.setSupp_ari(resultSet.getInt( "supp_ari" ) );
		role.setBien_meuble(resultSet.getInt( "bien_meuble" ) );
		role.setBien(resultSet.getInt( "bien" ) );		
		role.setAjout_b(resultSet.getInt( "ajout_b" ) );
		role.setModif_b(resultSet.getInt( "modif_b" ) );
		role.setSupp_b(resultSet.getInt( "supp_b" ) );
		role.setPaiement_bm(resultSet.getInt( "paiement_bm" ) );
		role.setGestion_service(resultSet.getInt( "gestion_service" ) );
		role.setRapport_c(resultSet.getInt( "rapport_c" ) );
		
		return role;
		}
}
