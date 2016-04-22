package WeatherApp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.*;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import com.google.gson.*;
public class retriveData{
	ArrayList<String> favs = new ArrayList<String>();
	Path uc = Paths.get("UsersChoice.txt");
	Charset cs = Charset.forName("US-ASCII");
	
	public JPanel getjsp() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
		String state;
		JComboBox<String> stateCB = new JComboBox<String>();
		JComboBox<String> cityCB = new JComboBox<String>();
		HashMap<String, JsonArray> hm = new HashMap<String, JsonArray>();
		new ArrayList<String>();
		JsonArray ar = new JsonArray();
		JPanel lp = new JPanel();
		
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
		
		//Get user's choices 
		try(BufferedReader reader = Files.newBufferedReader(uc,cs)) {
			while(reader.readLine()!=null){
				favs.add(reader.readLine());
			}
		} catch (IOException e1) {
			System.out.println("read failed");
			e1.printStackTrace();
		}
		
		//add selected station in favorites
		JButton addfav = new JButton("ADD");
		addfav.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				favs.add(cityCB.getSelectedItem().toString());
					try(BufferedWriter writer = Files.newBufferedWriter(uc,cs,StandardOpenOption.APPEND);) {
						writer.write("\r\n" + favs.get(favs.size()-1) + "\r\n");
					} catch (IOException e1) {
						System.out.println("write failed");
						e1.printStackTrace();
					}
			}});
		
		//remove selected station in favorites
		JButton removefav = new JButton("remove");
		removefav.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				favs.remove(cityCB.getSelectedItem().toString());
				try(BufferedWriter writer = Files.newBufferedWriter(uc,cs,StandardOpenOption.TRUNCATE_EXISTING);) {
					for(int i = 0;i<favs.size();i++){
					writer.write(favs.get(i) + "\r\n");
					}
				} catch (IOException e1) {
					System.out.println("write failed");
					e1.printStackTrace();
				}
			}});
		
		//confirm and display all selected stations
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
	lp.add(addfav);
	lp.add(removefav);
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
		double[] temp = new double[5];
		double[] apptemp = new double[5];
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
			temp[i] = obj.get("air_temp").getAsDouble();
			apptemp[i] = obj.get("apparent_t").getAsDouble();
			dew[i] = obj.get("dewpt").getAsString();
			rel[i] = obj.get("rel_hum").getAsString();
			deltat[i] = obj.get("delta_t").getAsString();
			winddir[i] = obj.get("wind_dir").getAsString();
			press[i] = obj.get("press").getAsString();
			rain[i] = obj.get("rain_trace").getAsString();
		}
				
		String[] coName = {"Date/Time WST","Temp","App Temp","Dew Point","Rel Hum","Delta-T","Wind Direction","Press MSL hPa","Rain since 9am"};
		Object[][] datas = {
				{dateTime[2],temp[2],apptemp[2],dew[2],rel[2],deltat[2],winddir[2],press[2],rain[2]},
				{dateTime[1],temp[1],apptemp[1],dew[1],rel[1],deltat[1],winddir[1],press[1],rain[1]},
				{dateTime[0],temp[0],apptemp[0],dew[0],rel[0],deltat[0],winddir[0],press[0],rain[0]}
		};
		JPanel wjp = new JPanel();
		
		//show the graph
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
				JFrame gf = new JFrame("Lateast Weather Observations(3 days)");
				gf.setSize((int)width/2,(int)height/2);
				gf.setLocation((int)width/4,(int)height/4); 
				gf.setResizable(false);
				
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
		        linedataset.addValue(temp[2], "Air Temperature", dateTime[2]);
		        linedataset.addValue(temp[1], "Air Temperature", dateTime[1]);
		        linedataset.addValue(temp[0], "Air Temperature", dateTime[0]);
		        //Temperature
		        linedataset.addValue(apptemp[2], "Apparent Temperature", dateTime[2]);
		        linedataset.addValue(apptemp[1], "Apparent Temperature", dateTime[1]);
		        linedataset.addValue(apptemp[0], "Apparent Temperature", dateTime[0]);
		        ChartPanel gp = new ChartPanel(chart);
		        
				gf.add(gp);
				gf.setVisible(true);
			    gf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			}});
		wjp.add(bp,BorderLayout.PAGE_END);
		
		wjf.add(wjp);
		wjf.setVisible(true);
	    wjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
	
}
