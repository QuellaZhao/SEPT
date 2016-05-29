package WeatherApp;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import java.io.IOException;
import java.net.MalformedURLException;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

public class WeatherAppTesting {
	
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
		favs.favs.add("Melbourne Airport");
		favs.favs.remove("York");
		assertEquals("Melbourne Airport",favs.getFavs().get(favs.favs.size() - 1));
	}	
	
	//tests don't working
	@Test
	public void URLHashMapTest1() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		retriveDatafromBOM rb = new retriveDatafromBOM();
		rb.getWeatherData("York");
		assertEquals("http://www.bom.gov.au/fwo/IDW60801/IDW60801.94623.json", rb.getURLForStation().get("York").toString());
	}
	
	@Test
	public void URLHashMapTest2() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		retriveDatafromBOM rb = new retriveDatafromBOM();
		rb.getWeatherData("Melbourne Airport");
		assertEquals("http://www.bom.gov.au/fwo/IDV60801/IDV60801.94866.json", rb.getURLForStation().get("Melbourne Airport"));

	}
	
	@Test
	public void URLHashMapTest3() throws JsonIOException, JsonSyntaxException, MalformedURLException, IOException{
		retriveDatafromBOM rb = new retriveDatafromBOM();
		rb.getWeatherData("Batemans Bay");
		assertEquals("http://www.bom.gov.au/fwo/IDN60903/IDN60903.94941.json", rb.getURLForStation().get("Batemans Bay"));

	}
}