package WeatherApp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.JPanel;

import com.google.gson.JsonArray;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

public class retriveDatafromBOM {
	String[] dateTime = new String[5];
	double[] temp = new double[5];
	double[] apptemp = new double[5];
	String[] dew = new String[5];
	String[] rel = new String[5];
	String[] deltat = new String[5];
	String[] winddir = new String[5];
	String[] press = new String[5];
	String[] rain = new String[5];
	
	JComboBox<String> stateCB = new JComboBox<String>();
	JComboBox<String> cityCB = new JComboBox<String>();
	JPanel lp = new JPanel();
	
	public retriveDatafromBOM() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
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
		lp.add(stateCB);
		lp.add(cityCB);
		getWeatherData();
	}
	
	public void getWeatherData() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader("York.json"));
		JsonObject observ = object.get("observations").getAsJsonObject();
		JsonArray data = observ.get("data").getAsJsonArray();
		for(int i = 0; i<data.size(); i++){
			JsonObject obj = data.get(i).getAsJsonObject();
			dateTime[i] = obj.get("local_date_time").getAsString();
			temp[i] = obj.get("air_temp").getAsDouble();
			apptemp[i] = obj.get("apparent_t").getAsDouble();
			dew[i] = obj.get("dewpt").getAsString();
			rel[i] = obj.get("rel_hum").getAsString();
			deltat[i] = obj.get("delta_t").getAsString();
			winddir[i] = obj.get("wind_dir").getAsString();
			press[i] = obj.get("press").getAsString();
			rain[i] = obj.get("rain_trace").getAsString();
		}
	}
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
}
