package WeatherApp;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.*;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class mainmenu extends JFrame{
	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{		
		mainframe mf = new mainframe();
		mf.mainF();
	}
	}