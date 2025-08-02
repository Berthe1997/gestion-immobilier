package com.news.dao_M;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_M.Bien_meuble;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Bien_meubleDI implements Bien_meubleDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO bien_meuble (agence,bien,type,adresse,statut,loyer_nuit,caution,description,date_ajout,site) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE bien_meuble SET agence=?,bien=?,type=?,adresse=?,statut=?,loyer_nuit=?,caution=?,description=?,date_ajout=?,site=? WHERE id=?";

	private static final String SQL_UPDATES 			= 
			"UPDATE bien_meuble SET statut =? WHERE bien=?";
	
	 @Override 
	 public boolean creerBien_meuble(Bien_meuble bien_meuble, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, bien_meuble.getAgence());
				  req.setString(2, bien_meuble.getBien());
				  req.setString(3, bien_meuble.getType());
				  req.setString(4, bien_meuble.getAdresse());
				  req.setString(5, bien_meuble.getStatut());
				  req.setInt(6, bien_meuble.getLoyer_nuit());
				  req.setInt(7, bien_meuble.getCaution());
				  req.setString(8, bien_meuble.getDescription());
				  req.setString(9, bien_meuble.getDate_ajout());
				  req.setString(10, bien_meuble.getSite());
				  				  
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
		public boolean supprimerBien_meuble(Bien_meuble bien_meuble, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM bien_meuble WHERE id=?");
					req.setLong(1, bien_meuble.getId());

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
		public boolean modifierBien_meuble(Bien_meuble bien_meuble, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			 if (null!=cn) {
				 try {
					 req=cn.prepareStatement(SQL_UPDATE);
					  req.setString(1, bien_meuble.getAgence());
					  req.setString(2, bien_meuble.getBien());
					  req.setString(3, bien_meuble.getType());
					  req.setString(4, bien_meuble.getAdresse());
					  req.setString(5, bien_meuble.getStatut());
					  req.setInt(6, bien_meuble.getLoyer_nuit());
					  req.setInt(7, bien_meuble.getCaution());
					  req.setString(8, bien_meuble.getDescription());
					  req.setString(9, bien_meuble.getDate_ajout());
					  req.setString(10, bien_meuble.getSite());
		              req.setLong(11, bien_meuble.getId());
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
		public boolean modifierBien_meubleS(Bien_meuble bien_meuble, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			 if (null!=cn) {
				 try {
					 req=cn.prepareStatement(SQL_UPDATES);
					  req.setString(1, bien_meuble.getStatut());
					  req.setString(2, bien_meuble.getBien());
					  
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
		public Bien_meuble getBien_meuble(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Bien_meuble bien_meuble = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM bien_meuble WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					 while (res.next()) {
						 bien_meuble = map(res);
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
			return bien_meuble;
		}
	 
	 @Override
		public Bien_meuble getBien_meuble(String bien) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Bien_meuble bien_meuble = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM bien_meuble WHERE bien=?");
					req.setString(1, bien);
					res = req.executeQuery();
					 while (res.next()) {
						 bien_meuble = map(res);
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
			return bien_meuble;
		}
	 
	 @Override
		public Boolean getBien_meubleVerifie(String bien) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM bien_meuble WHERE bien=?");
					req.setString(1, bien);
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
	 
	 private static Bien_meuble map(ResultSet res) throws SQLException {
		 Bien_meuble bien_meuble= new Bien_meuble();

			bien_meuble.setId(res.getLong("id"));
			bien_meuble.setAgence(res.getString("agence"));
			bien_meuble.setBien(res.getString("bien"));
			bien_meuble.setType(res.getString("type"));
			bien_meuble.setAdresse(res.getString("adresse"));
			bien_meuble.setStatut(res.getString("statut"));
			bien_meuble.setLoyer_nuit(res.getInt("loyer_nuit"));
			bien_meuble.setCaution(res.getInt("caution"));
			bien_meuble.setDescription(res.getString("description"));
			bien_meuble.setDate_ajout(res.getString("date_ajout"));
			bien_meuble.setSite(res.getString("site"));
						
			return bien_meuble;
		}

}
