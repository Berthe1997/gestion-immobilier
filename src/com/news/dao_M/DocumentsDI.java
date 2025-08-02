package com.news.dao_M;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans_M.Documents;
import com.news.controllers.CRUDAgence;
import com.news.controllers.CRUDUser;
import com.news.dao.GetConnexion;

public class DocumentsDI implements DocumentsDao {

	private static final String SQL_INSERT 			= 
			"INSERT INTO document (agence,client,type_doc,chemin_F) VALUES (?,?,?,?)";
	private static final String SQL_UPDATE 			= 
			"UPDATE document SET agence=?,client=?,type_doc=?,chemin_F=? WHERE id=?";

	@Override 
	 public boolean creerDocuments(Documents documents, AtomicReference<String> errorMsg) {
		  PreparedStatement req = null;
		  Connection cn = GetConnexion.getConnection();
		  if (null!=cn) {
			  try {
				  req=cn.prepareStatement(SQL_INSERT);
				  req.setString(1, documents.getAgence());
				  req.setString(2, documents.getClient());
				  req.setString(3, documents.getType_doc());
				  req.setString(4, documents.getChemin_F());
				  
				  				  
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
	public boolean supprimerDocuments(Documents documents, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM document WHERE id=?");
				req.setLong(1, documents.getId());

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
	public boolean modifierDocuments(Documents documents, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_UPDATE);
				 req.setString(1, documents.getAgence());
				  req.setString(2, documents.getClient());
				  req.setString(3, documents.getType_doc());
				  req.setString(4, documents.getChemin_F());				  
	              req.setLong(5, documents.getId());
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
	public Documents getDocuments(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Documents documents = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM document WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				 while (res.next()) {
					 documents = map(res);
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
		return documents;
	}
	
	@Override
	public Documents getDocuments(String client) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Documents documents = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM document WHERE client=?");
				req.setString(1, client);
				res = req.executeQuery();
				 while (res.next()) {
					 documents = map(res);
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
		return documents;
	}
	
	@Override
	public Boolean getDocumentsVerifie(String client) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Boolean verifie = false;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM document WHERE matricule=?");
				req.setString(1, client);
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
	
	private static Documents map(ResultSet res) throws SQLException {
		Documents documents= new Documents();

			documents.setId(res.getLong("id"));
			documents.setAgence(res.getString("agence"));
			documents.setClient(res.getString("client"));
			documents.setType_doc(res.getString("type_doc"));
			documents.setChemin_F(res.getString("chemin_F"));
			
						
			return documents;
		}

}
