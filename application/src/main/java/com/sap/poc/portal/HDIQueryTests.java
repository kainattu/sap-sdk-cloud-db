package com.sap.poc.portal;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HDIQueryTests {
	
	private static final Logger logger = LoggerFactory.getLogger(HDIQueryTests.class);
	
	// JDBC driver name and database URL
	static final String JDBC_DRIVER = "com.sap.db.jdbc.Driver";
	private String url = "";


	// Database credentials
	private String user = "";
	private String password = "";
	private String sql;
	private List<String> results = new ArrayList<>();

	public HDIQueryTests(HttpServletResponse response) throws IOException {

		Statement stmt = null;
		Connection conn = null;

		Map<String, String> env = System.getenv();

		DBDetails dBDetails = getDBDetails(env.get("VCAP_SERVICES"));
		response.getWriter().write(dBDetails.toString());

		response.getWriter().write("\r\n\n");

		url = dBDetails.getUrl();
		user = dBDetails.getUser();
		password = dBDetails.getPassword();

		// Register JDBC driver
		try {

			Class.forName("com.sap.db.jdbc.Driver");

			// Open a connection

			conn = DriverManager.getConnection(url, user, password);
			// Execute SQL query
			stmt = conn.createStatement();
			ResultSet rs = null;
			sql = "SELECT ID  FROM ICH_TRANSACTION_MONITORING_AGGREGATOR";

			rs = stmt.executeQuery(sql);

			// Extract data from result set
			while (rs.next()) {
				results.add(rs.getString("ID"));
			}
			response.getWriter().write(results.toString());
			// Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (

		ClassNotFoundException e1) {
			logger.error("cnf error",e1);
		} catch (SQLException e) {
			logger.error("sql error",e);
		}
	}

	public String getSQL() {
		return sql;
	}

	public List<String> getResults() {
		return results;
	}

	public DBDetails getDBDetails(String jsonStr) {

		final JSONObject obj = new JSONObject(jsonStr);
		final JSONArray hana = obj.getJSONArray("hana");

		final JSONObject dbdetails = hana.getJSONObject(0);
//	    System.out.println(" deb details :"+dbdetails);
		final JSONObject credentials = (JSONObject) dbdetails.get("credentials");
		/*
		 * System.out.println(" schema :"+credentials.getString("schema"));
		 * System.out.println(" user :"+credentials.getString("user"));
		 * System.out.println(" password :"+credentials.getString("password"));
		 * System.out.println(" hdi_user :"+credentials.getString("hdi_password"));
		 * System.out.println(" hdi_password :"+credentials.getString("hdi_user"));
		 * System.out.println(" driver :"+credentials.getString("driver"));
		 * System.out.println(" port :"+credentials.getString("port"));
		 * System.out.println(" host :"+credentials.getString("host"));
		 * System.out.println(" url :"+credentials.getString("url"));
		 */

		DBDetails dbDetails = new DBDetails();

		dbDetails.setSchema(credentials.getString("schema"));
		dbDetails.setUser(credentials.getString("user"));
		dbDetails.setPassword(credentials.getString("password"));
		dbDetails.setHdi_password(credentials.getString("hdi_user"));
		dbDetails.setHdi_user(credentials.getString("hdi_password"));
		dbDetails.setDriver(credentials.getString("driver"));
		dbDetails.setPort(credentials.getString("port"));
		dbDetails.setHost(credentials.getString("host"));
		dbDetails.setUrl(credentials.getString("url"));

		return dbDetails;

	}

	public class DBDetails {

		private String schema;
		private String user;
		private String password;
		private String hdi_user;
		private String hdi_password;
		private String driver;
		private String port;
		private String host;
		private String url;

		@Override
		public String toString() {
			return "DBDetails [schema=" + schema + ", user=" + user + ", password=" + password + ", hdi_user="
					+ hdi_user + ", hdi_password=" + hdi_password + ", driver=" + driver + ", port=" + port + ", host="
					+ host + ", url=" + url + "]";
		}

		public String getSchema() {
			return schema;
		}

		public void setSchema(String schema) {
			this.schema = schema;
		}

		public String getUser() {
			return user;
		}

		public void setUser(String user) {
			this.user = user;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getHdi_user() {
			return hdi_user;
		}

		public void setHdi_user(String hdi_user) {
			this.hdi_user = hdi_user;
		}

		public String getHdi_password() {
			return hdi_password;
		}

		public void setHdi_password(String hdi_password) {
			this.hdi_password = hdi_password;
		}

		public String getDriver() {
			return driver;
		}

		public void setDriver(String driver) {
			this.driver = driver;
		}

		public String getPort() {
			return port;
		}

		public void setPort(String port) {
			this.port = port;
		}

		public String getHost() {
			return host;
		}

		public void setHost(String host) {
			this.host = host;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}
	}

}