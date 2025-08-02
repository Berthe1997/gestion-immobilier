package com.news.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.atomic.AtomicReference;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.news.beans.Archive_locataire;
import com.news.controllers.CRUDConnexion;

public class Archive_locataireDI implements Archive_locataireDao {
	
	private static final String SQL_INSERT 			= 
			"INSERT INTO archive_locataire (site,matricule,nom,prenom,sexe,date_naiss,lieu_naiss,situation_matr,fonction,nationalite,tel,email,num_porte,type_contrat,date_entree,annee,tel_whatsapp,photo,code,p_C,p_A,p_CO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
	
	@Override
	public boolean creerArchive_locataire(Archive_locataire archive_locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		 if (null!=cn) {
			 try {
				 req=cn.prepareStatement(SQL_INSERT);
				 req.setString(1, archive_locataire.getSite());	            
	             req.setString(2, archive_locataire.getMatricule());
	             req.setString(3, archive_locataire.getNom());	
	             req.setString(4, archive_locataire.getPrenom());	
	             req.setString(5, archive_locataire.getSexe());
	             req.setString(6, archive_locataire.getDate_naiss());
	             req.setString(7, archive_locataire.getLieu_naiss());	            
	             req.setString(8, archive_locataire.getSituation_matr());
	             req.setString(9, archive_locataire.getFonction());	            
	             req.setString(10, archive_locataire.getNationalite());
	             req.setString(11, archive_locataire.getTel());
	             req.setString(12, archive_locataire.getEmail());	             
	             req.setString(13, archive_locataire.getNum_porte());	            
	             req.setString(14, archive_locataire.getType_contrat());
	             req.setString(15, archive_locataire.getDate_entree());
	             req.setString(16, archive_locataire.getAnnee());
	             req.setString(17, archive_locataire.getTel_whatsapp());
	             req.setString(18, archive_locataire.getPhoto());
	             req.setString(19, archive_locataire.getCode());	             
	             req.setInt(20, archive_locataire.getP_C());	            
	             req.setInt(21, archive_locataire.getP_A());
	             req.setInt(22, archive_locataire.getP_CO());
	           	           	           	           	             
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
	public boolean supprimerArchive_locataire(Archive_locataire archive_locataire, AtomicReference<String> errorMsg) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();

		if (null != cn) {
			try {
				req = cn.prepareStatement("DELETE FROM archive_locataire WHERE id=?");
				req.setLong(1, archive_locataire.getId());

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
	public Archive_locataire getArchive_locataire(int id) {
		PreparedStatement req = null;
		Connection cn = GetConnexion.getConnection();
		ResultSet res = null;
		Archive_locataire archive_locataire = null;

		if (null != cn) {
			try {
				req = cn.prepareStatement("SELECT * FROM archive_locataire WHERE id=?");
				req.setLong(1, id);
				res = req.executeQuery();
				if (res.next()) {
					archive_locataire = map(res);
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
		return archive_locataire;
	}
	
	
	private static Archive_locataire map( ResultSet resultSet ) throws SQLException {
		Archive_locataire archive_locataire = new Archive_locataire();
		
		archive_locataire.setId(resultSet.getLong("id"));
		archive_locataire.setSite(resultSet.getString("site"));
		archive_locataire.setMatricule( resultSet.getString( "matricule" ) );
		archive_locataire.setNom(resultSet.getString("nom"));
		archive_locataire.setPrenom(resultSet.getString("prenom"));
		archive_locataire.setSexe( resultSet.getString( "sexe" ) );
		archive_locataire.setDate_naiss( resultSet.getString( "date_naiss" ) );
		archive_locataire.setLieu_naiss(resultSet.getString("lieu_naiss"));
		archive_locataire.setSituation_matr( resultSet.getString( "situation_matr" ) );
		archive_locataire.setFonction(resultSet.getString("fonction"));
		archive_locataire.setNationalite( resultSet.getString( "nationalite" ) );
		archive_locataire.setTel( resultSet.getString( "tel" ) );
		archive_locataire.setEmail( resultSet.getString( "email" ) );		
		archive_locataire.setNum_porte(resultSet.getString("num_porte"));
		archive_locataire.setType_contrat( resultSet.getString( "type_contrat" ) );
		archive_locataire.setDate_entree( resultSet.getString( "date_entree" ) );
		archive_locataire.setDate_sortie( resultSet.getString( "date_sortie" ) );
		archive_locataire.setAnnee( resultSet.getString( "annee" ) );
		archive_locataire.setTel_whatsapp( resultSet.getString( "tel_whatsapp" ) );
		archive_locataire.setCode( resultSet.getString( "code" ) );
		archive_locataire.setPhoto( resultSet.getString( "photo" ) );
		
		archive_locataire.setCaution( resultSet.getInt( "caution" ) );
		archive_locataire.setAvance( resultSet.getInt( "avance" ) );
		archive_locataire.setCommission_E( resultSet.getInt( "commission_E" ) );
		
		archive_locataire.setP_C( resultSet.getInt( "p_C" ) );
		archive_locataire.setP_A( resultSet.getInt( "p_A" ) );
		archive_locataire.setP_CO( resultSet.getInt( "p_CO" ) );
		
		
		return archive_locataire;
	}
	
}
