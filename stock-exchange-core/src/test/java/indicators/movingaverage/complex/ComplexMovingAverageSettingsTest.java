package indicators.movingaverage.complex;

import static org.junit.Assert.*;
import indicators.movingaverage.simple.SimpleMovingAverageData;

import java.io.IOException;
import java.util.List;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Ignore;
import org.junit.Test;

import indicators.williamsr.TestBeans;

public class ComplexMovingAverageSettingsTest {
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("indicators/complexMovingAverage/");	
	TestBeans mock = new TestBeans();
	
	@Test
    @Ignore
	public void getPeriodTest() throws IOException{
//		List<SimpleMovingAverageData> averageData = mock.getAverageData(PATH + "getPeriodTest");
//		int period = 4;
//
//		try{
//			ComplexMovingAverageSettings data = new ComplexMovingAverageSettings(period, averageData);
//			assertTrue("Period differnent than expected", period == data.getPeriod());
//		}catch(Exception ex){
//			fail("Exception when not expected: " + ex.getMessage());
//		}
	}
	
	@Test
    @Ignore
	public void getAverageData() throws IOException{
//		List<SimpleMovingAverageData> averageData = mock.getAverageData(PATH + "getPeriodTest");
//		int period = 4;
//
//		try{
//			ComplexMovingAverageSettings data = new ComplexMovingAverageSettings(period, averageData);
//			List<SimpleMovingAverageData> currentResults = data.getAverageData();
//
//			assertTrue("Piriod diffrnent than expected", averageData.equals(currentResults));
//		}catch(Exception ex){
//			fail("Exception when not expected: " + ex.getMessage());
//		}
	}
}
