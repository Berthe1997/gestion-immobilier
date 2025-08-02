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

import com.news.beans.Maison;
import com.news.controllers.CRUDMaison;
import com.news.controllers.CRUDUser;

public class MaisonDI implements MaisonDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO maison (site,maison,piece,chambre,type,etage,ascenceur,balcon,terrasse,prix,occupe,matricule,nom_prenom,numero_compteur,numero_sodeci,salon,code) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE maison SET site=?,maison=?,piece=?,chambre=?,type=?,etage=?,ascenceur=?,balcon=?,terrasse=?,prix=?,occupe=?,matricule=?,nom_prenom=?,numero_compteur=?,numero_sodeci=?,salon=? WHERE id=?";
	private static final String SQL_UPDATES 			= 
			"UPDATE maison SET matricule=' ',nom_prenom=?,occupe='0' WHERE matricule=?";
	private static final String SQL_UPDATEM 			= 
			"UPDATE maison SET matricule=?,nom_prenom=?,occupe=? WHERE id=?";
	private static final String SQL_UPDATEML 			= 
			"UPDATE maison SET nom_prenom=? WHERE matricule=?";

	@Override 
	  public boolean creerMaison(Maison maison, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, maison.getSite());
				  req.setString(2, maison.getMaison());
				  req.setString(3, maison.getPiece());
				  req.setString(4, maison.getChambre());
				  req.setString(5, maison.getType());
				  req.setString(6, maison.getEtage());
				  req.setInt(7, maison.getAscenceur());
				  req.setInt(8, maison.getBalcon());
				  req.setInt(9, maison.getTerrasse());
				  req.setInt(10, maison.getPrix());
				  req.setInt(11, maison.getOccupe());
				  req.setString(12, maison.getMatricule());
				  req.setString(13, maison.getNom_prenom());
				  req.setString(14, maison.getNumero_compteur());
				  req.setString(15, maison.getNumero_sodeci());
				  req.setString(16, maison.getSalon());
				  req.setString(17, maison.getCode());
				  
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
	public boolean supprimerMaison(Maison maison, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM maison WHERE id=?");
				req.setLong(1, maison.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean modifierMaison(Maison maison, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, maison.getSite());
				  req.setString(2, maison.getMaison());
				  req.setString(3, maison.getPiece());
				  req.setString(4, maison.getChambre());
				  req.setString(5, maison.getType());
				  req.setString(6, maison.getEtage());
				  req.setInt(7, maison.getAscenceur());
				  req.setInt(8, maison.getBalcon());
				  req.setInt(9, maison.getTerrasse());
				  req.setInt(10, maison.getPrix());
				  req.setInt(11, maison.getOccupe());	
				  req.setString(12, maison.getMatricule());
				  req.setString(13, maison.getNom_prenom());	
				  req.setString(14, maison.getNumero_compteur());
				  req.setString(15, maison.getNumero_sodeci());
				  req.setString(16, maison.getSalon());
	             req.setLong(17, maison.getId());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public boolean modifierMaisonL(Maison maison, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {	
				 req=cn.prepareStatement(SQL_UPDATEML);
				  req.setString(1, maison.getNom_prenom());
				  req.setString(2, maison.getMatricule());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public boolean modifierMaisons(Maison maison, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATES);
				// req.setString(1, maison.getMatricule());
				  req.setString(1, maison.getNom_prenom());				 				 
	             req.setString(2, maison.getMatricule());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	
	@Override
	public boolean modifierMaisonM(Maison maison, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATEM);
				 req.setString(1, maison.getMatricule());
				  req.setString(2, maison.getNom_prenom());				 				 
	             req.setInt(3, maison.getOccupe());
	             req.setLong(4, maison.getId());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public Maison getMaison(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Maison maison = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM maison WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 maison = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return maison;
	}
	
	@Override
	public Maison getMaison(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Maison maison = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM maison WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 maison = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return maison;
	}
	
	@Override
	public Boolean getMaisonVerifie(String site,String maisons) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM maison WHERE site=? and maison=?");
				req.setString(1, site);
				req.setString(2, maisons);
				res = req.executeQuery();
				 while (res.next()) {
					 verifie = true;
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return verifie;
	}
	
	@Override
	public List<Maison> getAllMaison() {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		List<Maison> maison = new ArrayList<Maison>();

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM maison");
				res = req.executeQuery();
				 while (res.next()) {
					 maison.add(map(res));
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDMaison.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return maison;
	}
	
	private static Maison map(ResultSet res) throws SQLException {
		Maison maison = new Maison();

			maison.setId(res.getLong("id"));
			maison.setSite(res.getString("site"));
			maison.setMaison(res.getString("maison"));
			maison.setPiece(res.getString("piece"));
			maison.setChambre(res.getString("chambre"));
			maison.setType(res.getString("type"));
			maison.setEtage(res.getString("etage"));
			maison.setAscenceur(res.getInt("ascenceur"));
			maison.setBalcon(res.getInt("balcon"));
			maison.setTerrasse(res.getInt("terrasse"));
			maison.setPrix(res.getInt("prix"));
			maison.setOccupe(res.getInt("occupe"));
			maison.setMatricule(res.getString("matricule"));
			maison.setNom_prenom(res.getString("nom_prenom"));
			maison.setNumero_compteur(res.getString("numero_compteur"));
			maison.setNumero_sodeci(res.getString("numero_sodeci"));
			maison.setSalon(res.getString("salon"));
			maison.setCode(res.getString("code"));
			
			return maison;
		}

}
