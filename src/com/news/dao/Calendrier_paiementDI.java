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

import com.news.beans.Calendrier_paiement;
import com.news.controllers.CRUDConnexion;
import com.news.controllers.CRUDUser;

public class Calendrier_paiementDI implements Calendrier_paiementDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO calendrier_paiement (annee,mois,site,locataire,matricule,statut,date_paiement,code) VALUES (?,?,?,?,?,?,?,?)";
	
	private static final String SQL_MODIFIER_PAR_IDAM = "UPDATE calendrier_paiement SET  montant_loyer=? , montant_R=? WHERE matricule=? ";
	
	private static final String SQL_MODIFIER_PAR_IDP = "UPDATE calendrier_paiement SET  montant_P=?,montant_R=?,montant_S=?,taux_I=?,taux_A=?"
			+ ",statut=?,dateP=?,modeP=? WHERE annee=? and mois=? and matricule=?";
	
	private static final String SQL_MODIFIER_PAR_IDS = "UPDATE calendrier_paiement SET  montant_P=?,montant_R=?,montant_S=?,statut=? WHERE annee=? and mois=? and matricule=?";
	
	private static final String SQL_MODIFIER_PAR_IDL = "UPDATE calendrier_paiement SET  locataire=? WHERE  matricule=?";
	
	
	 
	@Override 
	  public boolean creerCalendrier_paiement(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setInt(1, calendrier_paiement.getAnnee());
				  req.setString(2, calendrier_paiement.getMois());
				  req.setString(3, calendrier_paiement.getSite());
				  req.setString(4, calendrier_paiement.getLocataire());
				  req.setString(5, calendrier_paiement.getMatricule());				
				  req.setString(6, calendrier_paiement.getStatut());
				  req.setString(7, calendrier_paiement.getDate_paiement());
				  req.setString(8, calendrier_paiement.getCode());
				  
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
	public boolean modifierCalendrier_paiement(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_IDAM);	         
				 req.setInt(1, calendrier_paiement.getMontant_loyer());	            
	             req.setInt(2, calendrier_paiement.getMontant_R());
	             req.setString(3, calendrier_paiement.getMatricule());
	           
	            
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
	public boolean modifierCalendrier_paiementL(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_IDL);	         
				 req.setString(1, calendrier_paiement.getLocataire());	            	          
	             req.setString(2, calendrier_paiement.getMatricule());	           	            
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
	public boolean modifierCalendrier_paiementS(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_IDS);	         
				 req.setInt(1, calendrier_paiement.getMontant_P());	            
	             req.setInt(2, calendrier_paiement.getMontant_R());
	             req.setInt(3, calendrier_paiement.getMontant_S());
	       	     req.setString(4, calendrier_paiement.getStatut());
	             req.setInt(5, calendrier_paiement.getAnnee());
	             req.setString(6, calendrier_paiement.getMois());
	             req.setString(7, calendrier_paiement.getMatricule());
	            
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
	public boolean modifierCalendrier_paiementP(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_MODIFIER_PAR_IDP);	         
				 req.setInt(1, calendrier_paiement.getMontant_P());	            
	             req.setInt(2, calendrier_paiement.getMontant_R());
	             req.setInt(3, calendrier_paiement.getMontant_S());
	             req.setInt(4, calendrier_paiement.getTaux_I());
	             req.setInt(5, calendrier_paiement.getTaux_A());
	       	     req.setString(6, calendrier_paiement.getStatut());
	       	     req.setString(7, calendrier_paiement.getDateP());
	       	     req.setString(8, calendrier_paiement.getModeP());
	             req.setInt(9, calendrier_paiement.getAnnee());
	             req.setString(10, calendrier_paiement.getMois());
	             req.setString(11, calendrier_paiement.getMatricule());
	            
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
	  public boolean supprimerCalendrier_paiement(Calendrier_paiement calendrier_paiement, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();

			if (null != cn) {
				try {
					req = cn.prepareStatement("DELETE FROM calendrier_paiement WHERE matricule=?");
					req.setString(1, calendrier_paiement.getMatricule());

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
		public Boolean getCalendrier_paiementVerifie(String matricule,int ans) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Boolean verifie = false;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE matricule=? and annee=? ");
					req.setString(1, matricule);
					req.setInt(2, ans);
					res = req.executeQuery();
					 while (res.next()) {
						 verifie = true;
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
			return verifie;
		}
	 
	 @Override
		public Calendrier_paiement getCalendrier_paiement(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Calendrier_paiement calendrier_paiement = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE matricule=?");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						calendrier_paiement = map(res);
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
			return calendrier_paiement;
		}
	 
	 @Override
		public Calendrier_paiement getCalendrier_paiementAn(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Calendrier_paiement calendrier_paiement = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE matricule=? order by id desc limit 1");
					req.setString(1, matricule);
					res = req.executeQuery();
					if (res.next()) {
						calendrier_paiement = map(res);
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
			return calendrier_paiement;
		}
	 
	 @Override
		public Calendrier_paiement getCalendrier_paiement(int ans,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Calendrier_paiement calendrier_paiement = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT site,sum(montant_R) as montant_R  FROM calendrier_paiement WHERE annee<>? and matricule=?");
					req.setInt(1, ans);
					req.setString(2, matricule);					
					res = req.executeQuery();
					if (res.next()) {
						calendrier_paiement = mapR(res);
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
			return calendrier_paiement;
		}
	 
	 @Override
		public Calendrier_paiement getCalendrier_paiement(String mois,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Calendrier_paiement calendrier_paiement = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE  mois=? and matricule=?");					
					req.setString(1, mois);
					req.setString(2, matricule);
					res = req.executeQuery();
					if (res.next()) {
						calendrier_paiement = map(res);
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
			return calendrier_paiement;
		}
	 
	 @Override
		public Calendrier_paiement getCalendrier_paiement(int ans,String mois,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Calendrier_paiement calendrier_paiement = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE annee=? and mois=? and matricule=?");
					req.setInt(1, ans);
					req.setString(2, mois);
					req.setString(3, matricule);
					res = req.executeQuery();
					if (res.next()) {
						calendrier_paiement = map(res);
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
			return calendrier_paiement;
		}
	 
	 
	 @Override
		public Calendrier_paiement getCalendrier_paiement(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			Calendrier_paiement calendrier_paiement = null;

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE id=? ");
					req.setInt(1, id);
					res = req.executeQuery();
					if (res.next()) {
						calendrier_paiement = map(res);
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
			return calendrier_paiement;
		}
	 
	 @Override
		public List<Calendrier_paiement> getAllCalendrier_paiement() {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement");
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiement(String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE  matricule=? and statut='impaye' group by annee");
					req.setString(1, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementAR(int ans,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE annee=? and matricule=? and statut='impaye'");
					req.setInt(1, ans);
					req.setString(2, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiement(String site,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and matricule=? and statut='impaye' limit 12");
					req.setString(1, site);
					req.setString(2, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementT(String site,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and matricule=? and montant_P <>'0' order by id desc");
					req.setString(1, site);
					req.setString(2, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
						
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementM(int id) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE id=? ");
					req.setInt(1, id);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiement1(String site,int ans,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and annee=? and matricule=?  and  statut ='impaye' order by id asc limit 2");
					req.setString(1, site);
					req.setInt(2, ans);
					req.setString(3, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiement2(String site,int ans,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and annee=? and matricule=?  and  statut ='impaye' order by id asc limit 3");
					req.setString(1, site);
					req.setInt(2, ans);
					req.setString(3, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiement3(String site,int ans,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and annee=? and matricule=?  and  statut ='impaye' order by id asc limit 4");
					req.setString(1, site);
					req.setInt(2, ans);
					req.setString(3, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiement4(String site,int ans,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and annee=? and matricule=?  and  statut ='impaye' order by id asc limit 5");
					req.setString(1, site);
					req.setInt(2, ans);
					req.setString(3, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiement5(String site,int ans,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and annee=? and matricule=?  and  statut ='impaye' order by id asc limit 6");
					req.setString(1, site);
					req.setInt(2, ans);
					req.setString(3, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementPDF(String site) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? group by matricule");
					req.setString(1, site);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementPDF(String site,String matricule) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and matricule=? and montant_P<>'0'");
					req.setString(1, site);
					req.setString(2, matricule);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementPDFM(String site,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and mois=?");
					req.setString(1, site);
					req.setString(2, mois);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementPDF(String site,String matricule,int ans) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and matricule=? and annee=? and montant_P<>'0'");
					req.setString(1, site);
					req.setString(2, matricule);
					req.setInt(3, ans);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementPDFST(String site,int ans,String statut) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and annee=? and statut=?");
					req.setString(1, site);					
					req.setInt(2, ans);
					req.setString(3, statut);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementPDF(String site,String matricule,int ans,String mois) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and matricule=? and annee=? and mois=? and montant_P<>'0'");
					req.setString(1, site);
					req.setString(2, matricule);
					req.setInt(3, ans);
					req.setString(4, mois);
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementPDFA(String site,int ans) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and annee=? and montant_R  <>'0' group by locataire");
					req.setString(1, site);
					req.setInt(2, ans);					
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
		
		@Override
		public List<Calendrier_paiement> getAllCalendrier_paiementPDFL(String site,int ans) {
			PreparedStatement req = null;
			Connection cn = GetConnexion.getConnection();
			ResultSet res = null;
			List<Calendrier_paiement> calendrier_paiement = new ArrayList<Calendrier_paiement>();

			if (null != cn) {
				try {
					req = cn.prepareStatement("SELECT * FROM calendrier_paiement WHERE site=? and annee=? group by locataire");
					req.setString(1, site);
					req.setInt(2, ans);					
					res = req.executeQuery();
					 while (res.next()) {
						 calendrier_paiement.add(map(res));
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
			return calendrier_paiement;
		}
	 
		 private static Calendrier_paiement map(ResultSet res) throws SQLException {
			 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
			 LocataireDI locataireDI = new LocataireDI();

				calendrier_paiement.setId(res.getLong("id"));
				calendrier_paiement.setAnnee(res.getInt("annee"));
				calendrier_paiement.setMois(res.getString("mois"));
				calendrier_paiement.setSite(res.getString("site"));
				calendrier_paiement.setLocataire(res.getString("locataire"));
				calendrier_paiement.setMatricule(res.getString("matricule"));			
				calendrier_paiement.setMontant_loyer(res.getInt("montant_loyer"));
				calendrier_paiement.setMontant_P(res.getInt("montant_P"));
				calendrier_paiement.setMontant_R(res.getInt("montant_R"));
				calendrier_paiement.setPenalite(res.getInt("penalite"));
				calendrier_paiement.setMontant_S(res.getInt("montant_S"));
				calendrier_paiement.setStatut(res.getString("statut"));
				calendrier_paiement.setDate_paiement(res.getString("date_paiement"));
				calendrier_paiement.setTaux_I(res.getInt("taux_I"));
				calendrier_paiement.setTaux_A(res.getInt("taux_A"));
				calendrier_paiement.setDateP(res.getString("dateP"));
				calendrier_paiement.setModeP(res.getString("modeP"));
				calendrier_paiement.setCode(res.getString("code"));
				
				calendrier_paiement.setLocataires(locataireDI.getLocataire(res.getString("matricule")));
				
				return calendrier_paiement;
			}
		 
		 
		 private static Calendrier_paiement mapR(ResultSet res) throws SQLException {
			 Calendrier_paiement calendrier_paiement = new Calendrier_paiement();
				
				calendrier_paiement.setSite(res.getString("site"));
				calendrier_paiement.setMontant_R(res.getInt("montant_R"));

				return calendrier_paiement;
			}
		 
}
