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

import com.news.beans.Profil;
import com.news.controllers.CRUDProfil;

public class ProfilDI implements ProfilDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO profil (profil,commentaire) VALUES (?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE profil SET profil=?,commentaire=? WHERE id=?";
	@Override
	public boolean creerProfil(Profil profil, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
	             req.setString(1, profil.getProfil());
	             req.setString(2, profil.getCommentaire());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}

	@Override
	public boolean supprimerProfil(Profil profil, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM profil WHERE id=?");
				req.setLong(1, profil.getId());

				return (1 == req.executeUpdate());
			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				errorMsg.set(ex.getMessage());
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
					errorMsg.set(ex.getMessage());
				}
			}
		}
		return false;
	}

	@Override
	public boolean modifierProfil(Profil profil, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
	             req.setString(1, profil.getProfil());
	             req.setString(2, profil.getCommentaire());
	             req.setLong(3, profil.getId());
	             return (1==req.executeUpdate());
					
			 } catch (SQLException ex) {
	               Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                errorMsg.set(ex.getMessage());
	            } finally {
	                try {
	                    req.close();
	                    cn.close();
	                } catch (SQLException ex) {
	                   Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
	                   errorMsg.set(ex.getMessage());
	                }
	            }
		 }
		 return false;
	}

	@Override
	public Profil getProfil(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Profil profil = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM profil WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 profil = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return profil;
	}

	@Override
	public Profil getProfil(String profils) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Profil profil = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM profil WHERE profil=?");
				req.setString(1, profils);
				res = req.executeQuery();
				 while (res.next()) {
					 profil = map(res);
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return profil;
	}

	@Override
	public Boolean getProfilVerifie(String profils) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM profil WHERE profil=?");
				req.setString(1, profils);
				res = req.executeQuery();
				 while (res.next()) {
					 verifie = true;
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return verifie;
	}
	
	@Override
	public List<Profil> getAllProfil() {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		List<Profil> profils = new ArrayList<Profil>();

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM profil");
				res = req.executeQuery();
				 while (res.next()) {
					 profils.add(map(res));
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return profils;
	}
	
	@Override
	public List<Profil> getAllVerifie(String profil) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		List<Profil> profils = new ArrayList<Profil>();

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM profil WHERE profil<>?");
				req.setString(1, profil);
				res = req.executeQuery();
				 while (res.next()) {
					 profils.add(map(res));
				}

			} catch (SQLException ex) {
				Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
			} finally {
				try {
					req.close();
					cn.close();
				} catch (SQLException ex) {
					Logger.getLogger(CRUDProfil.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
		}
		return profils;
	}

	
	private static Profil map(ResultSet resultSet) throws SQLException {
		Profil profil = new Profil();
		
		profil.setProfil( resultSet.getString( "profil" ) );
		profil.setCommentaire( resultSet.getString( "commentaire" ) );
		profil.setId(resultSet.getLong("id"));
		return profil;
	}

}
