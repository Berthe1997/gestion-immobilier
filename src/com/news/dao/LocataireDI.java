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

import com.news.beans.Locataire;
import com.news.controllers.CRUDConnexion;


public class LocataireDI implements LocataireDao {
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO locataire (site,matricule,nom,prenom,sexe,date_naiss,lieu_naiss,situation_matr,fonction,nationalite,tel,email,num_porte,type_contrat,date_entree,annee,tel_whatsapp,photo,code,p_C,p_A,p_CO,type,archive) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	private static final String SQL_MODIFIER_PAR_ID = "UPDATE locataire SET  site=?,matricule=?,nom=?,prenom=?,sexe=?,date_naiss=?,lieu_naiss=?,situation_matr=?,fonction=?,nationalite=?,tel=?,email=?,num_porte=?,type_contrat=?,date_sortie=?,tel_whatsapp=?,photo=?,p_C=?,p_A=?,p_CO=? WHERE id=?";
	
	private static final String SQL_MODIFIER_PAR_AR = "UPDATE locataire SET  archive=? WHERE matricule=?";
	private static final String SQL_MODIFIER_PAR_IDM = "UPDATE locataire SET  num_porte=? WHERE matricule=?";
	private static final String SQL_MODIFIER_PAR_IDS = "UPDATE locataire SET  num_porte=? WHERE matricule=? ";
	private static final String SQL_MODIFIER_PAR_IDAn = "UPDATE locataire SET  date_sortie=? WHERE matricule=? ";
//	private static final String SQL_MODIFIER_PAR_IDCA = "UPDATE locataire SET  caution=?,avance=?,commission_E=? WHERE matricule=? ";
	private static final String SQL_MODIFIER_PAR_IDAM = "UPDATE locataire SET caution=?,avance=?,commission_E=?,p_C=?,p_A=?,p_CO=? WHERE matricule=? ";
	
	@Override
	public boolean creerLocataire(Locataire locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
				 req.setString(1, locataire.getSite());	            
	             req.setString(2, locataire.getMatricule());
	             req.setString(3, locataire.getNom());	
	             req.setString(4, locataire.getPrenom());	
	             req.setString(5, locataire.getSexe());
	             req.setString(6, locataire.getDate_naiss());
	             req.setString(7, locataire.getLieu_naiss());	            
	             req.setString(8, locataire.getSituation_matr());
	             req.setString(9, locataire.getFonction());	            
	             req.setString(10, locataire.getNationalite());
	             req.setString(11, locataire.getTel());
	             req.setString(12, locataire.getEmail());	             
	             req.setString(13, locataire.getNum_porte());	            
	             req.setString(14, locataire.getType_contrat());
	             req.setString(15, locataire.getDate_entree());
	             req.setString(16, locataire.getAnnee());
	             req.setString(17, locataire.getTel_whatsapp());
	             req.setString(18, locataire.getPhoto());
	             req.setString(19, locataire.getCode());	             
	             req.setInt(20, locataire.getP_C());	            
	             req.setInt(21, locataire.getP_A());
	             req.setInt(22, locataire.getP_CO());
	             req.setString(23, locataire.getType());
	             req.setInt(24, locataire.getArchive());
	           	           	           	           	             
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
	public boolean supprimerLocataire(Locataire locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM locataire WHERE id=?");
				req.setLong(1, locataire.getId());

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
	public boolean modifierLocataire(Locataire locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_ID);	         
				 req.setString(1, locataire.getSite());	            
	             req.setString(2, locataire.getMatricule());
	             req.setString(3, locataire.getNom());	
	             req.setString(4, locataire.getPrenom());	
	             req.setString(5, locataire.getSexe());
	             req.setString(6, locataire.getDate_naiss());
	             req.setString(7, locataire.getLieu_naiss());	            
	             req.setString(8, locataire.getSituation_matr());
	             req.setString(9, locataire.getFonction());	            
	             req.setString(10, locataire.getNationalite());
	             req.setString(11, locataire.getTel());
	             req.setString(12, locataire.getEmail());	             
	             req.setString(13, locataire.getNum_porte());	            
	             req.setString(14, locataire.getType_contrat());
	             req.setString(15, locataire.getDate_sortie());	  
	             req.setString(16, locataire.getTel_whatsapp());
	             req.setString(17, locataire.getPhoto());
	             req.setInt(18, locataire.getP_C());	            
	             req.setInt(19, locataire.getP_A());
	             req.setInt(20, locataire.getP_CO());
	             req.setLong(21, locataire.getId());
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
	public boolean modifierLocataireAn(Locataire locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_IDAn);	         
				 req.setString(1, locataire.getDate_sortie());	            
	             req.setString(2, locataire.getMatricule());
	            
	            
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
	public boolean modifierLocataireS(Locataire locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_IDS);	         
				 req.setString(1, locataire.getNum_porte());	            
	             req.setString(2, locataire.getMatricule());
	            
	            
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
	public boolean modifierLocataireM(Locataire locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_IDM);	         
				 req.setString(1, locataire.getNum_porte());	            
	             req.setString(2, locataire.getMatricule());
	            
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
	public boolean modifierLocataireAR(Locataire locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_AR);	         
				 req.setInt(1, locataire.getArchive());	            
	             req.setString(2, locataire.getMatricule());
	            
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
	public boolean modifierLocataireAM(Locataire locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_IDAM);	         
				 req.setInt(1, locataire.getCaution());	            
	             req.setInt(2, locataire.getAvance());
	             req.setInt(3, locataire.getCommission_E());
	             req.setInt(4, locataire.getP_C());	            
	             req.setInt(5, locataire.getP_A());
	             req.setInt(6, locataire.getP_CO());
	             req.setString(7, locataire.getMatricule());
	           
	            
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
	public Locataire getLocataire(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Locataire locataire = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM locataire WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					locataire = map(res);
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
		return locataire;
	}
	
	@Override
	public Locataire getLocataire(String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Locataire locataire = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM locataire WHERE matricule=?");
				req.setString(1, matricule);
				res = req.executeQuery();
				if (res.next()) {
					locataire = map(res);
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
		return locataire;
	}
	
	@Override
	public Locataire getLocataire(String site,String ans) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Locataire locataire = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM locataire WHERE site=? and annee=?");
				req.setString(1, site);
				req.setString(2, ans);
				res = req.executeQuery();
				if (res.next()) {
					locataire = map(res);
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
		return locataire;
	}
	
	@Override
	public Boolean getVerifierLocataire(String site,String matricule) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM locataire WHERE site=? AND matricule=?");
				req.setString(1, site);
				req.setString(2, matricule);
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
	public Integer getLastIndex(String code,String site) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Integer nbre = 0;
		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM locataire WHERE matricule like ? order by id desc limit 1");
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
	public List<Locataire> getAllLocataire() {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Locataire> locataire = new ArrayList<Locataire>();
	        
	        if (null!=cn) {
	            try {
	                req=cn.prepareStatement("SELECT * FROM locataire");
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				locataire.add( map( res ) );
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
	        return locataire;
	}
	
	@Override
	public List<Locataire> getAllLocataire(String site) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Locataire> locataire = new ArrayList<Locataire>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM locataire WHERE site=? ");
					req.setString(1, site);			
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				locataire.add( map( res ) );
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
	        return locataire;
	}
	
	@Override
	public List<Locataire> getAllLocataireM(String matricule) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Locataire> locataire = new ArrayList<Locataire>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM locataire WHERE matricule=? ");
					req.setString(1, matricule);			
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				locataire.add( map( res ) );
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
	        return locataire;
	}
	
	@Override
	public List<Locataire> getAllLocataire(String site,String ans) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Locataire> locataire = new ArrayList<Locataire>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM locataire WHERE site=? and annee=?");
					req.setString(1, site);	
					req.setString(2, ans);	
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				locataire.add( map( res ) );
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
	        return locataire;
	}
	
	private static Locataire map( ResultSet resultSet ) throws SQLException {
		Locataire locataire = new Locataire();
		
		locataire.setId(resultSet.getLong("id"));
		locataire.setSite(resultSet.getString("site"));
		locataire.setMatricule( resultSet.getString( "matricule" ) );
		locataire.setNom(resultSet.getString("nom"));
		locataire.setPrenom(resultSet.getString("prenom"));
		locataire.setSexe( resultSet.getString( "sexe" ) );
		locataire.setDate_naiss( resultSet.getString( "date_naiss" ) );
		locataire.setLieu_naiss(resultSet.getString("lieu_naiss"));
		locataire.setSituation_matr( resultSet.getString( "situation_matr" ) );
		locataire.setFonction(resultSet.getString("fonction"));
		locataire.setNationalite( resultSet.getString( "nationalite" ) );
		locataire.setTel( resultSet.getString( "tel" ) );
		locataire.setEmail( resultSet.getString( "email" ) );		
		locataire.setNum_porte(resultSet.getString("num_porte"));
		locataire.setType_contrat( resultSet.getString( "type_contrat" ) );
		locataire.setDate_entree( resultSet.getString( "date_entree" ) );
		locataire.setDate_sortie( resultSet.getString( "date_sortie" ) );
		locataire.setAnnee( resultSet.getString( "annee" ) );
		locataire.setTel_whatsapp( resultSet.getString( "tel_whatsapp" ) );
		locataire.setCode( resultSet.getString( "code" ) );
		locataire.setPhoto( resultSet.getString( "photo" ) );
		locataire.setType( resultSet.getString( "type" ) );
		
		locataire.setCaution( resultSet.getInt( "caution" ) );
		locataire.setAvance( resultSet.getInt( "avance" ) );
		locataire.setCommission_E( resultSet.getInt( "commission_E" ) );
		
		locataire.setP_C( resultSet.getInt( "p_C" ) );
		locataire.setP_A( resultSet.getInt( "p_A" ) );
		locataire.setP_CO( resultSet.getInt( "p_CO" ) );
		
		locataire.setArchive( resultSet.getInt( "archive" ) );
		
		
		return locataire;
	}
	
}
