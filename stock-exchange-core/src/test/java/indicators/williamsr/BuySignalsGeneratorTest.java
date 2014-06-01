package indicators.williamsr;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MocksForTests;

public class BuySignalsGeneratorTest {
	
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");	
	final static String PATH = new String("indicators/williams/buySignal/");	
	MocksForTests mock = new MocksForTests();
	
	@Test
	public void williamsCollectionIsEmpty() throws NumberFormatException, IOException{
		String expectedErrorMessage = "Cannot generate buy signal for empty williams R collection";
		List<WilliamsRData> williamsRCollection = new ArrayList<WilliamsRData>();
		BuySignalsGenerator buySignal = new BuySignalsGenerator();
		
		try{
			buySignal.generate(williamsRCollection);
			fail("No exception found when expected: " + expectedErrorMessage);
		}catch(WilliamsRCalculationException ex){
			assertTrue("Was expecting: " + expectedErrorMessage + ", got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}		
	}
	
	@Test
	public void williamsCollectionHasOneElement() throws NumberFormatException, IOException{
		String expectedErrorMessage = "Cannot generate buy signal for williams R collection which has less than 2 elements";
		
		WilliamsRData data = new WilliamsRData();
		data.setDate(dateFormater.parseDateTime("20100108"));
		data.setWilliamsR(20.1);		
		
		List<WilliamsRData> williamsRCollection = new ArrayList<WilliamsRData>();
		williamsRCollection.add(data);
		BuySignalsGenerator buySignal = new BuySignalsGenerator();
		
		try{
			buySignal.generate(williamsRCollection);
			fail("No exception found when expected: " + expectedErrorMessage);
		}catch(WilliamsRCalculationException ex){
			assertTrue("Was expecting: " + expectedErrorMessage + ", got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}		
	}

	/*
	 * previousWilliamsValue<=REVALUATION_TRESHOLD - TRUE
	 * currentWilliamsValue>REVALUATION_TRESHOLD - TRUE
	 */
	@Test
	public void conditionsTrueTrue() throws NumberFormatException, IOException{
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "conditionsTrueTrue");
		DateTime expectedBuyDate = dateFormater.parseDateTime("20100111");
		
		try{
			BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
			List<DateTime> currentResult = buySignalsGenerator.generate(williamsRCollection);
			assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
			assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * previousWilliamsValue<=REVALUATION_TRESHOLD - TRUE
	 * currentWilliamsValue>REVALUATION_TRESHOLD - False
	 */
	@Test
	public void conditionsTrueFalse() throws NumberFormatException, IOException{
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "conditionsTrueFalse");
		
		try{
			BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
			List<DateTime> currentResult = buySignalsGenerator.generate(williamsRCollection);
			assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * previousWilliamsValue<=REVALUATION_TRESHOLD - False
	 * currentWilliamsValue>REVALUATION_TRESHOLD - False
	 */
	@Test
	public void conditionsFalseFalse() throws NumberFormatException, IOException{
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "conditionsFalseFalse");
		
		try{
			BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
			List<DateTime> currentResult = buySignalsGenerator.generate(williamsRCollection);
			assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * previousWilliamsValue<=REVALUATION_TRESHOLD - False
	 * currentWilliamsValue>REVALUATION_TRESHOLD - False
	 */
	@Test
	public void conditionsFalseTrue() throws NumberFormatException, IOException{
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "conditionsFalseTrue");
		
		try{
			BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
			List<DateTime> currentResult = buySignalsGenerator.generate(williamsRCollection);
			assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * previousWilliamsValue = REVALUATION_TRESHOLD 
	 */
	@Test
	public void boundrValueAnalysis_previousWilliamsValue_Equal_RevaluationTreshold() throws NumberFormatException, IOException{
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "boundrValueAnalysis_previousWilliamsValue_Equal_RevaluationTreshold");
		DateTime expectedBuyDate = dateFormater.parseDateTime("20100119");
		
		try{
			BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
			List<DateTime> currentResult = buySignalsGenerator.generate(williamsRCollection);
			assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
			assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * currentWilliamsValue = REVALUATION_TRESHOLD 
	 */
	@Test
	public void boundrValueAnalysis_currentWilliamsValue_Equal_RevaluationTreshold() throws NumberFormatException, IOException{
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "boundrValueAnalysis_currentWilliamsValue_Equal_RevaluationTreshold");		

		try{
			BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
			List<DateTime> currentResult = buySignalsGenerator.generate(williamsRCollection);
			assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void boundrValueAnalysis() throws NumberFormatException, IOException{
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "boundrValueAnalysis");
		DateTime expectedBuyDate = dateFormater.parseDateTime("20100124");
		
		try{
			BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
			List<DateTime> currentResult = buySignalsGenerator.generate(williamsRCollection);
			assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
			assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void multipleBuySignalGenerated() throws NumberFormatException, IOException{
		List<WilliamsRData> williamsRCollection = mock.getWillimasCollection(PATH + "multipleBuySignalGenerated");
		List<DateTime> expectedResult = mock.getBuysignal(PATH + "multipleBuySignalGenerated_expectedResults");
		
		try{
			BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
			List<DateTime> currentResult = buySignalsGenerator.generate(williamsRCollection);
			assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == expectedResult.size());
			
			boolean result = false;
			
			for(int i=0; i<expectedResult.size(); i++){
				if(!expectedResult.get(i).equals(currentResult.get(i))){
					result = false;
					fail("Currently generated buySignal do not much to expected. Element " + i + "has " + currentResult.get(i) + ", expected: " + expectedResult.get(i));
				}else{
					result = true;
				}
			}
			
			assertTrue("Currently generated buySignal date diffretn than expected.", result);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
}
