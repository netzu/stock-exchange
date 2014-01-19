package charts;

import java.awt.Color;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import DAO.StockDataSelect;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.annotations.XYLineAnnotation;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RectangleInsets;
import org.jfree.ui.RefineryUtilities;

import DAO.DBConnection;
import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerCollection;

public class ChartMain extends ApplicationFrame{

	public ChartMain(final String windowName, StockTickerCollection collection) {
		super(windowName);
		
		LinearChart series = new LinearChart();		
		XYDataset dataset = series.linearChartDailyData(collection);
		
		JFreeChart chart = createChart(dataset);
		ChartPanel chartPanel = new ChartPanel(chart);
		chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));
		chartPanel.setMouseZoomable(true, false);
		setContentPane(chartPanel);
	}
	
	private static JFreeChart createChart(XYDataset dataset) {
		JFreeChart chart = ChartFactory.createTimeSeriesChart(
		null, // title
		null, // x-axis label
		null, // y-axis label
		dataset, // data
		true, // create legend?
		true, // generate tooltips?
		false // generate URLs?
		);
		
		chart.setBackgroundPaint(Color.white);
		
		XYPlot plot = (XYPlot) chart.getPlot();
		plot.setBackgroundPaint(Color.white);
		plot.setDomainGridlinePaint(Color.gray);
		plot.setRangeGridlinePaint(Color.gray);
		plot.setAxisOffset(new RectangleInsets(5.0, 5.0, 5.0, 5.0));
		plot.setDomainCrosshairVisible(true);
		plot.setRangeCrosshairVisible(true);
		
		XYItemRenderer r = plot.getRenderer();
		
		if (r instanceof XYLineAnnotation) {
			XYLineAnnotation renderer = (XYLineAnnotation) r;
			renderer.setToolTipText("Tip");
		}
		DateAxis axis = (DateAxis) plot.getDomainAxis();
		axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM-yyyy"));
		return chart;
	}

	public static void main(String[] args) throws SQLException {
		
		StockExchangeProperties propertiesInstance = ApplicationContext.getPropertiesInstance();		
		final Connection connection = new DBConnection().getConnection(propertiesInstance);
		
		StockTickerCollection collection = new StockTickerCollection();
		
		StockDataSelect data;
		
			try {
				data = new StockDataSelect(connection);
				collection = data.getAllDataForStockTicker("LENA");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ParseException e) {
				e.printStackTrace();
			} finally {
				connection.close();	
			}
		
		ChartMain demo = new ChartMain("Time Series Demo 1", collection);
		demo.pack();		
		RefineryUtilities.centerFrameOnScreen(demo);
		demo.setVisible(true);
		}
}
