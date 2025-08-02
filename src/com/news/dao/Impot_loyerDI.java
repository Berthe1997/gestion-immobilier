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

import com.news.beans.Impot_loyer;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class Impot_loyerDI implements  Impot_loyerDao {

	private static final String SQL_INSERT 			=  
			"INSERT INTO impot_loyer (ans,mois,site,proprietaire,matricule,montant) VALUES (?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE impot_loyer SET montant=? WHERE ans=? AND mois=? AND site=?";

	@Override 
	  public boolean creerImpot_loyer(Impot_loyer impot_loyer, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setInt(1, impot_loyer.getAns());
				  req.setString(2, impot_loyer.getMois());
				  req.setString(3, impot_loyer.getSite());
				  req.setString(4, impot_loyer.getProprietaire());
				  req.setString(5, impot_loyer.getMatricule());				
				  req.setInt(6, impot_loyer.getMontant());
				
				  
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
	  public boolean modifierImpot_loyer(Impot_loyer impot_loyer, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setInt(1, impot_loyer.getMontant());
				  req.setInt(2, impot_loyer.getAns());
				  req.setString(3, impot_loyer.getMois());
				  req.setString(4, impot_loyer.getSite());
				 		 
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
		public  Boolean getVerifierImpot_loyer(String site,int ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM impot_loyer WHERE site=? AND ans=? AND mois=?");
					req.setString(1, site);
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
		public Impot_loyer getImpot_loyer(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Impot_loyer impot_loyer = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM impot_loyer WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						impot_loyer = UserMap(res);
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
			return impot_loyer;
		}
	 
	 
	 @Override
		public Impot_loyer getImpot_loyer(String site,int ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Impot_loyer impot_loyer = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM impot_loyer WHERE site=? AND ans=? AND mois=?");
					req.setString(1, site);
					req.setInt(2, ans);
					req.setString(3, mois);
					res = req.executeQuery();
					if (res.next()) {
						impot_loyer = UserMap(res);
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
			return impot_loyer;
		}
	 
	 @Override
		public List<Impot_loyer> getAllImpot_loyer(String propriete,int ans) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Impot_loyer> impot_loyer = new ArrayList<Impot_loyer>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM impot_loyer WHERE site=? and ans=?");
					req.setString(1, propriete);
					req.setInt(2, ans);
					res = req.executeQuery();
					 while (res.next()) {
						 impot_loyer.add(UserMap(res));
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
			return impot_loyer;
		}
		
		 @Override
			public List<Impot_loyer> getAllImpot_loyer(String propriete,int ans,String mois) {
				PreparedStatement req = null;
				Connection cn = GetConnexion.getConnection();
				ResultSet res = null;
				List<Impot_loyer> impot_loyer = new ArrayList<Impot_loyer>();

				if (null != cn) {
					try {
						req = cn.prepareStatement("SELECT * FROM impot_loyer WHERE site=? and ans=? and mois=?");
						req.setString(1, propriete);
						req.setInt(2, ans);
						req.setString(3, mois);
						res = req.executeQuery();
						 while (res.next()) {
							 impot_loyer.add(UserMap(res));
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
				return impot_loyer;
			}
	 
	 private static Impot_loyer UserMap(ResultSet res) throws SQLException {
		 Impot_loyer impot_loyer = new Impot_loyer();

		 impot_loyer.setId(res.getLong("id"));
		 impot_loyer.setAns(res.getInt("ans"));
		 impot_loyer.setMois(res.getString("mois"));
		 impot_loyer.setSite(res.getString("site"));
		 impot_loyer.setProprietaire(res.getString("proprietaire"));
		 impot_loyer.setMatricule(res.getString("matricule"));		
		 impot_loyer.setMontant(res.getInt("montant"));
				
			return impot_loyer;
		}

}
