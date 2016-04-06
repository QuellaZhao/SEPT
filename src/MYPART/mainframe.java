package MYPART;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class mainframe extends JFrame implements ActionListener{
JFrame jf,jf2;
JPanel jp;
JEditorPane jp2; 
JLabel choose;
JRadioButton st1,st2,st3,st4,st5,st6,st7,st8;
String chosen;
retriveData rd = new retriveData();

public mainframe(){
	jf = new JFrame("What's the weather today?");
	jf.setSize(500, 350);
	jf.setLocation(400, 200);
	
	jp = new JPanel();
	jp.setLayout(null);
	
	choose = new JLabel("Choose a station you like please :)");
	choose.setBounds(140, 10, 200, 50);
	jp.add(choose);
	
	st1 = new JRadioButton("NSW");
	st1.setBounds(160, 50, 150, 30);
	jp.add(st1);
	
	st2 = new JRadioButton("VIC");
	st2.setBounds(160, 80, 150, 30);
	jp.add(st2);
	
	st3 = new JRadioButton("QLD");
	st3.setBounds(160, 110, 150, 30);
	jp.add(st3);
	
	st4 = new JRadioButton("WA");
	st4.setBounds(160, 140, 150, 30);
	jp.add(st4);
	
	st5 = new JRadioButton("SA");
	st5.setBounds(160, 170, 150, 30);
	jp.add(st5);
	
	st6 = new JRadioButton("TAS");
	st6.setBounds(160, 200, 150, 30);
	jp.add(st6);
	
	st7 = new JRadioButton("ACT");
	st7.setBounds(160, 230, 150, 30);
	jp.add(st7);
	
	st8 = new JRadioButton("NT");
	st8.setBounds(160, 260, 150, 30);
	jp.add(st8);
	
	st1.addActionListener(this);
	st2.addActionListener(this);
	st3.addActionListener(this);
	st4.addActionListener(this);
	st5.addActionListener(this);
	st6.addActionListener(this);
	st7.addActionListener(this);
	st8.addActionListener(this);
	jf.add(jp);
    jf.setVisible(true);
    jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
}



public void showweather() {
	jp2 = new JEditorPane();
	jf2 = new JFrame();
	jf2.setSize(500, 350);
	jf2.setLocation(400, 200);
	jf2.add(jp2);
	jf2.setVisible(true);
	jf2.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
}

public void actionPerformed(ActionEvent ae) {
	showweather();
	if (ae.getActionCommand() == "NSW") {
		chosen = new String();
		chosen = "NSW";
    }
    else if(ae.getActionCommand() == "VIC"){
    	chosen = new String();
		chosen = "VIC";
    }
    else if(ae.getActionCommand() == "QLD"){
    	chosen = new String();
		chosen = "QLD";
    }
    else if(ae.getActionCommand() == "WA"){
    	chosen = new String();
		chosen = "WA";
    }
    else if(ae.getActionCommand() == "SA"){
    	chosen = new String();
		chosen = "SA";
    }
    else if(ae.getActionCommand() == "TAS"){
    	chosen = new String();
		chosen = "TAS";
    }
    else if(ae.getActionCommand() == "ACT"){
    	chosen = new String();
		chosen = "ACT";
    }
    else if(ae.getActionCommand() == "NT"){
    	chosen = new String();
		chosen = "NT";
    }
System.out.println(chosen);
}
}
