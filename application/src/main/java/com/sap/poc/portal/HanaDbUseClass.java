package com.sap.poc.portal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HanaDbUseClass {

	private static final Logger logger = LoggerFactory.getLogger(HanaDbUseClass.class);


	private static final String TABLE_NAME = "ICH_TRANSACTION_MONITORING_AGGREGATOR";

	public String getCountForId(HttpServletResponse response) throws SQLException, IOException {
		String query = String.format("SELECT \"ID\" FROM %s ", TABLE_NAME);
		
		Context ctx;
		DataSource dataSource = null;
		try {
			ctx = new InitialContext();
			dataSource= (DataSource) ctx.lookup("java:comp/env/jdbc/transaction-monitoring-db");
			logger.debug("dataSource : {}",dataSource);
			logger.debug("Connecton :{}", dataSource.getConnection());
//			response.getWriter().write("nds :"+dataSource.toString());
//			response.getWriter().write("\r\n\nnds connection :"+dataSource.getConnection());
			
		} catch (NamingException e1) {
			logger.error("initial contex ",e1);
		} 	

		logger.debug("datasource :{}", dataSource);
		
		/*
		 * try (Connection connection = dataSource.getConnection(); PreparedStatement
		 * statement = connection.prepareStatement(query)) { // statement.setLong(1,
		 * id);
		 * 
		 * try (ResultSet result = statement.executeQuery()) { if (result.next()) {
		 * return result.getString(0); } else {
		 * 
		 * logger.debug("ID not found"); return null; } } catch (SQLException e) {
		 * logger.error(" error at fetch ", e); } }
		 */		return null;
	}

}