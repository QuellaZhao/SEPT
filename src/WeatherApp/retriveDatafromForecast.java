package WeatherApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.log4j.*;
import org.apache.log4j.BasicConfigurator;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class retriveDatafromForecast {
	private JComboBox<String> stateCB = new JComboBox<String>();
	@SuppressWarnings("unused")
	private JComboBox<String> cityCB = new JComboBox<String>();
	@SuppressWarnings("unused")
	private JPanel lp = new JPanel();
	@SuppressWarnings("unused")
	private String lat,longt,timezone,summary,icon;
	private JsonObject daily;
	private JsonArray weatherData;
	private static final Logger logger = LoggerFactory.getLogger(retriveDatafromForecast.class);
	
	private static String[] time = new String[10];
	private	static String[] summarys = new String[10];
	private	static String[] icons = new String[10];
	private	static String[] sunriseTime = new String[10];
	private static String[] sunsetTime = new String[10];
	private	static String[] moonPhase = new String[10];
	private	static String[] precipProbability = new String[10];
	private	static String[] precipType = new String[10];
	private	static double[] temperatureMin = new double[10];
	private	static double[] temperatureMax = new double[10];
	private	static String[] apparentTemperatureMin = new String[10];
	private static String[] apparentTemperatureMax = new String[10];
	private	static String[] dewPoint = new String[10];
	private static String[] humidity = new String[10];
	private	static String[] windSpeed = new String[10];
	private	static String[] windBearing = new String[10];
	private	static String[] cloudCover = new String[10];
	private	static String[] pressure = new String[10];
	private	static String[] ozone = new String[10];
	
	public retriveDatafromForecast() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		String state;

		HashMap<String, JsonArray> hm = new HashMap<String, JsonArray>();
		new ArrayList<String>();
		JsonArray ar = new JsonArray();
		
		//get stations names from json file and make then into a combobox
		//also use hashmap to store states and stations correspondingly 
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader("stations.json"));
		JsonArray array = object.get("Array").getAsJsonArray();
		for(int i = 0; i<array.size(); i++){
			JsonObject obj = array.get(i).getAsJsonObject();
			state = obj.get("state").getAsString();	
			ar = obj.get("stations").getAsJsonArray();
			hm.put(state, ar);
			stateCB.addItem(state);
	}
	}
	
	public void getWeatherData() throws IOException{
		new mainframe();
		//because we can't find stations but just coordinates in Forecast website, we just use data from Melbourne as an example
		//get json file from URL by Melbourne's coordinate
		
		BasicConfigurator.configure();
		URL url = new URL("https://api.forecast.io/forecast/aff180e4fd288fc7d48f14d8f74f9daa/-37.8578,144.5191");
		URLConnection uc = url.openConnection();
		InputStream input = uc.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
		@SuppressWarnings("resource")
		String jsonString = new Scanner(reader).useDelimiter("\\Z").next();
		//get data we need from the json file
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(jsonString);
		lat = object.get("latitude").getAsString();
		longt = object.get("longitude").getAsString();
		timezone = object.get("timezone").getAsString();
		daily = object.get("daily").getAsJsonObject();
		summary = daily.get("summary").getAsString();
		icon = daily.get("icon").getAsString();
		weatherData = daily.get("data").getAsJsonArray();
		for(int i=0;i<weatherData.size();i++){
			JsonObject obj = weatherData.get(i).getAsJsonObject();
			time[i] = obj.get("time").toString();
	        summarys[i] = obj.get("summary").toString();
	        icons[i] = obj.get("icon").toString();
	        sunriseTime[i] = obj.get("sunriseTime").toString();
	        sunsetTime[i] = obj.get("sunsetTime").toString();
	        moonPhase[i] = obj.get("moonPhase").toString();
	        precipProbability[i] = obj.get("precipProbability").toString();
//	        precipType[i] = obj.get("precipType").toString();
	        temperatureMin[i] = obj.get("temperatureMin").getAsDouble();
	        temperatureMax[i] = obj.get("temperatureMax").getAsDouble();
	        apparentTemperatureMin[i] = obj.get("apparentTemperatureMin").toString();
	        apparentTemperatureMax[i] = obj.get("apparentTemperatureMax").toString();
	        dewPoint[i] = obj.get("dewPoint").toString();
	        humidity[i] = obj.get("humidity").toString();
	        windSpeed[i] = obj.get("windSpeed").toString();
	        windBearing[i] = obj.get("windBearing").toString();
	        cloudCover[i] = obj.get("cloudCover").toString();
	        pressure[i] = obj.get("pressure").toString();
	        ozone[i] = obj.get("ozone").toString();		
	        logger.info("The weather data has been acquired from the json files on forecast.io");
		}
		
	}
	//return some value
	public String[] getTime(){
		return time;
	}
	public String[] getSummary(){
		return summarys;
	}
	public String[] getIcon(){
		return icons;
	}
	public String[] getsunriseTime(){
		return sunriseTime;
	}
	public String[] getsunsetTime(){
		return sunsetTime;
	}
	public String[] getMoonPhase(){
		return moonPhase;
	}
	public String[] getprecipProbability(){
		return precipProbability;
	}
	public String[] getprecipType(){
		return precipType;
	}
	public double[] gettemperatureMin(){
		return temperatureMin;
	}
	public double[] gettemperatureMax(){
		return temperatureMax;
	}
	public String[] getapparentTemperatureMin(){
		return apparentTemperatureMin;
	}
	public String[] getapparentTemperatureMax(){
		return apparentTemperatureMax;
	}
	public String[] getdewPoint(){
		return dewPoint;
	}
	public String[] gethumidity(){
		return humidity;
	}
	public String[] getwindSpeed(){
		return windSpeed;
	}
	public String[] getwindBearing(){
		return windBearing;
	}
	public String[] getcloudCover(){
		return cloudCover;
	}
	public String[] getpressure(){
		return pressure;
	}
	public String[] getozone(){
		return ozone;
		
	}
	
}
