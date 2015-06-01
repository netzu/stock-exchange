package configuration;

import static paths.ResourcesUtils.getResourcePath;

/**
 * Created by brudnick on 2015-04-30.
 */
public class TestApplicationContext {

    private TestApplicationContext(){};

    public static StockExchangeProperties getTestPropertiesInstance(String propertiesFile) {
        String resourcePath = getResourcePath(propertiesFile);
        final StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance(resourcePath);

        return new TestStockExchangeProperties(propertiesInstance);
    }

}
