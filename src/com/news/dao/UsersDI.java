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

import com.news.beans.Users;
import com.news.controllers.CRUDUser;


public class UsersDI implements UsersDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO users (nom,contact,email,password,role,active,matricule,site) VALUES (?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE users SET nom=?,contact=?,email=?,password=?,role=?,active=?,matricule=?,site=? WHERE id_user=?";
	
	  @Override 
	  public boolean creerUser(Users user, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, user.getNom());
				  req.setString(2, user.getContact());
				  req.setString(3, user.getEmail());
				  req.setString(4, user.getPassword());
				  req.setString(5, user.getRole());				
				  req.setInt(6, user.getActive());
				  req.setString(7, user.getMatricule());
				  req.setString(8, user.getSite());
				  
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
	  public boolean supprimerUser(Users user, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM users WHERE id_user=?");
					req.setLong(1, user.getId());

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
	  public boolean modifierUser(Users user, AtomicReference<String> errorMsg) { 
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_UPDATE);
				  req.setString(1, user.getNom());
				  req.setString(2, user.getContact());
				  req.setString(3, user.getEmail());
				  req.setString(4, user.getPassword());
				  req.setString(5, user.getRole());
				  req.setInt(6, user.getActive());	
				  req.setString(7, user.getMatricule());
				  req.setString(8, user.getSite());
				  req.setLong(9, user.getId());
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
	public Users getUsers(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Users user = null;
	
		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM users WHERE id_user=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					user = UserMap(res);
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
		return user;
	}
	
	@Override
	public Users getUsers(String email) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Users user = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM users WHERE email=?");
				req.setString(1, email);
				res = req.executeQuery();
				if (res.next()) {
					user = UserMap(res);
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
		return user;
	}
	


	@Override
	public List<Users> getAllUser() {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Users> users = new ArrayList<Users>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM users");
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				users.add( UserMap( res ) );
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
	        return users;
	}
	


	private static Users UserMap(ResultSet res) throws SQLException {
		Users user = new Users();

		user.setId(res.getLong("id_user"));
		user.setNom(res.getString("nom"));
		user.setContact(res.getString("contact"));
		user.setEmail(res.getString("email"));
		user.setPassword(res.getString("password"));
		user.setRole(res.getString("role"));
		user.setActive(res.getInt("active"));
		user.setMatricule(res.getString("matricule"));
		user.setSite(res.getString("site"));
		
		return user;
	}


}
