package com.sappoc.portal.config;

import java.util.Arrays;
import java.util.List;

import javax.activation.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.service.relational.DataSourceConfig;
import org.springframework.context.annotation.Bean;

public class DBConfig extends org.springframework.cloud.config.java.AbstractCloudConfig {
	


	private static final Logger logger = LoggerFactory.getLogger(DBConfig.class);
	
	@Bean
	public DataSource dataSource() {
		logger.debug(" bean created ");
		List<String> dataSourceNames = Arrays.asList("BasicDbcpPooledDataSourceCreator",
				"TomcatJdbcPooledDataSourceCreator", "HikariCpPooledDataSourceCreator",
				"TomcatDbcpPooledDataSourceCreator");
		DataSourceConfig dbConfig = new DataSourceConfig(dataSourceNames);
		DataSource ds = (DataSource) connectionFactory().dataSource(dbConfig);
		logger.debug(" DS created: {}",ds);
		return ds;
	}
}