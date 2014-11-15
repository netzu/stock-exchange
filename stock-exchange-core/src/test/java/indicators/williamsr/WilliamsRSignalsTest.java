package indicators.williamsr;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import data.collector.StockTickerHistory;
import indicators.Signal;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class WilliamsRSignalsTest {
	
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("indicators/williams/signals/");	
	TestBeans mock = new TestBeans();
	
	@Test
	public void buySignal() throws NumberFormatException, IOException{

        List<DateTime> currentResults = getBuySignals(PATH + "williamsCollection");

        List<DateTime> expectedResults = mock.loadExpectedSignals(PATH + "buySignal_expectedResults");

        assertThat(currentResults).hasSameSizeAs(expectedResults).containsAll(expectedResults);

	}
	
	@Test
	public void sellSignal() throws NumberFormatException, IOException{
		List<DateTime> expectedResults = mock.loadExpectedSignals(PATH + "sellSignal_expectedResults");
		
	    List<DateTime> currentResults = getSellSignals(PATH + "williamsCollection");

        assertThat(currentResults).hasSameSizeAs(expectedResults).containsAll(expectedResults);
	}

    private WilliamsRSignalsGenerator prepareGeneratorWithMockData(final String path, final int dayNum) throws IOException {



        final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		final List<WilliamsRData> result = Lists.newArrayList();

		String line;

		while ((line = reader.readLine()) != null) {

			String[] splitData = line.split(",");

			if (splitData.length != 2) {
				throw new IllegalStateException(
						"Wrong data format, expected date<yyyyMMdd>,price<double>");
			}

			WilliamsRData williamsRData = new WilliamsRData();
			williamsRData.setWilliamsR(Double.parseDouble(splitData[1]));
			final DateTime date = dateFormater.parseDateTime(splitData[0]);
			williamsRData.setDate(date);

			result.add(williamsRData);
        }
        final WilliamsRIndicator mockIndicator = mock(WilliamsRIndicator.class);
        when(mockIndicator.calculateWilliamsR(any(StockTickerHistory.class))).thenReturn(result);

        return new WilliamsRSignalsGenerator(dayNum) {

            @Override
            WilliamsRIndicator getIndicator() {

                return mockIndicator;
            }

        };
    }


    private List<DateTime> getBuySignals(final String path) throws IOException {
        WilliamsRSignalsGenerator williamsRSignalsGenerator = prepareGeneratorWithMockData(path ,5);

        List<Signal> signals = williamsRSignalsGenerator.buySignals(new StockTickerHistory());

        return Lists.transform(signals, new Function<Signal, DateTime>() {
            @Override
            public DateTime apply(Signal signal) {
                return signal.getDate();
            }
        });

    }

    private List<DateTime> getSellSignals(final String path) throws IOException {
        WilliamsRSignalsGenerator williamsRSignalsGenerator = prepareGeneratorWithMockData(path ,5);

        List<Signal> signals = williamsRSignalsGenerator.sellSignals(new StockTickerHistory());

        return Lists.transform(signals, new Function<Signal, DateTime>() {
            @Override
            public DateTime apply(Signal signal) {
                return signal.getDate();
            }
        });

    }



}
