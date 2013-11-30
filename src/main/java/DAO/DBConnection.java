package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;

public class DBConnection {
	
	public Connection getConnection(StockExchangeProperties properites) {		
		try {  
            Class.forName(properites.getDBDriver());  
            Connection connection = DriverManager.getConnection(properites.getDBUrl(), properites.getDBUser(), properites.getDBPassword());  
            System.out.println("It WORKS. I'm connected!");
            return connection;

	    	} 
	    catch (Exception e) {  
            e.printStackTrace();  
            System.out.println("It doesn't work... I'm not connected :(");
            throw new IllegalStateException(e);
	    }
	    
	}
}
