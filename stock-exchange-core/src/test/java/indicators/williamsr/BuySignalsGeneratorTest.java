package indicators.williamsr;

import com.google.common.collect.Lists;
import configuration.Share;
import data.collector.StockTickerHistory;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;
import utils.FileDataReader;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;

public class BuySignalsGeneratorTest extends TestBeans {

    DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
    final static String PATH = new String("indicators/williams/buySignal/");

    @Test
    public void williamsCollectionIsEmpty() throws NumberFormatException, IOException {
        String expectedErrorMessage = "Cannot generate buy signal for empty williams R collection";
        List<WilliamsRData> williamsRCollection = new ArrayList<WilliamsRData>();
        BuySignalsGenerator buySignal = new BuySignalsGenerator();

        try {
            buySignal.generate(williamsRCollection);
            fail("No exception found when expected: " + expectedErrorMessage);
        } catch (WilliamsRCalculationException ex) {
            assertTrue("Was expecting: " + expectedErrorMessage + ", got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
        }
    }

    @Test
    public void williamsCollectionHasOneElement() throws NumberFormatException, IOException {
        String expectedErrorMessage = "Cannot generate buy signal for williams R collection which has less than 2 elements";

        WilliamsRData data = new WilliamsRData();
        data.setDate(dateFormater.parseDateTime("20100108"));
        data.setWilliamsR(20.1);

        List<WilliamsRData> williamsRCollection = new ArrayList<WilliamsRData>();
        williamsRCollection.add(data);
        BuySignalsGenerator buySignal = new BuySignalsGenerator();

        try {
            buySignal.generate(williamsRCollection);
            fail("No exception found when expected: " + expectedErrorMessage);
        } catch (WilliamsRCalculationException ex) {
            assertTrue("Was expecting: " + expectedErrorMessage + ", got: " + ex.getMessage(), ex.getMessage().equals(expectedErrorMessage));
        }
    }

    /*
     * previousWilliamsValue<=REVALUATION_TRESHOLD - TRUE
     * currentWilliamsValue>REVALUATION_TRESHOLD - TRUE
     */
    @Test
    public void conditionsTrueTrue() throws NumberFormatException, IOException {
//		List<WilliamsRData> williamsRCollection = testBeans.getWillimasCollection(PATH + "conditionsTrueTrue");

        DateTime expectedBuyDate = Share.COMMON_FORMATTER.parseDateTime("20100111");

        try {
            List<DateTime> currentResult = getCurrentResults(PATH + "conditionsTrueTrue");
            assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
            assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
        } catch (Exception ex) {
            fail("Exception when not expected: " + ex.getMessage());
        }
    }

    /*
     * previousWilliamsValue<=REVALUATION_TRESHOLD - TRUE
     * currentWilliamsValue>REVALUATION_TRESHOLD - False
     */
    @Test
    public void conditionsTrueFalse() throws NumberFormatException, IOException {

        try {
            List<DateTime> currentResult = getCurrentResults(PATH + "conditionsTrueFalse");
            assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
        } catch (Exception ex) {
            fail("Exception when not expected: " + ex.getMessage());
        }
    }

    /*
     * previousWilliamsValue<=REVALUATION_TRESHOLD - False
     * currentWilliamsValue>REVALUATION_TRESHOLD - False
     */
    @Test
    public void conditionsFalseFalse() throws NumberFormatException, IOException {

        try {
            List<DateTime> currentResult = getCurrentResults(PATH + "conditionsFalseFalse");
            assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
        } catch (Exception ex) {
            fail("Exception when not expected: " + ex.getMessage());
        }
    }

    /*
     * previousWilliamsValue<=REVALUATION_TRESHOLD - False
     * currentWilliamsValue>REVALUATION_TRESHOLD - False
     */
    @Test
    public void conditionsFalseTrue() throws NumberFormatException, IOException {

        try {
            List<DateTime> currentResult = getCurrentResults(PATH + "conditionsFalseTrue");
            assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
        } catch (Exception ex) {
            fail("Exception when not expected: " + ex.getMessage());
        }
    }

    /*
     * previousWilliamsValue = REVALUATION_TRESHOLD
     */
    @Test
    public void boundrValueAnalysis_previousWilliamsValue_Equal_RevaluationTreshold() throws NumberFormatException, IOException {
        DateTime expectedBuyDate = Share.COMMON_FORMATTER.parseDateTime("20100119");

        try {
            BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
            List<DateTime> currentResult = getCurrentResults(PATH + "boundrValueAnalysis_previousWilliamsValue_Equal_RevaluationTreshold");
            assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
            assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
        } catch (Exception ex) {
            fail("Exception when not expected: " + ex.getMessage());
        }
    }

    /*
     * currentWilliamsValue = REVALUATION_TRESHOLD
     */
    @Test
    public void boundrValueAnalysis_currentWilliamsValue_Equal_RevaluationTreshold() throws NumberFormatException, IOException {

        try {
            BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
            List<DateTime> currentResult = getCurrentResults(PATH + "boundrValueAnalysis_currentWilliamsValue_Equal_RevaluationTreshold");
            assertTrue("Buy signal generated when not expected", currentResult.size() == 0);
        } catch (Exception ex) {
            fail("Exception when not expected: " + ex.getMessage());
        }
    }

    @Test
    public void boundrValueAnalysis() throws NumberFormatException, IOException {
        DateTime expectedBuyDate = Share.COMMON_FORMATTER.parseDateTime("20100124");

        try {
            BuySignalsGenerator buySignalsGenerator = new BuySignalsGenerator();
            List<DateTime> currentResult = getCurrentResults(PATH + "boundrValueAnalysis");
            assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == 1);
            assertTrue("Currently generated buySignal date diffretn than expected.", currentResult.get(0).equals(expectedBuyDate));
        } catch (Exception ex) {
            fail("Exception when not expected: " + ex.getMessage());
        }
    }

    @Test
    public void multipleBuySignalGenerated() throws NumberFormatException, IOException {


        List<DateTime> expectedResult = FileDataReader.readData(new File(verifyGivenPath(PATH + "multipleBuySignalGenerated_expectedResults")), FileDataReader.DATE_FUNCTION);
        try {
            List<DateTime> currentResult = getCurrentResults(PATH + "multipleBuySignalGenerated");
            assertTrue("Buy signal list contain more ellements than expected", currentResult.size() == expectedResult.size());

            boolean result = false;

            for (int i = 0; i < expectedResult.size(); i++) {
                if (!expectedResult.get(i).equals(currentResult.get(i))) {
                    result = false;
                    fail("Currently generated buySignal do not much to expected. Element " + i + "has " + currentResult.get(i) + ", expected: " + expectedResult.get(i));
                } else {
                    result = true;
                }
            }

            assertTrue("Currently generated buySignal date diffretn than expected.", result);
        } catch (Exception ex) {
            fail("Exception when not expected: " + ex.getMessage());
        }
    }



    private List<DateTime> getCurrentResults(final String path) throws IOException {

        WilliamsRSignalsGenerator williamsRSignalsGenerator = prepareSignalGenerator(path);
        return Lists.transform(williamsRSignalsGenerator.buySignals(new StockTickerHistory()), SIGNAL_TO_DATETIME);
    }




}
