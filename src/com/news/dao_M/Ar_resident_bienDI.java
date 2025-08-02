package com.news.dao_M;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_M.Ar_resident_bien;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Ar_resident_bienDI implements Ar_resident_bienDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO ar_resident_bien (agence,code_reservation,nom,prenom,num_cni,lien_client,site,cni) VALUES (?,?,?,?,?,?,?,?)";
	
	@Override 
	 public boolean creerAr_resident_bien(Ar_resident_bien ar_resident_bien, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, ar_resident_bien.getAgence());
				  req.setString(2, ar_resident_bien.getCode_reservation());
				  req.setString(3, ar_resident_bien.getNom());
				  req.setString(4, ar_resident_bien.getPrenom());
				  req.setString(5, ar_resident_bien.getNum_cni());
				  req.setString(6, ar_resident_bien.getLien_client());
				  req.setString(7, ar_resident_bien.getSite());
				  req.setString(8, ar_resident_bien.getCni());
				  				  
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
	public boolean supprimerAr_resident_bien(Ar_resident_bien ar_resident_bien, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM ar_resident_bien WHERE id=?");
				req.setLong(1, ar_resident_bien.getId());

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
	public Ar_resident_bien getAr_resident_bien(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Ar_resident_bien ar_resident_bien = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM ar_resident_bien WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 ar_resident_bien = map(res);
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
		return ar_resident_bien;
	}
	
	@Override
	public Ar_resident_bien getAr_resident_bien(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Ar_resident_bien ar_resident_bien = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM ar_resident_bien WHERE code_reservation=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 ar_resident_bien = map(res);
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
		return ar_resident_bien;
	}
	
	 @Override
		public List<Ar_resident_bien> getAllAr_resident_bien(String matricule) {
			PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Ar_resident_bien> ar_resident_bien = new ArrayList<Ar_resident_bien>();
		        
		        if (null!=cn) {
		            try {
		            	req = cn.prepareStatement("SELECT * FROM ar_resident_bien WHERE code_reservation=? ");
						req.setString(1, matricule);			
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				ar_resident_bien.add( map( res ) );
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
		        return ar_resident_bien;
		}
	

	 private static Ar_resident_bien map(ResultSet res) throws SQLException {
		 Ar_resident_bien ar_resident_bien= new Ar_resident_bien();

			ar_resident_bien.setId(res.getLong("id"));
			ar_resident_bien.setAgence(res.getString("agence"));
			ar_resident_bien.setCode_reservation(res.getString("code_reservation"));
			ar_resident_bien.setNom(res.getString("nom"));
			ar_resident_bien.setPrenom(res.getString("prenom"));			
			ar_resident_bien.setNum_cni(res.getString("num_cni"));
			ar_resident_bien.setLien_client(res.getString("lien_client"));
			ar_resident_bien.setSite(res.getString("site"));
			ar_resident_bien.setCni(res.getString("cni"));
						
			return ar_resident_bien;
		}

}
