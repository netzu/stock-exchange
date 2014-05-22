package indicators.moving_average.simple;

import static org.junit.Assert.*;
import data.collector.StockTickerHistory;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import utils.MocksForTests;
import indicators.moving_average.simple.SimpleMovingAverageData;
import indicators.moving_average.simple.SimpleMovingAverageSignals;

import java.io.*;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Test class for SimpleMovingAverageSignals functionality.
 */
public class SimpleMovingAverageSignalsTest {


	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	
	final static String PATH_FLAT = new String("indicators/simpleMovingAverage/flatAverage/");
	final static String PATH_GROWING = new String("indicators/simpleMovingAverage/growingAverage/");
	final static String PATH_DROPING = new String("indicators/simpleMovingAverage/dropingAverage/");
	
	MocksForTests mock = new MocksForTests();
	
	
   @Test
   public void Test_001() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData = mock.getAverageData(PATH_FLAT + "test_001_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_001_ticker.mst");
       
        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);

        assertTrue(buySignal.isEmpty());        
        assertTrue(sellSignal.isEmpty());        
    }
   
   @Test
   public void Test_002() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_002_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_002_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());        
    }
   
   @Test
   public void Test_003() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_003_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_003_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        //DateTime date = dateFormater.parseDateTime("20100128");
        //assertTrue(buySignal.contains(date));
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_004() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_004_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_004_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_005() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_005_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_005_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
        //DateTime date = new DateTime("20100128");
        //assertTrue(sellSignal.contains(date));
    }

   @Test
   public void Test_006() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_006_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_006_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_007() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_007_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_007_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }

   
   @Test
   public void Test_008() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_008_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_008_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_009() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_009_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_009_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_010() throws IOException, ParseException {
	   
        List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_010_average_data.txt");
        StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_010_ticker.mst");

        SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
        
        ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
        List<DateTime> buySignal = new ArrayList<DateTime>();
        
        sellSignal = signals.simpleSell(averageData, history);
        buySignal = signals.buySignal(averageData, history);
        
        assertTrue(buySignal.isEmpty());
        //DateTime date = new DateTime("20100330");
        //assertTrue(buySignal.contains(date));
        assertTrue(sellSignal.isEmpty());
    }
   
   @Test
   public void Test_011() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_011_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_011_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       DateTime dt = dateFormater.parseDateTime("20100409");
       
       assertFalse(buySignal.isEmpty());       
       assertEquals(1, buySignal.size());
       assertTrue(buySignal.contains(dt));
       
       assertTrue(sellSignal.isEmpty());
   }

   @Test
   public void Test_012() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_012_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_012_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       //DateTime dt = dateFormater.parseDateTime("20100419");
       
       //assertFalse(buySignal.isEmpty());       
       //assertEquals(1, buySignal.size());
       //assertTrue(buySignal.contains(dt));
       
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_013() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_013_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_013_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
       
//       DateTime dt = dateFormater.parseDateTime("20100419");
//       
//       assertFalse(sellSignal.isEmpty());       
//       assertEquals(1, sellSignal.size());
//       assertTrue(sellSignal.contains(dt));
   }
   
   
   @Test
   public void Test_014() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_014_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_014_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
      
       DateTime dt = dateFormater.parseDateTime("20100419");       
       assertFalse(sellSignal.isEmpty());       
       assertEquals(1, sellSignal.size());
       assertTrue(sellSignal.contains(dt));
   }
   
   @Test
   public void Test_015() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_FLAT + "test_015_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_FLAT + "test_015_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
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
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_016_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_016_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());

   }
   
   @Test
   public void Test_017() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_017_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_017_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_018() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_018_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_018_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_019() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_019_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_019_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_020() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_020_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_020_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_021() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_021_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_021_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_022() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_022_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_022_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_023() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_023_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_023_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_024() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_024_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_024_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_025() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_025_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_025_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_026() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_026_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_026_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);

       DateTime dt = dateFormater.parseDateTime("20100409");
       assertFalse(buySignal.isEmpty()); 
       assertEquals(1, buySignal.size());
       assertTrue(buySignal.contains(dt));
      
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_027() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_027_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_027_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_028() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_028_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_028_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_029() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_029_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_029_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_030() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_GROWING + "test_030_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_GROWING + "test_030_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_031() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_031_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_031_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_032() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_032_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_032_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_033() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_033_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_033_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_034() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_034_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_034_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_035() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_035_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_035_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   } 
   
   @Test
   public void Test_036() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_036_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_036_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_037() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_037_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_037_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   
   @Test
   public void Test_038() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_038_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_038_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_039() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_039_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_039_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_040() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_040_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_040_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_041() throws IOException, ParseException {
	   
	   List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_041_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_041_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_042() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_042_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_042_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_043() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_043_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_043_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }
   
   @Test
   public void Test_044() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_044_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_044_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
      
       DateTime dt = dateFormater.parseDateTime("20100419");
       assertFalse(sellSignal.isEmpty()); 
       assertEquals(1, sellSignal.size());
       assertTrue(sellSignal.contains(dt));
   }
   
   @Test
   public void Test_045() throws IOException, ParseException {
	   
       List<SimpleMovingAverageData> averageData =  mock.getAverageData(PATH_DROPING + "test_045_average_data.txt");
       StockTickerHistory history =  mock.readTickerData(PATH_DROPING + "test_045_ticker.mst");

       SimpleMovingAverageSignals signals = new SimpleMovingAverageSignals();
       
       ArrayList<DateTime> sellSignal = new ArrayList<DateTime>();
       List<DateTime> buySignal = new ArrayList<DateTime>();
       
       sellSignal = signals.simpleSell(averageData, history);
       buySignal = signals.buySignal(averageData, history);
       
       assertTrue(buySignal.isEmpty());
       assertTrue(sellSignal.isEmpty());
   }

}



