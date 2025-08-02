package com.news.controllers;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.news.beans.Agence;
import com.news.beans.Roles;
import com.news.beans.Site;
import com.news.beans.UserSite;
import com.news.beans.Users;
import com.news.dao.AgenceDI;
import com.news.dao.GetConnexion;
import com.news.dao.RoleDI;
import com.news.dao.SiteDI;
import com.news.dao.UserSiteDI;
import com.news.dao.UsersDI;

@WebServlet("/Login")
public class CRUDConnexion extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	public static final String INDEX 	= "/index.jsp";
	private static final String ACCUEIL = "/WEB-INF/views/home.jsp";
	
	//private static final String FORMAT_DATE 			= "yyy-MM-dd";
	
	private static final String SQL_SELECT_PAR 	=	"SELECT * FROM users WHERE email =? AND password =?";
	
	UsersDI userDI = new UsersDI();
	Users users	=	new Users();
	
	UserSiteDI userSiteDI = new UserSiteDI();
	UserSite userSite = new UserSite();
	
	SiteDI siteDI = new SiteDI();
	Site site = new Site();
	
	RoleDI roleDI = new RoleDI();
	Roles role = new Roles();
	
	Agence agence = new Agence();
	AgenceDI agenceDI = new AgenceDI();
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect( request.getContextPath() + INDEX );
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String user = request.getParameter("username");
		String password = request.getParameter("password");
		String page = request.getParameter("page");
		
		Connection cn = GetConnexion.getConnection();
		PreparedStatement req = null;
		ResultSet resultSet	=	null;
		
		 try {
			 if (null!=cn) {				
				req = cn.prepareStatement(SQL_SELECT_PAR);
				req.setString(1, user);
				req.setString(2, password);
				resultSet = req.executeQuery();
				if ( resultSet.next() ) {
					
					String email = resultSet.getString("email");
					String sites = "";
					String code = "";
					users	=	userDI.getUsers(email);
					if(users.getRole().equals("Admin_General")) {
						List<Site> choixProp	=	siteDI.getAllSite();
						for(Site prop :choixProp) {
							sites	=	prop.getSite();
							code	=	prop.getCode();
						}
						role = roleDI.getRole("general",users.getRole());
					}else {
						List<UserSite> choixSite	=	userSiteDI.getAllUserEco(email);
						for(UserSite userSit : choixSite) {
							sites	=	userSit.getSite();
							code	=	userSit.getCode();
						}
						role = roleDI.getRole(code,users.getRole());
					}
					site	=	siteDI.getSite(sites);
					if(site != null)  
					agence	=	agenceDI.getAgence(code);
					
					session.setAttribute("agence", agence);
					session.setAttribute("userSite", userSite);
					session.setAttribute("users", users);
					session.setAttribute("site", site);	
					session.setAttribute("rolePr", role);
					session.setAttribute("page", page);
					this.getServletContext().getRequestDispatcher(ACCUEIL).forward(request, response);
				} else {
					response.sendRedirect( request.getContextPath() + INDEX );
				}
			 } else {
				 response.sendRedirect( request.getContextPath() + INDEX );
				 
			 }
		
		} catch ( SQLException e ) {
			e.getStackTrace();
		} finally {
			 try {
                 req.close();
                 cn.close();
             } catch (SQLException ex) {
                Logger.getLogger(CRUDConnexion.class.getName()).log(Level.SEVERE, null, ex);
             }
		}
		
	}
}
