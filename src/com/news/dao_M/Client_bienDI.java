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

import com.news.beans_M.Client_bien;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class Client_bienDI implements Client_bienDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO client_bien (agence,matricule,nom,prenom,tel,email,num_cni,nationalite,date_ajout,document,site,bien) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE client_bien SET agence=?,matricule=?,nom=?,prenom=?,tel=?,email=?,num_cni=?,nationalite=?,document=?,site=? WHERE id=?";
	private static final String SQL_UPDATEM 			= 
			"UPDATE client_bien SET bien=? WHERE matricule=?";
	
	@Override 
	 public boolean creerClient_bien(Client_bien client_bien, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, client_bien.getAgence());
				  req.setString(2, client_bien.getMatricule());
				  req.setString(3, client_bien.getNom());
				  req.setString(4, client_bien.getPrenom());
				  req.setString(5, client_bien.getTel());
				  req.setString(6, client_bien.getEmail());
				  req.setString(7, client_bien.getNum_cni());
				  req.setString(8, client_bien.getNationalite());
				  req.setString(9, client_bien.getDate_ajout());
				  req.setString(10, client_bien.getDocument());
				  req.setString(11, client_bien.getSite());
				  req.setString(12, client_bien.getBien());
				  				  
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
	public boolean supprimerClient_bien(Client_bien client_bien, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM client_bien WHERE id=?");
				req.setLong(1, client_bien.getId());

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
	public boolean modifierClient_bien(Client_bien client_bien, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, client_bien.getAgence());
				  req.setString(2, client_bien.getMatricule());
				  req.setString(3, client_bien.getNom());
				  req.setString(4, client_bien.getPrenom());
				  req.setString(5, client_bien.getTel());
				  req.setString(6, client_bien.getEmail());
				  req.setString(7, client_bien.getNum_cni());
				  req.setString(8, client_bien.getNationalite());
				  req.setString(9, client_bien.getDocument());
				  req.setString(10, client_bien.getSite());
	              req.setLong(11, client_bien.getId());
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
	public boolean modifierClient_bienM(Client_bien client_bien, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATEM);
				 req.setString(1, client_bien.getBien());
				  req.setString(2, client_bien.getMatricule());
				 
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
	public Client_bien getClient_bien(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Client_bien client_bien = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM client_bien WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 client_bien = map(res);
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
		return client_bien;
	}
	
	@Override
	public Client_bien getClient_bien(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Client_bien client_bien = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM client_bien WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				 while (res.next()) {
					 client_bien = map(res);
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
		return client_bien;
	}
	
	 @Override
		public Boolean getClient_bienVerifie(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM client_bien WHERE matricule=?");
					req.setString(1, matricule);
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
					req = cn.prepareStatement("SELECT * FROM client_bien WHERE matricule like ? order by id desc limit 1");
					req.setString(1, "%"+code+"%");
					res = req.executeQuery();
					if (res.next()) {
						String matSite = res.getString("matricule");
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
		public List<Client_bien> getAllClient_bien(String matricule) {
			PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Client_bien> client_bien = new ArrayList<Client_bien>();
		        
		        if (null!=cn) {
		            try {
		            	req = cn.prepareStatement("SELECT * FROM client_bien WHERE matricule=? ");
						req.setString(1, matricule);			
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				client_bien.add( map( res ) );
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
		        return client_bien;
		}
	 
	 private static Client_bien map(ResultSet res) throws SQLException {
		 Client_bien client_bien= new Client_bien();

			client_bien.setId(res.getLong("id"));
			client_bien.setAgence(res.getString("agence"));
			client_bien.setMatricule(res.getString("matricule"));
			client_bien.setNom(res.getString("nom"));
			client_bien.setPrenom(res.getString("prenom"));
			client_bien.setTel(res.getString("tel"));
			client_bien.setEmail(res.getString("email"));
			client_bien.setNum_cni(res.getString("num_cni"));
			client_bien.setNationalite(res.getString("nationalite"));
			client_bien.setDate_ajout(res.getString("date_ajout"));
			client_bien.setDocument(res.getString("document"));
			client_bien.setSite(res.getString("site"));
			client_bien.setBien(res.getString("bien"));
						
			return client_bien;
		}
	
}
