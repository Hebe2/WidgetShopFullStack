/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.zaxxer.hikari.HikariDataSource;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 *
 * @author hebebebebe
 */
public class JdbiDaoFactory {
    

   private static final String DB_USERNAME = databaseCredential("DB_USERNAME", 0);
   private static final String DB_PASSWORD = databaseCredential("DB_PASSWORD", 1);

   private static String jdbcUri = databaseJdbcUrl();

   private static HikariDataSource HIKARI_DATA_SOURCE;
   private static Jdbi JDBI;

	public static void setJdbcUri(String uri) {
		if (HIKARI_DATA_SOURCE != null) {
			if (jdbcUri.equals(uri)) {
				return; 
			}
			throw new IllegalStateException("Connection pool as already been initialised.  It is too late to change the JDBC URI.");
		}
                jdbcUri = uri; 
	}

	private static void initialisePool(){
		HIKARI_DATA_SOURCE = new HikariDataSource();
		HIKARI_DATA_SOURCE.setJdbcUrl(jdbcUri);
		HIKARI_DATA_SOURCE.setUsername(DB_USERNAME);
		HIKARI_DATA_SOURCE.setPassword(DB_PASSWORD);

		if (jdbcUri.startsWith("jdbc:postgresql:")) {
			HIKARI_DATA_SOURCE.setDriverClassName("org.postgresql.Driver");
		} else if (jdbcUri.startsWith("jdbc:h2:")) {
			HIKARI_DATA_SOURCE.setDriverClassName("org.h2.Driver");
		}

		JDBI = Jdbi.create(HIKARI_DATA_SOURCE);
		JDBI.installPlugin(new SqlObjectPlugin());
	}

	private static String databaseJdbcUrl() {
		String url = firstConfiguredValue("JDBC_DATABASE_URL", "DATABASE_URL");
		if (url == null) {
			if ("true".equalsIgnoreCase(System.getenv("RENDER"))) {
				throw new IllegalStateException(
						"Configure JDBC_DATABASE_URL or DATABASE_URL for the Render service.");
			}
			return "jdbc:h2:file:./widgetshop;AUTO_SERVER=TRUE";
		}
		if (url.startsWith("postgres://")) {
			return "jdbc:postgresql://" + url.substring("postgres://".length());
		}
		if (url.startsWith("postgresql://")) {
			return "jdbc:postgresql://" + url.substring("postgresql://".length());
		}
		return url;
	}

	private static String databaseCredential(String environmentVariable, int userInfoPart) {
		String value = System.getenv(environmentVariable);
		if (value != null && !value.isBlank()) {
			return value;
		}

		String databaseUrl = firstConfiguredValue("DATABASE_URL", "JDBC_DATABASE_URL");
		if (databaseUrl != null && !databaseUrl.isBlank()) {
			String url = databaseUrl.startsWith("jdbc:")
					? databaseUrl.substring("jdbc:".length())
					: databaseUrl;
			String userInfo = URI.create(url).getUserInfo();
			if (userInfo != null) {
				String[] credentials = userInfo.split(":", 2);
				if (credentials.length > userInfoPart) {
					return URLDecoder.decode(
							credentials[userInfoPart].replace("+", "%2B"),
							StandardCharsets.UTF_8);
				}
			}
			if (url.startsWith("postgres://") || url.startsWith("postgresql://")
					|| url.startsWith("postgresql:")) {
				throw new IllegalStateException(
						"Configure DB_USERNAME and DB_PASSWORD for the PostgreSQL database.");
			}
		}
		return "sa";
	}

	private static String firstConfiguredValue(String... environmentVariables) {
		for (String environmentVariable : environmentVariables) {
			String value = System.getenv(environmentVariable);
			if (value != null && !value.isBlank()) {
				return value;
			}
		}
		return null;
	}

	public static CustomerDAO getCustomerDAO() {
		if(HIKARI_DATA_SOURCE == null) {
			initialisePool();
		}
		return JDBI.onDemand(CustomerJdbiDAO.class);
	}
        
        public static ProductDAO getProductDAO() {
		if(HIKARI_DATA_SOURCE == null) {
			initialisePool();
		}
		return JDBI.onDemand(ProductJdbiDAO.class);
	}
        
        public static SaleDAO getSaleDAO() {
		if(HIKARI_DATA_SOURCE == null) {
			initialisePool();
		}
		return JDBI.onDemand(SaleJdbiDAO.class);
	}

}
