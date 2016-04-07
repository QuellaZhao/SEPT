package MYPART;

import java.awt.FlowLayout;
import java.awt.Toolkit;
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

public mainframe() throws JsonIOException, JsonSyntaxException, FileNotFoundException{
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
}

}

