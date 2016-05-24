package WeatherApp;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.MalformedURLException;

import javax.annotation.Resource;
import javax.naming.ldap.Rdn;
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
	private favs favs = new favs();
	retriveDatafromBOM rb = new retriveDatafromBOM();
	retriveDatafromForecast rf = new retriveDatafromForecast();
	private ChartPanel gp;
	static String res;
	public showGraph(String resource) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//get the users choice about data resource
		if(resource.equals("the BOM weather")){
			showBOMGraph();
		}
		else if(resource.equals("the Forecast weather")){
			showForecastGraph();
		}
		else {
			System.out.println("Please select a data resource.");
		}
		res = resource;
	}
	public void showBOMGraph() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//if the user choose BOM then show BOM weather graph in this method
		//make graph for BOM weather
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
        //two different lines to show different values
        for(int i=0;i<3;i++){
            linedataset.addValue(rb.getTemp()[i], "Air Temperature", rb.getTime()[i]);        	
        }
        for(int i=0;i<3;i++){
        	linedataset.addValue(rb.getAppTemp()[i], "Apparent Temperature", rb.getTime()[i]);
        }
        
        gp = new ChartPanel(chart);
	}
	public void showForecastGraph() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		//if the user choose Forecast then show forecast weather graph in this method
		
		//make graph for Forecast weather
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
        //two different lines to show different values
        linedataset.addValue(rf.gettemperatureMin()[6], "Temperature Min", rf.getTime()[6]);
        linedataset.addValue(rf.gettemperatureMin()[5], "Temperature Min", rf.getTime()[5]);
        linedataset.addValue(rf.gettemperatureMin()[4], "Temperature Min", rf.getTime()[4]);
        linedataset.addValue(rf.gettemperatureMin()[3], "Temperature Min", rf.getTime()[3]);
        linedataset.addValue(rf.gettemperatureMin()[2], "Temperature Min", rf.getTime()[2]);
        linedataset.addValue(rf.gettemperatureMin()[1], "Temperature Min", rf.getTime()[1]);
        linedataset.addValue(rf.gettemperatureMin()[0], "Temperature Min", rf.getTime()[0]);

        linedataset.addValue(rf.gettemperatureMax()[6], "Temperature Max", rf.getTime()[6]);
        linedataset.addValue(rf.gettemperatureMax()[5], "Temperature Max", rf.getTime()[5]);
        linedataset.addValue(rf.gettemperatureMax()[4], "Temperature Max", rf.getTime()[4]);
        linedataset.addValue(rf.gettemperatureMax()[3], "Temperature Max", rf.getTime()[3]);
        linedataset.addValue(rf.gettemperatureMax()[2], "Temperature Max", rf.getTime()[2]);
        linedataset.addValue(rf.gettemperatureMax()[1], "Temperature Max", rf.getTime()[1]);
        linedataset.addValue(rf.gettemperatureMax()[0], "Temperature Max", rf.getTime()[0]);
        gp = new ChartPanel(chart);
	}
	//return some value
	public ChartPanel getGraphPanel(){
		return gp;
	}
	
}
