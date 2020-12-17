package com.sap.poc.portal;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/hello")
public class HelloWorldServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = LoggerFactory.getLogger(HelloWorldServlet.class);

	HanaDbUseClass hanaDB =new HanaDbUseClass();
	
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		logger.info("I am running!");
		logger.debug("test debug:"+hanaDB);
		
		hanaDB.createTable();
		/*
		 * try { hanaDB.getCountForId(1l); } catch (SQLException e) { // TODO
		 * Auto-generated catch block logger.error("error", e); }
		 */
		response.getWriter().write("Hello World!");
	}
}
