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

import com.news.beans.Compte_proprietaire;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class Compte_proprietaireDI implements  Compte_proprietaireDao {

	private static final String SQL_INSERT 			=  
			"INSERT INTO compte_proprietaire (proprietaire,matricule,propriete,montant_APC,ans,mois,retraitM) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE compte_proprietaire SET montant_APC=? WHERE propriete=? AND ans=? AND mois=?";
	private static final String SQL_UPDATER 			= 
			"UPDATE compte_proprietaire SET retraitM=? WHERE propriete=? AND ans=? AND mois=?";

	@Override 
	  public boolean creerCompte_proprietaire(Compte_proprietaire compte_proprietaire, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, compte_proprietaire.getProprietaire());
				  req.setString(2, compte_proprietaire.getMatricule());
				  req.setString(3, compte_proprietaire.getPropriete());
				  req.setInt(4, compte_proprietaire.getMontant_APC());
				  req.setInt(5, compte_proprietaire.getAns());
				  req.setString(6, compte_proprietaire.getMois());
				  req.setInt(7, compte_proprietaire.getRetraitM());
				  
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
	  public boolean modifierCompte_proprietaire(Compte_proprietaire compte_proprietaire, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setInt(1, compte_proprietaire.getMontant_APC());
				  req.setString(2, compte_proprietaire.getPropriete());
				  req.setInt(3, compte_proprietaire.getAns());
				  req.setString(4, compte_proprietaire.getMois());				 
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
	  public boolean modifierCompte_proprietaireR(Compte_proprietaire compte_proprietaire, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATER);
				  req.setInt(1, compte_proprietaire.getRetraitM());
				  req.setString(2, compte_proprietaire.getPropriete());
				  req.setInt(3, compte_proprietaire.getAns());
				  req.setString(4, compte_proprietaire.getMois());				 
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
		public  Boolean getVerifierCompte_proprietaire(String propriete,int ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_proprietaire WHERE propriete=? AND ans=? AND mois=?");
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
		public Compte_proprietaire getCompte_proprietaire(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Compte_proprietaire compte_proprietaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_proprietaire WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						compte_proprietaire = UserMap(res);
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
			return compte_proprietaire;
		}
	 
	 @Override
		public Compte_proprietaire getCompte_proprietaire(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Compte_proprietaire compte_proprietaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_proprietaire WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						compte_proprietaire = UserMap(res);
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
			return compte_proprietaire;
		}
	 
	 @Override
		public Compte_proprietaire getCompte_proprietaire(String propriete,int ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Compte_proprietaire compte_proprietaire = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM compte_proprietaire WHERE propriete=? AND ans=? AND mois=?");
					req.setString(1, propriete);
					req.setInt(2, ans);
					req.setString(3, mois);
					res = req.executeQuery();
					if (res.next()) {
						compte_proprietaire = UserMap(res);
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
			return compte_proprietaire;
		}
	 
		
		 @Override
			public List<Compte_proprietaire> getAllCompte_proprietaire(String propriete,int ans) {
				PreparedStatement req = null;
				Connection cn = GetConnexion.getConnection();
				ResultSet res = null;
				List<Compte_proprietaire> compte_proprietaire = new ArrayList<Compte_proprietaire>();

				if (null != cn) {
					try {
						req = cn.prepareStatement("SELECT * FROM compte_proprietaire WHERE propriete=? and ans=?");
						req.setString(1, propriete);
						req.setInt(2, ans);
						res = req.executeQuery();
						 while (res.next()) {
							 compte_proprietaire.add(UserMap(res));
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
				return compte_proprietaire;
			}
			
			 @Override
				public List<Compte_proprietaire> getAllCompte_proprietaire(String propriete,int ans,String mois) {
					PreparedStatement req = null;
					Connection cn = GetConnexion.getConnection();
					ResultSet res = null;
					List<Compte_proprietaire> compte_proprietaire = new ArrayList<Compte_proprietaire>();

					if (null != cn) {
						try {
							req = cn.prepareStatement("SELECT * FROM compte_proprietaire WHERE propriete=? and ans=? and mois=?");
							req.setString(1, propriete);
							req.setInt(2, ans);
							req.setString(3, mois);
							res = req.executeQuery();
							 while (res.next()) {
								 compte_proprietaire.add(UserMap(res));
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
					return compte_proprietaire;
				}
	 
	 private static Compte_proprietaire UserMap(ResultSet res) throws SQLException {
		 Compte_proprietaire compte_proprietaire = new Compte_proprietaire();

		 compte_proprietaire.setId(res.getLong("id"));
		 compte_proprietaire.setProprietaire(res.getString("proprietaire"));
		 compte_proprietaire.setMatricule(res.getString("matricule"));
		 compte_proprietaire.setPropriete(res.getString("propriete"));
		 compte_proprietaire.setMontant_APC(res.getInt("montant_APC"));
		 compte_proprietaire.setAns(res.getInt("ans"));
		 compte_proprietaire.setMois(res.getString("mois"));
		 compte_proprietaire.setRetraitM(res.getInt("retraitM"));
			
			return compte_proprietaire;
		}
	
}
