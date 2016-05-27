package WeatherApp;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.print.attribute.standard.RequestingUserName;
import javax.swing.*;

import org.junit.experimental.theories.Theories;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class mainframe{
	private JFrame jf;
	private JPanel jp;
	private JScrollPane jsp;
	private String chosen, stationClicked,chosenResource;
	private retriveDatafromBOM rdb;
	private retriveDatafromForecast rdf;
	private favs fa;
	
	public String getResources(String resource){
		chosenResource = resource;
		return chosenResource;
	}
	public void mainF(){
		JFrame mFrame = new JFrame("Chose the dada resource please!");
		JPanel mPanel = new JPanel();
		JButton bJButton,fJButton;
		double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		double height = Toolkit.getDefaultToolkit().getScreenSize().height;
		mFrame.setSize((int)width/3,(int)height/4);
		mFrame.setLocation((int)width*1/3,(int)height*1/4); 
		mFrame.setResizable(false);
		bJButton = new JButton("BOM");
		fJButton = new JButton("Forecast");
		ActionListener buttonAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(e.getActionCommand().equals("BOM")){
					try {mFrame();} 
					catch (JsonIOException | JsonSyntaxException | IOException e1) {e1.printStackTrace();}
				}
				else if(e.getActionCommand().equals("Forecast")){
					try {
					favFrame("the Forecast weather");
					}
					catch (JsonIOException | JsonSyntaxException | IOException e1) {e1.printStackTrace();}
				}
				else{System.out.println("Please choose a data resource!");}
			}
		};
		bJButton.addActionListener(buttonAction);
		fJButton.addActionListener(buttonAction);
		mPanel.add(fJButton);
		mPanel.add(bJButton);
		mFrame.add(mPanel);
	    mFrame.setVisible(true);
	    mFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	public void mFrame() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//The main menu, shows when the program launched
		fa = new favs();
		rdb = new retriveDatafromBOM();
		jf = new JFrame("What's the weather?");
		double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		double height = Toolkit.getDefaultToolkit().getScreenSize().height;
		jf.setSize((int)width/2,(int)height/2);
		jf.setLocation((int)width/4,(int)height/4); 
		jf.setResizable(false);
		jp = fa.getFavPa();
		jf.add(jp);
	    jf.setVisible(true);
	    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}
	
	public void favFrame(String resource) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//a window shows users favorite stations
		fa = new favs();
		rdb = new retriveDatafromBOM();
		rdf = new retriveDatafromForecast();
		JFrame njf = new JFrame("What's the weather today?");
		double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		double height = Toolkit.getDefaultToolkit().getScreenSize().height;
		njf.setSize((int)width/5,(int)height/2);
		njf.setLocation((int)width*2/5,(int)height/5); 
		JPanel wjp = new JPanel();
		JScrollPane wsjp = new JScrollPane(wjp);
		wjp.setLayout(new GridLayout(700, 1));
		JButton jl;
		//when the user clicks station button, it will shows its weather data
		//which is retrieved from specific data resource users choose
		for(int n=0;n<fa.getFavs().size();n++){
			jl = new JButton(fa.getFavs().get(n));				
			jl.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					try {
						if(resource.equals("OK")){
							stationClicked = e.getActionCommand();
							rdb.getWeatherData(stationClicked);
						}
						else if(resource.equals("the Forecast weather")){
							rdf.getWeatherData();
						}
						else {
							System.out.println("Please select a data resource.");
						}
					} catch (JsonIOException | JsonSyntaxException e2) {e2.printStackTrace();}
				    stationClicked = e.getActionCommand();
					tableFrame(resource,stationClicked);
				} catch (JsonIOException | JsonSyntaxException | IOException e1) {
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
	}
	
	public void tableFrame(String resource, String stationName) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//the window for the weather table
		showTable st = new showTable(resource, stationName);
		JFrame wjf = new JFrame("WEATHER");
		double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		double height = Toolkit.getDefaultToolkit().getScreenSize().height;
		wjf.setSize((int)width,(int)height/2);
		wjf.setLocation(0,(int)height/3); 
		wjf.setResizable(false);
		wjf.add(st.getTablePanel());
		wjf.setVisible(true);
	    wjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void graphFrame(String resource) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		//the window for graphs
		showGraph sg = new showGraph(resource);
		double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
		double height = Toolkit.getDefaultToolkit().getScreenSize().height;
		JFrame gf = new JFrame("Lateast Weather Observations");
		gf.setSize((int)width/2,(int)height/2);
		gf.setLocation((int)width/4,(int)height/4); 
		gf.setResizable(false);
		gf.add(sg.getGraphPanel());
		gf.setVisible(true);
	    gf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	//return some value
	
	public String getClicked(){
		return stationClicked;
	}
}