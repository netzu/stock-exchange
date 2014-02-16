package indicators.simpleMovingAverage;

import data.DataFileReader;
import data.collector.StockTickerHistory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.io.*;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for SimpleMovingAverageSignals functionality.
 */
public class SimpleMovingAverageSignalsTest {


    @Test
    public void basicTest() throws IOException, ParseException {
        List<SimpleMovingAverageData> averageData = getAverageData("indicators/simpleMovingAverage/simpleBuySignal/test_average_data.txt");

        StockTickerHistory history = readTickerData("indicators/simpleMovingAverage/simpleBuySignal/test_ticker_data.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        signals.generateSimpleSignals(averageData, history);

    }



    private List<SimpleMovingAverageData> getAverageData(final String path) throws IOException {

        DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
        final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        final List<SimpleMovingAverageData> result = new ArrayList<SimpleMovingAverageData>();

        String line = null;

        while ((line = reader.readLine()) != null) {

            String[] splitData = line.split(",");

            if (splitData.length != 2) {
                throw new IllegalStateException("Wrong data format, expected date<yyyyMMdd>,price<double>");
            }

            SimpleMovingAverageData averageData = new SimpleMovingAverageData();
            averageData.setAverage(Double.parseDouble(splitData[1]));
            final DateTime date = dateFormater.parseDateTime(splitData[0]);
            averageData.setDate(date);

            result.add(averageData);
        }

        return result;

    }

    private StockTickerHistory readTickerData(final String path) throws ParseException {


        URL resource = this.getClass().getClassLoader().getResource(path);

        final String filePath = resource.getPath();

        DataFileReader fileReader = new DataFileReader();

        return fileReader.getStockTickerCollection(new File(filePath));
    }

}



