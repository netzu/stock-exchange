package indicators.movingaverage.simple;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import indicators.DateTimeFromSignal;
import indicators.williamsr.TestBeans;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Test;

import com.google.common.collect.Lists;

import configuration.Share;
import data.collector.StockTickerHistory;

/**
 * Test class for SimpleMovingAverageSignals functionality.
 */
public class MovingAverageSignalsGeneratorTest {

    private static final int period = 5;
	
	final static String PATH_FLAT = "indicators/simpleMovingAverage/flatAverage/";
	final static String PATH_GROWING = "indicators/simpleMovingAverage/growingAverage/";
	final static String PATH_DROPING = "indicators/simpleMovingAverage/dropingAverage/";
	
	TestBeans testBeans = new TestBeans();
	
	
   @Test
   public void Test_001() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData = testBeans.getAverageData(PATH_FLAT + "test_001_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_001_ticker.mst");
       
        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));
        
        List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
        List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());
                

        assertTrue(buySignal.isEmpty());        
        assertTrue(sellSignal.isEmpty());        
    }
   
   @Test
   public void Test_002() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_002_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_002_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());        
    }
   
   @Test
   public void Test_003() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_003_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_003_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

        assertTrue(buySignal.isEmpty());
        //DateTime date = dateFormater.parseDateTime("20100128");
        //assertTrue(buySignal.contains(date));
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_004() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_004_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_004_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_005() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_005_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_005_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
        //DateTime date = new DateTime("20100128");
        //assertTrue(sellSignal.contains(date));
    }

   @Test
   public void Test_006() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_006_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_006_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_007() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_007_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_007_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }

   
   @Test
   public void Test_008() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_008_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_008_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_009() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_009_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_009_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_010() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_010_average_data.txt");
        StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_010_ticker.mst");

        MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
        //DateTime date = new DateTime("20100330");
        //assertTrue(buySignal.contains(date));
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_011() throws IOException, ParseException {
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_011_average_data.txt");

       StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_011_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       DateTime dt = Share.COMMON_FORMATTER.parseDateTime("20100409");
       
       assertFalse(buySignal.isEmpty());       
       assertEquals(1, buySignal.size());
       assertTrue(buySignal.contains(dt));
       
       assertTrue(sellSignal.isEmpty());
   }

   @Test
   public void Test_012() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_012_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_012_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       //DateTime dt = dateFormater.parseDateTime("20100419");
       
       //assertFalse(buySignal.isEmpty());
       //assertEquals(1, buySignal.size());
       //assertTrue(buySignal.contains(dt));
       
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_013() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_013_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_013_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
       
//       DateTime dt = dateFormater.parseDateTime("20100419");
//       
//       assertFalse(sellSignal.isEmpty());       
//       assertEquals(1, sellSignal.size());
//      assertTrue(sellSignal.contains(dt));
   }
   
   
   @Test
   public void Test_014() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_014_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_014_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
      
       DateTime dt = Share.COMMON_FORMATTER.parseDateTime("20100419");       
       assertFalse(sellSignal.isEmpty());       
       assertEquals(1, sellSignal.size());
       assertTrue(sellSignal.contains(dt));
   }
   
   @Test
   public void Test_015() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_FLAT + "test_015_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_FLAT + "test_015_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());

       assertTrue(sellSignal.isEmpty());
//       DateTime dt = dateFormater.parseDateTime("20100419");
//       
//       assertFalse(sellSignal.isEmpty());       
//       assertEquals(1, sellSignal.size());
//       assertTrue(sellSignal.contains(dt));
   }
   
   @Test
   public void Test_016() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_016_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_016_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());

   }
   
   @Test
   public void Test_017() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_017_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_017_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_018() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_018_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_018_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_019() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_019_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_019_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_020() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_020_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_020_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_021() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_021_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_021_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_022() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_022_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_022_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_023() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_023_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_023_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_024() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_024_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_024_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_025() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_025_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_025_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_026() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_026_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_026_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       DateTime dt = Share.COMMON_FORMATTER.parseDateTime("20100409");
       assertFalse(buySignal.isEmpty()); 
       assertEquals(1, buySignal.size());
       assertTrue(buySignal.contains(dt));
      
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_027() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_027_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_027_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_028() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_028_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_028_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_029() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_029_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_029_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_030() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_GROWING + "test_030_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_GROWING + "test_030_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_031() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_031_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_031_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_032() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_032_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_032_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_033() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_033_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_033_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_034() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_034_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_034_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_035() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_035_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_035_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_036() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_036_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_036_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_037() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_037_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_037_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   
   @Test
   public void Test_038() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_038_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_038_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_039() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_039_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_039_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_040() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_040_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_040_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_041() throws IOException, ParseException {
	   
	   List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_041_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_041_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_042() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_042_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_042_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_043() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_043_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_043_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_044() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_044_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_044_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
      
       DateTime dt = Share.COMMON_FORMATTER.parseDateTime("20100419");
       assertFalse(sellSignal.isEmpty()); 
       assertEquals(1, sellSignal.size());
       assertTrue(sellSignal.contains(dt));
   }
   
   @Test
   public void Test_045() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_045_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_045_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));

       List<DateTime> sellSignal = Lists.transform(signals.sellSignals(history), new DateTimeFromSignal());
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }

   //previousClose <= current Close && previousAvr<=CurrentAveage
   @Test
   public void Test_046() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  testBeans.getAverageData(PATH_DROPING + "test_046_average_data.txt");
       StockTickerHistory history =  testBeans.readTickerData(PATH_DROPING + "test_046_ticker.mst");

       MovingAverageSignalsGenerator signals = new MovingAverageSignalsGenerator(period, prepareIndicator(history, averageData, period));
       List<DateTime> buySignal = Lists.transform(signals.buySignals(history), new DateTimeFromSignal());

       DateTime dt = Share.COMMON_FORMATTER.parseDateTime("20100419");
       assertEquals(1, buySignal.size());
       assertTrue(buySignal.contains(dt));
      
       assertFalse(buySignal.isEmpty());
   }



   private SimpleMovingAverageIndicator prepareIndicator(final StockTickerHistory tickerHistory, final List<SimpleMovingAverageData> averageDataList, final int period) {

       SimpleMovingAverageIndicator indicator = mock(SimpleMovingAverageIndicator.class);

       when(indicator.calculateSimpleMovingAverage(period, tickerHistory)).thenReturn(averageDataList);
       
       return indicator;
   }

}



