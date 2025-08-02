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

import com.news.beans.UserSite;
import com.news.controllers.CRUDView_User;


public class UserSiteDI implements UserSiteDao {

	@Override
	public boolean creerUserSite(UserSite userSite, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	     Connection cn = GetConnexion.getConnection();
	     if (null!=cn) {
	            try {
	                req=cn.prepareStatement("INSERT INTO user_site(email,site,code) VALUES(?,?,?)");
	                req.setString(1, userSite.getUserEmail());
	                req.setString(2, userSite.getSite());
	                req.setString(3, userSite.getCode());
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                    errorMsg.set(ex.getMessage());
	                }
	            }
	        }
		return false;
	}

	@Override
	public boolean supprimerUserEc(UserSite userSite, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	        java.sql.Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("DELETE FROM user_site WHERE id=?");
	                req.setLong(1, userSite.getId());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	                Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                    errorMsg.set(ex.getMessage());
	                }
	            }
	        }
		return false;
	}
	
	@Override
	public boolean supprimerUserEcS(UserSite userSite, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	        java.sql.Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("DELETE FROM user_site WHERE email=?");
	                req.setString(1, userSite.getUserEmail());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	                Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                    errorMsg.set(ex.getMessage());
	                }
	            }
	        }
		return false;
	}

	@Override
	public boolean modifierUserEc(UserSite userSite, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	        java.sql.Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("UPDATE user_site SET sites=? WHERE id=?");
	                
	                req.setString(1, userSite.getSite());
	                req.setLong(2, userSite.getId());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	                Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                    errorMsg.set(ex.getMessage());
	                }
	            }
	        }
	        return false;
	}
	
	@Override
	public boolean modifierUserEcM(UserSite userSite, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	        java.sql.Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("UPDATE user_site SET email=? WHERE email=?");
	                
	                req.setString(1, userSite.getUserEmail());
	                req.setString(2, userSite.getUserEmail());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	                Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                    errorMsg.set(ex.getMessage());
	                }
	            }
	        }
	        return false;
	}

	@Override
	public UserSite getUsersEc(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		UserSite UserSite = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM user_site WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					UserSite = UserEcMap(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return UserSite;
	}

	@Override
	public Boolean getUsersEc(String email, String site) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		Boolean verifie = false;
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM user_site WHERE email=? AND site=?");
	                req.setString(1, email);
	                req.setString(2, site);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				verifie = true;
	    			}
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	        return verifie;
	}

	@Override
	public List<UserSite> getAllUserEco() {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<UserSite> UserSite = new ArrayList<UserSite>();
	        
	        if (null!=cn) {
	        	try {
	                req=cn.prepareStatement("SELECT * FROM user_site");
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				UserSite.add( UserEcMap( res ) );
	    			}
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	        return UserSite;
	}
	
	@Override
	public List<UserSite> getAllUserEco(String email) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<UserSite> UserSite = new ArrayList<UserSite>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM user_site WHERE email=?");
	                req.setString(1, email);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				UserSite.add( UserEcMap( res ) );
	    			}
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                    Logger.getLogger(CRUDView_User.class.getName()).log(Level.SEVERE, null, ex);
	                }
	            }
	        }
	        return UserSite;
	}
	
	private static UserSite UserEcMap(ResultSet res) throws SQLException {
		UserSite userSite = new UserSite();

		userSite.setId(res.getLong("id"));
		userSite.setUserEmail(res.getString("email"));
		userSite.setSite(res.getString("site"));
		userSite.setCode(res.getString("code"));

		return userSite;
	}

}
