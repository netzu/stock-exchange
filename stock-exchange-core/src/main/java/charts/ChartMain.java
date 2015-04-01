package charts;

import configuration.ApplicationContext;
import configuration.StockExchangeProperties;
import data.collector.StockTickerHistory;
import metastockDB.StockDataSelect;

import org.apache.log4j.Logger;
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

import creator.MetastockDBConnection;

import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class ChartMain extends ApplicationFrame {

	private static final int HIGHT = 270;
	private static final int WIDTH = 500;
	private static final double RIGHT = 5.0;
	private static final double BOTTOM = 5.0;
	private static final double LEFT = 5.0;
	private static final double TOP = 5.0;

	private static final long serialVersionUID = 4408158464159618354L;
	
	private static final Logger LOGGER = Logger.getLogger(ChartMain.class);

    public ChartMain(final String windowName, StockTickerHistory collection) {
        super(windowName);

        LinearChart series = new LinearChart();
        XYDataset dataset = series.linearChartDailyData(collection);

        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new java.awt.Dimension(WIDTH, HIGHT));
        chartPanel.setMouseZoomable(true, false);
        setContentPane(chartPanel);
    }

    private static JFreeChart createChart(XYDataset dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                null,
                null,
                null,
                dataset,
                true,
                true,
                false
        );

        chart.setBackgroundPaint(Color.white);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(Color.white);
        plot.setDomainGridlinePaint(Color.gray);
        plot.setRangeGridlinePaint(Color.gray);
        plot.setAxisOffset(new RectangleInsets(TOP, LEFT, BOTTOM, RIGHT));
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
        final Connection connection = new MetastockDBConnection().getConnection(propertiesInstance);

        StockTickerHistory collection = new StockTickerHistory();

        StockDataSelect data;

        try {
            data = new StockDataSelect(connection);
            collection = data.getAllDataForStockTicker("LENA");
        } catch (ClassNotFoundException e) {
            LOGGER.error(e);
        } catch (SQLException e) {
            LOGGER.error(e);
        } catch (ParseException e) {
            LOGGER.error(e);
        } finally {
            connection.close();
        }

        ChartMain demo = new ChartMain("Time Series Demo 1", collection);
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }
}
