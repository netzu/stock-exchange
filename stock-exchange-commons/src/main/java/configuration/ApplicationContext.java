package configuration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class ApplicationContext {

	static StockExchangeProperties properties;

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
			FileInputStream fis;
			try {
				fis = new FileInputStream(propertiesFile);
			} catch (FileNotFoundException e) {
				throw new IllegalStateException(e);
			}
			properties = new StockExchangeProperties(fis);
		}
		return properties;
	}
}