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

import com.news.beans.Operation;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class OperationDI implements OperationDao {

	private static final String SQL_INSERT 			=  
			"INSERT INTO operation (code,site,matricule,nom_prenom,type,decaissement,approvisonnement,montantA,montantI,montantP,montantV,montantAU,description,date,caisse,annee,mois,heure,taux,proprietaire) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	@Override 
	  public boolean creerOperation(Operation operation, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, operation.getCode());
				  req.setString(2, operation.getSite());
				  req.setString(3, operation.getMatricule());
				  req.setString(4, operation.getNom_prenom());
				  req.setString(5, operation.getType());				
				  req.setInt(6, operation.getDecaissement());
				  req.setInt(7, operation.getApprovisonnement());
				  req.setInt(8, operation.getMontantA());
				  req.setInt(9, operation.getMontantI());
				  req.setInt(10, operation.getMontantP());
				  req.setInt(11, operation.getMontantV());
				  req.setInt(12, operation.getMontantAU());
				  req.setString(13, operation.getDescription());
				  req.setString(14, operation.getDate());
				  req.setString(15, operation.getCaisse());
				  req.setInt(16, operation.getAnnee());
		          req.setString(17, operation.getMois());
		          req.setString(18, operation.getHeure());
		          req.setInt(19, operation.getTaux());
		          req.setString(20, operation.getProprietaire());
				  
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
	  public boolean supprimerOperation(Operation operation, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM operation WHERE code=? and site=? and matricule=? and type=? and annee=? and mois=? and date=? and heure=?");
					req.setString(1, operation.getCode());	
					req.setString(2, operation.getSite());
					req.setString(3, operation.getMatricule());
					req.setString(4, operation.getType());
					req.setInt(5, operation.getAnnee());
					req.setString(6, operation.getMois());
					req.setString(7, operation.getDate());
					req.setString(8, operation.getHeure());

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
	  public boolean supprimerOperationR(Operation operation, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM operation WHERE code=? and site=? and matricule=? and type=? and date=? and heure=?");
					req.setString(1, operation.getCode());	
					req.setString(2, operation.getSite());
					req.setString(3, operation.getMatricule());
					req.setString(4, operation.getType());					
					req.setString(5, operation.getDate());
					req.setString(6, operation.getHeure());

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
	  public boolean supprimerOperationVM(Operation operation, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM operation WHERE code=? and site=? and type=? and date=? and heure=?");
					req.setString(1, operation.getCode());	
					req.setString(2, operation.getSite());
					req.setString(3, operation.getType());
					req.setString(4, operation.getDate());
					req.setString(5, operation.getHeure());

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
		public  Boolean getVerifierOperation(String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE site=?");
					req.setString(1, site);					
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
		public Operation getOperation(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Operation operation = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE id=?");
					req.setLong(1, id);
					res = req.executeQuery();
					if (res.next()) {
						operation = map(res);
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
			return operation;
		}

		@Override
		public List<Operation> getAllOperation(String site,String matricule) {
			PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Operation> operation = new ArrayList<Operation>();
		        
		        if (null!=cn) {
		            try {
		            	req = cn.prepareStatement("SELECT * FROM operation WHERE site=? AND matricule=? AND decaissement='0' order by id desc");
						req.setString(1, site);	
						req.setString(2, matricule);	
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				operation.add( map( res ) );
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
		        return operation;
		}
		
		@Override
		public List<Operation> getAllOperation(String code,String date,String caisse) {
			PreparedStatement req = null;
			 ResultSet res = null;
		     Connection cn=GetConnexion.getConnection();
		     
			List<Operation> operation = new ArrayList<Operation>();
		        
		        if (null!=cn) {
		            try {
		            	req = cn.prepareStatement("SELECT sum(approvisonnement) as somme,date,caisse FROM operation WHERE code=? AND  date=? AND caisse=?");
						req.setString(1, code);
						req.setString(2, date);	
						req.setString(3, caisse);	
		                res = req.executeQuery();
		    			while ( res.next() ) {
		    				operation.add( mapT( res ) );
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
		        return operation;
		}
		
	/* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ synthèse operation ++++++++++++++++++++++++++++++ */	
		@Override
		public List<Operation> getAllDateE(String code) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=? group by site");
					req.setString(1, code);					
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieDateE(String code,String date) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=? AND date=? group by site");
					req.setString(1, code);					
					req.setString(2, date);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieCustomE(String code, String deb,String fn) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=?  AND date BETWEEN ? AND ? group by site");
					req.setString(1, code);
					req.setString(2, deb);
					req.setString(3, fn);					
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}
		
 /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ synthèse operation site ++++++++++++++++++++++++++++++ */
		@Override
		public List<Operation> getAllDateES(String code,String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=? and site=? group by site");
					req.setString(1, code);	
					req.setString(2, site);	
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieDateES(String code,String date,String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=? AND date=? and site=? group by site");
					req.setString(1, code);					
					req.setString(2, date);
					req.setString(3, site);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieCustomES(String code, String deb,String fn,String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=?  AND date BETWEEN ? AND ? and site=? group by site");
					req.setString(1, code);
					req.setString(2, deb);
					req.setString(3, fn);
					req.setString(4, site);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}
		
		
  /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ synthèse operation proprietaire ++++++++++++++++++++++++++++++ */
		@Override
		public List<Operation> getAllDateEP(String code,String proprietaire) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=? and proprietaire=? group by site");
					req.setString(1, code);	
					req.setString(2, proprietaire);	
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieDateEP(String code,String date,String proprietaire) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=? AND date=? and proprietaire=? group by site");
					req.setString(1, code);					
					req.setString(2, date);
					req.setString(3, proprietaire);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieCustomEP(String code, String deb,String fn,String proprietaire) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montantA) as montantA,sum(montantI) as montantI,sum(montantP) as montantP,sum(montantAU) as montantAU,sum(montantV) as montantV "
							+ "FROM operation WHERE code=?  AND date BETWEEN ? AND ? and proprietaire=? group by site");
					req.setString(1, code);
					req.setString(2, deb);
					req.setString(3, fn);
					req.setString(4, proprietaire);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(mapO(res));
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
			return paie;
		}	
		
		
		
 /* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ détail operation ++++++++++++++++++++++++++++++ */		
		@Override
		public List<Operation> getAllDate(String code) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=?");
					req.setString(1, code);					
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieDate(String code,String date) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=? AND date=?");
					req.setString(1, code);					
					req.setString(2, date);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieCustom(String code, String deb,String fn) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=?  AND date BETWEEN ? AND ?");
					req.setString(1, code);
					req.setString(2, deb);
					req.setString(3, fn);					
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}
		
	/* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ detail operation site ++++++++++++++++++++++++++++++ */
		@Override
		public List<Operation> getAllDateS(String code,String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=? AND site=?");
					req.setString(1, code);	
					req.setString(2, site);	
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieDateS(String code,String date,String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=? AND date=? AND site=?");
					req.setString(1, code);					
					req.setString(2, date);
					req.setString(3, site);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieCustomS(String code, String deb,String fn,String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=?  AND date BETWEEN ? AND ? AND site=?");
					req.setString(1, code);
					req.setString(2, deb);
					req.setString(3, fn);
					req.setString(4, site);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}
		
		
	/* +++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++ detail operation site ++++++++++++++++++++++++++++++ */
		@Override
		public List<Operation> getAllDateP(String code,String proprietaire) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=? AND proprietaire=?");
					req.setString(1, code);	
					req.setString(2, proprietaire);	
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieDateP(String code,String date,String proprietaire) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=? AND date=? AND proprietaire=?");
					req.setString(1, code);					
					req.setString(2, date);
					req.setString(3, proprietaire);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}

		@Override
		public List<Operation> getAllPaieCustomP(String code, String deb,String fn,String proprietaire) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Operation> paie = new ArrayList<Operation>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM operation WHERE code=?  AND date BETWEEN ? AND ? AND proprietaire=?");
					req.setString(1, code);
					req.setString(2, deb);
					req.setString(3, fn);
					req.setString(4, proprietaire);
					res = req.executeQuery();
					 while (res.next()) {
						 paie.add(map(res));
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
			return paie;
		}
		
		
	 
	 private static Operation map( ResultSet resultSet ) throws SQLException {
		 Operation operation = new Operation();
			
			operation.setId(resultSet.getLong("id"));
			operation.setCode(resultSet.getString("code"));
			operation.setSite( resultSet.getString( "site" ) );
			operation.setMatricule(resultSet.getString("matricule"));
			operation.setNom_prenom( resultSet.getString( "nom_prenom" ) );
			operation.setType( resultSet.getString( "type" ) );
			operation.setDecaissement( resultSet.getInt( "decaissement" ) );
			operation.setApprovisonnement( resultSet.getInt( "approvisonnement" ) );
			operation.setMontantA( resultSet.getInt( "montantA" ) );
			operation.setMontantI( resultSet.getInt( "montantI" ) );
			operation.setMontantP( resultSet.getInt( "montantP" ) );
			operation.setMontantV( resultSet.getInt( "montantV" ) );
			operation.setMontantAU( resultSet.getInt( "montantAU" ) );
			operation.setDescription( resultSet.getString( "description" ) );
			operation.setDate( resultSet.getString( "date" ) );
			operation.setCaisse( resultSet.getString( "caisse" ) );
			operation.setAnnee(resultSet.getInt("annee"));
			operation.setMois(resultSet.getString("mois"));
			operation.setHeure(resultSet.getString("heure"));
			operation.setTaux(resultSet.getInt("taux"));
			operation.setProprietaire(resultSet.getString("proprietaire"));
				
			return operation;
		}
	 
	 private static Operation mapO( ResultSet resultSet ) throws SQLException {
		 Operation operation = new Operation();
				
		    operation.setSite( resultSet.getString( "site" ) );
		    operation.setMontantA( resultSet.getInt( "montantA" ) );
			operation.setMontantI( resultSet.getInt( "montantI" ) );
			operation.setMontantP( resultSet.getInt( "montantP" ) );
			operation.setMontantV( resultSet.getInt( "montantV" ) );
			operation.setMontantAU( resultSet.getInt( "montantAU" ) );
		
			return operation;
		}
	 
	 private static Operation mapT( ResultSet resultSet ) throws SQLException {
		 Operation operation = new Operation();
					
			operation.setApprovisonnement( resultSet.getInt( "somme" ) );
			operation.setDate( resultSet.getString( "date" ) );
			operation.setCaisse( resultSet.getString( "caisse" ) );
			return operation;
		}

}
