package configuration;

import java.io.InputStream;
import java.util.Properties;

public class StockExchangeProperties {

    protected static final String DRIVER = "db_connection.driver";
    protected static final String URL_PATH = "db_connection.url";
    protected static final String URL_DRIVER = "db_connection.driver";
    protected static final String USER = "db_connection.user";
    protected static final String PASSWORD = "db_connection.password";
	protected static final String STORAGE = "file.central_storage_of_files";
    protected static final String LINK_TO_METASTOCK_DATA = "metastock.link";
    protected static final String DIR_FOR_METASTOCK_ZIPPED = "metastock.download_dir";
    protected static final String METASTOCK_FILE_NAME = "metastock.file_name";
    protected static final String METASTOCK_UNZIP_DIR = "metastock.dir_unzipped_files";

	private final Properties properties;

	public StockExchangeProperties(final InputStream is) {
		this.properties = new Properties();

		try {
			this.properties.load(is);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

    public StockExchangeProperties(final Properties properties) {
        this.properties = properties;
    }


	public String getMetastockUnzipDir() {
		return this.properties.getProperty(METASTOCK_UNZIP_DIR);
	}

	public String getMetastockFileName() {
		return this.properties.getProperty(METASTOCK_FILE_NAME);
	}

	public String getLinkToMetastock() {
		return this.properties.getProperty(LINK_TO_METASTOCK_DATA);
	}

	public String getDirOfZippedMetastock() {
		return this.properties.getProperty(DIR_FOR_METASTOCK_ZIPPED);
	}

	public String getDBDriver() {
		return this.properties.getProperty(DRIVER);
	}

	public String getDBUrlPath() {
		return this.properties.getProperty(URL_PATH);
	}
	
	public String getDBUrlDriver() {
		return this.properties.getProperty(URL_DRIVER);
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

    protected Properties getProperties() {
        return this.properties;
    }
}
