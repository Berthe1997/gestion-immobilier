package com.news.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class GetConnexion {

	private static DataSource dataSource;
	
	public static Connection getConnection() {
		
		try {
			Context initContext = new InitialContext();
			Context envContext = (Context)initContext.lookup("java:/comp/env");
			
			dataSource = (DataSource)envContext.lookup("jdbc/gestions_imm");
			return dataSource.getConnection();
		}catch (NamingException | SQLException ex) {
            Logger.getLogger(GetConnexion.class.getName()).log(Level.SEVERE, null, ex);
        }
		return null;
	}
}
