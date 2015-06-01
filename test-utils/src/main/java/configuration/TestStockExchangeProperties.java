package configuration;

import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Properties;

import static paths.ResourcesUtils.getResourcePath;

/**
 * Created by brudnick on 2015-04-28.
 */
public class TestStockExchangeProperties extends StockExchangeProperties {


    private static final String JDBC_PATH_SEPARATOR = ":";

    public TestStockExchangeProperties(final StockExchangeProperties stockExchangeProperties) {

        super(stockExchangeProperties.getProperties());
        expandPaths();

    }

    public TestStockExchangeProperties(final Properties properties) {
        super(properties);
        expandPaths();
    }

    private Properties expandPaths() {

        final Properties properties = getProperties();

        if (null != getCentralStorage()) {
            final String centralStorage = getResourcePath(getCentralStorage());
            properties.setProperty(STORAGE, centralStorage);
        }

        if (null != getDBUrlPath()) {
            final String jdbcPath = expandJDBCPath(getDBUrlPath());
            properties.setProperty(URL_PATH, jdbcPath);
        }
        if (null != getDirOfZippedMetastock()) {
            final String downloadZippedDir = getResourcePath(getDirOfZippedMetastock());
            properties.setProperty(DIR_FOR_METASTOCK_ZIPPED, downloadZippedDir);
        }
        if (null != getDirOfZippedMetastock()) {
            final String unzippFolder = getResourcePath(getMetastockUnzipDir());
            properties.setProperty(METASTOCK_UNZIP_DIR, unzippFolder);
        }




        return properties;
    }

    private String expandJDBCPath(final String path) {



        String[] splitResult = path.split(JDBC_PATH_SEPARATOR);

        String pathToExpand = splitResult[splitResult.length - 1];

        final String databaseFullPath = getResourcePath(pathToExpand);
        final Joiner joiner = Joiner.on(":").skipNulls();

        ArrayList<String> strings = Lists.newArrayList(Iterables.limit(Lists.newArrayList(splitResult), splitResult.length - 1));
        strings.add(databaseFullPath);


        return joiner.join(strings);
    }



}
