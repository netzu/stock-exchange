package configuration;

/**
 * Created by brudnick on 2015-04-30.
 */
public class TestApplicationContext {

    private TestApplicationContext(){};

    public static StockExchangeProperties getTestPropertiesInstance(String propertiesFile) {
        final StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance(propertiesFile);

        return new TestStockExchangeProperties(propertiesInstance);
    }

}
