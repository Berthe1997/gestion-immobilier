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

import com.news.beans.Site;
import com.news.controllers.CRUDConnexion;




public class SiteDI implements SiteDao {
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO site (site,type_site,nombre_porte,ville,commune,quartier,cite,num_acd,permis_contruction,date_obtention,num_cnps,num_lot,num_illot,superficie,num_impot,situation_geo,description,matricule,nom_prenom,date_ajout,code) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	static final String SQL_MODIFIER_PAR_ID = "UPDATE site SET  site=?,type_site=?,nombre_porte=?,ville=?,commune=?,quartier=?,cite=?,num_acd=?,permis_contruction=?,date_obtention=?,num_cnps=?,num_lot=?,num_illot=?,superficie=?,num_impot=?,situation_geo=?,description=?,matricule=?,nom_prenom=?,date_ajout=?,code=? WHERE id=?";
	
	@Override
	public boolean creerSite(Site site, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
				 req.setString(1, site.getSite());	            
	             req.setString(2, site.getType_site());
	             req.setString(3, site.getNombre_porte());	            
	             req.setString(4, site.getVille());
	             req.setString(5, site.getCommune());
	             req.setString(6, site.getQuartier());	            
	             req.setString(7, site.getCite());
	             req.setString(8, site.getNum_acd());	            
	             req.setString(9, site.getPermis_contruction());
	             req.setString(10, site.getDate_obtention());
	             req.setString(11, site.getNum_cnps());
	             req.setString(12, site.getNum_lot());
	             req.setString(13, site.getNum_illot());
	             req.setString(14, site.getSuperficie());
	             req.setString(15, site.getNum_impot());
	             req.setString(16, site.getSituation_geo());
	             req.setString(17, site.getDescription());
	             req.setString(18, site.getMatricule());
	             req.setString(19, site.getNom_prenom());
	             req.setString(20, site.getDate_ajout());
	             req.setString(21, site.getCode());
	           
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public boolean supprimerSite(Site site, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM site WHERE id=?");
				req.setLong(1, site.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}
	
	@Override
	public boolean modifierSite(Site site, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_ID);	         
				 req.setString(1, site.getSite());	            
	             req.setString(2, site.getType_site());
	             req.setString(3, site.getNombre_porte());	            
	             req.setString(4, site.getVille());
	             req.setString(5, site.getCommune());
	             req.setString(6, site.getQuartier());	            
	             req.setString(7, site.getCite());
	             req.setString(8, site.getNum_acd());	            
	             req.setString(9, site.getPermis_contruction());
	             req.setString(10, site.getDate_obtention());
	             req.setString(11, site.getNum_cnps());
	             req.setString(12, site.getNum_lot());
	             req.setString(13, site.getNum_illot());
	             req.setString(14, site.getSuperficie());
	             req.setString(15, site.getNum_impot());
	             req.setString(16, site.getSituation_geo());
	             req.setString(17, site.getDescription());
	             req.setString(18, site.getMatricule());
	             req.setString(19, site.getNom_prenom());
	             req.setString(20, site.getDate_ajout());
	             req.setString(21, site.getCode());
	             req.setLong(22, site.getId());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}
	
	@Override
	public Site getSite(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Site site = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM site WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					site = map(res);
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
		return site;
	}
	
	@Override
	public Site getSite(String sites) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Site site = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM site WHERE site=?");
				req.setString(1, sites);
				res = req.executeQuery();
				if (res.next()) {
					site = map(res);
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
		return site;
	}
	
	
	@Override
	public Boolean getVerifierSite(String sites) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM site WHERE site=?");
				req.setString(1, sites);
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
	public List<Site> getAllSite() {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Site> site = new ArrayList<Site>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM site");
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				site.add( map( res ) );
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
	        return site;
	}
	
	@Override
	public List<Site> getAllSite(String sites) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Site> site = new ArrayList<Site>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM site WHERE site=?");
					req.setString(1, sites);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				site.add( map( res ) );
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
	        return site;
	}
	
	@Override
	public List<Site> getAllSiteC(String code) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Site> site = new ArrayList<Site>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM site WHERE code=?");
					req.setString(1, code);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				site.add( map( res ) );
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
	        return site;
	}
	
	@Override
	public List<Site> getAllSiteM(String matricule) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Site> site = new ArrayList<Site>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM site WHERE matricule=?");
					req.setString(1, matricule);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				site.add( map( res ) );
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
	        return site;
	}
	
	private static Site map( ResultSet resultSet ) throws SQLException {
		Site site = new Site();
		
		ProprietaireDI proprietaireDI = new ProprietaireDI();
		
		site.setId(resultSet.getLong("id"));
		site.setSite(resultSet.getString("site"));
		site.setType_site( resultSet.getString( "type_site" ) );
		site.setNombre_porte(resultSet.getString("nombre_porte"));
		site.setVille(resultSet.getString("ville"));
		site.setCommune( resultSet.getString( "commune" ) );
		site.setQuartier( resultSet.getString( "quartier" ) );
		site.setCite( resultSet.getString( "cite" ) );
		site.setNum_acd( resultSet.getString( "num_acd" ) );
		site.setPermis_contruction(resultSet.getString("permis_contruction"));
		site.setDate_obtention( resultSet.getString( "date_obtention" ) );
		site.setNum_cnps(resultSet.getString("num_cnps"));
		site.setNum_lot( resultSet.getString( "num_lot" ) );
		site.setNum_illot( resultSet.getString( "num_illot" ) );
		site.setSuperficie( resultSet.getString( "superficie" ) );
		site.setNum_impot( resultSet.getString( "num_impot" ) );
		site.setSituation_geo( resultSet.getString( "situation_geo" ) );
		site.setDescription( resultSet.getString( "description" ) );
		site.setMatricule( resultSet.getString( "matricule" ) );
		site.setNom_prenom( resultSet.getString( "nom_prenom" ) );
		site.setDate_ajout( resultSet.getString( "date_ajout" ) );
		site.setCode( resultSet.getString( "code" ) );
		
		site.setProprietaires(proprietaireDI.getProprietaire(resultSet.getString( "matricule" )));
			
		return site;
	}
	
	

}
