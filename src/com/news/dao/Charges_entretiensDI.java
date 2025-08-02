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

import com.news.beans.Charges_entretiens;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class Charges_entretiensDI implements  Charges_entretiensDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO charges_entretiens (site,type_charge,montantD,ans,mois,date,montantDM,porte,code) VALUES (?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_UPDATE_PAIEMENT_PER	 			= 
			"UPDATE charges_entretiens SET montantDM=? WHERE site=? and ans=? and mois=? ORDER BY id DESC LIMIT 1";
	
	 @Override 
	  public boolean creerCharges_entretiens(Charges_entretiens charges_entretiens, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, charges_entretiens.getPropriete());
				  req.setString(2, charges_entretiens.getType_charge());
				  req.setInt(3, charges_entretiens.getMontantD());
				  req.setString(4, charges_entretiens.getAns());
				  req.setString(5, charges_entretiens.getMois());
				  req.setString(6, charges_entretiens.getDate());
				  req.setInt(7, charges_entretiens.getMontantDM());
				  req.setString(8, charges_entretiens.getPorte());
				  req.setString(9, charges_entretiens.getCode());
				  
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
		public boolean modifierCharges_entretiens(Charges_entretiens charges_entretiens, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			if (null != cn) {
				try {
					req = cn.prepareStatement(SQL_UPDATE_PAIEMENT_PER);
					req.setInt(1, charges_entretiens.getMontantDM());				
					req.setString(2, charges_entretiens.getPropriete());
					req.setString(3, charges_entretiens.getMois());
					req.setString(4, charges_entretiens.getDate());
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
	  public boolean supprimerCharges_entretiens(Charges_entretiens charges_entretiens, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM charges_entretiens WHERE id=?");
					req.setLong(1, charges_entretiens.getId());

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
		public  Boolean getVerifierCharges_entretiens(String site,String ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM charges_entretiens WHERE site=? AND ans=? AND mois=?");
					req.setString(1, site);
					req.setString(2, ans);
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
		public Charges_entretiens getCharges_entretiens(String site,String ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Charges_entretiens charges_entretiens = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM charges_entretiens WHERE site=? AND ans=? AND mois=?");
					req.setString(1, site);
					req.setString(2, ans);
					req.setString(3, mois);
					res = req.executeQuery();
					if (res.next()) {
						charges_entretiens = UserMap(res);
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
			return charges_entretiens;
		}
	 
	 @Override
		public Charges_entretiens getCharges_entretiensD(String site,String ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Charges_entretiens charges_entretiens = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM charges_entretiens WHERE site=? AND ans=? AND mois=? ORDER BY mois DESC LIMIT 1");
					req.setString(1, site);
					req.setString(2, ans);
					req.setString(3, mois);
					res = req.executeQuery();
					if (res.next()) {
						charges_entretiens = UserMap(res);
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
			return charges_entretiens;
		}
	 
	 @Override
		public Charges_entretiens getCharges_entretiens(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Charges_entretiens charges_entretiens = null;
		
			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM charges_entretiens WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						charges_entretiens = UserMap(res);
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
			return charges_entretiens;
		}
	 
	 @Override
		public List<Charges_entretiens> getAllCharges_entretiens(String site,String ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Charges_entretiens> charges_entretiens = new ArrayList<Charges_entretiens>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM charges_entretiens WHERE site=? and ans=? and mois=?");
					req.setString(1, site);
					req.setString(2, ans);
					req.setString(3, mois);
					res = req.executeQuery();
					 while (res.next()) {
						 charges_entretiens.add(UserMap(res));
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
			return charges_entretiens;
		}


	 private static Charges_entretiens UserMap(ResultSet res) throws SQLException {
		 Charges_entretiens charges_entretiens = new Charges_entretiens();

		 charges_entretiens.setId(res.getLong("id"));
		 charges_entretiens.setPropriete(res.getString("site"));
		 charges_entretiens.setType_charge(res.getString("type_charge"));
		 charges_entretiens.setMontantD(res.getInt("montantD"));
		 charges_entretiens.setAns(res.getString("ans"));
		 charges_entretiens.setMois(res.getString("mois"));
		 charges_entretiens.setDate(res.getString("date"));
		 charges_entretiens.setMontantDM(res.getInt("montantDM"));
		 charges_entretiens.setPorte(res.getString("porte"));
		 charges_entretiens.setCode(res.getString("code"));
			
			return charges_entretiens;
		}

}
