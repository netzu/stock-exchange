package indicators.williamsr;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import configuration.Share;
import data.collector.StockTickerHistory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import utils.FileDataReader;

public class SimpleAverageSellSignalGeneratorTest extends TestBeans {
	
	final static String PATH = new String("indicators/williams/sellSignal/");

	@Test
	public void williamsCollectionIsEmpty() throws NumberFormatException, IOException{
		String expectedErrorMessage = "Cannot generate sell signal for empty williams R collection";
		List<WilliamsRData> williamsRCollection = new ArrayList<WilliamsRData>();
		SellSignalsGenerator sellSignal = new SellSignalsGenerator();
		
		try{
			sellSignal.generate(williamsRCollection);
			fail("No exception found when expected: " + expectedErrorMessage);
		}catch(WilliamsRCalculationException ex){
			assertTrue("Was expecting: " + expectedErrorMessage + ", got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}		
	}
	
	@Test
	public void williamsCollectionHasOneElement() throws NumberFormatException, IOException{
		String expectedErrorMessage = "Cannot generate sell signal for williams R collection which has less than 2 elements";
		
		WilliamsRData data = new WilliamsRData();
		data.setDate(Share.COMMON_FORMATTER.parseDateTime("20100108"));
		data.setWilliamsR(20.1);		
		
		List<WilliamsRData> williamsRCollection = new ArrayList<WilliamsRData>();
		williamsRCollection.add(data);
		SellSignalsGenerator sellSignal = new SellSignalsGenerator();
		
		try{
			sellSignal.generate(williamsRCollection);
			fail("No exception found when expected: " + expectedErrorMessage);
		}catch(WilliamsRCalculationException ex){
			assertTrue("Was expecting: " + expectedErrorMessage + ", got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
		}		
	}
	
	/*
	 * previousWilliamsVAlue >= UNDERVALUATION_TRESHOLD - TRUE
	 * currentWilliamsValue < UNDERVALUATION_TRESHOLD - TRUE
	 */
	@Test
	public void conditionsTrueTrue() throws NumberFormatException, IOException{
		DateTime expectedBuyDate = Share.COMMON_FORMATTER.parseDateTime("20100111");

		try{
			SellSignalsGenerator sellSignal = new SellSignalsGenerator();
			List<DateTime> currentResult = getCurrentResults(PATH + "conditionsTrueTrue");
			assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
			assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * previousWilliamsVAlue >= UNDERVALUATION_TRESHOLD - TRUE
	 * currentWilliamsValue < UNDERVALUATION_TRESHOLD - False
	 */
	@Test
	public void conditionsTrueFalse() throws NumberFormatException, IOException{

		try{
			List<DateTime> currentResult = getCurrentResults(PATH + "conditionsTrueFalse");
			assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * previousWilliamsVAlue >= UNDERVALUATION_TRESHOLD - False
	 * currentWilliamsValue < UNDERVALUATION_TRESHOLD - False
	 */
	@Test
	public void conditionsFalseFalse() throws NumberFormatException, IOException{

		try{
			List<DateTime> currentResult = getCurrentResults(PATH + "conditionsFalseFalse");
			assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * previousWilliamsVAlue >= UNDERVALUATION_TRESHOLD - False
	 * currentWilliamsValue < UNDERVALUATION_TRESHOLD - False
	 */
	@Test
	public void conditionsFalseTrue() throws NumberFormatException, IOException{

		try{
			List<DateTime> currentResult = getCurrentResults(PATH + "conditionsFalseTrue");
			assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * previousWilliamsValue = UNDERVALUATION_TRESHOLD 
	 */
	@Test
	public void boundrValueAnalysis_previousWilliams_Equal_UndervaluationTreshold() throws NumberFormatException, IOException{
		DateTime expectedBuyDate = Share.COMMON_FORMATTER.parseDateTime("20100119");
		
		try{
			List<DateTime> currentResult = getCurrentResults(PATH + "boundrValueAnalysis_previousWilliams_Equal_UndervaluationTresholld");
			assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
			assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * currentWilliamsValue = UNDERVALUATION_TRESHOLD 
	 */
	@Test
	public void boundrValueAnalysis_currentWilliams_Equal_UndervaluationTreshold() throws NumberFormatException, IOException{

		try{
			List<DateTime> currentResult = getCurrentResults(PATH + "boundrValueAnalysis_currentWilliams_Equal_UndervaluationTresholld");
			assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	/*
	 * currentWilliamsValue < UNDERVALUATION_TRESHOLD 
	 */
	@Test
	public void boundrValueAnalysis_currentWilliams_SmallerThan_UndervaluationTreshold() throws NumberFormatException, IOException{
		DateTime expectedBuyDate = Share.COMMON_FORMATTER.parseDateTime("20100124");
		
		try{
			List<DateTime> currentResult = getCurrentResults(PATH + "boundrValueAnalysis_currentWilliams_SmallerThan_UndervaluationTreshold");
			assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
			assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
		}catch(Exception ex){
			fail("Exception when not expected: " + ex.getMessage());
		}
	}
	
	@Test
	public void multipleSellSignalGenerated() throws NumberFormatException, IOException{
		List<DateTime> expectedResult = FileDataReader.readData(new File(verifyGivenPath(PATH + "multipleSellSignalGenerated_expectedResults")), FileDataReader.DATE_FUNCTION);
		
		try{
			List<DateTime> currentResult = getCurrentResults(PATH + "multipleSellSignalGenerated");
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

    private List<DateTime> getCurrentResults(final String path) throws IOException {

        WilliamsRSignalsGenerator williamsRSignalsGenerator = prepareSignalGenerator(path);
        return Lists.transform(williamsRSignalsGenerator.sellSignals(new StockTickerHistory()), SIGNAL_TO_DATETIME);
    }
}
