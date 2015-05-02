package indicators.movingaverage.complex;

import static org.junit.Assert.fail;
import indicators.movingaverage.simple.SimpleMovingAverageData;
import indicators.williamsr.TestBeans;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.junit.Test;

import data.collector.StockTickerHistory;

public class CompexMovingAverageIndicatorTest {
	
	final static String PATH = new String("indicators/complexMovingAverage/");	
	TestBeans mock = new TestBeans();

	@Test
	public void test() throws IOException, ParseException{
		List<SimpleMovingAverageData> first = mock.getAverageData(PATH + "FirstSimpleMovingAverageData");
		List<SimpleMovingAverageData> second = mock.getAverageData(PATH + "SecondSimpleMovingAverageData");
		List<SimpleMovingAverageData> third = mock.getAverageData(PATH + "ThirdSimpleMovingAverageData");
		
		StockTickerHistory stockCollection = mock.readTickerData(PATH + "tickerCollection");
		
		try{
//			CompexMovingAverageIndicator indicator = new CompexMovingAverageIndicator();
            final ComplexMovingAverageSettings settings = new ComplexMovingAverageSettings(4,5,7);
            final ComplexMovingAverageSignalsGenerator generator = new ComplexMovingAverageSignalsGenerator(settings);


//			List<ComplexMovingAverageSettings> currentResults = indicator.calculateComplexMovingAverage(4, 5, 7, stockCollection);
//
//			assertTrue("First SimpleMovingAverage size is diffrent than expected",currentResults.get(0).getAverageData().size() == first.size());
//			assertTrue("Second SimpleMovingAverage size is diffrent than expected",currentResults.get(1).getAverageData().size() == second.size());
//			assertTrue("Third SimpleMovingAverage size is diffrent than expected",currentResults.get(2).getAverageData().size() == third.size());
//
//			assertTrue("First SimpleMovingaAverage is diffrent than expected", CompareSimpleMovingAverageLists.compare(first, currentResults.get(0).getAverageData()));
//			assertTrue("Second SimpleMovingaAverage is diffrent than expected", CompareSimpleMovingAverageLists.compare(second, currentResults.get(1).getAverageData()));
//			assertTrue("Third SimpleMovingaAverage is diffrent than expected", CompareSimpleMovingAverageLists.compare(third, currentResults.get(2).getAverageData()));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
