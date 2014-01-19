package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import org.apache.log4j.Logger;

public class DBConnection {
	private static final Logger LOGGER = Logger.getLogger(DBConnection.class);

	public Connection getConnection(StockExchangeProperties properites) {		
		try {  
            Class.forName(properites.getDBDriver());  
            Connection connection = DriverManager.getConnection(properites.getDBUrl(), properites.getDBUser(), properites.getDBPassword());  
            System.out.println("It WORKS. I'm connected!");
            return connection;

	    	} 
	    catch (Exception e) {  
            LOGGER.error("It doesn't work... I'm not connected :(", e);
            throw new IllegalStateException(e);
	    }
	    
	}
}
