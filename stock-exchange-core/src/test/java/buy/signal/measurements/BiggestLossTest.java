package buy.signal.measurements;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import buy.signal.measurements.BiggestLoss;
import buy.signal.measurements.PriceDeltaEmptyException;

import utils.MocksForTests;

public class BiggestLossTest {
	
	final static String PATH = new String("buySignalMeasurements/BiggestLossTest/");
	MocksForTests mock = new MocksForTests();

	@Test
	public void emptyDelta() throws IOException{
		
		List<Double> delta = new ArrayList<Double>();		
		BiggestLoss loss = new BiggestLoss();
		
		String exceptionMessage = "Delta is empty";
		
		try{
			loss.calulateInValue(delta);
			fail("No exception when expecting");
		}catch(PriceDeltaEmptyException ex){
			assertTrue("\nWas expecting \n" + exceptionMessage + ", \nbut got \n" + ex.getMessage(), ex.getMessage().equals(exceptionMessage));
		}		
	}
	
	
	@Test
	public void firstTheBiggestLoss() throws IOException{
		List<Double> delta = mock.getListOfDoubles(PATH + "firstTheBiggestLoss");
		BiggestLoss loss = new BiggestLoss();
		
		double expectedResult = delta.get(0).doubleValue();
		double currentResult = loss.calulateInValue(delta);
		
		assertThat(Math.abs(expectedResult - currentResult)).isLessThan(0.001);
	}
	
	@Test
	public void lastTheBiggestLoss() throws IOException{
		List<Double> delta = mock.getListOfDoubles(PATH + "lastTheBiggestLoss");
		BiggestLoss loss = new BiggestLoss();
		
		int lastElement = delta.size()-1;
		
		double expectedResult = delta.get(lastElement).doubleValue();
		double currentResult = loss.calulateInValue(delta);
		
		assertThat(Math.abs(expectedResult - currentResult)).isLessThan(0.001);
	}
	
	@Test
	public void zeroTheBiggestLoss() throws IOException{
		List<Double> delta = mock.getListOfDoubles(PATH + "zeroTheBiggestLoss");
		BiggestLoss loss = new BiggestLoss();
		
		String exceptionMessage = "There was no loss";
		
		try{
			loss.calulateInValue(delta);
			fail("No exception when expecting");
		}catch(PriceDeltaEmptyException ex){
			assertTrue("\nWas expecting \n" + exceptionMessage + ", \nbut got \n" + ex.getMessage(), ex.getMessage().equals(exceptionMessage));
		}
	}
}
