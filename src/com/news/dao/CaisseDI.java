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

import com.news.beans.Caisse;
import com.news.controllers.CRUDOuvertureFermeture;
import com.news.controllers.CRUDUser;

public class CaisseDI implements CaisseDao {

	private static final String SQL_INSERT = "INSERT INTO caisse (code_caisse,code,libelle,cal_caisse,commentaire,date) "
			+ "VALUES (?,?,?,?,?,?)";
	private static final String SQL_MODIFIER_PAR_ID = "UPDATE caisse SET libelle=?,cal_caisse=?,commentaire=? WHERE id=?";
	private static final String SQL_MODIFIER_PAR_CODE = "UPDATE caisse SET ouv_fer=? WHERE code=? AND code_caisse=?";

	@Override
	public boolean creerCaisse(Caisse caisse, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_INSERT);
				req.setString(1, caisse.getCodeCaisse());
				req.setString(2, caisse.getCode());
				req.setString(3, caisse.getLibCaisse());
				req.setString(4, caisse.getCalCaisse());
				req.setString(5, caisse.getCommentaire());
				req.setString(6, caisse.getDate());
				return (1 == req.executeUpdate());

			} catch (SQLException ex) {
				Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
				// errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
					// errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}

	@Override
	public boolean supprimerCaisse(Caisse caisse, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM caisse WHERE id=?");
				req.setLong(1, caisse.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
				// errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
					// errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}

	@Override
	public boolean modifierCaisse(Caisse caisse, AtomicReference<String> errorMsg) {

		 PreparedStatement req = null;
	     Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement(SQL_MODIFIER_PAR_ID);
	                
		             req.setString(1, caisse.getLibCaisse());
		             req.setString(2, caisse.getCalCaisse());
		             req.setString(3, caisse.getCommentaire());
		             req.setLong(4, caisse.getId());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	                Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
	               // errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
	                    //errorMsg.set(ex.getMessage());
	                }
	            }
	        }
	        return false;
	}
	
	@Override
	public boolean modifierO(Caisse caisse, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	     Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement(SQL_MODIFIER_PAR_CODE);
	                
		             req.setInt(1, caisse.getOf());
		             req.setString(2, caisse.getCode());
		             req.setString(3, caisse.getCodeCaisse());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	                Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
	               // errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDUser.class.getName()).log(Level.SEVERE, null, ex);
	                    //errorMsg.set(ex.getMessage());
	                }
	            }
	        }
	        return false;
	}

	@Override
	public Caisse getCaisse(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Caisse caisse = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM caisse WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					caisse = map(res);
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
		return caisse;
	}
	
	@Override
	public Caisse getCaisse(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Caisse caisse = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM caisse WHERE code_caisse=?");
				req.setString(1, code);
				res = req.executeQuery();
				if (res.next()) {
					caisse = map(res);
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
		return caisse;
	}
	
	@Override
	public boolean getVerifierCalC(String codeS, String nom) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM caisse WHERE code=? AND libelle=?");
				req.setString(1, codeS);
				req.setString(2, nom);
				res = req.executeQuery();
				if (res.next()) {
					verifie = true;
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return verifie;
	}
	
	@Override
	public boolean getVerifierCalC1(String codeS, String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM caisse WHERE code=? AND code_caisse=?");
				req.setString(1, codeS);
				req.setString(2, code);
				res = req.executeQuery();
				while (res.next()) {
					verifie = true;
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return verifie;
	}

	@Override
	public List<Caisse> getAllCaisse() {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<Caisse> caisse = new ArrayList<Caisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM caisse ORDER BY id");
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				caisse.add( map( res ) );
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
	        return caisse;
	}

	@Override
	public List<Caisse> getAllCalC(String codeS) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<Caisse> caisse = new ArrayList<Caisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM caisse WHERE code=?");
	                req.setString(1, codeS);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				caisse.add( map( res ) );
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
	        return caisse;
	}
	
	@Override
	public List<Caisse> getAllCaisseO(String codeS) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<Caisse> caisse = new ArrayList<Caisse>();
	     
		if (null!=cn) {
            try {
                req=cn.prepareStatement("SELECT * FROM caisse WHERE code=? AND ouv_fer=1");
                req.setString(1, codeS);
                res = req.executeQuery();
    			while ( res.next() ) {
    				caisse.add( map( res ) );
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
        return caisse;
	}

	private static Caisse map( ResultSet resultSet ) throws SQLException {
		Caisse caisse = new Caisse();
		
		caisse.setCode(resultSet.getString("code"));
		caisse.setCodeCaisse(resultSet.getString("code_caisse"));
		caisse.setLibCaisse(resultSet.getString("libelle"));
		caisse.setCalCaisse(resultSet.getString("cal_caisse"));
		caisse.setOf(resultSet.getInt("ouv_fer"));
		caisse.setCommentaire(resultSet.getString("commentaire"));
		caisse.setDate(resultSet.getString("date"));
		caisse.setId(resultSet.getLong("id"));
		
		return caisse;
	}
}
