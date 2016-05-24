package WeatherApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
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

public class retriveDatafromBOM {
	private static String[] dateTime = new String[5];
	private static double[] temp = new double[5];
	private static double[] apptemp = new double[5];
	private static String[] dew = new String[5];
	private static String[] rel = new String[5];
	private static String[] deltat = new String[5];
	private static String[] winddir = new String[5];
	private static String[] press = new String[5];
	private static String[] rain = new String[5];
	
	private JComboBox<String> stateCB = new JComboBox<String>();
	private JComboBox<String> cityCB = new JComboBox<String>();
	private JPanel lp = new JPanel();

	private HashMap<String, JsonArray> hm = new HashMap<String, JsonArray>();
	private static HashMap<String, String> URLhm = new HashMap<String, String>();

	public retriveDatafromBOM() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		String state;

		JsonArray ar = new JsonArray();
		
		//get station names from json file and put then in a combobox
		//also use hashmap hm to store states and stations correspondingly 
		//and another hashmap URLhm will store stations and it's URL
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader("stations.json"));
		JsonArray array = object.get("Array").getAsJsonArray();
		for(int i = 0; i<array.size(); i++){
			JsonObject obj = array.get(i).getAsJsonObject();
			state = obj.get("state").getAsString();	
			ar = obj.get("stations").getAsJsonArray();
			hm.put(state, ar);
			stateCB.addItem(state);
			for(int o = 0;o<ar.size();o++){
				JsonObject subobj = ar.get(o).getAsJsonObject();
				URLhm.put(subobj.get("city").getAsString(), subobj.get("url").getAsString());
			}		
		}
		//when chose a state, the other combobox will display its stations
		stateCB.addActionListener((new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				stateCB.getSelectedIndex();
					JsonArray nja = new JsonArray();
					nja=hm.get(stateCB.getSelectedItem());
					if(cityCB.getItemCount()!=0){
						cityCB.removeAllItems();
					}
					for(int o = 0;o<nja.size();o++){
						JsonObject subobj = nja.get(o).getAsJsonObject();
						cityCB.addItem(subobj.get("city").getAsString());
					}			
			}}));
		//add comboboxes into the main panel
		lp.add(stateCB);
		lp.add(cityCB);
	}
	
	public void getWeatherData(String stationName) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//first get the chosen station's json file URL
		//read the URL to get the json file
		System.out.println(URLhm.get(stationName));
		URL url = new URL(URLhm.get(stationName));
		URLConnection uc = url.openConnection();
		InputStream input = uc.getInputStream();
		BufferedReader reader = new BufferedReader(new InputStreamReader(input, Charset.forName("UTF-8")));
		String jsonString = new Scanner(reader).useDelimiter("\\Z").next();
		
		//then read the json file to get all data we need
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(jsonString);
		JsonObject observ = object.get("observations").getAsJsonObject();
		JsonArray data = observ.get("data").getAsJsonArray();
		for(int i = 0; i<data.size(); i++){
			JsonObject obj = data.get(i).getAsJsonObject();
			
			dateTime[i] = obj.get("local_date_time").toString();
			temp[i] = obj.get("air_temp").getAsDouble();
			apptemp[i] = obj.get("apparent_t").getAsDouble();
			dew[i] = obj.get("dewpt").toString();
			rel[i] = obj.get("rel_hum").toString();
			deltat[i] = obj.get("delta_t").toString();
			winddir[i] = obj.get("wind_dir").toString();
			press[i] = obj.get("press").toString();
			rain[i] = obj.get("rain_trace").toString();
		}
	}
	//return some value
	public String[] getTime(){
		return dateTime;
	}
	public double[] getTemp(){
		return temp;
	}
	public double[] getAppTemp(){
		return apptemp;
	}
	public String[] getDew(){
		return dew;
	}
	public String[] getRel(){
		return rel;
	}
	public String[] getDeltat(){
		return deltat;
	}
	public String[] getWinddir(){
		return winddir;
	}
	public String[] getPress(){
		return press;
	}
	public String[] getRain(){
		return rain;
	}
	
	public JComboBox<String> getCity(){
		return cityCB;
	}
	public JComboBox<String> getstate(){
		return stateCB;
	}
	
	public JPanel getMainPanel(){
		return lp;
	}
	
	public HashMap<String, String> getURLForStation(){
		return URLhm;
	}
}
