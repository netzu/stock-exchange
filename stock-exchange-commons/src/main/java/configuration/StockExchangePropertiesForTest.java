package configuration;

import java.io.InputStream;
import java.util.Properties;

public class StockExchangePropertiesForTest{
	
	private final Properties properties;
	
	private static final String GET_LIST_OF_FILES_MST_ONLY= "metastockFilesCollectionTest.getListOfFilesMSTOnly";

	public StockExchangePropertiesForTest(InputStream is) {
		
		this.properties = new Properties();

		try {
			this.properties.load(is);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}

	public static String getGetListOfFilesMstOnly() {
		return GET_LIST_OF_FILES_MST_ONLY;
	}

	
}
