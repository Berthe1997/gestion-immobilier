package com.news.dao_GT;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_GT.Terrain;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class TerrainDI implements TerrainDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO terrain (libelle,code,adresse,superficie,type,proprietaire,contact,statut_ju,lot,ilot,prix,prixG,statut,lotissement,date_achat) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE terrain SET libelle=?,code=?,adresse=?,superficie=?,type=?,proprietaire=?,contact=?,statut_ju=?,lot=?,ilot=?,prix=?,prixG=?,lotissement=?,date_achat=? WHERE id=?";
	private static final String SQL_UPDATE_S 			= 
			"UPDATE terrain SET statut=? WHERE code=?";

	
	@Override 
	 public boolean creerTerrain(Terrain terrain, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, terrain.getLibelle());
				  req.setString(2, terrain.getCode());
				  req.setString(3, terrain.getAdresse());
				  req.setString(4, terrain.getSuperficie());
				  req.setString(5, terrain.getType());
				  req.setString(6, terrain.getProprietaire());
				  req.setString(7, terrain.getContact());
				  req.setString(8, terrain.getStatut_ju());
				  req.setString(9, terrain.getLot());
				  req.setString(10, terrain.getIlot());
				  req.setInt(11, terrain.getPrix());
				  req.setInt(12, terrain.getPrixG());
				  req.setInt(13, terrain.getStatut());
				  req.setString(14, terrain.getLotissement());
				  req.setString(15, terrain.getDate_achat());
				  				  
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
	public boolean supprimerTerrain(Terrain terrain, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM terrain WHERE id=?");
				req.setLong(1, terrain.getId());
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
	public boolean modifierTerrain(Terrain terrain, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, terrain.getLibelle());
				  req.setString(2, terrain.getCode());
				  req.setString(3, terrain.getAdresse());
				  req.setString(4, terrain.getSuperficie());
				  req.setString(5, terrain.getType());
				  req.setString(6, terrain.getProprietaire());
				  req.setString(7, terrain.getContact());
				  req.setString(8, terrain.getStatut_ju());
				  req.setString(9, terrain.getLot());
				  req.setString(10, terrain.getIlot());
				  req.setInt(11, terrain.getPrix());	
				  req.setInt(12, terrain.getPrixG());
				  req.setString(13, terrain.getLotissement());
				  req.setString(14, terrain.getDate_achat());
	              req.setLong(15, terrain.getId());
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
	public boolean modifierTerrainS(Terrain terrain, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE_S);
				 req.setInt(1, terrain.getStatut());
				  req.setString(2, terrain.getCode());
				 
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
	public Terrain getTerrain(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Terrain terrain = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM Terrain WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 terrain = map(res);
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
		return terrain;
	}
	
	@Override
	public Terrain getTerrain(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Terrain terrain = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM terrain WHERE code=?");
				req.setString(1, code);
				res = req.executeQuery();
				 while (res.next()) {
					 terrain = map(res);
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
		return terrain;
	}
	
	@Override
	public Boolean getTerrainVerifie(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM terrain WHERE code=?");
				req.setString(1, code);
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
					req = cn.prepareStatement("SELECT * FROM terrain WHERE code like ? order by id desc limit 1");
					req.setString(1, "%"+code+"%");
					res = req.executeQuery();
					if (res.next()) {
						String matSite = res.getString("code");
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
	
	private static Terrain map(ResultSet res) throws SQLException {
		Terrain terrain= new Terrain();

			terrain.setId(res.getLong("id"));
			terrain.setLibelle(res.getString("libelle"));
			terrain.setCode(res.getString("code"));
			terrain.setAdresse(res.getString("adresse"));
			terrain.setSuperficie(res.getString("superficie"));
			terrain.setType(res.getString("type"));
			terrain.setProprietaire(res.getString("proprietaire"));
			terrain.setContact(res.getString("contact"));
			terrain.setStatut_ju(res.getString("statut_ju"));
			terrain.setLot(res.getString("lot"));
			terrain.setIlot(res.getString("ilot"));
			terrain.setPrix(res.getInt("prix"));
			terrain.setPrixG(res.getInt("prixG"));
			terrain.setStatut(res.getInt("statut"));
			terrain.setLotissement(res.getString("lotissement"));
			terrain.setDate_achat(res.getString("date_achat"));
									
			return terrain;
		}
	

}
