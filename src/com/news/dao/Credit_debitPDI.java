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

import com.news.beans.Credit_debitP;
import com.news.controllers.CRUDConnexion;

public class Credit_debitPDI implements Credit_debitPDao {
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO credit_debitP (code,site,matricule,nom_prenom,montantD,date) VALUES (?,?,?,?,?,?)";
	
	
	@Override
	public boolean creerCredit_debitP(Credit_debitP credit_debitP, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
				 req.setString(1, credit_debitP.getCode());	            
	             req.setString(2, credit_debitP.getSite());
	             req.setString(3, credit_debitP.getMatricule());	            
	             req.setString(4, credit_debitP.getNom_prenom());
	             req.setInt(5, credit_debitP.getMontantD());	          
	             req.setString(6, credit_debitP.getDate());
	            
	             
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
	public boolean supprimerCredit_debitP(Credit_debitP credit_debitP, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM credit_debitP WHERE id=?");
				req.setLong(1, credit_debitP.getId());

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
	public List<Credit_debitP> getAllCredit_debitP(String site,String matricule) {
		PreparedStatement req = null;
		 ResultSet res = null;
	     Connection cn=GetConnexion.getConnection();
	     
		List<Credit_debitP> credit_debitP = new ArrayList<Credit_debitP>();
	        
	        if (null!=cn) {
	            try {
	            	req = cn.prepareStatement("SELECT * FROM credit_debitP WHERE site=? AND matricule=?");
					req.setString(1, site);	
					req.setString(2, matricule);	
	                res = req.executeQuery();
	    			while ( res.next() ) {
	    				credit_debitP.add( map( res ) );
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
	        return credit_debitP;
	}
	
	private static Credit_debitP map( ResultSet resultSet ) throws SQLException {
		Credit_debitP credit_debitP = new Credit_debitP();
		
		credit_debitP.setId(resultSet.getLong("id"));
		credit_debitP.setCode(resultSet.getString("code"));
		credit_debitP.setSite( resultSet.getString( "site" ) );
		credit_debitP.setMatricule(resultSet.getString("matricule"));
		credit_debitP.setNom_prenom( resultSet.getString( "nom_prenom" ) );
		credit_debitP.setMontantD( resultSet.getInt( "montantD" ) );	
		credit_debitP.setDate( resultSet.getString( "date" ) );
		
			
		return credit_debitP;
	}

}
