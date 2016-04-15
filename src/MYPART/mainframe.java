package MYPART;

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
	jf.setSize((int)width,(int)height);
	jf.setLocation(0,0); 
	jf.setResizable(false);
	
	jp = new JPanel();	
	jp.setLayout(new FlowLayout());
	choose = new JLabel("Choose a station please :)");
	jp.add(choose);
	

	retriveData rd = new retriveData();
	jp = (JPanel) rd.getjsp();
	
	
	jsp = new JScrollPane(jp);
	jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	jsp.setSize(500,500);
	jf.add(jsp);
    jf.setVisible(true);
    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
    jf.addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
		jf.dispose();	
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
	});
    return jf;
}

}

