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

import com.news.beans.Compte_gestionnaire;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class Compte_gestionnaireDI implements  Compte_gestionnaireDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO compte_gestionnaire (gestionnaire,propriete,montant_commission,ans,mois) VALUES (?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE compte_gestionnaire SET montant_commission=? WHERE propriete=? AND ans=? AND mois=?";
	private static final String SQL_UPDATEC 			= 
			"UPDATE compte_gestionnaire SET commission_E=? WHERE propriete=? AND ans=? AND mois=?";

	 @Override 
	  public boolean creerCompte_gestionnaire(Compte_gestionnaire compte_gestionnaire, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, compte_gestionnaire.getGestionnaire());
				  req.setString(2, compte_gestionnaire.getPropriete());
				  req.setInt(3, compte_gestionnaire.getMontant_commission());
				  req.setInt(4, compte_gestionnaire.getAns());
				  req.setString(5, compte_gestionnaire.getMois());
				  
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
	  public boolean modifierCompte_gestionnaire(Compte_gestionnaire compte_gestionnaire, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setInt(1, compte_gestionnaire.getMontant_commission());
				  req.setString(2, compte_gestionnaire.getPropriete());
				  req.setInt(3, compte_gestionnaire.getAns());
				  req.setString(4, compte_gestionnaire.getMois());					 
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
	  public boolean modifierCompte_gestionnaireS(Compte_gestionnaire compte_gestionnaire, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATEC);
				  req.setInt(1, compte_gestionnaire.getCommission_E());
				  req.setString(2, compte_gestionnaire.getPropriete());
				  req.setInt(3, compte_gestionnaire.getAns());
				  req.setString(4, compte_gestionnaire.getMois());					 
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
		public  Boolean getVerifierCompte_gestionnaire(String propriete,int ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_gestionnaire WHERE propriete=? AND ans=? AND mois=?");
					req.setString(1, propriete);
					req.setInt(2, ans);
					req.setString(3, mois);
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
		public Compte_gestionnaire getCompte_gestionnaire(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Compte_gestionnaire compte_gestionnaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_gestionnaire WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						compte_gestionnaire = UserMap(res);
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
			return compte_gestionnaire;
		}
	 
	 @Override
		public Compte_gestionnaire getCompte_gestionnaire(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Compte_gestionnaire compte_gestionnaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_gestionnaire WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						compte_gestionnaire = UserMap(res);
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
			return compte_gestionnaire;
		}
	 
	 @Override
		public Compte_gestionnaire getCompte_gestionnaire(String propriete,int ans) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Compte_gestionnaire compte_gestionnaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_gestionnaire WHERE propriete=? AND ans=? limit 1");
					req.setString(1, propriete);
					req.setInt(2, ans);
					res = req.executeQuery();
					if (res.next()) {
						compte_gestionnaire = UserMap(res);
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
			return compte_gestionnaire;
		}
	 
	 
	 @Override
		public Compte_gestionnaire getCompte_gestionnaire(String propriete,int ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Compte_gestionnaire compte_gestionnaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_gestionnaire WHERE propriete=? AND ans=? AND mois=?");
					req.setString(1, propriete);
					req.setInt(2, ans);
					req.setString(3, mois);
					res = req.executeQuery();
					if (res.next()) {
						compte_gestionnaire = UserMap(res);
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
			return compte_gestionnaire;
		}
	 
	 @Override
		public List<Compte_gestionnaire> getAllCompte_gestionnaire(String propriete,int ans) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Compte_gestionnaire> compte_gestionnaire = new ArrayList<Compte_gestionnaire>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_gestionnaire WHERE propriete=? and ans=?");
					req.setString(1, propriete);
					req.setInt(2, ans);
					res = req.executeQuery();
					 while (res.next()) {
						 compte_gestionnaire.add(UserMap(res));
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
			return compte_gestionnaire;
		}
		
		 @Override
			public List<Compte_gestionnaire> getAllCompte_gestionnaire(String propriete,int ans,String mois) {
				PreparedStatement req = null;
				Connection cn = GetConnexion.getConnection();
				ResultSet res = null;
				List<Compte_gestionnaire> compte_gestionnaire = new ArrayList<Compte_gestionnaire>();

				if (null != cn) {
					try {
						req = cn.prepareStatement("SELECT * FROM compte_gestionnaire WHERE propriete=? and ans=? and mois=?");
						req.setString(1, propriete);
						req.setInt(2, ans);
						req.setString(3, mois);
						res = req.executeQuery();
						 while (res.next()) {
							 compte_gestionnaire.add(UserMap(res));
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
				return compte_gestionnaire;
			}
	 
	 private static Compte_gestionnaire UserMap(ResultSet res) throws SQLException {
		 Compte_gestionnaire compte_gestionnaire = new Compte_gestionnaire();

		    compte_gestionnaire.setId(res.getLong("id"));
			compte_gestionnaire.setGestionnaire(res.getString("gestionnaire"));
			compte_gestionnaire.setPropriete(res.getString("propriete"));
			compte_gestionnaire.setMontant_commission(res.getInt("montant_commission"));
			compte_gestionnaire.setCommission_E(res.getInt("commission_E"));
			compte_gestionnaire.setAns(res.getInt("ans"));
			compte_gestionnaire.setMois(res.getString("mois"));
			

			return compte_gestionnaire;
		}

}
