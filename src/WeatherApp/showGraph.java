package WeatherApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.ObjectInputStream.GetField;

import javax.swing.JFrame;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class showGraph {
	ChartPanel gp;
	public showGraph() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		retriveDatafromBOM rb = new retriveDatafromBOM();
		
		DefaultCategoryDataset linedataset = new DefaultCategoryDataset();
		JFreeChart chart = ChartFactory.createLineChart("The Weather", 
                "Date", // domain axis label
                "Temperature", // range axis label
                linedataset, // data
                PlotOrientation.VERTICAL, // orientation
                true, // include legend
                true, // tooltips
                false // urls
                );
		chart.setBackgroundPaint(Color.white);
		CategoryPlot plot = chart.getCategoryPlot();
		NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
	    rangeAxis.setAutoRangeIncludesZero(true);
	    rangeAxis.setUpperMargin(0.20);
	    rangeAxis.setLabelAngle(Math.PI / 2.0);
	    plot.setForegroundAlpha(1.0f);
	    plot.getRenderer().setSeriesPaint(0, Color.red) ;
	    plot.getRenderer().setSeriesPaint(1, Color.blue) ;
		LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();        
        BasicStroke realLine = new BasicStroke(3.6f);
        renderer.setSeriesStroke(0, realLine); 
        
        linedataset.addValue(rb.getTemp()[2], "Air Temperature", rb.getTime()[2]);
        linedataset.addValue(rb.getTemp()[1], "Air Temperature", rb.getTime()[1]);
        linedataset.addValue(rb.getTemp()[0], "Air Temperature", rb.getTime()[0]);
        //Temperature
        linedataset.addValue(rb.getAppTemp()[2], "Apparent Temperature", rb.getTime()[2]);
        linedataset.addValue(rb.getAppTemp()[1], "Apparent Temperature", rb.getTime()[1]);
        linedataset.addValue(rb.getAppTemp()[0], "Apparent Temperature", rb.getTime()[0]);
        gp = new ChartPanel(chart);
	}
	
	public ChartPanel getGraphPanel(){
		return gp;
	}
	public void showForecastGraph(){
		retriveDatafromForecast rf = new retriveDatafromForecast();
		
	}
}
