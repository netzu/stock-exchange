package buy.signal.measurements;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import indicators.williamsr.TestBeans;

public class MaximumProfitTest {	
	
	final static String PATH = new String("buySignalMeasurements/MaximumProfitTest/");
	TestBeans mock = new TestBeans();
	
	@Test
	public void emptyDelta() throws IOException{
		
		List<Double> delta = new ArrayList<Double>();		
		MaximumProfit profit = new MaximumProfit();
		
		String exceptionMessage = "Delta is empty";
		
		try{
			profit.calulateInValue(delta);
			fail("No exception when expecting");
		}catch(PriceDeltaEmptyException ex){
			assertTrue("\nWas expecting \n" + exceptionMessage + ", \nbut got \n" + ex.getMessage(), ex.getMessage().equals(exceptionMessage));
		}		
	}
	
	@Test
	public void noProfitFound() throws IOException{
		List<Double> delta = mock.getListOfDoubles(PATH + "noProfitFound");
		MaximumProfit profit = new MaximumProfit();
		
		String exceptionMessage = "There was no profit fot this signal";
		
		try{
			profit.calulateInValue(delta);
			fail("No exception when expecting");
		}catch(ProfitException ex){
			assertTrue("\nWas expecting \n" + exceptionMessage + ", \nbut got \n" + ex.getMessage(), ex.getMessage().equals(exceptionMessage));
		}		
	}
	
	@Test
	public void firstTheBiggest() throws IOException{
		List<Double> delta = mock.getListOfDoubles(PATH + "firstTheBiggest");
		MaximumProfit profit = new MaximumProfit();
		
		double expectedResult = delta.get(0).doubleValue();
		double currentResult = profit.calulateInValue(delta);
		
		assertThat(Math.abs(expectedResult - currentResult)).isLessThan(0.001);
	}
	
	@Test
	public void lastTheBigestValue() throws IOException{
		List<Double> delta = mock.getListOfDoubles(PATH + "lastTheBigestValue");
		MaximumProfit profit = new MaximumProfit();
		
		int lastElement = delta.size()-1;
		
		double expectedResult = delta.get(lastElement).doubleValue();
		double currentResult = profit.calulateInValue(delta);
		
		assertThat(Math.abs(expectedResult - currentResult)).isLessThan(0.001);
	}
}
