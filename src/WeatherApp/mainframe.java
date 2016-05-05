package WeatherApp;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.FileNotFoundException;

import javax.swing.*;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class mainframe extends JFrame{
JFrame jf,jf2;
JPanel jp;
JScrollPane jsp;
JLabel choose;
JRadioButton st1,st2,st3,st4,st5,st6,st7,st8;
String chosen;
retriveData rd = new retriveData();

public JFrame mframe() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
	jf = new JFrame("What's the weather today?");
	double width = Toolkit.getDefaultToolkit().getScreenSize().width; 
	double height = Toolkit.getDefaultToolkit().getScreenSize().height;
	jf.setSize((int)width/2,(int)height/2);
	jf.setLocation((int)width/4,(int)height/4); 
	jf.setResizable(false);
	
	jp = new JPanel();	
	jp.setLayout(new FlowLayout());
	choose = new JLabel("Choose a station please :)");
	jp.add(choose);
	

	retriveData rd = new retriveData();
	jp = (JPanel) rd.getjsp();
	
	jf.add(jp);
    jf.setVisible(true);
    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
    jf.addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
		//	jf.dispose();	
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			
		}
	});
    return jf;
}

}

