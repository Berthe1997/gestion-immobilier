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

import com.news.beans.OuvertureFermetureCaisse;
import com.news.controllers.CRUDOuvertureFermeture;

public class OuvertureFermetureCaisseDI implements OuvertureFermetureCaisseDao {
	
	/*private static final String SQL_INSERT 			= 
			"INSERT INTO ouverture_fermeture_caisse (code,site,username,code_caisse,cashdispo_f,cashdispo_o,difference_o,date_ouv,ouvert,montantencaisse,montantdecaisse,cashcalcule,cashreel,difference_f,date_fer,fermer) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";*/
	
	private static final String SQL_INSERTUN 			= 
			"INSERT INTO ouverture_fermeture_caisse (code,site,username,code_caisse,cashdispo_f,cashdispo_o,difference_o,date_ouv,ouvert,cashcalcule) "
			+ "VALUES (?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_MODIFIER = 
			"UPDATE ouverture_fermeture_caisse SET montantencaisse=?,montantdecaisse=?,cashcalcule=? WHERE id=?";
	private static final String SQL_MODIFIERF = 
			"UPDATE ouverture_fermeture_caisse SET cashreel=?,difference_f=?,date_fer=?,fermer=? WHERE id=?";
	private static final String SQL_MOD = 
			"UPDATE ouverture_fermeture_caisse SET cashreel=? WHERE id=?";

	@Override
	public boolean creerOuv(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		if (null != cn) {
			try {
				req = cn.prepareStatement(SQL_INSERTUN);
				req.setString(1, ouvFer.getCode());
				req.setString(2, ouvFer.getSite());
				req.setString(3, ouvFer.getUserName());
				req.setString(4, ouvFer.getCodeCaisse());
				req.setInt(5, ouvFer.getCashDispoFSessionP());
				req.setInt(6, ouvFer.getCashDispoOSession());
				req.setInt(7, ouvFer.getDifferenceMontantO());
				req.setString(8, ouvFer.getDateOuverture());
				req.setInt(9, ouvFer.getOuvert());
				req.setInt(10, ouvFer.getCashCalcule());
				int statut = req.executeUpdate();
				
				return 1==statut;

			} catch (SQLException ex) {
				Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}

	@Override
	public boolean modifierOuvFer(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	     Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement(SQL_MODIFIERF);
	                
	                req.setInt(1, ouvFer.getCashReel());
	                req.setInt(2, ouvFer.getDifferenceMontantF());
	                req.setString(3, ouvFer.getDateFermeture());
	                req.setInt(4, ouvFer.getFermer());
		            req.setLong(5, ouvFer.getId());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
	               errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
	        }
	        return false;
	}
	
	@Override
	public boolean modifierEncaisseDecaisse(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	     Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement(SQL_MODIFIER);
	                
	                req.setInt(1, ouvFer.getMontantEncaisse());
	                req.setInt(2, ouvFer.getMontantDecaisse());
	                req.setInt(3, ouvFer.getCashCalcule());
		            req.setLong(4, ouvFer.getId());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
	               errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
	        }
	        return false;
	}
	
	@Override
	public boolean modifierOF(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	     Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement(SQL_MOD);
	                
	                req.setInt(1, ouvFer.getCashReel());
		            req.setLong(2, ouvFer.getId());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
	               errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
	        }
	        return false;
	}
	
	@Override
	public boolean modifierOFs(OuvertureFermetureCaisse ouvFer, AtomicReference<String> errorMsg) {
		 PreparedStatement req = null;
	     Connection cn=GetConnexion.getConnection();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("UPDATE ouverture_fermeture_caisse SET cashdispo_f=?,cashdispo_o=?,cashcalcule=?,cashreel=? WHERE id=?");
	                
	                req.setInt(1, ouvFer.getCashDispoFSessionP());
	                req.setInt(2, ouvFer.getCashDispoOSession());
	                req.setInt(3, ouvFer.getCashCalcule());
	                req.setInt(4, ouvFer.getCashReel());
		            req.setLong(5, ouvFer.getId());
	                
	                return (1==req.executeUpdate());
	            } catch (SQLException ex) {
	               Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
	               errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDOuvertureFermeture.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
	        }
	        return false;
	}
	
	@Override
	public OuvertureFermetureCaisse getOuvFer(Long id) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     OuvertureFermetureCaisse ouvFerCaisse = null;
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE  id=?");
	                req.setLong(1, id);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse = map( res );
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
	        return ouvFerCaisse;
	}
	
	@Override
	public OuvertureFermetureCaisse getOuvFer(String code, String site,String codeCa,String date) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     OuvertureFermetureCaisse ouvFerCaisse = null;
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE  code=? AND site=? AND code_caisse=? AND date_ouv=?");
	                req.setString(1, code);
	                req.setString(2, site);
	                req.setString(3, codeCa);
	                req.setString(4, date);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse = map( res );
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
	        return ouvFerCaisse;
	}
	
	@Override
	public Boolean getOuvFerVerifier(String code, String site,String codeCa,String date) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     Boolean verifie = false;
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE  code=? AND site=? AND code_caisse=? AND date_ouv=?");
	                req.setString(1, code);
	                req.setString(2, site);
	                req.setString(3, codeCa);
	                req.setString(4, date);
	                res = req.executeQuery();
	    			while ( res.next() ) {
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
	public List<OuvertureFermetureCaisse> getOuv(String code, String site, String codeCaisse) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<OuvertureFermetureCaisse> ouvFerCaisse = new ArrayList<OuvertureFermetureCaisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE code=? AND site=? AND code_caisse=? ORDER BY id DESC LIMIT 1");
	                req.setString(1, code);
	                req.setString(2, site);
	                req.setString(3, codeCaisse);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse.add(map( res ));
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
	        return ouvFerCaisse;
	}
	
	@Override
	public List<OuvertureFermetureCaisse> getOuvr(String code, String site, String codeCaisse) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<OuvertureFermetureCaisse> ouvFerCaisse = new ArrayList<OuvertureFermetureCaisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE code=? AND site=? AND code_caisse=? ORDER BY id ASC");
	                req.setString(1, code);
	                req.setString(2, site);
	                req.setString(3, codeCaisse);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse.add(map( res ));
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
	        return ouvFerCaisse;
	}

	@Override
	public List<OuvertureFermetureCaisse> getDernierCaisseOuverte(String code, String site, String codeCaisse) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<OuvertureFermetureCaisse> ouvFerCaisse = new ArrayList<OuvertureFermetureCaisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE code=? AND site=? AND code_caisse=? ORDER BY id DESC LIMIT 1");
	                req.setString(1, code);
	                req.setString(2, site);
	                req.setString(3, codeCaisse);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse.add(map( res ));
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
	        return ouvFerCaisse;
	}

	@Override
	public List<OuvertureFermetureCaisse> getOuv(String code, String site, String codeCaisse,int un) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<OuvertureFermetureCaisse> ouvFerCaisse = new ArrayList<OuvertureFermetureCaisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE code=? AND site=? AND code_caisse=? AND fermer=?");
	                req.setString(1, code);
	                req.setString(2, site);
	                req.setString(3, codeCaisse);
	                req.setInt(4, un);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse.add(map( res ));
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
	        return ouvFerCaisse;
	}
	
	@Override
	public List<OuvertureFermetureCaisse> getOuv(String code, String site,int un) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<OuvertureFermetureCaisse> ouvFerCaisse = new ArrayList<OuvertureFermetureCaisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE code=? AND site=? AND fermer=?");
	                req.setString(1, code);
	                req.setString(2, site);
	                req.setInt(3, un);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse.add(map( res ));
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
	        return ouvFerCaisse;
	}
	
	@Override
	public List<OuvertureFermetureCaisse> getOuvA(String code, String user, String date) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<OuvertureFermetureCaisse> ouvFerCaisse = new ArrayList<OuvertureFermetureCaisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM ouverture_fermeture_caisse WHERE code=? AND username=? AND date_ouv=? ORDER BY id DESC LIMIT 1");
	                req.setString(1, code);
	                req.setString(2, user);
	                req.setString(3, date);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse.add(map( res ));
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
	        return ouvFerCaisse;
	}

	@Override
	public List<OuvertureFermetureCaisse> getOuv(String code, String site) {
		 PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
	     List<OuvertureFermetureCaisse> ouvFerCaisse = new ArrayList<OuvertureFermetureCaisse>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT sum(montantencaisse) as montantencaisse FROM ouverture_fermeture_caisse WHERE code=? AND site=?");
	                req.setString(1, code);
	                req.setString(2, site);
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				ouvFerCaisse.add(maps( res ));
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
	        return ouvFerCaisse;
	}
	
	private static OuvertureFermetureCaisse map( ResultSet resultSet ) throws SQLException {
		OuvertureFermetureCaisse ouvFerCaisse = new OuvertureFermetureCaisse();
		
		ouvFerCaisse.setCode(resultSet.getString("code"));
		ouvFerCaisse.setSite(resultSet.getString("site"));
		ouvFerCaisse.setUserName(resultSet.getString("username"));
		ouvFerCaisse.setCodeCaisse(resultSet.getString("code_caisse"));
		ouvFerCaisse.setCashDispoFSessionP(resultSet.getInt("cashdispo_f"));
		ouvFerCaisse.setCashDispoOSession(resultSet.getInt("cashdispo_o"));
		ouvFerCaisse.setDifferenceMontantO(resultSet.getInt("difference_o"));
		ouvFerCaisse.setDateOuverture(resultSet.getString("date_ouv"));
		ouvFerCaisse.setOuvert(resultSet.getInt("ouvert"));
		ouvFerCaisse.setMontantEncaisse(resultSet.getInt("montantencaisse"));
		ouvFerCaisse.setMontantDecaisse(resultSet.getInt("montantdecaisse"));
		ouvFerCaisse.setCashCalcule(resultSet.getInt("cashcalcule"));
		ouvFerCaisse.setCashReel(resultSet.getInt("cashreel"));
		ouvFerCaisse.setDifferenceMontantF(resultSet.getInt("difference_f"));
		ouvFerCaisse.setDateFermeture(resultSet.getString("date_fer"));
		ouvFerCaisse.setFermer(resultSet.getInt("fermer"));
		ouvFerCaisse.setId(resultSet.getLong("id"));
		
		return ouvFerCaisse;
	}
	
	private static OuvertureFermetureCaisse maps( ResultSet resultSet ) throws SQLException {
		OuvertureFermetureCaisse ouvFerCaisse = new OuvertureFermetureCaisse();
		
		ouvFerCaisse.setMontantEncaisse(resultSet.getInt("montantencaisse"));
		
		return ouvFerCaisse;
	}

}
