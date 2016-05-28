package WeatherApp;

import java.io.IOException;
import java.net.MalformedURLException;

import javax.swing.*;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class mainmenu extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{		
		mainframe mf = new mainframe();
		mf.mainF();
	}
	}