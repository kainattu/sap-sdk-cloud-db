package com.sap.poc.portal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sappoc.portal.config.DBConfig;


public class HanaDbUseClass {

	private static final Logger logger = LoggerFactory.getLogger(HanaDbUseClass.class);

	private static final String TABLE_NAME = "dummy";

	 private DBConfig config = null;

	public void createTable() {
		config = new DBConfig();
		DataSource dataSource = (DataSource) config.dataSource();

		String query = " Create Table DEMO_TABLE(  ID INTEGER,\r\n" + "   NAME VARCHAR(10),\r\n"
				+ "   PRIMARY KEY (ID)\r\n" + ");";

		logger.debug(" dataSoruce at hana db class :{}", dataSource);

		try (Connection connection = dataSource.getConnection();
				PreparedStatement statement = connection.prepareStatement(query)) {

			logger.debug(" connection :{}", connection);
			statement.executeQuery();
			logger.debug("table created ");
		} catch (SQLException e) {
			logger.error("Error at table creation :", e);
		}
	}

	/*
	 * public Long getCountForId(Long id) throws SQLException { String query =
	 * String.format("SELECT \"sample\" FROM %s ", TABLE_NAME);
	 * 
	 * logger.debug("Connecton :{}", dataSource.getConnection()); try (Connection
	 * connection = dataSource.getConnection(); PreparedStatement statement =
	 * connection.prepareStatement(query)) { // statement.setLong(1, id); try
	 * (ResultSet result = statement.executeQuery()) { if (result.next()) { return
	 * result.getLong(1); } logger.debug("ID not found"); return null; } } catch
	 * (SQLException e) { // throw new HanaException(e); } return id; }
	 */
}