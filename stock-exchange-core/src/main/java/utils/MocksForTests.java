package utils;

import indicators.simpleMovingAverage.SimpleMovingAverageData;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import buySignalTest.BuySingalStatistics;
import buySignalTest.ProfitsFromSignal;
import data.DataFileReader;
import data.collector.StockTickerHistory;

public class MocksForTests {
	
	DateTimeFormatter dateFormater = DateTimeFormat.forPattern("yyyyMMdd");
	
    public List<SimpleMovingAverageData> getAverageData(final String path) throws IOException {

        
        final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        final List<SimpleMovingAverageData> result = new ArrayList<SimpleMovingAverageData>();

        String line = null;

        while ((line = reader.readLine()) != null) {

            String[] splitData = line.split(",");

            if (splitData.length != 2) {
                throw new IllegalStateException("Wrong data format, expected date<yyyyMMdd>,price<double>");
            }

            SimpleMovingAverageData averageData = new SimpleMovingAverageData();
            averageData.setAverage(Double.parseDouble(splitData[1]));
            final DateTime date = dateFormater.parseDateTime(splitData[0]);
            averageData.setDate(date);

            result.add(averageData);
        }

        return result;

    }
    
    public List<DateTime> getBuysignal(final String path) throws IOException{
    	
    	List<DateTime> buysignal = new ArrayList<DateTime>();
    	
    	final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
    	final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
    	
    	String line = null;
    	
    	while ((line = reader.readLine()) != null) {
    		final DateTime date = dateFormater.parseDateTime(line);
    		buysignal.add(date);
    	}
    	
    	return buysignal;
    }

    public List<ProfitsFromSignal> getProfitsFromSignal(final String path) throws IOException{
    	List<ProfitsFromSignal> profits = new ArrayList<ProfitsFromSignal>();
		
        final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        final List<SimpleMovingAverageData> result = new ArrayList<SimpleMovingAverageData>();

        String line = null;
        
        while ((line = reader.readLine()) != null) {
        	ProfitsFromSignal profistForOneDay = new ProfitsFromSignal();

            String[] splitData = line.split(",");

            if (splitData.length != 2) {
                throw new IllegalStateException("Wrong data format, expected date<yyyyMMdd>,price<double>");
            }
            
            profistForOneDay.setProfit(Double.parseDouble(splitData[0]));
            profistForOneDay.setProfitInPercentage(Double.parseDouble(splitData[1]));
            
            profits.add(profistForOneDay);
        }
    	
    	return profits;    	
    }
    
    public BuySingalStatistics getBuySignalStatistics(final String path) throws IOException{
    	BuySingalStatistics stats = new  BuySingalStatistics();
    	
    	List<ProfitsFromSignal> profits = new ArrayList<ProfitsFromSignal>();
		
        final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);

        final BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        final List<SimpleMovingAverageData> result = new ArrayList<SimpleMovingAverageData>();

        String line = null;
        
        while ((line = reader.readLine()) != null) {
        	String[] splitData = line.split(",");
        	
        	stats.setSumOfProfits(Double.parseDouble(splitData[0]));
        	stats.setSumOfPercentage(Double.parseDouble(splitData[1]));
        	stats.setAverage(Double.parseDouble(splitData[2]));        	
        	stats.setStandardDeviation(Double.parseDouble(splitData[3]));
        	stats.setMedian(Double.parseDouble(splitData[4]));
        	stats.setVariance(Double.parseDouble(splitData[5]));
        	stats.setMin(Double.parseDouble(splitData[6]));
        	stats.setMax(Double.parseDouble(splitData[7]));
        	stats.setSumNegativeProfits(Double.parseDouble(splitData[8]));
        	stats.setSumPositiveProfits(Double.parseDouble(splitData[9]));
        	stats.setCountNegativeProfits(Double.parseDouble(splitData[10]));
        	stats.setCountPositiveProfits(Double.parseDouble(splitData[11]));
        }
    	
		return stats;    	
    }
    
    public StockTickerHistory readTickerData(final String path) throws ParseException {


        URL resource = this.getClass().getClassLoader().getResource(path);

        final String filePath = resource.getPath();

        DataFileReader fileReader = new DataFileReader();

        return fileReader.getStockTickerCollection(new File(filePath));
    }
}