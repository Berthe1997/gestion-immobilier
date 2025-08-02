package com.news.dao_M;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_M.Reservation;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class ReservationDI implements ReservationDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO reservation (agence,code_bien,code_reservation,client,date_entree,date_sortie,reduction,montant_T,statut,commissionA,montant_P,date_A,site,nombre,montant_net) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE reservation SET agence=?,code_bien=?,code_reservation=?,client=?,date_entree=?,date_sortie=?,reduction=?,montant_T=?,statut=?,commissionA=?,montant_P=?,date_ajout=?,site=?,montant_net=? WHERE id=?";
	private static final String SQL_UPDATER 			= 
			"UPDATE reservation SET reduction=?,commissionA=?,montant_P=?,montant_net=?,mode=?,date_A=?,heure=? WHERE code_reservation=?";

	@Override 
	 public boolean creerReservation(Reservation reservation, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, reservation.getAgence());
				  req.setString(2, reservation.getCode_bien());
				  req.setString(3, reservation.getCode_reservation());
				  req.setString(4, reservation.getClient());
				  req.setString(5, reservation.getDate_entree());
				  req.setString(6, reservation.getDate_sortie());
				  req.setInt(7, reservation.getReduction());
				  req.setInt(8, reservation.getMontant_T());
				  req.setString(9, reservation.getStatut());
				  req.setInt(10, reservation.getCommissionA());
				  req.setInt(11, reservation.getMontant_P());
				  req.setString(12, reservation.getDate_A());
				  req.setString(13, reservation.getSite());
				  req.setInt(14, reservation.getNombre());
				  req.setInt(15, reservation.getMontant_net());
				  				  
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
	public boolean supprimerReservation(Reservation reservation, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM reservation WHERE id=?");
				req.setLong(1, reservation.getId());

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
	public boolean modifierReservation(Reservation reservation, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, reservation.getAgence());
				  req.setString(2, reservation.getCode_bien());
				  req.setString(3, reservation.getCode_reservation());
				  req.setString(4, reservation.getClient());
				  req.setString(5, reservation.getDate_entree());
				  req.setString(6, reservation.getDate_sortie());
				  req.setInt(7, reservation.getReduction());
				  req.setInt(8, reservation.getMontant_T());
				  req.setString(9, reservation.getStatut());
				  req.setInt(10, reservation.getCommissionA());
				  req.setInt(11, reservation.getMontant_P());
				  req.setString(12, reservation.getDate_A());
				  req.setString(13, reservation.getSite());
				  req.setInt(14, reservation.getMontant_net());
	              req.setLong(15, reservation.getId());
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
	public boolean modifierReservationR(Reservation reservation, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATER);
				 req.setInt(1, reservation.getReduction());
				  req.setInt(2, reservation.getCommissionA());
				  req.setInt(3, reservation.getMontant_P());
				  req.setInt(4, reservation.getMontant_net());
				  req.setString(5, reservation.getMode());
				  req.setString(6, reservation.getDate_A());
				  req.setString(7, reservation.getHeure());
				  req.setString(8, reservation.getCode_reservation());
				 
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
	public Reservation getReservation(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Reservation reservation = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM reservation WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 reservation = map(res);
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
		return reservation;
	}
	
	@Override
	public Reservation getReservation(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Reservation reservation = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM reservation WHERE code_reservation=?");
				req.setString(1, code);
				res = req.executeQuery();
				 while (res.next()) {
					 reservation = map(res);
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
		return reservation;
	}
	
	@Override
	public Reservation getReservationC(String client) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Reservation reservation = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM reservation WHERE client=?");
				req.setString(1, client);
				res = req.executeQuery();
				 while (res.next()) {
					 reservation = map(res);
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
		return reservation;
	}
 
 @Override
	public Boolean getReservationVerifie(String code) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM reservation WHERE code_reservation=?");
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
 
 private static Reservation map(ResultSet res) throws SQLException {
	 Reservation reservation= new Reservation();

		reservation.setId(res.getLong("id"));
		reservation.setAgence(res.getString("agence"));
		reservation.setCode_bien(res.getString("code_bien"));
		reservation.setCode_reservation(res.getString("code_reservation"));
		reservation.setClient(res.getString("client"));
		reservation.setDate_entree(res.getString("date_entree"));
		reservation.setDate_sortie(res.getString("date_sortie"));
		reservation.setReduction(res.getInt("reduction"));
		reservation.setMontant_T(res.getInt("montant_T"));
		reservation.setStatut(res.getString("statut"));
		reservation.setCommissionA(res.getInt("commissionA"));
		reservation.setMontant_P(res.getInt("montant_P"));
		reservation.setDate_A(res.getString("date_A"));
		reservation.setSite(res.getString("site"));
		reservation.setNombre(res.getInt("nombre"));
		reservation.setMontant_net(res.getInt("montant_net"));
		reservation.setMode(res.getString("mode"));
		reservation.setHeure(res.getString("heure"));
					
		return reservation;
	}
	
}
