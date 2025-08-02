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

import com.news.beans_M.Resident_bien;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Resident_bienDI implements Resident_bienDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO resident_bien (agence,code_reservation,nom,prenom,num_cni,lien_client,site,cni) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE resident_bien SET cni=? WHERE code_reservation=?";

	@Override 
	 public boolean creerResident_bien(Resident_bien resident_bien, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, resident_bien.getAgence());
				  req.setString(2, resident_bien.getCode_reservation());
				  req.setString(3, resident_bien.getNom());
				  req.setString(4, resident_bien.getPrenom());
				  req.setString(5, resident_bien.getNum_cni());
				  req.setString(6, resident_bien.getLien_client());
				  req.setString(7, resident_bien.getSite());
				  req.setString(8, resident_bien.getCni());
				  				  
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
	public boolean supprimerResident_bien(Resident_bien resident_bien, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM resident_bien WHERE id=?");
				req.setLong(1, resident_bien.getId());

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
	public boolean supprimerResident_bienM(Resident_bien resident_bien, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM resident_bien WHERE code_reservation=?");
				req.setString(1, resident_bien.getCode_reservation());

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
	public boolean modifierResident_bien(Resident_bien resident_bien, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, resident_bien.getCni());
				  req.setString(2, resident_bien.getCode_reservation());
				 
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
	public Resident_bien getResident_bien(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Resident_bien resident_bien = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM resident_bien WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 resident_bien = map(res);
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
		return resident_bien;
	}
	
	@Override
	public Resident_bien getResident_bien(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Resident_bien resident_bien = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM resident_bien WHERE code_reservation=?");
				req.setString(1, code);
				res = req.executeQuery();
				 while (res.next()) {
					 resident_bien = map(res);
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
		return resident_bien;
	}
	
	
	 @Override
		public Boolean getResident_bienVerifie(String code) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM resident_bien WHERE code_reservation=?");
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
		public List<Resident_bien> getAllResident_bien(String matricule) {
			PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Resident_bien> resident_bien = new ArrayList<Resident_bien>();
		        
		        if (null!=cn) {
		            try {
		            	req = cn.prepareStatement("SELECT * FROM resident_bien WHERE code_reservation=? ");
						req.setString(1, matricule);			
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				resident_bien.add( map( res ) );
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
		        return resident_bien;
		}

	 private static Resident_bien map(ResultSet res) throws SQLException {
		 Resident_bien resident_bien= new Resident_bien();

			resident_bien.setId(res.getLong("id"));
			resident_bien.setAgence(res.getString("agence"));
			resident_bien.setCode_reservation(res.getString("code_reservation"));
			resident_bien.setNom(res.getString("nom"));
			resident_bien.setPrenom(res.getString("prenom"));			
			resident_bien.setNum_cni(res.getString("num_cni"));
			resident_bien.setLien_client(res.getString("lien_client"));
			resident_bien.setSite(res.getString("site"));
			resident_bien.setCni(res.getString("cni"));
						
			return resident_bien;
		}
	 
}
