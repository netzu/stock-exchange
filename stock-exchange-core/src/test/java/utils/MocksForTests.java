package utils;

import indicators.movingaverage.complex.ComplexMovingAverageAverageData;
import indicators.movingaverage.simple.SimpleMovingAverageData;
import indicators.williamsr.WilliamsRData;

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

import data.DataFileReader;
import data.collector.StockTickerHistory;

public class MocksForTests {

//	private static final int COUNT_POSITIVE_INDEX = 11;
//	private static final int COUNT_NEGATIVE_INDEX = 10;
//	private static final int POSITIVE_PROFITS_INDEX = 9;
//	private static final int NEGATIVE_PROFITS_INDEX = 8;
//	private static final int MAX_INDEX = 7;
//	private static final int MIN_INDEX = 6;
//	private static final int VARIANCE_INDEX = 5;
//	private static final int MEDIAN_INDEX = 4;
//	private static final int STAND_DEV_INDEX = 3;
//	private static final int AVERAGE_INDEX = 2;
//	private static final int PERCENTAGE_SUM_INDEX = 1;
//	private static final int PROFITS_SUM_INDEX = 0;
	private DateTimeFormatter dateFormater = DateTimeFormat
			.forPattern("yyyyMMdd");

	public List<SimpleMovingAverageData> getAverageData(final String path) throws IOException {

		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		final List<SimpleMovingAverageData> result = new ArrayList<SimpleMovingAverageData>();

		String line = null;

		while ((line = reader.readLine()) != null) {

			String[] splitData = line.split(",");

			if (splitData.length != 2) {
				throw new IllegalStateException(
						"Wrong data format, expected date<yyyyMMdd>,price<double>");
			}

			SimpleMovingAverageData averageData = new SimpleMovingAverageData();
			averageData.setAverage(Double.parseDouble(splitData[1]));
			final DateTime date = dateFormater.parseDateTime(splitData[0]);
			averageData.setDate(date);

			result.add(averageData);
		}

		reader.close();
		return result;

	}
	
	public List<WilliamsRData> getWillimasCollection(final String path) throws NumberFormatException, IOException{

		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
		final List<WilliamsRData> result = new ArrayList<WilliamsRData>();

		String line = null;

		while ((line = reader.readLine()) != null) {

			String[] splitData = line.split(",");

			if (splitData.length != 2) {
				throw new IllegalStateException(
						"Wrong data format, expected date<yyyyMMdd>,price<double>");
			}

			WilliamsRData williamsRData = new WilliamsRData();
			williamsRData.setWilliamsR(Double.parseDouble(splitData[1]));
			final DateTime date = dateFormater.parseDateTime(splitData[0]);
			williamsRData.setDate(date);

			result.add(williamsRData);
		}

		reader.close();
		return result;
	}

	public List<DateTime> getBuysignal(final String path) throws IOException {

		List<DateTime> buySignal = new ArrayList<DateTime>();

		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		String line = null;

		while ((line = reader.readLine()) != null) {
			final DateTime date = dateFormater.parseDateTime(line);
			buySignal.add(date);
		}

		reader.close();

		return buySignal;
	}

	public List<Boolean> getCorrectSignals(final String path) throws IOException {

		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		String line = null;

		List<Boolean> correctSignals = new ArrayList<Boolean>();

		while ((line = reader.readLine()) != null) {

			if (line.trim().equals("t")) {
				correctSignals.add(true);
			} else if (line.trim().equals("f")) {
				correctSignals.add(false);
			} else {
				throw new IllegalStateException(
						"Value in input file is nither t or f");
			}
		}

		reader.close();
		return correctSignals;
	}

	public List<Double> getListOfDoubles(final String path) throws IOException {

		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
		final BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));

		String line = null;

		List<Double> correctSignals = new ArrayList<Double>();

		while ((line = reader.readLine()) != null) {
			correctSignals.add(Double.parseDouble(line));
		}

		reader.close();
		return correctSignals;
	}

//	public List<ProfitsFromSignal> getProfitsFromSignal(final String path) throws IOException {
//		
//		List<ProfitsFromSignal> profits = new ArrayList<ProfitsFromSignal>();
//
//		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
//		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//
//		String line = null;
//
//		while ((line = reader.readLine()) != null) {
//			ProfitsFromSignal profistForOneDay = new ProfitsFromSignal();
//
//			String[] splitData = line.split(",");
//
//			if (splitData.length != 2) {
//				throw new IllegalStateException(
//						"Wrong data format, expected date<yyyyMMdd>,price<double>");
//			}
//
//			profistForOneDay.setProfit(Double.parseDouble(splitData[0]));
//			profistForOneDay.setProfitInPercentage(Double
//					.parseDouble(splitData[1]));
//
//			profits.add(profistForOneDay);
//		}
//
//		reader.close();
//
//		return profits;
//	}

//	public BuySingalStatistics getBuySignalStatistics(final String path) throws IOException {
//		BuySingalStatistics stats = new BuySingalStatistics();
//
//		final InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
//		final BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//
//		String line = null;
//
//		while ((line = reader.readLine()) != null) {
//			String[] splitData = line.split(",");
//
//			stats.setSumOfProfits(Double.parseDouble(splitData[PROFITS_SUM_INDEX]));
//			stats.setSumOfPercentage(Double.parseDouble(splitData[PERCENTAGE_SUM_INDEX]));
//			stats.setAverage(Double.parseDouble(splitData[AVERAGE_INDEX]));
//			stats.setStandardDeviation(Double.parseDouble(splitData[STAND_DEV_INDEX]));
//			stats.setMedian(Double.parseDouble(splitData[MEDIAN_INDEX]));
//			stats.setVariance(Double.parseDouble(splitData[VARIANCE_INDEX]));
//			stats.setMin(Double.parseDouble(splitData[MIN_INDEX]));
//			stats.setMax(Double.parseDouble(splitData[MAX_INDEX]));
//			stats.setSumNegativeProfits(Double.parseDouble(splitData[NEGATIVE_PROFITS_INDEX]));
//			stats.setSumPositiveProfits(Double.parseDouble(splitData[POSITIVE_PROFITS_INDEX]));
//			stats.setCountNegativeProfits(Double.parseDouble(splitData[COUNT_NEGATIVE_INDEX]));
//			stats.setCountPositiveProfits(Double.parseDouble(splitData[COUNT_POSITIVE_INDEX]));
//		}
//
//		reader.close();
//		return stats;
//	}

	public StockTickerHistory readTickerData(final String path) throws ParseException {

		URL resource = this.getClass().getClassLoader().getResource(path);

		final String filePath = resource.getPath();

		DataFileReader fileReader = new DataFileReader();

		return fileReader.getStockTickerCollection(new File(filePath));
	}
}