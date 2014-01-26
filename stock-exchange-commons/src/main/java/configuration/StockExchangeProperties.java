package configuration;

import java.io.InputStream;
import java.util.Properties;

public class StockExchangeProperties {

	private static final String DRIVER = "db_connection.driver";
	private static final String URL = "db_connection.url";
	private static final String USER = "db_connection.user";
	private static final String PASSWORD = "db_connection.password";
	private static final String PATH_TO_METASTOCK_DB = "db_connection.path";
	private static final String NAME_FOR_METASTOCK_DB = "db_connection.name";
	private static final String STORAGE = "file.central_storage_of_files";
	private static final String LINK_TO_METASTOCK_DATA = "metastock.link";
	private static final String DIR_FOR_METASTOCK_ZIPPED = "metastock.download_dir";
	private static final String METASTOCK_FILE_NAME = "metastock.file_name";
	private static final String METASTOCK_UNZIP_DIR = "metastock.dir_unzipped_files";
	
	
	private final Properties properties;
	
	public StockExchangeProperties(final InputStream is) {
		this.properties = new Properties();
		
		try {
			this.properties.load(is);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
		
	}
	
	public String getNameForMEtastockDB(){
		return this.properties.getProperty(NAME_FOR_METASTOCK_DB);
	}
	
	public String getPathToMetastockDB(){
		return this.properties.getProperty(PATH_TO_METASTOCK_DB);
	}
	
	public String getMetastockUnzipDir(){
		return this.properties.getProperty(METASTOCK_UNZIP_DIR);
	}
	
	
	public String getMetastockFileName(){
		return this.properties.getProperty(METASTOCK_FILE_NAME);
	}
	
	public String getLinkToMetastock(){
		return this.properties.getProperty(LINK_TO_METASTOCK_DATA);
	}
	
	public String getDirOfZippedMetastock(){
		return this.properties.getProperty(DIR_FOR_METASTOCK_ZIPPED);
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
