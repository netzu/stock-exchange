package indicators.movingaverage.complex;

import static org.junit.Assert.*;
import indicators.movingaverage.simple.SimpleMovingAverageData;

import java.io.IOException;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MocksForTests;

public class ComplexMovingAverageAverageDataTest {
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("indicators/complexMovingAverage/");	
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void getPeriodTest() throws IOException{
		List<SimpleMovingAverageData> averageData = mock.getAverageData(PATH + "getPeriodTest");
		int period = 4;
		
		try{
			ComplexMovingAverageAverageData data = new ComplexMovingAverageAverageData(period, averageData);
			assertTrue("Period differnent than expected", period == data.getPeriod());
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void getAverageData() throws IOException{
		List<SimpleMovingAverageData> averageData = mock.getAverageData(PATH + "getPeriodTest");
		int period = 4;
		
		try{
			ComplexMovingAverageAverageData data = new ComplexMovingAverageAverageData(period, averageData);
			List<SimpleMovingAverageData> currentResults = data.getAverageData();
			
			assertTrue("Piriod diffrnent than expected", averageData.equals(currentResults));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
