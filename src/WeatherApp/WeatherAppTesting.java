package WeatherApp;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.MalformedURLException;

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
	public void addFavTest() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException {
		favs favs = new favs();
		favs.favs.add("York");
		assertEquals("York",favs.getFavs().get(favs.favs.size() - 1));
	}
	
	@Test
	public void removeFavTest() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException {
		favs favs = new favs();
		favs.favs.add("York");
		favs.favs.add("B");
		favs.favs.remove("York");
		assertEquals("B",favs.getFavs().get(favs.favs.size() - 1));
	}	
	
	
	
}
