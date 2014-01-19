package configuration;

import java.io.InputStream;

public class ApplicationContext {
	
	static StockExchangeProperties properties;

	
	public static StockExchangeProperties getPropertiesInstance() {
		if (null == properties) {
			
			InputStream is = ApplicationContext.class.getClassLoader().getSystemResourceAsStream("StockExchange.properties");
			properties = new StockExchangeProperties(is);
		}
		
		return properties;
	}
}
