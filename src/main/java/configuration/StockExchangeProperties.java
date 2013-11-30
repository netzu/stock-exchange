package configuration;

import java.io.InputStream;
import java.util.Properties;

public class StockExchangeProperties {

	private static final String DRIVER = "db_connection.driver";
	private static final String URL = "db_connection.url";
	private static final String USER = "db_connection.user";
	private static final String PASSWORD = "db_connection.password";
	private static final String STORAGE = "file.central_storage_of_files";
	
	private final Properties properties;
	
	public StockExchangeProperties(final InputStream is) {
		this.properties = new Properties();
		
		try {
			this.properties.load(is);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		
	}
	
	public String getDBDriver() {
		
		return this.properties.getProperty(DRIVER);
	}
	
	public String getDBUrl() {
		return this.properties.getProperty(URL);
	}
	
	public String getDBUser() {
		return this.properties.getProperty(USER);
	}
	
	public String getDBPassword() {
		return this.properties.getProperty(PASSWORD);
	}
	
	public String getCentralStorage() {
		String storageLocation = this.properties.getProperty(STORAGE);
		
		return storageLocation;
	}
	
}
