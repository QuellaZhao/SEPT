package WeatherApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.jws.soap.SOAPBinding.Use;
import javax.swing.*;
import javax.xml.transform.Templates;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.gson.*;
public class retriveData{
	public JPanel getjsp() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		String state,city,url;
		int j;
		JComboBox stateCB = new JComboBox();
		JComboBox cityCB = new JComboBox();
		ArrayList<String> favs = new ArrayList<String>();
		ArrayList<String> sts = new ArrayList<String>();
		ArrayList<String> cts = new ArrayList<String>();
		JPanel lp = new JPanel();
		
		//get stations names from json file
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader("stations.json"));
		JsonArray array = object.get("Array").getAsJsonArray();
		for(int i = 0; i<array.size(); i++){
			JsonObject obj = array.get(i).getAsJsonObject();
			state = obj.get("state").getAsString();	
			sts.add(state);
			stateCB.addItem(state);

			JsonArray ar = obj.get("stations").getAsJsonArray();
			for(j = 0; j<ar.size(); j++){
				JsonObject subobj = ar.get(j).getAsJsonObject();
				city = subobj.get("city").getAsString();
			//	url = subobj.get("url").getAsString();
				//use checkbox to display stations
				cts.add(city);
				cityCB.addItem(city);		
			}
		}
		lp.add(stateCB);
		lp.add(cityCB);
	
		cityCB.addActionListener((new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				if(cityCB.getSelectedItem()!=""){
					favs.add(cityCB.getSelectedItem().toString());
				}
			}}));
		//a confirm button	
		JButton confirm = new JButton("OK");
	confirm.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame njf = new JFrame("What's the weather today?");
			double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
			double height = Toolkit.getDefaultToolkit().getScreenSize().height;
			njf.setSize((int)width/5,(int)height/2);
			njf.setLocation((int)width*2/5,(int)height/5); 
		
			//put the chosen stations into a new window
			JPanel wjp = new JPanel();
			JScrollPane wsjp = new JScrollPane(wjp);
			wjp.setLayout(new GridLayout(700, 1));
			JButton jl;
			for(int n=0;n<favs.size();n++){
				jl = new JButton(favs.get(n));				
				jl.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						OpenUrlAction();
					} catch (JsonIOException e1) {
						e1.printStackTrace();
					} catch (JsonSyntaxException e1) {
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}});
				wjp.add(jl);			
			}
			wsjp.setVerticalScrollBarPolicy(wsjp.VERTICAL_SCROLLBAR_ALWAYS);
			wsjp.setHorizontalScrollBarPolicy(wsjp.HORIZONTAL_SCROLLBAR_NEVER);
			njf.add(wsjp);
			njf.setVisible(true);
		    njf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    	    //the old window closedsd
		}});
	lp.add(confirm);
	return lp;
	}
	//get data from station's json file and add data into a new window with a table
	@SuppressWarnings("null")
	public void OpenUrlAction() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		JFrame wjf = new JFrame("WEATHER");
		JPanel wejp = new JPanel();
		double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		double height = Toolkit.getDefaultToolkit().getScreenSize().height;
		wjf.add(wejp);
		wjf.setSize((int)width,(int)height/4);
		wjf.setLocation(0,(int)height/3); 
		wjf.setResizable(false);
		
		String[] dateTime = new String[5];
		String[] temp = new String[5];
		String[] apptemp = new String[5];
		String[] dew = new String[5];
		String[] rel = new String[5];
		String[] deltat = new String[5];
		String[] winddir = new String[5];
		String[] press = new String[5];
		String[] rain = new String[5];
		
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader("York.json"));
		JsonObject observ = object.get("observations").getAsJsonObject();
		JsonArray data = observ.get("data").getAsJsonArray();
		for(int i = 0; i<data.size(); i++){
			JsonObject obj = data.get(i).getAsJsonObject();
			dateTime[i] = obj.get("local_date_time").getAsString();
			temp[i] = obj.get("local_date_time").getAsString();
			apptemp[i] = obj.get("local_date_time").getAsString();
			dew[i] = obj.get("local_date_time").getAsString();
			rel[i] = obj.get("local_date_time").getAsString();
			deltat[i] = obj.get("local_date_time").getAsString();
			winddir[i] = obj.get("local_date_time").getAsString();
			press[i] = obj.get("local_date_time").getAsString();
			rain[i] = obj.get("local_date_time").getAsString();
		}
				
		String[] coName = {"Date/Time WST","Temp","App Temp","Dew Point","Rel Hum","Delta-T","Wind Direction","Press MSL hPa","Rain since 9am"};
		Object[][] datas = {
				{dateTime[2],temp[2],apptemp[2],dew[2],rel[2],deltat[2],winddir[2],press[2],rain[2]},
				{dateTime[1],temp[1],apptemp[1],dew[1],rel[1],deltat[1],winddir[1],press[1],rain[1]},
				{dateTime[0],temp[0],apptemp[0],dew[0],rel[0],deltat[0],winddir[0],press[0],rain[0]}
		};
		JPanel wjp = new JPanel();
		JTable wTable = new JTable(datas,coName);
		wjp.setLayout(new BorderLayout());
		wjp.add(wTable.getTableHeader(),BorderLayout.PAGE_START);
		wjp.add(wTable,BorderLayout.CENTER);
		
		wjf.add(wjp);
		wjf.setVisible(true);
	    wjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
}
