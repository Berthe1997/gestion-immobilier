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

import com.news.beans_GT.Offre_terrain;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Offre_terrainDI implements Offre_terrainDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO offre_terrain (num_offre,nature,lieu,lot,ilot,superficie,prix,caracteristique,document,statut,date_ajout) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE offre_terrain SET num_offre=?,nature=?,lieu=?,lot=?,ilot=?,superficie=?,prix=?,caracteristique=?,document=?,statut=?,date_ajout=? WHERE id=?";

	@Override 
	 public boolean creerOffre_terrain(Offre_terrain offre_terrain, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, offre_terrain.getNum_offre());
				  req.setString(2, offre_terrain.getNature());
				  req.setString(3, offre_terrain.getLieu());
				  req.setString(4, offre_terrain.getLot());
				  req.setString(5, offre_terrain.getIlot());
				  req.setString(6, offre_terrain.getSuperficie());
				  req.setInt(7, offre_terrain.getPrix());
				  req.setString(8, offre_terrain.getCaracteristique());
				  req.setString(9, offre_terrain.getDocument());
				  req.setInt(10, offre_terrain.getStatut());
				  req.setString(11, offre_terrain.getDate_ajout());
				  
		  				  
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
	public boolean supprimerOffre_terrain(Offre_terrain offre_terrain, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM offre_terrain WHERE id=?");
				req.setLong(1, offre_terrain.getId());

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
	public boolean modifierOffre_terrain(Offre_terrain offre_terrain, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, offre_terrain.getNum_offre());
				  req.setString(2, offre_terrain.getNature());
				  req.setString(3, offre_terrain.getLieu());
				  req.setString(4, offre_terrain.getLot());
				  req.setString(5, offre_terrain.getIlot());
				  req.setString(6, offre_terrain.getSuperficie());
				  req.setInt(7, offre_terrain.getPrix());
				  req.setString(8, offre_terrain.getCaracteristique());
				  req.setString(9, offre_terrain.getDocument());
				  req.setInt(10, offre_terrain.getStatut());
				  req.setString(11, offre_terrain.getDate_ajout());
				  req.setLong(12, offre_terrain.getId());
				  
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
	public Offre_terrain getOffre_terrain(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Offre_terrain offre_terrain = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM offre_terrain WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 offre_terrain = map(res);
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
		return offre_terrain;
	}
	

	@Override
	public Offre_terrain getOffre_terrain(String num_offre) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Offre_terrain offre_terrain = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM offre_terrain WHERE num_offre=?");
				req.setString(1, num_offre);
				res = req.executeQuery();
				 while (res.next()) {
					 offre_terrain = map(res);
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
		return offre_terrain;
	}
	
	@Override
	public Boolean getOffre_terrainVerifie(String num_offre) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM offre_terrain WHERE num_offre=?");
				req.setString(1, num_offre);
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
					req = cn.prepareStatement("SELECT * FROM offre_terrain WHERE num_offre like ? order by id desc limit 1");
					req.setString(1, "%"+code+"%");
					res = req.executeQuery();
					if (res.next()) {
						String matSite = res.getString("num_offre");
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
		public List<Offre_terrain> getAllOffre_terrain() {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Offre_terrain> offre_terrain = new ArrayList<Offre_terrain>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM offre_terrain order by nom");		
					res = req.executeQuery();
					 while (res.next()) {
						 offre_terrain.add(map(res));
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
			return offre_terrain;
		}
		
		private static Offre_terrain map(ResultSet res) throws SQLException {
			Offre_terrain offre_terrain= new Offre_terrain();
				
				offre_terrain.setId(res.getLong("id"));
				offre_terrain.setNum_offre(res.getString("num_offre"));
				offre_terrain.setNature(res.getString("nature"));
				offre_terrain.setLieu(res.getString("lieu"));
				offre_terrain.setLot(res.getString("lot"));
				offre_terrain.setIlot(res.getString("ilot"));
				offre_terrain.setSuperficie(res.getString("superficie"));
				offre_terrain.setPrix(res.getInt("prix"));
				offre_terrain.setCaracteristique(res.getString("caracteristique"));
				offre_terrain.setDocument(res.getString("document"));		
				offre_terrain.setStatut(res.getInt("statut"));
				offre_terrain.setDate_ajout(res.getString("date_ajout"));
				
	        return offre_terrain;
				
		}
			
}





