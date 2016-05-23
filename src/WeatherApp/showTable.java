package WeatherApp;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class showTable {
	private JPanel wjp = new JPanel();
	private favs favs = new favs();
	public showTable(String resource, String station) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//get the users choice about data resource
		mainframe mf = new mainframe();
		if(resource.equals("the BOM weather")){
			showBOMTable(resource);
		}
		else if(resource.equals("the Forecast weather")){
			showForecastTable(resource);
		}
		else {
			System.out.println("Please select a data resource.");
		}
	}
	
	public void showBOMTable(String resource) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//if the user choose BOM then show BOM weather in this method
		retriveDatafromBOM rd = new retriveDatafromBOM();
		//put the data into the table
		String[] coName = {"Date/Time WST","Temp","App Temp","Dew Point","Rel Hum","Delta-T","Wind Direction","Press MSL hPa","Rain since 9am"};
		Object[][] datas = {
				{rd.getTime()[2],rd.getTemp()[2],rd.getAppTemp()[2],rd.getDew()[2],rd.getRel()[2],rd.getDeltat()[2],
					rd.getWinddir()[2],rd.getPress()[2],rd.getRain()[2]},
				{rd.getTime()[1],rd.getTemp()[1],rd.getAppTemp()[1],rd.getDew()[1],rd.getRel()[1],rd.getDeltat()[1],
					rd.getWinddir()[1],rd.getPress()[1],rd.getRain()[1]},
				{rd.getTime()[0],rd.getTemp()[0],rd.getAppTemp()[0],rd.getDew()[0],rd.getRel()[0] ,rd.getDeltat()[0],
							rd.getWinddir()[0],rd.getPress()[0],rd.getRain()[0]}
		};
	
		//show the table
		JTable wTable = new JTable(datas,coName);
		wjp.setLayout(new BorderLayout());
		wjp.add(wTable.getTableHeader(),BorderLayout.PAGE_START);
		wjp.add(wTable,BorderLayout.CENTER);
		JPanel bp = new JPanel();
		JButton graphb = new JButton("Graph");
		graphb.setPreferredSize(new Dimension(100,25));
		JButton refresh = new JButton("Refresh");
		refresh.setPreferredSize(new Dimension(100,25));
		bp.add(graphb);
		bp.add(refresh);
		graphb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainframe mf = new mainframe();
					try {
						mf.graphFrame(resource);
					} catch (JsonIOException | JsonSyntaxException | IOException e1) {
						e1.printStackTrace();
					}
			}});
		wjp.add(bp,BorderLayout.PAGE_END);
	}
	
	public void showForecastTable(String resource) throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		//if the user choose Forecast then show forecast weather in this method
		retriveDatafromForecast rdf = new retriveDatafromForecast();
		//put the data into the table
		String[] coName = {"time", "summary", "icon", "sunriseTime", "sunsetTime", "moonPhase", "precipProbability", "precipType", "temperatureMin", "temperatureMax", "apparentTemperatureMin", "apparentTemperatureMax", "dewPoint", "humidity", "windSpeed", "windBearing", "cloudCover", "pressure", "ozone"};
		Object[][] datas = {
				{rdf.getTime()[0], rdf.getSummary()[0], rdf.getIcon()[0], rdf.getsunriseTime()[0], rdf.getsunsetTime()[0], rdf.getMoonPhase()[0], rdf.getprecipProbability()[0], 
				rdf.getprecipType()[0], rdf.gettemperatureMin()[0], rdf.gettemperatureMax()[0], rdf.getapparentTemperatureMin()[0], rdf.gettemperatureMax()[0], 
				rdf.getdewPoint()[0], rdf.gethumidity()[0], rdf.getwindSpeed()[0], rdf.getwindBearing()[0], rdf.getcloudCover()[0], rdf.getpressure()[0], rdf.getozone()[0]},
				
				{rdf.getTime()[1], rdf.getSummary()[1], rdf.getIcon()[1], rdf.getsunriseTime()[1], rdf.getsunsetTime()[1], rdf.getMoonPhase()[1], rdf.getprecipProbability()[1], 
				rdf.getprecipType()[1], rdf.gettemperatureMin()[1], rdf.gettemperatureMax()[1], rdf.getapparentTemperatureMin()[1], rdf.gettemperatureMax()[1], 
				rdf.getdewPoint()[1], rdf.gethumidity()[1], rdf.getwindSpeed()[1], rdf.getwindBearing()[1], rdf.getcloudCover()[1], rdf.getpressure()[1], rdf.getozone()[1]},
				
				{rdf.getTime()[2], rdf.getSummary()[2], rdf.getIcon()[2], rdf.getsunriseTime()[2], rdf.getsunsetTime()[2], rdf.getMoonPhase()[2], rdf.getprecipProbability()[2], 
				rdf.getprecipType()[2], rdf.gettemperatureMin()[2], rdf.gettemperatureMax()[2], rdf.getapparentTemperatureMin()[2], rdf.gettemperatureMax()[2], 
				rdf.getdewPoint()[2], rdf.gethumidity()[2], rdf.getwindSpeed()[2], rdf.getwindBearing()[2], rdf.getcloudCover()[2], rdf.getpressure()[2], rdf.getozone()[2]},
	
				{rdf.getTime()[3], rdf.getSummary()[3], rdf.getIcon()[3], rdf.getsunriseTime()[3], rdf.getsunsetTime()[3], rdf.getMoonPhase()[3], rdf.getprecipProbability()[3], 
				rdf.getprecipType()[3], rdf.gettemperatureMin()[3], rdf.gettemperatureMax()[3], rdf.getapparentTemperatureMin()[3], rdf.gettemperatureMax()[3], 
				rdf.getdewPoint()[3], rdf.gethumidity()[3], rdf.getwindSpeed()[3], rdf.getwindBearing()[3], rdf.getcloudCover()[3], rdf.getpressure()[3], rdf.getozone()[3]},
			
				{rdf.getTime()[4], rdf.getSummary()[4], rdf.getIcon()[4], rdf.getsunriseTime()[4], rdf.getsunsetTime()[4], rdf.getMoonPhase()[4], rdf.getprecipProbability()[4], 
				rdf.getprecipType()[4], rdf.gettemperatureMin()[4], rdf.gettemperatureMax()[4], rdf.getapparentTemperatureMin()[4], rdf.gettemperatureMax()[4], 
				rdf.getdewPoint()[4], rdf.gethumidity()[4], rdf.getwindSpeed()[4], rdf.getwindBearing()[4], rdf.getcloudCover()[4], rdf.getpressure()[4], rdf.getozone()[4]},
				
				{rdf.getTime()[5], rdf.getSummary()[5], rdf.getIcon()[5], rdf.getsunriseTime()[5], rdf.getsunsetTime()[5], rdf.getMoonPhase()[5], rdf.getprecipProbability()[5], 
				rdf.getprecipType()[5], rdf.gettemperatureMin()[5], rdf.gettemperatureMax()[5], rdf.getapparentTemperatureMin()[5], rdf.gettemperatureMax()[5], 
				rdf.getdewPoint()[5], rdf.gethumidity()[5], rdf.getwindSpeed()[5], rdf.getwindBearing()[5], rdf.getcloudCover()[5], rdf.getpressure()[5], rdf.getozone()[5]},
				
				{rdf.getTime()[6], rdf.getSummary()[6], rdf.getIcon()[6], rdf.getsunriseTime()[6], rdf.getsunsetTime()[6], rdf.getMoonPhase()[6], rdf.getprecipProbability()[6], 
				rdf.getprecipType()[6], rdf.gettemperatureMin()[6], rdf.gettemperatureMax()[6], rdf.getapparentTemperatureMin()[6], rdf.gettemperatureMax()[6], 
				rdf.getdewPoint()[6], rdf.gethumidity()[6], rdf.getwindSpeed()[6], rdf.getwindBearing()[6], rdf.getcloudCover()[6], rdf.getpressure()[6], rdf.getozone()[6]},
		}  ;
	
		//show the table
		JTable wTable = new JTable(datas,coName);
		wjp.setLayout(new BorderLayout());
		wjp.add(wTable.getTableHeader(),BorderLayout.PAGE_START);
		wjp.add(wTable,BorderLayout.CENTER);
		JPanel bp = new JPanel();
		JButton graphb = new JButton("Graph");
		graphb.setPreferredSize(new Dimension(100,25));
		JButton refresh = new JButton("Refresh");
		refresh.setPreferredSize(new Dimension(100,25));
		bp.add(graphb);
		bp.add(refresh);
		graphb.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				mainframe mf = new mainframe();
					try {
						mf.graphFrame(resource);
					} catch (JsonIOException | JsonSyntaxException | IOException e1) {
						e1.printStackTrace();
					}
			}});
		wjp.add(bp,BorderLayout.PAGE_END);
	}
	//return some value
	public JPanel getTablePanel(){
		return wjp;
	}
	
}
