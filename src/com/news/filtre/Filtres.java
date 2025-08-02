package com.news.filtre;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Filtres implements Filter {
	
	public static final String LOGIN_FORM 				= 	"/index.jsp";
	public static final String ATT_SESSION_USER 		= 	"users";
	public static final String ATT_SESSION_SITE		= 	"site";
	//public static final String ATT_SESSION_AN 		= 	"anScol";

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		if(session.getAttribute( ATT_SESSION_USER ) == null) {
			res.sendRedirect( req.getContextPath() + LOGIN_FORM );return;
		} else {
			if(session.getAttribute( ATT_SESSION_SITE ) == null) {
				res.sendRedirect( req.getContextPath() + LOGIN_FORM );return;
			} else chain.doFilter( request, response );
		}
	}

}
