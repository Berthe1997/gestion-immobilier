package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Reception_client;
import com.news.controllers.CRUDUser;

public class Reception_clientDI implements Reception_clientDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO reception_client (matricule,nom,tel,date_appel,chambre,statutP,prix,zone,statut,code) VALUES (?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE reception_client SET date_appel=?,chambre=?,statutP=?,prix=?,zone=?,statut=? WHERE id=?";

	 @Override
	  public boolean creerReception_client(Reception_client reception_client, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, reception_client.getMatricule());
				  req.setString(2, reception_client.getNom());
				  req.setString(3, reception_client.getTel());
				  req.setString(4, reception_client.getDate_appel());
				  req.setString(5, reception_client.getChambre());
				  req.setInt(6, reception_client.getStatutP());
				  req.setInt(7, reception_client.getPrix());
				  req.setString(8, reception_client.getZone());
				  req.setString(9, reception_client.getStatut());
				  req.setString(10, reception_client.getCode());
				  				  
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
		public boolean supprimerReception_client(Reception_client reception_client, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM reception_client WHERE id=?");
					req.setLong(1, reception_client.getId());

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
			public boolean modifierReception_client(Reception_client reception_client, AtomicReference<String> errorMsg) {
				PreparedStatement req = null;
				Connection cn = GetConnexion.getConnection();
				 if (null!=cn) {
					 try {
						 req=cn.prepareStatement(SQL_UPDATE);						
						  req.setString(1, reception_client.getDate_appel());
						  req.setString(2, reception_client.getChambre());
						  req.setInt(3, reception_client.getStatutP());
						  req.setInt(4, reception_client.getPrix());
						  req.setString(5, reception_client.getZone());
						  req.setString(6, reception_client.getStatut());						 
			              req.setLong(7, reception_client.getId());
			              
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
		public boolean modifierReception_clientS(Reception_client reception_client, AtomicReference<String> errorMsg) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			 if (null!=cn) {
				 try {
					 req = cn.prepareStatement("UPDATE reception_client SET statutP=? WHERE matricule=?");										  
					  req.setInt(1, reception_client.getStatutP());						 
		              req.setString(2, reception_client.getMatricule());		              
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
			public Reception_client getReception_client(int id) {
				PreparedStatement req = null;
				Connection cn = GetConnexion.getConnection();
				ResultSet res = null;
				Reception_client reception_client = null;

				if (null != cn) {
					try {
						req = cn.prepareStatement("SELECT * FROM reception_client WHERE id=?");
						req.setLong(1, id);
						res = req.executeQuery();
						 while (res.next()) {
							 reception_client = map(res);
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
				return reception_client;
			}
			
	 @Override
				public Reception_client getReception_client(String matricule) {
					PreparedStatement req = null;
					Connection cn = GetConnexion.getConnection();
					ResultSet res = null;
					Reception_client reception_client = null;

					if (null != cn) {
						try {
							req = cn.prepareStatement("SELECT * FROM reception_client WHERE matricule=?");
							req.setString(1, matricule);
							res = req.executeQuery();
							 while (res.next()) {
								 reception_client = map(res);
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
					return reception_client;
				}
				
				 @Override			
				public Boolean getReception_clientVerifie(String matricule) {
					PreparedStatement req = null;
					Connection cn = GetConnexion.getConnection();
					ResultSet res = null;
					Boolean verifie = false;

					if (null != cn) {
						try {
							req = cn.prepareStatement("SELECT * FROM reception_client WHERE matricule=?");
							req.setString(1, matricule);
							res = req.executeQuery();
							 while (res.next()) {
								 verifie = true;
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
					return verifie;
				}
				 
				 private static Reception_client map(ResultSet res) throws SQLException {
					 Reception_client reception_client = new Reception_client();

						reception_client.setId(res.getLong("id"));
						reception_client.setMatricule(res.getString("matricule"));
						reception_client.setNom(res.getString("nom"));
						reception_client.setTel(res.getString("tel"));
						reception_client.setDate_appel(res.getString("date_appel"));
						reception_client.setChambre(res.getString("chambre"));
						reception_client.setStatutP(res.getInt("statutP"));
						reception_client.setPrix(res.getInt("prix"));
						reception_client.setZone(res.getString("zone"));
						reception_client.setStatut(res.getString("statut"));
						reception_client.setCode(res.getString("code"));
						
				 
						return reception_client;
					}
	
}
