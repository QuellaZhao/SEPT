package WeatherApp;

import java.io.FileNotFoundException;

import javax.swing.*;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class mainmenu extends JFrame{
	public static void main(String[] args) throws JsonIOException, JsonSyntaxException, FileNotFoundException{		
		mainframe mf = new mainframe();
		mf.mframe();
	}
	}
