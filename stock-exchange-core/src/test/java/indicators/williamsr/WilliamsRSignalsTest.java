package indicators.williamsr;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MocksForTests;

public class WilliamsRSignalsTest {
	
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("indicators/williams/signals/");	
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void buySignal() throws NumberFormatException, IOException{
		
		WilliamsRSignals signals = new WilliamsRSignals();
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "williamsCollection");
		List<DateTime> expectedResults = mock.getBuysignal(PATH + "buySignal_expectedResults");
		
		try{
			List<DateTime> currentResults = signals.buySignals(williamsRCollection);
			assertTrue("Buy signal list contain more ellements than expected", currentResults.size() == expectedResults.size());
			
			boolean result = false;
			
			for(int i=0; i<expectedResults.size(); i++){
				if(!expectedResults.get(i).equals(currentResults.get(i))){
					result = false;
					fail("Currently generated sellSignal do not much to expected. Element " + i + "has " + currentResults.get(i) + ", expected: " + expectedResults.get(i));
				}else{
					result = true;
				}
			}
			assertTrue("Currently generated sellSignal date diffretn than expected.", result);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void sellSignal() throws NumberFormatException, IOException{
		WilliamsRSignals signals = new WilliamsRSignals();
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "williamsCollection");
		List<DateTime> expectedResults = mock.getBuysignal(PATH + "sellSignal_expectedResults");
		
		try{
			List<DateTime> currentResults = signals.sellSignals(williamsRCollection);
			assertTrue("Buy signal list contain more elements than expected - " + currentResults.size(), currentResults.size() == expectedResults.size());

			boolean result = false;

			for(int i=0; i<expectedResults.size(); i++){
				if(!expectedResults.get(i).equals(currentResults.get(i))){
					result = false;
					fail("Currently generated sellSignal do not much to expected. Element " + i + "has " + currentResults.get(i) + ", expected: " + expectedResults.get(i));
				}else{
					result = true;
				}
			}
			assertTrue("Currently generated sellSignal date diffretn than expected.", result);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
