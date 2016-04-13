package MYPART;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import javax.swing.*;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.google.gson.*;
public class retriveData{
	public JPanel getjsp() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		String state,city,url;
		int j;
		ArrayList<String> favs = new ArrayList<String>();
		JLabel chosenOne1 = new JLabel();
		JPanel lp = new JPanel(new GridLayout(140, 5));
		lp.setSize(900, 1300);
		JsonParser parser = new JsonParser();
		JsonObject object = (JsonObject) parser.parse(new FileReader("stations.json"));
		JsonArray array = object.get("Array").getAsJsonArray();
		for(int i = 0; i<array.size(); i++){
			JsonObject obj = array.get(i).getAsJsonObject();
			state = obj.get("state").getAsString();	
			JLabel statelb = new JLabel(state);
			statelb.setFont(new java.awt.Font("Dialog", 1, 15));
			statelb.setSize(100, 50);
			lp.add(statelb);
						
			JsonArray ar = obj.get("stations").getAsJsonArray();
			for(j = 0; j<ar.size(); j++){
				JsonObject subobj = ar.get(j).getAsJsonObject();
				city = subobj.get("city").getAsString();
				url = subobj.get("url").getAsString();

				
				JCheckBox cjb = new JCheckBox(city);	
				cjb.setSize(150,20);
				cjb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						if (cjb.isSelected()) 
						   {
							favs.add(cjb.getText().toString());
						   }
					}
					
				});
		        cjb.setSize(100, 30);
		        lp.add(cjb);
			
			}
			JLabel devideline = new JLabel("---------------------------");
			devideline.setSize(500, 50);
			
			lp.add(devideline);
		}
		JLabel devideline = new JLabel("===========================");
		lp.add(devideline);
	//lp.add(chosenOne1);
	//for(l=0;l<=200;l++){
	//	lp.add(chosenOne2[l]);
	//}
	
	JButton confirm = new JButton("OK");
	confirm.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame njf = new JFrame("What's the weather today?");
			double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
			double height = Toolkit.getDefaultToolkit().getScreenSize().height;
			njf.setSize((int)width/2,(int)height/2);
			njf.setLocation(0,0); 
			njf.setResizable(false);
			
			JPanel wjp = new JPanel();
			JButton jl;
			for(int n=0;n<favs.size();n++){
				jl = new JButton(favs.get(n));
				jl.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent e) {
					try {
						OpenUrlAction();
					} catch (JsonIOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (JsonSyntaxException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}});
				wjp.add(jl);						
			}
			njf.add(wjp);
			njf.setVisible(true);
		    njf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}});
	
	
	lp.add(confirm);
	return lp;
	}
	public void OpenUrlAction() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		JFrame wjf = new JFrame("WEATHERRRR");
		JPanel wejp = new JPanel();
		double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		double height = Toolkit.getDefaultToolkit().getScreenSize().height;
		wjf.add(wejp);
		wjf.setSize((int)width,(int)height);
		wjf.setLocation(0,0); 
		wjf.setResizable(false);
		
		String dateTime;
//		JsonParser parser = new JsonParser();
	//	JsonObject object = (JsonObject) parser.parse(new FileReader("York.json"));
		//dateTime = object.get("local_date_time").getAsString();
				
		String[] coName = {"Date/Time WST","Temp","App Temp","Dew Point","Rel Hum","Delta-T","Wind Direction","Press MSL hPa","Rain since 9am"};
		Object[][] data = {
				{"a","a","a","a","a","a","a","a","a"}
		};
		JPanel wjp = new JPanel();
		JTable wTable = new JTable(data,coName);
		wjp.setLayout(new BorderLayout());
		wjp.add(wTable.getTableHeader(),BorderLayout.PAGE_START);
		wjp.add(wTable,BorderLayout.CENTER);
		
		wjf.add(wjp);
		wjf.setVisible(true);
	    wjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
}
