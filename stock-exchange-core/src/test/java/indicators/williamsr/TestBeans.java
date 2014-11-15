package indicators.williamsr;

import com.google.common.base.Function;
import configuration.Share;
import indicators.Signal;
import indicators.movingaverage.simple.SimpleMovingAverageData;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.util.List;

import org.joda.time.DateTime;

import data.DataFileReader;
import data.collector.StockTickerHistory;
import utils.FileDataReader;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestBeans {

    static Function<Signal, DateTime> SIGNAL_TO_DATETIME = new SignalDateTimeFunction();


	public List<SimpleMovingAverageData> getAverageData(final String path) throws IOException {

        return FileDataReader.readData(new File(verifyGivenPath(path)), new SimpleAverageDataTransform());
	}

	public List<DateTime> loadExpectedSignals(final String path) throws IOException {


		return FileDataReader.readData(new File(verifyGivenPath(path)), FileDataReader.DATE_FUNCTION);
	}


	public List<Double> getListOfDoubles(final String path) throws IOException {


		return FileDataReader.readData(new File(verifyGivenPath(path)), FileDataReader.DOUBLE_FUNCTION);
	}


	public StockTickerHistory readTickerData(final String path) throws ParseException {

		URL resource = this.getClass().getClassLoader().getResource(path);

		final String filePath = resource.getPath();

		DataFileReader fileReader = new DataFileReader();

		return fileReader.getStockTickerCollection(new File(filePath));
	}

    protected String verifyGivenPath(final String path) {

        URL resource = this.getClass().getClassLoader().getResource(path);

        if (resource == null) {
            throw new IllegalStateException(String.format("Given resource, %s doesn't exists", path));
        }

        return resource.getPath();
    }

    protected WilliamsRSignalsGenerator prepareSignalGenerator(final String path) throws IOException {

        List<WilliamsRData> williamsRDatas = FileDataReader.readData(new File(verifyGivenPath(path)), new WilliamsRFunction());

        final WilliamsRIndicator mockWilliamsRIndicator = mock(WilliamsRIndicator.class);
        when(mockWilliamsRIndicator.calculateWilliamsR(any(StockTickerHistory.class))).thenReturn(williamsRDatas);


        return new WilliamsRSignalsGenerator(5) {

            WilliamsRIndicator getIndicator() {

                return mockWilliamsRIndicator;
            }

        };
    }

    private static class SimpleAverageDataTransform implements Function<String, SimpleMovingAverageData> {
        @Override
        public SimpleMovingAverageData apply(String s) {

            String[] splitData = s.split(",");

            if (splitData.length != 2) {
                throw new IllegalStateException(
                        "Wrong data format, expected date<yyyyMMdd>,price<double>");
            }

            SimpleMovingAverageData averageData = new SimpleMovingAverageData();
            averageData.setAverage(Double.parseDouble(splitData[1]));
            final DateTime date = Share.COMMON_FORMATTER.parseDateTime(splitData[0]);
            averageData.setDate(date);

            return averageData;
        }
    }

    private static final class SignalDateTimeFunction implements Function<Signal, DateTime> {

        @Override
        public DateTime apply(Signal signal) {
            return signal.getDate();
        }
    }
    private class WilliamsRFunction implements Function<String, WilliamsRData> {

        @Override
        public WilliamsRData apply(String s) {


            String[] splitData = s.split(",");

            if (splitData.length != 2) {
                throw new IllegalStateException(
                        "Wrong data format, expected date<yyyyMMdd>,price<double>");
            }

            WilliamsRData williamsRData = new WilliamsRData();

            williamsRData.setWilliamsR(Double.parseDouble(splitData[1]));
            final DateTime date = Share.COMMON_FORMATTER.parseDateTime(splitData[0]);
            williamsRData.setDate(date);

            return williamsRData;
        }
    }
}