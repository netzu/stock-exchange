package configuration;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static paths.ResourcesUtils.getResourceStream;

public class TestStockExchangePropertiesTest{

    @Test
    public void pathToCentralStorageResolvedProperly() throws IOException {

        final Properties properties = loadTestProperties("StockExchange.properties");
        TestStockExchangeProperties testStockExchangeProperties = new TestStockExchangeProperties(properties);

        final String centralStorage = testStockExchangeProperties.getCentralStorage();

        final File f = new File(centralStorage);

        assertThat(f.exists()).isTrue();
        assertThat(f.isDirectory()).isTrue();


    }

    private Properties loadTestProperties(final String propsName) throws IOException {

        final InputStream is = getResourceStream(propsName);

        assertThat(is).isNotNull();

        final Properties properties = new Properties();

        properties.load(is);

        return properties;
    }




}
