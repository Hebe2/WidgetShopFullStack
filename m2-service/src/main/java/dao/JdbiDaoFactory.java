/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.zaxxer.hikari.HikariDataSource;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

/**
 *
 * @author hebebebebe
 */
public class JdbiDaoFactory {
    
   // 1. Replace with your Render Postgres Username
   private static final String DB_USERNAME = "hebe"; 
   // 2. Replace with your Render Postgres Password
   private static final String DB_PASSWORD = "5SHN3EhkTOPPGPDGFubqPI1ttxjEIJhq"; 

   // 3. Paste your External Database URL here, but change the prefix from "postgresql://" to "jdbc:postgresql://"
   private static String jdbcUri = "jdbc:postgresql://hebe:5SHN3EhkTOPPGPDGFubqPI1ttxjEIJhq@dpg-db8nl7qvcj2c739t14h0-a.oregon-postgres.render.com/widgetshop";

//
//   private static final String DB_USERNAME = "sa";
//   private static final String DB_PASSWORD = "sa";
//
//   private static String jdbcUri = "jdbc:h2:tcp://localhost/customers";

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

		JDBI = Jdbi.create(HIKARI_DATA_SOURCE);
		JDBI.installPlugin(new SqlObjectPlugin());
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

