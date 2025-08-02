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

import com.news.beans_M.Ar_client_bien;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Ar_client_bienDI implements Ar_client_bienDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO ar_client_bien (agence,matricule,nom,prenom,tel,email,num_cni,nationalite,date_ajout,document,site,bien) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	
	@Override 
	 public boolean creerAr_client_bien(Ar_client_bien ar_client_bien, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, ar_client_bien.getAgence());
				  req.setString(2, ar_client_bien.getMatricule());
				  req.setString(3, ar_client_bien.getNom());
				  req.setString(4, ar_client_bien.getPrenom());
				  req.setString(5, ar_client_bien.getTel());
				  req.setString(6, ar_client_bien.getEmail());
				  req.setString(7, ar_client_bien.getNum_cni());
				  req.setString(8, ar_client_bien.getNationalite());
				  req.setString(9, ar_client_bien.getDate_ajout());
				  req.setString(10, ar_client_bien.getDocument());
				  req.setString(11, ar_client_bien.getSite());
				  req.setString(12, ar_client_bien.getBien());
				  				  
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
	public boolean supprimerAr_client_bien(Ar_client_bien ar_client_bien, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM ar_client_bien WHERE id=?");
				req.setLong(1, ar_client_bien.getId());

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
	public Ar_client_bien getAr_client_bien(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Ar_client_bien ar_client_bien = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM ar_client_bien WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 ar_client_bien = map(res);
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
		return ar_client_bien;
	}
	
	
	
	@Override
	public Ar_client_bien getAr_client_bien(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Ar_client_bien ar_client_bien = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM ar_client_bien WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 ar_client_bien = map(res);
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
		return ar_client_bien;
	}
	 
	 
	 
	 @Override
		public List<Ar_client_bien> getAllAr_client_bien(String matricule) {
			PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Ar_client_bien> ar_client_bien = new ArrayList<Ar_client_bien>();
		        
		        if (null!=cn) {
		            try {
		            	req = cn.prepareStatement("SELECT * FROM ar_client_bien WHERE matricule=? ");
						req.setString(1, matricule);			
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				ar_client_bien.add( map( res ) );
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
		        return ar_client_bien;
		}
	 
	 private static Ar_client_bien map(ResultSet res) throws SQLException {
		 Ar_client_bien ar_client_bien= new Ar_client_bien();

			ar_client_bien.setId(res.getLong("id"));
			ar_client_bien.setAgence(res.getString("agence"));
			ar_client_bien.setMatricule(res.getString("matricule"));
			ar_client_bien.setNom(res.getString("nom"));
			ar_client_bien.setPrenom(res.getString("prenom"));
			ar_client_bien.setTel(res.getString("tel"));
			ar_client_bien.setEmail(res.getString("email"));
			ar_client_bien.setNum_cni(res.getString("num_cni"));
			ar_client_bien.setNationalite(res.getString("nationalite"));
			ar_client_bien.setDate_ajout(res.getString("date_ajout"));
			ar_client_bien.setDocument(res.getString("document"));
			ar_client_bien.setSite(res.getString("site"));
			ar_client_bien.setBien(res.getString("bien"));
						
			return ar_client_bien;
		}

}
