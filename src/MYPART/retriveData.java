package MYPART;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.swing.*;
import com.google.gson.*;
public class retriveData{
	public JPanel getjsp() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		String state,city,url;
		JLabel chosenOne1 = new JLabel();
		JLabel chosenOne2 = new JLabel();
		ButtonGroup cbjGroup = new ButtonGroup();
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
			for(int j = 0; j<ar.size(); j++){
				JsonObject subobj = ar.get(j).getAsJsonObject();
				city = subobj.get("city").getAsString();
				//url = subobj.get("url").getAsString();
				
				JRadioButton cjb = new JRadioButton(city);	
				cjb.setSize(150,20);
				cjb.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						chosenOne1.setText("The Station you chose is:");
						chosenOne1.setFont(new java.awt.Font("Dialog", 1, 15));
						chosenOne1.setSize(150,100);
						chosenOne2.setText(cjb.getText());
						chosenOne2.setFont(new java.awt.Font("Dialog", 1, 15));
						chosenOne2.setSize(150,100);
					}
					
				});
				cbjGroup.add(cjb);
		        cjb.setSize(100, 30);
		        lp.add(cjb);
			
			}
			JLabel devideline = new JLabel("---------------------------");
			devideline.setSize(500, 50);
			
			lp.add(devideline);
		}
		JLabel devideline = new JLabel("===========================");
		lp.add(devideline);
	lp.add(chosenOne1);
	lp.add(chosenOne2);
	
	JButton confirm = new JButton("OK");
	confirm.addActionListener(new ActionListener(){

		@Override
		public void actionPerformed(ActionEvent e) {
			JFrame weather = new JFrame("The weather of "+chosenOne2.getText());
			double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
			double height = Toolkit.getDefaultToolkit().getScreenSize().height;
			weather.setSize((int)width/2,(int)height/2);
			weather.setLocation((int)width/4,(int)height/4); 
			weather.setResizable(false);
			
			weather.setVisible(true);
		    weather.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}});
	lp.add(confirm);
	return lp;
	}
}
