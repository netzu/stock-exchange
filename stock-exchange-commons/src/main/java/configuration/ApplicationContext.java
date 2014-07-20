package configuration;

import java.io.InputStream;

public class ApplicationContext {

	static StockExchangeProperties properties;
	static StockExchangePropertiesForTest propertiesForTest;

	public static StockExchangeProperties getPropertiesInstance() {
		if (null == properties) {
			ApplicationContext.class.getClassLoader();
			InputStream is = ClassLoader.getSystemResourceAsStream("StockExchange.properties");
			properties = new StockExchangeProperties(is);
		}
		return properties;
	}
	
	public static StockExchangeProperties getPropertiesInstance(String propertiesFile) {
		if (null == properties) {
			ApplicationContext.class.getClassLoader();
			InputStream is = ClassLoader.getSystemResourceAsStream(propertiesFile);
			properties = new StockExchangeProperties(is);
		}
		return properties;
	}
	
	public static StockExchangePropertiesForTest getPropertiesInstanceForTest(String propertiesFile) {
		if (null == propertiesForTest) {
			ApplicationContext.class.getClassLoader();
			InputStream is = ClassLoader.getSystemResourceAsStream(propertiesFile);
			propertiesForTest = new StockExchangePropertiesForTest(is);
		}
		return propertiesForTest;
	}

}