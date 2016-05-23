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

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class retriveDatafromForecast {
	private JComboBox<String> stateCB = new JComboBox<String>();
	private JComboBox<String> cityCB = new JComboBox<String>();
	private JPanel lp = new JPanel();
	private String lat,longt,timezone,summary,icon;
	private JsonObject daily;
	private JsonArray weatherData;
	
	private	String[] time = new String[10];
	private	String[] summarys = new String[10];
	private	String[] icons = new String[10];
	private	String[] sunriseTime = new String[10];
	private String[] sunsetTime = new String[10];
	private	String[] moonPhase = new String[10];
	private	String[] precipProbability = new String[10];
	private	String[] precipType = new String[10];
	private	double[] temperatureMin = new double[10];
	private	double[] temperatureMax = new double[10];
	private	String[] apparentTemperatureMin = new String[10];
	private String[] apparentTemperatureMax = new String[10];
	private	String[] dewPoint = new String[10];
	private String[] humidity = new String[10];
	private	String[] windSpeed = new String[10];
	private	String[] windBearing = new String[10];
	private	String[] cloudCover = new String[10];
	private	String[] pressure = new String[10];
	private	String[] ozone = new String[10];

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
		//because we can't find stations in Forecast website, we just use data from Melbourne as an example
		//get json file from URL by Melbourne's coordinate
		URL url = new URL("https://api.forecast.io/forecast/aff180e4fd288fc7d48f14d8f74f9daa/-37.8578,144.5191");
		URLConnection uc = url.openConnection();
		InputStream input = uc.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
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
			time[i] = obj.get("time").getAsString();
	        summarys[i] = obj.get("summary").getAsString();
	        icons[i] = obj.get("icon").getAsString();
	        sunriseTime[i] = obj.get("sunriseTime").getAsString();
	        sunsetTime[i] = obj.get("sunsetTime").getAsString();
	        moonPhase[i] = obj.get("moonPhase").getAsString();
	        precipProbability[i] = obj.get("precipProbability").getAsString();
	        precipType[i] = obj.get("precipType").getAsString();
	        temperatureMin[i] = obj.get("temperatureMin").getAsDouble();
	        temperatureMax[i] = obj.get("temperatureMax").getAsDouble();
	        apparentTemperatureMin[i] = obj.get("apparentTemperatureMin").getAsString();
	        apparentTemperatureMax[i] = obj.get("apparentTemperatureMax").getAsString();
	        dewPoint[i] = obj.get("dewPoint").getAsString();
	        humidity[i] = obj.get("humidity").getAsString();
	        windSpeed[i] = obj.get("windSpeed").getAsString();
	        windBearing[i] = obj.get("windBearing").getAsString();
	        cloudCover[i] = obj.get("cloudCover").getAsString();
	        pressure[i] = obj.get("pressure").getAsString();
	        ozone[i] = obj.get("ozone").getAsString();		
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
