package WeatherApp;

import static org.junit.Assert.*;

import java.awt.Desktop.Action;
import java.awt.event.ActionEvent;
import java.io.FileNotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class WeatherAppTesting {
	
	//retrive data
	//add button
	//station button
	//remove button
	//ok button
	//graph button
	//refresh button?!!
	//
	
	@Test
	public void retriveBOMDataTest() {
		
	}
	
	@Test
	public void retriveForecastDataTest() {
		
	}
	
	@Test
	public void addFavTest() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		favs favs = new favs();
		favs.favs.add("York");
		assertEquals("York",favs.getFavs().get(favs.favs.size() - 1));
	}
	
	@Test
	public void removeFavTest() throws JsonIOException, JsonSyntaxException, FileNotFoundException {
		favs favs = new favs();
		favs.favs.add("York");
		favs.favs.add("B");
		favs.favs.remove("York");
		assertEquals("B",favs.getFavs().get(favs.favs.size() - 1));
	}	
}
