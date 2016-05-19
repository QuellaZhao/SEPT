package WeatherApp;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class mainframe{
JFrame jf;
JPanel jp;
JScrollPane jsp;
String chosen;

public void mFrame() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
	favs favs = new favs();
	retriveDatafromBOM rb = new retriveDatafromBOM();
	jf = new JFrame("What's the weather today?");
	double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
	double height = Toolkit.getDefaultToolkit().getScreenSize().height;
	jf.setSize((int)width/2,(int)height/2);
	jf.setLocation((int)width/4,(int)height/4); 
	jf.setResizable(false);
	
	jp = favs.getFavPa();
	
	jf.add(jp);
    jf.setVisible(true);
    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	

}

public void favFrame() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
	favs favs = new favs();
	
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
	for(int n=0;n<favs.getFavs().size();n++){
		jl = new JButton(favs.getFavs().get(n));				
		jl.addActionListener(new ActionListener(){
		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				tableFrame();
			} catch (JsonIOException e1) {
				e1.printStackTrace();
			} catch (JsonSyntaxException e1) {
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
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

public void tableFrame() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
	showTable st = new showTable();
	JFrame wjf = new JFrame("WEATHER");
	double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
	double height = Toolkit.getDefaultToolkit().getScreenSize().height;
	wjf.setSize((int)width,(int)height/4);
	wjf.setLocation(0,(int)height/3); 
	wjf.setResizable(false);
	wjf.add(st.getTablePanel());
	wjf.setVisible(true);
    wjf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}

public void graphFrame() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
	showGraph sg = new showGraph();
	double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
	double height = Toolkit.getDefaultToolkit().getScreenSize().height;
	JFrame gf = new JFrame("Lateast Weather Observations(3 days)");
	gf.setSize((int)width/2,(int)height/2);
	gf.setLocation((int)width/4,(int)height/4); 
	gf.setResizable(false);
	gf.add(sg.getGraphPanel());
	gf.setVisible(true);
    gf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
}